package com.wuyue.web.response.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className ResponseServlet2
 * @description 测试Response的重定向，小行星重定向计划，重定向至/ResponseServlet2_2
 * @date 2020/2/15 22:57
 */
@WebServlet("/ResponseServlet2")
public class ResponseServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo2");
//        response.setStatus(302);
//        response.setHeader("location", "/ResponseServlet2_2");
        response.sendRedirect("ResponseServlet2_2");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
