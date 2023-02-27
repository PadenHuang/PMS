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
        <h2 class="layui-colla-title">绩效信息-查询条件</h2>
        <div class="layui-colla-content layui-show">
            <fieldset class="layui-elem-field layui-field-title" style="margin: 10px">
                <legend style="font-weight: bolder">绩效信息-查询条件</legend>
                <div class="layui-field-box">
                    <form class="layui-form layui-form-pane" lay-filter="formA">
                        <div class="layui-form-item">
                            <label class="layui-form-label">员工名称</label>
                            <div class="layui-input-inline">
                                <select name="codeEmp" lay-search></select>
                            </div>
                            <label class="layui-form-label">项目名称</label>
                            <div class="layui-input-inline">
                                <select name="codePro" lay-search></select>
                            </div>
                            <span>
                            <input type="button" value="查询" class="layui-btn layui-btn-radius layui-btn-normal"
                                   lay-submit lay-filter="search">
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
        <th style="width: 8%">员工编号</th>
        <th style="width: 8%">员工姓名</th>
        <th style="width: 8%">项目编号</th>
        <th style="width: 8%">项目名称</th>
        <th style="width: 8%">绩效</th>
        <th style="width: 12%">操作</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>
<div id="pageInfo" style="text-align: right;padding-right: 30px"></div>
<script type="text/javascript">
    initSel();
    initSel2();
    function initSel() {//从数据库初始化下拉框数据
        ajax('/EmployeeServlet', {action: 'list'}, 'json', function (data) {
            var opt = "<option value=''>请选择员工</option>"
            $.each(data, function () {
                opt += "<option value='" + this.code + "'>" + this.name + "</option>"
            })
            $("select[name='codeEmp']").html(opt);
            form.render()
        })
    }

    function initSel2() {//从数据库初始化下拉框数据
        ajax('/ProjectServlet', {action: 'list'}, 'json', function (data) {
            var opt = "<option value=''>请选择项目</option>"
            $.each(data, function () {
                opt += "<option value='" + this.code + "'>" + this.name + "</option>"
            })
            $("select[name='codePro']").html(opt);
            form.render()
        })
    }

    formOnSubmit('search', '/ScoreServlet', 'json', function (data) {
        console.log(data)
        //分页空间的渲染=表格中全部数据的条数
        var curr = $("input[name='pageIndex']").val();//取出表单中隐藏域的数值
        var limit = $("input[name='pageLimit']").val();
        pageInfo('pageInfo', data.count, curr, limit, function (obj, first) {
            $("input[name='pageIndex']").val(obj.curr);
            $("input[name='pageLimit']").val(obj.limit);
            if (!first) {
                refresh();
            }//首次不执行
        });
        //展示当前页面的数据=表格中的当前页面的数据
        var html = "";
        var tpl = $("#tradd").html();
        $.each(data.list, function (i, dom) {
            dom.i = i + 1 + (curr - 1) * limit;
            html += laytpl(tpl).render(dom);
        })
        $("tbody").html(html)
    })
    refresh();

    function openAdd() {
        layerOpen('/web/page/score/add.jsp', refresh);
    }

    function openUpd(codeEmp, codePro) {
        layerOpen('/web/page/score/upd.jsp?codeEmp=' + codeEmp + '&codePro=' + codePro, refresh);
    }

    function del(codeEmp, codePro) {
        layerConfirm(function () {
            ajax("/ScoreServlet", {codeEmp: codeEmp, codePro: codePro, action: 'del'}, 'text', function (data) {
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
        <td>{{d.codeEmp}}</td>
        <td>{{d.empName}}</td>
        <td>{{d.codePro}}</td>
        <td>{{d.proName}}</td>
        <td>{{d.score}}</td>
        <td>
            <a href="javascript:del('{{d.codeEmp}}','{{d.codePro}}')"
               class="layui-btn layui-btn-radius layui-btn-danger">删除
                <i class="layui-icon layui-icon-delete"></i>
            </a>
            <a href="javascript:openUpd('{{d.codeEmp}}','{{d.codePro}}')"
               class="layui-btn layui-btn-radius layui-btn-normal">修改
                <i class="layui-icon layui-icon-edit"></i>
            </a>
        </td>
    </tr>
</script>
</body>
</html>
