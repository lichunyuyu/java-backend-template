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
                            <div class="pull-left">连续录入</div>
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
                                        <label class="col-sm-4 control-label">MAC区间</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" id="startMacStr" name="startMacStr"
                                                   placeholder="请输入需要录入的起始MAC地址">
                                        </div>

                                        <label class="col-sm-4 control-label"><strong>--------- </strong></label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" id="endMacStr" name="endMacStr"
                                                   placeholder="请输入需要录入的结尾MAC地址">
                                        </div>

                                        <div style="display:none;" class="alert alert-danger col-sm-2">
                                            <a class="close" data-dismiss="alert">x</a>
                                            对不起，起始MAC地址格式不正确
                                        </div>
                                        <div style="display:none;" class="alert alert-warning col-sm-2">
                                            <a class="close" data-dismiss="alert">x</a>
                                            对不起，结尾MAC地址格式输入不正确
                                        </div>
                                        <div style="display:none;" class="alert alert-info col-sm-2">
                                            <a class="close" data-dismiss="alert">x</a>
                                        </div>
                                    </div>
                                    <hr/>
                                    <div class="form-group" align="center">
                                        <div class="col-lg-offset-1 col-lg-8">
                                            <button id="addserisemac" disabled="true"
                                                    class="btn btn-success">录入
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
        $("#startMacStr").blur(function () {
            //var temp = /[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}/;
            var temp = /^[0-9a-fA-F]{2}(:[0-9a-fA-F]{2}){5}$/
            if (!temp.test($(this).val())) {
                //console.log($(this).val());
                $('#addserisemac').attr('disabled', "true");
                $('.alert-danger').show();
            } else if ($("#endMacStr").val() == "") {
                $('.alert-danger').hide();
                $('#addserisemac').attr('disabled', "true");
                //alert('开启');
            } else {
                $('#addserisemac').removeAttr("disabled");
                $('.alert-danger').hide();
            }
        });

        $("#endMacStr").blur(function () {
            //var temp = /[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}/;
            var temp = /^[0-9a-fA-F]{2}(:[0-9a-fA-F]{2}){5}$/
            if (!temp.test($(this).val())) {
                console.log(($("#startMacStr").val()));
                //console.log($(this).val());
                $('#addserisemac').attr('disabled', "true");
                $('.alert-warning').show();
            } else if($("#startMacStr").val() == ""){
                $('.alert-warning').hide();
                $('#addserisemac').attr('disabled', "true");
                //alert('开启');
            } else {
                $('#addserisemac').removeAttr("disabled");
                $('.alert-warning').hide();
            }
        });

        //ajax提交区间录入表单
        $('#addserisemac').click(function () {
            $('#addserisemac').attr('disabled', "true");
            console.log($('#endMacStr').val());
            console.log($('#startMacStr').val());
            $.ajax({
                type: "post",
                url: "<%=basePath%>admin/loadserisemacs",
                data: {"endMacStr": $('#endMacStr').val(), "startMacStr": $('#startMacStr').val()},
                dataType: "json",
                success: function (data) {
                    if (data.result == "success") {
                        setTimeout(function () {
                            //alert('sd');
                            $('.alert-info').html(data.msg);
                            $('.alert-info').show();
                        }, 200);
                        window.location.href = "<%=basePath%>admin/allstatusmacs";
                    } else {
                        console.log(data);
                        $('.alert-info').html(data.msg);
                        $('.alert-info').show();
                    }
                }
            });
        })
    })
</script>