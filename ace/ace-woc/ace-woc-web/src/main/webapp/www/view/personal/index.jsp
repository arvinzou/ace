<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>个人中心</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/personal/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/personal/js/init-rem.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/www/personal/css/index.css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/personal/js/index.js"></script>
	</head>
	<body>
		<div class="index">
			<div class="main">
				<div class="box">
					<div class="home_top">
						<div class="user_info">
							<div class="user_img">
								<img src="${pageContext.request.contextPath}/www/personal/img/header_img.png" />
							</div>
							<div class="user_name">
								小武先森
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="badding" >
				<div class="badding_info">还未绑定任何车辆</div>
				<div class="badding_opt">
					<button id="bandBtn" onclick="location.href='${pageContext.request.contextPath}/www/view/personal/banding.html'">立即绑定</button>
				</div>
			</div>
			<div class="cancle" hidden="true">
				<div class="carInfo">
					<span>湘N88888</span>
					<button id="cancelBand">取消绑定</button>
				</div>
				<div class="info">已绑定车辆</div>
			</div>
			<div class="footer">
				绑定后如有违章会实时推送相关违章详情
			</div>
		</div>
	</body>
</html>
