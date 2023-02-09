package lk.ijse.tailorshop.dao.custom;

import lk.ijse.tailorshop.dao.CrudDAO;
import lk.ijse.tailorshop.entity.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDAO extends CrudDAO<User,String> {
    public Optional<User> findByUserName(String userName) throws SQLException;
}
