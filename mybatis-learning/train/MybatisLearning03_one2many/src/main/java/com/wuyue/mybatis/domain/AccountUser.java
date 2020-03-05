package com.wuyue.mybatis.domain;

/**
 * @author DeltaV235
 * @version 1.0
 * @className AccountUser
 * @description
 * @date 2020/3/1 20:55
 */
public class AccountUser extends Account {
    private String username;
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return super.toString() + "  AccountUser{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
