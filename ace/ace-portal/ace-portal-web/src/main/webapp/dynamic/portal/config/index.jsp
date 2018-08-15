<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>config</title>
</head>
<jsp:include page="../../common/common.jsp" />
<table>
	<tr><td></td></tr>
</table>
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
						参数名称： <input name="configName" type="text"
							style="width: 200px;height:25px" />

						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/config/findConfigList.do">
							 <i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">

						<button class="btn btn-info" id="btn-view-add"
							authority="${pageContext.request.contextPath}/config/insertConfig.do">
							 <i
								class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-edit"
							authority="${pageContext.request.contextPath}/config/updateConfig.do">
							 <i
								class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
						</button>
						
						
						<button class="btn btn-purple" id="btn-view-deploy"
							authority="${pageContext.request.contextPath}/sysConfig/deployConfig.do">
							 <i
								class="ace-icon glyphicon glyphicon-refresh  align-middle bigger-125 icon-on-right"></i>
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
		src="${pageContext.request.contextPath}/content/portal/js/config/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/config/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/config/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/config/view.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
window.onresize = function () {
	console.log('autoWidthJqgrid');
	$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight);
	//parent.autoWidth();
}
</script>
</body>
</html>