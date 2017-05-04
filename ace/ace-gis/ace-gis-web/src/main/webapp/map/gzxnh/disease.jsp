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
		<div class="daditu1" id="main" style="height: 834px; width: 883px">

		</div>
		<div class="tb1">
			<table class="table2" cellspacing="0" cellpadding="0">
				<tr class="tr5">
				<td>疾病</td>
					<td>发病次数（万次）</td>
					<td>使用资金占比</td>
				</tr>
				<tr class="tr6">
					<td id="jibing_">慢性支气管炎 </td>
					<td id="fbcs">232</td>
					<td align="right" id="syzjzb1">95.4%</td>
				</tr>
			</table>
			<table class="table2" cellspacing="0" cellpadding="0">
				<tr class="tr5">
				<td>药品</td>
					<td>用药金额（万元）</td>
					<td>使用资金占比</td>
				</tr>
				<tr class="tr6">
				<td id="yaopin_">炎琥宁</td>
					<td id="yyje">281</td>
					<td align="right" id="syzjzb2">4.6%</td>
				</tr>
			</table>

		</div>
		<div class="jibing" id="barJb"></div>
		<div class="yaopin" id="barYp">
			<a>药品图</a>
		</div>
		<div class="jibingtu">
			<table class="table3" cellspacing="0" cellpadding="0" id="tablejb">
				<thead>
					<tr class="tr7">
						<td>疾病</td>
						<td align="right">数量（万元）<img
							src="pic/magnifying-glass-finder.png" width="26" height="25" /></td>
					</tr>
				</thead>
				<tbody>

				</tbody>


			</table>
		</div>
		<div class="yaopintu">
			<table class="table3" cellspacing="0" cellpadding="0" id="tableyp">
				<thead>
					<tr class="tr7">
						<td>药名</td>
						<td align="right" with="100px">数量（万元）<img
							src="pic/magnifying-glass-finder.png" width="26" height="25" /></td>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
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
	<script src="03/config.js"></script>
	<script src="03/view.js"></script>
	<script src="03/controller.js"></script>
	<script src="03/model.js"></script>
	<style>
	.logo{
	
	top: 992px;
	
}
	</style>
</body>
</html>
