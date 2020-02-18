<%@ page import="com.wuyue.jsp.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
   测试EL表达式和JSTL标签库
  Created by IntelliJ IDEA.
  User: DeltaV
  Date: 2020/2/18
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试域中对象的获取</title>
</head>
<body>
<%
    User user = new User();
    user.setName("wuyue");
    user.setAge(18);
    user.setBirthday(new Date());
    request.setAttribute("user", user);
    String str = "abc";
    request.setAttribute("test", str);
%>

${requestScope.user.name}<br>
${requestScope.user.age}<br>
${requestScope.user.birthday}<br>
${user.birStr}<br>

${pageContext.request.contextPath}<br>

<c:if test="${empty requestScope.test}">
    str is empty
</c:if>

<%
    request.setAttribute("date", 18);
%>

<c:choose>
    <c:when test="${date == 1}">周一</c:when>
    <c:when test="${date == 2}">周二</c:when>
    <c:when test="${date == 3}">周三</c:when>
    <c:when test="${date == 4}">周四</c:when>
    <c:when test="${date == 5}">周五</c:when>
    <c:when test="${date == 6}">周六</c:when>
    <c:when test="${date == 7}">周七</c:when>
    <c:otherwise>星期九</c:otherwise>
</c:choose>
<br>
<c:forEach begin="1" end="10" step="2" var="i" varStatus="s">
    ${i}&nbsp;${s.index}&nbsp;${s.count}<br>
</c:forEach>

<%
    List list = new ArrayList();
    list.add("bbbb");
    list.add("cccc");
    list.add("dddd");
    request.setAttribute("list", list);
%>

<c:forEach items="${list}" var="s" varStatus="status">
    ${s} ${status.index} ${status.count}<br>
</c:forEach>

</body>
</html>
