<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>logs</title>
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
						内容： <input name="name" type="text"
							style="width: 200px;height:25px" />
							时间： 
							<input class="easyui-datetimebox" name="startDate" style="width:200px;height:25px;line-height: 25px;">
							至
							<input class="easyui-datetimebox" name="endDate" style="width:200px;height:25px;line-height: 25px;">
						<button class="btn btn-info" id="btn-search"
							authority="false">
							查询
							 <i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">

						
						
					</div>
				</div>
			</div>
		</div>

		<table id="grid-table"></table>

		<div id="grid-pager"></div>
		
		
	</div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/portal/js/logs/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/logs/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/logs/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/logs/view.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
window.onresize = function () {
	console.log('autoWidthJqgrid');
	$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight);
	parent.autoWidth();
}
</script>
</body>
</html>