package lk.ijse.tailorshop.view.tm;

public class GetOrderTM {
    private String measurementId;
    private String receiveDate;
    private String returnDate;
    private double cost;
    private String fabricId;
    private String fabDescription;
    private int qty;
    private double unitPrice;
    private double fullTotal;

    public GetOrderTM() {
    }

    public GetOrderTM(String measurementId, String reciveDate, String returnDate, double cost, String fabricId, String fabDescription, int qty, double unitPrice, double fullTotal) {
        this.measurementId = measurementId;
        this.receiveDate = reciveDate;
        this.returnDate = returnDate;
        this.cost = cost;
        this.fabricId = fabricId;
        this.fabDescription = fabDescription;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.fullTotal = fullTotal;
    }

    public String getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(String measurementId) {
        this.measurementId = measurementId;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getFabricId() {
        return fabricId;
    }

    public void setFabricId(String fabricId) {
        this.fabricId = fabricId;
    }

    public String getFabDescription() {
        return fabDescription;
    }

    public void setFabDescription(String fabDescription) {
        this.fabDescription = fabDescription;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getFullTotal() {
        return fullTotal;
    }

    public void setFullTotal(double fullTotal) {
        this.fullTotal = fullTotal;
    }
}
