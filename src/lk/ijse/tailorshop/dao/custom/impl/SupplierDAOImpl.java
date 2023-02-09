package lk.ijse.tailorshop.dao.custom.impl;



import lk.ijse.tailorshop.dao.custom.SupplierDAO;
import lk.ijse.tailorshop.dao.util.DBUtil;
import lk.ijse.tailorshop.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public boolean save(Supplier entity) throws SQLException {
        return DBUtil.executeUpdate("INSERT INTO supplier (supplierId, name, description, telNumber, address) VALUES (?,?,?,?,?)",
                entity.getSupplierId(),
                entity.getName(),
                entity.getDescription(),
                entity.getTelNumber(),
                entity.getAddress()
        );
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException {
        return DBUtil.executeUpdate("UPDATE supplier SET name=? ,description=? ,telNumber=? ,address=? WHERE supplierId=?",
                supplier.getName(),
                supplier.getDescription(),
                supplier.getTelNumber(),
                supplier.getAddress(),
                supplier.getSupplierId()
        );
    }

    @Override
    public boolean deleteByPk(String supplierId) throws SQLException {
        return DBUtil.executeUpdate("DELETE FROM supplier WHERE supplierId=?",supplierId);
    }

    @Override
    public List<Supplier> findAll() throws SQLException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM supplier");
        List<Supplier> supplierList= new ArrayList<>();
        while (rst.next()){
            supplierList.add(new Supplier(
                    rst.getString("supplierId"),
                    rst.getString("name"),
                    rst.getString("description"),
                    rst.getString("telNumber"),
                    rst.getString("address")
            ));
        }
        return supplierList;
    }

    @Override
    public Optional<Supplier> findByPk(String supplierId) throws SQLException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM supplier WHERE supplierId=?", supplierId);
        if(rst.next()){
            return Optional.of(new Supplier(
                    rst.getString("supplierId"),
                    rst.getString("name"),
                    rst.getString("description"),
                    rst.getString("telNumber"),
                    rst.getString("address")
            ));
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> findLastPk() throws SQLException {
        ResultSet rst= DBUtil.executeQuery("SELECT supplierId FROM supplier ORDER BY supplierId DESC LIMIT 1");
        if(rst.next()){
            return Optional.of(rst.getString(1));
        }
        return Optional.empty();
    }

    @Override
    public long count() throws SQLException {
        ResultSet rst  = DBUtil.executeQuery("SELECT COUNT(supplierId) AS count FROM supplier");
        rst.next();
        return rst.getInt(1);
    }
}
