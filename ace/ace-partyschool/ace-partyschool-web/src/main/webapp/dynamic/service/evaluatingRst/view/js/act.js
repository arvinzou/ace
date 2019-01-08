var loading = {};
var editor;
var params={
    limit:15,
    classScheduleId:urlParams.id,
};
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li> <span> 创建课程表管理 </span></li>");
        initPage();
        initEchart();
    });
}


/*评测管理初始化分页*/
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


/*评测管理加载表格数据*/
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
}



/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}

function initEchart() {
    var myChart = echarts.init(document.getElementById('echartMain'));
    // 指定图表的配置项和数据
    var option = {
        xAxis: {
            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
        },
        yAxis:{

        },
        // yAxis: {data:['不好','一般','可以','满意']},
        series: [{
            name: '销量',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
        }]
    };
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