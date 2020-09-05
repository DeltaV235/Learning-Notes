<%--
  Created by IntelliJ IDEA.
  User: DeltaV
  Date: 2020/3/29
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ajax</title>
    <% pageContext.setAttribute("ctp", request.getContextPath()); %>
    <script src="scripts/jquery-1.9.1.min.js"></script>
    <script>
        $(function () {
            $("a:first").click(function () {
                $.ajax(
                    {
                        url: "${ctp}/ajax"
                        ,
                        method: "get",
                        success: function (data) {
                            $("#emps").empty();
                            console.log(typeof data);
                            $.each(data, function () {
                                let empinfo = this.lastName + "\t" + this.gender + "\t" + this.email;
                                $("#emps").append(empinfo + "<br>")
                            });
                            console.log(data)
                        }
                    }
                )
            });

            $("#sendJson").click(function () {
                let jsonInfo = {lastName: "wuyue", gender: "0", email: "wuyue@gmail.com"};
                console.log(typeof jsonInfo);

                let jsonStringInfo = JSON.stringify(jsonInfo);

                $.ajax(
                    {
                        url: "${ctp}/reqEmp",
                        type: "POST",
                        data: jsonStringInfo,
                        success: function (data) {
                            console.log(typeof data);
                            console.log(data)
                        },
                        contentType:"application/json"
                    }
                )
            })
        })
    </script>
</head>
<body>
<a href="javascript:void(0);">获取员工信息</a>
<a id="sendJson" href="javascript:void(0);">发送Json请求</a>
<div id="emps">
</div>
<br>
</body>
</html>
