var myGauge;
var myBar;
var myLine;
var myPie;
var myMap;

$(function () {
    myGauge = echarts.init(document.getElementById('transfinite'));
    myBar = echarts.init(document.getElementById('barChart'));
    myLine = echarts.init(document.getElementById('lineChart'));
    myPie = echarts.init(document.getElementById('pieChart'));
    loadMap();
    myGauge.setOption(optionGauge);
    myBar.setOption(optionBar);
    myLine.setOption(optionLine);
    myPie.setOption(optionPie);
    updata();
});
var optionGauge = {
    series: [{
        name: '超限率',
        type: 'gauge',
        radius: '100%',
        splitNumber: 10, // 分割段数，默认为5
        axisLine: { // 坐标轴线
            lineStyle: { // 属性lineStyle控制线条样式
                color: [
                    [0.2, '#228b22'],
                    [0.8, '#48b'],
                    [1, '#ff4500']
                ],
                width: 5
            }
        },
        axisTick: { // 坐标轴小标记
            splitNumber: 5, // 每份split细分多少段
            length: 12, // 属性length控制线长
            lineStyle: { // 属性lineStyle控制线条样式
                color: 'auto'
            }
        },
        axisLabel: { // 坐标轴文本标签，详见axis.axisLabel
            textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                color: 'auto'
            }
        },
        splitLine: { // 分隔线
            show: true, // 默认显示，属性show控制显示与否
            length: 14, // 属性length控制线长
            lineStyle: { // 属性lineStyle（详见lineStyle）控制线条样式
                color: 'auto'
            }
        },
        pointer: {
            width: 5
        },
        title: {
            show: true,
            offsetCenter: ['-135%', '-78%'], // x, y，单位px
            textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                fontWeight: 'bolder',
                fontSize: 28,
                color: '#01F0FF'
            }
        },
        detail: {
            formatter: '{value}%',
            textStyle: { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                color: 'auto',
                fontWeight: 'bolder',
                fontSize: 20,
            }
        },
        data: [{
            value: 50,
            name: '超限率'
        }]
    }]
};
var optionBar = {
    grid: {
        x: 30,
        y: 10,
        width: '88%',
        height: '88%',
        borderWidth: '0'
    },
    calculable: true,
    xAxis: [{
        splitLine: {
            show: false
        },
        type: 'category',
        data: [1],
        axisLabel: {
            show: true,
            interval: 'auto', // {number}
            //					rotate: -45,
            margin: 4,
            textStyle: {
                color: '#fff',
                fontFamily: 'verdana',
                fontSize: 10,
                fontStyle: 'normal',
                fontWeight: 'bold'
            }
        },
    }],
    yAxis: [{
        splitLine: {
            show: false
        },
        type: 'value',
        axisLabel: {
            show: true,
            interval: 'auto', // {number}
            //					rotate: -45,
            margin: 5,
            textStyle: {
                color: '#fff',
                fontFamily: 'verdana',
                fontSize: 10,
                fontStyle: 'normal',
                fontWeight: 'bold'
            }
        },
    }],
    series: [{
        name: '',
        type: 'bar',
        barWidth: 21, // 系列级个性化，柱形宽度
        itemStyle: {
            normal: { // 系列级个性化，横向渐变填充
                borderRadius: 5,
                color: function (params) {
                    // build a color map as your need.
                    var colorList = [
                        '#003CFF', '#FF971C', '#EF4F4F', '#FFFF00', '#22AC38',
                        '#8400FF', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
                        '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0'
                    ];
                    return colorList[params.dataIndex]
                },
                label: {
                    show: true,
                    textStyle: {
                        fontSize: '10',
                        fontFamily: '微软雅黑',
                        fontWeight: 'bold',
                        color: '#FFFFFF'
                    }
                }
            }
        },
        data: [1],
    }]
};
var optionLine = {
    tooltip: {
        trigger: 'axis'
    },
    grid: {
        x: 30,
        y: 10,
        width: '88%',
        height: '88%',
        borderWidth: '0'
    },
    calculable: true,
    xAxis: [{
        splitLine: {
            show: false
        },
        type: 'category',
        boundaryGap: false,
        data: ['1时', '2时', '3时', '4时', '5时', '6时',
            '7时', '8时', '9时', '10时', '11时', '12时',
            '13时', '14时', '15时', '16时', '17时', '18时',
            '19时', '20时', '21时', '22时', '23时', '24时'
        ],
        axisLabel: {
            show: true,
            interval: 'auto', // {number}
            //					rotate: -45,
            margin: 5,
            textStyle: {
                color: '#fff',
                fontFamily: 'verdana',
                fontSize: 10,
                fontStyle: 'normal',
                fontWeight: 'bold'
            }
        },
    }],
    yAxis: [{
        splitLine: {
            show: false
        },
        type: 'value',
        axisLabel: {
            show: true,
            interval: 'auto', // {number}
            //					rotate: -45,
            margin: 5,
            textStyle: {
                color: '#fff',
                fontFamily: 'verdana',
                fontSize: 10,
                fontStyle: 'normal',
                fontWeight: 'bold'
            }
        },
    }],
    series: [{
        type: 'line',
        smooth: true,
        symbol: 'emptyCircle',
        lineStyle: {
            width: 0.1,
        },
        itemStyle: {
            normal: {
                lineStyle: { // 系列级个性化折线样式，横向渐变描边
                    width: 6,
                    color: {
                        type: 'linear',
                        x: 0,
                        y: 0,
                        x2: 1,
                        y2: 0,
                        colorStops: [{
                            offset: 0,
                            color: '#6600FF' // 0% 处的颜色
                        },
                            {
                                offset: 0.33,
                                color: '#0D7BFF' // 100% 处的颜色
                            },
                            {
                                offset: 0.66,
                                color: '#01F0FF' // 100% 处的颜色
                            },
                            {
                                offset: 1,
                                color: '#D5ED51' // 100% 处的颜色
                            }
                        ],
                        globalCoord: false // 缺省为 false
                    },
                }
            },
            emphasis: {
                label: {
                    show: true
                }
            }
        },
        data: [
            16, 7, 27, 8, 11, 18, 9, 8, 13
        ]
    }]
};
var optionPie = {
    visualMap: {
        show: false,
        min: 80,
        max: 600,
        inRange: {
            colorLightness: [0, 1]
        }
    },
    series: [{
        type: 'pie',
        radius: '70%',
        center: ['50%', '50%'],
        data: [{
            value: 274,
            name: '已审核'
        },
            {
                value: 235,
                name: '待审核'
            },
            {
                value: 400,
                name: '已执行'
            }
        ].sort(function (a, b) {
            return a.value - b.value;
        }),
        roseType: 'radius',
        label: {
            normal: {
                formatter: '{b}:{c}',
                borderColor: '#aaa',
                fontSize: 14,
                color: '#f4f4f4',
                }
        },
        labelLine: {
            normal: {
                lineStyle: {
                    color: 'rgba(255, 255, 255, 1)'
                },
                smooth: 0.2,
                length: 10,
                length2: 20
            }
        },
        itemStyle: {
            normal: {
                color: function (params) {
                    var colorList = [
                        '#4167E2', '#5BE0F1', '#5C7DEE'
                    ];
                    return colorList[params.dataIndex]
                },
                shadowBlur: 600,
                shadowColor: 'rgba(0, 0, 0, 1)'
            }
        },

        animationType: 'scale',
        animationEasing: 'elasticOut',
        animationDelay: function (idx) {
            return Math.random() * 200;
        }
    }]
};

var optionMap = {
    geo: {
        zoom: 1.25,
        map: 'lixian',
        itemStyle: {
            normal: {
                borderWidth: 0, // 省份的边框宽度
                areaColor: 'rgba(0,0,0,0)',
            }
        }
    },
    tooltip: {
        trigger: 'none',
        showDelay: 0,
        triggerOn: 'click',
        alwaysShowContent: true,
        backgroundColor: 'rgba(255,0,0,0)',
        formatter: function (params) {
            return '<div style="z-index=0;" class="tooltip-map">' +
                '<div class="tooltip-title">' +
                '<span>' + params.data.name + '</span>' +
                '</div>' +
                '<div class="tooltip-warp">' +
                '<p><span class="msg">通行记录（' + params.data.trafficCount + '）</span><span class="infobtn"><a href="javascript:preview(\'' + params.data.siteId + '\')">查看详情</a></span></p>' +
                '<p><span class="msg">超限数（' + params.data.illegalCount + '）      </span><span class="infobtn"><a href="javascript:preview(\'' + params.data.siteId + '\',\'0\')">查看详情</a></span></p>' +
                '</div>' +
                '<div class="tooltip-xc">' +
                '<a href="javascript:siteLive(\'' + params.data.siteId + '\')">查看现场监控</a>' +
                '</div>' +
                '</div>';
        },
    },
    series: [
        {
            name: '澧县地图',
            type: 'map',
            zoom: 1.25,
            map: "lixian",
            mapType: '澧县',
            selectedMode: 'single',
            itemStyle: {
                normal: {
                    borderWidth: 2, // 省份的边框宽度
                    borderColor: '#0195D3', // 省份的边框颜色
                    areaColor: 'rgba(0,0,0,0)',
                },
                emphasis: {
                    label: {
                        show: true,
                        color: '#f4f4f4',
                        fontSize: 15,
                        fontweight: 'bolder'
                    },
                    borderColor: '#0195D3',
                    areaColor: '#07142b',
                }
            },
            data: [],
            markPoint: {
                symbolSize: 25,
                itemStyle: {
                    normal: {
                        color: 'orange',
                        label: {
                            show: false
                        },
                    },
                    emphasis: {
                        color: '#ff0',
                        label: {
                            show: false
                        },
                    }
                },
                data: []
            },
            textFixed: {
                '澧西街道': [0, 30],
                '澧浦街道': [0, -10],
                '城头山镇': [-10, -20],
                '澧阳街道': [0, -10],
                '澧澹街道': [0, 15],
            },
        },
        {
            type: 'effectScatter',
            coordinateSystem: 'geo',
            data: [{
                value: []
            }],
            rippleEffect: {
                period: 4,
                scale: 10,
                brushType: 'fill'
            },
            itemStyle: {
                normal: {
                    color: '#f00',
                }
            },
            symbolSize: 15,
            zlevel: 0,
        }
    ]
};

function loadMap() {
    myMap = echarts.init(document.getElementById('mapChart'));
    $.get('geojson/china-main-city/430723.json', function (geoJson) {
        echarts.registerMap('lixian', geoJson);
        myMap.setOption(optionMap);
    });
}