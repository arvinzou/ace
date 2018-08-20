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
    initpage();
}

function initpage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 20,
        pageSize: 10,
        visiblePages: 20,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            getCourseCmtList(num, type);
        }
    });
}

function getCourseCmtList(num, type) {
    var url = contextPath+ "/courseCmt/findCourseCmtList";
    var data = {
        page: num,
        limit: 10,
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            if (type == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: result.total,
                });
            }
            viewHtml("cmtList", result.rows, "list");
        }
    })
}

function viewHtml(IDom, data, tempId) {
    var navitem = document.getElementById(tempId).innerHTML;
    var html = juicer(navitem, {
        data: data,
    });
    $("#" + IDom).html(html);
}

function deleteCmt(obj,cmtId){
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
            if(result.status == 0) {
                alert("删除成功！");
                $(obj).parent().parent().remove();
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}
