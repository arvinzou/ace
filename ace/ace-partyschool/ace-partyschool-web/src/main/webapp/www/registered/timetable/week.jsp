<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>周课表</title>
		<jsp:include page="../../common/common.jsp"/>
		<link rel="stylesheet" type="text/css" href="css/week.css"/>
		<link rel="stylesheet" type="text/css" href="css/calendar.css"/>
	</head>
	<body>
		<div class="index">
			<div class="banner">
				<img src="img/banner.png" />
			</div>
			<div class="box">
				<div class="bd_w jcalendar_week" id="jcalendar_week"></div>
			</div>
			<div id="courseList" class="mt">

			</div>
		</div>

		<script id="list-tpl" type="text/template">
			{@each data as item, index}
			{@if item.courseIndex == "am"}
			<div class="course-box">
				<div class="course">
					<div class="course-left">
						<div class="course-title">\${item.course.name}</div>
						<div class="course-teacher">
							<img src="img/icon-teacher.png" />
							<span>\${item.course.teacherNames}</span>
						</div>
					</div>
					<div class="course-right">
						<span class="morning">上午</span>
					</div>
				</div>
			</div>
			{@else}
			<div class="course-box">
				<div class="course">
					<div class="course-left">
						<div class="course-title">\${item.course.name}</div>
						<div class="course-teacher">
							<img src="img/icon-teacher.png" />
							<span>\${item.course.teacherNames}</span>
						</div>
					</div>
					<div class="course-right">
						<span class="morning">下午</span>
					</div>
				</div>
			</div>
			{@/if}
			{@/each}
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
		<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/week.js"></script>
	</body>
</html>
