var ct4 = echarts.init(document.getElementById('ct4'), 'shine');
var ct3 = echarts.init(document.getElementById('ct3'), 'shine');
var ct1 = echarts.init(document.getElementById('ct1'), 'shine');
var ct2 = echarts.init(document.getElementById('ct2'), 'shine');


var option1 =
    {
        tooltip: {
            trigger: 'item'
        },
        legend: {
            data: ['违章记录数']
        },
        calculable: false,
        dataZoom: {
            show: true,
            realtime: true,
            start: 50,
            end: 100
        },
        xAxis: [{
            type: 'category',
            boundaryGap: false,
            data: ['0~1', '1~2', '2~3', '3~4', '4~5', '5~6', '6-7', '7~8', '8~9', '9~10', '10~11', '11~12',
                '12~13', '13~14', '14~15', '15-16', '16~17', '17~18', '18~19', '19~20', '20~21', '21~22', '22~23', '23~24'],
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
                data: [],
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
    tooltip: {
        trigger: 'item'
    },
    legend: {
        data: ['通行次数']
    },
    calculable: false,
    dataZoom: {
        show: true,
        realtime: true,
        start: 50,
        end: 100
    },
    xAxis: [{
        type: 'category',
        boundaryGap: false,
        data: [],
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
            name: '通行次数',
            type: 'line',
            data: [],
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

var option3 = {
    legend: {
        data: ['通行次数', '超载次数']
    },
    tooltip: {
        trigger: 'item'
    },
    xAxis: [{
        type: 'category',
        data: [],
        axisLabel: {
            interval: 0, // {number}
            margin: 4,
            textStyle: {
                color: '#333',
                fontSize: 10,
                fontStyle: 'normal',
            }
        },
    }],
    yAxis: [{
        type: 'value',
        axisLabel: {
            interval: 0, // {number}
            //					rotate: -45,
            margin: 5,
            textStyle: {
                color: '#333',
                fontFamily: 'verdana',
                fontSize: 10,
                fontStyle: 'normal',
                fontWeight: 'bold'
            }
        },
    }],
    series: [
        {
            name: '通行次数',
            type: 'bar',
            barGap: 0,
            data: []
        },
        {
            name: '超载次数',
            type: 'bar',
            data: []
        },
    ]
};

var option4 = {
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

            type: 'gauge',
            center: ['40%', '40%'],    // 默认全局居中
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
            data: [{value: 50}]
        }
    ]
};

