<%--
  Created by IntelliJ IDEA.
  User: DeltaV
  Date: 2020/4/22
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>RedisTest</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/doKill" method="post">
    <input type="hidden" name="pid" value="1010">
    <input type="submit" value="submit">
</form>
</body>
</html>
