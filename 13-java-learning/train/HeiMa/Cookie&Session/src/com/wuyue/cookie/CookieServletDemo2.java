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
 * @className CookieServletDemo2
 * @description 获取cookie
 * @date 2020/2/16 15:27
 */
@WebServlet("/CookieServletDemo2")
public class CookieServletDemo2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        System.out.println(request.getContextPath() + ":\n");
        for (Cookie cookie : cookies) {
            System.out.println("cookie.getName() = " + cookie.getName());
            System.out.println("cookie.getValue() = " + cookie.getValue());
            System.out.println("-".repeat(70));
        }
        System.out.println("\n\n\n\n\n");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
