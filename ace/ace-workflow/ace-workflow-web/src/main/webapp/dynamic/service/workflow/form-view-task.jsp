<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>formDef</title>
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
</head>
<jsp:include page="../../common/common.jsp" />
<link rel="stylesheet"
	href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
	type="text/css" media="screen" />
<script type="text/javascript">
	var formId = '${param.formId}';
	var title = '${param.title}';
	var taskId = '${param.taskId}';
	var processInstanceId = '${param.processInstanceId}';
	var processDefinitionId = '${param.processDefinitionId}';
	var formSubmitUrl = contextPath + "/workflow/completeTask.do";
</script>

<body>
	<div class="page-content">
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
			</div>
			<div class="formPanel">

				<form id="fm-preview" onsubmit="return false">
					<div class="profile-user-info profile-user-info-striped profile-bg">

					</div>

					<table class="table-bordered table-hover hide profile-user-info">
						<thead>
							<tr>
								<th class="center">序号</th>
								<th>材料名称</th>
								<th>备注</th>
								<th>样例</th>
								<th>上传</th>
								<th>查看</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>

				</form>
			</div>
		</div>
	</div>

	<div id="dialog-message" class="hide">
		<div id="uploader">
			<p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
		</div>
	</div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script type="text/javascript"
		src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js?version=${cfg.version}"></script>
	<script type="text/javascript"
		src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js?version=${cfg.version}"></script>
	<script type="text/javascript"
		src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/formEditor/formDef/upload.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/formEditor/formDef/view-act-form.js?version=${cfg.version}"></script>

	<script
		src="${pageContext.request.contextPath}/content/formEditor/formDef/controller-act-from.js?version=${cfg.version}"></script>
		
		<script
		src="${pageContext.request.contextPath}/content/formEditor/formDef/view-act-form-data.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />


</body>
</html>