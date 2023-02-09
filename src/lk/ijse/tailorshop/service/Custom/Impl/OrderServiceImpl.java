package lk.ijse.tailorshop.service.Custom.Impl;

import lk.ijse.tailorshop.dao.DaoFactory;
import lk.ijse.tailorshop.dao.DaoTypes;
import lk.ijse.tailorshop.dao.custom.*;
import lk.ijse.tailorshop.db.DBConnection;
import lk.ijse.tailorshop.dto.MeasurementDTO;
import lk.ijse.tailorshop.dto.OrderDTO;
import lk.ijse.tailorshop.dto.OrderDetailsDTO;
import lk.ijse.tailorshop.entity.OrderDetails;
import lk.ijse.tailorshop.entity.Orders;
import lk.ijse.tailorshop.service.Custom.OrderService;
import lk.ijse.tailorshop.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private final MeasurementDAO measurementDAO;
    private final OrderDAO orderDAO;
    private final OrderDetailsDAO orderDetailsDAO;
    private final QueryDAO queryDAO;
    private final FabricDAO fabricDAO;
    private final Convertor convertor;
    private final Connection connection;
    public OrderServiceImpl() {
        measurementDAO = DaoFactory.getInstance().getDAO(DaoTypes.MEASUREMENT);
        orderDAO = DaoFactory.getInstance().getDAO(DaoTypes.ORDER);
        orderDetailsDAO = DaoFactory.getInstance().getDAO(DaoTypes.ORDER_DETAILS);
        queryDAO = DaoFactory.getInstance().getDAO(DaoTypes.QUERY);
        fabricDAO = DaoFactory.getInstance().getDAO(DaoTypes.FABRIC);
        convertor = new Convertor();
        connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public String getNextMeasurementId() {
        try {
            Optional<String> lastPk = measurementDAO.findLastPk();
            if(lastPk.isPresent()){
                String pk=lastPk.get().substring(1);
                pk=String.format("M%03d", Integer.parseInt(pk)+1);
                return pk;
            }
            return "M001";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getNextOrderId() {
        try {
            Optional<String> lastPk = orderDAO.findLastPk();
            if(lastPk.isPresent()){
                String pk=lastPk.get().substring(1);
                pk=String.format("O%03d", Integer.parseInt(pk)+1);
                return pk;
            }
            return "O001";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean saveOrder(OrderDTO orderDTO) {
        try {
            connection.setAutoCommit(false);
            if (!orderDAO.save(convertor.toOrders(orderDTO))){
                connection.rollback();
                return false;
            }
            for (MeasurementDTO measurementDTO : orderDTO.getMeasurementList()){
                if (!measurementDAO.save(convertor.toMeasurement(measurementDTO))){
                    connection.rollback();
                    return false;
                }
            }
            for (OrderDetailsDTO orderDetailsDTO : orderDTO.getOrderDetailsList()){
                if (!orderDetailsDAO.save(convertor.toOrderDetails(orderDetailsDTO))){
                    connection.rollback();
                    return false;
                }
                if (!fabricDAO.sellFabricQtyById(orderDetailsDTO.getFabricId(),orderDetailsDTO.getQty())){
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<OrderDTO> getAllNonReturnOrdersByCustomerId(String customerId) {
        try {
            List<OrderDTO> orders = new ArrayList<>();
            for (Orders order : queryDAO.findAllNonReturnedOrdersByCustomerId(customerId)){
                orders.add(convertor.fromOrders(order));
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderDetailsDTO> getOrderDetailByOrderId(String orderId) {
        try {
            List<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>();
            for(OrderDetails detail : orderDetailsDAO.findAllByPk(orderId)){
                orderDetailsDTOS.add(convertor.fromOrderDetails(detail));
            }
            return orderDetailsDTOS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MeasurementDTO getMeasurementByMeasurementId(String measurementId) {
        try {
            return convertor.fromMeasurement(measurementDAO.findByPk(measurementId).get());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderDTO getOrdersByOrderId(String orderId) {
        try {
            return convertor.fromOrders(orderDAO.findByPk(orderId).get());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean purchaseOrders(List<String> orderIds) {
        try {
            connection.setAutoCommit(false);
            for (String orderId : orderIds) {
                if ((!orderDetailsDAO.purchaseOrderDetailByOrderId(orderId, LocalDate.now()))
                        || (!orderDAO.orderReturn(orderId, "returned"))){
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
                return false;
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


//    @Override
//    public boolean saveOrder(OrderDTO orderDTO) {
//        try {
//            connection.setAutoCommit(false);
//            if (orderDAO.save(convertor.toOrders(orderDTO))){
//                if (saveMeasurement(orderDTO.getMeasurementList())){
//                    if (saveOrderDetails(orderDTO.getOrderDetailsList())){
//                        connection.commit();
//                        return true;
//                    }
//                }
//            }
//            connection.rollback();
//            return false;
//        }catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                throw new RuntimeException(ex);
//            }
//            throw new RuntimeException(e);
//        }finally {
//            try {
//                connection.setAutoCommit(true);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    private boolean saveOrderDetails(List<OrderDetailsDTO> orderDetailsList) throws SQLException {
//        for (OrderDetailsDTO orderDetailsDTO : orderDetailsList){
//            if (orderDetailsDAO.save(convertor.toOrderDetails(orderDetailsDTO))){
//                if (!fabricDAO.sellFabricQtyById(orderDetailsDTO.getFabricId(),orderDetailsDTO.getQty())){
//                    return false;
//                }
//            }else {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean saveMeasurement(List<MeasurementDTO> measurementList) throws SQLException {
//        for (MeasurementDTO measurementDTO : measurementList){
//            if (!measurementDAO.save(convertor.toMeasurement(measurementDTO))){
//                return false;
//            }
//        }
//        return true;
//    }
}
