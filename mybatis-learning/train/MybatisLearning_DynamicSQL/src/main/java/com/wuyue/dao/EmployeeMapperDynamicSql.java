package com.wuyue.dao;

import com.wuyue.entities.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className EmployeeMapperDynamicSql
 * @description
 * @date 2020/3/8 1:46
 */
public interface EmployeeMapperDynamicSql {
    List<Employee> getEmpsByConditionIf(Employee employee);

    List<Employee> getEmpsByConditionChoose(Employee employee);

    void updateEmp(Employee employee);

    List<Employee> getEmpsByIdForeach(@Param("ids") List<Integer> ids);

    void addEmps(List<Employee> emps);
}
