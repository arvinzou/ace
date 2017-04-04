
<%@page import="com.huacainfo.ace.common.tools.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String noticeId = request.getParameter("noticeId");
	String taskId = request.getParameter("taskId");
	request.setAttribute("noticeId", noticeId);
	request.setAttribute("taskId", taskId);
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>通知</title>
</head>
<script type="text/javascript">
	var noticeId = '${noticeId}';
	var taskId = '${taskId}';
</script>
<jsp:include page="../../common/common.jsp" />


<body>
	<div class="page-content">
		<div align="center">
			<h3 id="notice-title"></h3>

			<span id="notice-department"></span><span id="notice-publisher"></span> <span id="notice-publishTime"></span>
		</div>
		<hr align="center" width="100%" size="1" noshade>
		<div id="notice-content"></div>
		<hr align="center" width="100%" size="1" noshade>
		<div id="filelist-history"></div>

	</div>








	<jsp:include page="../../common/footer-1.jsp" />

	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
		jQuery(function($) {
			loadAttach(noticeId);
			loadNotice(noticeId);
			//complectTask(taskId);

		});
		function loadNotice(id) {
			$
					.ajax({
						type : "get",
						url : contextPath
								+ "/notice/selectNoticeByPrimaryKey.do",
						data : {
							id : id
						},
						beforeSend : function(XMLHttpRequest) {
						},
						success : function(rst, textStatus) {
							if (rst && rst.state) {
								$("#notice-content").html(rst.value.content);
								$("#notice-title").html(rst.value.title);
								$("#notice-publisher").html(
										rst.value.name);
								$("#notice-publishTime").html(
										rst.value.publishTime);
								$("#notice-department").html(
										rst.value.departmentName);

							} else {
								bootbox
										.dialog({
											title : '系统提示',
											message : rst.errorMessage,
											detail : rst.detail,
											buttons : {
												"success" : {
													"label" : "<i class='ace-icon fa fa-check'></i>确定",
													"className" : "btn-sm btn-success",
													"callback" : function() {
														//$( this ).dialog( "close" );
													}
												}
											}
										});
							}
						},
						complete : function(XMLHttpRequest, textStatus) {

						},
						error : function() {
						}
					});
		}
		function loadAttach(noticeId) {
			$
					.ajax({
						type : "get",
						url : contextPath + "/attach/findAttachList.do",
						data : {
							noticeId : noticeId
						},
						beforeSend : function(XMLHttpRequest) {
						},
						success : function(rst, textStatus) {
							if (rst && rst.state) {
								var html = [];
								html.push('<h3>附件列表</h3>');
								$
										.each(
												rst.value,
												function(n, file) {

													html
															.push('<div id="' + file.fileUrl + '"><a style="font-size:25px" href="'
																	+ fastdfs_server+file.fileUrl
																	+ '" target="_blank">'
																	+ file.fileName
																	+ '</a> ('
																	+ parseInt(file.fileSize / 1024)
																	+ 'kb) <b></b></div>');
												});
								//document.getElementById('filelist').innerHTML=html.join('');
								$('#filelist-history').html(html.join(''));
							} else {
								bootbox
										.dialog({
											title : '系统提示',
											message : rst.errorMessage,
											detail : rst.detail,
											buttons : {
												"success" : {
													"label" : "<i class='ace-icon fa fa-check'></i>确定",
													"className" : "btn-sm btn-success",
													"callback" : function() {
														//$( this ).dialog( "close" );
													}
												}
											}
										});
							}
						},
						complete : function(XMLHttpRequest, textStatus) {

						},
						error : function() {
						}
					});
		}
		function complectTask(id) {
			$.ajax({
				type : "get",
				url : contextPath + "/task/deleteTaskByTaskId.do",
				data : {
					id : id
				},
				beforeSend : function(XMLHttpRequest) {
				},
				success : function(rst, textStatus) {
				},
				complete : function(XMLHttpRequest, textStatus) {
					parent.initTaskList();
				},
				error : function() {
				}
			});
		}
	</script>
</body>
</html>