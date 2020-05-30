<!DOCTYPE html>
<html>
<#include "include-admin-head.ftl">
<body>

<#include "include-admin-nav.ftl">

<div class="container-fluid">
    <div class="row">

        <#include "include-admin-sidebar.ftl">

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="admin/to/main/page.html">首页</a></li>
                <li><a href="admin/get/page.html">数据列表</a></li>
                <li class="active">新增</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                                class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <form role="form" action="admin/save.html" method="post">
                        <p style="color: red">${(Request.exception.message)!""}</p>
                        <div class="form-group">
                            <label for="exampleInputPassword1">登陆账号</label>
                            <input type="text" class="form-control" id="exampleInputPassword1" placeholder="请输入登陆账号"
                                   value="${(RequestParameters.loginAcct)!""}"
                                   name="loginAcct">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">登录密码</label>
                            <input type="text" class="form-control" id="exampleInputPassword1" placeholder="请输入用户密码"
                                   value="${(RequestParameters.userPswd)!""}"
                                   name="userPswd">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">用户昵称</label>
                            <input type="text" class="form-control" id="exampleInputPassword1" placeholder="请输入用户昵称"
                                   value="${(RequestParameters.userName)!""}"
                                   name="userName">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">邮箱地址</label>
                            <input type="email" class="form-control" id="exampleInputEmail1" placeholder="请输入邮箱地址"
                                   value="${(RequestParameters.email)!""}"
                                   name="email">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增
                        </button>
                        <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
