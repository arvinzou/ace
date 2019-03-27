<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>借阅记录</title>
		<jsp:include page="../../common/common.jsp"/>
		<link rel="stylesheet" type="text/css" href="css/index.css"/>
	</head>
	<body>
		<div class="index">
			<!--未归还-->
			<div class="unreturn" id="unreturn">

			</div>
			<!--已归还-->
			<div class="return" id="return">

			</div>
			<div class="defaultTips" style="display: none;">
				<img src="img/default.png">
				<p>暂无借阅记录哦~</p>
			</div>
		</div>

		<!--未归还列表-->
		<script type="text/template" id="ulist-tpl">
			{@each data as item, index}
			{@if index == 0}
			<div class="uitem md">
				<div class="inner-middle">
					<div class="title">《\${item.Title}》</div>
					<div class="detail">
						<div class="icon-box"><img src="img/icon_time.png" class="icon-time" /></div>
						<div class="text-box"><span class="detail-title">请于</span></div>
						<div class="text-box"><span class="light-title">\${item.dueToDate.substring(0,10)}</span></div>
						<div class="text-box"><span class="detail-title">前还书</span></div>
					</div>
				</div>
			</div>
			{@else}
			<div class="uitem">
				<div class="inner-middle">
					<div class="title">《\${item.Title}》</div>
					<div class="detail">
						<div class="icon-box"><img src="img/icon_time.png" class="icon-time" /></div>
						<div class="text-box"><span class="detail-title">请于</span></div>
						<div class="text-box"><span class="light-title">\${item.dueToDate.substring(0,10)}</span></div>
						<div class="text-box"><span class="detail-title">前还书</span></div>
					</div>
				</div>
			</div>
			{@/if}
			{@/each}
		</script>

		<script type="text/template" id="list-tpl">
			{@each data as item, index}
			<div class="item">
				<div class="inner-middle">
					<div class="title">《\${item.Title}》</div>
					<div class="detail">
						<div class="icon-box"><img src="img/icon_time.png" class="icon-time" /></div>
						<div class="text-box"><span class="detail-title">\${item.BorrowDate}借阅</span></div>
					</div>
				</div>
			</div>
			{@/each}
		</script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
		<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
		<script type="text/javascript" src="js/index.js"></script>
	</body>
</html>
