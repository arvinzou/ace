$(function () {
    ErrorChart();
    errorList();
});

/**
 * 故障监控统计图表
 * @constructor
 */
function ErrorChart() {
    var dayList = [];
    var dayCountList = [];
    startLoad();
    var year = $("#year option:selected").val();
    var month = $("#month option:selected").val();
    $.ajax({
        url: contextPath + "/errFeedback/getDayErrCountList",
        type: "post",
        async: false,
        data: {
            year: year,
            month: month,
        },
        success: function (rst) {
            stopLoad();
            if(rst.length > 0){
                for(var i=0; i<rst.length; i++){
                    dayList.push(rst[i].errday);
                    dayCountList.push(rst[i].totalErrNum);
                }
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });

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
                data: dayList
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
                data: dayCountList
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

