<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2022/1/13
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
pageEncoding="UTF-8" %>
<html>
<head>
    <title>Login In</title>
    <%@include file="/web/header.jsp" %>
    <style>
        body {
            background: url(${pageContext.request.contextPath}/web/base/img/Light_2.jpg) no-repeat center center fixed;
            -webkit-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
        }
    </style>
</head>
<body>
<%--http://127.0.0.1:8080/demo220111_war_exploded/web/login.jsp--%>
<div class="window">
    <fieldset class="layui-elem-field" style="margin: 10px">
        <legend style="font-weight: bolder">登录</legend>
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
                    <div class="layui-input-inline">
                        <input type="button" value="登录" class="layui-btn layui-btn-radius layui-btn-normal" lay-submit
                               lay-filter="login" onclick="toHref('/web/page/main.jsp')"><%--无法提交给form--%>
                        <input type="reset" value="重置" class="layui-btn layui-btn-radius layui-btn-warm">
                    </div>
                    <input type="button" value="注册" class="layui-btn layui-btn-radius layui-btn-normal" onclick="toHref('/web/reg.jsp')">
                </div>
                <input type="hidden" name="action" value="login">
            </form>
        </div>
    </fieldset>
</div>

<script type="text/javascript">
    formOnSubmit('login','${pageContext.request.contextPath}/EmployeeServlet','text', function (data) {
        console.log(data)
        if (data == 1) {
            layer.msg("登录成功",{
                time:1500,
                shade:0.3,
                shadeClose:true
            },function (){
                toHref("/web/page/main.jsp");
            })

        } else if (data == 0) {
            layer.msg("账号不存在",{
                time:1500,
                anim:6,
                shade:0.3,
                shadeClose: true
            })
        } else {
            layer.msg("登录失败，密码错误",{
                time:1500,
                anim:6,
                shade:0.3,
                shadeClose: true
            })
        }

    })
    // function toHref(){
    //     location.href=base.app+"/web/reg.jsp";
    // }
</script>

</body>
</html>
