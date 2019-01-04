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
		<div class="identity">
			<img src="img/student.png" id="student" onclick="changeIdentity('student');"/>
		</div>
		<div class="identity">
			<img src="img/teacher.png" id="teacher" onclick="changeIdentity('teacher');"/>
		</div>
		<div class="footer" id="nextStep">
			<img src="img/next.png" id="unactive"/>
			<img src="img/next-active.png" onclick="nextStep();" style="display: none;" id="active"/>
		</div>
	</div>
	</body>

	<script type="text/javascript" src="../common/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="../common/js/init-rem.js"></script>
	<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
</html>
