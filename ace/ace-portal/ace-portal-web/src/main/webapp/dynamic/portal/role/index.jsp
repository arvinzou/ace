<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<meta name="viewport"
		  content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<title>系统配置</title>
</head>
<jsp:include page="/dynamic/common/header.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}" />
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
<div class="portlet light ">

	<div class="portlet-body">

		<div class="row custom-toolbar">
			<div class="col-md-4">

				<button type="button" class="btn  green" id="btn-view-add" authority="${pageContext.request.contextPath}/role/insertRole.do"></button>




			</div>
			<div class="col-md-5">
			</div>

			<div class="col-md-3">
				<form action="#" id="fm-search" >
					<div class="input-group">
						<input type="text"
							   name="roleName"
							   class="form-control"
							   placeholder="请输入名称">
						<span class="input-group-btn">
							<button class="btn  btn-default search_btn"  id="btn-search"
									authority="${pageContext.request.contextPath}/role/findRoleList.do">
									搜索
							</button>
						</span>
					</div>
				</form>
			</div>

		</div>

		<table id="grid-table"></table>

		<div class="paginationbar"><ul id="grid-pager" class="pagination"></ul></div>
	</div>
</div>

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />


<div class="modal fade"  role="dialog" id="modal-role">
	<div class="modal-dialog" role="document" style="width: 830px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button"  authority="false" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">分配权限</h4>
			</div>
			<div class="modal-body">
				<div class="easyui-panel" style="padding:5px;width:800px;height:400px">
					<ul id="tt" class="easyui-tree" data-options="url:'${pageContext.request.contextPath}/role/selectRoleResourceTreeList.do?roleId=1',method:'get',animate:true,checkbox:true,lines:false">
					</ul>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
				<button type="button" class="btn green" authority="false" id="btn-view-save">确定</button>
			</div>
		</div>
	</div>
</div>

<jsp:include page="/dynamic/common/footer.jsp" />
<link rel="stylesheet" type="text/css"
	  href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
	  href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
		src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
		src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>

<script src="${portalPath}/content/common/jqGrid/jquery.jqGrid.new.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/js/jqGrid/i18n/grid.locale-cn.js?version=${cfg.version}"></script>
<script
		src="${pageContext.request.contextPath}/content/portal/js/role/config.js?version=${cfg.version}"></script>
<script
		src="${pageContext.request.contextPath}/content/portal/js/role/model.js?version=${cfg.version}"></script>
<script
		src="${pageContext.request.contextPath}/content/portal/js/role/controller.js?version=${cfg.version}"></script>
<script
		src="${pageContext.request.contextPath}/content/portal/js/role/view.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>



</body>
</html>