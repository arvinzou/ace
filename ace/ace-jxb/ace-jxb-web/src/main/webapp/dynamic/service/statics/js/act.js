var loading = {};
var colors = ['#5793f3', '#d14a61'];
function loadlocal() {
    var urls = [];
    urls.push({path: contextPath, url: '/dynamic/service/statics/js/echarts.js', type: 'js'});
    for (var i = 0; i < urls.length; i++) {
        loader(urls[i]);
    }
}

function App() {
    console.log("=============================App Start==============================");
    loadlocal();
}


window.onload = function (){
    weekRevenue();
}

/**
 * 本周营收
 */
function weekRevenue(){
    $("#monthRevenue").hide();
    $("#regist").hide();
    $("#weekRevenue").show();
    var dom = document.getElementById("weekRevenue");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        color: colors,
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        grid: {
            right: '20%'
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        title: {
            text: '周营收情况'
        },
        legend: {
            data:['付费订单','付费金额']
        },
        yAxis: [
            {
                type: 'value',
                name: '订单总数',
                min: 0,
                //max: 250,
                position: 'right',
                axisLine: {
                    lineStyle: {
                        color: colors[0]
                    }
                },
                axisLabel: {
                    formatter: '{value}'
                }
            },
            {
                type: 'value',
                name: '营收总额',
                min: 0,
                //max: 250,
                position: 'right',
                offset: 80,
                axisLine: {
                    lineStyle: {
                        color: colors[1]
                    }
                },
                axisLabel: {
                    formatter: '{value}元'
                }
            }],

        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ['星期一','星期二','星期三','星期四','星期五','星期六','星期日']
            }
        ],
        series : [
            {
                name:'付费订单',
                type:'line',
                stack: '总量',
                yAxisIndex: 0,
                areaStyle: {normal: {}},
                data:[120, 132, 101, 134, 90, 230, 210]
            },
            {
                name:'付费金额',
                type:'line',
                stack: '总量',
                yAxisIndex: 1,
                areaStyle: {normal: {}},
                data:[220, 182, 191, 234, 290, 330, 310]
            }
        ]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}

/**
 * 本月营收
 */
function monthRevenue(){
    $("#monthRevenue").show();
    $("#weekRevenue").hide();
    $("#regist").hide();
    var dom = document.getElementById("monthRevenue");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        color: colors,
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        grid: {
            right: '20%'
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        title: {
            text: '月营收情况'
        },
        legend: {
            data:['付费订单','付费金额']
        },
        yAxis: [
            {
                type: 'value',
                name: '订单总数',
                min: 0,
                //max: 250,
                position: 'right',
                axisLine: {
                    lineStyle: {
                        color: colors[0]
                    }
                },
                axisLabel: {
                    formatter: '{value}'
                }
            },
            {
                type: 'value',
                name: '营收总额',
                min: 0,
                //max: 250,
                position: 'right',
                offset: 80,
                axisLine: {
                    lineStyle: {
                        color: colors[1]
                    }
                },
                axisLabel: {
                    formatter: '{value}元'
                }
            }],

        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ['一月份','二月份','三月份','四月份','五月份','六月份','七月份','八月份','九月份','十月份','十一月份','十二月份']
            }
        ],
        series : [
            {
                name:'付费订单',
                type:'line',
                stack: '总量',
                yAxisIndex: 0,
                areaStyle: {normal: {}},
                data:[120, 132, 101, 134, 90, 230, 210, 99, 90,120, 110, 102]
            },
            {
                name:'付费金额',
                type:'line',
                stack: '总量',
                yAxisIndex: 1,
                areaStyle: {normal: {}},
                data:[220, 182, 191, 234, 290, 330, 310, 440, 120, 233, 340, 150]
            }
        ]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}

function registStatics(){
    $("#monthRevenue").hide();
    $("#weekRevenue").hide();
    $("#regist").show();
    $(".revenue_change").hide();
    var dom = document.getElementById("regist");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        color: colors,
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        grid: {
            right: '20%'
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        title: {
            text: '年度注册用户情况'
        },
        legend: {
            data:['注册用户','付费用户']
        },
        yAxis: [
            {
                type: 'value',
                name: '总量',
                min: 0,
                //max: 250,
                position: 'right',
                axisLine: {
                    lineStyle: {
                        color: colors[0]
                    }
                },
                axisLabel: {
                    formatter: '{value}'
                }
            }],
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ['一月份','二月份','三月份','四月份','五月份','六月份','七月份','八月份','九月份','十月份','十一月份','十二月份']
            }
        ],
        series : [
            {
                name:'注册用户',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:[120, 132, 101, 134, 90, 230, 210, 99, 90,120, 110, 102]
            },
            {
                name:'付费用户',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:[220, 182, 191, 234, 290, 330, 310, 440, 120, 233, 340, 150]
            }
        ]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}