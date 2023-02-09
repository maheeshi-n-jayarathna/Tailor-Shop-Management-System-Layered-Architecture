package lk.ijse.tailorshop.dto;

import java.time.LocalDate;

public class OrderDetailsDTO {

    private String orderId;
    private String fabricId;
    private String measurementId;
    private int qty;
    private double unitPrice;
    private LocalDate receiveDate;
    private LocalDate returnDate;
    private double cost;
    private LocalDate trueReturnDate;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(String orderId, String fabricId, String measurementId, int qty, double unitPrice, LocalDate receiveDate, LocalDate returnDate, double cost, LocalDate trueReturnDate) {
        this.orderId = orderId;
        this.fabricId = fabricId;
        this.measurementId = measurementId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.receiveDate = receiveDate;
        this.returnDate = returnDate;
        this.cost = cost;
        this.trueReturnDate = trueReturnDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFabricId() {
        return fabricId;
    }

    public void setFabricId(String fabricId) {
        this.fabricId = fabricId;
    }

    public String getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(String measurementId) {
        this.measurementId = measurementId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LocalDate getTrueReturnDate() {
        return trueReturnDate;
    }

    public void setTrueReturnDate(LocalDate trueReturnDate) {
        this.trueReturnDate = trueReturnDate;
    }

    @Override
    public String toString() {
        return "OrderDetailsDTO{" +
                "orderId='" + orderId + '\'' +
                ", fabricId='" + fabricId + '\'' +
                ", measurementId='" + measurementId + '\'' +
                ", qty=" + qty +
                ", receiveDate=" + receiveDate +
                ", returnDate=" + returnDate +
                ", cost=" + cost +
                ", trueReturnDate=" + trueReturnDate +
                '}';
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
