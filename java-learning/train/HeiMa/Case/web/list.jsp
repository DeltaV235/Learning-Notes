<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <%--    <script src="js/update-delete-user.js"></script>--%>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>

    <script>
        function deleteUser(id) {
            if (confirm("是否删除该用户记录？"))
                location.href = "${pageContext.request.contextPath}/UserDeleteServlet?id=" + id;
        }

        window.onload = function () {
            document.getElementById("delSelectedBtn").onclick = function () {
                if (confirm("是否删除选中的用户？")) {
                    let flag = false;
                    let ids = document.getElementsByName("id");
                    for (let i = 0; i < ids.length; i++) {
                        if (ids[i].checked) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag)
                        document.getElementById("form").submit();
                }
            };

            document.getElementById("firstCb").onclick = function () {
                let ids = document.getElementsByName("id");
                for (let i = 0; i < ids.length; i++) {
                    ids[i].checked = this.checked;
                }
            }
        };

    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <%--    查询框--%>
    <div style="float: left; margin: 10px;">
        <form class="form-inline">
            <div class="form-group">
                <label for="name">姓名</label>
                <input type="text" class="form-control" id="name" name="name">
            </div>
            <div class="form-group">
                <label for="address">籍贯</label>
                <input type="text" class="form-control" id="address" name="address">
            </div>
            <div class="form-group">
                <label for="email">邮箱</label>
                <input type="email" class="form-control" id="email" name="email">
            </div>
            <button type="submit" class="btn btn-default">查新</button>
        </form>
    </div>

    <div style="margin: 10px; float: right">
        <td colspan="8" align="center"><a class="btn btn-primary" href="add.jsp">添加联系人</a></td>
        <td colspan="8"
            align="center"><a class="btn btn-primary" href="javascript:void(0);" id="delSelectedBtn">删除选中</a></td>
    </div>

    <form action="${pageContext.request.contextPath}/DelSelectedServlet" method="post" id="form">

        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="firstCb"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${requestScope.users}" var="user" varStatus="status">
                <tr>
                    <td><input value="${user.id}" name="id" type="checkbox"></td>
                    <td>${status.count}</td>
                    <td>${user.name}</td>
                    <td>${user.gender}</td>
                    <td>${user.age}</td>
                    <td>${user.address}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                    <td><a class="btn btn-default btn-sm update-del"
                           href="${pageContext.request.contextPath}/UserUpdateServlet?id=${user.id}">修改</a>
                        <a class="btn btn-default btn-sm update-del"
                           href="javascript:deleteUser(${user.id})">删除</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>

    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li class="disabled">
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li class="active"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li>
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 25px;margin-left: 10px">
                    共16条记录，共4页
                </span>
            </ul>
        </nav>
    </div>

</div>
</body>
</html>
