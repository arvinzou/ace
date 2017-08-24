<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>queueCmccWait</title>
</head>
<jsp:include page="../../common/common.jsp" />
<script type="text/javascript">


</script>
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
						手机号： <input name="tel" type="text"
							style="width: 200px;" />
							时间:
							<input class="easyui-datetimebox" name="startDate" style="width:200px;height:30px;line-height: 30px;">
							至
							<input class="easyui-datetimebox" name="endDate" style="width:200px;height:30px;line-height: 30px;">
						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/queueCmccWait/findQueueCmccWaitList.do">
							 <i
								class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div id="toolbar" class="toolbar">

						<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/queueCmccWait/deleteQueueCmccHisByQueueCmccHisId.do">
							 <i
								class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
						</button>
						
					</div>
				</div>
			</div>
		</div>

		<table id="grid-table"></table>

		<div id="grid-pager"></div>
		
		
	</div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/queueCmccWait/config.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/queueCmccWait/model.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/queueCmccWait/controller.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/queueCmccWait/view.js"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
window.onresize = function () {
	console.log('autoWidthJqgrid');
	$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight - layoutTopHeight);
}
</script>
</body>
</html>