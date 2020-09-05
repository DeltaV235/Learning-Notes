package com.wuyue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

/**
 * @author DeltaV235
 * @version 1.0
 * @className ViewResolverHandler
 * @description
 * @date 2020/3/23 2:02
 */
@Controller
public class ViewResolverHandler {
    @RequestMapping("/handle01")
    public String handle01() {
        System.out.println("handle01");
        return "success";
    }

    @RequestMapping("/handle02")
    public String handle02() {
        System.out.println("handle02");
        return "forward:/handle01";
    }

    @RequestMapping("/handle03")
    public String handle03() {
        System.out.println("handle03");
        return "redirect:/view.jsp";
    }

    @RequestMapping("/handle04")
    public String handle04() {
        System.out.println("handle04");
        return "redirect:/handle03";
    }

    @RequestMapping("/myview")
    public ModelAndView myView() {
        System.out.println("handler:myView");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("wuyue:/");
        modelAndView.addObject("string", "现杀").addObject("list", Arrays.asList("1", "2"));
        return modelAndView;
    }
}














