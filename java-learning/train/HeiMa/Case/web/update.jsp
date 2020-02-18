<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <%--    <base href="<%=basePath%>"/>--%>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <script src="js/reset-btn.js"></script>
    <script src="js/back-btn.js"></script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="${pageContext.request.contextPath}/UserUpdateServlet" method="post">
        <input type="hidden" name="id" value="${requestScope.user.id}">
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" readonly="readonly" placeholder="请输入姓名"
                   value="${requestScope.user.name}"/>
        </div>

        <div class="form-group">
            <label>性别：</label>

            <input type="radio" name="gender" value="男"
                    <c:if test="${requestScope.user.gender == '男'}">
                        checked
                    </c:if>
            />男
            <input type="radio" name="gender" value="女"
                    <c:if test="${requestScope.user.gender == '女'}">
                        checked
                    </c:if>
            />女


        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age" name="age" placeholder="请输入年龄"
                   value="${requestScope.user.age}"/>
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="address" id="address" class="form-control">
                <option value="广东"
                        <c:if test="${requestScope.user.address == '广东'}">
                            selected
                        </c:if>
                >广东
                </option>
                <option value="广西"
                        <c:if test="${requestScope.user.address == '广西'}">
                            selected
                        </c:if>
                >广西
                </option>
                <option value="湖南"
                        <c:if test="${requestScope.user.address == '湖南'}">
                            selected
                        </c:if>
                >湖南
                </option>
            </select>
        </div>

        <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" id="qq" class="form-control" name="qq" placeholder="请输入QQ号码"
                   value="${requestScope.user.qq}"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" id="email" class="form-control" name="email" placeholder="请输入邮箱地址"
                   value="${requestScope.user.email}"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"/>
            <input id="reset" class="btn btn-default" type="reset" value="重置"/>
            <input id="back" class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>