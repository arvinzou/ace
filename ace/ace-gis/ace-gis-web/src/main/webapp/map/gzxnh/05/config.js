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
	//color:['#00ffff','#ff7e00'],
	tooltip : {
		trigger : 'item',
		// formatter : '{b}<br>蓝色：精准人员（单位：人）<br>红色：救助救助（单位：百万）'
		formatter : function(params, ticket, callback) {
			var res = params.name;
			var data1 = option.series[0].markPoint.data;
			var data2 = option.series[1].markPoint.data;
			var v1 = 0, v2 = 0;
			for (var i = 0; i < data1.length; i++) {
				if (data1[i].name == params.name) {
					v1 = data1[i].value;
					break;
				}
			}
			for (var i = 0; i < data2.length; i++) {
				if (data2[i].name == params.name) {
					v2 = data2[i].value;
					break;
				}
			}
			res = params.name + '<br>精准人员:' + v1 + '（单位：人）<br>救助资金:' + v2
					+ '（单位：万元）';
			setTimeout(function() {
				// 仅为了模拟异步回调
				callback(ticket, res);
			}, 800)
			return 'loading';
		}
	},
	legend : {
		orient : 'vertical',
		x : 'right',
		y : 'bottom',
		data : [ {
			name : '精准人员(单位：人)',
			textStyle : {
				color : '#00ffff'
			}
		}, {
			name : '资金使用(单位：万元)',
			textStyle : {
				color : '#FF7E00'
			}
		} ],
		textStyle:{
			color:'auto'
		},
		  selected: {
	            '精准人员(单位：人)' : true,
	            '资金使用(单位：万元)' : true
	        }
	},

	series : [ {
		name : '精准人员(单位：人)',
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
						fontSize:20
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
					color : '#07142b'
				}
			}
		},
		data : [],
		markLine : {
			smooth : true,
			effect : {
				show : true,
				loop : true,
				period : 30,
				scaleSize : 1,
				color : '#fff',
				shadowColor : 'yellow',
				shadowBlur : 6
			},
			itemStyle : {
				normal : {
					lineStyle : {
						type : 'solid',
						shadowBlur : 10,
						width:2,
						color:'green'
					},
					label : {
						show : true,
						formatter : '\n\r\n\r\n\r\n\r{c}',
						textStyle : {
							color : '#00ffff',
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
						formatter : '{c}\n\r\n\r\n\r\n\r\n\r',
						textStyle : {
							color : '#00ffff',
							fontSize : 20,
							fontWeight : 100
						}

					}
				},
				emphasis : {

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

	}, {
		name : '资金使用(单位：万元)',
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
				// color : '#07142b'
				}
			}
		},
		data : [],
		markLine : {
			smooth : true,
			symbol : [ 'none', 'circle' ],
			symbolSize : 0,
			itemStyle : {
				normal : {
					lineStyle : {
						type : 'solid',
						color : 'green',
						shadowBlur : 0,
						width : 1
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
/* 资金占比*/
var optionPieolds = {
	color : [ '#DA70D6', '#6495ED', '#32CD32' ],
	title : {
		text : '资金占比',
		x : 'center',
		y : 'center',
		textStyle : {
			color : '#FFFFFF',
			fontSize : 20
		}
	},
	legend : {
		orient : 'vertical',
		x : 'right',
		y : 'top',
		data : [ '农合', '民政', '商保' ],
		textStyle : {
			color : '#FFFFFF',
			fontSize : 20
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

/* 柱形图 */
var optionBar = {
	title : {
		x : 'center',
		text : '贵州精准人员分布情况统计(单位：万人)',
		subtext : '2015年9月',
		textStyle : {
			color : '#FFFFFF'
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
		borderWidth : 1,
		// borderColor:'blue',
		y : 70,
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
			rotate : 0,
			margin : 2,
			textStyle : {
				color : '#FFFFFF',
				fontSize : 20
			}

		},
		data : [ 'Line', 'Bar', 'Scatter', 'K', 'Pie', 'Radar', 'Chord',
				'Force', 'Map', 'Gauge', 'Funnel' ]
	} ],
	yAxis : [ {
		type : 'value',
		show : true,
		splitArea : {
			show : false
		},
		splitLine : {
			show : true
		},
		lineStyle : {
			color : '#FFFFFF',
			width : 1,
			type : 'solid'
		},
		axisLabel : {
			formatter : '{value}万人',
			textStyle : {
				color : '#FFFFFF',
				fontSize : 20
			}
		}
	} ],
	series : [ {
		name : '地区精准情况（单位：万人）',
		type : 'bar',
		barWidth : 25,
		itemStyle : {
			normal : {
				color : function(params) {
					// build a color map as your need.
					var colorList = [ '#ff7f50', '#87cefa', '#da70d6',
							'#fff100', '#6495ed', '#ff69b4', '#ba55d3',
							'#cd5c5c', '#5ccd77', '#ff7f50', '#87cefa',
							'#da70d6', '#fff100', '#6495ed', '#ff7f50',
							'#87cefa', '#da70d6', '#fff100', '#6495ed' ];
					return colorList[params.dataIndex]
				},
				label : {
					show : true,
					position : 'top',
					formatter : '{c}'
				}
			}
		},
		data : []

	} ]
};
/* 柱形曲线 */
var optionBarJb = {
	title : {
		x : 'center',
		text : '补偿情况走势',
		//subtext : '2015年9月',
		textStyle : {
			color : '#FFFFFF'
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
		borderWidth : 1,
		// borderColor:'blue',
		y : 40,
		y2 : 60
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
			margin : 2,
			textStyle : {
				color : '#FFFFFF',
				fontSize : 12
			}

		},
		data : [  ]
	} ],
	yAxis : [ {
		
		type : 'value',
		show : true,
		splitArea : {
			show : false
		},
		splitLine : {
			show : true
		},
		lineStyle : {
			color : '#FFFFFF',
			width : 1,
			type : 'solid'
		},
		axisLabel : {
			formatter : '{value}次',
			textStyle : {
				color : '#FFFFFF',
				fontSize : 12
			}
		}
	} ,{
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
			width : 0,
			type : 'solid'
		},
		axisLabel : {
			formatter : '{value}万元',
			textStyle : {
				color : '#FFFFFF',
				fontSize : 12
			}
		}
	}],
	series : [ {
		name : '补偿次数（单位：次）',
		type : 'bar',
		barWidth : 20,
		itemStyle : {
			normal : {
				color :'#00479d',
				label : {
					show : false,
					position : 'top',
					formatter : '{c}次',
					fontSize : 12
				}
			}
		},
		data : [ ]

	} , {
		name : '补偿金额（单位：万元）',
		yAxisIndex: 1,
		symbol:'emptyCircle',
		symbolSize:4,
		type : 'line',
		itemStyle : {
			normal : {
				color :'#00479d',
				label : {
					show : false,
					position : 'top',
					formatter : '{c}万元',
					fontSize : 12
				},
				lineStyle:{
					color :'#02adf3',
				}
			}
		},
		data : [ ]

	}]
};