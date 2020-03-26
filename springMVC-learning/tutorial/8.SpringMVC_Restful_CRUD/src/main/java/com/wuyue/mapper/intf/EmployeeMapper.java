package com.wuyue.mapper.intf;

import com.wuyue.model.entities.Employee;

import java.util.Collection;

/**
 * @author DeltaV235
 * @version 1.0
 * @className EmployeeMapper
 * @description
 * @date 2020/3/26 1:34
 */
public interface EmployeeMapper {
    void save(Employee employee);

    void update(Employee employee);

    Collection<Employee> getAll();

    Employee get(Integer id);

    void delete(Integer id);
}














