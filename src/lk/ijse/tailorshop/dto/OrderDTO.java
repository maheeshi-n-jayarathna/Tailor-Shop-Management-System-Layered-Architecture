package lk.ijse.tailorshop.dto;


import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    private String orderId;
    private String customerId;
    private String description;
    private Double advance;
    private double totalPrice;
    private String status;
    private List<OrderDetailsDTO> orderDetailsList = new ArrayList<>();
    private List<MeasurementDTO> measurementList = new ArrayList<>();

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String customerId, String description, Double advance, double totalPrice, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.description = description;
        this.advance = advance;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public OrderDTO(String orderId, String customerId, String description, Double advance, double totalPrice, String status, List<OrderDetailsDTO> orderDetailsList, List<MeasurementDTO> measurementList) {
        this.setOrderId(orderId);
        this.setCustomerId(customerId);
        this.setDescription(description);
        this.setAdvance(advance);
        this.setTotalPrice(totalPrice);
        this.setStatus(status);
        this.setOrderDetailsList(orderDetailsList);
        this.setMeasurementList(measurementList);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAdvance() {
        return advance;
    }

    public void setAdvance(Double advance) {
        this.advance = advance;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderDetailsDTO> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetailsDTO> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    public List<MeasurementDTO> getMeasurementList() {
        return measurementList;
    }

    public void setMeasurementList(List<MeasurementDTO> measurementList) {
        this.measurementList = measurementList;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", description='" + description + '\'' +
                ", advance=" + advance +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                ", orderDetailsList=" + orderDetailsList +
                ", measurementList=" + measurementList +
                '}';
    }
}
