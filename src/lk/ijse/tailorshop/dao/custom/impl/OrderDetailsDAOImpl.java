package lk.ijse.tailorshop.dao.custom.impl;



import lk.ijse.tailorshop.dao.custom.OrderDetailsDAO;
import lk.ijse.tailorshop.dao.util.DBUtil;
import lk.ijse.tailorshop.entity.OrderDetails;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public boolean save(OrderDetails entity) throws SQLException {
        return DBUtil.executeUpdate("INSERT INTO orderdetails VALUES(?,?,?,?,?,?,?,?,?)",
                entity.getOrderId(),
                entity.getFabricId(),
                entity.getMeasurementId(),
                entity.getReceiveDate(),
                entity.getReturnDate(),
                entity.getCost(),
                entity.getTrueReturnDate(),
                entity.getQty(),
                entity.getUnitPrice()
        );
    }

    @Override
    public boolean update(OrderDetails entity) throws SQLException {
        return DBUtil.executeUpdate("UPDATE orderdetails SET fabricId=?,measurementId=?,receiveDate=?,returnDate=?,cost=?,trueReturnDate=?,qty=?,unitPrice=? WHERE orderId=?",
                entity.getFabricId(),
                entity.getMeasurementId(),
                entity.getReceiveDate(),
                entity.getReturnDate(),
                entity.getCost(),
                entity.getTrueReturnDate(),
                entity.getQty(),
                entity.getUnitPrice(),
                entity.getOrderId()
        );
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException {
        return  DBUtil.executeUpdate("Delete From orderdetails where orderId=?",pk);
    }

    @Override
    public List<OrderDetails> findAll() throws SQLException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM orderdetails");
        List<OrderDetails> orderDetails = new ArrayList<>();
        while (rst.next()) {
            orderDetails.add(getOrderDetails(rst));
        }
        return orderDetails;
    }

    @Override
    public Optional<OrderDetails> findByPk(String pk) throws SQLException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM orderdetails WHERE orderId = ?", pk);
        if(rst.next()) {
            return Optional.of(getOrderDetails(rst));
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> findLastPk() throws SQLException {
        ResultSet rst= DBUtil.executeQuery("SELECT orderId FROM orderdetails ORDER BY orderId DESC LIMIT 1");
        if(rst.next()){
            return Optional.of(rst.getString(1));
        }
        return Optional.empty();
    }

    @Override
    public long count() throws SQLException {
        ResultSet rst  = DBUtil.executeQuery("SELECT COUNT(orderId) AS count FROM orderdetails");
        rst.next();
        return rst.getInt(1);
    }

    @Override
    public List<OrderDetails> findAllByPk(String orderId) throws SQLException{
        List<OrderDetails> orderDetails = new ArrayList<>();
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM orderdetails WHERE orderId = ?", orderId);
        while (rst.next()){
            orderDetails.add(getOrderDetails(rst));
        }
        return orderDetails;
    }

    @Override
    public boolean purchaseOrderDetailByOrderId(String orderId, LocalDate now) throws SQLException {
        return DBUtil.executeUpdate("UPDATE orderdetails SET trueReturnDate=? WHERE orderId=?", Date.valueOf(now),orderId);
    }

    private OrderDetails getOrderDetails(ResultSet rst) throws SQLException {
        return new OrderDetails(
                rst.getString(1),
                rst.getString(2),
                rst.getString(3),
                (rst.getString(4)!=null)?LocalDate.parse(rst.getString(4)):null,
                (rst.getString(5)!=null)?LocalDate.parse(rst.getString(5)):null,
                rst.getDouble(6),
                (rst.getString(7)!=null)?LocalDate.parse(rst.getString(7)):null,
                rst.getInt(8),
                rst.getDouble(9)
        );
    }
}
