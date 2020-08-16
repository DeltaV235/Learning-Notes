package com.wuyue.entities;

import java.io.Serializable;

/**
 * @author DeltaV235
 * @version 1.0
 * @className employee
 * @description 员工employee的实体类
 * @date 2020/3/8 1:43
 */
public class Employee implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private String gender;

    public Employee() {
    }

    public Employee(String name, String email, String gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public Employee(Integer id, String name, String email, String gender) {
        this(name, email, gender);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                '}';
    }
}
