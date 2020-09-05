package com.wuyue.springboot08test.controller;

import com.wuyue.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DeltaV235
 * @version 1.0
 * @className HelloController
 * @description
 * @date 2020/4/11 23:40
 */
@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String getName() {
        return helloService.getName("wuyue");
    }
}
