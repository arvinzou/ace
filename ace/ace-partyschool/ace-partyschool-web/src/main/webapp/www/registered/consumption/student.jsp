<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>消费信息</title>
		<jsp:include page="../../common/common.jsp"/>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
	</head>
	<body>
		<div class="index">
			<div class="banner">
				<div class="cardNo">卡号:Z0000768</div>
				<div class="balance">36</div>
				<div class="balance-title">累计刷卡次数</div>
			</div>
			<div class="title">刷卡记录</div>
			<div class="list">
				<div class="item">
					<div class="icon"><img src="img/icon_consume.png"/></div>
					<div class="record">2018-11-20 15:30:02</div>
				</div>
				<div class="item">
					<div class="icon"><img src="img/icon_consume.png"/></div>
					<div class="record">2018-11-20 15:30:02</div>
				</div>
				<div class="item">
					<div class="icon"><img src="img/icon_consume.png"/></div>
					<div class="record">2018-11-20 15:30:02</div>
				</div>
				<div class="item">
					<div class="icon"><img src="img/icon_consume.png"/></div>
					<div class="record">2018-11-20 15:30:02</div>
				</div>
			</div>
		</div>

		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
	</body>
</html>
