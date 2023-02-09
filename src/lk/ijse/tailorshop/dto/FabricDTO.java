package lk.ijse.tailorshop.dto;

public class FabricDTO {
    private String fabricId;
    private String description;
    private int qtyOnStock;
    private double unitPrice;

    public FabricDTO() {
    }

    public FabricDTO(String fabricId, String description, int qtyOnStock, double unitPrice) {
        this.fabricId = fabricId;
        this.description = description;
        this.qtyOnStock = qtyOnStock;
        this.unitPrice = unitPrice;
    }

    public String getFabricId() {
        return fabricId;
    }

    public void setFabricId(String fabricId) {
        this.fabricId = fabricId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQtyOnStock() {
        return qtyOnStock;
    }

    public void setQtyOnStock(int qtyOnStock) {
        this.qtyOnStock = qtyOnStock;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "FabricDTO{" +
                "fabricId='" + fabricId + '\'' +
                ", description='" + description + '\'' +
                ", qtyOnStock=" + qtyOnStock +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
