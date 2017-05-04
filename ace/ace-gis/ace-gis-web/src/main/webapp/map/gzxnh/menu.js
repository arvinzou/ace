if (!window.console)
	window.console = {};
if (!window.console.log)
	window.console.log = function() {
	};
/* 资金运行情况 */
var optionPie = {
	color:['#DA70D6','#6495ED','#32CD32'],
	title : {
		text : '资金占比',
		x : 'center',
		y : 'center',
		textStyle : {
			color : '#FFFFFF',
			fontSize : 14
		}
	},
	legend : {
		orient : 'vertical',
		x : 'right',
		y : 'bottom',
		data : [ '农合', '民政', '商保' ],
		textStyle : {
			color : '#FFFFFF',
			fontSize : 12
		}
	},
	tooltip : {
		trigger : 'item',
		formatter : "{a} <br/>{b} : {c} ({d}%)"
	},
	calculable : false,
	series : [ {
		name : '系统运行情况',
		type : 'pie',
		radius : [ '50%', '80%' ],
		itemStyle : {
			normal : {
				label : {
					show : true,
					textStyle : {
						color : '#FFFFFF',
						fontSize : 12
					}
				},
				labelLine : {
					show : true
				}
			},
			emphasis : {
				label : {
					show : true,
					position : 'center',
					textStyle : {
						fontSize : '12',
						color : '#FFFFFF'
					}
				}
			}
		},
		data : [ {
			value : 335,
			name : '农合'
		}, {
			value : 310,
			name : '民政'
		}, {
			value : 234,
			name : '商保'
		} ]
	} ]
};
var optionGaugeSy = {
		color : '#FFFFFF',
		tooltip : {
			formatter : "{a} <br/>{b} : {c}%"
		},
		series : [ {
			type : 'gauge',
			radius : [ 0, '90%' ],
			axisLabel : {
				textStyle : {
					color : '#FFFFFF',
					fontSize : 12
				}
			},
			detail : {
				formatter : 'cpu\n\r{value}%',
				textStyle : {
					color : '#FFFFFF',
					fontSize : 14
				}
			},
			data : [ {
				name : 'cpu',
				value : 50.45

			} ]
		} ]
	};
	/* 仪表盘系统资源使用 */
var myChartGaugeSy;
var optionGaugeSy;
function initMyChartGagueSy() {
	myChartGaugeSy = echarts.init(document.getElementById('gaugeSy'), curTheme);
	myChartGaugeSy.setOption(optionGaugeSy, true);
}
function initDataGagueSy(isInit) {
	$.ajax({
		type : "post",
		url : contextPath+"/gpsChian/selectListByPAreaName04.do",
		data : {
			areaName : areaName
		},
		success : function(rst, textStatus) {
			// console.log(rst);
			optionGaugeSy.series[0].data = [ {
				value : GetRandomNum(6.33, 69.87),
				name : ''
			} ];

			// optionGaugeSy.title.subtext=new Date().Format("yyyy-MM-dd h:mm");

			if (isInit) {
				if (myChartGaugeSy && myChartGaugeSy.dispose) {
					myChartGauge.dispose();
				}
				/* 初始化图表－地图 */
				initMyChartGagueSy();
			}
			myChartGaugeSy.setOption(optionGaugeSy, true);
		}
	});
}
/* 并图图资金运行情况 */
var myChartPie;
var optionPie;
function initMyChartPie() {
	myChartPie = echarts.init(document.getElementById('pies'), curTheme);
	myChartPie.setOption(optionPie, true);
}
function initDataPie(isInit) {
	$.ajax({
		type : "post",
		url : contextPath+"/gpsChian/selectListByPAreaName05.do",
		data : {
			areaName : areaName
		},
		success : function(rst, textStatus) {
			// console.log(rst);
			optionPie.series[0].data = rst.data1;

			// optionPie.title.subtext=new Date().Format("yyyy-MM-dd h:mm");

			if (isInit) {
				if (myChartPie && myChartPie.dispose) {
					myChartGauge.dispose();
				}
				/* 初始化图表－地图 */
				initMyChartPie();
			}
			myChartPie.setOption(optionPie, true);
		}
	});
}