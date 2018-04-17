require.config({
    paths: {
        echarts: 'http://echarts.baidu.com/build/dist'
    }
});

// 使用
require(
    [
        'echarts',
        'echarts/chart/gauge', // 使用柱状图就加载bar模块，按需加载
        'echarts/chart/bar',
        'echarts/chart/line',
        'echarts/chart/pie',
        'echarts/chart/map',
    ],
    function (ec) {
        // 基于准备好的dom，初始化echarts图表
        var mapGeoData = require('echarts/util/mapData/params');
        mapGeoData.params['澧县'] = {
            getGeoJson: (function (c) {
                return function (callback) {
                    $.getJSON('geojson/china-main-city/430723.json', callback);
                }
            })('澧县')
        }

        var myGauge = ec.init(document.getElementById('transfinite'));
        var myBar = ec.init(document.getElementById('barChart'));
        var myLine = ec.init(document.getElementById('lineChart'));
        var myPie = ec.init(document.getElementById('pieChart'));
        var myMap = ec.init(document.getElementById('mapChart'));
        /*仪表*/
        optionGauge = {
//			tooltip: {
//				formatter: "{a} <br/>{b} : {c}%"
//			},
            series: [{
                name: '超限率',
                type: 'gauge',
                radius: ['90%', '95%'],
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
                    offsetCenter: ['-140%', '-80%'], // x, y，单位px
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
                        fontSize: 28,
                    }
                },
                data: [{
                    value: 50,
                    name: '超限率'
                }]
            }]
        };
        /*柱形图*/
        optionBar = {
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
                name: '搜索引擎',
                type: 'bar',
                barWidth: 21, // 系列级个性化，柱形宽度
                itemStyle: {
                    normal: { // 系列级个性化，横向渐变填充
                        borderRadius: 5,
                        color: function (params) {
                            // build a color map as your need.
                            var colorList = [
                                '#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B',
                                '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
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
                data: [1
                ],
            }]
        };
        //仪表		clearInterval(timeTicket);
        //		timeTicket = setInterval(function() {
        //			option.series[0].data[0].value = (Math.random() * 100).toFixed(2) - 0;
        //			myMapChart.setOption(option, true);
        //		}, 2000)
        optionLine = {
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
                data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24'],
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
                            width: 2,
                            color: (function () {
                                var zrColor = require('zrender/tool/color');
                                return zrColor.getLinearGradient(
                                    0, 0, 1000, 0, [
                                        [0, 'rgba(255,0,0,0.8)'],
                                        [0.8, 'rgba(255,255,0,0.8)']
                                    ]
                                )
                            })(),
                            shadowColor: 'rgba(0,0,0,0.5)',
                            shadowBlur: 20,
                            shadowOffsetX: 8,
                            shadowOffsetY: 8
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
        // 为echarts对象加载数据
        optionPie = {
            visualMap: {
                show: false,
                min: 80,
                max: 600,
                inRange: {
                    colorLightness: [0, 1]
                }
            },
            series: [{
                name: '访问来源',
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
                        textStyle: {
                            color: 'rgba(255, 255, 255, 0.3)'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        lineStyle: {
                            color: 'rgba(255, 255, 255, 0.3)'
                        },
                        smooth: 0.2,
                        length: 10,
                        length2: 20
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#4167E2',
                        shadowBlur: 200,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                },

                animationType: 'scale',
                animationEasing: 'elasticOut',
                animationDelay: function (idx) {
                    return Math.random() * 200;
                }
            }]
        };

        optionMap = {
            grid: {
                x: 30,
                y: 10,
                width: '88%',
                height: '88%',
                borderWidth: '0'
            },
            tooltip: {
                trigger: 'item'
            },
            series: [{
                name: '澧县地图',
                type: 'map',
                mapType: '澧县',
                selectedMode: 'single',
                itemStyle: {
                    normal: {
                        label: {
                            show: true,
                            textStyle: {
                                color: '#02B2C2',
                                fontSize: 15,
                                fontweight: 'bolder'
                            }
                        },
                        borderWidth: 2, // 省份的边框宽度
                        borderColor: '#0195D3', // 省份的边框颜色
                        areaStyle: {
                            color: 'rgba(0,0,0,0)',
                        }
                    },
                    emphasis: {
                        label: {
                            show: true,
                            textStyle: {
                                color: '#f4f4f4',
                                fontSize: 15,
                                fontweight: 'bolder'
                            }
                        },
                        areaStyle: {
                            color: '#07142b'
                        }
                    }
                },
                data: [],
                markPoint: {
                    symbolSize: 5, // 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize * 2
                    itemStyle: {
                        normal: {
                            borderColor: '#FFF100',
                            borderWidth: 1, // 标注边线线宽，单位px，默认为1
                            label: {
                                show: false
                            }
                        },
                        emphasis: {
                            borderColor: '#FFF100',
                            borderWidth: 5,
                            label: {
                                show: false
                            }
                        }
                    },
                    data: [{
                        name: "大堰垱超限检测站",
                        value: 9
                    },
                        {
                            name: "水泗村站点",
                            value: 9
                        },
                        {
                            name: "熊家湾村站点",
                            value: 9
                        },
                    ]
                },
                textFixed: {
                    '澧西街道': [0, 30],
                    '澧浦街道': [0, -10],
                    '城头山镇': [-10, -20],
                    '澧阳街道': [0, -10],
                    '澧澹街道': [0, 15],
                },
                geoCoord: {
                    '大堰垱超限检测站': [111.641135, 29.746159],
                    '水泗村站点': [111.654911, 29.727266],
                    '熊家湾村站点': [111.653709, 29.74083],
                }
            }]
        };
        window.setInterval(function () {
            var url = 'http://106.75.69.81/woc/www/data/statistics';
            var data = {};
            $.getJSON(url, data, function (result) {
                viewNumber('.trafficCounts', result.trafficCounts);
                viewNumber('.trafficIllegalCounts', result.trafficIllegalCounts);
                console.log(result.trafficIllegalCounts);
                optionGauge.series[0].data[0].value = (parseInt(result.trafficIllegalCounts) / parseInt(result.trafficCounts)).toFixed(4) * 100;
                myGauge.setOption(optionGauge, true);
            });

            var url = 'http://106.75.69.81/woc/www/data/site';
            data = {}
            $.getJSON(url, data, function (result) {
                if (result.siteList.length) {
                    var siteBar = [];
                    var dataBar = [];
                    var data = result.siteList;
                    for (var i = 0; i < data.length; i++) {
                        siteBar.push(data[i].siteCode);
                        dataBar.push(data[i].count);
                    }
                    ;
                    optionBar.xAxis[0].data = siteBar;
                    optionBar.series[0].data = dataBar;
                    myBar.setOption(optionBar);
                }
            });

            var url = 'http://106.75.69.81/woc/www/data/interval';
            data = {}
            $.getJSON(url, data, function (result) {
                if (result.siteList.length) {
                    var siteBar = [];
                    var dataBar = [];
                    var data = result.siteList;
                    for (var i = 0; i < data.length; i++) {
                        siteBar.push(data[i].siteCode);
                        dataBar.push(data[i].count);
                    }
                    ;
                    optionBar.xAxis[0].data = siteBar;
                    optionBar.series[0].data = dataBar;
                    myBar.setOption(optionBar);
                }
            });
        }, 1000)
        myGauge.setOption(optionGauge);
        myBar.setOption(optionBar);
        myLine.setOption(optionLine);
        myPie.setOption(optionPie);
        myMap.setOption(optionMap);
    }
);