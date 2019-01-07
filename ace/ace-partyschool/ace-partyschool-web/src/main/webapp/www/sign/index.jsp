<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>注册</title>
		<jsp:include page="../common/common.jsp"/>
		<link rel="stylesheet" type="text/css" href="css/index.css"/>
	</head>
	<body>
	<div class="index">
		<div class="title">请选择您的身份</div>
		<div class="identity" onclick="changeIdentity('student');">
			<img src="img/student.png" id="student"/>
			<span class="identity-title">我是学员</span>
		</div>
		<div class="identity" onclick="changeIdentity('teacher');">
			<img src="img/teacher.png" id="teacher" />
			<span class="identity-title">我是教职工</span>
		</div>
	</div>
	</body>

	<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
	<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
</html>
