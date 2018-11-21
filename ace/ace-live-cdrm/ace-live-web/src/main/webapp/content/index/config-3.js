var option3 = {
		title : {
			text : '',
			subtext : ''
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c}%"
		},
		toolbox : {
			feature : {
				dataView : {
					readOnly : false
				},
				restore : {},
				saveAsImage : {}
			}
		},
		legend : {
			data : [  ]
		},
	    calculable : true,
		series : [

		{
			name : '会员数',
			type : 'funnel',
			left : '0%',
			width : '50%',
			maxSize : '80%',
			  sort : 'ascending',
			label : {
				normal : {
					position : 'inside',
					formatter : '{a}',
					textStyle : {
						color : '#fff'
					}
				},
				emphasis : {
					position : 'inside',
					formatter : '{a}'
				}
			},
			itemStyle : {
				normal : {
					opacity : 0.5,
					borderColor : '#fff',
					borderWidth : 2
				}
			},
			funnelAlign : 'top',
			data : []
		} ]
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
