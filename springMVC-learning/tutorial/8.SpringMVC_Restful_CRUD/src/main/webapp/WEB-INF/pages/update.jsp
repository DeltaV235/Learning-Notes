<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DeltaV
  Date: 2020/3/26
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update</title>
    <style>
        * {
            margin-top: 12px;
        }
    </style>
</head>
<body>
<h3>Update Employee</h3>
<form action="${requestScope.emp.id}" method="post">
    <input type="hidden" name="_method" value="put"/>
    Email: <input type="text" name="email" value="${requestScope.emp.email}"/>
    <br>
    Gender:
    <c:if test="${requestScope.emp.gender==0}">
        Male<input type="radio" name="gender" value="0" checked>
        Female<input type="radio" name="gender" value="1">
    </c:if>
    <c:if test="${requestScope.emp.gender==1}">
        Male<input type="radio" name="gender" value="0">
        Female<input type="radio" name="gender" value="1" checked>
    </c:if>
    <br>
    Department:
    <select name="department.id">
        <c:forEach items="${requestScope.depts}" var="dept">
            <option value="${dept.id}"
                    <c:if test="${requestScope.emp.department.id == dept.id}">selected</c:if>>${dept.departmentName}
            </option>
        </c:forEach>
    </select>
    <br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
