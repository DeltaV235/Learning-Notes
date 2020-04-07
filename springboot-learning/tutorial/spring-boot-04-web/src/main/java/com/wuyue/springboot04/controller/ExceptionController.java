package com.wuyue.springboot04.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DeltaV235
 * @version 1.0
 * @className ExceptionController
 * @description
 * @date 2020/4/7 23:33
 */
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public String expHandler(Exception e, HttpServletRequest request) {
        request.setAttribute("javax.servlet.error.status_code", 500);
        Map<String, Object> errorMsg = new HashMap<>();
        errorMsg.put("code", "10010");
        errorMsg.put("test", true);
        request.setAttribute("ext", errorMsg);
        return "forward:/error";
    }
}
