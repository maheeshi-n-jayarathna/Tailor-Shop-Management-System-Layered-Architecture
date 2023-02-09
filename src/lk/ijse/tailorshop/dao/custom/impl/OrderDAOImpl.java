package lk.ijse.tailorshop.dao.custom.impl;



import lk.ijse.tailorshop.dao.custom.OrderDAO;
import lk.ijse.tailorshop.dao.util.DBUtil;
import lk.ijse.tailorshop.entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean save(Orders entity) throws SQLException {
        return DBUtil.executeUpdate("INSERT INTO orders (orderId,customerId, description, advance, totalPrice) VALUES (?,?,?,?,?,?)",
                entity.getOrderId(),
                entity.getCustomerId(),
                entity.getDescription(),
                entity.getAdvance(),
                entity.getTotalPrice(),
                entity.getStatus()
        );
    }

    @Override
    public boolean update(Orders order) throws SQLException {
        return DBUtil.executeUpdate("UPDATE orders SET customerId=? ,description=? ,advance=? ,totalPrice=? ,status=? WHERE orderId=?",
                order.getCustomerId(),
                order.getDescription(),
                order.getAdvance(),
                order.getTotalPrice(),
                order.getStatus(),
                order.getOrderId()
        );
    }

    @Override
    public boolean deleteByPk(String orderId) throws SQLException {
        return DBUtil.executeUpdate("DELETE FROM orders WHERE orderId=?",orderId);
    }

    @Override
    public List<Orders> findAll() throws SQLException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM orders");
        List<Orders> orderList= new ArrayList<>();
        while (rst.next()){
            Orders order = new Orders(
                    rst.getString("orderId"),
                    rst.getString("customerId"),
                    rst.getString("description"),
                    rst.getDouble("advance"),
                    rst.getDouble("totalPrice"),
                    rst.getString("status")
            );
            orderList.add(order);
        }
        return orderList;
    }

    @Override
    public Optional<Orders> findByPk(String orderId) throws SQLException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM orders WHERE orderId=?", orderId);
        if(rst.next()){
            return Optional.of(new Orders(
                    rst.getString("orderId"),
                    rst.getString("customerId"),
                    rst.getString("description"),
                    rst.getDouble("advance"),
                    rst.getDouble("totalPrice"),
                    rst.getString("status")
            ));

        }
        return Optional.empty();
    }

    @Override
    public Optional<String> findLastPk() throws SQLException {
        ResultSet rst= DBUtil.executeQuery("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1");
        if(rst.next()){
            return Optional.of(rst.getString(1));
        }
        return Optional.empty();
    }

    @Override
    public long count() throws SQLException {
        ResultSet rst  = DBUtil.executeQuery("SELECT COUNT(orderId) AS count FROM orders");
        rst.next();
        return rst.getInt(1);
    }

    @Override
    public List<Orders> findAllByCustomerId(String customerId) throws SQLException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM orders WHERE customerId=?",customerId);
        List<Orders> orderList = new ArrayList<>();
        while (rst.next()){
            Orders order = new Orders(
                    rst.getString("orderId"),
                    rst.getString("customerId"),
                    rst.getString("description"),
                    rst.getDouble("advance"),
                    rst.getDouble("totalPrice"),
                    rst.getString("status")
            );
            orderList.add(order);
        }
        return orderList;
    }

    @Override
    public boolean orderReturn(String orderId, String returned) throws SQLException {
        return DBUtil.executeUpdate("UPDATE orders SET status=? WHERE orderId=?", returned,orderId);
    }
}
