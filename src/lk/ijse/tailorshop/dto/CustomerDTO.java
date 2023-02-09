package lk.ijse.tailorshop.dto;

public class CustomerDTO {
    private String customerId;
    private String name;
    private String nic;
    private String telNumber;
    private String email;
    private String address;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerId, String name, String nic, String telNumber, String email, String address) {
        this.customerId = customerId;
        this.name = name;
        this.nic = nic;
        this.telNumber = telNumber;
        this.email = email;
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "CustomerDTO{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", telNumber='" + telNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
