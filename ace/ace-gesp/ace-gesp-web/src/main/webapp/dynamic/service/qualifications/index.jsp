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
<script type="text/javascript">
	var deId = '${SESSION_USERPROP_KEY.corpId}';
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
	
						会员名称： <input name="deptName" type="text"
							style="width: 150px; height: 25px" /> <input name="status"
							value="0" type="hidden">
						<input type="hidden" id="index_id" value="${SESSION_USERPROP_KEY.corpId}" />
						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/qualifications/findQualificationsList.do">
							<i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>


					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar"></div>
				</div>
			</div>
		</div>

		<table id="grid-table"></table>

		<div id="grid-pager"></div>


	</div>

	<div id="dialog-message" class="hide"  onsubmit="return beforeSubmit()">
		<form action="" id="fm1">
			<table id="table1"
				class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">序号</th>
						<th>材料名称</th>
						<th>操作</th>
						<th>审核结果</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</form>
	</div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/qualifications/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/qualifications/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/qualifications/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/qualifications/view.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/common/js/jquery.serializeObject.min.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
		window.onresize = function() {
			console.log('autoWidthJqgrid');
			$(cfg.grid_selector).jqGrid('setGridWidth',
					$(".page-content").width());
			$(cfg.grid_selector).jqGrid('setGridHeight',
					window.innerHeight - layoutTopHeight + 25);
			parent.autoWidth();
		}
	</script>
</body>
</html>