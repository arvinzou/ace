var option1 = {
	title : {
		x : 'center',
		text : '',
		subtext : '',
		textStyle : {
			color : '#000000'
		}
	},
	color : [ 'red'],
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
		borderWidth : 1,
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
		x : 'right',
		data : []
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
	}],
	series : [{
		name : '指标值',
		type : 'bar',
		//barWidth : 20,
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
				},
				color : function(params) {
					var colorList = ['#EA0000', '#FF9797', '#2894FF',
							'#F9F900', '#00E3E3', '#00A600', '#6F00D2',
							'#EA0000', '#FF9797', '#2894FF', '#F9F900',
							'#00E3E3', '#00A600', '#6F00D2', '#EA0000',
							'#FF9797', '#2894FF', '#F9F900', '#00E3E3',
							'#00A600', '#6F00D2', '#EA0000', '#FF9797',
							'#2894FF', '#F9F900', '#00E3E3', '#00A600',
							'#6F00D2'];
					//return colorList[params.dataIndex];
					return '#3398DB';
				}
			}
		},
		markLine : {
		symbol:'circle',
		symbolSize:2,
			itemStyle : {
				normal : {
					lineStyle : {
						type : 'solid',
						color : 'red'
					},
					label : {
						show : true,
						position : 'end',
						color : 'red',
						formatter : '{c}',
						textStyle : {
							color : 'red'
						}
					}
				}
			},
			data : [[{

				value : 1,
				xAxis : -1,
				yAxis : 0.045
			}, {

				xAxis : '1月',
				yAxis : 0.05
			}], [{

				xAxis : '1月',
				yAxis : 0.05
			}, {

				xAxis : '2017年累计',
				yAxis : 0.045
			}]]
		},
	}]
};
var oj = [[{
	"value" : 0.65,
	"xAxis" : -1,
	"yAxis" : 0.65
}, {
	"xAxis" : "2016年",
	"yAxis" : 0.65
}], [{
	"xAxis" : "2016年",
	"yAxis" : 0.65
}, {
	"xAxis" : "1月",
	"yAxis" : 0.3
}], [{
	"xAxis" : "1月",
	"yAxis" : 0.3
}, {
	"xAxis" : "2月",
	"yAxis" : 0.3
}], [{
	"xAxis" : "2月",
	"yAxis" : 0.3
}, {
	"xAxis" : "9周",
	"yAxis" : 0.3
}], [{
	"xAxis" : "9周",
	"yAxis" : 0.3
}, {
	"xAxis" : "10周",
	"yAxis" : 0.3
}], [{
	"xAxis" : "10周",
	"yAxis" : 0.3
}, {
	"xAxis" : "2017年累计",
	"yAxis" : 0.3
}]];