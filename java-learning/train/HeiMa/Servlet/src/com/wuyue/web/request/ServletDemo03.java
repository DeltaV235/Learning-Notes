package com.wuyue.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className ServletDemo03
 * @description 测试request获取请求体的方法
 * @date 2020/2/14 14:20
 */
@WebServlet("/ServletDemo03")
public class ServletDemo03 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String line = null;
        while ((line = reader.readLine()) != null)
            System.out.println(line);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
