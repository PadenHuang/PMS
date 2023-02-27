<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2022/1/12
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%String app = request.getContextPath();%>
<link rel="stylesheet" href="<%=app%>/web/base/css/base.css">
<link rel="stylesheet" href="<%=app%>/web/base/layui/css/layui.css">
<script type="text/javascript" src="<%=app%>/web/base/layui/layui.js">
</script>
<script>
    var base = {
        app: "${pageContext.request.contextPath}"
    }
</script>
<script type="text/javascript" src="<%=app%>/web/base/js/base.js"></script>