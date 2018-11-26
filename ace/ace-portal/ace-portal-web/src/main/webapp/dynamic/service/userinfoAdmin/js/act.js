var loading = {};
var params = {limit: 10};
window.onload = function (){
    initPage();
    initEvents();
}


/*管理员列表初始化分页*/
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
/*管理员列表条件查询*/
function t_query(){
    params['initType'] = 'init';
    params['start']=0;
    getPageList();
    return false;
}
function setParams(key,value){
    params[key]=value;
    getPageList();
}
/*管理员列表加载表格数据*/
function getPageList() {
    var url = contextPath+ "/wxAdmin/findWxAdminList";
    params['nickname']=$("input[name=keyword]").val();
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
/*管理员列表添加*/
function add(type){
window.location.href = 'add/index.jsp?id=' + urlParams.id;
}
function initEvents() {
    ﻿   $('#modal-preview').on('show.bs.modal', function (event) {
    		var relatedTarget = $(event.relatedTarget);
    		var id = relatedTarget.data('id');
    		var title = relatedTarget.data('title');
    		var modal = $(this);

    	})
         $(".btn-group .btn").bind('click', function (event) {
                $(event.target).siblings().removeClass("active");
                console.log(event);
                $(event.target).addClass("active");
            });



}
