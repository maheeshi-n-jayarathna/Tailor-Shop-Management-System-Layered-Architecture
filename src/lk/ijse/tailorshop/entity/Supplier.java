package lk.ijse.tailorshop.entity;

public class Supplier implements SuperEntity {
    private String supplierId;
    private String name;
    private String description;
    private String telNumber;
    private String address;

    public Supplier() {
    }

    public Supplier(String supplierId, String name, String description, String telNumber, String address) {
        this.setSupplierId(supplierId);
        this.setName(name);
        this.setDescription(description);
        this.setTelNumber(telNumber);
        this.setAddress(address);
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId='" + supplierId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", telNumber='" + telNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}