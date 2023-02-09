package lk.ijse.tailorshop.service.Custom.Impl;

import lk.ijse.tailorshop.dao.DaoFactory;
import lk.ijse.tailorshop.dao.DaoTypes;
import lk.ijse.tailorshop.dao.custom.CustomerDAO;
import lk.ijse.tailorshop.dao.custom.OrderDAO;
import lk.ijse.tailorshop.dto.CustomerDTO;
import lk.ijse.tailorshop.entity.Customer;
import lk.ijse.tailorshop.entity.Orders;
import lk.ijse.tailorshop.service.Custom.CustomerService;
import lk.ijse.tailorshop.service.exception.DuplicateException;
import lk.ijse.tailorshop.service.exception.InUseException;
import lk.ijse.tailorshop.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerDAO customerDAO;
    private final OrderDAO orderDAO;
    private final Convertor convertor;

    public CustomerServiceImpl() {
        customerDAO = DaoFactory.getInstance().getDAO(DaoTypes.CUSTOMER);
        orderDAO = DaoFactory.getInstance().getDAO(DaoTypes.ORDER);
        convertor = new Convertor();
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        try {
            List<Customer> customers = customerDAO.findAll();
            List<CustomerDTO> customerDTOS = new ArrayList<>();
            for (Customer customer : customers){
                customerDTOS.add(convertor.fromCustomer(customer));
            }
            return customerDTOS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws DuplicateException {
        try {
            if (customerDAO.findByNic(customerDTO.getNic()).isPresent())
                throw new DuplicateException("Customer nic is exists !");
            return customerDAO.save(convertor.toCustomer(customerDTO));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getNextCustomerId() {
        try {
            Optional<String> lastPk = customerDAO.findLastPk();
            if(lastPk.isPresent()){
                String pk=lastPk.get().substring(1);
                pk=String.format("C%03d", Integer.parseInt(pk)+1);
                return pk;
            }
            return "C001";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CustomerDTO getCustomerByNic(String nic) {
        try {
            return convertor.fromCustomer(customerDAO.findByNic(nic).get());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteCustomer(String customerId) throws InUseException {
        try {
            for (Orders orders : orderDAO.findAllByCustomerId(customerId)) {
                if (!orders.getStatus().equals("returned"))
                    throw new InUseException("There are undelivered orders for this customer !");
            }
            return customerDAO.deleteByPk(customerId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws DuplicateException {
        try {
            Optional<Customer> optional = customerDAO.findByNic(customerDTO.getNic());
            if (optional.isPresent() && (!optional.get().getCustomerId().equals(customerDTO.getCustomerId())))
                throw new DuplicateException("Customer nic is exists !");
            return customerDAO.update(convertor.toCustomer(customerDTO));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
