package com.wuyue.controller;

import com.wuyue.mapper.intf.EmployeeMapper;
import com.wuyue.model.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

/**
 * @author DeltaV235
 * @version 1.0
 * @className AjaxController
 * @description
 * @date 2020/3/29 14:06
 */
@Controller
public class AjaxController {

    @Autowired
    private EmployeeMapper employeeMapper;

    @RequestMapping("/download")
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request) throws IOException {
        String location = request.getServletContext().getRealPath("/scripts/jquery-1.9.1.min.js");
        File file = new File(location);
        FileInputStream fis = new FileInputStream(file);
        byte[] fileBytes = new byte[fis.available()];
        fis.read(fileBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + file.getName());
        return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
    }

    @RequestMapping("/test02")
    public ResponseEntity<String> test02() {
        String body = "<h1>HelloWorld</h1>";
        MultiValueMap<String, String> header = new HttpHeaders();
        header.add("Set-Cookie", "test=teardrop");
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<String>(body, header, status);
    }

    @ResponseBody
    @RequestMapping("/ajax")
    public Collection<Employee> ajaxGetAll() {
        Collection<Employee> employees = employeeMapper.getAll();
        return employees;
    }

    @RequestMapping("/req")
    public String reqBodyTest(@RequestBody String body) {
        System.out.println(body);
        return "success";
    }

    @RequestMapping("/reqEmp")
    public String reqEmpTest(@RequestBody Employee employee) {
        System.out.println(employee);
        return "forward:/emps";
    }

    @RequestMapping("/test01")
    public String test01(HttpEntity<String> entity) {
        System.out.println(entity);
        return "forward:/emps";
    }
}














