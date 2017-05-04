<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!-- 导航开始 -->
<div class="menu_tu3" id="menu4" onmouseleave="menuHide()">
	<div class="menu_title">
		<img src="pic/椭圆-2-副本-2.png" width="14" height="14" /> <a
			class="menu_f">参合、资金使用情况</a>
	</div>
	<div><img src="pic/ch.png" width="300px" height="140px" /></div>
</div>
<div class="menu_tu" id="menu1" onmouseleave="menuHide()">
	<div class="menu_title">
		<img src="pic/yuan2.png" width="14" height="14" /> <a class="menu_f">资金运行情况</a>
	</div>
	<div id="pies" style="height: 150px; width: 310px"></div>
</div>
<div class="menu_tu1" id="menu2" onmouseleave="menuHide()">
	<div class="menu_title">
		<img src="pic/yuan3.png" width="14" height="14" /> <a class="menu_f">疾病用药分析</a>
	</div>
	<div align="center" id="menu2sub">
		<table class="table2" cellspacing="0" cellpadding="0">
			<tr class="tr5">
				<td>发病次数（次）</td>
				<td>使用资金占比</td>
			</tr>
			<tr class="tr6">
				<td>232</td>
				<td align="right">95.4%</td>
			</tr>
		</table>
		<table class="table2" cellspacing="0" cellpadding="0">
			<tr class="tr5">
				<td>用药金额（元）</td>
				<td>使用资金占比</td>
			</tr>
			<tr class="tr6">
				<td>281</td>
				<td align="right">4.6%</td>
			</tr>
		</table>

	</div>
</div>
<div class="menu_tu2" id="menu3" onmouseleave="menuHide()">
	<div class="menu_title">
		<img src="pic/yuan4.png" width="14" height="14" /> <a class="menu_f">系统运行分析</a>
	</div>
	<div id="gaugeSy" style="height: 180px; width: 310px"></div>
</div>
<div class="menu">

	<div class="menu4" align="center" id="button4"
		onmouseover="menuDisplay('menu4')" onmouseleave="divHide('menu')">
		<a href="index.jsp"><img class="menu_ren" src="pic/user.png"
			width="45" height="41" /></a>
	</div>
	<div class="menu1" align="center" id="button1"
		onmouseover="menuDisplay('menu1')" onmouseleave="divHide('menu1')">

		<a href="money.jsp"><img class="menu_jiner"
			src="pic/stack-of-three-coins.png" width="45" height="37" /></a>
	</div>
	<div class="menu2" align="center" id="button2"
		onmouseover="menuDisplay('menu2')" onmouseleave="divHide('menu2')">
		<a href="disease.jsp"><img class="menu_yao"
			src="pic/pill-outline.png" width="45" height="45" /></a>
	</div>
	<div class="menu3" align="center" id="button3"
		onmouseover="menuDisplay('menu3')" onmouseleave="divHide('menu3')">
		<a href="system.jsp"><img class="menu_xitong"
			src="pic/empty-terminal-window.png" width="45" height="38" /></a>
	</div>
	
	<!--新增精准与智能审核  -->
	<div class="menu5" align="center" id="button5"
		onmouseover="menuDisplay('menu5')" onmouseleave="divHide('menu5')">
		<a href="jzjz.jsp"><img class="menu_xitong"
			src="pic/icon-jingzhun.png" width="45" height="38" /></a>
	</div>
	<div class="menu6" align="center" id="button6"
		onmouseover="menuDisplay('menu6')" onmouseleave="divHide('menu6')">
		<a href="znsh.jsp"><img class="menu_xitong"
			src="pic/icon-shenhe.png" width="45" height="38" /></a>
	</div>
	
</div>

<!-- 导航结束 -->

<script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}';
</script>
<script type="text/javascript">

	function MM_swapImgRestore() { //v3.0
		var i, x, a = document.MM_sr;
		for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++)
			x.src = x.oSrc;
	}
	function MM_preloadImages() { //v3.0
		var d = document;
		if (d.images) {
			if (!d.MM_p)
				d.MM_p = new Array();
			var i, j = d.MM_p.length, a = MM_preloadImages.arguments;
			for (i = 0; i < a.length; i++)
				if (a[i].indexOf("#") != 0) {
					d.MM_p[j] = new Image;
					d.MM_p[j++].src = a[i];
				}
		}
	}

	function MM_findObj(n, d) { //v4.01
		var p, i, x;
		if (!d)
			d = document;
		if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
			d = parent.frames[n.substring(p + 1)].document;
			n = n.substring(0, p);
		}
		if (!(x = d[n]) && d.all)
			x = d.all[n];
		for (i = 0; !x && i < d.forms.length; i++)
			x = d.forms[i][n];
		for (i = 0; !x && d.layers && i < d.layers.length; i++)
			x = MM_findObj(n, d.layers[i].document);
		if (!x && d.getElementById)
			x = d.getElementById(n);
		return x;
	}

	function MM_swapImage() { //v3.0
		var i, j = 0, x, a = MM_swapImage.arguments;
		document.MM_sr = new Array;
		for (i = 0; i < (a.length - 2); i += 3)
			if ((x = MM_findObj(a[i])) != null) {
				document.MM_sr[j++] = x;
				if (!x.oSrc)
					x.oSrc = x.src;
				x.src = a[i + 2];
			}
	}
	function menuDisplay(id) {
		if (id == "menu1") {
			/* $("#menu1").show();
			$("#menu2").hide();
			$("#menu3").hide();
			$("#menu4").hide();
*/
			$("#button1").css('height', '210px');
			$("#button2").css('height', '110px');
			$("#button3").css('height', '110px');
			$("#button4").css('height', '110px');
			$("#button5").css('height', '110px');
			$("#button6").css('height', '110px');
		}
		if (id == "menu2") {
		/*	$("#menu2").show();
			$("#menu1").hide();
			$("#menu3").hide();
			$("#menu4").hide();
*/
			$("#button1").css('height', '110px');
			$("#button2").css('height', '210px');
			$("#button3").css('height', '110px');
			$("#button4").css('height', '110px');
			$("#button5").css('height', '110px');
			$("#button6").css('height', '110px');
		}
		if (id == "menu3") {
			/*$("#menu3").show();
			$("#menu2").hide();
			$("#menu1").hide();
			$("#menu4").hide();*/

			$("#button1").css('height', '110px');
			$("#button2").css('height', '110px');
			$("#button3").css('height', '210px');
			$("#button4").css('height', '110px');
			$("#button5").css('height', '110px');
			$("#button6").css('height', '110px');
		}
		if (id == "menu4") {
			/*$("#menu4").show();
			$("#menu2").hide();
			$("#menu1").hide();
			$("#menu3").hide();*/

			$("#button1").css('height', '110px');
			$("#button2").css('height', '110px');
			$("#button3").css('height', '110px');
			$("#button4").css('height', '210px');
			$("#button5").css('height', '110px');
			$("#button6").css('height', '110px');
		}
		if (id == "menu5") {			
			$("#button1").css('height', '110px');
			$("#button2").css('height', '110px');
			$("#button3").css('height', '110px');
			$("#button4").css('height', '110px');
			$("#button5").css('height', '210px');
			$("#button6").css('height', '110px');
		}
		if (id == "menu6") {	
			$("#button1").css('height', '110px');
			$("#button2").css('height', '110px');
			$("#button3").css('height', '110px');
			$("#button4").css('height', '110px');
			$("#button5").css('height', '110px');
			$("#button6").css('height', '210px');
		}
	}
	function menuHide() {
		$("#menu6").hide();
		$("#menu5").hide();
		$("#menu4").hide();
		$("#menu3").hide();
		$("#menu2").hide();
		$("#menu1").hide();
	}
	function divHide(id){
		var h1=$("#id").is(":hidden");//是否隐藏
		var h2=$("#id").is(":visible");//是否可见
		if(!h1){
			console.log("hdie");
			$("#id").hide();
		}
		$("#menu6").hide();
		$("#menu5").hide();
		$("#menu4").hide();
		$("#menu3").hide();
		$("#menu2").hide();
		$("#menu1").hide();
		console.log(h1);
		console.log(h2);
	}
	
</script>
<script src="menu.js"></script>

