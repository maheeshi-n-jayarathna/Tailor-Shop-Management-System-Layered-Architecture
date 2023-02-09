package lk.ijse.tailorshop.view.tm;

public class OrderDetailsTM {
    private String fabricId;
    private String measurementId;
    private String dressType;
    private double cost;
    private int qty;
    private double unitPrice;

    public OrderDetailsTM() {
    }

    public OrderDetailsTM(String fabricId, String measurementId, String dressType, double cost, int qty, double unitPrice) {
        this.fabricId = fabricId;
        this.measurementId = measurementId;
        this.dressType = dressType;
        this.cost = cost;
        this.qty = qty;
        this.unitPrice = unitPrice;
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

    public String getDressType() {
        return dressType;
    }

    public void setDressType(String dressType) {
        this.dressType = dressType;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
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
}
