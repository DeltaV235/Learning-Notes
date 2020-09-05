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
 * @className ResponseServlet2_2
 * @description 测试重定向，ResponseServlet2重定向至的servlet
 * @date 2020/2/15 22:59
 */
@WebServlet("/ResponseServlet2_2")
public class ResponseServlet2_2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo2_2");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
