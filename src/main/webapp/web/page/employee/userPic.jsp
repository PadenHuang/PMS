<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2022/1/17
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/web/header.jsp" %>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin: 15px">
    <legend>头像图片</legend>
    <div class="layui-field-box">
        <button type="button" class="layui-btn" id="test1">
            <i class="layui-icon">&#xe67c;</i>上传图片
        </button>
        <table class="layui-table">
            <thead><tr><td>文件名</td><td>图片</td><td>操作</td></tr></thead>
            <tbody></tbody>
        </table>
    </div>
</fieldset>



<script type="text/javascript">
    var code='<%=request.getParameter("code")%>';
    renderUpload("test1","/EmployeeUploadServlet",{code:code},function (res){console.log(res)
        if (res.code == 1){
              layer.msg("上传成功");
              showPic(res.image)
        }
    })
    function init() {
        ajax('/EmployeeServlet',{action:'get',code:code},'json',function (data){
            var image = data.image;
            if (image) showPic(image);
            else $("tbody").html();
        })
    }
    init();
    function showPic(image){
        var html="<tr><td>"+image
        +"</td><td><img src='/temp/"+image+"' class='layer-upload-img'>"
        +"</td><td><input type='button' onclick='delPic()' class='layui-btn layui-btn-radius layui-btn-danger' value='删除'>"
        +"</td></tr>"
        $("tbody").html(html);
    }
    function delPic() {
        layerConfirm(function () {
            ajax("/EmployeeServlet", {code: code, action: 'delPic'}, 'text', function (data) {
                console.log(data)
                if (data == 1) {
                    layer.msg("删除成功", {
                        time: 1500,
                        shade: 0.3,
                        shadeClose: true
                    });
                    $("tbody").html();
                }
            })
        })
    }
</script>
</body>
</html>
