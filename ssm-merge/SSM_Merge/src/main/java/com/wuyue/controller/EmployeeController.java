package com.wuyue.controller;

import com.wuyue.entities.Employee;
import com.wuyue.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author DeltaV235
 * @version 1.0
 * @className EmployeeController
 * @description
 * @date 2020/4/1 3:12
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String getEmpById(@PathVariable("id") Integer id, Model model) {
        Employee emp = service.getEmpById(id);
        model.addAttribute("emp", emp);
        return "emp";
    }
}














