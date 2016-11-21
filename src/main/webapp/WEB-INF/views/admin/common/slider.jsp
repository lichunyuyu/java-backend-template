<%--
  Created by IntelliJ IDEA.
  User: mark
  Date: 16/11/7
  Time: 下午1:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div class="content">

    <!-- Sidebar -->
    <div class="sidebar">
        <div class="sidebar-dropdown"><a href="#">导航</a></div>

        <!--- Sidebar navigation -->
        <!-- If the main navigation has sub navigation, then add the class "has_sub" to "li" of main navigation. -->
        <ul id="nav">
            <!-- Main menu with font awesome icon class="open"-->
            <li><a href="<%=basePath%>admin/index" class="open"><i class="icon-home"></i>首页</a>
            </li>
            <li><a href="<%=basePath%>admin/adminlist"><i class="icon-bar-chart"></i>管理员管理</a></li>
            <li class="has_sub open"><a><i class="icon-list-alt"></i>MAC录入<span class="pull-right"><i class="icon-chevron-right"></i></span></a>
                <ul>
                    <li><a href="admin/addsinglemac">单条录入</a></li>
                    <li><a href="<%=basePath%>admin/loadnoordermacs">多条录入</a></li>
                    <li><a href="<%=basePath%>admin/addserisemacs">连续录入</a></li>
                </ul>
            </li>
            <li class="has_sub"><a><i class="icon-file-alt"></i>MAC状态查询<span class="pull-right"><i class="icon-chevron-right"></i></span></a>
                <ul>
                    <li><a href="<%=basePath%>admin/allstatusmacs">总体录入情况</a></li>
                    <%--<li><a href="/admin/allinitstatusmacs">初始化</a></li>--%>
                    <li><a href="<%=basePath%>admin/alldeliveringstatusmacs">正在录入</a></li>
                    <li><a href="<%=basePath%>admin/allsuccessstatusmacs">录入成功</a></li>
                    <li><a href="<%=basePath%>admin/allfailedstatusmacs">录入失败</a></li>
                </ul>
            </li>
           <%-- <li class="has_sub"><a href="#"><i class="icon-file-alt"></i> 页面模块2  <span class="pull-right"><i class="icon-chevron-right"></i></span></a>
                <ul>
                    <li><a href="media.html">媒体</a></li>
                    <li><a href="statement.html">描述</a></li>
                    <li><a href="error.html">错误</a></li>
                    <li><a href="error-log.html">错误日志</a></li>
                    <li><a href="calendar.html">日历</a></li>
                    <li><a href="grid.html">网格</a></li>
                </ul>
            </li>

            <li><a href="tables.html"><i class="icon-table"></i>表格</a></li>
            <li><a href="forms.html"><i class="icon-tasks"></i>表单</a></li>
            <li><a href="ui.html"><i class="icon-magic"></i>UI图标</a></li>
            <li><a href="calendar.html"><i class="icon-calendar"></i>日历</a></li>--%>
        </ul>
    </div>

    <!-- Sidebar ends -->

    <!-- Main bar -->
