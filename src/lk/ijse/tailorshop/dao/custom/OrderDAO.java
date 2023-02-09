package lk.ijse.tailorshop.dao.custom;

import lk.ijse.tailorshop.dao.CrudDAO;
import lk.ijse.tailorshop.entity.Orders;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO extends CrudDAO<Orders,String> {
    List<Orders> findAllByCustomerId(String customerId) throws SQLException;

    public boolean orderReturn(String orderId, String returned) throws SQLException;
}
