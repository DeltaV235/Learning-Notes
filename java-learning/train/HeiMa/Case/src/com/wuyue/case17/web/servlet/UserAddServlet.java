package com.wuyue.case17.web.servlet;

import com.wuyue.case17.entities.User;
import com.wuyue.case17.service.UserService;
import com.wuyue.case17.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.StringConverter;

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
 * @className UserAddServlet
 * @description 处理add.jsp提交的表单，将其封装为User对象，然后交给service层处理
 * @date 2020/2/18 20:15
 */
@WebServlet("/UserAddServlet")
public class UserAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            // 将Integer和String转换器的默认值设为null，防止原属性为null时，自动把目标属性转换为0或""
            ConvertUtils.register(new IntegerConverter(null), Integer.class);
            ConvertUtils.register(new StringConverter(null), String.class);
            BeanUtils.populate(user, parameterMap);
            UserService userService = new UserServiceImpl();
            boolean isSuccess = userService.addUser(user);
            if (isSuccess)
                response.sendRedirect(request.getContextPath() + "/UserListServlet");
            else {
                request.getRequestDispatcher("/add.jsp").forward(request, response);
                // TODO 提示错误信息
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
