<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>贵州新农合业务分析平台概览</title>
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
		<a id="text">贵州新农合业务分析平台概览</a>
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
				<a class="jishu">参合人数：</a><a class="shuzi" id="chrs" data-to="300" data-speed="1500">2504</a><br>
				<a  class="danwei" style="font-size: 20px">(单位：万人)</a>
			</div>
			<div class="canhe">
				<a class="jishu">资金使用：</a><a class="shuzi" id="zjsy">5238</a>
				<br><a  class="danwei" style="font-size: 20px">(单位：万元)</a>
			</div>
			<div align="center" class="yibiao">
				<div id="gauge" style="height: 375px; width: 319px"></div>
			</div>
		</div>
		<div class="daditu">
			<div id="main" style="height: 832px; width: 883px"></div>
		</div>
		<div class="tubiao">
			<div class="shujutu" align="center" valign="middle">
				<div style="height: 10px;"></div>
				<div id="bar" style="height: 300px; width: 500px"></div>
			</div>
			<div class="shujubiao">
				<table class="table1" cellspacing="0" cellpadding="0">
					<tr class="tr1">
						<td>指标</td>
						<td align="right">值</td>
					</tr>
					<tr class="tr2">
						<td>参合率</td>
						<td align="right" class="tr4" id="chl">98.33</td>
					</tr>
					<tr class="tr3">
						<td>统筹基金使用率</td>
						<td align="right" class="tr4" id="dntcjjsyl">58.54</td>
					</tr>
					<tr class="tr2">
						<td>筹集资金</td>
						<td align="right" class="tr4" id="lstcjjsyl">112.66</td>
					</tr>
					<tr class="tr3">
						<td>实际补偿比</td>
						<td align="right" class="tr4" id="zcfwnbxbl">85.10</td>
					</tr>
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
	<script src="01/config.js"></script>
	<script src="01/view.js"></script>
	<script src="01/controller.js"></script>
	<script src="01/model.js"></script>
</body>
</html>
