<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DeltaV
  Date: 2020/3/26
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add</title>
    <style>
        * {
            margin-top: 12px;
        }
    </style>
</head>
<body>
<form action="emp" method="post">
    LastName: <input type="text" name="lastName"><br>
    Email: <input type="text" name="email"><br>
    Gender: <input type="radio" name="gender" value="0" checked>Male&nbsp;<input type="radio" name="gender" value="1">Female
    <br>
    Department:
    <select name="department.id">
        <c:forEach items="${requestScope.depts}" var="dept">
            <option value="${dept.id}">${dept.departmentName}</option>
        </c:forEach>
    </select>
    <br>
    <input type="submit" value="Submit">
</form>
<h1>${requestScope.employee.lastName}</h1>
</body>
</html>
