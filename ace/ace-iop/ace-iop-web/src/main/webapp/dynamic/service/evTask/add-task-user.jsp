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
						<td align="center"><strong>分发账号</strong></td>
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
						
						分发账户数量：<input type="text" name="limit"
							value="30">
							
							<button class="btn btn-info" id="btn-view-deploy"
							authority="false">
							 <i
								class="ace-icon glyphicon glyphicon-refresh  align-top bigger-125 icon-on-right"></i>
						获取可分配账户
						</button>
							
						</td>
					</tr>
					<tr>

						<td align="center" id="task-content"></td>
					</tr>
					<tr>
						<td><input type="hidden" name="evTaskId" value="${evTaskId }">

							<button class="btn btn-info btn-block" id="btn-evTask-user"
								authority="false">保存</button></td>
					</tr>

				</tbody>
			</table>
		








	</div>

	<!-- /section:elements.tab.option -->






	<jsp:include page="../../common/footer-1.jsp" />


	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
	jQuery(function($) {
		$('#btn-view-deploy').on('click', function() {
			selectUsersListByDepartmentId();
		});
		$('#btn-evTask-user').on('click', function() {
			setUserAccount($('#btn-evTask-user'));
		});
	});
		function selectUsersListByDepartmentId() {
			$.ajax({
						type : "post",
						url : contextPath
								+ "/evTaskUsers/selectUserListByDeptId.do",
						data : {
							evTaskId : evTaskId,
							limit : $("input[name=limit]").val()
						},
						beforeSend : function(XMLHttpRequest) {
						},
						success : function(rst, textStatus) {
							var html = new Array();
							$.each($(rst.list),function(i, o) {
									html.push('<div class="layout-user" >');
									html.push('<user id="'+o.USER_ID+'" class="badge badge-'+cssColor9[0]+'">');
									html.push(o.NAME);
									html.push('</user>');
									html.push('</div>');
							});
							$('#task-content').html(html.join(''));
						}
					});
		}
		
		function setUserAccount(btn){
			var data={};
			var list=new Array();
			$.each($('user'),function(i,obj){
				var evTaskUsers=new Object();
				evTaskUsers.evTaskId=evTaskId;
				evTaskUsers.userId=$(obj).attr("id");
				evTaskUsers.userName=$(obj).html();
				list.push(evTaskUsers);
			});
			data.list=list;
			console.log(data);
			$.ajax({
				type : "post",
				url : contextPath
						+ "/evTaskUsers/insertEvTaskUsers.do",
				data : {
					jsons :JSON.stringify(data)
				},
				beforeSend : function(XMLHttpRequest) {
					$(btn).attr('disabled',"true");
				},
				success : function(rst, textStatus) {
					$(btn).removeAttr("disabled");
					if (rst) {
						bootbox.dialog({
							title:'系统提示',
							message:rst.errorMessage,
							detail:rst.detail,
							buttons: 			
							{
								"success" :
								 {
									"label" : "<i class='ace-icon fa fa-check'></i>确定",
									"className" : "btn-sm btn-success",
									"callback": function() {
										//关闭窗口
										parent.reloadGrid();
										parent.removePanel();
									}
								}
							}
						});
				
					}
				},
				error : function() {
					$(btn).removeAttr("disabled");
				}
			});
		}
		
	</script> 


	<script
		src="${pageContext.request.contextPath}/content/js/service/evTask/controller-add.js"></script>

</body>
</html>