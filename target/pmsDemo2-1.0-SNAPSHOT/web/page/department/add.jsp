<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2022/1/14
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/web/header.jsp" %>
    <style>
        body {
            background: url(${pageContext.request.contextPath}/web/base/img/222_2.jpg) no-repeat center center fixed;
            -webkit-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
        }
    </style>
</head>
<body>
<div class="window">
<fieldset class="layui-elem-field" style="margin: 10px">
    <legend style="font-weight: bolder">部门添加</legend>
    <div class="layui-field-box">
        <form class="layui-form layui-form-pane">
            <div class="layui-form-item">
                <label class="layui-form-label" style="">编号</label>
                <div class="layui-input-inline">
                    <input type="text" name="code" required lay-verify="required"
                           placeholder="请输入编号" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" required lay-verify="required"
                           placeholder="请输入名称" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">电话</label>
                <div class="layui-input-inline">
                    <input type="text" name="tel" required lay-verify="required"
                           placeholder="请输入电话" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                    <input type="button" value="添加" class="layui-btn layui-btn-radius layui-btn-normal" lay-submit
                           lay-filter="add">
                    <input type="reset" value="重置" class="layui-btn layui-btn-radius layui-btn-warm">
                    <input type="button" value="关闭" class="layui-btn layui-btn-radius layui-btn-normal" onclick="layerClose()">
            </div>
            <input type="hidden" name="action" value="add">
        </form>
    </div>
</fieldset>
</div>
<script type="text/javascript">
    formOnSubmit('add','/DepartmentServlet','text', function (data) {
        console.log(data)
        if (data == 1) {
            layer.msg("添加成功",{
                time:1500,
                shade:0.3,
                shadeClose:true
            },layerClose)
        } else if (data == "0") {
            layer.msg("编号重复",{
                time:1500,
                anim:6,
                shade:0.3,
                shadeClose:true
            })
        } else {
            layer.msg("添加失败",{
                time:1500,
                anim:6,
                shade:0.3,
                shadeClose:true
            })
        }
    })
</script>
</body>
</html>
