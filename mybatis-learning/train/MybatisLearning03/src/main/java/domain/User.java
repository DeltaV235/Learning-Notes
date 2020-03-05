package domain;

import java.util.Date;

/**
 * @author DeltaV235
 * @version 1.0
 * @className User
 * @description
 * @date 2020/2/28 17:57
 */
public class User {
    private Integer userId;
    private String userName;
    private Date userBirthday;
    private String userSex;
    private String userAddress;

    public User(Integer userId, String userName, Date userBirthday, String userSex, String userAddress) {
        this.userId = userId;
        this.userName = userName;
        this.userBirthday = userBirthday;
        this.userSex = userSex;
        this.userAddress = userAddress;
    }

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userBirthday=" + userBirthday +
                ", userSex='" + userSex + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }
}
