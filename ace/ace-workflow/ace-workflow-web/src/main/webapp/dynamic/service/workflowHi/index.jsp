<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>表单</title>
</head>

<jsp:include page="../../common/common.jsp" />
<style type="text/css">
.profile-group-title {
	padding: 2px;
	font-weight: 800;
}

.profile-bg {
	background: #fafafa;
}

.profile-info-value {
	background: #FFFFFF;
}
</style>

<script type="text/javascript">
	var urlid = '${param.id}';
	var title = '${param.title}';
	var processDefinitionId = '${param.processDefinitionId}';
	var processInstanceId = null;
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

						机构： <input name="deptId" class="easyui-combotree"
							data-options="url:'${portalPath}/system/selectDepartmentTreeList.do?id=01',method:'get',animate: true,
                lines:false,"
							style='width: 200px; line-height: 25px; height: 25px;'>

						开始时间： <input class="easyui-datebox" name="startDate"
							style="width: 100px; height: 25px; line-height: 25px;"> 至
						<input class="easyui-datebox" name="endDate"
							style="width: 100px; height: 25px; line-height: 25px;">
						<button class="btn btn-info" id="btn-search" authority="false">
							查询 <i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>


					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">



						<button class="btn btn-info" id="btn-view-list" authority="false">
							查看 <i
								class="ace-icon fa fa-eye  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-list2" authority="false">
							查看流程 <i
								class="ace-icon fa fa-eye  align-middle bigger-125 icon-on-right"></i>
						</button>


					</div>
				</div>
			</div>
		</div>

		<table id="grid-table"></table>

		<div id="grid-pager"></div>


	</div>

	<div id="dialog-message-preview" class="hide">
		<div id="fm-preview-data">
			<div class="profile-user-info profile-user-info-striped profile-bg">

			</div>

			<table class="table-bordered table-hover hide profile-user-info">
				<thead>
					<tr>
						<th class="center">序号</th>
						<th>材料名称</th>

						<th>查看</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<div>
			
				<div id="bpmnModel" data-model-id="15023" data-model-type="runtime"
					style="width: 643px; height: 350px;"></div>
			</div>
		</div>

	</div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/workflowHi/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/workflowHi/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/workflowHi/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/workflowHi/view.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/formEditor/formDef/view-act-form-data.js?version=${cfg.version}"></script>

	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
		window.onresize = function() {
			console.log('autoWidthJqgrid');
			$(cfg.grid_selector).jqGrid('setGridWidth',
					$(".page-content").width());
			$(cfg.grid_selector).jqGrid('setGridHeight',
					window.innerHeight - layoutTopHeight);
			parent.autoWidth();
		}
	</script>
	
	



</body>
</html>