<%--
  Created by IntelliJ IDEA.
  User: mark
  Date: 16/11/8
  Time: 下午4:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div class="mainbar">
    <div class="matter">
        <div class="container">

            <div class="row">

                <div class="col-md-12">


                    <div class="widget wgreen">

                        <div class="widget-head">
                            <div class="pull-left">单条MAC录入</div>
                            <div class="widget-icons pull-right">
                                <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                <a href="#" class="wclose"><i class="icon-remove"></i></a>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="widget-content">
                            <div class="padd">
                                <!-- Form starts.  -->
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <label class="col-lg-4 control-label">MAC地址</label>
                                        <div class="col-lg-8">
                                            <input type="text" class="form-control" id="macAddr" name="macAddr"
                                                   placeholder="请输入需要录入的MAC地址">
                                        </div>

                                        <div style="display:none;" class="alert alert-danger col-sm-2">
                                            <a class="close" data-dismiss="alert">x</a>
                                            MAC地址格式不正确
                                        </div>

                                        <div style="display:none;" class="alert alert-warning col-sm-2">
                                            <a class="close" data-dismiss="alert">x</a>
                                            对不起，您的录入操作失败，请重新录入！
                                        </div>
                                    </div>
                                    <hr/>
                                    <div class="form-group" align="center">
                                        <div class="col-lg-offset-1 col-lg-8">
                                            <button id="addsinglemac" disabled="true" class="btn btn-success">
                                                录入
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="widget-foot">
                            <!-- Footer goes here -->
                        </div>
                    </div>

                </div>

            </div>

        </div>
    </div>
    <!-- Matter ends -->
</div>
<script>
    $(function () {
        $("#macAddr").blur(function () {
            //mac地址正则表达式
            var temp = /^[0-9a-fA-F]{2}(:[0-9a-fA-F]{2}){5}$/
            if (!temp.test($(this).val())) {
                $('#addsinglemac').attr('disabled', "true");
                $('.alert-danger').show();
            } else {
                $('#addsinglemac').removeAttr("disabled");
                $('.alert-danger').hide();
            }
        });

        $('#addsinglemac').click(function () {
            $('#addsinglemac').attr('disabled', "true");
            $.ajax({
                type: "post",
                url: "<%=basePath%>admin/loadSingleMac",
                data: {"macAddr": $('#macAddr').val()},
                dataType:"json",
                success: function (data) {
                    if (data.result === "success") {
                        window.location.href = "<%=basePath%>admin/allstatusmacs";
                    } else {
                        $('.alert-warning').html(data.msg);
                        $('.alert-warning').show();
                    }
                }
            });
        })
    })
</script>