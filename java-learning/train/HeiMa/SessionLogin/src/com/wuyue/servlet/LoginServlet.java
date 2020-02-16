package com.wuyue.servlet;

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
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
