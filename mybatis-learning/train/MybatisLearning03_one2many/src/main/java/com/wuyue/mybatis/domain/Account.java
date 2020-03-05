package com.wuyue.mybatis.domain;

/**
 * @author DeltaV235
 * @version 1.0
 * @className Account
 * @description account表的实体类
 * @date 2020/3/1 18:44
 */
public class Account {
    private Integer id;
    private Integer uid;
    private double money;

    public Account() {
    }

    public Account(Integer id, Integer uid, double money) {
        this.id = id;
        this.uid = uid;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", uid=" + uid +
                ", money=" + money +
                '}';
    }
}
