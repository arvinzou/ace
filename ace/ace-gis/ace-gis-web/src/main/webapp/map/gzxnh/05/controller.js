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
	myIntervalTable();
	setInterval("myIntervalTable()", 6000);// 1000为1秒钟

}
function changeTitle() {
	$("#text").html(areaName + "精准救助实施情况概览");
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
	initDataPieolds(false);
	initDataBar(false);
	initDataBarJb(false);
	//initDataGague(false);
	//initDataGagueSy(false);
	//initDataPie(false);
}

function initDataMap(isInit, mapType) {
	$.ajax({
		type : "post",
		url : contextPath+"/gpsChianDatajzjz/selectListByPAreaName01.do",
		data : {
			areaName : areaName
		},
		success : function(rst, textStatus) {
			changeTitle();
			option.series[0].data = rst.data2;
			option.series[0].markPoint.data = rst.data2;
			//option.series[0].markLine.data = rst.data4;
			option.series[0].geoCoord = rst.data3;

			option.series[1].data = rst.data1;
			option.series[1].markPoint.data = rst.data1;
			//option.series[1].markLine.data = rst.data4;
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

/* 柱形图 */
var myChartBar;
function initMyChartBar() {
	myChartBar = echarts.init(document.getElementById('bar'), curTheme);
	myChartBar.setOption(optionBar, true);
}
function initDataBar(isInit) {
	$.ajax({
		type : "post",
		url : contextPath+"/gpsChianDatajzjz/selectListByPAreaName03.do",
		data : {
			areaName : areaName
		},
		success : function(rst, textStatus) {
			// console.log(rst.datax);
			optionBar.xAxis[0].data = rst.datax;
			optionBar.series[0].data = rst.datay;
			optionBar.title.text = areaName + "精准人员分布情况统计(单位：万人)";
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
//资金占比
var myChartPieolds;
function initMyChartPieolds(){
	myChartPieolds = echarts.init(document.getElementById('piesolds'),curTheme);
	myChartPieolds.setOption(optionPieolds,true);
}
function initDataPieolds(isInit){
	$.ajax({
		type : "post",
		url : "/portal/gpsChianDatajzjz/selectListByPAreaName05.do",
		data : {
			areaName : areaName
		},
		success : function(rst, textStatus) {
			optionPieolds.series[0].data = rst.data1;
			if(isInit){
				if (myChartPieolds&& myChartPieolds.dispose) {
					myChartPieolds.dispose();
			    }
			    initMyChartPieolds();
			}
			myChartPieolds.setOption(optionPieolds,true);
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


function setCurrentParamJzdx(jb){
	jzdx=jb;
	changeTitle();
	myInterval();
}
function myIntervalTable(){
	initDataTableJzdx();
}
var page = 0;
var t = 0;
var jzdx;
function initDataTableJzdx() {
	
	if (t > 3) {
		t = 0;
	}
	page = 8* t;
	$.ajax({
		type : "post",
		url : contextPath+"/gpsChianDatajzjz/selectListByPAreaName12.do",
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
				html.push('<td><a href="javascript:setCurrentParamJzdx(\''+obj.jbmc+'\')">'+obj.jbmc.substr(0,15)+'</a></td>');
				html.push('<td align="right" class="tr4">'+obj.hsbcfy+'</td>');
				html.push('</tr>');
				$("#tablejzdx>tbody>tr:last").remove();
			});	
			//console.log(html.join(''));
			$("#tablejzdx>tbody").append(html.join(''));
		}
	});
	t = t + 1;
}
var myChartBarJb;
function initMyChartBarJb() {
	myChartBarJb = echarts.init(document.getElementById('barJb'), curTheme);
	myChartBarJb.setOption(optionBarJb, true);
}
function initDataBarJb(isInit) {
	$.ajax({
		type : "post",
		url : contextPath+"/gpsChianDatajzjz/selectListByPAreaName14.do",
		data : {
			areaName : areaName
		},
		success : function(rst, textStatus) {
			// console.log(rst.datax);
			optionBarJb.xAxis[0].data = rst.datax;
			optionBarJb.series[0].data = rst.datay;
			optionBarJb.series[1].data = rst.datay2;
			optionBarJb.title.text = areaName +"精准补偿趋势";
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