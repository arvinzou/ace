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
</head>
<jsp:include page="../../common/common.jsp" />
<link rel="stylesheet"
	href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
	type="text/css" media="screen" />
<script type="text/javascript">
	var formId = '${param.id}';
</script>
<link rel="stylesheet"
	href="../css/form-editor.css?version=${cfg.version}" />
<body>
	<div class="page-content">

		<div class="widget-box" id="widget-box">
			<div class="widget-header">
				<h5 class="widget-title smaller">表单设计</h5>

				<div class="widget-toolbar"></div>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-6">


					<div id="toolbar" class="toolbar">



						<button class="btn btn-info" id="btn-view-edit" authority="false">
							编辑 <i
								class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
						</button>

						<button class="btn btn-warning" id="btn-view-del"
							authority="false">
							删除 <i
								class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
						</button>

						<button class="btn btn-info" id="btn-view-save" authority="false">
							保存 <i
								class="ace-icon fa fa-floppy-o  align-middle bigger-125 icon-on-right"></i>
						</button>

						<button class="btn btn-info" id="btn-view-preview"
							authority="false">
							预览 <i
								class="ace-icon fa fa-chevron-circle-right  align-middle bigger-125 icon-on-right"></i>
						</button>

					</div>
				</div>
			</div>
		</div>

		<div style="width: 100%;">
			<div class="left">
				<table class="table-bordered table-hover" style="width: 100%">

					<tbody>


					</tbody>
				</table>
			</div>
			<div class="right">
				<table class="table-bordered table-hover">
					<thead>
						<tr>
							<th class="title" style="width: 30px"></th>
							<th class="title">列1</th>
							<th class="title">列2</th>
							<th class="title">列3</th>
							<th class="title">列4</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="title">1</td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>

						</tr>
						<tr>
							<td class="title">2</td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
						</tr>
						<tr>
							<td class="title">3</td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
						</tr>
						<tr>
							<td class="title">4</td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
						</tr>
						<tr>
							<td class="title">5</td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
						</tr>

						<tr>
							<td class="title">6</td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
						</tr>
						<tr>
							<td class="title">7</td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
						</tr>
						<tr>
							<td class="title">8</td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
						</tr>
						<tr>
							<td class="title">9</td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
						</tr>
						<tr>
							<td class="title">10</td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
						</tr>
						<tr>
							<td class="title">11</td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
						</tr>
						<tr>
							<td class="title">12</td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
						</tr>
						<tr>
							<td class="title">13</td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
						</tr>
						<tr>
							<td class="title">14</td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
						</tr>
						<tr>
							<td class="title">15</td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
							<td class="drop"></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<div id="dialog-message" class="hide">
		<div id="uploader">
			<p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
		</div>
	</div>
	<jsp:include page="filedEditorTemplete/textbox.jsp" />
	<jsp:include page="filedEditorTemplete/combobox.jsp" />
	<jsp:include page="filedEditorTemplete/checkboxgroup.jsp" />
	<jsp:include page="filedEditorTemplete/combogrid.jsp" />
	<jsp:include page="filedEditorTemplete/combotree.jsp" />
	<jsp:include page="filedEditorTemplete/datebox.jsp" />
	<jsp:include page="filedEditorTemplete/filesbox.jsp" />
	<jsp:include page="filedEditorTemplete/grouptitle.jsp" />
	<jsp:include page="filedEditorTemplete/rediogroup.jsp" />
	<div id="dialog-message-preview" class="hide">
		<div class="formPanel">
			<form id="fm-preview">
				<div class="profile-user-info profile-user-info-striped profile-bg">

				</div>
			</form>
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
		src="${pageContext.request.contextPath}/content/formEditor/formDef/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/formEditor/formDef/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/formEditor/formDef/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/formEditor/formDef/view.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<script
		src="/portal/content/common/js/easyui-draggable.js?version=${cfg.version}"></script>
</body>
</html>