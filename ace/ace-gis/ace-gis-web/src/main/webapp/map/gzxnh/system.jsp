<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>贵州新农合运行情况</title>
<link rel="shortcut icon" href="../asset/ico/favicon.png">
<link href="../asset/css/font-awesome.min.css" rel="stylesheet">
<link href="../asset/css/bootstrap.css" rel="stylesheet">
<link href="../asset/css/carousel.css" rel="stylesheet">
<link href="css/css.css" rel="stylesheet" type="text/css">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script src="./www/js/echarts.js"></script>
</head>
<body>
    <div id="logo_head"><img src="img/logo_head.png"></div>
	<div class="title">
		<a id="text">贵州新农合系统疾病用药分析</a>
	</div>
	<div class="time">
		<a id="subText">2015年9月9日 10:04</a>
	</div>
	<div class="connet">
		<div class="back">
			<a href="gis.jsp" onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('Image1','','pic/left-2.png',1)"><img
				src="pic/left-1.png" width="33" height="152" id="Image1" /></a>
		</div>
		<div class="peple">
			<div class="canhe">
				<a class="jishu">访问人数：</a><a class="shuzi" id="sypeople">32,34</a><br />
				<a class="danwei">(人)</a>
			</div>
			<div class="zhuji">
				<a class="a2">主机</a><a class="a2" id="zhuji">1</a><br /> <br /> <br />
				<a class="a2">物理内存：</><a class="a2" id="memory">60%</a><br /> <a
					class="a2">存储：</a> <a class="a2" id="disk">45%</a><br /> <a
					class="a2">网络使用率：</a><a class="a2" id="net">50%</a>
			</div>
			<div align="center" class="yibiao1"
				style="width: 400px; height: 335px" id="gauge"></div>
		</div>
		<div class="daditu" id="main"></div>
		<div class="tubiao">
			<div class="shujubiao">
			<table class="table1" id="userList" cellspacing="0" cellpadding="0">
							<thead>
								<tr class="tr7">
									<td>用户</td>
									<td align="right">地市</td>
									<td align="right">访问次数</td>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
			</div>
		</div>
	<jsp:include page="menu.jsp" />
	</div>
	<div class="bottom" style="display:none">
		<a class="bottom_f">更改主题</a> <select id="theme-select"></select> <span
			id='wrong-message' style="color: #FFFFFF"></span>
	</div>
	<div class="logo">
		<img src="pic2/76u58picfig.png"  />
	</div>
	<script src="../asset/js/jquery.min.js"></script>
	<script src="../asset/js/bootstrap.min.js"></script>
	<script src="04/config.js"></script>
	<script src="04/view.js"></script>
	<script src="04/controller.js"></script>
	<script src="04/model.js"></script>
</body>
</html>
