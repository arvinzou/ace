<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>通讯录</title>
		<jsp:include page="../../common/common.jsp"/>
		<link rel="stylesheet" type="text/css" href="css/index.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
		<script type="text/javascript" src="js/index.js"></script>
	</head>

	<body>
		<div class="index">
			<div class="box">
				<div class="search">
					<input class="serach-input" type="text" id="search" onfocus="focusInput();" onblur="blurInput();" />
					<img id="search-icon" src="img/icon_search.png" class="icon-search" onclick="searchIconClick();" />
					<span id="search-title" class="search-title" onclick="searchIconClick();">搜索</span>
				</div>
			</div>
			<div class="box">
				<li class="list">
					<div class="row rb">
						<div class="title01">中共常德市委党校全体教职工</div>
						<div class="option" onclick="dorpAndDown(this,'oneMenu')"><img src="img/icon_up.png" class="icon-up" name="up"/></div>
					</div>
					<ul id="oneMenu" style="display: none;">
						<div class="row rb">
							<div class="title02">第77期县级处干班第一小组(10)</div>
							<div class="option" onclick="dorpAndDown(this,'secondMenu')"><img src="img/icon_up.png" class="icon-up" name="up"/></div>
						</div>
						<ul id="secondMenu" style="display: none;">
							<div class="row rb">
								<div class="item-left">
									<img src="img/default_header.png" class="header" />
									<span class="username">彭磊</span>
								</div>
								<div class="item-right">
									<span class="mobile">17888888888</span>
								</div>
							</div>
							<div class="row rb">
								<div class="item-left">
									<img src="img/default_header.png" class="header" />
									<span class="username">张三</span>
								</div>
								<div class="item-right">
									<span class="mobile">17888888888</span>
								</div>
							</div>
							<div class="row rb">
								<div class="item-left">
									<img src="img/default_header.png" class="header" />
									<span class="username">李四四</span>
								</div>
								<div class="item-right">
									<span class="mobile">17888888888</span>
								</div>
							</div>
							<div class="row rb">
								<div class="item-left">
									<img src="img/default_header.png" class="header" />
									<span class="username">王武龙</span>
								</div>
								<div class="item-right">
									<span class="mobile">17888888888</span>
								</div>
							</div>
						</ul>
					</ul>
				</li>
			</div>
			<div class="title">未分组(10)</div>
			<div class="box">
				<div class="row rb">
					<div class="item-left">
						<img src="img/default_header.png" class="header" />
						<span class="username">彭磊</span>
					</div>
					<div class="item-right">
						<span class="mobile">17888888888</span>
					</div>
				</div>
				<div class="row rb">
					<div class="item-left">
						<img src="img/default_header.png" class="header" />
						<span class="username">张三</span>
					</div>
					<div class="item-right">
						<span class="mobile">17888888888</span>
					</div>
				</div>
				<div class="row rb">
					<div class="item-left">
						<img src="img/default_header.png" class="header" />
						<span class="username">李四四</span>
					</div>
					<div class="item-right">
						<span class="mobile">17888888888</span>
					</div>
				</div>
				<div class="row rb">
					<div class="item-left">
						<img src="img/default_header.png" class="header" />
						<span class="username">王武龙</span>
					</div>
					<div class="item-right">
						<span class="mobile">17888888888</span>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>