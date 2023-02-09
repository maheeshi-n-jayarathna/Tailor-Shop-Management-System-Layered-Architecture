package lk.ijse.tailorshop.entity;

public class SuppliesDetails implements SuperEntity{
    private String suppliesId;
    private String fabricId;
    private int qty;
    private Double unitPrice;

    public SuppliesDetails() {
    }

    public SuppliesDetails(String suppliesId, String fabricId, int qty, Double unitPrice) {
        this.setSuppliesId(suppliesId);
        this.setFabricId(fabricId);
        this.setQty(qty);
        this.setUnitPrice(unitPrice);
    }

    public String getSuppliesId() {
        return suppliesId;
    }

    public void setSuppliesId(String suppliesId) {
        this.suppliesId = suppliesId;
    }

    public String getFabricId() {
        return fabricId;
    }

    public void setFabricId(String fabricId) {
        this.fabricId = fabricId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "SuppliesDetails{" +
                "suppliesId='" + suppliesId + '\'' +
                ", fabricId='" + fabricId + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
