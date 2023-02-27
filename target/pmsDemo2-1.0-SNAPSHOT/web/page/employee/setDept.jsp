<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2022/1/17
  Time: 16:15
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
    <legend style="font-weight: bolder">设置部门</legend>
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
                <label class="layui-form-label">部门名称</label>
                <div class="layui-input-inline">
                    <select name="codeDept" lay-search></select>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="button" value="确定" class="layui-btn layui-btn-radius layui-btn-normal" lay-submit
                           lay-filter="setDept">
                    <input type="button" value="关闭" class="layui-btn layui-btn-radius layui-btn-normal" onclick="layerClose()">
                </div>
            </div>
            <input type="hidden" name="action" value="setDept">
        </form>
    </div>
</fieldset>
</div>
<script type="text/javascript">
    function initSel() {//从数据库初始化下拉框数据
        ajax('/DepartmentServlet',{action:'list'},'json',function (data){
            var opt="<option value=''>请选择部门</option>"
            $.each(data,function (){
                opt+="<option value='" + this.code + "'>" + this.name + "</option>"
            })
            $("select[name='codeDept']").html(opt);
            form.render()
            init()
        })
    }
    initSel();

    var code='<%=request.getParameter("code")%>';
    function init(){
        ajax("/EmployeeServlet",{code:code,action:'get'},'json',function (data){console.log(data)
            form.val('formA',data)
        })
    }

    formOnSubmit('setDept','/EmployeeServlet','text', function (data) {
        console.log(data)
        if (data == 1) {
            layer.msg("设置成功",{
                time:1500,
                shade:0.3,
                shadeClose:true
            },layerClose)
        } else {
            layer.msg("设置失败",{
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
