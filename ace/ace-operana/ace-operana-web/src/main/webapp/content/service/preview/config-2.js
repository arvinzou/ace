var option2 = {
	title : {
		x : 'center',
		text : 'TOP10问题分析',
		subtext : '',
		textStyle : {
			color : '#000000'
		}
	},
	color : ['#3398DB', 'red'],
	tooltip : {
		trigger : 'axis',
		axisPointer : {
			type : 'cross',
			crossStyle : {
				color : 'red'
			}
		}
	},
	calculable : true,
	grid : {
		x : 60,
		x2 : 40,
		// borderColor:'blue',
		y : 30,
		y2 : 60
	},
	toolbox : {
		feature : {
			dataView : {
				show : true,
				readOnly : false
			},
			magicType : {
				show : true,
				type : ['bar']
			},
			restore : {
				show : true
			},
			saveAsImage : {
				show : true
			}
		}
	},
	legend : {
		x : 'center',
		y : 'bottom',
		data : ['数量', '累计百分比']
	},
	xAxis : [{
		type : 'category',
		data : [],
		axisPointer : {
			type : 'shadow'
		},
		splitLine : {
			show : false
		}
	}],
	yAxis : [{
		type : 'value',
		name : '',
		axisLabel : {
			formatter : '{value} '
		}
	}, {
		type : 'value',
		name : '',
		axisLabel : {
			formatter : '{value}% '
		},
		splitLine : {
			show : false
		}
	}],
	series : [{
		name : '数量',
		type : 'bar',
		//barWidth : 40,
		data : [],
		itemStyle : {
			normal : {
				label : {
					show : true,
					position : 'top',
					formatter : '{c}',
					textStyle : {
						color : '#000000'
					}
				}
			}
		}
	}, {
		name : '累计百分比',
		type : 'line',
		yAxisIndex : 1,
		data : [],
		itemStyle : {
			normal : {
				label : {
					show : true,
					position : 'bottom',
					formatter : '{c}%',
					textStyle : {
						color : '#000000'
					}
				}
			}
		}
	}]
};
