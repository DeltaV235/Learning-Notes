package com.wuyue.case17.web;

import com.wuyue.case17.dao.entities.User;
import com.wuyue.case17.service.UserService;
import com.wuyue.case17.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UserListServlet
 * @description 处理用户的查询请求，并将结果转发至list.jsp中显示
 * @date 2020/2/18 18:22
 */
@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        List<User> users = userService.findAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
