package com.wuyue.web.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className Demo4
 * @description 测试GenericServlet类
 * @date 2020/2/13 23:01
 */
@WebServlet("/demo4")
public class Demo4 extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("demo4 is running");
    }
}
