<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<meta name="viewport"
		  content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<title>role</title>
</head>
<jsp:include page="../../common/common.jsp" />
<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">

<div class="page-content">
	<div class="widget-box" id="widget-box">
		<div class="widget-header">
			<h5 class="widget-title smaller">设置查询条件</h5>

			<div class="widget-toolbar"></div>
		</div>

		<div class="widget-body">
			<div class="widget-main padding-6">
				<form action="#" id="fm-search">
					角色名称： <input name="roleName" type="text"
								 style="width: 200px;height:25px" />

					<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/role/findRoleList.do">
						<i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
					</button>

				</form>
				<div class="space10"></div>
				<div id="toolbar" class="toolbar">

					<button class="btn btn-info" id="btn-view-add"
							authority="${pageContext.request.contextPath}/role/insertRole.do">
						<i
								class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
					</button>
					<button class="btn btn-info" id="btn-view-edit"
							authority="${pageContext.request.contextPath}/role/updateRole.do">
						<i
								class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
					</button>
					<button class="btn btn-purple" id="btn-view-da"
							authority="${pageContext.request.contextPath}/role/insertRoleResources.do">
						<i
								class="ace-icon glyphicon  glyphicon-cog  align-middle bigger-125 icon-on-right"></i>
					</button>
					<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/role/deleteRoleByRoleId.do">
						<i
								class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
					</button>

				</div>
			</div>
		</div>
	</div>

	<table id="grid-table"></table>

	<div id="grid-pager"></div>

	<div id="dialog-message" class="hide">
		<div class="easyui-panel" style="padding:5px;width:350px;height:400px">
			<ul id="tt" class="easyui-tree" data-options="url:'${pageContext.request.contextPath}/role/selectRoleResourceTreeList.do?roleId=1',method:'get',animate:true,checkbox:true,lines:false">
			</ul>
		</div>
		<div id="spinner-preview"></div>
	</div>
	<div id="dialog-confirm" class="hide">
		<div class="alert alert-info bigger-110">
			重新分配权限后，分配此角色的用户将获取新的权限.

		</div>

		<div class="space-6"></div>

		<p class="bigger-110 bolder center grey">
			<i class="ace-icon fa fa-hand-o-right blue bigger-120"></i>
			您确定吗?
		</p>
	</div>
</div>
<jsp:include page="../../common/footer-1.jsp" />
<script
		src="${pageContext.request.contextPath}/content/portal/js/role/config.js?version=${cfg.version}"></script>
<script
		src="${pageContext.request.contextPath}/content/portal/js/role/model.js?version=${cfg.version}"></script>
<script
		src="${pageContext.request.contextPath}/content/portal/js/role/controller.js?version=${cfg.version}"></script>
<script
		src="${pageContext.request.contextPath}/content/portal/js/role/view.js?version=${cfg.version}"></script>
<jsp:include page="../../common/footer-2.jsp" />
<script type="text/javascript">
window.onresize = function () {
	console.log('autoWidthJqgrid');
	$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight);
	////parent.autoWidth();
}
jQuery(function ($) {

});




</script>
</body>
</html>