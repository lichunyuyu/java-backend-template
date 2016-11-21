<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<body>
<center>
    欢迎<sec:authentication property="name"/> 超级管理员光临
    <br>
    <a href="/j_spring_security_logout">退出</a>
</center>
</body>
</html>