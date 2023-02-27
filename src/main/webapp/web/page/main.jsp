<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2022/1/13
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
pageEncoding="UTF-8" %>
<html>
<head>
    <title>主界面</title>
    <%@include file="/web/header.jsp" %>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo layui-hide-xs layui-bg-black">绩效信息管理系统</div>
        <!-- 头部区域（可配合layui 已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <!-- 移动端显示 -->
            <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-header-event="menuLeft">
                <i class="layui-icon layui-icon-spread-left"></i>
            </li>

            <li class="layui-nav-item layui-hide-xs"><a href="javascript:openUrl('/web/page/department/list.jsp');">部门信息</a></li>
            <li class="layui-nav-item layui-hide-xs"><a href="javascript:openUrl('/web/page/employee/list.jsp');">员工信息</a></li>
            <li class="layui-nav-item layui-hide-xs"><a href="javascript:openUrl('/web/page/project/list.jsp');">项目信息</a></li>
            <li class="layui-nav-item layui-hide-xs"><a href="javascript:openUrl('/web/page/score/list.jsp');">绩效信息</a></li>

        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item layui-hide layui-show-md-inline-block">
                <a href="javascript:;">
                    <img src="//tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" class="layui-nav-img">
                    用户：${user.name}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">修改信息</a></dd>
                    <dd><a href="javascript:toLogout()">退出登录</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item" lay-header-event="menuRight" lay-unselect>
                <a href="javascript:openRight();">
                    <i class="layui-icon layui-icon-more-vertical"></i>
                </a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">信息维护</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;"data-url="/web/page/department/list.jsp" class="site-demo-active">部门信息维护</a></dd>
                        <dd><a href="javascript:;"data-url="/web/page/employee/list.jsp" class="site-demo-active">员工信息维护</a></dd>
                        <dd><a href="javascript:;"data-url="/web/page/project/list.jsp"class="site-demo-active">项目信息维护</a></dd>
                        <dd><a href="javascript:;"data-url="/web/page/score/list.jsp"class="site-demo-active">绩效信息维护</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">基础配置</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:openUrl('/web/page/department/list.jsp');">list 1</a></dd>
                        <dd><a href="javascript:openUrl('/web/page/employee/list.jsp');">list 2</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <iframe name ='frameA' width="99%" height="97%"></iframe>
    </div>

    <div class="layui-footer">

    </div>
</div>
<script type="text/javascript">
    $('.site-demo-active').click(function (){
        window.open(base.app + $(this).data("url"),"frameA")
    })
    $('.layui-nav-tree .site-demo-active:first').addClass('layui-this') .click()
    function openUrl(url) {
        window.open(base.app + url,'frameA')
    }
    function toLogout(){
        layerConfirm(function (){
            toHref('/EmployeeServlet?action=logout')
        },"是否退出?")
    }
    function openRight() {
        layer.open({
            type: 1,
            content: '<div style="padding: 15px;">处理右侧面版的操作</div>',
            area: ['300px','100%'],
            offset: 'rt',//右上角
            anim: 5,
            shadeClose: true
        })
    }
</script>
</body>
</html>
