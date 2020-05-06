package com.wuyue.crowd.mvc.controller;

import com.wuyue.crowd.service.inter.AdminService;
import com.wuyue.util.CrowdUtil;
import entity.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className TestController
 * @description
 * @date 2020/5/5 15:50
 */
@Controller
public class TestController {

    private final AdminService adminService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public TestController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/index.html")
    public String testHandler(Model model, HttpServletRequest request) {
        List<Admin> all = adminService.getAll();
        model.addAttribute("Admins", all);
        String nullTest = null;
        nullTest.trim();
        logger.info(String.valueOf(CrowdUtil.judgeRequestType(request)));
        return "index";
    }

    @RequestMapping("/test/array3.html")
    @ResponseBody
    public String testJsonWithArray3(@RequestBody List<Integer> array, HttpServletRequest request) {
        logger.info(String.valueOf(CrowdUtil.judgeRequestType(request)));
        for (Integer num : array) {
            logger.info(num + "");
        }
        return "success";
    }

    @RequestMapping("/test/array2.html")
    @ResponseBody
    public String testJsonWithArray2(@RequestParam("array") List<Integer> array) {
        for (Integer num : array) {
            logger.info(num + "");
        }
        return "success";
    }

    @RequestMapping("/test/array1.html")
    @ResponseBody
    public String testJsonWithArray(@RequestParam("array[]") List<Integer> array) {
        for (Integer num : array) {
            logger.info(num + "");
        }
        return "success";
    }
}
