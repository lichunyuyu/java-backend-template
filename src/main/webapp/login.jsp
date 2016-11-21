<%--
  Created by IntelliJ IDEA.
  User: mark
  Date: 16/11/7
  Time: 下午12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <base href="<%=basePath%>">
    <!-- Title and other stuffs -->
    <title>PHICOMM 产测系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" coentent="">
    <!-- Stylesheets -->
    <link href="res/bootstrap-template/html/style/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="res/bootstrap-template/html/style/font-awesome.css">
    <link href="res/bootstrap-template/html/style/style.css" rel="stylesheet">
    <!-- HTML5 Support for IE -->
    <!--[if lt IE 9]>
    <script src="js/html5shim.js"></script>
    <![endif]-->
    <!-- Favicon -->
    <link rel="shortcut icon" href="img/favicon/favicon.png">
</head>

<body>

<!-- Form area -->
<div class="admin-form">
    <div class="container">

        <div class="row">
            <div class="col-md-12">
                <!-- Widget starts -->
                <div class="widget worange">
                    <!-- Widget head -->
                    <div class="widget-head">
                        <i class="icon-lock"></i> 管理员登录
                    </div>

                    <div class="widget-content">
                        <div class="padd">
                            <!-- Login form -->
                            <form class="form-horizontal" action="<%=basePath%>j_spring_security_check" method="post">
                                <!-- Email -->
                                <div class="form-group">
                                    <label class="control-label col-lg-3" for="username">登录名</label>
                                    <div class="col-lg-9">
                                        <input type="text" class="form-control" id="username" name="j_username"
                                               placeholder="请输入用户名">
                                    </div>
                                </div>
                                <!-- Password -->
                                <div class="form-group">
                                    <label class="control-label col-lg-3" for="inputPassword">密码</label>
                                    <div class="col-lg-9">
                                        <input type="password" class="form-control" id="inputPassword" name="j_password"
                                               placeholder="请输入登录密码">
                                    </div>
                                </div>
                                <!-- Remember me checkbox and sign in button -->
                                <!--<div class="form-group">
                                    <div class="col-lg-9 col-lg-offset-3">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox"> Remember me
                                            </label>
                                        </div>
                                    </div>
                                </div>-->
                                <div class="col-lg-9 col-lg-offset-2">
                                    <button type="submit" class="btn btn-danger" onclick="check()">登录</button>
                                    <button type="reset" class="btn btn-default">重置</button>
                                </div>
                                <br/>
                            </form>

                        </div>
                    </div>

                    <div class="widget-foot">
                       <%-- 没有注册? <a href="#">点击注册</a>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- JS -->
<script type="text/javascript">
    //检查是否输入用户名  否则不予提交
    function check() {
        var username = document.getElementById("username").value;
        if (username == null || "" == username) {
            alert("请输入用户名");
            return false;
        }
        return true;
    }
</script>
<script src="res/bootstrap-template/html/js/jquery.js"></script>
<script src="res/bootstrap-template/html/js/bootstrap.js"></script>
</body>
</html>
