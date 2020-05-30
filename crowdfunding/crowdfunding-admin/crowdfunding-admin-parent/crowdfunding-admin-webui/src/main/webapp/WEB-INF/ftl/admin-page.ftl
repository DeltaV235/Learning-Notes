<!DOCTYPE html>
<html>
<#include "include-admin-head.ftl">
<link href="css/pagination.css" rel="stylesheet"/>
<script src="jquery/jquery.pagination.js"></script>
<script>
    $(function () {
        initPagination()
    })

    function initPagination() {
        var totalRecord = ${(Request.pageInfo.total)!0};
        var properties = {
            num_edge_entries: 3,
            num_display_entries: 5,
            callback: pageSelectCallback,
            items_per_page: ${Request.pageInfo.pageSize},
            current_page: ${Request.pageInfo.pageNum - 1},
            prev_text: "上一页",
            next_text: "下一页"
        };
        $("#Pagination").pagination(totalRecord, properties)
    }

    function pageSelectCallback(pageIndex, jq) {
        let pageNum = pageIndex + 1;
        location.href = "admin/get/page.html?pageNum=" + pageNum
            <#if RequestParameters.keyword??>
            <#if RequestParameters.keyword != "">
            + "&keyword=" + ${RequestParameters.keyword};
        </#if>
        </#if>
        return false;
    }
</script>
<body>

<#include "include-admin-nav.ftl">

<div class="container-fluid">
    <div class="row">

        <#include "include-admin-sidebar.ftl">

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;" action="admin/get/page.html"
                          method="post">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success" type="text" placeholder="请输入查询条件"
                                       name="keyword">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                                class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;"
                            onclick="window.location.href='admin/to/add/page.html'"><i class="glyphicon
                            glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if (Request.pageInfo.list)??>
                                <#list Request.pageInfo.list as admin>
                                    <tr>
                                        <td>${admin?counter}</td>
                                        <td><input type="checkbox"></td>
                                        <td>${admin.loginAcct!"null"}</td>
                                        <td>${admin.userName!"null"}</td>
                                        <td>${admin.email!"null"}</td>
                                        <td>
                                            <button type="button" class="btn btn-success btn-xs"><i
                                                        class=" glyphicon glyphicon-check"></i></button>
                                            <button type="button" class="btn btn-primary btn-xs"><i
                                                        class=" glyphicon glyphicon-pencil"></i></button>
                                            <#--                                            <button type="button" class="btn btn-danger btn-xs"><i-->
                                            <#--                                                        class=" glyphicon glyphicon-remove"></i></button>-->
                                            <a href="admin/remove/${admin.id}/${Request.pageInfo
                                            .pageNum}/${RequestParameters.keyword!""}.html"
                                               type="button"
                                               class="btn
                                            btn-danger
                                            btn-xs"><i
                                                        class=" glyphicon glyphicon-remove"></i></a>
                                        </td>
                                    </tr>
                                </#list>
                            <#else>
                                <tr>
                                    <td colspan="6">
                                        没有您查找的数据
                                    </td>
                                </tr>
                            </#if>

                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>

                                    <#--                                    <ul class="pagination">-->
                                    <#--                                        <li class="disabled"><a href="#">上一页</a></li>-->
                                    <#--                                        <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>-->
                                    <#--                                        <li><a href="#">2</a></li>-->
                                    <#--                                        <li><a href="#">3</a></li>-->
                                    <#--                                        <li><a href="#">4</a></li>-->
                                    <#--                                        <li><a href="#">5</a></li>-->
                                    <#--                                        <li><a href="#">下一页</a></li>-->
                                    <#--                                    </ul>-->
                                </td>
                            </tr>

                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
