package com.wuyue.springboot04.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author DeltaV235
 * @version 1.0
 * @className LoginInterceptor
 * @description
 * @date 2020/4/7 0:12
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("loginUser") == null) {
            request.setAttribute("msg", "请先登录");
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        }
        return true;
    }
}
