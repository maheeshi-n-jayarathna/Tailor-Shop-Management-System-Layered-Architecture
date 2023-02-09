package lk.ijse.tailorshop.service.Custom.Impl;

import lk.ijse.tailorshop.dao.DaoFactory;
import lk.ijse.tailorshop.dao.DaoTypes;
import lk.ijse.tailorshop.dao.custom.UserDAO;
import lk.ijse.tailorshop.dto.SupplierDTO;
import lk.ijse.tailorshop.dto.UserDTO;
import lk.ijse.tailorshop.entity.Supplier;
import lk.ijse.tailorshop.entity.User;
import lk.ijse.tailorshop.service.Custom.UserService;
import lk.ijse.tailorshop.service.exception.DuplicateException;
import lk.ijse.tailorshop.service.exception.NotFoundException;
import lk.ijse.tailorshop.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    public final Convertor convertor;
    public UserServiceImpl() {
        userDAO = DaoFactory.getInstance().getDAO(DaoTypes.USER);
        convertor = new Convertor();
    }

    @Override
    public boolean verifyUser(UserDTO userDTO) {
        try {
            Optional<User> optional = userDAO.findByUserName(userDTO.getUserName());
            return optional.isPresent() && optional.get().getPassword().equals(userDTO.getPassword()) && optional.get().getUserRank().equals(userDTO.getUserRank());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserDTO> getAllUser() {
        try{
            List<UserDTO> userDTOS = new ArrayList<>();
            for (User user : userDAO.findAll()){
                userDTOS.add(convertor.fromUser(user));
            }
            return userDTOS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean saveUser(UserDTO userDTO) throws DuplicateException {
        try {
            if (userDAO.findByUserName(userDTO.getUserName()).isPresent())
                throw new DuplicateException("user name is exists !");
            return userDAO.save(convertor.toUser(userDTO));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getNextUserId() {
        try {
            Optional<String> lastPk = userDAO.findLastPk();
            if(lastPk.isPresent()){
                String pk=lastPk.get().substring(1);
                pk=String.format("U%03d", Integer.parseInt(pk)+1);
                return pk;
            }
            return "U001";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteUser(String userId) {
        try {
            return userDAO.deleteByPk(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws DuplicateException{
        try {
            Optional<User> optional = userDAO.findByUserName(userDTO.getUserName());
            if (optional.isPresent() && (!optional.get().getUserId().equals(userDTO.getUserId())))
                throw new DuplicateException("user name is exists !");
            return userDAO.update(convertor.toUser(userDTO));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
