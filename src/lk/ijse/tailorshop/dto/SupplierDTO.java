package lk.ijse.tailorshop.dto;

import java.sql.Date;

public class SupplierDTO {
    private String supplierId;
    private String name;
    private String description;
    private String telNumber;
    private String address;


    public SupplierDTO(String supplierId, String name, String description, String telNumber, String address) {
        this.supplierId = supplierId;
        this.name = name;
        this.description = description;
        this.telNumber = telNumber;
        this.address = address;
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
        return "SupplierDTO{" +
                "supplierId='" + supplierId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", telNumber='" + telNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
