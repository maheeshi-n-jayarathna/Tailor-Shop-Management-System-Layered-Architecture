package lk.ijse.tailorshop.dto;

public class EmployeeDTO {
    private String employeeId;
    private String name;
    private String rank;
    private String nic;
    private String telNumber;
    private String email;
    private String address;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String employeeId, String name, String rank, String nic, String telNumber, String email, String address) {
        this.employeeId = employeeId;
        this.name = name;
        this.rank = rank;
        this.nic = nic;
        this.telNumber = telNumber;
        this.email = email;
        this.address = address;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employeeId='" + employeeId + '\'' +
                ", name='" + name + '\'' +
                ", rank='" + rank + '\'' +
                ", nic='" + nic + '\'' +
                ", telNumber='" + telNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
