$(function () {
    query();
    ErrorChart();
    errorList();
    initJuicerMethod();
});


//juicer自定义函数
function initJuicerMethod() {
    juicer.register('errTypes', errTypes);
}

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
            limit: 10
            //orderBy: 'errLoopNum',
            //sord:'desc'
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

/**
 * 获取统计数据
 */
function query(){
    startLoad();
    $.ajax({
        url: contextPath + "/anslysis/query.do",
        type: "post",
        async: false,
        data: {
            reportId: 'portal'
        },
        success: function (rst) {
            stopLoad();
            if (rst.status == 0) {
                var data = {};
                var retData = rst.value;
                for(var i=0; i<retData.length; i++){
                    if(retData[i].itemKey == 'offlineDeviceCount'){
                        data.offlineDeviceCount = retData[i].itemValue;
                    }else if(retData[i].itemKey == 'onlineDeviceCount'){
                        data.onlineDeviceCount = retData[i].itemValue;
                    }else if(retData[i].itemKey == 'topBuildingCount'){
                        data.topBuildingCount = retData[i].itemValue;
                    }else if(retData[i].itemKey == 'totalDeviceCount'){
                        data.totalDeviceCount = retData[i].itemValue;
                    }else if(retData[i].itemKey == 'totalErrLoopNum'){
                        data.totalErrLoopNum = retData[i].itemValue;
                    }else if(retData[i].itemKey == 'totalIngStrategy'){
                        data.totalIngStrategy = retData[i].itemValue;
                    }else if(retData[i].itemKey == 'totalStrategy'){
                        data.totalStrategy = retData[i].itemValue;
                    }else if(retData[i].itemKey == 'totalScene'){
                        data.totalScene = retData[i].itemValue;
                    }else if(retData[i].itemKey == 'totalEnergy'){
                        data.totalEnergy = retData[i].itemValue;
                    }
                }
                render('#totalCount', data, 'total-count-tpl');
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

function errTypes(type) {
    console.log(a);
    var typeList = staticDictObject['180'];
    console.log(typeList);
    for (var i = 0; i < typeList.length; i++) {
        if (type == typeList[i].CODE) {
            return typeList[i].NAME;
        }
    }
}