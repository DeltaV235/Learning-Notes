package com.wuyue.springboot04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author DeltaV235
 * @version 1.0
 * @className LoginController
 * @description
 * @date 2020/4/6 23:35
 */
@Controller
@RequestMapping("/user")
public class LoginController {
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session) {
        if (!StringUtils.isEmpty(username) && "1234".equals(password)) {
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        }
        model.addAttribute("msg", "用户名或密码不正确");
        return "login";
    }
}
