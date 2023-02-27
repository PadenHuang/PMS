<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2022/1/13
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/web/header.jsp" %>
</head>
<body>
<div class="layui-collapse">
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">项目信息-查询条件</h2>
        <div class="layui-colla-content layui-show">
            <fieldset class="layui-elem-field layui-field-title" style="margin: 10px">
                <legend style="font-weight: bolder">项目信息-查询条件</legend>
                <div class="layui-field-box">
                    <form class="layui-form layui-form-pane">
                        <div class="layui-form-item">
                            <label class="layui-form-label">项目编号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="code" placeholder="请输入编号" autocomplete="off"
                                       class="layui-input">
                            </div>
                            <label class="layui-form-label">项目名称</label>
                            <div class="layui-input-inline">
                                <input type="text" name="name" placeholder="请输入名称" autocomplete="off"
                                       class="layui-input">
                            </div>
                            <span>
                            <input type="button" value="查询" class="layui-btn layui-btn-radius layui-btn-normal"
                                   lay-submit
                                   lay-filter="search">
                            <input type="reset" value="重置" class="layui-btn layui-btn-radius layui-btn-warm">
                            <input type="button" value="添加" class="layui-btn layui-btn-radius layui-btn-normal"
                                   onclick="openAdd()">
                                <input type="hidden" name="action" value="page">
                                <input type="hidden" name="pageIndex" value="1">
                                <input type="hidden" name="pageLimit" value="10">
                         </span>
                        </div>
                    </form>
                </div>
            </fieldset>
        </div>
    </div>
</div>
<table class="layui-table">

    <thead>
    <tr>
        <th style="width: 8%">序号</th>
        <th style="width: 8%">项目编号</th>
        <th style="width: 8%">项目名称</th>
        <th style="width: 8%">时间</th>
        <th style="width: 12%">操作</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>
<div id="pageInfo" style="text-align: right;padding-right: 30px"></div>
<script type="text/javascript">
    formOnSubmit('search', '/ProjectServlet', 'json', function (data) {// console.log(data)
        //分页空间的渲染=表格中全部数据的条数
        var curr = $("input[name='pageIndex']").val();//取出表单中隐藏域的数值
        var limit = $("input[name='pageLimit']").val();
        pageInfo('pageInfo',data.count,curr,limit,function (obj,first){
            $("input[name='pageIndex']").val(obj.curr);
            $("input[name='pageLimit']").val(obj.limit);
            if (!first){refresh();}//首次不执行
        });
        //展示当前页面的数据=表格中的当前页面的数据
        var html = "";
        var tpl = $("#tradd").html();
        $.each(data.list, function (i, dom) {
            var d = {id: (i+1+(curr-1)*limit), code: dom.code, name: dom.name, time1: dom.time1}
            html += laytpl(tpl).render(d);
        })
        $("tbody").html(html)
    })
    refresh();
    function openAdd() {
        layerOpen('/web/page/project/add.jsp', refresh);
    }

    function openUpd(code) {
        layerOpen('/web/page/project/upd.jsp?code=' + code, refresh);
    }

    function del(code) {
        layerConfirm(function () {
            ajax("/ProjectServlet", {code: code, action: 'del'}, 'text', function (data) {
                console.log(data)
                if (data == 1) {
                    layer.msg("删除成功", {
                        time: 1500,
                        shade: 0.3,
                        shadeClose: true
                    }, refresh())
                } else {
                    layer.msg("删除失败", {
                        time: 1500,
                        anim: 6,
                        shade: 0.3,
                        shadeClose: true
                    })
                }
            })
        })
    }
</script>
<script type="text/html" id="tradd">
    <tr>
        <td>{{d.id}}</td>
        <td>{{d.code}}</td>
        <td>{{d.name}}</td>
        <td>{{d.time1}}</td>
        <td>
            <a href="javascript:del('{{d.code}}')" class="layui-btn layui-btn-radius layui-btn-danger">删除
                <i class="layui-icon layui-icon-delete"></i>
            </a>
            <a href="javascript:openUpd('{{d.code}}')" class="layui-btn layui-btn-radius layui-btn-normal">修改
                <i class="layui-icon layui-icon-edit"></i>
            </a>
        </td>
    </tr>
</script>
</body>
</html>
