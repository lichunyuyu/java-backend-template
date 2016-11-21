<%--
  Created by IntelliJ IDEA.
  User: mark
  Date: 16/11/2
  Time: 下午2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>403页面</title>
</head>
<body>
对不起您的权限不足，<a href="<%=basePath%>login.jsp">请重新登录</a>

</body>
</html>
