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
function add(){
     console.log("=========add=========");
   $('#modal-add').modal('show');
}
function initEvents() {
    ﻿   $('#modal-add').on('show.bs.modal', function (event) {
    		var modal = $(this);
            console.log("=========show.bs.modal=========");
            query();
    	})
        $(".btn-group .btn").bind('click', function (event) {
                $(event.target).siblings().removeClass("active");
                console.log(event);
                $(event.target).addClass("active");
         });
}



function getList(nickname){
    $.ajax({
 		type: "post",
 		url: contextPath + "/wxAdmin/selectList",
 		data: {
 			nickname: nickname
 		},
 		beforeSend: function(XMLHttpRequest) {
 			startLoad();
 		},
 		success: function(rst, textStatus) {
 		    console.log(rst);
            render($("#user-List"), rst, "tpl-user");
 		},
 		complete: function(XMLHttpRequest, textStatus) {
 			stopLoad();
 		},
 		error: function() {
 			stopLoad();
 			alert("对不起出错了。");
 		}
 	});
}

function query(){
    var nickname=$("input[name=nickname]").val();
    getList(nickname);
    return false;
}

function insertWxUser(unionid,nickname,headimg){
     $.ajax({
            type: "post",
            url: contextPath + "/wxAdmin/insertWxAdmin",
            data: {
                jsons: JSON.stringify({unionid:unionid,nickname:nickname,headimg:headimg})
            },
            beforeSend: function(XMLHttpRequest) {
                startLoad();
            },
            success: function(rst, textStatus) {
                alert(rst.errorMessage);
               if(rst.status==0){
                    t_query();
                    $('#modal-add').modal('hide');
               }
            },
            complete: function(XMLHttpRequest, textStatus) {
                stopLoad();
            },
            error: function() {
                stopLoad();
                alert("对不起出错了。");
            }
        });
}

function del(id){
     $.ajax({
            type: "post",
            url: contextPath + "/wxAdmin/deleteWxAdminByWxAdminId",
            data: {
                id: id
            },
            beforeSend: function(XMLHttpRequest) {
                startLoad();
            },
            success: function(rst, textStatus) {
                alert(rst.errorMessage);
               if(rst.status==0){
                    t_query();
                    $('#modal-add').modal('hide');
               }
            },
            complete: function(XMLHttpRequest, textStatus) {
                stopLoad();
            },
            error: function() {
                stopLoad();
                alert("对不起出错了。");
            }
        });
}