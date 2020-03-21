<%--
  Created by IntelliJ IDEA.
  User: DeltaV
  Date: 2020/3/21
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HelloWorld</title>
</head>
<body>
<a href="hello">hello</a>
</body>
<form action="methodTest" method="post">
    <input type="hidden" name="_method" value="put">
    书名: <input type="text" name="bookName"><br>
    售价: <input type="text" name="price"><br>
    作者: <input type="text" name="author"><br>
    作者省: <input type="text" name="address.province"><br>
    作者市: <input type="text" name="address.city"><br>
    <input type="submit" value="提交">
</form>
</html>
