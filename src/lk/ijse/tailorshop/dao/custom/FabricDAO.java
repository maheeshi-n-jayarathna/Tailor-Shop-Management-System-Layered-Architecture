package lk.ijse.tailorshop.dao.custom;

import lk.ijse.tailorshop.dao.CrudDAO;
import lk.ijse.tailorshop.entity.Fabric;

import java.sql.SQLException;
import java.util.Optional;

public interface FabricDAO extends CrudDAO<Fabric,String> {
    public boolean sellFabricQtyById(String fabricId, int sellingQty) throws SQLException;

    public boolean suppliesFabricQtyById(String fabricId, int addingQty) throws SQLException;

    public Optional<Fabric> findByDescription(String description) throws SQLException;
}
