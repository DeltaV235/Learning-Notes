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
@WebServlet("/SessionDemo3")
public class SessionDemo3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("test", "haha");
        System.out.println("session = " + session);
        request.getRequestDispatcher("SessionDemo4").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
