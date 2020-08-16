package com.wuyue.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className Demo6
 * @description 测试url-patterns相关的配置
 * @date 2020/2/13 23:30
 */
//@WebServlet({"/d6", "/dd6", "/ddd6"})
//@WebServlet("/d6/hehe")
//@WebServlet("/demo/*")
@WebServlet("*.hehe")
public class Demo6 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo6");
    }
}
