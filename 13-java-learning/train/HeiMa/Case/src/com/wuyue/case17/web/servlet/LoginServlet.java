package com.wuyue.case17.web.servlet;

import com.wuyue.case17.entities.User;
import com.wuyue.case17.service.UserService;
import com.wuyue.case17.service.impl.UserServiceImpl;

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
 * @className LoginServlet
 * @description 处理用户的登录请求，若验证码和用户名密码均正确，则重定向至index.jsp
 * @date 2020/2/19 17:40
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取session的正确的验证码
        String checkCode = null;
        HttpSession session = request.getSession();
        Object sessionAttribute = session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (sessionAttribute != null)
            if (sessionAttribute instanceof String) {
                checkCode = (String) sessionAttribute;
            }

        // 获取用户提交的表单内容
        String verifycode = request.getParameter("verifycode");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User loginUser = new User(username, password);

        // 交由service判断输入是否合法
        boolean isLegal = false;
        UserService userService = new UserServiceImpl();
        boolean checkCodeLegal = userService.isCheckCodeLegal(checkCode, verifycode);
        // 验证码正确，判断用户名和密码是否正确，若正确则isLegal为true
        if (checkCodeLegal)
            isLegal = userService.isLegal(loginUser);

        // 登录成功，在session添加isLegal = true的参数，并重定向至index.jsp
        if (isLegal) {
            session.setAttribute("isLegal", true);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            // 登录失败，验证码或用户名密码错误，重定向至login.jsp，并在session中添加 isLegal = false的参数
            request.setAttribute("isLegal", false);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
