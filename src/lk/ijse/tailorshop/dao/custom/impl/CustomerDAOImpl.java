package lk.ijse.tailorshop.dao.custom.impl;


import lk.ijse.tailorshop.dao.custom.CustomerDAO;
import lk.ijse.tailorshop.dao.util.DBUtil;
import lk.ijse.tailorshop.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(Customer entity) throws SQLException {
        return DBUtil.executeUpdate("INSERT INTO Customer VALUES (?,?,?,?,?,?)",
                entity.getCustomerId(),
                entity.getName(),
                entity.getNic(),
                entity.getTelNumber(),
                entity.getEmail(),
                entity.getAddress());
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        return DBUtil.executeUpdate("UPDATE Customer SET name=? ,nic=? ,telNumber=? ,email=? ,address=? WHERE customerId=?",
                customer.getName(),
                customer.getNic(),
                customer.getTelNumber(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getCustomerId());
    }

    @Override
    public boolean deleteByPk(String customerId) throws SQLException {
        return DBUtil.executeUpdate("DELETE FROM Customer WHERE customerId=?",customerId);
    }

    @Override
    public List<Customer> findAll() throws SQLException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM Customer");
        List<Customer> customerList= new ArrayList<>();
        while (rst.next()){
            customerList.add(getCustomer(rst));
        }
        return customerList;
    }

    @Override
    public Optional<Customer> findByPk(String customerId) throws SQLException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM Customer WHERE customerId=?", customerId);
        if(rst.next()){
            return Optional.of(getCustomer(rst));
        }
        return Optional.empty();

    }

    @Override
    public Optional<String> findLastPk() throws SQLException {
        ResultSet rst= DBUtil.executeQuery("SELECT customerId FROM Customer ORDER BY customerId DESC LIMIT 1");
        if(rst.next()){
            return Optional.of(rst.getString(1));
        }
        return Optional.empty();
    }

    @Override
    public long count() throws SQLException {
        ResultSet rst  = DBUtil.executeQuery("SELECT COUNT(customerId) AS count FROM Customer");
        rst.next();
        return rst.getInt(1);
    }

    @Override
    public Optional<Customer> findByNic(String nic) throws SQLException{
        ResultSet rst= DBUtil.executeQuery("SELECT * FROM customer WHERE nic =?",nic);
        if(rst.next()){
            return Optional.of(getCustomer(rst));
        }
        return Optional.empty();
    }

    private Customer getCustomer(ResultSet rst) throws SQLException {
        return new Customer(
                rst.getString(1),
                rst.getString(2),
                rst.getString(3),
                rst.getString(4),
                rst.getString(5),
                rst.getString(6)
        );
    }
}
