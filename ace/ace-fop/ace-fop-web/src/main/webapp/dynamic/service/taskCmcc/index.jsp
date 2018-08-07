<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>taskCmcc</title>
</head>
<jsp:include page="../../common/common.jsp"/>
<script type="text/javascript">
    var urlid = '${param.id}';
    var edit = false;
</script>
<style>
    .layout-user {
        width: 150px;
        height: 20px;
        float: left;
        margin: 1px 1px 1px;
    }
</style>
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
                    任务名称： <input name="taskCmccName" type="text"
                                 style="width: 200px;"/>
                    时间:
                    <input class="easyui-datetimebox" name="dateStart"
                           style="width:200px;height:30px;line-height: 30px;">
                    至
                    <input class="easyui-datetimebox" name="dateEnd" style="width:200px;height:30px;line-height: 30px;">

                    <button class="btn btn-info" id="btn-search"
                            authority="${pageContext.request.contextPath}/taskCmcc/findTaskCmccList.do">
                        <i class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
                    </button>

                </form>
                <div id="toolbar" class="toolbar">

                    <button class="btn btn-info" id="btn-view-add"
                            authority="${pageContext.request.contextPath}/taskCmcc/insertTaskCmcc.do">
                        <i class="ace-icon fa fa-plus-square  align-top bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-info" id="btn-view-detail"
                            authority="false">
                        详细 <i class="ace-icon fa fa-edit  align-top bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-purple" id="btn-view-da"
                            authority="${pageContext.request.contextPath}/taskCmcc/insertTaskCmccResources.do">
                        <i class="ace-icon glyphicon  glyphicon-cog  align-top bigger-125 icon-on-right"></i>
                    </button>
                    <button class="btn btn-warning" id="btn-view-del"
                            authority="${pageContext.request.contextPath}/taskCmcc/deleteTaskCmccByTaskCmccId.do">
                        <i class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
                    </button>
                    <!--
					<button class="btn btn-info" id="btn-view-repeat"
							authority="${pageContext.request.contextPath}/taskCmcc/updateTaskStatusCmccByTaskCmccId.do">
							<i
								class="ace-icon glyphicon glyphicon-repeat  align-top bigger-125 icon-on-right"></i>
						</button>
						 -->
                </div>
            </div>
        </div>
    </div>

    <table id="grid-table"></table>

    <div id="grid-pager"></div>
    <div id="dialog-message" class="hide">
        <div class="easyui-panel" id="msg-content" style="padding:5px;width:630px;height:100px">

        </div>
        <div style="height:5px"></div>
        <div class="easyui-panel" id="task-content" style="padding:5px;width:630px;height:200px">

        </div>

    </div>
    <div id="dialog-confirm" class="hide">
        <div class="alert alert-info bigger-110">
            点击确定后，系统将发送短信.

        </div>

        <div class="space-6"></div>

        <p class="bigger-110 bolder center grey">
            <i class="ace-icon fa fa-hand-o-right blue bigger-120"></i>
            您确定吗?
        </p>
    </div>
</div>
<jsp:include page="../../common/footer-1.jsp"/>
<script
        src="${pageContext.request.contextPath}/content/service/taskCmcc/config.js"></script>
<script
        src="${pageContext.request.contextPath}/content/service/taskCmcc/model.js"></script>
<script
        src="${pageContext.request.contextPath}/content/service/taskCmcc/controller.js"></script>
<script
        src="${pageContext.request.contextPath}/content/service/taskCmcc/view.js"></script>
<jsp:include page="../../common/footer-2.jsp"/>
<script type="text/javascript">
    window.onresize = function () {
        console.log('autoWidthJqgrid');
        $(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        $(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight - layoutTopHeight);
    }
</script>
</body>
</html>