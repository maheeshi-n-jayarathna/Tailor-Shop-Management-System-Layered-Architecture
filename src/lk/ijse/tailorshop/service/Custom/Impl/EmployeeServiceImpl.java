package lk.ijse.tailorshop.service.Custom.Impl;

import lk.ijse.tailorshop.dao.DaoFactory;
import lk.ijse.tailorshop.dao.DaoTypes;
import lk.ijse.tailorshop.dao.custom.EmployeeDAO;
import lk.ijse.tailorshop.dto.EmployeeDTO;
import lk.ijse.tailorshop.entity.Customer;
import lk.ijse.tailorshop.entity.Employee;
import lk.ijse.tailorshop.service.Custom.EmployeeService;
import lk.ijse.tailorshop.service.exception.DuplicateException;
import lk.ijse.tailorshop.service.exception.InUseException;
import lk.ijse.tailorshop.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDAO employeeDAO;
    private final Convertor convertor;

    public EmployeeServiceImpl() {
        employeeDAO = DaoFactory.getInstance().getDAO(DaoTypes.EMPLOYEE);
        convertor = new Convertor();
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        try {
            List<EmployeeDTO> employeeDTOS = new ArrayList<>();
            for (Employee employee : employeeDAO.findAll()){
                employeeDTOS.add(convertor.fromEmployee(employee));
            }
            return employeeDTOS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean saveEmployee(EmployeeDTO employeeDTO) throws DuplicateException {
        try {
            if (employeeDAO.findByNic(employeeDTO.getNic()).isPresent())
                throw new DuplicateException("Employee nic is exists !");
            return employeeDAO.save(convertor.toEmployee(employeeDTO));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws DuplicateException {
        try {
            Optional<Employee> optional = employeeDAO.findByNic(employeeDTO.getNic());
            if (optional.isPresent() && (!optional.get().getEmployeeId().equals(employeeDTO.getEmployeeId())))
                throw new DuplicateException("Employee nic is exists !");
            return employeeDAO.update(convertor.toEmployee(employeeDTO));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteEmployee(String employeeId) {
        try {
            return employeeDAO.deleteByPk(employeeId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getNextEmployeeId() {
        try {
            Optional<String> lastPk = employeeDAO.findLastPk();
            if(lastPk.isPresent()){
                String pk=lastPk.get().substring(1);
                pk=String.format("E%03d", Integer.parseInt(pk)+1);
                return pk;
            }
            return "E001";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
