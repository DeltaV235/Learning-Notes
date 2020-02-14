package com.wuyue.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description 测试ServletRequest中获取请求行的一些方法
 * @date 2020/2/14 0:57
 * @author DeltaV235
 */
@WebServlet("/ServletDemo01")
public class ServletDemo01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();
        System.out.println("getMethod() = " + method);

        String contextPath = request.getContextPath();
        System.out.println("getContextPath() = " + contextPath);

        String servletPath = request.getServletPath();
        System.out.println("getServletPath() = " + servletPath);

        String queryString = request.getQueryString();
        System.out.println("getQueryString() = " + queryString);

        String requestURI = request.getRequestURI();
        System.out.println("getRequestURI() = " + requestURI);

        StringBuffer requestURL = request.getRequestURL();
        System.out.println("getRequestURL() = " + requestURL);

        String protocol = request.getProtocol();
        System.out.println("getProtocol() = " + protocol);

        String remoteAddr = request.getRemoteAddr();
        System.out.println("getRemoteAddr() = " + remoteAddr);
    }
}
