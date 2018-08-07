<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>工作室邀请</title>
		<jsp:include page="../../../dynamic/common/base.jsp" />
		<script type="text/javascript" src="../../common/js/loader.js"></script>
		<script type="text/javascript" src="js/invate.js"></script>
		<style>
			.box{
				width: 100%;
			}
			.box img{
				width: 100%;
				height: 100%;
			}
		</style>
	</head>
	<body>
		<div class="box">
		</div>

	<script id="codeTemp" type="text/template">
		<img src="\${data}"/>
	</script>
	</body>
</html>
