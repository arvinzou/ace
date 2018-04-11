var option1 =
    {
        title: {
            text: '分时段通行情况',
            subtext: ''
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            data: ['违章记录数']
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: false},
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: false,
        dataZoom: {
            show: true,
            realtime: true,
            start: 0,
            end: 50
        },
        xAxis: [{
            type: 'category',
            boundaryGap: false,
            data: ['0~1', '1~2', '2~3', '3~4', '4~5', '5~6', '6-7', '7~8','8~9','9~10', '10~11', '11~12',
                 '12~13', '13~14', '14~15', '15-16', '16~17','17~18','18~19','19~20','20~21', '21~22', '22~23','23~24'],
            axisLabel: {
                interval: 0,
                formatter: '{value} 时'
            },

        }],
        yAxis: [{
            type: 'value',
            axisLabel: {
                formatter: '{value} 次'
            }
        }],
        series: [
            {
                name: '违章记录数',
                type: 'line',
                data: [0, 1, 2, 3, 4, 5, 6, 0, 4, 3, 2, 1],
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            }
        ]
    };

var option2 = {
    title: {
        text: '每日通行情况',
        subtext: '',
    },
    tooltip: {
        trigger: 'item'
    },
    legend: {
        data: ['违章记录数']
    },
    toolbox: {
        show: true,
        feature: {
            mark: {show: false},
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    calculable: false,
    dataZoom: {
        show: true,
        realtime: true,
        start: 0,
        end: 50
    },
    xAxis: [{
        type: 'category',
        boundaryGap: false,
        data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12',
            '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30'],
        axisLabel: {
            interval: 'auto',
            formatter: '{value} 日'
        }
    }],
    yAxis: [{
        type: 'value',
        axisLabel: {
            formatter: '{value} 次'
        }
    }],
    series: [
        {
            name: '违章记录数',
            type: 'line',
            data: [0, 1, 2, 3, 4, 5, 6, 0, 4, 3, 2, 1],
            markPoint: {
                data: [
                    {type: 'max', name: '最大值'},
                    {type: 'min', name: '最小值'}
                ]
            },
            markLine: {
                data: [
                    {type: 'average', name: '平均值'}
                ]
            }
        }
    ]
};

var ecConfig = {
    EVENT: {
        // -------全局通用
        REFRESH: 'refresh',
        RESTORE: 'restore',
        RESIZE: 'resize',
        CLICK: 'click',
        DBLCLICK: 'dblclick',
        HOVER: 'hover',
        MOUSEOUT: 'mouseout',
        // MOUSEWHEEL: 'mousewheel',
        // -------业务交互逻辑
        DATA_CHANGED: 'dataChanged',
        DATA_ZOOM: 'dataZoom',
        DATA_RANGE: 'dataRange',
        DATA_RANGE_SELECTED: 'dataRangeSelected',
        DATA_RANGE_HOVERLINK: 'dataRangeHoverLink',
        LEGEND_SELECTED: 'legendSelected',
        LEGEND_HOVERLINK: 'legendHoverLink',
        MAP_SELECTED: 'mapSelected',
        PIE_SELECTED: 'pieSelected',
        MAGIC_TYPE_CHANGED: 'magicTypeChanged',
        DATA_VIEW_CHANGED: 'dataViewChanged',
        TIMELINE_CHANGED: 'timelineChanged',
        MAP_ROAM: 'mapRoam',
        FORCE_LAYOUT_END: 'forceLayoutEnd',
        // -------内部通信
        TOOLTIP_HOVER: 'tooltipHover',
        TOOLTIP_IN_GRID: 'tooltipInGrid',
        TOOLTIP_OUT_GRID: 'tooltipOutGrid',
        ROAMCONTROLLER: 'roamController'
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
