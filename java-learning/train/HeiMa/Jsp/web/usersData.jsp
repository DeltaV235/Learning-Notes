<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.wuyue.jsp.User" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: DeltaV
  Date: 2020/2/18
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>案例</title>
</head>
<body>
<%
    User user1 = new User("wuyue", 18, new Date(4012345596134L));
    User user2 = new User("zjn", 18, new Date());
    User user3 = new User("lyh", 18, new Date(40245596134L));
    User user4 = new User("lcy", 30, new Date(4992345596134L));
    List<User> list = new LinkedList<>();
    list.add(user1);
    list.add(user2);
    list.add(user3);
    list.add(user4);
    request.setAttribute("list", list);
%>

<table style="border: 1px solid">
    <tr>
        <th style="border: 1px solid">序号</th>
        <th style="border: 1px solid">姓名</th>
        <th style="border: 1px solid">年龄</th>
        <th style="border: 1px solid">生日</th>
    </tr>
    <c:forEach items="${requestScope.list}" var="user" varStatus="status">
        <tr>
            <td style="border: 1px solid">
                    ${status.count}
            </td>
            <td style="border: 1px solid">
                    ${user.name}
            </td>
            <td style="border: 1px solid">
                    ${user.age}
            </td>
            <td style="border: 1px solid">
                    ${user.birStr}
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
