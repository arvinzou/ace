Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

function initMyChartMap() {
	var ecConfig = require('echarts/config');
	var zrEvent = require('zrender/tool/event');
	var curIndx = 0;
	var mapType = [];
	var mapGeoData = require('echarts/util/mapData/params');
	for ( var city in cityMap) {
		mapType.push(city);
		// 自定义扩展图表类型
		mapGeoData.params[city] = {
			getGeoJson : (function(c) {
				var geoJsonName = cityMap[c];
				return function(callback) {
					$.getJSON('geoJson/china-main-city/' + geoJsonName
							+ '.json', callback);
				}
			})(city)
		}
	}
	/*
	 * document.getElementById('main').onmousewheel = function(e) { var event =
	 * e || window.event; curIndx += zrEvent.getDelta(event) > 0 ? (-1) : 1; if
	 * (curIndx < 0) { curIndx = mapType.length - 1; } var mt = mapType[curIndx %
	 * mapType.length]; option.series[0].mapType = mt; option.title.subtext = mt + '
	 * （滚轮或点击切换）'; myChart.setOption(option, true); zrEvent.stop(event);
	 * areaName = mt; };
	 */
	myChart.on(ecConfig.EVENT.MAP_SELECTED, function(param) {

		var mt = param.target;
		var len = mapType.length;
		console.log(mt);
		var f = false;
		for (var i = 0; i < len; i++) {
			if (mt == mapType[i]) {
				f = true;
				mt = mapType[i];
			}
		}
		if (!f) {
			mt = '贵州';
		}
		areaName = mt;
		if(myChart){
			myChart.showLoading();
		}
		changeTitle();
		myInterval();
		initDataMap(false, mt)

	});
	myIntervalTable();
	setInterval("myInterval()", 6000);// 1000为1秒钟
	setInterval("myIntervalTable()", 6000);// 1000为1秒钟

}
function changeTitle() {
	$("#text").html(areaName + "新农合系统疾病用药分析");
	$("#subText").html( new Date().Format("yyyy-MM-dd h:mm"));
	$("#jibing_").html(nameSuba );
	$("#yaopin_").html(nameSubb );
}
function GetRandomNum(Min, Max) {
	var Range = Max - Min;
	var Rand = Math.random();
	return (Min + Math.round(Rand * Range));
}
function myInterval() {
	initDataMap(false);
	initDataGague(false);
	initDataBarJb(false);
	initDataBarYp(false);
	initDataGagueSy(false);
	initDataPie(false);
	
}

function initDataMap(isInit, mapType) {
	changeTitle();
	$.ajax({
		type : "post",
		url : contextPath+"/gpsChian/selectListByPAreaName10.do",
		data : {
			areaName : areaName,
			nameSuba : nameSuba,
			nameSubb : nameSubb
		},
		success : function(rst, textStatus) {

			option.series[0].data = rst.data2;
			option.series[0].markPoint.data = rst.data2;
			option.series[0].markLine.data = rst.data4;
			option.series[0].geoCoord = rst.data3;

			option.series[1].data = rst.data1;
			option.series[1].markPoint.data = rst.data1;
			option.series[1].markLine.data = rst.data4;
			option.series[1].geoCoord = rst.data3;
			if (isInit) {
				if (myChart && myChart.dispose) {
					myChart.dispose();
				}
				myChart = echarts.init(domMain, curTheme);
				window.onresize = myChart.resize;
				/* 初始化图表－地图 */
				initMyChartMap();
			}
			if (mapType) {
				option.series[0].mapType = mapType;
				option.series[1].mapType = mapType;
				// option.title.subtext = mapType;
			}
			myChart.setOption(option, true);
			myChart.hideLoading();
		}
	});
}

/* 仪表盘 */
var myChartGauge;
function initMyChartGague() {
	myChartGauge = echarts.init(document.getElementById('gauge'), curTheme);
	myChartGauge.setOption(optionGauge, true);
}
function initDataGague(isInit) {
	$.ajax({
		type : "post",
		url : contextPath+"/gpsChian/selectListByPAreaName11.do",
		data : {
			areaName : areaName,
			nameSuba : nameSuba,
			nameSubb : nameSubb
		},
		success : function(rst, textStatus) {
			var num1 = new Number(rst.syzjzb1);
			var num2 = new Number(rst.syzjzb2);
			/* 更新指标 */
			$("#fbcs").html(rst.fbcs);
			$("#syzjzb1").html(num1.toFixed(2) + "%");
			$("#yyje").html(rst.yyje);
			$("#syzjzb2").html(num2.toFixed(2) + "%");
		}
	});
}
/* 柱形图 */
var myChartBarJb;
function initMyChartBarJb() {
	myChartBarJb = echarts.init(document.getElementById('barJb'), curTheme);
	myChartBarJb.setOption(optionBarJb, true);
}
function initDataBarJb(isInit) {
	$.ajax({
		type : "post",
		url : contextPath+"/gpsChian/selectListByPAreaName14.do",
		data : {
			areaName : areaName
		},
		success : function(rst, textStatus) {
			// console.log(rst.datax);
			optionBarJb.xAxis[0].data = rst.datax;
			optionBarJb.series[0].data = rst.datay;
			optionBarJb.series[1].data = rst.datay2;
			optionBarJb.title.text = areaName +"TOP 10 疾病";
			//optionBarJb.title.subtext = new Date().Format("yyyy-MM-dd h:mm");
			if (isInit) {
				if (myChartBarJb && myChartBarJb.dispose) {
					myChartBarJb.dispose();
				}
				/* 初始化图表－地图 */
				initMyChartBarJb();
			}
			myChartBarJb.setOption(optionBarJb, true);
		}
	});
}
/* 柱形图 */
var myChartBarYp;
function initMyChartBarYp() {
	myChartBarYp = echarts.init(document.getElementById('barYp'), curTheme);
	myChartBarYp.setOption(optionBarYp, true);
}
function initDataBarYp(isInit) {
	$.ajax({
		type : "post",
		url : contextPath+"/gpsChian/selectListByPAreaName15.do",
		data : {
			areaName : areaName
		},
		success : function(rst, textStatus) {
			// console.log(rst.datax);
			optionBarYp.xAxis[0].data = rst.datax;
			optionBarYp.series[0].data = rst.datay;
			optionBarYp.series[1].data = rst.datay2;
			optionBarYp.title.text = areaName +"TOP 10 药品";
			//optionBarYp.title.subtext = new Date().Format("yyyy-MM-dd h:mm");
			if (isInit) {
				if (myChartBarYp && myChartBarYp.dispose) {
					myChartBarYp.dispose();
				}
				/* 初始化图表－地图 */
				initMyChartBarYp();
			}
			myChartBarYp.setOption(optionBarYp, true);
		}
	});
}

function changeNum(n, id) { // n设为想要改成的数
	$(function() {
		$("#" + id).animate({
			top : '+=20px',
			opacity : '0'
		}, "slow", function() { // 让数字向下移动并消失，top为元素距网页顶部距离，opacity为透明度，值为0~1
			document.getElementById(id).innerHTML = n; // 等数字消失后变为n，网页里有id为counter的一个span元素，这段代码必须放在animate里边，否则数字消失之前它的数值就改变了
		}).animate({
			top : '-=40px'
		}, "slow") // 数字n跳至原来位置的上方
		.animate({
			top : '+=20px',
			opacity : '1'
		}, "slow"); // 数字n出现并落至数字原来位置，opacity为0时是对象完全透明，就是消失，值为1时是完全显现
	});
}
function show_num(n, id) {
	var it = $("#" + id + " i");
	var len = String(n).length;
	for (var i = 0; i < len; i++) {
		if (it.length <= i) {
			$("#" + id).append("<i></i>");
		}
		var num = String(n).charAt(i);
		var y = -parseInt(num) * 30; // y轴位置
		var obj = $("#" + id + " i").eq(i);
		obj.animate({
			top : '+=20px',
			opacity : '1'
		}, "slow");
	}
}

function myIntervalTable(){
	initDataTableJb();
}
var page = 0;
var t = 0;
function initDataTableJb() {
	
	if (t > 10) {
		t = 0;
	}
	page = 9 * t;
	$.ajax({
		type : "post",
		url : contextPath+"/gpsChian/selectListByPAreaName12.do",
		data : {
			areaName : areaName,
			page : page
		},
		success : function(rst, textStatus) {
			var html = new Array();
			var trc="tr8";
			$.each(rst.data1,function(i,obj){
				i++;
				trc="tr8";
				if(i%2==0){
					trc="tr9";
				}
				html.push('<tr class="'+trc+'">');
				html.push('<td><a href="javascript:setCurrentParamJb(\''+obj.jbmc+'\')">'+obj.jbmc.substr(0,6)+'</a></td>');
				html.push('<td align="right" class="tr4">'+obj.hsbcfy+'</td>');
				html.push('</tr>');
				$("#tablejb>tbody>tr:last").remove();
			});	
			//console.log(html.join(''));
			$("#tablejb>tbody").append(html.join(''));
		}
	});
	t = t + 1;
	initDataTableYp();
}
function initDataTableYp() {
	$.ajax({
		type : "post",
		url : contextPath+"/gpsChian/selectListByPAreaName13.do",
		data : {
			areaName : areaName,
			page : page
		},
		success : function(rst, textStatus) {
			var html = new Array();
			var trc="tr8";
			$.each(rst.data1,function(i,obj){
				i++;
				trc="tr8";
				if(i%2==0){
					trc="tr9";
				}
				html.push('<tr class="'+trc+'">');
				html.push('<td><a href="javascript:setCurrentParamYp(\''+obj.ypmc+'\')">'+obj.ypmc.substr(0,6)+'</a></td>');
				html.push('<td align="right" class="tr4">'+obj.je+'</td>');
				html.push('</tr>');
				$("#tableyp>tbody>tr:last").remove();
			});	
			//console.log(html.join(''));
			$("#tableyp>tbody").append(html.join(''));
		}
	});
}
function setCurrentParamJb(jb){
	nameSuba=jb;
	changeTitle();
	myInterval();
}
function setCurrentParamYp(yp){
	nameSubb=yp;
	changeTitle();
	myInterval();
}