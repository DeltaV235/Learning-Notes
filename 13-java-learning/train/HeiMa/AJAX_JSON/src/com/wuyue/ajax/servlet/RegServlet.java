package com.wuyue.ajax.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DeltaV235
 * @version 1.0
 * @className RegServlet
 * @description 检查输入用户名是否已使用
 * @date 2020/2/23 15:17
 */
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        response.setContentType("application/json;charset=utf-8");

        Map<String, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        if ("test".equals(username)) {
            map.put("userExist", true);
            map.put("msg", "用户名已使用");
        } else {
            map.put("userExist", false);
            map.put("msg", "用户名可用");
        }
        mapper.writeValue(response.getWriter(), map);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
