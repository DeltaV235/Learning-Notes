<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <base href="${rc.contextPath}/">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <script src="jquery/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/login.css">
    <script>
        $(function () {
            $("button").click(function () {
                history.back()
            })
        })
    </script>
    <style>

    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">
    <h2 class="form-signin-heading" style="text-align: center"><i class="glyphicon glyphicon-log-in"></i>
        抛异常啦
        <br>
        ${(Request.exception)!"null"}
    </h2>
    <button class="btn btn-lg btn-success btn-block" style="width: 180px;margin-left: 42%">返回上一页</button>
</div>
</body>
</html>