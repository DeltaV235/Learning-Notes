package com.wuyue.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className CookieServletDemo5
 * @description 设置cookie的path和domain
 * @date 2020/2/16 16:38
 */
@WebServlet("/CookieServletDemo5")
public class CookieServletDemo5 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("PathTest", "好吃的");
        cookie.setPath("/");
//        cookie.setMaxAge(0);
//        cookie.setDomain(".baidu.com");
        response.addCookie(cookie);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
