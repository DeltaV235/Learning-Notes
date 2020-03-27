package com.wuyue.controller;

import com.wuyue.mapper.intf.DepartmentMapper;
import com.wuyue.mapper.intf.EmployeeMapper;
import com.wuyue.model.entities.Department;
import com.wuyue.model.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/**
 * @author DeltaV235
 * @version 1.0
 * @className EmpController
 * @description
 * @date 2020/3/26 16:40
 */
@Controller
public class EmpController {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 查询所有emps
     *
     * @return
     */
    @RequestMapping("/emps")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        Collection<Employee> emps = employeeMapper.getAll();
        modelAndView.addObject("emps", emps);
        modelAndView.setViewName("list");
        return modelAndView;
    }

    /**
     * 获取数据库中的部门信息并将请求转发到 /WEB-INF/pages/add.jsp
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String forwardAddPage(ModelMap modelMap) {
        Collection<Department> departments = departmentMapper.getDepartments();
        modelMap.addAttribute("depts", departments);
        return "add";
    }

    /**
     * 将提交的表单写入到数据库中
     *
     * @param employee
     * @return
     */
    @Transactional
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String add(Employee employee) {
        employeeMapper.save(employee);
        return "redirect:/emps";
    }

    /**
     * 查询emp和depts,请求转发到update.jsp页面
     */
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String updatePage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeMapper.get(id);
        Collection<Department> departments = departmentMapper.getDepartments();
        model.addAttribute("emp", employee).addAttribute("depts", departments);
        return "update";
    }

    /**
     * 更新员工信息
     */
    @Transactional
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.PUT)
    public String update(@PathVariable("id") Integer id, Employee employee) {
        employee.setId(id);
        employeeMapper.update(employee);
        return "redirect:/emps";
    }

    /**
     * 删除员工信息
     */
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id) {
        employeeMapper.delete(id);
        return "redirect:/emps";
    }

    @RequestMapping("/quickAdd")
    public String quickAdd(@RequestParam("empinfo") Employee employee) {
        System.out.println(employee);
        return "redirect:/emps";
    }

}














