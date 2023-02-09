package lk.ijse.tailorshop.view.tm;

public class CustomerTM {
    private String customerId;
    private String name;
    private String nic;
    private String phoneNumber;
    private String email;
    private String address;

    public CustomerTM() {
    }

    public CustomerTM(String customerId, String name, String nic, String phoneNumber, String email, String address) {
        this.customerId = customerId;
        this.name = name;
        this.nic = nic;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
        return "CustomerTM{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
