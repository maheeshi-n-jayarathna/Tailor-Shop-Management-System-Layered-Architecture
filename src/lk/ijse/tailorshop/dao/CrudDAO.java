package lk.ijse.tailorshop.dao;

import lk.ijse.tailorshop.entity.SuperEntity;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDAO <T extends SuperEntity,ID extends Serializable> extends SuperDAO{
    public boolean save(T entity) throws SQLException;
    public boolean update(T entity) throws SQLException;
    public boolean deleteByPk(ID pk) throws SQLException;
    public List<T> findAll() throws SQLException;
    public Optional<T> findByPk(ID pk) throws SQLException;
    public Optional<String> findLastPk() throws SQLException;
    public long count() throws SQLException ;
}
