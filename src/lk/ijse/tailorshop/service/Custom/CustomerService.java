package lk.ijse.tailorshop.service.Custom;

import lk.ijse.tailorshop.dto.CustomerDTO;
import lk.ijse.tailorshop.service.SuperService;
import lk.ijse.tailorshop.service.exception.DuplicateException;
import lk.ijse.tailorshop.service.exception.InUseException;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService extends SuperService {
    public boolean saveCustomer(CustomerDTO customerDTO) throws DuplicateException;

    public boolean updateCustomer(CustomerDTO customer) throws DuplicateException ;

    public boolean deleteCustomer(String customerId) throws InUseException;

    public List<CustomerDTO> getAllCustomer();

    public String getNextCustomerId();

    public CustomerDTO getCustomerByNic(String value);
}
