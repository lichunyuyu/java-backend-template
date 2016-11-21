<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div class="mainbar">
    <div class="matter">
        <div class="container">
            <div class="row">
                <div class="col-md-8">

                    <div class="widget">
                        <div class="widget-head">
                            <div class="pull-left">上传MAC Excel文件</div>
                            <div class="widget-icons pull-right">
                                <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                <a href="#" class="wclose"><i class="icon-remove"></i></a>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="widget-content">
                            <div class="padd">
                                <div id="uploadForm">
                                    <input type="file" class="form-control col-lg-8" placeholder="" name="file" multiple
                                           id="file">
                                    <br/><br>
                                    <button class="btn btn-success" id="mac-file-upload－button"><i
                                            class="icon-paper-clip"></i>上传
                                    </button>
                                    <h1></h1>
                                    <div style="display:none;" class="alert alert-warning col-sm-2">
                                        <a class="close" data-dismiss="alert">x</a>
                                        MAC文件格式不正确
                                    </div>
                                    <div style="display:none;" class="alert alert-danger col-sm-2">
                                        <a class="close" data-dismiss="alert">x</a>
                                    </div>
                                    <br/><br/>
                                    上传进度：
                                    <progress></progress>
                                    <br/>
                                    <p id="progress">0 bytes</p>
                                    <p id="info"></p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="widget">
                    </div>

                </div>

                <div class="col-md-4">
                    <div class="widget">
                        <div class="widget-head">
                            <div class="pull-left">模板文件</div>
                            <div class="widget-icons pull-right">
                                <a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
                                <a href="#" class="wclose"><i class="icon-remove"></i></a>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="widget-content">
                            <div class="padd">
                                <hr/>
                                <input id="filepath" style="display:none;" type="text"
                                       value="mac-template.xlsx"/>
                                <img style="max-height: 300px;max-width: 289px"
                                     src="<%=basePath%>res/mac-template-file/macfiletemplate.png"/>
                                <h1></h1>
                                <div class="buttons">
                                    <a id="mac-download" class="btn btn-info">下载 MAC Excle模板文件
                                    </a>
                                </div>


                            </div>
                            <div class="widget-foot">
                            </div>
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
        /**
         * 实现模板文件下载
         */
        $('#mac-download').click(function () {
            var requestUrl = '<%=basePath%>admin/downloadmacexecledemo?filePath=' + $('#filepath').val();
            $(this).attr("href", requestUrl);
        });

        $("#file").blur(function () {
            var fileVal = $(this).val();
            var extend = fileVal.substring(fileVal.lastIndexOf(".")+1);
            if (!(extend=="xls"||extend=="xlsx")) {
                $('#mac-file-upload－button').attr('disabled',"true");
                $('.alert-warning').show();
                $('.alert-danger').hide();
            }else{
                $('#mac-file-upload－button').removeAttr("disabled");
                $('.alert-warning').hide();
                $('.alert-danger').hide();
            }
        });

        /**
         * 实现无序mac文件上传
         * 使用html5 formData
         */
        $('#mac-file-upload－button').click(function () {
            $('#mac-file-upload－button').attr('disabled', "true");
            //var formData = new FormData($("#uploadForm")[0]);//用form 表单直接 构造formData 对象; 就不需要下面的append 方法来为表单进行赋值了。
            var formData = new FormData();
            formData.append('file', $('#file')[0].files[0]);
            $.ajax({
                type: "post",
                url: "<%=basePath%>admin/anylisuploadmacfile",
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
                xhr: function () { //获取ajaxSettings中的xhr对象，为它的upload属性绑定progress事件的处理函数
                    myXhr = $.ajaxSettings.xhr();
                    if (myXhr.upload) { //检查upload属性是否存在
                        //绑定progress事件的回调函数
                        myXhr.upload.addEventListener('progress', progressHandlingFunction, false);
                    }
                    return myXhr; //xhr对象返回给jQuery使用
                },
                success: function (data) {
                    if(data.result == "success") {
                        setTimeout(function () {
                           alert("上传成功");
                        },200);
                        window.location.href = "<%=basePath%>admin/allstatusmacs";
                    }else {
                        $('.alert-danger').html(data.msg);
                        $('.alert-danger').show();
                    }

                }
            });
        })
        /**
         *
         * @param e
         * 上传显示进度条
         */
        function progressHandlingFunction(e) {
            if (e.lengthComputable) {
                $('progress').attr({value: e.loaded, max: e.total}); //更新数据到进度条
                var percent = e.loaded / e.total * 100;
                $('#progress').html(e.loaded + "/" + e.total + " bytes. " + percent.toFixed(2) + "%");
            }
        }
    })

</script>

