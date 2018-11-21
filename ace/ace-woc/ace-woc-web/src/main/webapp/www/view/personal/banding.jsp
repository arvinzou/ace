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
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/personal/js/personal.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/www/personal/css/personal.css"/>
	</head>
	<body>
		<div class="index">
			<div class="main">
				<div class="box">
					<div class="home_top">
					</div>
				</div>
			</div>
			<div class="badding">
				<div class="phoneNum">
					<input type="text" name="mobile" id="mobile" placeholder="请输入手机号码" onfocus="this.placeholder=''" onblur="this.placeholder='请输入手机号码'"/>
					<button id="getNumBtn" onclick="getNumFun();">获取验证码</button>
				</div>
				<div class="code">
					<input type="text" name="codeNum" id="codeNum" placeholder="请输入验证码" onfocus="this.placeholder=''" onblur="this.placeholder='请输入验证码'"/>
				</div>
				<div class="option">
					<button id="band" name="band" onclick="bandFun();">立即绑定</button>
				</div>
			</div>
			<div class="footer">
				绑定后如有违章会实时推送相关违章详情
			</div>
		</div>
	</body>
</html>