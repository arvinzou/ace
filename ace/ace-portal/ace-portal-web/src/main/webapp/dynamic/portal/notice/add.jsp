
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<%
	String noticeId = request.getParameter("noticeId");
	request.setAttribute("edit", "true");
	if (noticeId==null) {
		noticeId = String.valueOf(new java.util.Date().getTime());
		request.setAttribute("edit", "false");
	}
	request.setAttribute("noticeId", noticeId);
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>基本信息</title>
</head>
<script type="text/javascript">
	var noticeId = '${noticeId}';
	var edit = ${edit};
</script>
<jsp:include page="../../common/common.jsp" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
	type="text/css" media="screen" />


<body>
	<div class="page-content">

		<form id="fm-notice">
			<div>
				<input type="hidden" name="noticeId" value="${noticeId }"> <input
					type="hidden" name="status" value="0"> <input type="hidden"
					name="top" value="0"> 类别：<input class="easyui-combobox"
					style="width: 100px; height: 25px; line-height: 25px;"
					name="category"
					id="cc"
					data-options="
                    url:'${pageContext.request.contextPath}/dict/findListByCategoryId.do?categoryId=21',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">有效日期：<input
					class="easyui-datebox"
					style="width: 100px; height: 25px; line-height: 25px;"
					name="deadline" data-options="required:false"> 标题：<input
					class="easyui-validatebox textbox"
					style="width: 600px; height: 25px; line-height: 25px;" name="title"
					data-options="required:false,validType:'length[1,200]'">
				发布者:${SESSION_USERPROP_KEY.name }
			</div>

			<div>
				<textarea rows="" cols="" name="content"></textarea>
			</div>
			<br />
			<div id="filelist-history"></div>
			<div id="filelist">Your browser doesn't have Flash, Silverlight
				or HTML5 support.</div>

			<div id="container">
				附件：<a id="pickfiles" href="javascript:;">[添加附件]</a> <a
					id="uploadfiles" href="javascript:;">[上传]</a>
			</div>
			<br />
			<pre id="console"></pre>
			<div>
				<button class="btn btn-info" id="btn-notice-submit"
					authority="false">
					<i class="ace-icon fa fa-check bigger-110"></i> 保存
				</button>
			</div>
		</form>

	</div>

	<!-- /section:elements.tab.option -->






	<jsp:include page="../../common/footer-1.jsp" />
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js?version=${cfg.version}"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js?version=${cfg.version}"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js?version=${cfg.version}"></script>

	<script
		src="${pageContext.request.contextPath}/content/portal/js/notice/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/notice/upload.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />

	<ckeditor:replaceAll basePath="/portal/ckeditor/" />
</body>
</html>