var option1 = {
	color : ['#3398DB', 'red'],
	tooltip : {
		trigger : 'axis',
		axisPointer : {
			type : 'cross',
			crossStyle : {
				color : '#999'
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
		data : ['指标值', {
			name : '目标值',
			textStyle : {
				color : 'red'
			}
		}]
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
		name : '指标值',
		axisLabel : {
			formatter : '{value} '
		}
	}],
	series : [{
		name : '指标值',
		type : 'bar',
		barWidth : 40,
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
		},
		markLine : {
			itemStyle : {
				normal : {
					lineStyle : {
						type : 'solid',
						color : 'red'
					},
					label : {
						show : true,
						position : 'right',
						formatter : '目标值{c}',
						textStyle : {
							color : 'red'
						}
					}
				}
			},
			data : [[{
				name : 'A',
				value : 0.03,
				xAxis : -1,
				yAxis : 0.03
			}, {
				name : 'B',
				xAxis : '2017年累计',
				yAxis : 0.03
			}]]
		},
	}, {
		name : '目标值',
		type : 'line',
		yAxisIndex : 1,
		data : []
	}]
};

var ecConfig = {
	EVENT : {
		// -------全局通用
		REFRESH : 'refresh',
		RESTORE : 'restore',
		RESIZE : 'resize',
		CLICK : 'click',
		DBLCLICK : 'dblclick',
		HOVER : 'hover',
		MOUSEOUT : 'mouseout',
		// MOUSEWHEEL: 'mousewheel',
		// -------业务交互逻辑
		DATA_CHANGED : 'dataChanged',
		DATA_ZOOM : 'dataZoom',
		DATA_RANGE : 'dataRange',
		DATA_RANGE_SELECTED : 'dataRangeSelected',
		DATA_RANGE_HOVERLINK : 'dataRangeHoverLink',
		LEGEND_SELECTED : 'legendSelected',
		LEGEND_HOVERLINK : 'legendHoverLink',
		MAP_SELECTED : 'mapSelected',
		PIE_SELECTED : 'pieSelected',
		MAGIC_TYPE_CHANGED : 'magicTypeChanged',
		DATA_VIEW_CHANGED : 'dataViewChanged',
		TIMELINE_CHANGED : 'timelineChanged',
		MAP_ROAM : 'mapRoam',
		FORCE_LAYOUT_END : 'forceLayoutEnd',
		// -------内部通信
		TOOLTIP_HOVER : 'tooltipHover',
		TOOLTIP_IN_GRID : 'tooltipInGrid',
		TOOLTIP_OUT_GRID : 'tooltipOutGrid',
		ROAMCONTROLLER : 'roamController'
	}
};
function eConsole(param) {
	var mes = '【' + param.type + '】';
	if (typeof param.seriesIndex != 'undefined') {
		mes += '  seriesIndex : ' + param.seriesIndex;
		mes += '  dataIndex : ' + param.dataIndex;
	}
	if (param.type == 'hover') {
		document.getElementById('hover-console').innerHTML = 'Event Console : '
				+ mes;
	} else {
		document.getElementById('console').innerHTML = mes;
	}
	console.log(param);
}
