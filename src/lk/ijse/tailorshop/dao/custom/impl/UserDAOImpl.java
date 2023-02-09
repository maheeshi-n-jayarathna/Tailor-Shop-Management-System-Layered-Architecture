package lk.ijse.tailorshop.dao.custom.impl;



import lk.ijse.tailorshop.dao.custom.UserDAO;
import lk.ijse.tailorshop.dao.util.DBUtil;
import lk.ijse.tailorshop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean save(User entity) throws SQLException {
        return DBUtil.executeUpdate("INSERT INTO User VALUES (?,?,?,?,?,?,?,?)",
                entity.getUserId(),
                entity.getUserRank(),
                entity.getUserName(),
                entity.getPassword(),
                entity.getNic(),
                entity.getTelNumber(),
                entity.getEmail(),
                entity.getAddress());
    }

    @Override
    public boolean update(User user) throws SQLException {
        return DBUtil.executeUpdate("UPDATE User SET userRank=? ,userName=? ,password=? ,nic=? ,telNumber=? ,email=? ,address=? WHERE userIs=?",
                user.getUserRank(),
                user.getUserName(),
                user.getPassword(),
                user.getTelNumber(),
                user.getNic(),
                user.getEmail(),
                user.getAddress(),
                user.getUserId());
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException {
        return DBUtil.executeUpdate("DELETE FROM User WHERE userId=?",pk);
    }

    @Override
    public List<User> findAll() throws SQLException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM User");
        List<User> userList = new ArrayList<>();
        while (rst.next()){
            userList.add(new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8)
            ));
        }
        return userList;
    }

    @Override
    public Optional<User> findByPk(String pk) throws SQLException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM User WHERE userId=?", pk);
        if(rst.next()){
            return Optional.of(new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8)
            ));
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> findLastPk() throws SQLException {
        ResultSet rst= DBUtil.executeQuery("SELECT userId FROM User ORDER BY userId DESC LIMIT 1");
        if(rst.next()){
            return Optional.of(rst.getString(1));
        }
        return Optional.empty();
    }

    @Override
    public long count() throws SQLException {
        ResultSet rst  = DBUtil.executeQuery("SELECT COUNT(userId) AS count FROM User");
        rst.next();
        return rst.getInt(1);
    }

    @Override
    public Optional<User> findByUserName(String userName) throws SQLException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM User WHERE userName=?", userName);
        if(rst.next()){
            return Optional.of(new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8)
            ));
        }
        return Optional.empty();
    }
}
