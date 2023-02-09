package lk.ijse.tailorshop.dao.custom;

import lk.ijse.tailorshop.dao.CrudDAO;
import lk.ijse.tailorshop.entity.OrderDetails;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails,String> {
    public List<OrderDetails> findAllByPk(String orderId) throws SQLException;

    public boolean purchaseOrderDetailByOrderId(String orderId, LocalDate now) throws SQLException;
}
