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
<title>基本信息</title>
</head>
<script type="text/javascript">
	var evTaskId = '${evTaskId}';
	var edit = ${edit};
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

<body>
	<div class="page-content">


		
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<td align="center"><strong>账号列表</strong>     上次更新：<span id="time"></span></td>
					</tr>
				</thead>


				<tbody>
					<tr>
						<td align="center">
							<table style="width: 80%">
								<tr>
									<td align="right">编号：</td>
									<td id="evTaskId"></td>
									<td align="right">名称：</td>
									<td id="evTaskName"></td>
									<td align="right">评测对象：</td>
									<td id="evObjName"></td>
									<td align="right">开始时间：</td>
									<td id="evStartDate"></td>
									<td align="right">截止时间：</td>
									<td ><span class='badge badge-info' id="evDeadline"></span></td>
								</tr>
								<tr>
									<td align="right">状态：</td>
									<td id="status"></td>
									<td align="right">创建时间：</td>
									<td id="createTime"></td>
									<td align="right">评测模板：</td>
									<td id="evTempleteId"></td>
									<td align="right">操作员：</td>
									<td id="adminName"></td>
									<td align="right">类别：</td>
									<td id="category"></td>
								</tr>
							</table>
						</td>
					</tr>
			
				</tbody>
			</table>
			<div style="text-align:right">
			<button class="btn btn-app btn-danger btn-xs" authority="false" id="btn-del">
											<i class="ace-icon fa fa-trash-o bigger-160"></i>
											删除
										</button>
			<button class="btn btn-app btn-light btn-xs" authority="false" id="btn-print">
											<i class="ace-icon fa fa-print bigger-160"></i>
											打印
										</button>
			
			</div>
			<table id="task-user" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">
													<label class="position-relative">
														<input type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
												</th>
						<th>序号</th>
						<th>账号</th>
						<th>用户名</th>
						<th>状态</th>
						<th>分发时间</th>
						<th>投票时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="task-content">
						
				</tbody>
			</table>
	</div>

	<!-- /section:elements.tab.option -->
	<jsp:include page="../../common/footer-1.jsp" />


<script
		src="${pageContext.request.contextPath}/content/js/service/evTask/view-task-user.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/evTask/preview.js"></script>
<jsp:include page="../../common/footer-2.jsp" />
</body>
</html>