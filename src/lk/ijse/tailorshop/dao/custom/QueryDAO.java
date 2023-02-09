package lk.ijse.tailorshop.dao.custom;

import lk.ijse.tailorshop.dao.SuperDAO;
import lk.ijse.tailorshop.entity.Orders;

import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    public List<Orders> findAllNonReturnedOrdersByFabricId(String fabricId) throws SQLException;
    public List<Orders> findAllNonReturnedOrdersByCustomerId(String customerId) throws SQLException;
}
