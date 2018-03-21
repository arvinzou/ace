<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<%
	String evTaskId = request.getParameter("evTaskId");
	request.setAttribute("edit", "true");
	if (SnailUtils.isBlankString(evTaskId)) {
		Object obj = session.getAttribute(CommonKeys.SystemUser);
		SystemUser systemUser = (SystemUser) obj;
		evTaskId = String.valueOf(new java.util.Date().getTime());
		request.setAttribute("edit", "false");

	}
	request.setAttribute("evTaskId", evTaskId);
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>二维码打印</title>
</head>
<script type="text/javascript">
	var evTaskId = '${evTaskId}';
	var deptId = '${SystemUser.users.departmentId}';
</script>
<jsp:include page="../../common/common.jsp" />
<style>
.layout-user {
	width: 100px;
	height: 30px;
	float: left;
	margin: 2px 2px 2px;
}
</style>
<style>
@media print {
    .noprint {
        display: none
    }
}
</style>
<body>
	<div class="page-content">




		<div style="text-align: right" class="noprint">
			<button class="btn btn-app btn-light btn-xs" authority="false"
				id="btn-print">
				<i class="ace-icon fa fa-print bigger-160"></i> 打印
			</button>

		</div>
		<table width="655" border="0" align="center" cellpadding="0"
			cellspacing="0" id="task-content">
			
		</table>


	</div>

	<!-- /section:elements.tab.option -->
	<jsp:include page="../../common/footer-1.jsp" />


	<script
		src="${pageContext.request.contextPath}/content/js/service/evTask/printqr.js"></script>

	<jsp:include page="../../common/footer-2.jsp" />
</body>
</html>