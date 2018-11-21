var loading = {};
var params = {limit: 5};
window.onload = function (){
    initPage();
    initEvents();
    initJuicerMethod();
}


/*互动初始化分页*/
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
            params['start']=(num-1)*params.limit;
            params['initType']=type;
            getPageList();
        }
    });
}
/*互动条件查询*/
function t_query(){
params['initType'] = 'init';
    params['start']=0;
    getPageList();
    return false;
}
function setParams(key,value){
    params[key]=value;
    t_query();
}
/*互动加载表格数据*/
function getPageList() {
    var url = contextPath+ "/liveMsg/findLiveMsgLists";
    params['content']=$("input[name=keyword]").val();
    startLoad();
    $.getJSON(url, params, function (rst) {
        stopLoad();
        if (rst.status == 0) {
            if (params.initType == "init") {
                $('#pagination1').jqPaginator('option', {
                       totalCounts: rst.total==0?1:rst.total,
                                         currentPage: 1
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


function initEvents() {
    ﻿
     $(".btn-group .btn").bind('click', function (event) {
            $(event.target).siblings().removeClass("active");
            console.log(event);
            $(event.target).addClass("active");
        });



}
/*互动审核*/
function audit(id,status){
    startLoad();
    $.ajax({
        url: contextPath + "/liveMsg/updateStatus",
        type:"post",
        async:false,
        data:{id:id,status:status},
        success:function(rst){
            stopLoad();
            if(rst.status == 0) {
                getPageList();
            }
        },
        error:function(){
            stopLoad();
            alert("对不起出错了！");
        }
    });
}

//juicer自定义函数
function initJuicerMethod() {
juicer.register('parseJson', parseJson);
}



function parseJson(text){
    return JSON.parse(text)
}