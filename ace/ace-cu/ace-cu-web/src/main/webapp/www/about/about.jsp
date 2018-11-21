<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>关于我们</title>
		<script type="text/javascript" src="/cu/www/common/js/jquery-3.2.1.min.js?v=<%=System.currentTimeMillis()%>"></script>
		<script type="text/javascript" src="/cu/www/common/js/init-rem.js?v=<%=System.currentTimeMillis()%>"></script>
		<link rel="stylesheet" type="text/css" href="css/about.css?v=<%=System.currentTimeMillis()%>"/>
		<style>
			.about_banner{
				width: 100%;
				height: 4.5rem;
			}
		
			.line{
				width: 90%;
				height: 1rem;
				line-height: 1rem;
				border-bottom: 1px solid #E4E6E9;
				margin-left: 5%;
				margin-right: 5%;
			}
			.about_content{
				font-size: 0.4rem;
				color: #333333;
				font-weight: 500;
			}
			.line_left{
				width: 80%;
				height: 100%;
				float: left;
				text-align: left;
			}
			.line_right{
				width: 20%;
				height: 100%;
				float: left;
				text-align: right;
			}
			.copyright{
				width: 100%;
				height: 1.5rem;
				line-height: 1.5rem;
				text-align: center;
				font-size: 0.35rem;
				color: #999999;
				background: #F7F9FC;
				position: fixed;
				bottom: 0;
			}
			.aboutus{
				width: 90%;
				height: 1.5rem;
				line-height: 1.5rem;
				margin: 0 auto;
				color: #EA4436;
				font-size: 0.5rem;
				font-weight:500;
				text-align: center;
			}
			.aboutus_title{
				width: 30%;
				height: 100%;
				display: block;
				float: left;
			}
			.liner{
				width: 35%;
				height: 1px;
				background: #EA4436;
				display: block;
				float: left;
				margin-top: 0.75rem;
			}
		</style>
	</head>
	<body>
		<div class="about_box">
			<div class="about_banner">
				<!--<img src="img/logo.png" style="width: 1.5rem; height: 1.5rem;"/>
				<p>常德市慈善总会</p>-->
				<img src="img/banner.png" style="width: 100%;height: 100%;" />
			</div>
			<div class="aboutus">
				<span class="liner"></span>
				<span class="aboutus_title">关于我们</span>
				<span class="liner"></span>
				<div style="clear: both;"></div>
			</div>
			<div class="about_content">
				<div class="line" onclick="window.location.href='agency.jsp'">
					<span class="line_left">机构简介</span>
					<span class="line_right"><img src="img/back.png" style="width: 0.18rem;height: 0.29rem;padding-top: 0.23rem;"/></span>
				</div>
				<!--<div class="line">
					<span class="line_left">个人简介</span>
					<span class="line_right"><img src="img/back.png" style="width: 0.3rem;height: 0.55rem;padding-top: 0.23rem;"/></span>
				</div>-->
				<div class="line" onclick="window.location.href='culture.jsp'">
					<span class="line_left">慈善文化</span>
					<span class="line_right"><img src="img/back.png" style="width: 0.18rem;height: 0.29rem;padding-top: 0.23rem;"/></span>
				</div>
				<div class="line" onclick="window.location.href='rules.jsp'">
					<span class="line_left">规章制度</span>
					<span class="line_right"><img src="img/back.png" style="width: 0.18rem;height: 0.29rem;padding-top: 0.23rem;"/></span>
				</div>
				<div class="line" onclick="window.location.href='aboutus.jsp'">
					<span class="line_left">联系方式</span>
					<span class="line_right"><img src="img/back.png" style="width: 0.18rem;height: 0.29rem;padding-top: 0.23rem;"/></span>
				</div>
			</div>
			<div class="logo_panel">
				<img src="img/logo.jpg"/>
			</div>
			<div class="copyright">
				<p>常德市慈善总会版权所有</p>
			</div>
		</div>
	</body>
</html>
