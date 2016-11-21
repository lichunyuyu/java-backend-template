<%--
  Created by IntelliJ IDEA.
  User: mark
  Date: 16/11/7
  Time: 下午1:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <meta name="author" content="">
    <!-- Stylesheets -->
    <link href="res/bootstrap-template/html/outresource-css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="res/bootstrap-template/html/outresource-css/dataTables.bootstrap.min.css" rel="stylesheet"
          media="screen">
    <link href="res/bootstrap-template/html/outresource-css/jquery.dataTables.min.css" rel="stylesheet"
          media="screen">
    <!-- Font awesome icon -->
    <link rel="stylesheet" href="res/bootstrap-template/html/style/font-awesome.css">
    <!-- jQuery UI -->
    <link rel="stylesheet" href="res/bootstrap-template/html/style/jquery-ui.css">
    <!-- Calendar -->
    <link rel="stylesheet" href="res/bootstrap-template/html/style/fullcalendar.css">
    <!-- prettyPhoto -->
    <link rel="stylesheet" href="res/bootstrap-template/html/style/prettyPhoto.css">
    <!-- Star rating -->
    <link rel="stylesheet" href="res/bootstrap-template/html/style/rateit.css">
    <!-- Date picker -->
    <link rel="stylesheet" href="res/bootstrap-template/html/style/bootstrap-datetimepicker.min.css">
    <!-- CLEditor -->
    <link rel="stylesheet" href="res/bootstrap-template/html/style/jquery.cleditor.css">
    <!-- Uniform -->
    <link rel="stylesheet" href="res/bootstrap-template/html/style/uniform.default.css">
    <!-- Bootstrap toggle -->
    <link rel="stylesheet" href="res/bootstrap-template/html/style/bootstrap-switch.css">
    <!-- Main stylesheet -->
    <link href="res/bootstrap-template/html/style/style.css" rel="stylesheet">
    <!-- Widgets stylesheet -->
    <link href="res/bootstrap-template/html/style/widgets.css" rel="stylesheet">
    <!-- HTML5 Support for IE -->
    <script src="res/bootstrap-template/html/js/html5shim.js"></script>
    <script src="res/bootstrap-template/html/outresource-js/jquery.js"></script>
    <script src="res/bootstrap-template/html/outresource-js/bootstrap.min.js"></script>
    <script src="res/bootstrap-template/html/outresource-js/jquery.dataTables.min.js"></script>
    <script src="res/bootstrap-template/html/outresource-js/dataTables.bootstrap.min.js"></script>
 <%--   <script src="http://cdnjs.cloudflare.com/ajax/libs/store.js/1.3.14/store.min.js" type="text/javascript"></script>
    <script src="/res/bootstrap-template/html/change-coulmns/change-colums.js" type="text/javascript"></script>--%>
    <link rel="shortcut icon" href="res/bootstrap-template/html/img/favicon/favicon.png">
</head>
<body>
<header>
    <div class="container">
        <div class="row">
            <!-- Logo section -->
            <div class="col-md-4">
                <!-- Logo. -->
                <div class="logo">
                    <h1><a href="#">PHICOMM <span class="bold"></span></a></h1>
                    <p class="meta">产测系统</p>
                </div>
                <!-- Logo ends -->
            </div>
            <ul class="nav navbar-nav pull-right">
                <li class="dropdown pull-right">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="icon-user"></i> <sec:authentication property="name"/> 管理员 <b class="caret"></b>
                    </a>
                    <!-- Dropdown menu -->
                    <ul class="dropdown-menu">
                        <li><a href="j_spring_security_logout"><i class="icon-off"></i> 退出</a></li>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</header>
