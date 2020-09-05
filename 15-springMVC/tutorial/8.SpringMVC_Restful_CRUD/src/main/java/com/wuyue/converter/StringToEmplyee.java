package com.wuyue.converter;

import com.wuyue.mapper.intf.DepartmentMapper;
import com.wuyue.model.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * @author DeltaV235
 * @version 1.0
 * @className StringToEmplyee
 * @description
 * @date 2020/3/27 23:28
 */
public class StringToEmplyee implements Converter<String, Employee> {
    // Converter<S, T> : <S>: Source , <T>: Target

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Employee convert(String source) {
        Employee employee = new Employee();
        if (source.contains("-")) {
            String[] properties = source.split("-");
            employee.setLastName(properties[0]);
            employee.setEmail(properties[1]);
            employee.setGender(Integer.parseInt(properties[2]));
            employee.setDepartment(departmentMapper.getDepartment(Integer.parseInt(properties[3])));
        }
        return employee;
    }
}














