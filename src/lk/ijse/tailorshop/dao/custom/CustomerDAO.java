package lk.ijse.tailorshop.dao.custom;

import lk.ijse.tailorshop.dao.CrudDAO;
import lk.ijse.tailorshop.entity.Customer;

import java.sql.SQLException;
import java.util.Optional;

public interface CustomerDAO extends CrudDAO<Customer,String> {

    public Optional<Customer> findByNic(String nic) throws SQLException;
}
