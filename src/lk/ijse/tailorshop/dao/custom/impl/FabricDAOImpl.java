package lk.ijse.tailorshop.dao.custom.impl;


import lk.ijse.tailorshop.dao.custom.FabricDAO;
import lk.ijse.tailorshop.dao.util.DBUtil;
import lk.ijse.tailorshop.entity.Fabric;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FabricDAOImpl implements FabricDAO {

    @Override
    public boolean save(Fabric entity) throws SQLException {
        return DBUtil.executeUpdate("INSERT INTO Fabric (fabricId, description, qtyOnStock, unitPrice) VALUES (?,?,?,?)",
                entity.getFabricId(),
                entity.getDescription(),
                entity.getQtyOnStock(),
                entity.getUnitPrice()
        );
    }

    @Override
    public boolean update(Fabric fabric) throws SQLException {
        return DBUtil.executeUpdate("UPDATE Fabric SET description=? ,qtyOnStock=? ,unitPrice=? WHERE fabricId=?",
                fabric.getDescription(),
                fabric.getQtyOnStock(),
                fabric.getUnitPrice(),
                fabric.getFabricId()
        );
    }

    @Override
    public boolean deleteByPk(String fabricId) throws SQLException {
        return DBUtil.executeUpdate("DELETE FROM Fabric WHERE fabricId=?",fabricId);
    }

    @Override
    public List<Fabric> findAll() throws SQLException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM Fabric");
        List<Fabric> fabricList= new ArrayList<>();
        while (rst.next()){
            Fabric fabric = new Fabric(
                    rst.getString("fabricId"),
                    rst.getString("description"),
                    rst.getInt("qtyOnStock"),
                    rst.getDouble("unitPrice")
            );
            fabricList.add(fabric);
        }
        return fabricList;
    }

    @Override
    public Optional<Fabric> findByPk(String fabricId) throws SQLException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM Fabric WHERE fabricId=?", fabricId);
        if(rst.next()){
            return Optional.of(new Fabric(
                    rst.getString("fabricId"),
                    rst.getString("description"),
                    rst.getInt("qtyOnStock"),
                    rst.getDouble("unitPrice")
            ));

        }
        return Optional.empty();
    }

    @Override
    public Optional<String> findLastPk() throws SQLException {
        ResultSet rst= DBUtil.executeQuery("SELECT fabricId FROM Fabric ORDER BY fabricId DESC LIMIT 1");
        if(rst.next()){
            return Optional.of(rst.getString(1));
        }
        return Optional.empty();
    }

    @Override
    public long count() throws SQLException {
        ResultSet rst  = DBUtil.executeQuery("SELECT COUNT(fabricId) AS count FROM Fabric");
        rst.next();
        return rst.getInt(1);
    }

    @Override
    public boolean sellFabricQtyById(String fabricId, int sellingQty) throws SQLException {
        return updateQty(fabricId, sellingQty);
    }

    @Override
    public boolean suppliesFabricQtyById(String fabricId, int addingQty) throws SQLException {
        return updateQty(fabricId,-(addingQty));
    }

    @Override
    public Optional<Fabric> findByDescription(String description) throws SQLException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM Fabric WHERE description=?", description);
        if(rst.next()){
            return Optional.of(new Fabric(
                    rst.getString("fabricId"),
                    rst.getString("description"),
                    rst.getInt("qtyOnStock"),
                    rst.getDouble("unitPrice")
            ));

        }
        return Optional.empty();
    }

    private boolean updateQty(String fabricId, int qty) throws SQLException {
        return DBUtil.executeUpdate("UPDATE fabric SET qtyOnStock = qtyOnStock - ? WHERE fabricId = ?",qty,fabricId);
    }
}
