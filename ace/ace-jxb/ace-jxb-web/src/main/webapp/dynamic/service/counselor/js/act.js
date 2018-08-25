var loading = {};
function loadlocal() {
    var urls = [];
    urls.push({path: contextPath, url: '/content/common/js/jqPaginator.js', type: 'js'});
    for (var i = 0; i < urls.length; i++) {
        loader(urls[i]);
    }
}

function App() {
    console.log("=============================App Start==============================");
    loadlocal();
	$('#audit').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget)
        var id = relatedTarget.data('id')
        var modal = $(this);
        modal.find('.modal-body input[name=id]').val(id);
    })
	$('#preview').on('show.bs.modal', function (event) {
		var relatedTarget = $(event.relatedTarget)
		var id = relatedTarget.data('id')
		getById(id);
	})
}


window.onload = function (){
    initPage();
}
var params = {limit: 2};
function initPage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 20,
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
function t_query(){
    getPageList();
    return false;
}
function getPageList() {
    var url = contextPath+ "/counselor/findCounselorList";
     params['name']=$("input[name=keyword]").val();
    startLoad();
    $.getJSON(url, params, function (result) {
        stopLoad();
        if (result.status == 0) {
            if (params.initType == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: result.total
                });
            }
            renderPage("counselorList", result.rows, "list");
        }
    })
}

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}



/**
 * 审核
 * @param id
 */

function audit(){
    startLoad();
    $.ajax({
        url: contextPath + "/counselor/audit",
        type:"post",
        async:false,
        data:{
            data:JSON.stringify({
                counselorId: $("#fm-audit input[name='id']").val(),
                statement: $("#fm-audit textarea[name = 'message']").val(),
                rst: $("#fm-audit input[name='auditState']:checked").val()
            })
        },
        success:function(result){
            stopLoad();
            $("#audit").modal('hide');
            alert(result.errorMessage);
            if(result.status == 0) {
                getPageList();
            }
        },
        error:function(){
            stopLoad();
            alert("对不起出错了！");
        }
    });
}

function online(id){
    if(confirm("确定要上架吗？")){
        startLoad();
        $.ajax({
            url: contextPath + "/counselor/updateLineState",
            type:"post",
            async:false,
            data:{
               counselorId :id,
               state:'1'
            },
            success:function(rst){
                stopLoad();
                if(rst.status == 0) {
                  getPageList();
                }else {
                    alert(rst.errorMessage);
                }
            },
            error:function(){
                stopLoad();
                alert("对不起，出错了！");
            }
        });
    }
}
function outline(id){
    if(confirm("确定要下架吗？")){
        startLoad();
        $.ajax({
            url: contextPath + "/counselor/updateLineState",
            type:"post",
            async:false,
            data:{
               counselorId :id,
               state:'0'
            },
            success:function(rst){
                stopLoad();
                if(rst.status == 0) {
                  getPageList();
                }else {
                    alert(rst.errorMessage);
                }
            },
            error:function(){
                stopLoad();
                alert("对不起，出错了！");
            }
        });
    }
}

function getById(id) {
	startLoad();
	$.ajax({
		url: contextPath + "/counselor/selectCounselorByPrimaryKey",
		type:"get",
		data:{
		 id :id
		},
		success:function(rst){
			stopLoad();
			if(rst.status == 0) {
				renderPage("info", rst.value, "tpl-info");
			}else {
				alert(rst.errorMessage);
			}
		},
		error:function(){
			stopLoad();
			alert("对不起，出错了！");
		}
	});
}