package com.wuyue.mapper.intf;

import com.wuyue.model.entities.Department;

import java.util.Collection;

/**
 * @author DeltaV235
 * @version 1.0
 * @className DepartmentMapper
 * @description
 * @date 2020/3/26 1:12
 */
public interface DepartmentMapper {
    /**
     * 获取所有部门信息并封装为一个集合
     *
     * @return
     */
    Collection<Department> getDepartments();

    /**
     * 根据部门id获取指定部门的所有字段并封装到Department对象中
     *
     * @param id
     * @return
     */
    Department getDepartment(Integer id);
}














