$(function () {
    ErrorChart();
    errorList();
});

/**
 * 故障监控统计图表
 * @constructor
 */
function ErrorChart() {
    var myChart = echarts.init(document.getElementById('chartBox'));
    option = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['故障数目']
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
                type: 'category',
                boundaryGap: false,
                data: ['0301', '0302', '0303', '0304', '0305', '0306', '0307', '0308', '0309', '0310', '0311', '0312', '0313', '0314', '0315']
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '故障数目',
                type: 'line',
                stack: '总量',
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data: [10, 9, 11, 15, 3, 1, 5, 6, 10, 3, 0, 12, 3, 8, 9]
            }
        ]
    };
    myChart.setOption(option);
}

/**
 * 故障监控Top10
 */
function errorList() {
    startLoad();
    $.ajax({
        url: contextPath + "/errFeedback/findErrFeedbackList",
        type: "post",
        async: false,
        data: {
            start: 0,
            limit: 10,
            orderBy: 'errLoopNum',
            sord:'desc'
        },
        success: function (rst) {
            stopLoad();
            if (rst.status == 0) {
                render('#top10-list', rst.rows, 'top10-tpl');
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}
/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}

