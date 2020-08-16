package com.wuyue.service;

import com.wuyue.entities.Employee;
import com.wuyue.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DeltaV235
 * @version 1.0
 * @className EmployeeService
 * @description
 * @date 2020/4/1 3:11
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper mapper;

    public Employee getEmpById(Integer id) {
        return mapper.getEmpById(id);
    }
}














