package com.wuyue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author DeltaV235
 * @version 1.0
 * @className OutputController
 * @description
 * @date 2020/3/22 0:17
 */
@Controller
public class OutputController {
    @RequestMapping("/output01")
    public String handle01(Map<String, Object> outputMap) {
        outputMap.put("msg", "map");
        System.out.println(outputMap.getClass());
        return "success";
    }

    @RequestMapping("/output02")
    public String handle02(Model model) {
        model.addAttribute("msg", "model");
        System.out.println(model.getClass());
        return "success";
    }

    @RequestMapping("/output03")
    public String handle03(ModelMap modelMap) {
        modelMap.put("msg", "modelMap");
        System.out.println(modelMap.getClass());
        return "success";
    }

    @RequestMapping("/output04")
    public ModelAndView handle04() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        modelAndView.addObject("msg", "modelAndView");
        System.out.println(modelAndView.getClass());
        return modelAndView;
    }
}














