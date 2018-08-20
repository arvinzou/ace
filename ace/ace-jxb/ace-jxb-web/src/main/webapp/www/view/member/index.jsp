<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>个人中心</title>
		<jsp:include page="../../../dynamic/common/base.jsp" />
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<script type="text/javascript" src="../../common/js/loader.js"></script>
		<script type="text/javascript" src="js/act.js"></script>
	</head>
	<body>
		<div class="box">
			<div class="row" id="userInfo" style="height: 2.533333rem;">

			</div>
		</div>
		<div class="box">
			<div class="row menu-li" onclick="showConsultOrder();">
				<div class="col-xs-10 col-sm-10"><p class="menu_title">咨询订单</p></div>
				<div class="col-xs-2 col-sm-2"><img class="next" src="img/next.png"/></div>
			</div>
			<div class="row menu-li" onclick="showCourseOrder();">
				<div class="col-xs-10 col-sm-10"><p class="menu_title">课程订单</p></div>
				<div class="col-xs-2 col-sm-2"><img class="next" src="img/next.png"/></div>
			</div>
			<div class="row menu-li">
				<div class="col-xs-10 col-sm-10"><p class="menu_title">测试记录</p></div>
				<div class="col-xs-2 col-sm-2"><img class="next" src="img/next.png"/></div>
			</div>
			<div class="row menu-li">
				<a href="tel:0736-7083862">
					<div class="col-xs-10 col-sm-10"><p class="menu_title">联系客服</p></div>
					<div class="col-xs-2 col-sm-2"><img class="next" src="img/next.png"/></div>
				</a>
			</div>
		</div>

	<script id="userInfoTemp" type="text/template">
		<div class="col-xs-4 col-sm-4" style="height: 100%;">
			<img src="\${data.headimgurl}" class="head_img" />
		</div>
		<div class="col-xs-8 col-sm-8" style="height: 100%;padding-left: 0!important;">
			<p class="nickName">\${data.nickName}</p>
		</div>
	</script>
	</body>
</html>
