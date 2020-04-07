package com.wuyue.springboot04.controller;

import com.wuyue.springboot04.dao.DepartmentDao;
import com.wuyue.springboot04.dao.EmployeeDao;
import com.wuyue.springboot04.entities.Department;
import com.wuyue.springboot04.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author DeltaV235
 * @version 1.0
 * @className EmployeeController
 * @description
 * @date 2020/4/7 2:07
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    // 删除指定员工
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }

    // 修改员工信息
    @PutMapping("/emp")
    public String updateEmp(Employee employee) {
        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    // 跳转到员工修改页面
    @GetMapping("/emp/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id, Model model) {
        Employee oldEmp = employeeDao.get(id);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("emp", oldEmp).addAttribute("depts", departments);
        return "emp/add";
    }

    // 添加员工
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    // 处理添加页面请求
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    // 查询所有员工
    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps", employees);
        return "emp/list";
    }
}
