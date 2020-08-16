package com.wuyue.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author DeltaV235
 * @version 1.0
 * @className ExHandler
 * @description
 * @date 2020/3/31 14:14
 */
@ControllerAdvice
public class ExHandler {
    public ExHandler() {
        System.out.println("ExceptionHandler...");
    }

    //    @ExceptionHandler(Exception.class)
    public ModelAndView exHandler2(Exception ex) {
        System.out.println("exHandler2.." + ex);
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("ex", ex);
        return mv;
    }

    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView exHandler(Exception ex) {
        System.out.println("exHandler.." + ex);
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("ex", ex);
        return mv;
    }
}














