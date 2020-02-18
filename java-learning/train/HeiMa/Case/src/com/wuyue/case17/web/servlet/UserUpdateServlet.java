package com.wuyue.case17.web.servlet;

import com.wuyue.case17.entities.User;
import com.wuyue.case17.service.UserService;
import com.wuyue.case17.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UserUpdateServlet
 * @description 若通过get方式请求，则调用UserService.findUser查询指定id的用户信息，并将请求转发至update.jsp。否则封装提交的
 * 表单为User对象，调用UserService.updateUser来更新用户信息，并重定向至"/UserListServlet"
 * @date 2020/2/19 0:50
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    // 在update.jsp页面修改完成后，提交表单
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
            userService.updateUser(user);
            response.sendRedirect(request.getContextPath() + "/UserListServlet");
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    // 点击修改按钮后，在update.jsp上显示原来的用户信息
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User user = userService.findUser(Integer.parseInt(id));
        request.setAttribute("user", user);
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }
}
