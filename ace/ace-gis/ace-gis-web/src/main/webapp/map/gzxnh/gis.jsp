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


</head>
<link href="css/mu.css" rel="stylesheet" type="text/css">
<link href="css/css.css" rel="stylesheet" type="text/css">

<style type="text/css">
/*圆点一闪一闪*/
</style>

<script>
	function fundSurveyOut() {
		document.getElementById('fundLine').style.display = "none";
		document.getElementById('fundSurvey').style.display = "none";
	}

	function fundSurveyOver() {
		document.getElementById('fundLine').style.display = "block";
		document.getElementById('fundSurvey').style.display = "block";
	}

	function diseaseOver() {
		document.getElementById('diseaseLine').style.display = "block";
		document.getElementById('diseaseSurvey').style.display = "block";
	}

	function diseaseOut() {
		document.getElementById('diseaseLine').style.display = "none";
		document.getElementById('diseaseSurvey').style.display = "none";
	}

	function onlineOver() {
		document.getElementById('onlineLine').style.display = "block";
		document.getElementById('onlineSurvey').style.display = "block";
	}

	function onlineOut() {
		document.getElementById('onlineLine').style.display = "none";
		document.getElementById('onlineSurvey').style.display = "none";
	}

	function systemOver() {
		document.getElementById('systemLine').style.display = "block";
		document.getElementById('systemSurvey').style.display = "block";
	}

	function systemOut() {
		document.getElementById('systemLine').style.display = "none";
		document.getElementById('systemSurvey').style.display = "none";
	}
</script>

</head>

<body class="bg">

	<div align="center" class="title">
		<a>新农合大数据分析平台</a>
	</div>

	<div id="Main" class="main"></div>
	<div class="zhuan">
		<img id="img" class="icon-spin" src="pic2/椭圆-2-拷贝-2.png"">
	</div>

	<div align="center" class="cet">
		<img src="pic2/5066229_115506329190_2.png" width="88" height="89" />
	</div>

	<div onmouseover="onlineOver()" onmouseout="onlineOut()" align="center"
		class="user">
		<a href="index.jsp"> <img class="ur" src="pic2/user.png"
			width="69" height="65" /></a>
	</div>
	<div onmouseover="fundSurveyOver()" onmouseout="fundSurveyOut()"
		align="center" class="money">
		<a href="money.jsp"> <img class="mon"
			src="pic2/stack-of-three-coins.png" width="59" height="48" /></a>
	</div>
	<div onmouseover="diseaseOver()" onmouseout="diseaseOut()"
		align="center" class="yao">
		<a href="disease.jsp"> <img class="jiyao"
			src="pic2/pill-outline.png" width="63" height="63" /></a>
	</div>
	<div onmouseover="systemOver()" onmouseout="systemOut()" align="center"
		class="xitong">
		<a href="system.jsp"><img class="xit"
			src="pic2/empty-terminal-window.png" width="51" height="44" /></a>
	</div>

	<div id="fundLine" class="line1">
		<img src="pic2/形状-2.png" width="392" height="50" />
	</div>
	<div id="fundSurvey" class="daokuang1">
		<div class="juli">
			<div>
				<a class="zi">资金运行情况</a>
			</div>
			<div class="juli2">
				<a class="zi">资金使用：</a> </br> <a class="zi2">(单位：万)</a>
			</div>
			<div align="center" class="juli2">
				<a class="zi3">526315.27</a>
			</div>
		</div>
	</div>


	<div id="diseaseLine" class="line2">
		<img src="pic2/形状-2_1.png" width="392" height="50" />
	</div>
	<div id="diseaseSurvey" class="daokuang1">
		<div class="juli">
			<div>
				<a class="zi">疾病用药分析</a>
			</div>
			<div class="juli2">
				<a class="zi">用药金额：</a> </br> <a class="zi2">(单位：万)</a>
			</div>
			<div align="center" class="juli2">
				<a class="zi3">618980.65</a>
			</div>
		</div>
	</div>

	<div id="systemLine" class="line3">
		<img src="pic2/形状-2_2.png" width="258" height="50" />
	</div>
	<div id="systemSurvey" class="daokuang2">
		<div class="juli">
			<div>
				<a class="zi">系统运行分析</a>
			</div>
			<div class="juli2">
				<a class="zi">访问人数：</a> </br> <a class="zi2">(单位：人)</a>
			</div>
			<div align="center" class="juli2">
				<a class="zi3">32340</a>
			</div>
		</div>
	</div>

	<div id="onlineLine" class="line4">
		<img src="pic2/形状-2_1.png" width="106" height="38" />
	</div>
	<div id="onlineSurvey" class="daokuang2">
		<div class="juli">
			<div>
				<a class="zi">参合、资金使用情况</a>
			</div>
			<div class="juli2">
				<a class="zi">参合人数：</a> </br> <a class="zi2">(单位：万人)</a>
			</div>
			<div align="center" class="juli2">
				<a class="zi3">2504.84</a>
			</div>
		</div>
	</div>

	<div class="logo">
		<img src="pic2/76u58picfig.png"  />
	</div>

</body>
</html>
