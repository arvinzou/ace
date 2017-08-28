var option1 = {
	    title : {
	        text: '',
	        subtext: ''
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['会员企业数量(家)','行业企业数量(家)']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            data : []
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        },{
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'会员企业数量(家)',
	            type:'bar',
	            data:[],
	            markPoint : {
	                data : [
	                        {type : 'max', name: '最大值'},
	                        {type : 'min', name: '最小值'}
	                    ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name: '平均值'}
	                ]
	            }
	            /*,itemStyle: {
	                normal: {
	                    label: {
	                        show: true,
	                        position: 'top',
	                        formatter: '{c}'
	                    }
	                }
	            }*/
	        },
	        {
	            name:'行业企业数量(家)',
	            type:'bar',
	            data:[],
	            markPoint : {
	                data : [
	                        {type : 'max', name: '最大值'},
	                        {type : 'min', name: '最小值'}
	                    ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name: '平均值'}
	                ]
	            }
	           /* ,itemStyle: {
	                normal: {
	                    label: {
	                        show: true,
	                        position: 'top',
	                        formatter: '{c}'
	                    }
	                }
	            }*/
	        }
	    ]
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
