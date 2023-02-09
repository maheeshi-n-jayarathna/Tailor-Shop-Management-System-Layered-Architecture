package lk.ijse.tailorshop.service.Custom;

import lk.ijse.tailorshop.dto.UserDTO;
import lk.ijse.tailorshop.service.SuperService;
import lk.ijse.tailorshop.service.exception.DuplicateException;
import lk.ijse.tailorshop.view.tm.UserTM;

import java.util.List;

public interface UserService extends SuperService {
    public boolean verifyUser(UserDTO userDTO);

    public List<UserDTO> getAllUser();

    public boolean saveUser(UserDTO userDTO) throws DuplicateException;

    public String getNextUserId();

    public boolean deleteUser(String userId);

    public boolean updateUser(UserDTO userDTO) throws DuplicateException;
}
