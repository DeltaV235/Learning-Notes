package com.wuyue.crowd.mvc.interceptor;

import com.wuyue.util.AccessForbiddenException;
import com.wuyue.util.CrowdConstant;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author DeltaV235
 * @version 1.0
 * @className LoginInterceptor
 * @description
 * @date 2020/5/7 13:45
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object admin = session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN.getStrConstant());
        if (null == admin)
            throw new AccessForbiddenException(CrowdConstant.MESSAGE_NOT_LOGIN.getStrConstant());
        return true;
    }
}
