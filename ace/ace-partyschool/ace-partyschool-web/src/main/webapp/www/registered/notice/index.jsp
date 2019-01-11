<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>通知公告</title>
		<jsp:include page="../../common/common.jsp"/>
		<link rel="stylesheet" type="text/css" href="css/index.css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
	</head>
	<body>
		<div class="index">
			<div class="title-box">
				<span class="title">最新消息</span>
				<span class="num">12</span>
			</div>
			<div class="box">
				<div class="content">
					<div class="item">
						<div class="item-left">
							<img src="img/icon_message.png" class="message-img" />
						</div>
						<div class="item-right">
							<div class="notice-title">新书推荐（2018年11月下）</div>
							<div class="notice-detail">
								<span class="time">2018-11-20 13:20:15</span>
								<span class="detail">查看详情</span>
							</div>
						</div>
					</div>
				</div>
				<div class="content">
					<div class="item">
						<div class="item-left">
							<img src="img/icon_message.png" class="message-img" />
						</div>
						<div class="item-right">
							<div class="notice-title">2018年度整体支出绩效评哈哈哈哈哈哈哈哈哈哈</div>
							<div class="notice-detail">
								<span class="time">2018-11-20 13:20:15</span>
								<span class="detail">查看详情</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="title-box">
				<span class="title">历史消息</span>
			</div>
			<div class="box">
				<div class="content">
					<div class="item blank">暂无最新通知</div>
				</div>
			</div>
			
		</div>
	</body>
</html>
