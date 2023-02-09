package lk.ijse.tailorshop.entity;

import java.time.LocalDate;

public class OrderDetails implements SuperEntity{
    private String orderId;
    private String fabricId;
    private String measurementId;
    private LocalDate receiveDate;
    private LocalDate returnDate;
    private double cost;
    private LocalDate trueReturnDate;
    private int qty;
    private double unitPrice;

    public OrderDetails() {
    }

    public OrderDetails(String orderId, String fabricId, String measurementId, LocalDate receiveDate, LocalDate returnDate, double cost, LocalDate trueReturnDate, int qty, double unitPrice) {
        this.orderId = orderId;
        this.fabricId = fabricId;
        this.measurementId = measurementId;
        this.receiveDate = receiveDate;
        this.returnDate = returnDate;
        this.cost = cost;
        this.trueReturnDate = trueReturnDate;
        this.qty = qty;
        this.unitPrice = unitPrice;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderId='" + orderId + '\'' +
                ", fabricId='" + fabricId + '\'' +
                ", measurementId='" + measurementId + '\'' +
                ", receiveDate=" + receiveDate +
                ", returnDate=" + returnDate +
                ", cost=" + cost +
                ", trueReturnDate=" + trueReturnDate +
                ", qty=" + qty +
                '}';
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}

