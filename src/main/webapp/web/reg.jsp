<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2022/1/12
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
pageEncoding="UTF-8" %>
<html>
<head>
    <title>Sign In</title>
    <%@include file="/web/header.jsp" %>
    <style>
        body {
            background: url(${pageContext.request.contextPath}/web/base/img/TiTan_2.jpg) no-repeat center center fixed;
            -webkit-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
        }
    </style>
</head>
<body>
<%--http://127.0.0.1:8080/demo220111_war_exploded/web/reg.jsp--%>
<div class="window">
    <fieldset class="layui-elem-field" style="margin: 10px">
        <legend style="font-weight: bolder">注册</legend>
        <div class="layui-field-box">
            <form class="layui-form layui-form-pane">
                <div class="layui-form-item">
                    <label class="layui-form-label">账号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="code" required lay-verify="required"
                               placeholder="请输入账号" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">密码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="pass" required lay-verify="required"
                               placeholder="请输入密码" autocomplete="off" class="layui-input">
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
                    <div class="layui-input-inline">
                        <input type="button" value="注册" class="layui-btn layui-btn-radius layui-btn-normal" lay-submit="reg"
                               lay-filter="reg">
                        <input type="reset" value="重置" class="layui-btn layui-btn-radius layui-btn-warm">
                    </div>
                    <input type="button" value="返回登录" class="layui-btn layui-btn-radius layui-btn-normal" onclick="toHref('/web/login.jsp')">
                </div>
                <input type="hidden" name="action" value="reg">
            </form>
        </div>
    </fieldset>
</div>
<script type="text/javascript">
    var form = layui.form;
    var $ = layui.jquery;
    var layer = layui.layer;
    form.on("submit(reg)", function (data) {
        // console.log(data.field)
        $.ajax({
            url: base.app + "/EmployeeServlet",
            data: data.field,//a=b&c=d / {a:b,c:d}
            type: "post",
            dataType: "json",//text / json
            success: function (data) {
                console.log(data)
                if (data == 1) {
                    layer.msg("注册成功")
                } else if (data == "repeat") {
                    layer.msg("注册失败，账号重复")
                } else {
                    layer.msg("注册失败")
                }
            }
        });
    })
    /*formOnSubmit('reg', '/EmployeeServlet','text', function (data) {
        console.log(data)
        if (data == 1) {
            layer.msg("注册成功",{
                time:1500,
                shade:0.3,
                shadeClose:true
            },function (){
                toHref("/web/login.jsp")
            })
        } else if (data == "0") {
            layer.msg("注册失败，账号重复",{
                time:1500,
                anim:6,
                shade:0.3,
                shadeClose:true
            })
        } else {
            layer.msg("注册失败",{
                time:1500,
                anim:6,
                shade:0.3,
                shadeClose:true
            })
        }

    })*/
    // function toHref(){
    //     location.href=base.app+"/web/login.jsp";
    // }
</script>
</body>
</html>
