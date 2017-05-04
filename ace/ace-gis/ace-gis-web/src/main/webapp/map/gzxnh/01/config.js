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
		// formatter : '{b}<br>蓝色：参合人员（单位：人）<br>红色：统筹资金使用（单位：百万）'
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
			res = params.name + '<br>参合人员:' + v1 + '（单位：万人）<br>统筹资金使用:' + v2
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
			name : '参合人员(单位：万人)',
			textStyle : {
				color : '#00ffff'
			}
		}, {
			name : '资金使用(单位：万元)',
			textStyle : {
				color : '#FF7E00'
			}
		}, {
			name : '跨区补偿人次(单位：人)',
			textStyle : {
				color : 'green'
			}
		} ],
		textStyle:{
			color:'auto'
		},
		  selected: {
	            '参合人员(单位：万人)' : true,
	            '资金使用(单位：万元)' : true,
	            '跨区补偿人次(单位：人)' : true
	        }
	},

	series : [ {
		name : '参合人员(单位：万人)',
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

var optionGauge = {
	color : '#FFFFFF',
	tooltip : {
		formatter : "{a} <br/>{b} : {c}%"
	},
	series : [ {
		name : '资金使用',
		type : 'gauge',
		itemStyle : {
			normal : {
				label : {
					show : true,
					textStyle : {
						color : '#fff',
						fontSize:28
					}
				}
			},
			emphasis : {
				label : {
					show : true
				}
			}
		},
		axisLabel : {
			textStyle : {
				color : '#FFFFFF'
			}
		},
		detail : {
			formatter : '资金使用\n\r{value}%',
			textStyle : {
				color : '#FFFFFF',
				fontSize : 20
			}
		},
		data : [ {
			value : 50.45,
			name : ''
		} ]
	} ]
};
/* 柱形图 */
var optionBar = {
	title : {
		x : 'center',
		text : '贵州参合情况统计(单位：万人)',
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
				fontSize : 12
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
			formatter : '{value}万',
			textStyle : {
				color : '#FFFFFF',
				fontSize : 18
			}
		}
	} ],
	series : [ {
		name : '地区参合情况（单位：万人）',
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
