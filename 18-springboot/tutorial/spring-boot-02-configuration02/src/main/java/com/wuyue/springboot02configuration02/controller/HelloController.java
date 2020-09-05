package com.wuyue.springboot02configuration02.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DeltaV235
 * @version 1.0
 * @className HelloController
 * @description
 * @date 2020/4/4 15:44
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
