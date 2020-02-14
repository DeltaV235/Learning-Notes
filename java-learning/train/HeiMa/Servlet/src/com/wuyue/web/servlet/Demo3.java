package com.wuyue.web.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className Demo3
 * @description 测试Servlet注解配置url-patterns
 * @date 2020/2/13 20:37
 */
//@WebServlet(urlPatterns = "/3")
@WebServlet("/3")
public class Demo3 implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("----------service---------");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
