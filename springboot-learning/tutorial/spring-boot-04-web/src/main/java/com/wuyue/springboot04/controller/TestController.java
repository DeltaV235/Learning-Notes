package com.wuyue.springboot04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

/**
 * @author DeltaV235
 * @version 1.0
 * @className TestController
 * @description
 * @date 2020/4/5 23:50
 */
@Controller
public class TestController {
    @RequestMapping("/success")
    public String success(Model model) {
        model.addAttribute("msg", "<h1>SpringBoot</h1>");
        model.addAttribute("users", Arrays.asList("zhangsan", "lisi", "wangwu"));
        return "success";
    }
}
