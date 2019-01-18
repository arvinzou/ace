var colors = ['#5793f3', '#d14a61'];
var params = {};
window.onload = function () {
    //
    initChart(params);

    //选值后自动查询
    $(".easyui-combotree").combotree({
        onChange: function (newValue, oldValue) {
            // alert("文本已被修改:" + newValue);
            params = {'areaCode': newValue};
            initChart(params);
        }
    });
}


function initChart(p) {
    var nowData = [];
    var pastData = [];

    startLoad();
    $.ajax({
        url: contextPath + "/www/report/contrastiveReport",
        type: "post",
        async: false,
        data: p,
        success: function (rst) {
            stopLoad();
            if (rst.status == 0) {
                console.log(rst);
                console.log(rst.value);
                //今年死亡人数数据
                var now = rst.value.now;
                nowData = [now.January, now.February, now.March, now.April, now.May, now.June,
                    now.July, now.August, now.September, now.October, now.November, now.December];
                //去年死亡人数数据
                var past = rst.value.past;
                pastData = [past.January, past.February, past.March, past.April, past.May, past.June,
                    past.July, past.August, past.September, past.October, past.November, past.December];
            } else {
                alert("获取数据失败")
                return;
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
    if (nowData.length < 1 || pastData.length < 1) {
        alert("获取数据失败")
        return;
    }

    var dom = document.getElementById("div-chart");
    var myChart = echarts.init(dom);
    var option = {
        color: colors,
        title: {
            text: '同期对比',
            subtext: '交通事故死亡人数同期对比'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['去年', '今年']
        },
        toolbox: {
            show: false,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: false,
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                data: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月",]
            }
        ],
        yAxis: [
            {
                type: 'value',
                axisLabel: {
                    formatter: '{value} 人'
                }
            }
        ],
        series: [
            {
                name: '今年',
                type: 'line',
                data: nowData,
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
            },
            {
                name: '去年',
                type: 'line',
                data: pastData,
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

    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}

