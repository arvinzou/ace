<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title>日行一善</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<link rel="stylesheet" type="text/css" href="css/barrager.css"/>
		<link rel="stylesheet" type="text/css" href="css/index.css" />
		<script src="js/flexible.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body>
		<div class="content">
			<div class="menu">
				<ul class="menuBtn">
					<li onclick="window.location.href='mine.jsp?type=1'">
						<img src="img/icon1.png" alt=""/>
						<p>积分:11</p>
					</li>
					<li onclick="window.location.href='mine.jsp?type=2'">
						<img src="img/icon2.png" alt=""/>
						<p>爱的奉献</p>
					</li>
					<li>
						<img src="img/icon3.png" alt=""/>
						<p>积分排行</p>
					</li>
					<li>
						<img src="img/icon4.png" alt=""/>
						<p>年度表彰</p>
					</li>
				</ul>
			</div>
			<div class="message">
				<div id="message">
					<a href="" class="btn">点击捐款</a>
				</div>
			</div>
			<div class="todayIntegral">
				<div class="shanxing progress">
					<div class="title">善行积分 <span class="icon">?</span></div>
					<div>
						<div class="status"></div>
						<div class="text">
							<span>累计2积分</span><span>今日<span class="style1">0</span></span>
						</div>
					</div>
				</div>
				<div class="shanxin progress">
					<div class="title">善心积分<span class="icon">?</span></div>
					<div>
						<div class="status  active"></div>
						<div class="text">
							<span>累计2积分</span>   <span  class="style1">今日<span>0</span></span>
						</div>
					</div>
				</div>
				<div class="pool progress">
					<div class="title">捐款池</div>
					<div>
						<div class="status status1"></div>
						<div class="text text1">
							<span>累计1555元</span><span class="style1">今日<span>0</span></span>
						</div>
					</div>
				</div>

			</div>
		</div>
		<script type="text/javascript" src="/cu/www/common/js/jquery-3.2.1.min.js?v=<%=System.currentTimeMillis() %>"></script>
		<script src="js/jquery.barrager.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
	</body>

</html>