<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>学员须知</title>
		<jsp:include page="../../common/common.jsp"/>
		<link rel="stylesheet" type="text/css" href="css/index.css"/>

	</head>
	<body>
		<div class="index" id="classNotice">

		</div>

		<script id="class-tpl" type="text/template">
			<div class="banner"><img src="\${data.list.photoUrl}"/></div>
			<div class="container">
				<div class="title">\${data.list.name}</div>
				<div class="class-status">
					{@if data.list.status == 1}
					<span class="onstatus">授课中</span>
					{@else}
					<span class="end">已结业</span>
					{@/if}
				</div>
			</div>
			<div class="box pm">
				<div class="title02 br">学员须知</div>
				<div class="notice-link">
					<a href="\${data.list.fileUrl}">\${data.list.fileName}</a>
				</div>
			</div>
			<div class="box">
				<div class="title02 br">班级位置</div>
				<div class="content-box br">
					<div class="content-title">班级教室</div>
					<div class="content bd">\${data.list.classroom.name}</div>
				</div>
				<div class="content-box br">
					<div class="content-title">具体位置</div>
					<div class="content bd">\${data.list.classroom.introduce}</div>
				</div>
			</div>
			<div class="box">
				<div class="title02 br">班级成员</div>
				<div class="content-box br">
					<div class="content-title">班主任</div>
					<div class="content">
						<span class="name bj bd">\${data.list.teacher.name}</span>
						<span class="mobile bj bd">\${data.list.teacher.mobile}</span>
						<a href="tel:\${data.list.teacher.mobile}"><img class="icon-phone" src="img/icon_phone.png"/></a>
					</div>
				</div>
				<div class="content-box br">
					<div class="content-title">跟班老师</div>
					<div class="content">
						<span class="name bj bd">\${data.list.clsteacher.name}</span>
						<span class="mobile bj bd">\${data.list.clsteacher.mobile}</span>
						<a href="tel:\${data.list.clsteacher.mobile}"><img class="icon-phone" src="img/icon_phone.png"/></a>
					</div>
				</div>
				<div class="content-box br">
					<div class="content-title">跟班干部</div>
					<div class="content">
						<span class="name bj bd">\${data.list.student.name}</span>
						<span class="mobile bj bd">\${data.list.student.mobile}</span>
						<a href="tel:\${data.list.student.mobile}"><img class="icon-phone" src="img/icon_phone.png"/></a>
					</div>
				</div>
				<div class="content-box br">
					<div class="content-title">班级学员</div>
					<div class="content">
						<div class="mail">查看班级通讯录(\${data.count}人)</div>
						<div class="icon-mail" onclick="toMailList();"><img src="img/icon_select.png"/></div>
					</div>
				</div>
			</div>
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
		<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
		<script type="text/javascript" src="js/index.js"></script>
	</body>
</html>
