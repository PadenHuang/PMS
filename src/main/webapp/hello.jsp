<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2022/12/1/0001
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
      hello world!

      <%--提交表单给Servlet——user_login——使用绝对路径--%>
     <%-- <form action="${pageContext.request.contextPath}/HelloServlet" method="post">
          <div class="register-top-grid">
              <h3>用户登录</h3>
              <div class="input">
                  <span>用户名/邮箱 <label style="color:red;">*</label></span>
                  <input type="text" name="ue" placeholder="请输入用户名" required="required">
              </div>
              <div class="input">
                  <span>密码 <label style="color:red;">*</label></span>
                  <input type="password" name="password" placeholder="请输入密码" required="required">
              </div>

              <div class="clearfix"> </div>
          </div>
          <div class="register-but text-center">
              <input type="submit" value="提交">
              <div class="clearfix"> </div>
          </div>
      </form>--%>

      <a href="${pageContext.request.contextPath}/HelloServlet">
          <button>跳转</button>
      </a>

</body>
</html>
