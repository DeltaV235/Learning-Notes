package com.wuyue.web.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * 测试Servlet的生命周期，以及在xml中配置是否在Tomcat服务器启动时实例化servlet并执行init方法
 *
 * @author DeltaV235
 */
public class Demo2 implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("--------------init--------------");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("--------------service--------------");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("--------------destory--------------");
    }
}
