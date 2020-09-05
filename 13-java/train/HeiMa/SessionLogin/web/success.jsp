<%--
  Created by IntelliJ IDEA.
  User: DeltaV
  Date: 2020/2/17
  Time: 1:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
</head>
<body>
<h1>
    <%
        String username = (String) request.getSession().getAttribute("username");
        out.write(username);
    %>
    ，欢迎您！</h1>
</body>
</html>
