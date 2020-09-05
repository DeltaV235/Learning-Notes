<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<form:form action="${employee.id}" method="post" modelAttribute="employee">
    <input type="hidden" name="_method" value="put"/>
    Email: <form:input type="text" path="email"/><form:errors path="email"/>&nbsp;${requestScope.errors.email}
    <br>
    Gender:
    Male<form:radiobutton path="gender" value="0"/>
    Female<form:radiobutton path="gender" value="1"/>
    <br>
    Department:
    <form:select path="department.id" items="${depts}" itemLabel="departmentName" itemValue="id"/>
    <br>
    <input type="submit" value="Submit">
</form:form>
</body>
</html>
