package lk.ijse.tailorshop.dao.custom;

import lk.ijse.tailorshop.dao.CrudDAO;
import lk.ijse.tailorshop.entity.Employee;

import java.sql.SQLException;
import java.util.Optional;

public interface EmployeeDAO extends CrudDAO<Employee,String> {
    public Optional<Employee> findByNic(String nic) throws SQLException;
}
