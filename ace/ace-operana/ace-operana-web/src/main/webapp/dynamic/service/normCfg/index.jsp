<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>指标</title>
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

						部门： <input id="cc1" name="deptId"
								   class="easyui-combotree"
								   data-options="url:'${portalPath}/system/selectDepartmentTreeList.do',method:'get',animate: true,
                lines:false"
								   style='width: 200px; line-height: 25px; height: 25px;'/>

						<a
								href="javascript:clearQparams()">清除</a>
						类别：<input
							class="easyui-combobox" style="width: 100px" name="category"
							data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=86&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'"/>

						年度：<input
							class="easyui-combobox" style="width: 100px" name="year"
							data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=year&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'"/>

						名称： <input name="name" type="text"  style="width: 100px;" />
						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/normCfg/findNormCfgList.do">
							 <i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>

						
					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">

						

						<button class="btn btn-info" id="btn-view-edit"
							authority="${pageContext.request.contextPath}/normCfg/updateNormCfg.do">
							 <i
								class="ace-icon fa fa-cog  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/normCfg/deleteNormCfgByNormCfgId.do">
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
		src="${pageContext.request.contextPath}/content/service/normCfg/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/normCfg/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/normCfg/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/normCfg/view.js?version=${cfg.version}"></script>
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