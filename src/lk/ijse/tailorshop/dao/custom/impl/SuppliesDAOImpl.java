package lk.ijse.tailorshop.dao.custom.impl;



import lk.ijse.tailorshop.dao.custom.SuppliesDAO;
import lk.ijse.tailorshop.dao.util.DBUtil;
import lk.ijse.tailorshop.entity.Supplies;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SuppliesDAOImpl implements SuppliesDAO {

    @Override
    public boolean save(Supplies entity) throws SQLException {
        return DBUtil.executeUpdate("INSERT INTO Supplies (suppliesId, date, supplierId) VALUES (?,?,?)",
                entity.getSuppliesId(),
                entity.getDate(),
                entity.getSupplierId()
        );
    }

    @Override
    public boolean update(Supplies supplies) throws SQLException {
        return DBUtil.executeUpdate("UPDATE Supplies SET date=? ,supplierId=? WHERE suppliesId=?",
                supplies.getDate(),
                supplies.getSupplierId(),
                supplies.getSuppliesId()
        );
    }

    @Override
    public boolean deleteByPk(String suppliesId) throws SQLException {
        return DBUtil.executeUpdate("DELETE FROM Supplies WHERE supplierId=?",suppliesId);
    }

    @Override
    public List<Supplies> findAll() throws SQLException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM Supplies");
        List<Supplies> suppliesList= new ArrayList<>();
        while (rst.next()){
            suppliesList.add(new Supplies(
                    rst.getString("suppliesId"),
                    rst.getDate("date"),
                    rst.getString("supplierId")
            ));
        }
        return suppliesList;
    }

    @Override
    public Optional<Supplies> findByPk(String suppliesId) throws SQLException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM Supplies WHERE suppliesId=?", suppliesId);
        if(rst.next()){
            return Optional.of(new Supplies(
                    rst.getString("suppliesId"),
                    rst.getDate("date"),
                    rst.getString("supplierId")
            ));
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> findLastPk() throws SQLException {
        ResultSet rst= DBUtil.executeQuery("SELECT suppliesId FROM Supplies ORDER BY suppliesId DESC LIMIT 1");
        if(rst.next()){
            return Optional.of(rst.getString(1));
        }
        return Optional.empty();
    }

    @Override
    public long count() throws SQLException {
        ResultSet rst  = DBUtil.executeQuery("SELECT COUNT(suppliesId) AS count FROM Supplies");
        rst.next();
        return rst.getInt(1);
    }
}
