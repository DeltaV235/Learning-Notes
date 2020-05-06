<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Index</title>
    <script src="${rc.contextPath}/JQuery/jquery-3.4.1.min.js"></script>
    <script src="${rc.contextPath}/layer/layer.js"></script>
    <script>
        $(function () {
            $("#btn3").click(function () {
                const array = [1, 3, 5];
                let jsonStr = JSON.stringify(array);
                $.ajax({
                    url: "${rc.contextPath}/test/array3.html",
                    method: "post",
                    data: jsonStr,
                    contentType: "application/json;charset=utf-8",
                    success: function (response) {
                        console.log(response)
                    },
                    error: function (response) {
                        console.log(response)
                    },
                    dataType: "text",
                    traditional: true
                })
            })
            $("#btn2").click(function () {
                $.ajax({
                    url: "${rc.contextPath}/test/array2.html",
                    method: "post",
                    data: {
                        array: [1, 3, 5]
                    },
                    success: function (response) {
                        console.log(response)
                    },
                    error: function (response) {
                        console.log(response)
                    },
                    dataType: "text",
                    traditional: true
                })
            })
            $("#btn1").click(function () {
                $.ajax({
                    url: "${rc.contextPath}/test/array1.html",
                    method: "post",
                    data: {
                        array: [1, 3, 5]
                    },
                    success: function (response) {
                        console.log(response)
                    },
                    error: function (response) {
                        console.log(response)
                    },
                    dataType: "text"
                })
            })
            $("#btn4").click(function () {
                layer.msg("layer msg")
            })
        })
    </script>
</head>
<body>
Hello
<#list Admins as admin>
    id = ${admin.id},
    ${admin.email}
    <br>
</#list>
<button id="btn1">send array to server</button>
<button id="btn2">send array to server2</button>
<button id="btn3">send array to server3</button>
<button id="btn4">layer test</button>
</body>
</html>