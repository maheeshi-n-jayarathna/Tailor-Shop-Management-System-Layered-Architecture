package lk.ijse.tailorshop.service.util;

import lk.ijse.tailorshop.dto.*;
import lk.ijse.tailorshop.entity.*;

import java.util.List;
import java.util.Optional;

public class Convertor {
    public CustomerDTO fromCustomer(Customer customer) {
        return new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getNic(), customer.getTelNumber(), customer.getEmail(), customer.getAddress());
    }

    public Customer toCustomer(CustomerDTO customerDTO) {
        return new Customer(customerDTO.getCustomerId(), customerDTO.getName(), customerDTO.getNic(), customerDTO.getTelNumber(), customerDTO.getEmail(), customerDTO.getAddress());
    }

    public EmployeeDTO fromEmployee(Employee employee) {
        return new EmployeeDTO(employee.getEmployeeId(), employee.getName(), employee.getRank(), employee.getNic(), employee.getTelNumber(), employee.getEmail(), employee.getAddress());
    }

    public Employee toEmployee(EmployeeDTO employee) {
        return new Employee(employee.getEmployeeId(), employee.getName(), employee.getRank(), employee.getNic(), employee.getTelNumber(), employee.getEmail(), employee.getAddress());
    }

    public FabricDTO fromFabric(Fabric fabric) {
        return new FabricDTO(fabric.getFabricId(), fabric.getDescription(), fabric.getQtyOnStock(), fabric.getUnitPrice());
    }

    public Fabric toFabric(FabricDTO fabricDTO) {
        return new Fabric(fabricDTO.getFabricId(), fabricDTO.getDescription(), fabricDTO.getQtyOnStock(), fabricDTO.getUnitPrice());
    }

    public SupplierDTO fromSupplier(Supplier supplier) {
        return new SupplierDTO(supplier.getSupplierId(), supplier.getName(), supplier.getDescription(), supplier.getTelNumber(), supplier.getAddress());
    }

    public Supplier toSupplier(SupplierDTO supplierDTO) {
        return new Supplier(supplierDTO.getSupplierId(), supplierDTO.getName(), supplierDTO.getDescription(), supplierDTO.getTelNumber(), supplierDTO.getAddress());
    }

    public UserDTO fromUser(User user) {
        return new UserDTO(user.getUserId(), user.getUserRank(), user.getUserName(), user.getPassword(), user.getNic(), user.getTelNumber(), user.getEmail(), user.getAddress());
    }

    public User toUser(UserDTO userDTO) {
        return new User(userDTO.getUserId(), userDTO.getUserRank(), userDTO.getUserName(), userDTO.getPassword(), userDTO.getNic(), userDTO.getTelNumber(), userDTO.getEmail(), userDTO.getAddress());
    }

    public Orders toOrders(OrderDTO orderDTO) {
        return new Orders(orderDTO.getOrderId(), orderDTO.getCustomerId(), orderDTO.getDescription(), orderDTO.getAdvance(), orderDTO.getTotalPrice(), orderDTO.getStatus());
    }

    public Measurement toMeasurement(MeasurementDTO measurementDTO) {
        return new Measurement(
                measurementDTO.getMeasurementId(),
                measurementDTO.getDetails(),
                measurementDTO.getLength(),
                measurementDTO.getNeck(),
                measurementDTO.getBust(),
                measurementDTO.getDownAShoulder(),
                measurementDTO.getUnderBust(),
                measurementDTO.getSleeve(),
                measurementDTO.getWest(),
                measurementDTO.getChest(),
                measurementDTO.getCrotch(),
                measurementDTO.getKnee(),
                measurementDTO.getCalf(),
                measurementDTO.getHipAndWestDistance(),
                measurementDTO.getInseam(),
                measurementDTO.getThigh(),
                measurementDTO.getHip(),
                measurementDTO.getSkirtLength(),
                measurementDTO.getFrockLength()
        );
    }

    public OrderDetails toOrderDetails(OrderDetailsDTO orderDetailsDTO) {
        return new OrderDetails(orderDetailsDTO.getOrderId(), orderDetailsDTO.getFabricId(), orderDetailsDTO.getMeasurementId(), orderDetailsDTO.getReceiveDate(), orderDetailsDTO.getReturnDate(), orderDetailsDTO.getCost(), orderDetailsDTO.getTrueReturnDate(), orderDetailsDTO.getQty(), orderDetailsDTO.getUnitPrice());
    }

    public OrderDTO fromOrders(Orders order) {
        return new OrderDTO(order.getOrderId(), order.getCustomerId(), order.getDescription(), order.getAdvance(), order.getTotalPrice(), order.getStatus());
    }

    public OrderDetailsDTO fromOrderDetails(OrderDetails detail) {
        return new OrderDetailsDTO(detail.getOrderId(), detail.getFabricId(), detail.getMeasurementId(), detail.getQty(),detail.getUnitPrice(), detail.getReceiveDate(), detail.getReturnDate(), detail.getCost(), detail.getTrueReturnDate());
    }

    public MeasurementDTO fromMeasurement(Measurement measurement) {
        return new MeasurementDTO(
                measurement.getMeasurementId(),
                measurement.getDetails(),
                measurement.getLength(),
                measurement.getNeck(),
                measurement.getBust(),
                measurement.getDownAShoulder(),
                measurement.getUnderBust(),
                measurement.getSleeve(),
                measurement.getWest(),
                measurement.getChest(),
                measurement.getCrotch(),
                measurement.getKnee(),
                measurement.getCalf(),
                measurement.getHipAndWestDistance(),
                measurement.getInseam(),
                measurement.getThigh(),
                measurement.getHip(),
                measurement.getSkirtLength(),
                measurement.getFrockLength()
        );
    }
}