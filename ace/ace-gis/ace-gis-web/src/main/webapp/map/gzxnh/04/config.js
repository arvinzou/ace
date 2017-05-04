var areaName = '贵州';
var nameSuba = "慢性支气管炎";
var nameSubb = "炎琥宁";
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
	tooltip : {
		trigger : 'item',
		// formatter : '{b}<br>蓝色：参合人员（单位：人）<br>红色：统筹资金使用（单位：百万）'
		formatter : function(params, ticket, callback) {
			var res = params.name;
			var data1 = option.series[0].markPoint.data;
			var v1 = 0, v2 = 0;
			for (var i = 0; i < data1.length; i++) {
				if (data1[i].name == params.name) {
					v1 = data1[i].value;
					break;
				}
			}

			res = params.name + '<br>使用用户:' + v1 + '（单位：人次)';
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
			name : '系统用户(单位：人次)',
			textStyle : {
				color : '#00ffff'
			}
		} ],
		textStyle : {
			color : '#FFFFFF',
			fontSize : 12,
			fontWeight : 10,
			align : 'left'
		}
	},

	series : [ {
		name : '系统用户',
		type : 'map',
		mapType : '贵州',
		selectedMode : 'multiple',
		// roam:true,
		itemStyle : {
			normal : {
				label : {
					show : true,
					textStyle : {
						color : '#FFF',
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
					show : true,
					textStyle : {
						color : '#FFF'
					}
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
							fontWeight : 10,
							align : 'left'
						}

					}
				}
			},

			data : []
		},
		markPoint : {
			symbolSize : 0,

			// 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize * 2
			itemStyle : {
				normal : {
					// borderColor : '#FFFFFF',
					borderWidth : 0, // 标注边线线宽，单位px，默认为1
					label : {
						show : true,
						formatter : '{c}\n\r\n\r\n\r\n\r',
						textStyle : {
							color : '#00ffff',
							fontSize : 20,
							fontWeight : 100
						}

					}
				},
				emphasis : {
					// borderColor : '#FFFFFF',
					borderWidth : 0,
					label : {
						show : true,
						formatter : '{c}\n\r\n\r',
						textStyle : {
							color : 'red',
							fontSize : 20,
							fontWeight : 100
						}

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
	color : '#FFFFFF',
	tooltip : {
		formatter : "{a} <br/>{b} : {c}%"
	},
	toolbox : {
		show : false,
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
		name : 'CPU',
		type : 'gauge',
		axisLabel : {
			textStyle : {
				color : '#FFFFFF'
			}
		},
		detail : {
			formatter : 'CPU\n\r{value}%',
			textStyle : {
				color : '#FFFFFF',
				fontSize : 20,
				fontWeight : 100
			}
		},
		data : [ {
			value : 50.45,
			name : '使用率'
		} ]
	} ]
};
/* 柱形图 */
var optionBarJb = {
	title : {
		x : 'center',
		text : 'TOP10疾病',
		// subtext : '2015年9月',
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
		data : []
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
			formatter : '{value}万次',
			textStyle : {
				color : '#FFFFFF',
				fontSize : 12
			}
		}
	}, {
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
			formatter : '{value}万',
			textStyle : {
				color : '#FFFFFF',
				fontSize : 12
			}
		}
	} ],
	series : [ {
		name : '疾病发病次数（单位：万次）',
		type : 'bar',
		barWidth : 20,
		itemStyle : {
			normal : {
				color : '#00479d',
				label : {
					show : false,
					position : 'top',
					formatter : '{c}万次'
				}
			}
		},
		data : []

	}, {
		name : '疾病补偿金额（单位：万元）',
		yAxisIndex : 1,
		symbol : 'emptyCircle',
		symbolSize : 4,
		type : 'line',
		itemStyle : {
			normal : {
				color : '#00479d',
				label : {
					show : false,
					position : 'top',
					formatter : '{c}万元'
				},
				lineStyle : {
					color : '#02adf3',
				}
			}
		},
		data : []

	} ]
};
/* 柱形图 */
var optionBarYp = {
	title : {
		x : 'center',
		text : 'TOP10药品',
		// subtext : '2015年9月',
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
		data : []
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
			formatter : '{value}万次',
			textStyle : {
				color : '#FFFFFF',
				fontSize : 12
			}
		}
	}, {
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
	} ],
	series : [
			{
				name : '药品使用次数（单位：次）',
				type : 'bar',
				barWidth : 20,
				itemStyle : {
					normal : {
						color : '#00479d',
						label : {
							show : false,
							position : 'top',
							formatter : '{c}'
						}
					}
				},
				data : []

			},
			{
				name : '药品总费用（单位：万次）',
				symbol : 'emptyCircle',
				symbolSize : 4,
				yAxisIndex : 1,
				type : 'line',
				itemStyle : {
					normal : {
						color : '#00479d',
						label : {
							show : false,
							position : 'top',
							formatter : '{c}万元'
						},
						lineStyle : {
							color : '#02adf3',
						}
					}
				},
				data : [ 58677, 68770900, 798900, 4, 5867747, 5, 6, 5, 25, 23,
						5867730 ]

			} ]
};
