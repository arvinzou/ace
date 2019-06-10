var loading = {};
var editor;
var myChart;
var params = {
    limit: 15,
    classScheduleId: urlParams.id,
};
window.onload = function () {
    juicer.register('formatdate', formatdate);
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span> 统计详情 </span></li>");
        initPage();
        initEchart();
    });
}


function formatdate(data) {
    if (!data) {
        return;
    }
    var a = "";
    switch (data) {
        case "1":
            a = "很有必要";
            break
        case "2":
            a = "可以开设";
            break
        case "3":
            a = "可有可无";
            break
        case "4":
            a = "无需开设";
            break
    }
    return a;
}

/**查看成绩*/
function view(classScheduleId, userId) {
    var url = contextPath + "/evaluationRst/findEvaluationRstList";
    var params = {
        classScheduleId: classScheduleId,
        userId: userId,
        start: 0,
        limit: 100,
    }
    startLoad();
    $.getJSON(url, params, function (rst) {
        console.log();
        stopLoad();
        if (rst.status == 0) {
            $('#modal-preview').modal('show');
            data = rst.rows;
            render('#pageLists', data, 'tpl-preview');
        } else {
            alert(result.errorMessage);
        }
    })
}


/*删除成绩*/
function del(classScheduleId, userId) {
    var url = contextPath + "/evaluationRst/delectEvaluationRstGroup";
    var params = {
        jsons: JSON.stringify({
            classScheduleId: classScheduleId,
            userId: userId
        })
    }
    if (confirm("确认删除该记录么？")) {
        startLoad();
        $.getJSON(url, params, function (rst) {
            stopLoad();
            if (rst.status == 0) {
                getPageList();
            } else {
                alert(result.errorMessage);
            }
        });
    }

}

function exportData() {
    window.location.href = contextPath + '/evaluationRst/exportData?id=' + urlParams.id;
}


/*测评管理初始化分页*/
function initPage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 1,
        pageSize: params.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: ' <li class="prev"><a href="javascript:;">上一页</a></li>',
        next: ' <li class="next"><a href="javascript:;">下一页</a></li>',
        page: ' <li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            params['start'] = (num - 1) * params.limit;
            params['initType'] = type;
            getPageList();
        }
    });
}


/*测评管理加载表格数据*/
function getPageList() {
    var url = contextPath + "/evaluationRst/findEvaluationRstListVo";
    params['name'] = $("input[name=keyword]").val();
    startLoad();
    $.getJSON(url, params, function (rst) {
        stopLoad();
        if (rst.status == 0) {
            if (params.initType == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: rst.total
                });
            }
            render($("#page-list"), rst.rows, "tpl-list");
        }
    })
    url = contextPath + "/evaluationRst/statistics";
    $.getJSON(url, params, function (rst) {
        stopLoad();
        if (rst.status == 0) {
            formateEchartData(rst.data);
        }
    })
}


/*课程表管理条件查询*/
function t_query() {
    getPageList();
    return false;
}

/*整理数据成Echart支持的格式*/
function formateEchartData(data) {
    var xData = [];
    var yData = [];
    for (obj in data) {
        xData.push(data[obj].NAME);
        yData.push(data[obj].number);
    }
    resetEchartData(xData, yData);
}


function resetEchartData(xData, yDate) {
    option.xAxis.data = xData;
    option.series[0].data = yDate;
    myChart.setOption(option);
}


/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}

var option = {
    xAxis: {
        data: []
    },
    yAxis: {},
    // yAxis: {data:['不好','一般','可以','满意']},
    series: [{
        type: 'bar',
        data: []
    }]
};

function initEchart() {
    myChart = echarts.init(document.getElementById('echartMain'));
    // 指定图表的配置项和数据

    myChart.setOption(option);
}

/*保存表单**/
function save(params) {
    $.extend(params, {});
    startLoad();
    $.ajax({
        url: contextPath + "/classSchedule/insertClassSchedule",
        type: "post",
        async: false,
        data: {
            jsons: JSON.stringify(params)
        },
        success: function (result) {
            stopLoad();
            alert(result.errorMessage);
            if (result.status == 0) {
                window.location.href = '../index.jsp?id=' + urlParams.id;
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}
