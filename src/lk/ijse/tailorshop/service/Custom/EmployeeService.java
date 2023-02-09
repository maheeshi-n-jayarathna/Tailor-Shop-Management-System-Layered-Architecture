package lk.ijse.tailorshop.service.Custom;

import lk.ijse.tailorshop.dto.EmployeeDTO;
import lk.ijse.tailorshop.service.SuperService;
import lk.ijse.tailorshop.service.exception.DuplicateException;
import lk.ijse.tailorshop.service.exception.InUseException;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService extends SuperService {
    public List<EmployeeDTO> getAllEmployee();

    public boolean saveEmployee(EmployeeDTO employee) throws DuplicateException;

    public boolean updateEmployee(EmployeeDTO employee) throws DuplicateException;

    public boolean deleteEmployee(String text);

    public String getNextEmployeeId();
}
