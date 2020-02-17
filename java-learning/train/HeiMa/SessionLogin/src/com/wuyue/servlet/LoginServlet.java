package com.wuyue.servlet;

import com.wuyue.dao.UserDao;
import com.wuyue.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @author DeltaV235
 * @version 1.0
 * @className LoginServlet
 * @description 处理用户登录业务
 * @date 2020/2/16 23:36
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> form = request.getParameterMap();
        Set<String> keySet = form.keySet();
        String username = "";
        String password = "";
        String checkCode = "";
        // 获取三个需要的请求表单参数
        for (String key : keySet) {
            switch (key) {
                case "username": {
                    String[] values = form.get(key);
                    if (values != null)
                        username = values[0];
                }
                case "password": {
                    String[] values = form.get(key);
                    if (values != null)
                        password = values[0];
                }
                case "checkCode": {
                    String[] values = form.get(key);
                    if (values != null)
                        checkCode = values[0];
                }
            }
        }

        // 判断验证码是否正确
        String rightCheckCode = (String) request.getSession().getAttribute("checkCode");
        if (rightCheckCode != null) {
            // checkCode is right
            if (checkCode.equalsIgnoreCase(rightCheckCode)) {
                // 判断数据库中是否存在该用户
                UserDao dao = new UserDao();
                User loginUser = new User();
                loginUser.setUsername(username);
                loginUser.setPassword(password);
                User user = dao.login(loginUser);
                if (user != null) {
                    // 用户名密码正确
                    request.getSession().setAttribute("username", user.getUsername());
                    response.sendRedirect("success.jsp");
                } else {
                    // 用户名或密码错误
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
                    request.setAttribute("isSuccess", false);
                    requestDispatcher.forward(request, response);
                }

            } else {
                // 验证码不正确
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
                request.setAttribute("checkCode", false);
                requestDispatcher.forward(request, response);
            }


        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
