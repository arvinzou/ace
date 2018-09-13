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
}

window.onload = function (){
    initPage();
}
var params = {limit: 5};
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
function t_query(){
    getPageList();
    return false;
}
function getPageList() {
    var url = contextPath + "/courseCmt/findMyCourseCmtList";
    params['nickname']=$("input[name=keyword]").val();
    startLoad();
    $.getJSON(url, params, function (result) {
        stopLoad();
        if (result.status == 0) {
            if (params.initType == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: result.total
                });
            }
            renderPage("cmtList", result.rows, "list");
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

function deleteCmt(obj,cmtId){
	if(confirm("确定要删除？")){
		startLoad();
		$.ajax({
			url: contextPath+ "/courseCmt/deleteCourseCmtByCourseCmtId",
			type:"post",
			async:false,
			data:{
				jsons:JSON.stringify({
					id : cmtId
				})
			},
			success:function(result){
				stopLoad();
				alert(result.errorMessage);
				if(result.status == 0) {
					getPageList();
				}
			},
			error:function(){
				stopLoad();
				alert("系统服务内部异常！");
			}
		});
	}
	
}
