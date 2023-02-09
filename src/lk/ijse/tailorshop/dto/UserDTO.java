package lk.ijse.tailorshop.dto;

public class UserDTO {
    private String userId;
    private String userRank;
    private String userName;
    private String password;
    private String nic;
    private String telNumber;
    private String email;
    private String address;

    public UserDTO() {
    }

    public UserDTO(String userRank, String userName, String password) {
        this.userRank = userRank;
        this.userName = userName;
        this.password = password;
    }

    public UserDTO(String userId, String userRank, String userName, String password, String nic, String telNumber, String email, String address) {
        this.userId = userId;
        this.userRank = userRank;
        this.userName = userName;
        this.password = password;
        this.nic = nic;
        this.telNumber = telNumber;
        this.email = email;
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserRank() {
        return userRank;
    }

    public void setUserRank(String userRank) {
        this.userRank = userRank;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "UserDTO{" +
                "userId='" + userId + '\'' +
                ", userRank='" + userRank + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nic='" + nic + '\'' +
                ", telNumber='" + telNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
