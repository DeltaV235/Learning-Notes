package com.wuyue.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author DeltaV235
 * @version 1.0
 * @className ServletDemo02
 * @description 测试request中获取请求头的方法
 * @date 2020/2/14 1:34
 */
@WebServlet("/ServletDemo02")
public class ServletDemo02 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取所有请求头键值对
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + " = " + value);
        }

        // 获取user-agent的值
        System.out.println("=".repeat(35));
        String userAgent = request.getHeader("user-agent");
        System.out.println("userAgent = " + userAgent);

        // 获取referer
        System.out.println("+".repeat(35));
        String referer = request.getHeader("referer");
        System.out.println("referer = " + referer + "\n");
    }
}
