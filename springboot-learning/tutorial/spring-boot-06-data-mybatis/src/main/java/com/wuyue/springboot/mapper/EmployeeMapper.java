package com.wuyue.springboot.mapper;

import com.wuyue.springboot.entities.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author DeltaV235
 * @version 1.0
 * @className EmployeeMapper
 * @description
 * @date 2020/4/11 15:37
 */
@Mapper
public interface EmployeeMapper {
    Employee getEmpById(Integer id);

    void insertEmp(Employee employee);
}
