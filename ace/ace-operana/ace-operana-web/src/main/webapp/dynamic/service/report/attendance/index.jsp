<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta charset="utf-8"/>
	<meta name="viewport"
		  content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
	<title>指标</title>
</head>
<jsp:include page="../../../common/common.jsp"/>
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

					会议时间：
					<input class="easyui-datebox" name="startDate">
					~
					<input class="easyui-datebox" name="endDate">
					<button class="btn btn-info" id="btn-search"
							authority="false">
						统计<i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
					</button>


				</form>
				<div class="space10"></div>
				<div id="toolbar" class="toolbar">





				</div>
			</div>
		</div>
	</div>

	<table id="report"
		   class="table table-striped table-bordered table-hover">
		<thead>
		<tr>
			<th class="center" style="width:80px;">序号</th>
			<th>姓名</th>
			<th>部门</th>
			<th>Total</th>
			<th>Present</th>
			<th>Absent</th>
			<th>Justified</th>
		</tr>
		</thead>
		<tbody>
		</tbody>
	</table>


</div>

<jsp:include page="../../../common/footer-1.jsp"/>
<script
		src= "${portalPath}/content/common/js/dataTable/jquery.dataTables.min.js?version=${cfg.version}"></script>
<script
		src= "${portalPath}/content/common/js/dataTable/dataTables.bootstrap.min.js?version=${cfg.version}"></script>

<script
		src="${pageContext.request.contextPath}/content/service/report/attendance/controller.js?version=${cfg.version}"></script>
<jsp:include page="../../../common/footer-2.jsp"/>
<script type="text/javascript">

</script>
</body>
</html>