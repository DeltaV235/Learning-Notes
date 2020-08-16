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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author DeltaV235
 * @version 1.0
 * @className FindByCondition
 * @description 处理条件查询请求
 * @date 2020/2/21 17:38
 */
@WebServlet("/ListByPage")
public class FindByCondition extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 获取请求参数
        Map<String, String[]> _parameterMap = request.getParameterMap();
        UserService userService = new UserServiceImpl();

        // 将apache map中的键值对克隆到 HashMap中，因为request返回的Map无法修改
        Map<String, String> parameterMap = new HashMap<>();
        Set<String> keySet = _parameterMap.keySet();
        for (String key : keySet) {
            String value = _parameterMap.get(key)[0];
            parameterMap.put(key, value);
        }

        // 将请求参数Map交由service处理
        PageBean<User> pageBean = userService.findUserByConditionAndPage(parameterMap);

        // 将请求重定向，并在request中添加查询结果
        request.setAttribute("condition", parameterMap);
        request.setAttribute("userByPage", pageBean);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
