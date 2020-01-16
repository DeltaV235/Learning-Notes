package com.wuyue.server03;

public class LoginServlet implements Servlet {

    @Override
    public void service(Request request, Response response) {
        response.print("<html>");
        response.print("<head>");
        response.print("<title>");
        response.print("第一个Servlet");
        response.print("</title>");
        response.print("</head>");
        response.print("<body>");
        response.print("不欢迎，滚！" + request.getParaValue("name"));
        response.print("</body>");
        response.print("</html>");

    }
}
