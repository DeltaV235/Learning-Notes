package com.wuyue.springboot.mapper;

import com.wuyue.springboot.entities.Department;
import org.apache.ibatis.annotations.*;

/**
 * @author DeltaV235
 * @version 1.0
 * @className DepartmentMapper
 * @description
 * @date 2020/4/11 15:00
 */
@Mapper
public interface DepartmentMapper {
    @Select("select * from department where id = #{id}")
    Department getDeptById(Integer id);

    @Delete("delete from department where id = #{id}")
    void delDeptById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into department (departmentName) values(#{departmentName})")
    void insertDept(Department department);

    @Update("update department set departmentName = #{departmentName} where id = #{id}")
    void updateDept(Department department);
}
