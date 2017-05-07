<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>会议</title>
</head>
<jsp:include page="../../common/common.jsp"/>
<script type="text/javascript">


</script>
<style type="text/css">
			*{padding:0;margin:0;}
			ul li{list-style:none;}
			.ulinput{margin-left:50px;}

</style>
<link rel="stylesheet"
      href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
      type="text/css" media="screen" />
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
                    部门： <input id="cc1" name="division"
                               class="easyui-combotree"
                               data-options="url:'${portalPath}/system/selectDepartmentTreeList.do',method:'get',animate: true,
                lines:false"
                               style='width: 200px; line-height: 25px; height: 25px;'/>

                    <a
                            href="javascript:clearQparams()">清除</a>

                    状态：
                    <input type="radio" name="status" id="r1" value="1"/><label for="r1">准备中</label>
                    <input type="radio" name="status" id="r2" value="2"/><label for="r2">打开</label>
                    <input type="radio" name="status" id="r3" value="3"/><label for="r3">关闭</label>

                    名称： <input name="name" type="text"
                               style="width: 200px;"/>
                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/meeting/findMeetingList.do">
                        <i
                                class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
                    </button>


                </form>
                <div class="space10"></div>
                <div id="toolbar" class="toolbar">


                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/meeting/insertMeeting.do">
                        <i
                                class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-edit"
                            authority="${pageContext.request.contextPath}/meeting/updateMeeting.do">
                        <i
                                class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/meeting/deleteMeetingByMeetingId.do">
                        <i
                                class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
                    </button>

                </div>
            </div>
        </div>
    </div>

    <table id="grid-table"></table>

    <div id="grid-pager"></div>


</div>
<div id="dialog-message" class="hide">

</div>
<div id="dialog-user" class="hide">
    <input type="text" name="_user" id="_user">
</div>

<div id="dialog-message-view" class="hide">

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
    <h5 class="header-title">议题</h5>
    <div class="row">
        <form id="fm3">
            <table id="detail"
                   class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th class="center" style="width:80px;">序号</th>

                    <th>议题名称</th>
                    <th>负责人</th>
                    <th >操作</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </form>
    </div>
    <h5 class="header-title">与会人员</h5>
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
<jsp:include page="../../common/footer-1.jsp"/>
<script
        src="${portalPath}/content/common/js/xcheck/XCheck.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/meeting/config.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/meeting/model.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/meeting/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/meeting/view.js?version=${cfg.version}"></script>
<jsp:include page="../../common/footer-2.jsp"/>
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
window.onresize = function () {
	console.log('autoWidthJqgrid');
	$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight);
	parent.autoWidth();
}

</script>
<style>
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

</style>
</body>
</html>