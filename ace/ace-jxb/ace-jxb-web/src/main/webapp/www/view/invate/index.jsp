<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>邀请加入</title>
		<jsp:include page="../../../dynamic/common/base.jsp" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<script type="text/javascript" src="../../common/js/loader.js"></script>
		<script type="text/javascript" src="js/invate.js"></script>
	</head>
	<body>
		<div class="box">
			<div class="main_box">

			</div>
		</div>

	<script id="codeTemp" type="text/template">
		<div class="invate_code"><img src="\${data.qrcode.qrcodeUrl}"/></div>
		<div class="footer">
			<p class="roomname">\${data.studio.name}</p>
			<p class="tips">扫描上方二维码注册咨询师加入TA的工作室</p>
		</div>
	</script>
	</body>
</html>
