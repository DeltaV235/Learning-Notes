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
 * @className ResponseServlet1
 * @description 测试状态码
 * @date 2020/2/15 22:31
 */
@WebServlet("/ResponseServlet1")
public class ResponseServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int i = 3/0;
    }
}
