package com.wuyue.web.response.servletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className ServletContextDemo4
 * @description 获取web项目目录下文件的真实路径(文件系统路径)
 * @date 2020/2/16 1:42
 */
@WebServlet("/ServletContextDemo4")
public class ServletContextDemo4 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        String realPath = servletContext.getRealPath("/a.txt");
        System.out.println("a_realPath = " + realPath);
        realPath = servletContext.getRealPath("/WEB-INF/b.txt");
        System.out.println("b_realPath = " + realPath);
        realPath = servletContext.getRealPath("/WEB-INF/classes/c.txt");
        System.out.println("c_realPath = " + realPath);
        String path = ServletContextDemo4.class.getClassLoader().getResource("c.txt").getPath();
        System.out.println("c_path = " + path);
        System.out.println("System.getProperty(\"user.dir\") = " + System.getProperty("user.dir"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
