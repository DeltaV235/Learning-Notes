package com.wuyue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author DeltaV235
 * @version 1.0
 * @className HelloController
 * @description
 * @date 2020/3/21 15:34
 */
@Controller
public class HelloController {

    @RequestMapping("hello")
    public String handle01() {
        System.out.println("handle01 has been invoked");
        return "success";
    }
}














