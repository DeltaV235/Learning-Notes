package com.wuyue.web.response.demo;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author DeltaV235
 * @version 1.0
 * @className ResponseServlet3
 * @description 向客户端输出字符/字节数据，并解决中文乱码问题
 * @date 2020/2/15 23:45
 */
@WebServlet("/ResponseServlet3")
public class ResponseServlet3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setCharacterEncoding("GBK");
        response.setContentType("text/html;charset=utf-8");
//        PrintWriter writer = response.getWriter();
//        writer.write("来打我aaaa");

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write("不要打我啊".getBytes("utf-8"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
