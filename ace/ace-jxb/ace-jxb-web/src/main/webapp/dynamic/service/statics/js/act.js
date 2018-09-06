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
    initOperationData();
    weekRevenue();
}

/**
 * 本周营收
 */
function weekRevenue(){
    var weekData = null;
    startLoad();
    $.ajax({
        url: contextPath + "/www/report/weekOperationChart",
        type:"post",
        async:false,
        data:{
        },
        success:function(result){
            if(result.status == 0){
                stopLoad();
                weekData = result.data;
            }
        },
        error:function(){
            stopLoad();
            alert("对不起出错了！");
        }
    });
    if(weekData == null) return;

    $("#monthRevenue").hide();
    $("#regist").hide();
    $("#weekRevenue").show();
    $(".revenue_change").show();
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
                data : [weekData.weekTimeMap.Monday, weekData.weekTimeMap.Tuesday, weekData.weekTimeMap.Wednesday,
                        weekData.weekTimeMap.Thursday, weekData.weekTimeMap.Friday, weekData.weekTimeMap.Saturday,
                        weekData.weekTimeMap.Sunday]
            }
        ],
        series : [
            {
                name:'付费订单',
                type:'line',
                stack: '订单总数',
                yAxisIndex: 0,
                areaStyle: {normal: {}},
                data:[weekData.detailData.Monday.dayCount, weekData.detailData.Tuesday.dayCount, weekData.detailData.Wednesday.dayCount,
                    weekData.detailData.Thursday.dayCount, weekData.detailData.Friday.dayCount, weekData.detailData.Saturday.dayCount,
                    weekData.detailData.Sunday.dayCount]
            },
            {
                name:'付费金额',
                type:'line',
                stack: '金额总数',
                yAxisIndex: 1,
                areaStyle: {normal: {}},
                data:[weekData.detailData.Monday.dayTurnover, weekData.detailData.Tuesday.dayTurnover, weekData.detailData.Wednesday.dayTurnover,
                    weekData.detailData.Thursday.dayTurnover, weekData.detailData.Friday.dayTurnover, weekData.detailData.Saturday.dayTurnover,
                    weekData.detailData.Sunday.dayTurnover]
            }
        ]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, false);
    }
}

/**
 * 本月营收
 */
function monthRevenue(){
    var yearData = null;
    var xMonth = [];
    var countArr = [];
    var turnArr = [];
    startLoad();
    $.ajax({
        url: contextPath + "/www/report/yearTurnOverChart",
        type:"post",
        async:false,
        data:{
        },
        success:function(result){
            if(result.status == 0){
                stopLoad();
                yearData = result.data.monthData;
                for (var key in yearData) {
                    console.log(key);     //获取key值
                    xMonth.push(key);
                }
                for(var i=0; i<xMonth.length; i++){
                    countArr.push(yearData[xMonth[i]].weekCount);
                    turnArr.push(yearData[xMonth[i]].weekTurnover);
                }
            }
        },
        error:function(){
            stopLoad();
            alert("对不起出错了！");
        }
    });
    if(yearData == null) return;

    $("#monthRevenue").show();
    $("#weekRevenue").hide();
    $("#regist").hide();
    $(".revenue_change").show();
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
                data : xMonth
            }
        ],
        series : [
            {
                name:'付费订单',
                type:'line',
                stack: '订单总数',
                yAxisIndex: 0,
                areaStyle: {normal: {}},
                data:countArr
            },
            {
                name:'付费金额',
                type:'line',
                stack: '金额总数',
                yAxisIndex: 1,
                areaStyle: {normal: {}},
                data:countArr
            }
        ]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, false);
    }
}

function registStatics(){
    var registData = null;
    var xMonth = [];
    var registArr = [];
    var paidArr = [];
    startLoad();
    $.ajax({
        url: contextPath + "/www/report/yearRegUserChart",
        type:"post",
        async:false,
        data:{
        },
        success:function(result){
            if(result.status == 0){
                stopLoad();
                registData = result.data;
                for (var key in registData.monthData) {
                    console.log(key);     //获取key值
                    xMonth.push(key);
                }
                var data = registData.monthData;
                for(var i=0; i<xMonth.length; i++){
                    registArr.push(data[xMonth[i]].regUserCount);
                    paidArr.push(data[xMonth[i]].paidUserCount);
                }
            }
        },
        error:function(){
            stopLoad();
            alert("对不起出错了！");
        }
    });
    if(registData == null) return;

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
                data : xMonth
            }
        ],
        series : [
            {
                name:'注册用户',
                type:'line',
                stack: '注册用户量',
                areaStyle: {normal: {}},
                data:registArr
            },
            {
                name:'付费用户',
                type:'line',
                stack: '付费用户量',
                areaStyle: {normal: {}},
                data:paidArr
            }
        ]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, false);
    }
}

function initOperationData(){
    startLoad();
    $.ajax({
        url: contextPath + "/www/report/operationData",
        type:"post",
        async:false,
        data:{
        },
        success:function(result){
            if(result.status == 0){
                stopLoad();
                renderPage('operationData', result.data, 'operationDataTemp');
            }
        },
        error:function(){
            stopLoad();
            alert("对不起出错了！");
        }
    });
}
function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}