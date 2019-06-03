<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>就餐记录</title>
		<jsp:include page="../../common/common.jsp"/>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<link rel="stylesheet" type="text/css" href="js/Mdate/needcss/Mdate.css"/>
	</head>
	<body>
		<div class="index">
			<div class="banner" id="balance">

			</div>
			<div class="title-box">
				<div class="title">消费记录</div>
				<div class="date-box">
					<input type="text" id="dateSelectorOne" placeholder="请选择" readonly disabled/>
					<img src="img/icon-drop-down.png" class="icon-date-picker" id="dateShowBtn">
				</div>
			</div>
			<div class="list" id="list">

			</div>
		</div>

		<script id="list-tpl" type="text/template">
			{@each data as item, index}
			<div class="item">
				<div class="icon"><img src="img/icon_consume.png"/></div>
				<div class="content">
					<div class="type">\${item.bisCodeName}</div>
					<div class="date">\${item.dealTime}</div>
				</div>
				<div class="amount">-\${item.consumption}</div>
			</div>
			{@/each}
		</script>
		<script id="balance-tpl" type="text/template">
			<div class="cardNo">卡号:\${data.lCardNo}</div>
			<div class="balance">\${data.curBalance?data.curBalance:0}</div>
			<div class="balance-title">一卡通余额(元)</div>
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
		<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
		<script type="text/javascript" src="js/Mdate/iScroll.js"></script>
		<script type="text/javascript" src="js/Mdate/Mdate.js"></script>
		<script type="text/javascript" src="js/teacher.js"></script>
	</body>
</html>
