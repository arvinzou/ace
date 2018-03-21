<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<%
	String evTempleteId = request.getParameter("evTempleteId");
	request.setAttribute("edit", "true");
	if (SnailUtils.isBlankString(evTempleteId)) {
		Object obj = session.getAttribute(CommonKeys.SystemUser);
		SystemUser systemUser = (SystemUser) obj;
		evTempleteId = String.valueOf(new java.util.Date().getTime());
		request.setAttribute("edit", "false");
	}
	request.setAttribute("evTempleteId", evTempleteId);
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
	var evTempleteId = '${evTempleteId}';
	var edit = ${edit};
</script>
<jsp:include page="../../common/common.jsp" />


<body>
	<div class="page-content">


<form id="fm-evTemplete">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<td colspan="6" align="center"><strong>基本信息</strong></td>
				</tr>
			</thead>
			
			<tr>
				<td>名称：</td>
				<td><input class="easyui-validatebox textbox"
					style="width: 300px;" name="evName"
					data-options="required:false,validType:'length[1,200]'"></td>
				<td>说明：</td>
				<td><input class="easyui-validatebox textbox"
					style="width: 300px;" name="evDiscription"
					data-options="required:false,validType:'length[1,200]'"></td>
				<td>类别：</td>
				<td><input class="easyui-combobox" style="width: 100px;"
					name="category"
					data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=20',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            "></td>
			</tr>

			<tbody>
				
				<tr>
				<td>样式:</td>
					<td colspan="5"><textarea name="evContent" style="width: 100%"></textarea></td>
				</tr>
				<tr>
					<td colspan="6"><input type="hidden" name="evTempleteId"
						value="${evTempleteId }"> <input type="hidden"
						name="status" value="1">

						<button class="btn btn-info btn-block" id="btn-evTemplete-submit"
							authority="false">
							 保存
						</button></td>
				</tr>
				
			</tbody>
		</table>
		</form>
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<td colspan="6" align="center"><strong>指标维护</strong></td>
				</tr>
			</thead>
			<tr>
				<td colspan="6" align="center">
					<div id="toolbar" class="toolbar">

						<button class="btn btn-info" id="btn-view-add" authority="false">
							指标添加 <i
								class="ace-icon fa fa-plus-square  align-top bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-edit" authority="false">
							指标修改 <i
								class="ace-icon fa fa-edit  align-top bigger-125 icon-on-right"></i>
						</button>

						<button class="btn btn-warning" id="btn-view-del"
							authority="false">
							指标删除 <i
								class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
						</button>

					</div>
					<table id="grid-table"></table>
				</td>
			</tr>
			</tbody>
		</table>







	</div>

	<!-- /section:elements.tab.option -->






	<jsp:include page="../../common/footer-1.jsp" />


	<script
		src="${pageContext.request.contextPath}/content/js/service/evTarget/config.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/evTarget/model.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/evTarget/controller.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/evTarget/view.js"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
		window.onresize = function() {
			console.log('autoWidthJqgrid');
			$(cfg.grid_selector).jqGrid('setGridWidth',
					$(".page-content").width());
			$(cfg.grid_selector).jqGrid('setGridHeight',
					window.innerHeight-layoutTopHeight);
		}
	</script>

	<jsp:include page="../../common/footer-2.jsp" />

	<script
		src="${pageContext.request.contextPath}/content/js/service/evTemplete/controller-add.js"></script>

</body>
</html>