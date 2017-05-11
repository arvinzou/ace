<%@page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>运营分析</title>

</head>
<jsp:include page="/dynamic/common/common.jsp"/>
<style type="text/css">
			*{padding:0;margin:0;}
			ul li{list-style:none;}
			.ulinput{margin-left:50px;}



</style>
<link rel="stylesheet"
      href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
      type="text/css" media="screen"/>
<body>
<!-- /section:basics/sidebar -->
<div class="page-content">


    <div class="row">
        <div class="col-md-3">
            <h5 class="header-title">即将进行的会议</h5>
            <table id="meeting-grid" class="table table-bordered">


            </table>
        </div>
        <div class="col-md-7">
            <div id="dialog-message" class="hide">

            </div>
            <div id="dialog-user" class="hide">
                <input type="text" name="_user" id="_user">
            </div>

            <div id="dialog-message-view">

                <h5 class="header-title">基本信息</h5>

                <div style="padding:10px" class="row">
                    <div class="labelItem">
                        <span class="labelItemHeader">会议名称</span><br>
                        <span id="name"> </span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">会议描述</span><br>
                        <span id="discribtion"> </span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">会议时间</span><br>
                        <span id="meetingDate"> </span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">会议地点</span><br> <span id="site"> </span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">会议部门</span><br>
                        <span id="divisionName"> </span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">说明</span><br>
                        <span id="comment"> </span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">主持人</span><br>
                        <span id="ownerName"> </span>
                    </div>
                    <div class="labelItem">
                        <span class="labelItemHeader">注意事项</span><br>
                        <span id="note"> </span>
                    </div>
                </div>
                <div>
                    <div class="div-left header-title">议题</div>
                    <div class="div-right header-title">
                        <div style="text-align:right"><a data-rel="tooltip" data-placement="top" title="议题设置"
                                                         class="blue" href="javascript:topicSetting()"><i
                                class="ace-icon fa fa-plus-circle"></i></a></div>
                    </div>
                </div>
                <div class="row">
                    <form id="fm3">
                        <table id="detail"
                               class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center" style="width:80px;">序号</th>

                                <th>议题名称</th>
                                <th>负责人</th>
                                <th style="width:120px;">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </form>
                </div>
                <div>
                    <div class="div-left header-title">与会人员</div>
                    <div class="div-right header-title">
                        <div style="text-align:right"><a data-rel="tooltip" data-placement="top" title="与会人员设置"
                                                         class="blue" href="javascript:userSetting()"><i
                                class="ace-icon fa fa-plus-circle"></i></a></div>
                    </div>
                </div>
                <div class="row">
                    <form id="fm4">
                        <table id="detail1"
                               class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center" style="width:80px;">序号</th>

                                <th>职务</th>
                                <th>姓名</th>
                                <th>是否必须</th>
                                <th style="width:80px;">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>

            <div id="dialog-meeting-user" class="hide">

            </div>
            <div id="dialog-upload" class="hide">
                <div id="uploader">
                    <p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
                </div>
            </div>
            <div id="dialog-norm" class="hide">
                <h5 class="header-title">指标</h5>
                <table id="detail4"
                       class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th class="center" style="width:80px;">序号</th>
                        <th>指标名称</th>
                        <th>说明</th>
                        <th style="width:100px;">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>

        </div>
        <div class="col-md-2">
            <h5 class="header-title">代办任务</h5>
        </div>

    </div>


</div>
<!-- /.page-content -->
<jsp:include page="/dynamic/common/footer-1.jsp"/>
<script
        src="${portalPath}/content/common/js/xcheck/XCheck.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/index/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/index/view.js?version=${cfg.version}"></script>

<jsp:include page="/dynamic/common/footer-2.jsp"/>
<script
        src="${portalPath}/content/common/js/dataTable/jquery.dataTables.min.js?version=${cfg.version}"></script>
<script
        src="${portalPath}/content/common/js/dataTable/dataTables.bootstrap.min.js?version=${cfg.version}"></script>
<script
        src="${portalPath}/content/common/js/easyui-draggable.js?version=${cfg.version}"></script>

<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
<script
        src="${pageContext.request.contextPath}/content/service/meeting/upload.js"></script>
<script type="text/javascript">

</script>
<style>
.page-content {
	background-color: #fff;
	position: relative;
	margin: 0;
	padding: 10px 10px 10px;
}
.checkboxitem {
	width: 250px;
	height: 20px;
	float: left;
	margin: 2px 2px 2px;
}
.labelItem{
	width: 180px;
	height: 30px;
	float: left;
	margin: 4px 4px 4px;
}
.labelItemHeader{
	font-weight:800;
	font-size:14px;
}
.div-left{ float:left;width:90%;}
.div-right{ float:right;width:10%;}

</style>
</body>
</html>