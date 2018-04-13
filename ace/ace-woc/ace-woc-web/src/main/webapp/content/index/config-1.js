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

require.config({
    paths: {
        echarts: 'http://echarts.baidu.com/build/dist'
    }
});

require(
    [
        'echarts',
        'echarts/chart/gauge', // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
        'echarts/chart/bar', // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
        //                'echarts/chart/bar',
        //				'echarts/chart/pie'
    ],
    function (ec) {
        var ct4 = ec.init(document.getElementById('ct3'), 'infographic');
        var ct3 = ec.init(document.getElementById('ct4'), 'infographic');
        option3 = {
            tooltip: {
                formatter: "{a} <br/>{b} : {c}%"
            },
            toolbox: {
                show: false,
                feature: {
                    mark: {show: true},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            series: [
                {
                    name: '案件处理率',
                    type: 'gauge',
                    center: ['50%', '50%'],    // 默认全局居中
                    radius: [0, '95%'],
                    startAngle: 140,
                    endAngle: -140,
                    min: 0,                     // 最小值
                    max: 100,                   // 最大值
                    precision: 0,               // 小数精度，默认为0，无小数点
                    splitNumber: 10,             // 分割段数，默认为5
                    axisLine: {            // 坐标轴线
                        show: true,        // 默认显示，属性show控制显示与否
                        lineStyle: {       // 属性lineStyle控制线条样式
                            color: [[0.4, 'lightgreen'], [0.6, 'orange'], [0.8, 'skyblue'], [1, '#ff4500']],
                            width: 40
                        }
                    },
                    axisTick: {            // 坐标轴小标记
                        show: true,        // 属性show控制显示与否，默认不显示
                        splitNumber: 5,    // 每份split细分多少段
                        length: 12,         // 属性length控制线长
                        lineStyle: {       // 属性lineStyle控制线条样式
                            color: '#eee',
                            width: 1,
                            type: 'solid'
                        }
                    },
                    axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
                        show: true,
                        formatter: function (v) {
                            switch (v + '') {
                                case '20':
                                    return '弱';
                                case '50':
                                    return '低';
                                case '70':
                                    return '中';
                                case '90':
                                    return '高';
                                default:
                                    return '';
                            }
                        },
                        textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                            color: '#333'
                        }
                    },
                    splitLine: {           // 分隔线
                        show: true,        // 默认显示，属性show控制显示与否
                        length: 30,         // 属性length控制线长
                        lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                            color: '#eee',
                            width: 2,
                            type: 'solid'
                        }
                    },
                    pointer: {
                        length: '80%',
                        width: 8,
                        color: 'auto'
                    },

                    title: {
                        show: true,
                        offsetCenter: ['-65%', -10],       // x, y，单位px
                        textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                            color: '#333',
                            fontSize: 15
                        }
                    },
                    detail: {
                        show: true,
                        backgroundColor: 'rgba(0,0,0,0)',
                        borderWidth: 0,
                        borderColor: '#ccc',
                        width: 100,
                        height: 40,
                        offsetCenter: ['-60%', 10],       // x, y，单位px
                        formatter: '{value}%',
                        textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                            color: 'auto',
                            fontSize: 30
                        }
                    },
                    data: [{value: 50, name: '案件处理率'}]
                }
            ]
        };
        option4 = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data: ['卡点一', '卡点二', '卡点三', '卡点四', '卡点五']
            },
            toolbox: {
                show: false,
                feature: {
                    mark: {show: true},
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            calculable: true,
            xAxis: [
                {
                    type: 'value'
                }
            ],
            yAxis: [
                {
                    type: 'category',
                    data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
                }
            ],
            series: [
                {
                    name: '卡点一',
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {normal: {label: {show: true, position: 'insideRight'}}},
                    data: [320, 302, 301, 334, 390, 330, 320]
                },
                {
                    name: '卡点二',
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {normal: {label: {show: true, position: 'insideRight'}}},
                    data: [120, 132, 101, 134, 90, 230, 210]
                },
                {
                    name: '卡点三',
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {normal: {label: {show: true, position: 'insideRight'}}},
                    data: [220, 182, 191, 234, 290, 330, 310]
                },
                {
                    name: '卡点四',
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {normal: {label: {show: true, position: 'insideRight'}}},
                    data: [150, 212, 201, 154, 190, 330, 410]
                },
                {
                    name: '卡点五',
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {normal: {label: {show: true, position: 'insideRight'}}},
                    data: [820, 832, 901, 934, 1290, 1330, 1320]
                }
            ]
        };
        ct3.setOption(option3);
        ct4.setOption(option4);
    }
);

