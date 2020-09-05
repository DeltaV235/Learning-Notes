package com.wuyue.server05.user;

import com.wuyue.server05.core.Request;
import com.wuyue.server05.core.Response;
import com.wuyue.server05.core.Servlet;

public class RegisterServlet implements Servlet {

    @Override
    public void service(Request request, Response response) {response.print("<html>");
        response.print("<head>");
        response.print("<meta charset=utf-8>");
        response.print("<title>");
        response.print("</title>");
        response.print("</head>");
        response.print("<body>");
        response.print("注册成功，用户名为: " + request.getParaValue("uname"));
        response.print("密码为: " + request.getParaValue("pwd"));
        response.print("</body>");
        response.print("</html>");
    }
}
