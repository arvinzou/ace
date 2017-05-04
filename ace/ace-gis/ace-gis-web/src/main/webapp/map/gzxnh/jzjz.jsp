<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>精准救助</title>
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

<body id="1">
   <div id="logo_head"><img src="img/logo_head.png"></div>
	<div class="title">
		<a id="text"></a>
	</div>
	<div class="time">
		<a id="subText"></a>
	</div>
	<div class="connet">
		<div class="back">
			<a href="#" onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('Image1','','pic/left-2.png',1)"><img
				src="pic/left-1.png" width="33" height="152" id="Image1" /></a>
		</div>
		<div class="daditu1" id="main"  style="height: 883px; width: 834px">
			
		</div>

		<div class="jibing" align="center">
			<a style="font-size: 22px;">精准对象类型</a><br />
			<br />
			<table class="table3" cellspacing="0" cellpadding="0"
				style="font-size: 16px;" id="tablejzdx">
				
				<thead>
					<tr class="tr7">
					<td>类型</td>
					<td align="right">人数</td>
				</tr>
				</thead>
				<tbody>

				</tbody>

			</table>
		</div>
		<div class="jzjzbar1" style="height: 400px;width:520px" id="bar">
			
		</div>
		<div class="jzjzpie" id="piesolds" align="center" style="padding-top: 100px;padding-right: 120px;">
			
		</div>
		<div class="jzjzbar2" style="padding-top: 100px;height: 400px;width:520px" id="barJb">
			
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
	<script src="05/config.js"></script>
	<script src="05/view.js"></script>
	<script src="05/controller.js"></script>
	<script src="05/model.js"></script>
</body>

</html>
