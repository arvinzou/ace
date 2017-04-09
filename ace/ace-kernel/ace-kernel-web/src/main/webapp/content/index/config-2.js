var names=[];
var number=[];
var totalCost=[];
function getElemPos(){
option2 = {
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['违规病例数(次)','违规金额(万元)']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: false},
            dataView : {readOnly:false},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    dataZoom : {
        show : false,
        realtime : true,
        start : 0,
        end : 100
    },
    xAxis : [
        {
        	
        	axisLabel: {
                rotate: 60,
            },    
            type : 'category',
            boundaryGap : true,
            data : function (){
                var list = names;
               
                return list;
            }()
        }
    ],
    yAxis : [
        {
            type : 'value',
            show : true,
			splitArea : {
				show : false
			},
			splitLine : {
				show : false
			},
            axisLabel : {
                formatter: '{value} 次'
            }	
        },{
			type : 'value',
			show : true,
			splitArea : {
				show : false
			},
			splitLine : {
				show : false
			},
			axisLabel : {
                formatter: '{value} 万元'
            }
  
			
		}
    ],
    series : [
		{
		    name:'违规病例数(次)',
		    type:'bar',
		    data:number
		},
        {
    		name : '违规金额(万元)',
    		yAxisIndex: 1,
    		symbol:'emptydiamond',
    		symbolSize:4,
    		type : 'line',
    		data : totalCost
        }
        
    ]
}};
function eConsole(param) {
    var mes = '【' + param.type + '】';
    if (typeof param.seriesIndex != 'undefined') {
        mes += '  seriesIndex : ' + param.seriesIndex;
        mes += '  dataIndex : ' + param.dataIndex;
    }
    if (param.type == 'hover') {
        document.getElementById('hover-console').innerHTML = 'Event Console : ' + mes;
    }
    else {
        document.getElementById('console').innerHTML = mes;
    }
    console.log(param);
}
/*
// -------全局通用
REFRESH: 'refresh',
RESTORE: 'restore',
RESIZE: 'resize',
CLICK: 'click',
DBLCLICK: 'dblclick',
HOVER: 'hover',
MOUSEOUT: 'mouseout',
// -------业务交互逻辑
DATA_CHANGED: 'dataChanged',
DATA_ZOOM: 'dataZoom',
DATA_RANGE: 'dataRange',
DATA_RANGE_HOVERLINK: 'dataRangeHoverLink',
LEGEND_SELECTED: 'legendSelected',
LEGEND_HOVERLINK: 'legendHoverLink',
MAP_SELECTED: 'mapSelected',
PIE_SELECTED: 'pieSelected',
MAGIC_TYPE_CHANGED: 'magicTypeChanged',
DATA_VIEW_CHANGED: 'dataViewChanged',
TIMELINE_CHANGED: 'timelineChanged',
MAP_ROAM: 'mapRoam',
*/
/*myChart2.on(ecConfig.EVENT.CLICK, eConsole);
myChart2.on(ecConfig.EVENT.DBLCLICK, eConsole);
//myChart.on(ecConfig.EVENT.HOVER, eConsole);
myChart2.on(ecConfig.EVENT.DATA_ZOOM, eConsole);
myChart2.on(ecConfig.EVENT.LEGEND_SELECTED, eConsole);
myChart2.on(ecConfig.EVENT.MAGIC_TYPE_CHANGED, eConsole);
myChart2.on(ecConfig.EVENT.DATA_VIEW_CHANGED, eConsole);
*/
                    