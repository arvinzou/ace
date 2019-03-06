var loading = {};
var params = {start:0,limit:100,orgType:1};
window.onload = function () {
    getPageList();
    initEvents();
    initJuicerMethod();
}
function App() {
    console.log("=============================App Start==============================");
    loadCustom();
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

/*社会组织信息条件查询*/
function t_query() {
    getPageList();
    return false;
}
/*社会组织信息加载表格数据*/
function getPageList() {
    var url = contextPath + "/personInfo/queryperson";
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
                format: 'yyyy-mm-dd ',
                language: 'zh-CN',
                weekStart: 1,
                todayBtn: 1,//显示‘今日’按钮
                autoclose: true,
                todayHighlight: 1,
                startView: 2,
                minView: 'month',  //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
                clearBtn: true,//清除按钮
                forceParse: 0
            });


             $("input[name=endDate]").datetimepicker({
                    format: 'yyyy-mm-dd ',
                    language: 'zh-CN',
                    weekStart: 1,
                    todayBtn: 1,//显示‘今日’按钮
                    autoclose: true,
                    todayHighlight: 1,
                    startView: 2,
                    minView: 'month',  //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
                    clearBtn: true,//清除按钮
                    forceParse: 0
                });

 }


//juicer自定义函数
function initJuicerMethod() {
    juicer.register('parseJson', parseJson);
}

function parseJson(text){
    return JSON.parse(text)
}



