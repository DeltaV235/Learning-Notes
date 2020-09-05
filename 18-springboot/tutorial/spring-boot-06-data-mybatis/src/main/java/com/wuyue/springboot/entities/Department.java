package com.wuyue.springboot.entities;

/**
 * @author DeltaV235
 * @version 1.0
 * @className Department
 * @description
 * @date 2020/4/11 14:44
 */
public class Department {
    private Integer id;
    private String departmentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
