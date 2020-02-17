<%--
  Created by IntelliJ IDEA.
  User: DeltaV
  Date: 2020/2/16
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%--<!doctype html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <script>
        window.onload = function () {
            let checkImg = document.getElementById("checkImg");
            checkImg.onclick = function () {
                let date = new Date();
                checkImg.src += "?date" + date;
            }
        }
    </script>
</head>
<body>

<div class="container-fluid">
    <div class="col-md-3">
        <div class="row">
            <form action="/session/LoginServlet" method="post">
                <div class="form-group">
                    <label for="username">用户名</label>
                    <input type="text" class="form-control" id="username" name="username">
                </div>
                <div class="form-group">
                    <label for="password">密码</label>
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <div class="form-group col-md-7">
                    <label for="checkCode">验证码</label>
                    <input type="text" class="form-control" id="checkCode" name="checkCode">
                </div>
                <div class="img-fluid">
                    <img src="CheckCodeServlet" alt="checkCode" id="checkImg">
                </div>
                <button type="submit" class="btn btn-primary">登录</button>
            </form>
            <%
                Object isSuccess = request.getAttribute("isSuccess");
                if (isSuccess != null) {
                    if (!(boolean) isSuccess) {
                        out.write("用户名或密码错误");
                    }
                }
                Object checkCode = request.getAttribute("checkCode");
                if (checkCode != null) {
                    if (!(boolean) checkCode) {
                        out.write("验证码错误");
                    }
                }

            %>

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.4.1/dist/jquery.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
