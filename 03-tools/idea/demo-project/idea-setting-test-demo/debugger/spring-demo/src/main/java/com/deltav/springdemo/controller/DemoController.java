package com.deltav.springdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DeltaV235
 * @version 1.0
 */
@RestController
public class DemoController {
    @GetMapping("/demo")
    public String demoHandler() {
        int i = 0;
        System.out.println("demo hit, i = " + i);
        return "{\"field\": \"demoValue\", \"field2\": \"demoValue\"}";
    }
}
