<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>vendorMana</title>
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
						公司名称： <input name="companyName" type="text" style="width: 110px;height:25px" />
						公司编码： <input name="companyCode" type="text" style="width: 110px;height:25px" />
						状态：<select name="stauts" style="width: 80px;height:25px">
                            <option value="">请选择</option>
                            <option value="0">未启用</option>
                            <option value="1">启用</option>
                         </select>
						
						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/vendorMana/findVendorManaList.do">
							 <i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">

						<button class="btn btn-info" id="btn-view-add"
							authority="${pageContext.request.contextPath}/vendorMana/insertVendorMana.do">
							 <i
								class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-edit"
							authority="${pageContext.request.contextPath}/vendorMana/updateVendorMana.do">
							 <i
								class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
						</button>
						
						<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/vendorMana/deleteVendorMana.do">
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
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/portal/js/vendorMana/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/vendorMana/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/vendorMana/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/vendorMana/view.js?version=${cfg.version}"></script>
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