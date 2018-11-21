
<%@page import="com.huacainfo.ace.common.tools.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String noticeId = request.getParameter("noticeId");
	String taskId = request.getParameter("taskId");
	request.setAttribute("noticeId", noticeId);
	request.setAttribute("taskId", taskId);
%>
<%
session.setAttribute("portalPath", "/portal");
%>
<script type="text/javascript">
    var contextPath = '${pageContext.request.contextPath}';
    var portalPath = '${portalPath}';
    var version = '${cfg.version}';
    var fastdfs_server = '${cfg.fastdfs_server}';
    var activeSyId = '${SESSION_USERPROP_KEY.activeSyId}';
    var portalType = '${SESSION_USERPROP_KEY.cfg.portalType}';
    var default_page_list=[${cfg.default_page_list}];
</script>
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

<script src="${portalPath}/content/common/assets/global/plugins/jquery.min.js?v=${cfg.version}" type="text/javascript"></script>
<link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/bootstrap/css/bootstrap.min.css?v=${cfg.version}" />
<body>
	<div class="page-content">
		<div align="center">
			<h3 id="notice-title"></h3>

			  <span id="notice-department"></span>  <span id="notice-publisher"></span>  <span id="notice-publishTime"></span>
		</div>
		<hr align="center" width="100%" size="1" noshade style="color:eeeeee">

		<div id="notice-content"></div>

		<hr align="center" width="100%" size="1" noshade style="color:eeeeee">

		<div id="filelist-history"></div>

	</div>










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
										rst.value.createTime);
								$("#notice-department").html(
										rst.value.departmentName);

							} else {

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
								html.push('附件列表');
								$
										.each(
												rst.value,
												function(n, file) {

													html
															.push('<div id="' + file.fileUrl + '"><a href="'
																	+ fastdfs_server+file.fileUrl
																	+ '" target="_blank">'
																	+ file.fileName
																	+ '</a> ('
																	+ parseInt(file.fileSize / 1024)
																	+ 'kb) <b></b></div>');
												});

								$('#filelist-history').html(html.join(''));
							} else {

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

				},
				error : function() {
				}
			});
		}
	</script>
</body>
</html>