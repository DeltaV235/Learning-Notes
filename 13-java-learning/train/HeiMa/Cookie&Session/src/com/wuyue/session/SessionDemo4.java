package com.wuyue.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className SessionDemo3
 * @description 若采用请求转发的方式调用其他资源，那么session是否能够传递数据?
 * @date 2020/2/17 22:45
 */
@WebServlet("/SessionDemo4")
public class SessionDemo4 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String str = (String) session.getAttribute("test");
        System.out.println("session = " + session);
        if (str != null)
            System.out.println("str = " + str);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
