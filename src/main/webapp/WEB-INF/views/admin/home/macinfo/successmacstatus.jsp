<%--
  Created by IntelliJ IDEA.
  User: mark
  Date: 16/11/3
  Time: 下午5:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div class="mainbar">

    <!-- Page heading -->
    <div class="page-head">
        <h2 class="pull-left"><i class="icon-home"></i>MAC-成功录入</h2>
        <div class="clearfix"></div>
    </div>

    <div class="row-fluid">
        <h3></h3>
        <table id="example" class="display table-striped table-bordered table-hover table-condensed" cellspacing="0"
               width="100%">
            <thead>
            <tr>
                <th>MAC地址</th>
                <th>DownLoadId</th>
                <th>DeviceId</th>
                <th>录入状态</th>
                <th>操作人</th>
                <th>录入日期</th>
            </tr>
            </thead>

        </table>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        //提示信息
        var lang = {
            "sProcessing": "处理中...",
            "sLengthMenu": "每页 _MENU_ 项",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
            "sInfoEmpty": "当前显示第 0 至 0 项，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": '搜索:',
            "sUrl": "",
            "sEmptyTable": "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页",
                "sJump": "跳转"
            },
            "oAria": {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        };

        //初始化表格
        var table = $("#example").dataTable({
            serverSide: true,  //启用服务器端分页
            //data:showdata,
            language: lang,  //提示信息
            //searchable: true,
            autoWidth: true,  //禁用自动调整列宽
            stripeClasses: ["odd", "even"],  //为奇偶行加上样式，兼容不支持CSS伪类的场合
            processing: true,  //隐藏加载提示,自行处理
            searching: true,  //隐藏原生搜索
            //orderMulti: true,  //启用多列排序
            order: [],// 取消默认排序查询,否则复选框一列会出现小箭头
            renderer: "bootstrap",  //渲染样式：Bootstrap和jquery-ui
            pagingType: "simple_numbers",  //分页样式：simple,simple_numbers,full,full_numbers
            /* columnDefs : [ {
             // 定义操作列,######以下是重点########
             "targets" : 4,//操作按钮目标列
             "data" : null,
             "render" : function(data, type,row) {
             var id = '"' + row.id + '"';
             var html = "<a href='javascript:void(0);' class='delete btn btn-default btn-xs' ><i class='fa fa-times'></i> 查看</a>"
             html += "<a href='javascript:void(0);' class='up btn btn-default btn-xs'><i class='fa fa-arrow-up'></i> 编辑</a>"
             html += "<a href='javascript:void(0);' onclick='deleteThisRowPapser("+ id + ")' class='down btn btn-default btn-xs'><i class='fa fa-arrow-down'></i> 删除</a>"
             return html;
             }
             } ],*/
            ajax: function (data, callback, settings) {
                //封装请求参数
                var param = {};
                param.limit = data.length;//页面显示记录条数，在页面显示每页显示多少项的时候
                param.start = data.start;//开始的记录序号
                param.page = (data.start / data.length) + 1;//当前页码
                param.searches = data.search.value;
                param.status = "success";
                //alert(param.searches);
                //param.orderColum = data.order[0].column;
                //console.log(.data);
                //param.order = data.order[0].dir;
                //console.log(param);
                //ajax请求数据
                $.ajax({
                    type: "GET",
                    url: "<%=basePath%>admin/macstatus",
                    cache: false,  //禁用缓存
                    data: param,  //传入组装的参数
                    dataType: "json",
                    success: function (result) {
                        console.log("进来了");
                        //console.log(result);
                        //setTimeout仅为测试延迟效果
                        setTimeout(function () {
                            //封装返回数据
                            var returnData = {};
                            returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                            returnData.recordsTotal = result.rows.pageInfo.totalQuantity;//返回数据全部记录
                            returnData.recordsFiltered = result.rows.pageInfo.totalQuantity;
                            //后台不实现过滤功能，每次查询均视作全部结果
                            returnData.data = result.rows.results;//返回的数据列表
                            //console.log(returnData);
                            //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                            //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                            callback(returnData);
                        }, 200);
                    }
                });
            },
            columns: [
                /* {"data": "enable"},
                 {"data": "password"},
                 {"data": "userName"}*/
                {"data": "macAddr"},
                {"data": "downLoadId"},
                {"data": "deviceId"},
                {"data": "statusStr"},
                {"data": "admin.userName"},
                {"data": "dateStr"}
            ]
        }).api();

        setInterval(function () {
            table.ajax.reload();
        }, 10000000);

        /*  $('input.form-control').on( 'keyup', function () {
         //alert($('input.form-control').val());
         //console.log($('input.form-control').text());
         table.search($(this).text()).draw();
         //salert("运行了");
         } );*/
        //此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象
    });
</script>