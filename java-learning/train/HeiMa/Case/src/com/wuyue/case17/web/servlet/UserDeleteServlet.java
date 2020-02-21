package com.wuyue.case17.web.servlet;

import com.wuyue.case17.service.UserService;
import com.wuyue.case17.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UserDeleteServlet
 * @description 将请求参数中的id传递给service处理，删除指定的用户信息
 * @date 2020/2/18 21:31
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        UserService userService = new UserServiceImpl();
        if (null != id) {
            userService.deleteUser(Integer.parseInt(id));
            response.sendRedirect(request.getContextPath() + "/ListByPage?currentPage=1&rows=5");
        } else {
            // TODO 提示错误信息
            // request.setAttribute()
            request.getRequestDispatcher("/ListByPage?currentPage=1&rows=5").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
