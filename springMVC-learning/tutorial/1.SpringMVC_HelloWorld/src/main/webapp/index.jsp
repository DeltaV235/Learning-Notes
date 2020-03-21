<%--
  Created by IntelliJ IDEA.
  User: DeltaV
  Date: 2020/3/20
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HelloWorld</title>
</head>
<body>
<a href="hello">helloWorld</a>
<br>
<a href="test/handle01">test/handle01</a>

<hr/>
<a href="books/1">查询图书</a>
<br>
<form action="books/1" method="post">
    <input type="hidden" name="_method" value="put">
    <input type="submit" value="更新图书">
</form>
<br>
<form action="books/1" method="post">
    <input type="hidden" name="_method" value="delete">
    <input type="submit" value="删除图书">
</form>
<br>
<form action="books" method="post">
    <input type="submit" value="增加图书">
</form>
</body>
</html>
