<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>签到</title>
		<jsp:include page="../../common/common.jsp"/>
		<link rel="stylesheet" type="text/css" href="css/index.css" />
		<link rel="stylesheet" type="text/css" href="js/layui/css/layui.css" />
	</head>

	<body>
		<div class="index">
			<div class="header">
				<div class="headerImg"><img src="img/default_header.png" /></div>
				<div class="username">许黎明</div>
				<div class="currentDte">2019-02-26</div>
			</div>
			<div class="mapBox" id="mapBox">

			</div>
			<div class="timeList">
				<div class="time-title">今日已签到0/5</div>
				<div class="timeList-inner">
					<ul class="layui-timeline">
						<li class="layui-timeline-item">
							<i class="layui-icon layui-timeline-axis"></i>
							<div class="layui-timeline-content layui-text">
								<h3 class="layui-timeline-title">上午</h3>
								<p>上午签到08:00-08:30</p>
								<p>上午签退08:00-08:30</p>
								<div class="qiandao">
									<div class="cell">
										<p class="qtitle">上午签到</p>
										<p class="qtime">08:00</p>
									</div>
								</div>
							</div>
						</li>
						<li class="layui-timeline-item">
							<i class="layui-icon layui-timeline-axis"></i>
							<div class="layui-timeline-content layui-text">
								<h3 class="layui-timeline-title">下午</h3>
								<p>下午签到08:00-08:30</p>
								<p>下午签退08:00-08:30</p>
								<div class="qiandao">
									<div class="cell">
										<p class="qtitle">下午签到</p>
										<p class="qtime">08:00</p>
									</div>
								</div>
							</div>
						</li>
						<li class="layui-timeline-item">
							<i class="layui-icon layui-timeline-axis"></i>
							<div class="layui-timeline-content layui-text">
								<h3 class="layui-timeline-title">晚上</h3>
								<p>晚上签到08:00-08:30</p>
								<div class="qiandao">
									<div class="cell">
										<p class="qtitle">晚上签到</p>
										<p class="qtime">08:00</p>
									</div>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>

		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
		<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
		<script charset="utf-8" src="https://map.qq.com/api/js?v=2.exp&key=BYLBZ-HDYW4-WQNUU-XIH6N-XDPX7-44FGT"></script>
		<script type="text/javascript" src="js/layui/layui.js"></script>
		<script type="text/javascript" src="js/index.js"></script>
	</body>

</html>