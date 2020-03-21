package com.wuyue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String handle01(@RequestParam(value = "username", required = false) String user) {
        System.out.println("handle01 has been invoked, username is " + user);
        return "success";
    }

    @RequestMapping("header")
    public String handle02(@RequestHeader(value = "Header-Agent", required = false, defaultValue = "noHeader") String userAgent) {
        System.out.println("handle02 has been invoked, User-Agent is " + userAgent);
        return "success";
    }

    @RequestMapping("cookie")
    public String handle03(@CookieValue("JSESSIONID") String jid) {
        System.out.println("handle03 has been invoked, cookie is " + jid);
        return "success";
    }
}














