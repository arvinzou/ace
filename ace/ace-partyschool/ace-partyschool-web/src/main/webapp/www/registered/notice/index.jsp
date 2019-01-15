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
	</head>
	<body>
		<div class="index" id="list">

		</div>

		<script id="list-tpl" type="text/template">
			<div class="title-box">
				<span class="title">最新消息</span>
				<span class="num">\${data.newNotice.length}</span>
			</div>
			<div class="box">
				{@if data.newNotice.length > 0}
				{@each data.newNotice as item01, index01}
				<div class="content">
					<div class="item">
						<div class="item-left">
							<img src="img/icon_message.png" class="message-img" />
						</div>
						<div class="item-right">
							<div class="notice-title">\${item01.title}</div>
							<div class="notice-detail">
								<span class="time">2018-11-20 13:20:15</span>
								<span class="detail" onclick="findDetail('\${item01.id}');">查看详情</span>
							</div>
						</div>
					</div>
				</div>
				{@/each}
				{@else}
				<div class="content">
					<div class="item blank">暂无最新通知</div>
				</div>
				{@/if}
			</div>
			<div class="title-box">
				<span class="title">历史消息</span>
			</div>
			<div class="box">
				{@if data.oldNotice.length > 0}
				{@each data.oldNotice as item02, index02}
				<div class="content">
					<div class="item">
						<div class="item-left">
							<img src="img/icon_message.png" class="message-img" />
						</div>
						<div class="item-right">
							<div class="notice-title">\${item02.title}</div>
							<div class="notice-detail">
								<span class="time">2018-11-20 13:20:15</span>
								<span class="detail" onclick="findDetail('\${item01.id}');">查看详情</span>
							</div>
						</div>
					</div>
				</div>
				{@/each}
				{@else}
				<div class="content">
					<div class="item blank">暂无最新通知</div>
				</div>
				{@/if}
			</div>
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
		<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
		<script type="text/javascript" src="js/index.js"></script>
	</body>
</html>
