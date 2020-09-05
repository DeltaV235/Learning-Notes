package com.wuyue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author DeltaV235
 * @version 1.0
 * @className MyFirstController
 * @description
 * @date 2020/3/20 14:45
 */
@Controller
public class MyFirstController {

    @RequestMapping("/hello")
    public String myFirstRequest() {
        System.out.println("Request");
        return "success";
    }
}














