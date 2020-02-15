package com.wuyue.web.request.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className SuccessServlet
 * @description 若登录成功，则在页面上打印出“登录成功”的提示
 * @date 2020/2/15 15:22
 */
@WebServlet("/SuccessServlet")
public class SuccessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object username = request.getAttribute("username");
        if (username != null){
            if (username instanceof String) {
                System.out.println("登录成功!" + username + "，欢迎您");
                response.setHeader("Content-Type", "text/html; charset=utf-8");
                response.getWriter().write("登录成功! " + username + "，欢迎您");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
