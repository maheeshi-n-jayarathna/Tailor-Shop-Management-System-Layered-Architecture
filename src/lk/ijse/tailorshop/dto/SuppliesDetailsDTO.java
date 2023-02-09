package lk.ijse.tailorshop.dto;

public class SuppliesDetailsDTO {

    private String suppliesId;
    private String fabricId;
    private int qty;
    private Double unitPrice;

    public SuppliesDetailsDTO() {
    }

    public SuppliesDetailsDTO(String suppliesId, String fabricId, int qty, Double unitPrice) {
        this.suppliesId = suppliesId;
        this.fabricId = fabricId;
        this.qty = qty;
        this.unitPrice = unitPrice;
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
        return "SuppliesDetailsDTO{" +
                "suppliesId='" + suppliesId + '\'' +
                ", fabricId='" + fabricId + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
