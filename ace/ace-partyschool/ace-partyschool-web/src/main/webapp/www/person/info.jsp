<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>个人信息</title>
		<jsp:include page="../common/common.jsp"/>
		<link rel="stylesheet" type="text/css" href="css/info.css"/>
	</head>
	<body>
		<div class="index">
			<div class="box">
				<div class="info">
					<div class="title">姓名</div>
					<div class="content">王鱼鱼 </div>
				</div>
				<div class="info">
					<div class="title">身份证号</div>
					<div class="content">431**************X</div>
				</div>
				<div class="info">
					<div class="title">手机号</div>
					<div class="content">17688886666</div>
				</div>
				<div class="info">
					<div class="title">性别</div>
					<div class="content">女生</div>
				</div>
				<div class="info">
					<div class="title">政治面貌</div>
					<div class="content">党员</div>
				</div>
				<div class="info">
					<div class="title">单位名称</div>
					<div class="content">湖南华彩伟业网络科技有限公司</div>
				</div>
				<div class="info">
					<div class="title">单位职务</div>
					<div class="content">UI设计师</div>
				</div>
			</div>
			<div class="box">
				<div class="info">
					<div class="title">密码设置</div>
					<div class="content"><span>修改密码</span><img src="img/icon_select.png"/></div>
				</div>
			</div>
			<div class="box">
				<div class="exit">
					<span>退出登录</span>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="../common/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="../common/js/init-rem.js"></script>
	<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
</html>
