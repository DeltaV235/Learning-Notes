package com.wuyue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author DeltaV235
 * @version 1.0
 * @className RequestMappingController
 * @description
 * @date 2020/3/20 17:51
 */

@RequestMapping("test")
@Controller
public class RequestMappingController {

    @RequestMapping(value = "/handle01", method = {RequestMethod.POST, RequestMethod.GET})
    public String handle01() {
        System.out.println("RequestMappingController.handle01");
        return "success";
    }

    @RequestMapping(value = "/handle02", params = {"username=123", "age!=88", "!sex"})
    public String handle02() {
        System.out.println("RequestMappingController.handle02");
        return "success";
    }

    @RequestMapping("/{username}")
    public String handle03(@PathVariable("username") String username) {
        System.out.println("占位符为: " + username);
        return "success";
    }
}














