package com.wuyue.case17.web.servlet;

import com.wuyue.case17.entities.PageBean;
import com.wuyue.case17.entities.User;
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
 * @className ListByPage
 * @description 通过至今的页数和每页显示的记录数，返回对应的PageBean
 * @date 2020/2/21 13:46
 * @deprecated 由FindByCondition代替
 */
@Deprecated
@WebServlet(name = "/ListByPage")
public class ListByPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        if ("".equals(currentPage))
            currentPage = "1";
        if ("".equals(rows))
            rows = "5";
        UserService userService = new UserServiceImpl();
        PageBean<User> userByPage = userService.findUserByPage(currentPage, rows);
        request.setAttribute("userByPage", userByPage);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
