package lk.ijse.tailorshop.dao.custom.impl;

import lk.ijse.tailorshop.dao.custom.EmployeeDAO;
import lk.ijse.tailorshop.dao.util.DBUtil;
import lk.ijse.tailorshop.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAOImpl implements EmployeeDAO {


    @Override
    public boolean save(Employee entity) throws SQLException {
        return DBUtil.executeUpdate("INSERT INTO Employee VALUES (?,?,?,?,?,?,?)",
                entity.getEmployeeId(),
                entity.getName(),
                entity.getRank(),
                entity.getNic(),
                entity.getTelNumber(),
                entity.getEmail(),
                entity.getAddress()
        );
    }

    @Override
    public boolean update(Employee employee) throws SQLException {
        return DBUtil.executeUpdate("UPDATE Employee SET name=? ,`rank`=? ,nic=? ,telNumber=? ,email=? ,address=? WHERE employeeId=?",
                employee.getName(),
                employee.getRank(),
                employee.getNic(),
                employee.getTelNumber(),
                employee.getEmail(),
                employee.getAddress(),
                employee.getEmployeeId()
        );
    }

    @Override
    public boolean deleteByPk(String employeeId) throws SQLException {
        return DBUtil.executeUpdate("DELETE FROM Employee WHERE employeeId=?",employeeId);
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM Employee");
        List<Employee> employeeList= new ArrayList<>();
        while (rst.next()){
            Employee employee = new Employee(
                    rst.getString("employeeId"),
                    rst.getString("name"),
                    rst.getString("`rank`"),
                    rst.getString("nic"),
                    rst.getString("telNumber"),
                    rst.getString("email"),
                    rst.getString("address"));
            employeeList.add(employee);
        }
        return employeeList;
    }

    @Override
    public Optional<Employee> findByPk(String employeeId) throws SQLException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM Employee WHERE employeeId=?", employeeId);
        if(rst.next()){
            return Optional.of(new Employee(
                    rst.getString("employeeId"),
                    rst.getString("name"),
                    rst.getString("rank"),
                    rst.getString("nic"),
                    rst.getString("telNumber"),
                    rst.getString("email"),
                    rst.getString("address")
            ));
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> findLastPk() throws SQLException {
        ResultSet rst= DBUtil.executeQuery("SELECT employeeId FROM Employee ORDER BY employeeId DESC LIMIT 1");
        if(rst.next()){
            return Optional.of(rst.getString(1));
        }
        return Optional.empty();
    }

    @Override
    public long count() throws SQLException {
        ResultSet rst  = DBUtil.executeQuery("SELECT COUNT(employeeId) AS count FROM Employee");
        rst.next();
        return rst.getInt(1);
    }

    @Override
    public Optional<Employee> findByNic(String nic) throws SQLException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM Employee WHERE nic=?", nic);
        if(rst.next()){
            return Optional.of(new Employee(
                    rst.getString("employeeId"),
                    rst.getString("name"),
                    rst.getString("rank"),
                    rst.getString("nic"),
                    rst.getString("telNumber"),
                    rst.getString("email"),
                    rst.getString("address")
            ));
        }
        return Optional.empty();
    }
}
