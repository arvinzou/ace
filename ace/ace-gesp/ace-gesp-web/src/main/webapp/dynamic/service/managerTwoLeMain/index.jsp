<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>二级维护</title>
</head>
<jsp:include page="../../common/common.jsp" />
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
						<input name="repairType" type="hidden" value="二级维护" />
						企业名称： <input name="companyName" type="text" style="width: 150px; height: 25px" />
						车牌号： <input name="plateNo" type="text" style="width: 150px; height: 25px" />
						<%-- 终检有效期： <input class="easyui-combobox" style="width: 150px" name="repairDate"
							data-options="url:'${portalPath}/dict/findListByCategoryId.do?categoryId=98&selected=false',
				                    method:'get',
				                    valueField:'code',
				                    textField:'name',
				                    editable:false,
				                    panelHeight:200">	 --%>
						<button class="btn btn-info" id="btn-search" authority="false">
							查询 <i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>
					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">
						<button class="btn btn-info" id="btn-view-update"
							authority="${pageContext.request.contextPath}/industryResource/updateCarTypeInfoById.do">
							<i class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
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
		src="${pageContext.request.contextPath}/content/service/managerTwoLeMain/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/managerTwoLeMain/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/managerTwoLeMain/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/managerTwoLeMain/view.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
		window.onresize = function() {
			autoSize();
		}
		parent.onresize = function() {
			autoSize();
		}
		function autoSize() {
			parent.autoWidth();
			$(cfg.grid_selector).jqGrid('setGridWidth',
					$(".page-content").width());
			$(cfg.grid_selector).jqGrid('setGridHeight',
					window.innerHeight - layoutTopHeight+25);
		}
	</script>
</body>
</html>