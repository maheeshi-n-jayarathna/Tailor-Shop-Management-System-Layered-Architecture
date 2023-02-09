package lk.ijse.tailorshop.dao.custom;



import lk.ijse.tailorshop.dao.CrudDAO;
import lk.ijse.tailorshop.entity.SuppliesDetails;

import java.sql.SQLException;
import java.util.List;

public interface SuppliesDetailsDAO extends CrudDAO<SuppliesDetails,String> {
    public List<SuppliesDetails> findAllByPk(String suppliesId) throws SQLException;
}
