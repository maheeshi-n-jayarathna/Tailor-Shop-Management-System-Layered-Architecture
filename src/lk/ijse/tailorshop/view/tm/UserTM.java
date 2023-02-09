package lk.ijse.tailorshop.view.tm;

import lk.ijse.tailorshop.entity.SuperEntity;

public class UserTM implements SuperEntity {
    private String userId;
    private String userRank;
    private String userName;
    private String password;
    private String nic;
    private String telNumber;
    private String email;
    private String address;

    public UserTM() {
    }

    public UserTM(String userId, String userRank, String userName, String password, String nic, String telNumber, String email, String address) {
        this.setUserId(userId);
        this.setUserRank(userRank);
        this.setUserName(userName);
        this.setPassword(password);
        this.setNic(nic);
        this.setTelNumber(telNumber);
        this.setEmail(email);
        this.setAddress(address);
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
        return "User{" +
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
