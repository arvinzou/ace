var colors = ['#5793f3', '#d14a61'];
var params = {};
window.onload = function () {
    //初始化监听事件
    initEvents();
    //初始参数查询
    params['category'] = $("#category").val();
    params['field'] = $("#field").val();
    initChart(params);
}

function setParams(key, val) {
    params[key] = val;
    initChart(params);
}

function initEvents() {
    //所属路长
    $('input[name=roadManId]').combogrid({
        panelWidth: 500,
        idField: 'id',
        textField: 'name',
        url: contextPath + '/roadMan/getListByCondition',
        mode: 'remote',
        fitColumns: true,
        method: 'get',
        columns: [
            [{
                field: 'name',
                title: '姓名',
                width: 100
            }, {
                field: 'orgName',
                title: '单位',
                width: 100
            }, {
                field: 'areaName',
                title: '县区',
                width: 100
            }]

        ],
        onSelect: function (rowIndex, rowData) {
            console.log(rowData);
            setParams('roadManId', rowData.id);
        }
    });
    //所属路段
    $('input[name=roadSectionId]').combogrid({
        panelWidth: 500,
        idField: 'id',
        textField: 'name',
        url: contextPath + '/roadSection/getListByCondition',
        mode: 'remote',
        fitColumns: true,
        method: 'get',
        columns: [
            [{
                field: 'name',
                title: '路段名称',
                width: 100
            }, {
                field: 'roadManName',
                title: '路长',
                width: 100
            }, {
                field: 'startName',
                title: '路段起始',
                width: 100
            }, {
                field: 'endName',
                title: '路段截止',
                width: 100
            }]
        ],
        onSelect: function (rowIndex, rowData) {
            console.log(rowData);
            setParams('roadSectionId', rowData.id);
        }
    });

    //查询类别
    $("#category").change(function () {
        setParams('category', $("#category").val());
    });
    //分析类别
    $("#field").change(function () {
        setParams('field', $("#field").val());
    });


}
//初始化chart
function initChart(p) {
    var category = p.category;
    var field = p.field;

    var seriesData = [-1, 0, 1];
    var isContinue = true;
    var errMsg = '未知错误';
    startLoad();
    $.ajax({
        url: contextPath + "/www/report/analysisReport",
        type: "post",
        async: false,
        data: p,
        success: function (rst) {
            stopLoad();
            //*********** parse data ***********
            console.log("*********** ajax response ***********");
            console.log(JSON.stringify(rst));

            if (rst.status == 0) {
                var data = rst.data;
                if ('month' == category) {
                    var mothData = data[0];
                    seriesData = [mothData.January, mothData.February, mothData.March, mothData.April,
                        mothData.May, mothData.June, mothData.July, mothData.August, mothData.September,
                        mothData.October, mothData.November, mothData.December];
                    chart_xAxis_data = ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"];
                } else if ('season' == category) {
                    var seasonData = data[0];
                    seriesData = [seasonData.one, seasonData.two, seasonData.three, seasonData.four];
                    chart_xAxis_data = ['第一季度', '第二季度', '第三季度', '第四季度'];
                } else if ('year' == category) {
                    var yearData = data[0];
                    chart_xAxis_data = [];
                    seriesData = [];
                    for (var Key in yearData) {
                        chart_xAxis_data.push(Key);
                        seriesData.push(yearData[Key]);
                    }
                }
            } else {
                isContinue = false;
                errMsg = rst.info
            }
        },
        error: function () {
            stopLoad();
            isContinue = false;
            errMsg = "对不起出错了！";
        }
    });
    //友情提示
    if (!isContinue) {
        alert(errMsg);
        return;
    }
    //变更 - 默认标题
    chart_title.subtext = 'deadthToll' == field ? '死亡人数分析' : '受伤人数分析';
    //变更 - 导航标题
    var markTitle = '数据1';
    switch (category) {
        case 'year':
            markTitle = '年度查询';
            break;
        case 'month':
            markTitle = '月份查询';
            break;
        case 'season':
            markTitle = '季度查询';
            break;
        default:
            markTitle = '月份查询';
            break;
    }
    chart_legend.data = [markTitle];
    chart_series[0].name = markTitle;
    //变更 - 显示数据
    chart_series[0].data = seriesData;
    //变更 - x轴坐标
    option.xAxis[0].data = chart_xAxis_data;

    //加载图形
    loadChart();
}

//加载图形
function loadChart() {
    var dom = document.getElementById("div-chart");
    var myChart = echarts.init(dom);
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}
//图表 - 默认标题
var chart_title = {
    text: '交通事故分析',
    subtext: '死亡人数分析'
};
//图表 - 导航栏标记
var chart_legend = {
    data: ['markTitle1', 'markTitle2']
};
//图表 - 数据填充
var chart_series = [
    {
        name: '测试',
        type: 'line',
        data: [1, 2],
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
];
//图表 - 工具栏配置
var chart_toolbox = {
    show: false,
    feature: {
        mark: {show: true},
        dataView: {show: true, readOnly: false},
        magicType: {show: true, type: ['line', 'bar']},
        restore: {show: true},
        saveAsImage: {show: true}
    }
};
//图表 - x轴坐标
var chart_xAxis_data = ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"];
//图表 - 全局配置
var option = {
    color: colors,
    title: chart_title,
    tooltip: {trigger: 'axis'},
    legend: chart_legend,
    toolbox: chart_toolbox,
    calculable: false,
    xAxis: [
        {
            type: 'category',
            boundaryGap: false,
            data: chart_xAxis_data
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
    series: chart_series
};