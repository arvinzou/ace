Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

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
				};
			})(city)
		};
	}
	myChart.on(ecConfig.EVENT.MAP_SELECTED, function(param) {
		var mt = param.target;
		var len = mapType.length;
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
		animateTop();
		myInterval();
		initDataMap(false,mt);
	});
	setInterval("myInterval()", 6000);// 1000为1秒钟
	
}

function GetRandomNum(Min, Max) {
	var Range = Max - Min;
	var Rand = Math.random();
	return (Min + Math.round(Rand * Range));
}

/**
 * 晃动效果
 */
function animateTop(){
	$("#areaNameTitle").html(areaName+"新农合系统资金运行分析");
	$("#subText").html(new Date().Format("yyyy-MM-dd h:mm"));
	$(".shujutu1").animate({"margin-top": '30px',"margin-left": '-30px'}, "slow");
	$(".shujutu1").animate({"margin-top": '0px',"margin-left": '0px'}, "slow");
//	$(".shujutu1").animate({"margin-top": '-30px',"margin-left": '-30px'}, "slow");
//	$(".shujutu1").animate({"margin-top": '0px',"margin-left": '0px'}, "slow");
	
	$(".shujutu2").animate({"margin-top": '30px',"margin-left": '30px'}, "slow");
	$(".shujutu2").animate({"margin-top": '0px',"margin-left": '0px'}, "slow");
//	$(".shujutu2").animate({"margin-top": '-30px',"margin-left": '30px'}, "slow");
//	$(".shujutu2").animate({"margin-top": '0px',"margin-left": '0px'}, "slow");
	
	$(".shujutu3").animate({"margin-top": '-30px',"margin-left": '-30px'}, "slow");
	$(".shujutu3").animate({"margin-top": '0px',"margin-left": '0px'}, "slow");
//	$(".shujutu3").animate({"margin-top": '30px',"margin-left": '-30px'}, "slow");
//	$(".shujutu3").animate({"margin-top": '0px',"margin-left": '0px'}, "slow");
	
	$(".shujutu4").animate({"margin-top": '-30px',"margin-left": '30px'}, "slow");
	$(".shujutu4").animate({"margin-top": '0px',"margin-left": '0px'}, "slow");
//	$(".shujutu4").animate({"margin-top": '30px',"margin-left": '30px'}, "slow");
//	$(".shujutu4").animate({"margin-top": '0px',"margin-left": '0px'}, "slow");
}

function myInterval() {

    initDataMap(false);
    initDataBar_01(false);
    initDataBar_02(false);
    initDataBar_03(false);
    initDataBar_04(false);
    initDataGagueSy(false); 
    initDataPieolds(false);
    initDataPie_02(false);
    initPoorData(false);
}

function initDataMap(isInit,mapType){
	$("#subText").html("当前监测时间"+new Date().Format("yyyy年MM月dd日  h:mm"));
	$.ajax({
		type : "post",
		url : contextPath+"/gpsChian/selectListByPAreaName06.do",
		data : {
			areaName : areaName
		},
		success : function(rst, textStatus) {
			option.series[0].data=rst.data1;
			option.series[0].markPoint.data = rst.data1;
			option.series[0].markLine.data = rst.data4;
			option.series[0].geoCoord=rst.data3;
			if(isInit){
				if (myChart && myChart.dispose) {
			        myChart.dispose();
			    }
			    myChart = echarts.init(domMain, curTheme);
			    window.onresize = myChart.resize;
			    /*初始化图表－地图*/
				initMyChartMap();
			}
			if(mapType){
				option.series[0].mapType = mapType;
			}
			myChart.setOption(option, true);
			myChart.hideLoading();
		}
	});
}
/*左边柱形图*/
var myChartBar_01;
var myChartBar_01_big;
function initMyChartBar_01(){
	myChartBar_01 = echarts.init(document.getElementById('bar_01'),curTheme);
	myChartBar_01.setOption(optionBar_01,true);
	
	myChartBar_01_big = echarts.init(document.getElementById('bar_01_big'),curTheme);
	myChartBar_01_big.setOption(optionBar_01_big,true);
}
function initDataBar_01(isInit){
	$.ajax({
		type : "post",
		url : contextPath+"/gpsChian/selectListByPAreaName07.do",
		data : {
			areaName : areaName
		},
		success : function(rst, textStatus) {
			//console.log(rst.datax);
//			optionBar_01.xAxis[0].data=rst.datax;
			optionBar_01.series[0].data=rst.data;
			optionBar_01_big.series[0].data=rst.data;
//			optionBar_01.title.text=areaName+"参合情况统计";
//			optionBar_01.title.subtext=new Date().Format("yyyy-MM-dd h:mm");
			if(isInit){
				if (myChartBar_01 && myChartBar_01.dispose) {
					myChartBar_01.dispose();
			    }
				if (myChartBar_01_big && myChartBar_01_big.dispose) {
					myChartBar_01_big.dispose();
				}
				/*初始化图表－地图*/
				initMyChartBar_01();
			}
			myChartBar_01.setOption(optionBar_01,true);
			myChartBar_01_big.setOption(optionBar_01_big,true);
		}
	});
}

/*右边柱形图*/
var myChartBar_02;
var myChartBar_02_big;
function initMyChartBar_02(){
	myChartBar_02 = echarts.init(document.getElementById('bar_02'),curTheme);
	myChartBar_02.setOption(optionBar_02,true);
	
	myChartBar_02_big = echarts.init(document.getElementById('bar_02_big'),curTheme);
	myChartBar_02_big.setOption(optionBar_02_big,true);
}
function initDataBar_02(isInit){
	$.ajax({
		type : "post",
		url : contextPath+"/gpsChian/selectListByPAreaName08.do",
		data : {
			areaName : areaName
		},
		success : function(rst, textStatus) {
			//console.log(rst.datax);
//			optionBar_02.xAxis[0].data=rst.datax;
			optionBar_02.series[0].data=rst.data;
			optionBar_02_big.series[0].data=rst.data;
//			optionBar_02.title.text=areaName+"参合情况统计";
//			optionBar_02.title.subtext=new Date().Format("yyyy-MM-dd h:mm");
			if(isInit){
				if (myChartBar_02 && myChartBar_02.dispose) {
					myChartBar_02.dispose();
			    }
				
				if (myChartBar_02_big && myChartBar_02_big.dispose) {
					myChartBar_02_big.dispose();
			    }
				/*初始化图表－地图*/
				initMyChartBar_02();
			}
			myChartBar_02.setOption(optionBar_02,true);
			myChartBar_02_big.setOption(optionBar_02_big,true);
		}
	});
}


/*左下边柱形图*/
var myChartBar_03;
var myChartBar_03_big;
function initMyChartBar_03(){
	myChartBar_03 = echarts.init(document.getElementById('bar_03'),curTheme);
	myChartBar_03.setOption(optionBar_03,true);
	
	myChartBar_03_big = echarts.init(document.getElementById('bar_03_big'),curTheme);
	myChartBar_03_big.setOption(optionBar_03_big,true);
}
function initDataBar_03(isInit){
	$.ajax({
		type : "post",
		url : contextPath+"/gpsChian/selectListByPAreaName09.do",
		data : {
			areaName : areaName
		},
		success : function(rst, textStatus) {
			//console.log(rst.datax);
//			optionBar_02.xAxis[0].data=rst.datax;
			optionBar_03.series[0].data=rst.data;
			optionBar_03_big.series[0].data=rst.data;
//			optionBar_02.title.text=areaName+"参合情况统计";
//			optionBar_02.title.subtext=new Date().Format("yyyy-MM-dd h:mm");
			if(isInit){
				if (myChartBar_03 && myChartBar_03.dispose) {
					myChartBar_03.dispose();
			    }
				
				if (myChartBar_03_big && myChartBar_03_big.dispose) {
					myChartBar_03_big.dispose();
			    }
				/*初始化图表－地图*/
				initMyChartBar_03();
			}
			myChartBar_03.setOption(optionBar_03,true);
			myChartBar_03_big.setOption(optionBar_03_big,true);
		}
	});
}


/*右下边柱形图*/
var myChartBar_04;
var myChartBar_04_big;
function initMyChartBar_04(){
	myChartBar_04 = echarts.init(document.getElementById('bar_04'),curTheme);
	myChartBar_04.setOption(optionBar_04,true);
	
	myChartBar_04_big = echarts.init(document.getElementById('bar_04_big'),curTheme);
	myChartBar_04_big.setOption(optionBar_04_big,true);
	
}
function initDataBar_04(isInit){
//	$.ajax({
//		type : "post",
//		url : contextPath+"/gpsChian/selectListByPAreaName03.do",
//		data : {
//			areaName : areaName
//		},
//		success : function(rst, textStatus) {
			//console.log(rst.datax);
//			optionBar_02.xAxis[0].data=rst.datax;
//	        var data1=(Math.random()*40).toFixed(1);
//	        var data2=(Math.random()*30).toFixed(1);
//			optionBar_04.series[0].data=[data1,data2];
//			optionBar_04_big.series[0].data=[data1,data2];
//			optionBar_02.title.text=areaName+"参合情况统计";
//			optionBar_02.title.subtext=new Date().Format("yyyy-MM-dd h:mm");
			if(isInit){
				if (myChartBar_04 && myChartBar_04.dispose) {
					myChartBar_04.dispose();
			    }
				if (myChartBar_04_big && myChartBar_04_big.dispose) {
					myChartBar_04_big.dispose();
			    }
				/*初始化图表－地图*/
				initMyChartBar_04();
			}
			myChartBar_04.setOption(optionBar_04,true);
			myChartBar_04_big.setOption(optionBar_04_big,true);
//		}
//	});
}


function initPoorData(isInit){
	for(var i=1;i<=12;i++){
		var monery=[234.2,123.9,78.6,45.9,890.8,667.0,237,23.9,214.7,98.9,784.5,56.8,123.5];
		$("#personCount"+i).text(monery[i]);
		$("#personCountSmall"+i).text(monery[i]);
	}
}

/*仪表盘系统资源使用*/
var myChartGaugeSy;
var optionGaugeSy;
function initMyChartGagueSy(){
	myChartGaugeSy = echarts.init(document.getElementById('gaugeSy'),curTheme);
	myChartGaugeSy.setOption(optionGaugeSy,true);
}
function initDataGagueSy(isInit){
	$.ajax({
		type : "post",
		url : contextPath+"/gpsChian/selectListByPAreaName04.do",
		data : {
			areaName : areaName
		},
		success : function(rst, textStatus) {
			//console.log(rst);
			optionGaugeSy.series[0].data=[{value: GetRandomNum(6.33,69.87), name: ''}];
			
			//optionGaugeSy.title.subtext=new Date().Format("yyyy-MM-dd h:mm");
			
			
			if(isInit){
				if (myChartGaugeSy && myChartGaugeSy.dispose) {
					myChartGauge.dispose();
			    }
				/*初始化图表－地图*/
			    initMyChartGagueSy();
			}
			myChartGaugeSy.setOption(optionGaugeSy,true);
		}
	});
}
/*并图图资金运行情况*/
var myChartPieolds;
var myChartPieBig;
function initMyChartPieolds(){
	myChartPieolds = echarts.init(document.getElementById('piesolds'),curTheme);
	myChartPieolds.setOption(optionPieolds,true);
	
	//myChartPies = echarts.init(document.getElementById('pies'),curTheme);
	//myChartPies.setOption(optionPieOld,true);
	
	myChartPieBig = echarts.init(document.getElementById('piesBig'),curTheme);
	myChartPieBig.setOption(optionPieBig,true);
//	console.log(myChartPie);
}
function initDataPieolds(isInit){
	$.ajax({
		type : "post",
		url : contextPath+"/gpsChian/selectListByPAreaName05.do",
		data : {
			areaName : areaName
		},
		success : function(rst, textStatus) {
//			console.log(rst);
//			optionPie.series[0].data=[{"name":"农合","value":5263152717},{"name":"民政","value":13591399},{"name":"商保","value":7068477}];
//			console.log(rst.data1);
			optionPieolds.series[0].data = rst.data1;
			optionPieBig.series[0].data = rst.data1;
			optionPieOld.series[0].data = rst.data1;
			
			//optionPie.title.subtext=new Date().Format("yyyy-MM-dd h:mm");
			
			
			if(isInit){
				if (myChartPieolds&& myChartPieolds.dispose) {
					myChartPieolds.dispose();
			    }
				
				
				
				if (myChartPieBig && myChartPieBig.dispose) {
					myChartPieBig.dispose();
			    }
				
				/*初始化图表－地图*/
			    initMyChartPieolds();
			}
			//console.log(myChartPie);
			myChartPieolds.setOption(optionPieolds,true);
			myChartPieBig.setOption(optionPieBig,true);
		}
	});
}


/*走下方饼图*/
var myChartPie_02;
var myChartPie_02_big;
function initMyChartPie_02(){
	myChartPie_02 = echarts.init(document.getElementById('pies_02'),curTheme);
	myChartPie_02.setOption(optionPie_02,true);
	
	myChartPie_02_big = echarts.init(document.getElementById('pies_02_big'),curTheme);
	myChartPie_02_big.setOption(optionPie_02_big,true);
//	console.log(myChartPie_02);
}

function initDataPie_02(isInit){
	$.ajax({
		type : "post",
		url : contextPath+"/gpsChian/selectListByPAreaName99.do",
		data : {
			areaName : areaName
		},
		success : function(rst, textStatus) {
//			console.log(rst);
			//optionPie.series[0].data=[{"name":"农合","value":5263152717},{"name":"民政","value":13591399},{"name":"商保","value":7068477}];
			 optionPie_02.series[0].data[0].value=rst.data[0];
			 optionPie_02.series[0].data[1].value=100-rst.data[0];
			 optionPie_02.series[1].data[0].value=rst.data[2];
			 optionPie_02.series[1].data[1].value=100-rst.data[2];
			 optionPie_02.title.text='门诊均次补偿:'+(rst.data[1])+"元\n住院均次补偿:"+(rst.data[3])+"元";
			//optionPie.title.subtext=new Date().Format("yyyy-MM-dd h:mm");
			 
			 optionPie_02_big.series[0].data[0].value=rst.data[0];
			 optionPie_02_big.series[0].data[1].value=100-rst.data[0];
			 optionPie_02_big.series[1].data[0].value=rst.data[2];
			 optionPie_02_big.series[1].data[1].value=100-rst.data[2];
			 optionPie_02_big.title.text='门诊均次补偿:'+(rst.data[1])+"元、住院均次补偿:"+(rst.data[3])+"元";
			
			if(isInit){
				if (myChartPie_02 && myChartPie_02.dispose) {
					myChartPie_02.dispose();
			    }
				if (myChartPie_02_big && myChartPie_02_big.dispose) {
					myChartPie_02_big.dispose();
			    }
				/*初始化图表－地图*/
			    initMyChartPie_02();
			}
			//console.log(myChartPie);
			myChartPie_02.setOption(optionPie_02,true);
			myChartPie_02_big.setOption(optionPie_02_big,true);
		}
	});
}
