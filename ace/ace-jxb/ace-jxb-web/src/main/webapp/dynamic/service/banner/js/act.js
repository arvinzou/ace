var loading = {};
var params = {limit: 10};
window.onload = function (){
    initPage();
    initEvents();
}


/*轮播图初始化分页*/
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
/*轮播图条件查询*/
function t_query(){
    getPageList();
    return false;
}
function setParams(key,value){
    params[key]=value;
     getPageList();
}
/*轮播图加载表格数据*/
function getPageList() {
    var url = contextPath+ "/banner/findBannerList";
    params['title']=$("input[name=keyword]").val();
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


function initEvents() {
    ﻿   $('#modal-preview').on('show.bs.modal', function (event) {
    		var relatedTarget = $(event.relatedTarget);
    		var id = relatedTarget.data('id');
    		var title = relatedTarget.data('title');
    		var modal = $(this);
    		console.log(relatedTarget);
    		initPreview(id);
    	})

         $(".btn-group .btn").bind('click', function (event) {
                $(event.target).siblings().removeClass("active");
                console.log(event);
                $(event.target).addClass("active");
            });



}

function del(id){
    startLoad();
    $.ajax({
        url: contextPath + "/banner/deleteBannerByBannerId",
        type:"post",
        async:false,
        data:{id:id},
        success:function(rst){
            stopLoad();
            alert(rst.errorMessage);
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


﻿function initPreview(id) {
	startLoad();
    $.ajax({
        url: contextPath +  "/banner/selectBannerByPrimaryKey",
        type: "post",
        async: false,
        data: {
            id: id
        },
        success: function (result) {
			 stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                render('#fm-preview', data, 'tpl-preview');
            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
			stopLoad();
            alert("对不起出错了！");
        }
    });
}
