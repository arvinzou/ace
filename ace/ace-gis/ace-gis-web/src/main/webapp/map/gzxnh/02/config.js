var areaName = '贵州';
var cityMap = {
	"贵阳市" : "520100",
	"六盘水市" : "520200",
	"遵义市" : "520300",
	"安顺市" : "520400",
	"毕节地区" : "520500",
	"铜仁地区" : "522200",
	"黔西南布依族苗族自治州" : "522300",
	"黔东南苗族侗族自治州" : "522600",
	"黔南布依族苗族自治州" : "522700"
};
var curIndx = 0;
var mapType = [];
var mapGeoData = {};
var option = {
	// color:['#00ffff','#ff7e00'],
	tooltip : {
		trigger : 'item',
		// formatter : '{b}<br>蓝色：参合人员（单位：人）<br>红色：统筹资金使用（单位：百万）'
		formatter : function(params, ticket, callback) {
			var res = params.name;
			var data1 = option.series[0].markPoint.data;

			var v2 = 0;
			for (var i = 0; i < data1.length; i++) {
				if (data1[i].name == params.name) {
					v2 = data1[i].value;
					break;
				}
			}

			res = params.name + '统筹资金使用:' + v2 + '单位：万元';
			setTimeout(function() {
				// 仅为了模拟异步回调
				callback(ticket, res);
			}, 800);
			return 'loading';
		}
	},
	legend : {
		orient : 'vertical',
		x : 'right',
		y : 'bottom',
		data : [ {
			name : '资金使用(单位:万元)',
			textStyle : {
				color : '#FF7E00'
			}
		}, {
			name : '跨区补偿人数(单位:人)',
			textStyle : {
				color : 'green'
			}
		}],
		textStyle : {
			color : 'auto'
		},
		selected : {
			 '资金使用(单位：万元)' : true,
			'跨区补偿人数(单位:人)' : true
		}
	},

	series : [ {
		name : '资金使用(单位:万元)',
		type : 'map',
		mapType : '贵州',
		selectedMode : 'multiple',
		// roam:true,
		itemStyle : {
			normal : {
				label : {
					show : true,
					textStyle : {
						color : '#fff',
						fontSize : 20
					}
				},
				borderWidth : 1,// 省份的边框宽度
				borderColor : '#00b4ff',// 省份的边框颜色
				areaStyle : {
					color : '#0a233b'
				}
			},
			emphasis : {
				label : {
					show : true
				},
				areaStyle : {
					color : '#0a233b'
				}
			}
		},
		data : [],
		markLine : {
			smooth : true,
			symbol : [ 'none', 'circle' ],
			effect : {
				show : true,
				loop : true,
				period : 30,
				scaleSize : 1,
				color : '#fff',
				shadowColor : 'yellow',
				shadowBlur : 2
				
			},
			symbolSize : 0,
			itemStyle : {
				normal : {
					lineStyle : {
						type : 'solid',
						color : 'green',
						shadowBlur : 0,
						width : 3
					},
					borderWidth : 1,
					borderColor : 'rgba(30,144,255,0.5)',
					label : {
						show : true,
						formatter : '\n\r\n\r\n\r\n\r{c}',
						textStyle : {
							color : '#00FFFF',
							fontSize : 20,
							align : 'left'
						}

					}
				}
			},

			data : []
		},
		markPoint : {
			symbolSize : 0,
			itemStyle : {
				normal : {
					borderWidth : 0, // 标注边线线宽，单位px，默认为1
					label : {
						show : true,
						formatter : '{c}\n\r\n\r\n\r',
						textStyle : {
							color : '#FF7E00',
							fontSize : 20
						}

					}
				},
				emphasis : {
					borderColor : '#1e90ff',
					borderWidth : 0,
					label : {
						show : true,
						formatter : '{c}\n\r\n\r'

					}
				}
			},
			data : []
		},
		geoCoord : {
			'贵阳市' : [ 106.6992, 26.7682 ],
			'六盘水市' : [ 104.8727953, 26.5821372 ],
			'遵义市' : [ 106.9090748, 27.6583199 ],
			'铜仁地区' : [ 108.50, 27.90 ],
			'毕节地区' : [ 105.28, 27.28 ],
			'安顺市' : [ 105.55, 26.14 ],
			'黔南布依族苗族自治州' : [ 107.5112951, 26.2830359 ],
			'黔东南苗族侗族自治州' : [ 107.9810035, 26.5830679 ],
			'黔西南布依族苗族自治州' : [ 104.8948967, 25.093547 ]
		}

	} ]
};
var optionGauge = {
	title : {
		x : 'center',
		text : '资金使用率',
		subtext : '2015年9月',
		textStyle : {
			color : '#FFFFFF'
		}
	},
	color : '#FFFFFF',
	tooltip : {
		formatter : "{a} <br/>{b} : {c}%"
	},
	toolbox : {
		show : true,
		feature : {

			restore : {
				show : false
			},
			saveAsImage : {
				show : false
			}
		}
	},
	series : [ {
		name : '资金使用',
		type : 'gauge',
		axisLabel : {
			textStyle : {
				color : '#FFFFFF'
			}
		},
		detail : {
			formatter : '{value}%'
		},
		data : [ {
			value : 50.45,
			name : '使用率'
		} ]
	} ]
};

/* 左边柱形图 */
var optionBar_01 = {
	title : {
		x : 'left',
		y : 'top',
		text : '数量(千例)',
		subtext : '',
		textStyle : {
			color : '#FFFFFF',
			fontSize : 12,
			fontWeight : 'lighter'
		}
	},
	tooltip : {
		trigger : 'item'
	},
	toolbox : {
		show : true,
		feature : {
			dataView : {
				show : false,
				readOnly : false
			},
			restore : {
				show : false
			},
			saveAsImage : {
				show : false
			}
		}
	},
	calculable : true,
	grid : {
		borderWidth : 0,
		// borderColor:'blue',
		x : 30,
		x2 : 30,
		y : 30,
		y2 : 40
	},
	xAxis : [ {
		type : 'category',
		show : true,
		splitArea : {
			show : false
		},
		splitLine : {
			show : false,
			lineStyle : {
				color : [ '#FFFFFF' ],
				width : 1,
				type : 'solid'
			}
		},

		axisLabel : {
			show : true,
			rotate : 0,
			margin : 4,
			textStyle : {
				color : '#FFFFFF',
				fontSize : 12
			}

		},
		data : [ {
			value : '疑似异常\n病例',
			textStyle : {
				color : 'white',
				fontSize : 12,
				marginTop : 5
			}
		},

		{
			value : '认定违规\n病例',
			textStyle : {
				color : 'white',
				fontSize : 12,
				marginTop : 5
			}
		}, ]
	} ],
	yAxis : [ {
		type : 'value',
		show : true,
		splitArea : {
			show : false
		},
		axisTick : {
			show : true,
			inside : true,
			length : 5,
			lineStyle : {
				color : 'white',
				width : 1
			}
		},
		splitLine : {
			show : false
		},
		lineStyle : {
			color : '#FFFFFF',
			width : 1,
			type : 'solid'
		},
		axisLabel : {
			show : true,
			formatter : '{value}',
			textStyle : {
				fontSize : 12,
				color : 'white'
			}
		}
	} ],
	series : [ {
//		name : '地区参合情况',
		type : 'bar',
		barWidth : 25,
		itemStyle : {
			normal : {
				color : function(params) {
					// build a color map as your need.
					var colorList = [ '#FF7F50', '#FF936C' ];
					return colorList[params.dataIndex];
				},
				label : {
					show : true,
					position : 'top',
					formatter : '{c}'
				}
			}
		},
		data : [ 34, 21 ]

	} ]
};

/* 左边柱形图 */
var optionBar_01_big = {
	title : {
		x : 'left',
		y : 'top',
		text : '数量(千例)',
		subtext : '',
		textStyle : {
			color : '#FFFFFF',
			fontSize : 20,
			fontWeight : 'lighter'
		}
	},
	tooltip : {
		trigger : 'item'
	},
	toolbox : {
		show : true,
		feature : {
			dataView : {
				show : false,
				readOnly : false
			},
			restore : {
				show : false
			},
			saveAsImage : {
				show : false
			}
		}
	},
	calculable : true,
	grid : {
		borderWidth : 0,
		// borderColor:'blue',
		x : 50,
		x2 : 50,
		y : 50,
		y2 : 30
	},
	xAxis : [ {
		type : 'category',
		show : true,
		splitArea : {
			show : false
		},
		splitLine : {
			show : false,
			lineStyle : {
				color : [ '#FFFFFF' ],
				width : 1,
				type : 'solid'
			}
		},
		axisLabel : {
			show : true,
			rotate : 0,
			margin : 4,
			textStyle : {
				color : '#FFFFFF',
				fontSize : 20
			}

		},
		data : [ {
			value : '疑似异常病例',
			textStyle : {
				color : 'white',
				fontSize : 16
			}
		},

		{
			value : '认定违规病例',
			textStyle : {
				color : 'white',
				fontSize : 16
			}
		}, ]
	} ],
	yAxis : [ {
		type : 'value',
		show : true,
		splitArea : {
			show : false
		},
		axisTick : {
			show : true,
			inside : true,
			length : 5,
			lineStyle : {
				color : 'white',
				width : 1
			}
		},
		splitLine : {
			show : false
		},
		lineStyle : {
			color : '#FFFFFF',
			width : 1,
			type : 'solid'
		},
		axisLabel : {
			show : true,
			formatter : '{value} ',
			textStyle : {
				fontSize : 20,
				color : 'white'
			}
		}
	} ],
	series : [ {
//		name : '地区参合情况',
		type : 'bar',
		barWidth : 80,
		itemStyle : {
			normal : {
				color : function(params) {
					// build a color map as your need.
					var colorList = [ '#FF7F50', '#FF936C' ];
					return colorList[params.dataIndex];
				},
				label : {
					show : true,
					position : 'top',
					formatter : '{c}',
					textStyle : {
						fontSize : 20
					}
				}
			}
		},
		data : [ 12, 21 ]

	} ]
};

/* 左边柱形图 */
var optionBar_02 = {
	title : {
		x : 'left',
		y : 'top',
		text : '金额(百万元)',
		subtext : '',
		textStyle : {
			color : '#FFFFFF',
			fontSize : 12,
			fontWeight : 'lighter'
		}
	},
	tooltip : {
		trigger : 'item'
	},
	toolbox : {
		show : true,
		feature : {
			dataView : {
				show : false,
				readOnly : false
			},
			restore : {
				show : false
			},
			saveAsImage : {
				show : false
			}
		}
	},
	calculable : true,
	grid : {
		borderWidth : 0,
		// borderColor:'blue',
		x : 30,
		x2 : 30,
		y : 30,
		y2 : 40
	},
	xAxis : [ {
		type : 'category',
		show : true,
		splitArea : {
			show : false
		},
		splitLine : {
			show : false,
			lineStyle : {
				color : [ '#FFFFFF' ],
				width : 1,
				type : 'solid'
			}
		},
		axisLabel : {
			show : true,
			rotate : 0,
			margin : 4,
			textStyle : {
				color : '#FFFFFF',
				fontSize : 12
			}

		},
		data : [ {
			value : '疑似异常\n费用',
			textStyle : {
				color : 'white',
				fontSize : 12
			}
		},

		{
			value : '认定违规\n费用',
			textStyle : {
				color : 'white',
				fontSize : 12
			}
		}, ]
	} ],
	yAxis : [ {
		type : 'value',
		show : true,
		splitArea : {
			show : false
		},
		axisTick : {
			show : true
		},
		axisTick : {
			show : true,
			inside : true,
			length : 5,
			lineStyle : {
				color : 'white',
				width : 1
			}
		},
		splitLine : {
			show : false
		},
		lineStyle : {
			color : '#FFFFFF',
			width : 1,
			type : 'solid'
		},
		axisLabel : {
			formatter : '{value} ',
			textStyle : {
				color : '#FFFFFF',
				fontSize : 12
			}
		}
	} ],
	series : [ {
//		name : '地区参合情况',
		type : 'bar',
		barWidth : 25,
		itemStyle : {
			normal : {
				color : function(params) {
					// build a color map as your need.
					// color:[ '#32CD32','#59CD59' ],
					var colorList = [ '#32CD32', '#59CD59' ];
					return colorList[params.dataIndex];
				},
				label : {
					show : true,
					position : 'top',
					formatter : '{c}'
				}
			}
		},
		data : [ 12, 21 ]

	} ]
};

/* 左边柱形图 */
var optionBar_02_big = {
	title : {
		x : 'left',
		y : 'top',
		text : '金额(百万元)',
		subtext : '',
		textStyle : {
			color : '#FFFFFF',
			fontSize : 20,
			fontWeight : 'lighter'
		}
	},
	tooltip : {
		trigger : 'item'
	},
	toolbox : {
		show : true,
		feature : {
			dataView : {
				show : false,
				readOnly : false
			},
			restore : {
				show : false
			},
			saveAsImage : {
				show : false
			}
		}
	},
	calculable : true,
	grid : {
		borderWidth : 0,
		// borderColor:'blue',
		x : 50,
		x2 : 50,
		y : 50,
		y2 : 30
	},
	xAxis : [ {
		type : 'category',
		show : true,
		splitArea : {
			show : false
		},
		splitLine : {
			show : false,
			lineStyle : {
				color : [ '#FFFFFF' ],
				width : 1,
				type : 'solid'
			}
		},
		axisLabel : {
			show : true,
			rotate : 0,
			margin : 4,
			textStyle : {
				color : '#FFFFFF',
				fontSize : 20
			}

		},
		data : [ {
			value : '疑似异常费用',
			textStyle : {
				color : 'white',
				fontSize : 16
			}
		},

		{
			value : '认定违规费用',
			textStyle : {
				color : 'white',
				fontSize : 16
			}
		}, ]
	} ],
	yAxis : [ {
		type : 'value',
		show : true,
		splitArea : {
			show : false
		},
		axisTick : {
			show : true,
			inside : true,
			length : 5,
			lineStyle : {
				color : 'white',
				width : 1
			}
		},
		splitLine : {
			show : false
		},
		lineStyle : {
			color : '#FFFFFF',
			width : 1,
			type : 'solid'
		},
		axisLabel : {
			show : true,
			formatter : '{value} ',
			textStyle : {
				fontSize : 20,
				color : 'white'
			}
		}
	} ],
	series : [ {
//		name : '地区参合情况',
		type : 'bar',
		barWidth : 80,
		itemStyle : {
			normal : {
				color : function(params) {
					// build a color map as your need.
					// color:[ '#32CD32','#59CD59' ],
					var colorList = [ '#32CD32', '#59CD59' ];
					return colorList[params.dataIndex];
				},
				label : {
					show : true,
					position : 'top',
					formatter : '{c}',
					textStyle : {
						fontSize : 20
					}
				}
			}
		},
		data : [ 12, 21 ]

	} ]
};

/* 左下边柱形图 */
var optionBar_03 = {
	title : {
		x : 'left',
		y : 'top',
		text : '金额(百万元)',
		subtext : '',
		textStyle : {
			color : '#FFFFFF',
			fontSize : 12,
			fontWeight : 'lighter'
		}
	},
	tooltip : {
		trigger : 'item'
	},
	toolbox : {
		show : true,
		feature : {
			dataView : {
				show : false,
				readOnly : false
			},
			restore : {
				show : false
			},
			saveAsImage : {
				show : false
			}
		}
	},
	calculable : true,
	grid : {
		borderWidth : 0,
		// borderColor:'blue',
		x : 50,
		x2 : 30,
		y : 30,
		y2 : 50
	},
	xAxis : [ {
		type : 'category',
		show : true,
		splitArea : {
			show : false
		},
		splitLine : {
			show : false,
			lineStyle : {
				color : [ '#FFFFFF' ],
				width : 1,
				type : 'solid'
			}
		},
		axisLabel : {
			show : true,
			rotate : 0,
			margin : 4,
			textStyle : {
				color : '#FFFFFF',
				fontSize : 12
			}

		},
		data : [{
			value : '门诊补偿',
			textStyle : {
				color : 'white',
				fontSize : 12
			}
		},{
			value : '住院补偿',
			textStyle : {
				color : 'white',
				fontSize : 12
			}
		}]
	} ],
	yAxis : [ {
		type : 'value',
		show : true,
		splitArea : {
			show : false
		},
		splitLine : {
			show : false
		},
		lineStyle : {
			color : '#FFFFFF',
			width : 1,
			type : 'solid'
		},
		axisTick : {
			show : true,
			inside : true,
			length : 5,
			lineStyle : {
				color : 'white',
				width : 1
			}
		},
		axisLabel : {
			formatter : '{value} ',
			textStyle : {
				color : '#FFFFFF',
				fontSize : 12
			}
		}
	} ],
	series : [ {
//		name : '地区参合情况',
		type : 'bar',
		barWidth : 25,
		itemStyle : {
			normal : {
				color : function(params) {
					// build a color map as your need.
					var colorList = [ '#6495ED', '#DA70D6' ];
					return colorList[params.dataIndex];
				},
				label : {
					show : true,
					position : 'top',
					formatter : '{c}'
				}
			}
		},
		data : [ 12, 21 ]

	} ]
};

var optionBar_03_big = {
	title : {
		x : 'left',
		y : 'top',
		text : '金额(百万元)',
		subtext : '',
		textStyle : {
			color : '#FFFFFF',
			fontSize : 20,
			fontWeight : 'lighter'
		}
	},
	tooltip : {
		trigger : 'item'
	},
	toolbox : {
		show : true,
		feature : {
			dataView : {
				show : false,
				readOnly : false
			},
			restore : {
				show : false
			},
			saveAsImage : {
				show : false
			}
		}
	},
	calculable : true,
	grid : {
		borderWidth : 0,
		// borderColor:'blue',
		x : 80,
		x2 : 50,
		y : 50,
		y2 : 80
	},
	xAxis : [ {
		type : 'category',
		show : true,
		splitArea : {
			show : false
		},
		splitLine : {
			show : false,
			lineStyle : {
				color : [ '#FFFFFF' ],
				width : 1,
				type : 'solid'
			}
		},
		axisLabel : {
			show : true,
			rotate : 0,
			margin : 4,
			textStyle : {
				color : '#FFFFFF',
				fontSize : 20
			}

		},
		data : [
		{
			value : '门诊补偿',
			textStyle : {
				color : 'white',
				fontSize : 16
			}
		}, {
			value : '住院补偿',
			textStyle : {
				color : 'white',
				fontSize : 16
			}
		}
 ]
	} ],
	yAxis : [ {
		type : 'value',
		show : true,
		splitArea : {
			show : false
		},
		axisTick : {
			show : true,
			inside : true,
			length : 5,
			lineStyle : {
				color : 'white',
				width : 1
			}
		},
		splitLine : {
			show : false
		},
		lineStyle : {
			color : '#FFFFFF',
			width : 1,
			type : 'solid'
		},
		axisLabel : {
			show : true,
			formatter : '{value} ',
			textStyle : {
				fontSize : 20,
				color : 'white'
			}
		}
	} ],
	series : [ {
//		name : '地区参合情况',
		type : 'bar',
		barWidth : 80,
		itemStyle : {
			normal : {
				color : function(params) {
					// build a color map as your need.
					var colorList = [ '#6495ED', '#DA70D6' ];
					return colorList[params.dataIndex];
				},
				label : {
					show : true,
					position : 'top',
					formatter : '{c}',
					textStyle : {
						fontSize : 20
					}
				}
			}
		},
		data : [ 12, 21 ]

	} ]
};

/* 右下边柱形图 */
var optionBar_04 = {
	title : {
		x : 'left',
		y : 'top',
		text : '扶贫自付比（单位:%）',
		subtext : '',
		textStyle : {
			color : '#FFFFFF',
			fontSize : 12,
			fontWeight : 'lighter'
		}
	},
	tooltip : {
		trigger : 'item'
	},
	legend : {
		data : [ '扶贫前自付比例', '扶贫后自付比例' ],
		show : false
	},
	toolbox : {
		show : true,
		feature : {
			dataView : {
				show : false,
				readOnly : false
			},
			restore : {
				show : false
			},
			saveAsImage : {
				show : false
			}
		}
	},
	calculable : true,
	grid : {
		borderWidth : 0,
		// borderColor:'blue',
		x : 50,
		x2 : 50,
		y : 30,
		y2 : 40
	},
	xAxis : [ {
		type : 'category',
		show : true,
		splitArea : {
			show : false
		},
		splitLine : {
			show : false,
			lineStyle : {
				color : [ '#FFFFFF' ],
				width : 1,
				type : 'solid'
			}
		},
		axisLabel : {
			show : true,
			rotate : -20,
			margin : 4,
			textStyle : {
				color : '#FFFFFF',
				fontSize : 12
			}

		},
		data : [

		{
			value : '扶贫前自付比',
			textStyle : {
				color : '#fff',
				fontSize : 10
			}
		}, {
			value : '扶贫后自付比',
			textStyle : {
				color : '#fff',
				fontSize : 10
			}
		} ]
	} ],
	yAxis : [ {
		type : 'value',
		show : false,
		splitArea : {
			show : false
		},
		splitLine : {
			show : false
		},
		// lineStyle : {
		// color : '#FFFFFF',
		// width : 1,
		// type : 'solid'
		// },
		axisLabel : {
			formatter : '{value} ',
			textStyle : {
				// color : '#FFFFFF',
				fontSize : 12
			}
		}
	} ],
	series : [ {
//		name : '地区参合情况',
		type : 'bar',
		barWidth : 25,
		itemStyle : {
			normal : {
				color : function(params) {
					// build a color map as your need.
					var colorList = [ '#FF7E00', '#00FFFF' ];
					return colorList[params.dataIndex];
				},
				label : {
					show : true,
					position : 'top',
					formatter : '{c}'
				}
			}
		},
		data : [ 32.8,12.6]

	} ]
};

/* 右下边柱形图 */
var optionBar_04_big = {
	title : {
		x : 'left',
		y : '10',
		text : '扶贫自付比（单位:%）',
		subtext : '',
		textStyle : {
			color : '#FFFFFF',
			fontSize : 20,
			fontWeight : 'lighter'
		}
	},
	tooltip : {
		trigger : 'item'
	},
	legend : {
		data : [ '扶贫前自付比例', '扶贫后自付比例' ],
		show : false,
		textStyle : {
			fontSize : 20
		}
	},
	toolbox : {
		show : true,
		feature : {
			dataView : {
				show : false,
				readOnly : false
			},
			restore : {
				show : false
			},
			saveAsImage : {
				show : false
			}
		}
	},
	calculable : true,
	grid : {
		borderWidth : 0,
		// borderColor:'blue',
		x : 60,
		x2 : 60,
		y : 60,
		y2 : 70
	},
	xAxis : [ {
		type : 'category',
		show : true,
		splitArea : {
			show : false
		},
		splitLine : {
			show : false,
			lineStyle : {
				color : [ '#FFFFFF' ],
				width : 1,
				type : 'solid'
			}
		},
		axisLabel : {
			show : true,
			rotate : -20,
			margin : 4,
			textStyle : {
				color : '#FFFFFF',
				fontSize : 12
			}

		},
		data : [{
			value : '扶贫前自付比例',
			textStyle : {
				color : '#fff',
				fontSize : 20
			}
		},{
			value : '扶贫后自付比例',
			textStyle : {
				color : '#fff',
				fontSize : 20
			}
		}]
	} ],
	yAxis : [ {
		type : 'value',
		show : false,
		splitArea : {
			show : false
		},
		splitLine : {
			show : false
		},
		// lineStyle : {
		// color : '#FFFFFF',
		// width : 1,
		// type : 'solid'
		// },
		axisLabel : {
			formatter : '{value}% ',
			textStyle : {
				// color : '#FFFFFF',
				fontSize : 20
			}
		}
	} ],
	series : [ {
//		name : '地区参合情况',
		type : 'bar',
		barWidth : 60,
		itemStyle : {
			normal : {
				color : function(params) {
					// build a color map as your need.
					var colorList = [ '#FF7E00', '#00FFFF' ];
					return colorList[params.dataIndex];
				},
				label : {
					show : true,
					position : 'top',
					formatter : '{c}',
					textStyle : {
						fontSize : 20
					}
				}
			}
		},
		data : [ 32.8,12.6]
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

/* 资金运行情况 */
var optionPieolds = {
	color : [ '#DA70D6', '#6495ED', '#32CD32' ],
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
		formatter : "{b} : {c} ({d}%)"
	},
	calculable : false,
	series : [ {
		name : '访问来源',
		type : 'pie',
		radius : [ '45%', '65%' ],
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

/* 资金运行情况 */
var optionPieOld = {
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
		formatter : "{b} : {c} ({d}%)"
	},
	calculable : false,
	series : [ {
		name : '访问来源',
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

/* 资金运行情况 */
var optionPieBig = {
	color : [ '#DA70D6', '#6495ED', '#32CD32' ],
	title : {
		text : '资金占比',
		x : 'center',
		y : '140',
		textStyle : {
			color : '#FFFFFF',
			fontSize : 26
		}
	},
	legend : {
		orient : 'vertical',
		x : 'right',
		y : 'bottom',
		data : [ '农合', '民政', '商保' ],
		textStyle : {
			color : '#FFFFFF',
			fontSize : 20
		}
	},
	tooltip : {
		trigger : 'item',
		formatter : "{b} : {c}万元 ({d}%)"
	},
	calculable : false,
	series : [ {
		name : '访问来源',
		type : 'pie',
		radius : [ '50%', '80%' ],
		itemStyle : {
			normal : {
				label : {
					show : true,
					textStyle : {
						color : '#FFFFFF',
						fontSize : 20
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
						fontSize : 20,
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

/* 左下方饼图 */

var dataStyle = {
	normal : {
		label : {
			show : false
		},
		labelLine : {
			show : false
		}
	}
};
var placeHolderStyle = {
	normal : {
		color : 'rgba(0,0,0,0)',
		label : {
			show : false
		},
		labelLine : {
			show : false
		}
	},
	emphasis : {
		color : 'rgba(0,0,0,0)'
	}
};
// var optionPie_02 = {
// color:['#32CD32','#AOAOAO'],
// title : {
// text : '资金占比',
// x : 'center',
// y : 'center',
// textStyle : {
// color : '#FFFFFF',
// fontSize : 14
// }
// },
// legend : {
// orient : 'vertical',
// show:false,
// x : 'right',
// y : 'bottom',
// data : [ '农合'],
// textStyle : {
// color : '#FFFFFF',
// fontSize : 12
// }
// },
// tooltip : {
// trigger : 'item',
// formatter : "{a} <br/>{b} : {c} ({d}%)"
// },
// calculable : false,
// series : [ {
// name : '访问来源',
// type : 'pie',
// radius : [ '50%', '80%' ],
// itemStyle : {
// normal : {
// label : {
// show : true,
// textStyle : {
// color : '#FFFFFF',
// fontSize : 12
// }
// },
// labelLine : {
// show : true
// }
// },
// emphasis : {
// label : {
// show : true,
// position : 'center',
// textStyle : {
// fontSize : '12',
// color : '#FFFFFF'
// }
// }
// }
// },
// data : [ {
// value : 335,
// name : '农合'
// }]
// } ]

// };

/*左下方饼图 */

var dataStyle = {
		normal: {
			label: {show:false},
			labelLine: {show:false}
		}
};
var placeHolderStyle = {
		normal : {
			color: 'rgba(0,0,0,0)',
			label: {show:false},
			labelLine: {show:false}
		},
		emphasis : {
			color: 'rgba(0,0,0,0)'
		}
};
var optionPie_02 = {
		color:['#00A1E9','#1EC32A'],
		title: {
			text: '住院补偿与门诊补偿比例',
			subtext: '',
			sublink: '',
			x: 'center',
			y: '170',
			itemGap: 5,
			textStyle : {
				color : 'white',
				fontSize : 12
			}
		},
		tooltip : {
			show: true,
			formatter: "{b} : {d}%"
//			formatter: "{a} {b} : {c}元"
		},
		legend: {
			orient : 'horizontal',
			x : 20,
			y : 0,
			itemGap:12,
			data:['门诊补偿比例','住院补偿比例'],
			textStyle : {
				color : 'white',
				fontSize : 12
			}


		},
		toolbox: {
			show : false,
			feature : {
				mark : {show: true},
				dataView : {show: true, readOnly: false},
				restore : {show: true},
				saveAsImage : {show: true}
			}
		},
		series : [
		          {
		        	  name:'门诊补偿比例',
		        	  type:'pie',
		        	  clockWise:false,
		        	  radius : [40, 60],
		        	  itemStyle : {
		        			normal: {
		        				label: {show:false},
		        				labelLine: {show:false}
		        			}
		        	},
		        	  data:[
		        	        {
		        	        	value:68,
		        	        	name:'门诊次均费用',
		        	        	textStyle : {
		        	        		color : 'white',
		        	        		fontFamily : '微软雅黑',
		        	        		fontSize : 12,
		        	        		fontWeight : 'bolder'
		        	        	}
		        	        },
		        	        {
		        	        	value:32,
		        	        	name:'门诊自付比',
		        	        	itemStyle : {
		        	        		normal : {
		        	        			color: '#7EcFF5',
		        	        			label: {show:false},
		        	        			labelLine: {show:false}
		        	        		},
		        	        		emphasis : {
		        	        			color: 'rgba(0,0,0,0)'
		        	        		}
		        	        }
		        	        }
		        	        ]
		          },
		          {
		        	  name:'住院补偿比例',
		        	  type:'pie',
		        	  clockWise:false,
		        	  radius : [30, 40],
		        	  itemStyle :  {
		        			normal: {
		        				label: {show:false},
		        				labelLine: {show:false}
		        			}
		        	},
		        	  data:[
		        	        {
		        	        	value:29, 
		        	        	name:'住院补偿比例'
		        	        },
		        	        {
		        	        	value:71,
		        	        	name:'住院自付比',
		        	        	itemStyle : {
		        	        		normal : {
		        	        			color: '#ADD599',
		        	        			label: {show:false},
		        	        			labelLine: {show:false}
		        	        		},
		        	        		emphasis : {
		        	        			color: 'rgba(0,0,0,0)'
		        	        		}
		        	        }
		        	        }
		        	        ]
		          }
		          ]
};


var optionPie_02_big = {
		color:['#00A1E9','#1EC32A'],
		title: {
			text: '住院补偿与门诊补偿比例',
			subtext: '',
			sublink: '',
			x: 'center',
			y: 370,
			itemGap: 5,
			textStyle : {
				color : 'white',
				fontSize : 16
			}
		},
		tooltip : {
			show: true,
			formatter: "{b} : {d}%"
//			formatter: "{a} <br/>{b} : {c}"
		},
		legend: {
			orient : 'vertical',
			x : 100,
			y : 0,
			itemGap:12,
			data:['门诊补偿比例','住院补偿比例'],
			textStyle : {
				color : 'white',
				fontSize : 20
			}


		},
		toolbox: {
			show : false,
			feature : {
				mark : {show: true},
				dataView : {show: true, readOnly: false},
				restore : {show: true},
				saveAsImage : {show: true}
			}
		},
		series : [
		          {
		        	  name:'门诊补偿比例',
		        	  type:'pie',
		        	  clockWise:false,
		        	  radius : [125, 150],
		        	  itemStyle : {
		        			normal: {
		        				label: {show:false},
		        				labelLine: {show:false}
		        			}
		        	},
		        	  data:[
		        	        {
		        	        	value:68,
		        	        	name:'门诊补偿比例',
		        	        	textStyle : {
		        	        		color : 'white',
		        	        		fontFamily : '微软雅黑',
		        	        		fontSize : 12,
		        	        		fontWeight : 'bolder'
		        	        	}
		        	        },
		        	        {
		        	        	value:32,
		        	        	name:'门诊自付比',
		        	        	itemStyle : {
		        	        		normal : {
		        	        			color: '#7EcFF5',
		        	        			label: {show:false},
		        	        			labelLine: {show:false}
		        	        		},
		        	        		emphasis : {
		        	        			color: 'rgba(0,0,0,0)'
		        	        		}
		        	        }
		        	        }
		        	        ]
		          },
		          {
		        	  name:'住院补偿比例',
		        	  type:'pie',
		        	  clockWise:false,
		        	  radius : [100, 125],
		        	  itemStyle :  {
		        			normal: {
		        				label: {show:false},
		        				labelLine: {show:false}
		        			}
		        	},
		        	  data:[
		        	        {
		        	        	value:29, 
		        	        	name:'住院补偿比例',
		        	        	textStyle : {
		        	        		color : 'white',
		        	        		fontFamily : '微软雅黑',
		        	        		fontSize : 12,
		        	        		fontWeight : 'bolder'
		        	        	}
		        	        },
		        	        {
		        	        	value:71,
		        	        	name:'住院自付比',
		        	        	itemStyle : {
		        	        		normal : {
		        	        			color: '#ADD599',
		        	        			label: {show:false},
		        	        			labelLine: {show:false}
		        	        		},
		        	        		emphasis : {
		        	        			color: 'rgba(0,0,0,0)'
		        	        		}
		        	        }
		        	        }
		        	        ]
		          }
		          ]
};
