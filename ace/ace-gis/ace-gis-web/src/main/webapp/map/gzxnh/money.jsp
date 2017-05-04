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
<link href="css/css.css" rel="stylesheet" type="text/css">
<style type="text/css">
  。hoverOver{
   filter: alpha(opacity =50); -moz-opacity: 0。5; opacity: 0.5;
   display: block;
}

。hoverOut{
 filter: alpha(opacity =   0); -moz-opacity: 0; opacity: 0;
  display:none;
}
</style>
</head>

<body>
	<div id="cover"
		style="filter: alpha(opacity =   50); -moz-opacity: 50; opacity: 50; height: 100%; width: 100%; position: fixed; z-index: 999; opacity: 0.6; background-color: #ebebeb; display: none;"></div>
	<div id="logo_head"><img src="img/logo_head.png"></div>
	<div class="title">
		<a id="areaNameTitle">贵州移动新农合系统、资金运行情况</a>
	</div>
	<div class="time">
		<a id="subText"></a>
	</div>
	<div class="connet">
		<div class="back">
			<a href="gis.jsp" onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('Image1','','pic/left-2.png',1)"><img
				src="pic/left-1.png" width="33" height="152" id="Image1" /></a>
		</div>
		<div class="daditu1">
			<div id="main" style="height: 800px; width: 883px"></div>
		</div>
		<div class="daotu">
			<img src="pic/zb_2.png" />
		</div>
		<div class="shujutu1">
			<div align="center" valign="middle">
				<div style="height: 10px;"></div>
				<div id="bar_03"
					style="height: 220px; width: 200px; float: left; margin-left: 10px; margin-top: 30px"></div>
				<div id="pies_02"
					style="height: 215px; width: 205px; float: left; margin-left: -17px;margin-top: 20px"></div>
			</div>
		</div>

		<a href="javaScript:;">
		<div id="threeClick" class="shujutu1" style="filter: alpha(opacity =   0); -moz-opacity: 0; opacity: 0;"></div>
		</a>

		<div class="shujutu2">
			<div align="center" valign="middle">
				<div style="height: 10px;"></div>
				<div id="bar_04"
					style="height: 215px; width: 175px; float: left; margin-top: 10px; margin-left: 10px"></div>
				<div class="tb2">
					<table class="table4" cellspacing="0" cellpadding="0">
						<tr class="tr9sm">
							<td>人员</td>
							<td >资金使用</td>
						</tr>
						<tr class="tr8sm">
							<td>麻风患病者</td>
							<td align="center" id="personCountSmall1"></td>
						</tr>
						<tr class="tr7sm">
							<td>计生“两户”</td>
							<td id="personCountSmall2" align="center">&nbsp;</td>
						</tr>
						<tr class="tr8sm">
							<td>艾滋病</td>
							<td id="personCountSmall3" align="center"></td>
						</tr>
						<tr class="tr7sm">
							<td>大病患者</td>
							<td id="personCountSmall4" align="center">&nbsp;</td>
						</tr>
						<tr class="tr8sm">
							<td>特困供养人员</td>
							<td id="personCountSmall5" align="center"></td>
						</tr>
						<tr class="tr7sm">
							<td>低保家庭成员</td>
							<td id="personCountSmall6" align="center">&nbsp;</td>
						</tr>
						<tr class="tr8sm">
							<td>抚恤优抚对象</td>
							<td align="center" id="personCountSmall7"></td>
						</tr>
						<tr class="tr7sm">
							<td>精减退职老职工</td>
							<td align="center" id="personCountSmall8">&nbsp;</td>
						</tr>
						<tr class="tr8sm">
							<td>精神障碍患者</td>
							<td align="center" id="personCountSmall9"></td>
						</tr>
						<tr class="tr7sm">
							<td>低收入家庭重病患者</td>
							<td align="center" id="personCountSmall10">&nbsp;</td>
						</tr>
						<tr class="tr8sm">
							<td>自付费用过高患者</td>
							<td align="center" id="personCountSmall11"></td>
						</tr>
						<tr class="tr7sm">
							<td>其他特殊困难人群</td>
							<td align="center" id="personCountSmall12">&nbsp;</td>
						</tr>
					</table>
				</div>

			</div>
		</div>

        <a href="javaScript:;">
		<div id="fourClick" class="shujutu2"
			style="filter: alpha(opacity =   0); -moz-opacity: 0; opacity: 0;"></div>
        </a>

		<div class="shujutu3">
			<div align="center" valign="middle">
				<div style="height: 10px;"></div>
				<div id="piesolds" style="height: 180px; width: 300px; margin-top: 20px"></div>
			</div>
		</div>

        <a href="javaScript:;">
		<div id="oneClick" class="shujutu3"
			style="filter: alpha(opacity =   0); -moz-opacity: 0; opacity: 0;"></div>
        </a>

		<div class="shujutu4">
			<div align="center" valign="middle"
				style="background-color: #243d53; position: absolute;">
				<div style="height: 10px;"></div>
				<div id="bar_01"
					style="height: 210px; width: 185px; float: left; margin-left: 10px; margin-top: 10px;"></div>
				<div id="bar_02"
					style="height: 210px; width: 185px; float: left; margin-left: 10px; margin-top: 10px;"></div>
			</div>
		</div>

        <a href="javaScript:;">
		<div id="twoClick" class="shujutu4"
			style="filter: alpha(opacity =   0); -moz-opacity: 0; opacity: 0;"></div>
         </a>

	<jsp:include page="menu.jsp" />
		<!--  -->
	</div>
	<div class="bottom" style="display:none">
		<a class="bottom_f">说明</a> <select id="theme-select"></select> <span
			id='wrong-message' style="color: #FFFFFF"></span>
	</div>

	<div class="shujutu1_big" id="shujutu1_big" style="display: none;">
		<div align="right">
			<img id="closeImg1" src="pic/形状-33_1.png" width="39" height="38"
				style="padding: 10px;" />
			<div align="center" valign="middle">
				<div style="height: 10px;"></div>
				<div id="piesBig" style="height: 430px; width: 720px;"></div>
			</div>
		</div>
	</div>
	<div class="shujutu2_big" id="shujutu2_big" style="display: none;">
		<div align="right">
			<img id="closeImg2" src="pic/形状-33_1.png" width="39" height="38"
				style="padding: 10px;" />
			<div align="center" valign="middle"
				style="background-color: #243d53; position: absolute;">
				<div style="height: 10px;"></div>
				<div id="bar_01_big"
					style="height: 430px; width: 390px; float: left; margin-left: 25px; margin-top: 0px;"></div>
				<div id="bar_02_big"
					style="height: 430px; width: 390px; float: left; margin-left: -10px; margin-top:0px;"></div>
			</div>
		</div>
	</div>
	<div class="shujutu3_big" id="shujutu3_big" style="display: none;">
		<div align="right">
			<img id="closeImg3" src="pic/形状-33_1.png" width="39" height="38"
				style="padding: 10px;" />
			<div align="center" valign="middle">
				<div style="height: 10px;"></div>
				<div id="bar_03_big"
					style="height: 430px; width: 370px; float: left; margin-left: 30px; margin-top: 38px"></div>
				<div id="pies_02_big"
					style="height: 430px; width: 370px; float: left; margin-left: 25px;margin-top: 25px;"></div>
			</div>
		</div>
	</div>
	<div class="shujutu4_big" id="shujutu4_big" style="display: none;">
		<div align="right">
			<img id="closeImg4" src="pic/形状-33_1.png" width="39" height="38"
				style="padding: 10px;" />
		</div>
		<div id="bar_04_big"
			style="height: 430px; width: 350px; float: left; margin-top: 10px; margin-left: 10px"></div>
		<div class="tb3">
			<table class="table5" cellspacing="0" cellpadding="0">
				<tr class="tr12">
					<td>人员</td>
					<td >扶贫资金使用</td>
				</tr>
				<tr class="tr11">
					<td>麻风患病者</td>
					<td align="center" id="personCount1"></td>
				</tr>
				<tr class="tr10">
					<td>计生“两户”</td>
					<td align="center" id="personCount2">&nbsp;</td>
				</tr>
				<tr class="tr11">
					<td>艾滋病</td>
					<td align="center" id="personCount3"></td>
				</tr>
				<tr class="tr10">
					<td>大病患者</td>
					<td align="center" id="personCount4">&nbsp;</td>
				</tr>
				<tr class="tr11">
					<td>特困供养人员</td>
					<td align="center" id="personCount5"></td>
				</tr>
				<tr class="tr10">
					<td>低保家庭成员</td>
					<td align="center" id="personCount6">&nbsp;</td>
				</tr>
				<tr class="tr11">
					<td>抚恤优抚对象</td>
					<td align="center" id="personCount7"></td>
				</tr>
				<tr class="tr10">
					<td>精减退职老职工</td>
					<td align="center" id="personCount8">&nbsp;</td>
				</tr>
				<tr class="tr11">
					<td>精神障碍患者</td>
					<td align="center" id="personCount9"></td>
				</tr>
				<tr class="tr10">
					<td>低收入家庭重病患者</td>
					<td align="center" id="personCount10">&nbsp;</td>
				</tr>
				<tr class="tr11">
					<td>自付费用过高患者</td>
					<td align="center" id="personCount11"></td>
				</tr>
				<tr class="tr10">
					<td>其他特殊困难人群</td>
					<td align="center" id="personCount12">&nbsp;</td>
				</tr>
			</table>
		</div>
	</div>
<div class="logo">
		<img src="pic2/76u58picfig.png"  />
	</div>
	<script src="../asset/js/jquery.min.js"></script>
	<script src="../asset/js/bootstrap.min.js"></script>
	<script src="02/config.js"></script>
	<script src="02/view.js"></script>
	<script src="02/controller.js"></script>
	<script src="02/model.js"></script>
	<script type="text/javascript">
		
		

		$(function() {
			animateTop();
			$("#threeClick").hover(function() {
				$(this).css({
					"filter": "alpha(opacity =80)",
				    "-moz-opacity": "0。8",
				    "opacity": "0.8",
				    "display":"block"
				 });
			}, function() {
				$(this).css({
					"filter": "alpha(opacity =0)",
				    "-moz-opacity": "0",
				    "opacity": "0",
				    "display":"block"
				 });
			});
			
			$("#threeClick").click(function() {
				if ($('#shujutu3_big').is(':visible')) {
					$('#shujutu3_big').slideUp();
				} else {
					$('#shujutu3_big').slideDown();
					$("#cover").show();
				}
			});
			
			
			$("#fourClick").hover(function() {
				$(this).css({
					"filter": "alpha(opacity =80)",
				    "-moz-opacity": "0。8",
				    "opacity": "0.8",
				    "display":"block"
				 });
			}, function() {
				$(this).css({
					"filter": "alpha(opacity =0)",
				    "-moz-opacity": "0",
				    "opacity": "0",
				    "display":"block"
				 });
			});

			$("#fourClick").click(function() {
				if ($('#shujutu4_big').is(':visible')) {
					$('#shujutu4_big').slideUp();
				} else {
					$('#shujutu4_big').slideDown();
					$("#cover").show();
				}
			});
			
			$("#oneClick").hover(function() {
				$(this).css({
					"filter": "alpha(opacity =80)",
				    "-moz-opacity": "0。8",
				    "opacity": "0.8",
				    "display":"block"
				 });
			}, function() {
				$(this).css({
					"filter": "alpha(opacity =0)",
				    "-moz-opacity": "0",
				    "opacity": "0",
				    "display":"block"
				 });
			});

			$("#oneClick").click(function() {
				if ($('#shujutu1_big').is(':visible')) {
					$('#shujutu1_big').slideUp();
				} else {
					$('#shujutu1_big').slideDown();
					$("#cover").show();
				}
			});
			
			
			$("#twoClick").hover(function() {
				$(this).css({
					"filter": "alpha(opacity =80)",
				    "-moz-opacity": "0。8",
				    "opacity": "0.8",
				    "display":"block"
				 });
			}, function() {
				$(this).css({
					"filter": "alpha(opacity =0)",
				    "-moz-opacity": "0",
				    "opacity": "0",
				    "display":"block"
				 });
			});

			$("#twoClick").click(function() {
				if ($('#shujutu2_big').is(':visible')) {
					$('#shujutu2_big').slideUp();
				} else {
					$('#shujutu2_big').slideDown();
					$("#cover").show();
				}
			});

			$("#closeImg1,#closeImg2,#closeImg3,#closeImg4").click(function() {
								$("#shujutu1_big,#shujutu2_big,#shujutu3_big,#shujutu4_big").fadeOut();
								$("#cover").hide();
							});

		});
	</script>
</body>

</html>
