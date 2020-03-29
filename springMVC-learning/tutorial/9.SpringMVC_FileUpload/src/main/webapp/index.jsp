<%--
  Created by IntelliJ IDEA.
  User: DeltaV
  Date: 2020/3/30
  Time: 2:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Upload</title>
    <%
        pageContext.setAttribute("ctp", request.getContextPath());
    %>
</head>
<body>
${requestScope.msg}
<form action="${ctp}/upload" method="post" enctype="multipart/form-data">
    用户名: <input type="text" name="username"><br>
    文件上传: <input type="file" name="userFile"><br>
    <input type="submit" value="上传">
</form>
</body>
</html>
