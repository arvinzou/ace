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


<body>
	<div class="page-content">


		<form id="fm-evTask">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<td align="center"><strong>开启评测</strong></td>
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
								<td id="evDeadline"></td>
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
					<tr>
						
						<td align="center">
						
							请选择评测状态：<input class="easyui-combobox"
									style="height: 20px; line-height: 20px;"
									name="status"
									data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=54',
                    method:'get',
                    valueField:'code',
                    textField:'name'
            ">
						</td>
					</tr>
					<tr>
						<td><input type="hidden" name="evTaskId"
							value="${evTaskId }">

							<button class="btn btn-info btn-block" id="btn-evTask-submit"
								authority="false">保存</button></td>
					</tr>

				</tbody>
			</table>
		</form>








	</div>

	<!-- /section:elements.tab.option -->






	<jsp:include page="../../common/footer-1.jsp" />


	<jsp:include page="../../common/footer-2.jsp" />


	
	<script
		src="${pageContext.request.contextPath}/content/js/service/evTask/controller-add.js"></script>
	
</body>
</html>