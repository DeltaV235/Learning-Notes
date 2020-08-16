package com.wuyue.helloworld.concroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DeltaV235
 * @version 1.0
 * @className HelloController
 * @description
 * @date 2020/4/2 15:56
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String Hello() {
        return "hello world!";
    }
}














