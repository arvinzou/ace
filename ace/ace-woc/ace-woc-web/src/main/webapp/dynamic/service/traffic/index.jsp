<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>通行记录</title>
    <style>
        .traffic-file {
            padding: 10px;
        }

        .fileList {
            list-style: none;
            padding: 0px;
            margin: 0px;
        }

        .fileList > li {
            position: relative;
            float: left;
            width: 100%;
            margin-bottom: 10px;
            text-align: center
        }

        .fileList img {
            width: 100%;
        }

        .fileList video {
            background-color: #333;
            width: 100%;
            height: 420px;
        }

        .fileList p {
            display: inline-block;
            position: absolute;
            left: 0;
            top: 0;
            width: 100%;
            height: 35px;
            padding: 0 20px;
            background-color: rgba(100, 100, 100, 0.5);
            margin: 0px;
            color: #f8f8f8;
            font-size: 18px;
            line-height: 35px;
            text-align: left;
        }

        .fileList p .time {
            font-size: 14px;
            float: right;
        }

    </style>
</head>
<jsp:include page="../../common/common.jsp"/>
<body>
<div class="page-content">
    <div class="widget-box" id="widget-box">
        <div class="widget-header">
            <h5 class="widget-title smaller">设置查询条件</h5>
            <div class="widget-toolbar"></div>
        </div>
        <div class="widget-body">
            <div class="widget-main padding-6">
                <form action="#" id="fm-search">
                    开始时间：<input id="startTime" class="easyui-datetimebox" name="startTime"
                                data-options="onSelect:onSelect">
                    结束时间：<input id="endTime" class="easyui-datetimebox" name="endTime"
                                data-options="disabled:true">
                    卡点名称： <input class="easyui-combogrid" name="siteId" style="height: 20px;"
                                 data-options="
                                        panelWidth: '300',
                                        idField: 'id',
                                        textField: 'siteName',
                                        url: '${pageContext.request.contextPath}/site/selectSite',
                                        mode: 'remote',
                                        fitColumns: true,
                                        onChange: function (newValue, oldValue) {

                                        },
                                        method: 'get',
                                        columns: [[{
                                            field: 'siteCode',
                                            title: '卡点编码',
                                            width: 50
                                        }, {
                                            field: 'siteName',
                                            title: '卡点名称',
                                            width: 100
                                        }]]"/>
                    是否超限：<input class="easyui-combobox" style="width: 100px" name="status"
                                data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=120&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">
                    车牌： <input name="plateNo" type="text" style="width: 100px;"/>
                    轴数： <input name="axleCount" type="text" style="width: 100px;"/>
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/traffic/findTrafficList">
                        <i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>
                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">
                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/traffic/insertTraffic">
                        <i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/traffic/updateTraffic">
                        <i class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/traffic/deleteTrafficByTrafficId">
                        <i class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
                    </button>
                </div>
            </div>
        </div>
    </div>

    <table id="grid-table"></table>

    <div id="grid-pager"></div>

</div>
<div id="dialog-message" class="hide">
    <div id="uploader">
        <p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
    </div>
</div>
<div id="dialog-message-file" class="hide">
    <div id="load" class="loading"></div>
</div>

<div id="dialog-message-view" class="hide">
    <h5 class="header-title">基本信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem"><span class="labelItemHeader">
主键</span>
            <br>
            <span id="id">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
记录卡点</span>
            <br>
            <span id="siteName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
记录监控点</span>
            <br>
            <span id="monitorSiteName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
检查时间</span>
            <br>
            <span id="inspectTime">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
地点</span>
            <br>
            <span id="locale">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
车牌号</span>
            <br>
            <span id="plateNo">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
方向</span>
            <br>
            <span id="direction">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
轴数</span>
            <br>
            <span id="axleCount">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
总重量</span>
            <br>
            <span id="totalMass">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
超限</span>
            <br>
            <span id="overMass">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
超限率</span>
            <br>
            <span id="overRate">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
速度</span>
            <br>
            <span id="speed">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
备注</span>
            <br>
            <span id="remark">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
状态</span>
            <br>
            <span id="status">
</span>
        </div>
    </div>

    <h5 class="header-title">通行画面</h5>
    <div class="row traffic-file">
        <ul class="fileList">
            <%--<li>--%>
            <%--<video src="http://www.w3school.com.cn/i/movie.ogg"  controls="controls">--%>
            <%--</video>--%>
            <%--<p>111111111111111111111111111111111111111111</p>--%>
            <%--</li>--%>
        </ul>
    </div>


    <h5 class="header-title">操作信息</h5>
    <div class="row" style="padding:10px">
        <div class="labelItem"><span class="labelItemHeader">
创建人编号</span>
            <br>
            <span id="createUserId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
创建人姓名</span>
            <br>
            <span id="createUserName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
入库日期</span>
            <br>
            <span id="createDate">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
最后更新人编号</span>
            <br>
            <span id="lastModifyUserId">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
最后更新人姓名</span>
            <br>
            <span id="lastModifyUserName">
</span>
        </div>
        <div class="labelItem"><span class="labelItemHeader">
最后更新时间</span>
            <br>
            <span id="lastModifyDate">
</span>
        </div>
    </div>

</div>
<jsp:include page="../../common/footer-1.jsp"/>
<script
        src="${pageContext.request.contextPath}/content/service/traffic/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/traffic/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/traffic/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/traffic/view.js?version=${cfg.version}"></script>
<jsp:include page="../../common/footer-2.jsp"/>
<script type="text/javascript">
    window.onresize = function () {
        console.log('autoWidthJqgrid');
        $(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        $(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight - layoutTopHeight);
        parent.autoWidth();
    }

    function onSelect(date) {  //开始日期选择时触发
        $('#endTime').datebox('enable');    //启用结束日期控件
        $('#endTime').datebox('reset')      //重置结束日期的值
    };
    $(function () {
        //只能选择今日前365天的日期
        $('#startTime').datebox('calendar').calendar({
            validator: function (date) {
                var now = new Date();
                var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate(), now.getHours(), now.getMinutes());
                return d1 >= date;
            }
        });
        $('#endTime').datebox('calendar').calendar({
            validator: function (date) {
                var now = new Date();
                var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate(), now.getHours(), now.getMinutes());
                var d3 = $('#startTime').datebox('getValue').replace(new RegExp(/-/gm), "/");
                var d4 = new Date(d3);
                return d1 >= date && date >= d4;
            }
        })
    });
</script>
</body>
</html>