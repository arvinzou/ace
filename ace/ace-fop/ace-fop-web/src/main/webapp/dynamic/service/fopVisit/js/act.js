var loading = {};
var params = {limit: 5};
window.onload = function (){
    initPage();
    initEvents();
    initJuicerMethod();
}


/*企业走访初始化分页*/
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
/*企业走访条件查询*/
function t_query() {
    params['initType'] = 'init';
    params['start']=0;
    getPageList();
    return false;
}

function setParams(key, value) {
    params[key] = value;
    t_query();
}
/*企业走访加载表格数据*/
function getPageList() {
    var url = contextPath+ "/fopVisit/findFopVisitList";
    params['title']=$("input[name=keyword]").val();
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
/*企业走访添加*/
function add(type){
window.location.href = 'add/index.jsp?id=' + urlParams.id;
}
/*企业走访编辑*/
function edit(did){
window.location.href = 'edit/index.jsp?id=' + urlParams.id + '&did=' + did;
}
/*查看详情*/
function detail(id) {
var url = contextPath + "/fopVisit/selectFopVisitByPrimaryKey";
$.getJSON(url, {id: id}, function (result) {
if (result.status == 0) {
var navitem = document.getElementById('tpl-detail').innerHTML;
var html = juicer(navitem, {data: result.value});
$("#detail-info").html(html);
$("#modal-detail").modal("show");
}
})
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


//juicer自定义函数
function initJuicerMethod() {
juicer.register('parseStatus', parseStatus);
}

/**
* 状态
* 0-删除
* 1-暂存
* 2-提交审核
* 3-审核通过
* 4-审核驳回
*/
function parseStatus(status) {
    switch (status) {
        case '0':
        return "删除";
        case '1':
        return "暂存";
        case '2':
        return "待审";
        case '3':
        return "通过";
        case '4':
        return "驳回";
        default:
        return "";
    }
}


﻿function initPreview(id) {
	startLoad();
    $.ajax({
        url: contextPath +  "/fopVisit/selectFopVisitByPrimaryKey",
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
                loadAttach(id);
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
function initForm(id) {
	startLoad();
    $.ajax({
        url: contextPath +  "/fopVisit/selectFopVisitByPrimaryKey",
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
                render('#fm-audit', data, 'tpl-fm');
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


function loadAttach(noticeId){
	$.ajax({
		type : "get",
		url : portalPath + "/attach/findAttachList.do",
		data:{noticeId:noticeId},
		success : function(rst, textStatus) {
			if(rst&&rst.state){
				var html=[];
				$.each(rst.value, function(n, file) {
					html.push('<div id="' + file.fileUrl + '"><a href="'+fastdfs_server+file.fileUrl+'" target="_blank">' + file.fileName + '</a> (' + parseInt(file.fileSize/1024) + 'kb) </div>');
				});
				$('#filelist-history').html(html.join(''));
			}else{
				alert(rst.errorMessage);
			}
		}
	});
}