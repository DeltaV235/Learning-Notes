package com.wuyue.dao;

import com.wuyue.entities.Employee;

/**
 * @author DeltaV235
 * @version 1.0
 * @className EmployeeCacheMapper
 * @description
 * @date 2020/3/8 23:39
 */
public interface EmployeeCacheMapper {
    Employee findById(Integer id);

    void addEmp(Employee employee);
}














