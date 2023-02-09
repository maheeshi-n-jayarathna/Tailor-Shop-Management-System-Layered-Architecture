package lk.ijse.tailorshop.entity;

import java.sql.Date;

public class Supplies implements SuperEntity {
    private String suppliesId;
    private Date date;
    private String supplierId;

    public Supplies() {
    }

    public Supplies(String suppliesId, Date date, String supplierId) {
        this.setSuppliesId(suppliesId);
        this.setDate(date);
        this.setSupplierId(supplierId);
    }

    public String getSuppliesId() {
        return suppliesId;
    }

    public void setSuppliesId(String suppliesId) {
        this.suppliesId = suppliesId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "Supplies{" +
                "suppliesId='" + suppliesId + '\'' +
                ", date=" + date +
                ", supplierId='" + supplierId + '\'' +
                '}';
    }
}