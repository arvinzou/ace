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
			<div class="header" id="userInfo">

			</div>
			<div class="mapBox" id="mapBox">

			</div>
			<div class="timeList">
				<div class="time-title" id="count"></div>
				<div class="timeList-inner">
					<ul class="layui-timeline" id="recordList">

					</ul>
				</div>
			</div>
		</div>

		<script type="text/template" id="user-tpl">
			<div class="headerImg">
				{@if data.isBindWx == "1"}
				<img src="\${data.avatarUrl}"/>
				{@else}
				<img src="img/default_header.png"/>
				{@/if}
			</div>
			<div class="username">
				{@if data.regType == 'student'}
				<span>\${data.student.name}</span>
				{@else}
				<span>\${data.teacher.name}</span>
				{@/if}
			</div>
			<div class="currentDte">\${data.currentDate}</div>
		</script>

		<script type="text/template" id="record-tpl">
			<li class="layui-timeline-item">
				<i class="layui-icon layui-timeline-axis"></i>
				<div class="layui-timeline-content layui-text">
					<h3 class="layui-timeline-title">上午</h3>
					{@if data.am.length > 0}
						{@each data.am as am, index01}
							<p>上午打卡\${am.attTime}</p>
						{@/each}
					{@/if}
					<div id="amBtn" >

					</div>
				</div>
			</li>
			<li class="layui-timeline-item">
				<i class="layui-icon layui-timeline-axis"></i>
				<div class="layui-timeline-content layui-text">
					<h3 class="layui-timeline-title">下午</h3>
					{@if data.pm.length > 0}
					{@each data.pm as pm, index02}
					<p>下午打卡\${pm.attTime}</p>
					{@/each}
					{@/if}
					<div id="pmBtn">

					</div>
				</div>
			</li>
			<li class="layui-timeline-item">
				<i class="layui-icon layui-timeline-axis"></i>
				<div class="layui-timeline-content layui-text">
					<h3 class="layui-timeline-title">晚上</h3>
					{@if data.night[0]}
					<p>晚上签到\${data.night[0].attTime}</p>
					{@/if}
					<div id="nightBtn">

					</div>
				</div>
			</li>
		</script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
		<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jweixin-1.2.0.js"></script>
		<script charset="utf-8" src="https://map.qq.com/api/js?v=2.exp&key=BYLBZ-HDYW4-WQNUU-XIH6N-XDPX7-44FGT"></script>
		<script type="text/javascript" src="js/layui/layui.js"></script>
		<script type="text/javascript" src="js/index.js"></script>
	</body>

</html>