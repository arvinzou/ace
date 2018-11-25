var loading = {};
var params = {limit:10,orgType:2};
window.onload = function () {
    initPage();
    initEvents();
    initJuicerMethod();
}
function App() {
    console.log("=============================App Start==============================");
    // loadCustom();
}
/*加载资源*/
function loadCustom() {
    var urls = [];
    urls.push({path: contextPath, url: '/content/common/js/jqPaginator.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/js/jquery.form.js', type: 'js'});
    for (var i = 0; i < urls.length; i++) {
        loader(urls[i]);
    }
}
/*社会组织信息初始化分页*/
function initPage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 1,
        pageSize: params.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            params['start'] = (num - 1) * params.limit;
            params['initType'] = type;
            getPageList();
        }
    });
}
/*社会组织信息条件查询*/
function t_query() {
    getPageList();
    return false;
}
/*社会组织信息加载表格数据*/
function getPageList() {
    var url = contextPath + "/personInfo/querysocietyOrg";
    params['startDate']= $("input[name=startDate]").val();
    params['endDate']= $("input[name=endDate]").val();
    startLoad();
    $.getJSON(url, params, function (rtn) {
        stopLoad();
        if (rtn.status == 0) {
            if (params.initType == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: rtn.total
                });
            }

            render($("#page-list"), rtn.data, "tpl-list");
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

function initEvents() {
    $(".btn-group .btn").bind('click', function (event) {
        $(event.target).siblings().removeClass("active");
        console.log(event);
        $(event.target).addClass("active");
    });

    $("input[name=startDate]").datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,//显示‘今日’按钮
        autoclose: true,
        todayHighlight: 1,
        startView: 2,
        minView: 'hour',  //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
        clearBtn: true,//清除按钮
        forceParse: 0
    });
    $('input[name=startDate]').focus(function () {
        $(this).blur();//不可输入状态
    });

     $("input[name=endDate]").datetimepicker({
            format: 'yyyy-mm-dd hh:ii',
            language: 'zh-CN',
            weekStart: 1,
            todayBtn: 1,//显示‘今日’按钮
            autoclose: true,
            todayHighlight: 1,
            startView: 2,
            minView: 'hour',  //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
            clearBtn: true,//清除按钮
            forceParse: 0
        });
        $('input[name=startDate]').focus(function () {
            $(this).blur();//不可输入状态
        });
}


//juicer自定义函数
function initJuicerMethod() {
    juicer.register('parseJson', parseJson);
}

function parseJson(text) {
    return JSON.parse(text)
}



