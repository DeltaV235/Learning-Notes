package com.wuyue.controller;

import com.wuyue.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

    @RequestMapping("/book")
    public String handle04(Book book) {
        System.out.println(book);
        return "success";
    }

    @RequestMapping("Servlet")
    public String handle05(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        request.setAttribute("requestTest", "请求域参数");
        session.setAttribute("sessionTest", "会话域中参数");
        return "success";
    }

    @RequestMapping(value = "methodTest", method = RequestMethod.PUT)
    public String handle06() {
        System.out.println("PUT");
        return "success";
    }


}














