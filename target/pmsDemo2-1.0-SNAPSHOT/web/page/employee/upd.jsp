<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2022/1/14
  Time: 16:43
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
    <legend style="font-weight: bolder">修改</legend>
    <div class="layui-field-box">
        <form class="layui-form layui-form-pane" lay-filter="formA">
            <div class="layui-form-item">
                <label class="layui-form-label" style="">编号</label>
                <div class="layui-input-inline">
                    <input type="text" name="code" required lay-verify="required" readonly
                           placeholder="请输入编号" autocomplete="off" class="layui-input" style="color:cadetblue">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" required lay-verify="required"
                           placeholder="请输入姓名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="button" value="修改" class="layui-btn layui-btn-radius layui-btn-normal" lay-submit
                           lay-filter="upd">
                    <input type="button" value="关闭" class="layui-btn layui-btn-radius layui-btn-normal" onclick="layerClose()">
                </div>
            </div>
            <input type="hidden" name="action" value="upd">
        </form>
    </div>
</fieldset>
</div>
<script type="text/javascript">
    var code='<%=request.getParameter("code")%>';
    // layer.msg(code)
    function init(){
        ajax("/EmployeeServlet",{code:code,action:'get'},'json',function (data){//console.log(data)
            form.val('formA',data)
        })
    }
    init();


    formOnSubmit('upd','/EmployeeServlet','text', function (data) {
        console.log(data)
        if (data == 1) {
            layer.msg("修改成功",{
                time:1500,
                shade:0.3,
                shadeClose:true
            },layerClose)
        } else {
            layer.msg("修改失败",{
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
