package lk.ijse.tailorshop.dto;

import java.sql.Date;
import java.util.ArrayList;

public class SuppliesDTO {
    private String suppliesId;
    private Date date;
    private String supplierId;
    private ArrayList<SuppliesDetailsDTO> suppliesDetailsDTOArrayList;

    public SuppliesDTO() {
    }

    public SuppliesDTO(String suppliesId, Date date, String supplierId, ArrayList<SuppliesDetailsDTO> suppliesDetailsDTOArrayList) {
        this.suppliesId = suppliesId;
        this.date = date;
        this.supplierId = supplierId;
        this.suppliesDetailsDTOArrayList = suppliesDetailsDTOArrayList;
    }

    public SuppliesDTO(String suppliesId, Date date, String supplierId) {
        this.suppliesId = suppliesId;
        this.date = date;
        this.supplierId = supplierId;
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
        return "SuppliesDTO{" +
                "suppliesId='" + suppliesId + '\'' +
                ", date=" + date +
                ", supplierId='" + supplierId + '\'' +
                '}';
    }

    public ArrayList<SuppliesDetailsDTO> getSuppliesDetailsDTOArrayList() {
        return suppliesDetailsDTOArrayList;
    }

    public void setSuppliesDetailsDTOArrayList(ArrayList<SuppliesDetailsDTO> suppliesDetailsDTOArrayList) {
        this.suppliesDetailsDTOArrayList = suppliesDetailsDTOArrayList;
    }
}
