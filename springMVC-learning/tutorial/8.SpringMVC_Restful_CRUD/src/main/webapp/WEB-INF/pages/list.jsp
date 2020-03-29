<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: DeltaV
  Date: 2020/3/26
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list</title>
    <style>
        table {
            border: 1px solid;
        }

        td {
            border: 1px solid;
        }

        th {
            border: 1px solid;
        }

        form {
            margin: 0;
        }
    </style>
</head>
<body>
<table cellspacing="0" cellpadding="2px">
    <tr>
        <th>ID</th>
        <th>LastName</th>
        <th>Email</th>
        <th>Gender</th>
        <th>Department</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${requestScope.emps}" var="emp">
        <tr>
            <td>${emp.id}</td>
            <td>${emp.lastName}</td>
            <td>${emp.email}</td>
            <td>
                <c:if test="${emp.gender==0}">
                    Male
                </c:if>
                <c:if test="${emp.gender==1}">
                    Female
                </c:if>
            </td>
            <td>${emp.department.departmentName}</td>
            <td>
                <form action="emp/${emp.id}" method="get">
                    <input type="submit" value="Edit">
                </form>
            </td>
            <td>
                <form action="emp/${emp.id}" method="post">
                    <input type="hidden" name="_method" value="delete">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="emp"><b>Add Employee</b></a>

<%--<h1>${requestScope.employee.lastName}</h1>--%>
</body>
</html>
