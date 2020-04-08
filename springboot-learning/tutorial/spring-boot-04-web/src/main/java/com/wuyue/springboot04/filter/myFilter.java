package com.wuyue.springboot04.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className MyFilter
 * @description
 * @date 2020/4/8 15:14
 */
public class myFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("myFilter");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
