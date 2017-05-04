<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html>
<head>
<title>table内容连续滚动</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<style type="text/css">
* {
	padding: 0;
	margin: 0
}

;
td {
	height: 28px;
}
;
</style>
</head>
<body bgcolor="#FFFFFF">
	<div style="padding-top: 100px;">
		<div id="demo" style="overflow: hidden; height: 240;">
			<div id="demo1">
				
			</div>
			<div id="demo2"></div>
		</div>
	</div>
	<script type="text/javascript">
		var speed = 50;
		demo2.innerHTML = demo1.innerHTML;
		function Marquee() {
			if (demo2.offsetTop - demo.scrollTop <= 0) {
				demo.scrollTop -= demo1.offsetHeight;
			} else {
				demo.scrollTop++;
			}
		}
		var MyMar = setInterval(Marquee, speed);

		demo.onmouseover = function() {
			clearInterval(MyMar);
		}

		demo.onmouseout = function() {
			MyMar = setInterval(Marquee, speed);
		}
	</script>
</body>
</html>
