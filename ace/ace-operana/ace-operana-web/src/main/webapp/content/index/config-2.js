var option2 = {
	title : {
		x : 'center',
		text : '',
		subtext : '',
		textStyle : {
			color : '#000000'
		}
	},
	 legend: {
            data:['任务','已关闭']
        },
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
		name : '任务',
		type : 'bar',
		markPoint : {
			data : [{
				type : 'max',
				name : '最大值'
			}, {
				type : 'min',
				name : '最小值'
			}]
		},
		markLine : {
			data : [{
				type : 'average',
				name : '平均值'
			}]
		},
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
	},{
      		name : '已关闭',
      		type : 'bar',
      		markPoint : {
      			data : [{
      				type : 'max',
      				name : '最大值'
      			}, {
      				type : 'min',
      				name : '最小值'
      			}]
      		},
      		markLine : {
      			data : [{
      				type : 'average',
      				name : '平均值'
      			}]
      		},
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
      	}]
};
