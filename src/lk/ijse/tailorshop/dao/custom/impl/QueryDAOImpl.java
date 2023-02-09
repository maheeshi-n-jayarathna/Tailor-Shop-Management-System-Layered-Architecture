package lk.ijse.tailorshop.dao.custom.impl;

import lk.ijse.tailorshop.dao.custom.QueryDAO;
import lk.ijse.tailorshop.dao.util.DBUtil;
import lk.ijse.tailorshop.entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<Orders>findAllNonReturnedOrdersByFabricId(String fabricId) throws SQLException {
        List<Orders> orders = new ArrayList<>();
        ResultSet rst = DBUtil.executeQuery("SELECT  o.orderid, o.customerid, o.description, o.advance, o.totalprice, o.status FROM orders o LEFT JOIN orderdetails od ON o.orderId = od.orderId WHERE od.trueReturnDate IS NULL AND od.fabricId = ?",fabricId);
        while (rst.next()){
            orders.add(new Orders(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getString(6)
            ));
        }
        return orders;
    }

    @Override
    public List<Orders> findAllNonReturnedOrdersByCustomerId(String customerId) throws SQLException {
        List<Orders> orders = new ArrayList<>();
        ResultSet rst = DBUtil.executeQuery("SELECT  o.orderid, o.customerid, o.description, o.advance, o.totalprice, o.status FROM orders O LEFT JOIN orderdetails OD ON O.orderId = OD.orderId WHERE OD.trueReturnDate IS NULL AND o.customerId = ? GROUP BY  o.orderId;",customerId);
        while (rst.next()){
            orders.add(new Orders(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getString(6)
            ));
        }
        return orders;
    }
}
