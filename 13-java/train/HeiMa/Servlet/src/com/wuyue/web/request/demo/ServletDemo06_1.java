package com.wuyue.web.request.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className ServletDemo06_1
 * @description 测试servlet的请求转发，和多个servlet之间的数据共享
 * @date 2020/2/14 20:08
 */
@WebServlet("/ServletDemo06_1")
public class ServletDemo06_1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("ServletDemo06_1");
        request.setAttribute("msg", "hello!!!");
        request.getRequestDispatcher("/ServletDemo06_2").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
