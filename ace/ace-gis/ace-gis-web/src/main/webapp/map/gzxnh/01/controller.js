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
		changeTitle()
		myInterval();
		initDataMap(false,mt);

	});
	setInterval("myInterval()", 6000);// 1000为1秒钟

}
function changeTitle() {
	$("#text").html(areaName + "新农合业务分析平台概览");
	$("#subText").html(new Date().Format("yyyy-MM-dd h:mm"));
	if(myChart){
		myChart.showLoading();
	}
}
function GetRandomNum(Min, Max) {
	var Range = Max - Min;
	var Rand = Math.random();
	return (Min + Math.round(Rand * Range));
}
function myInterval() {
	initDataMap(false);
	initDataGague(false);
	initDataBar(false);
	initDataGagueSy(false);
	initDataPie(false);
}

function initDataMap(isInit, mapType) {
	
	
	$.ajax({
		type : "post",
		url : contextPath+"/gpsChian/selectListByPAreaName01.do",
		data : {
			areaName : areaName
		},
		success : function(rst, textStatus) {
			changeTitle();
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
		url : contextPath+"/gpsChian/selectListByPAreaName02.do",
		data : {
			areaName : areaName
		},
		success : function(rst, textStatus) {
			optionGauge.series[0].data = [ {
				value : rst.dntcjjsyl,
				name : ''
			} ];
			console.log(areaName);
			if(areaName=='贵州2'){
				optionGauge.series[0].data = [ {
					value : 36.75,
					name : ''
				} ];
				changeNum("569918.22","zjsy");
			    $("#chrs").html("3292.33");
				$("#chl").html("99.12%");
				$("#dntcjjsyl").html("38.47%");
				$("#lstcjjsyl").html("1481548.37万元");
			}else{
				optionGauge.series[0].data = [ {
					value : rst.dntcjjsyl,
					name : ''
				} ];
				changeNum(rst.zjsy,"zjsy");
			    $("#chrs").html(rst.chrs);
				$("#chl").html(rst.chl+"%");
				$("#dntcjjsyl").html(rst.dntcjjsyl+"%");
				var num1 = new Number(rst.dncjzj);
				$("#lstcjjsyl").html(num1.toFixed(2)+"万元");
			}
			
			
			$("#zcfwnbxbl").html(rst.zcfwnbxbl+"%");
			if (isInit) {
				if (myChartGauge && myChartGauge.dispose) {
					myChartGauge.dispose();
				}
				/* 初始化图表－地图 */
				initMyChartGague();
			}
			myChartGauge.setOption(optionGauge, true);
		}
	});
}
/* 柱形图 */
var myChartBar;
function initMyChartBar() {
	myChartBar = echarts.init(document.getElementById('bar'), curTheme);
	myChartBar.setOption(optionBar, true);
}
function initDataBar(isInit) {
	$.ajax({
		type : "post",
		url : contextPath+"/gpsChian/selectListByPAreaName03.do",
		data : {
			areaName : areaName
		},
		success : function(rst, textStatus) {
			// console.log(rst.datax);
			optionBar.xAxis[0].data = rst.datax;
			optionBar.series[0].data = rst.datay;
			optionBar.title.text = areaName + "参合情况统计(单位：万人)";
			optionBar.title.subtext = new Date().Format("yyyy-MM-dd h:mm");
			if (isInit) {
				if (myChartBar && myChartBar.dispose) {
					myChartBar.dispose();
				}
				/* 初始化图表－地图 */
				initMyChartBar();
			}
			myChartBar.setOption(optionBar, true);
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