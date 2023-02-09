package lk.ijse.tailorshop.dao.custom.impl;



import lk.ijse.tailorshop.dao.custom.SuppliesDetailsDAO;
import lk.ijse.tailorshop.dao.util.DBUtil;
import lk.ijse.tailorshop.entity.SuppliesDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SuppliesDetailsDAOImpl implements SuppliesDetailsDAO {

    @Override
    public boolean save(SuppliesDetails entity) throws SQLException {
        return DBUtil.executeUpdate("INSERT INTO SuppliesDetails VALUES(?,?,?,?)",
                entity.getSuppliesId(),
                entity.getFabricId(),
                entity.getQty(),
                entity.getUnitPrice()
        );
    }

    @Override
    public boolean update(SuppliesDetails entity) throws SQLException {
        return DBUtil.executeUpdate("UPDATE SuppliesDetails SET fabricId=?,qty=?,unitPrice=? WHERE suppliesId=?",
                entity.getFabricId(),
                entity.getQty(),
                entity.getUnitPrice(),
                entity.getSuppliesId()
        );
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException {
        return DBUtil.executeUpdate("Delete From SuppliesDetails where suppliesId=?",pk);
    }

    @Override
    public List<SuppliesDetails> findAll() throws SQLException {
        List<SuppliesDetails> suppliesDetails = new ArrayList<>();
        ResultSet rst= DBUtil.executeQuery("SELECT * FROM SuppliesDetails");
        while (rst.next()){
            suppliesDetails.add(getSuppliesDetails(rst));
        }
        return suppliesDetails;
    }

    @Override
    public Optional<SuppliesDetails> findByPk(String pk) throws SQLException {
        ResultSet result = DBUtil.executeQuery("SELECT * FROM SuppliesDetails WHERE suppliesId = ?", pk);
        if(result.next()) {
            return Optional.of(getSuppliesDetails(result));
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> findLastPk() throws SQLException {
        ResultSet rst= DBUtil.executeQuery("SELECT suppliesId FROM SuppliesDetails ORDER BY suppliesId DESC LIMIT 1");
        if(rst.next()){
            return Optional.of(rst.getString(1));
        }
        return Optional.empty();
    }

    @Override
    public long count() throws SQLException {
        ResultSet rst  = DBUtil.executeQuery("SELECT COUNT(suppliesId) AS count FROM SuppliesDetails");
        rst.next();
        return rst.getInt(1);
    }

    @Override
    public List<SuppliesDetails> findAllByPk(String suppliesId) throws SQLException {
        List<SuppliesDetails> suppliesDetails = new ArrayList<>();
        ResultSet rst= DBUtil.executeQuery("SELECT * FROM SuppliesDetails WHERE suppliesId = ?",suppliesId);
        while (rst.next()){
            suppliesDetails.add(getSuppliesDetails(rst));
        }
        return suppliesDetails;
    }

    private SuppliesDetails getSuppliesDetails(ResultSet rst) throws SQLException {
        return new SuppliesDetails(
                rst.getString(1),
                rst.getString(2),
                rst.getInt(3),
                rst.getDouble(4)
        );
    }
}