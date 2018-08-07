window.onload = function (){

    /*initpage();*/
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
            getStudioList(num, type);
        }
    });
}

function getStudioList(num, type) {
    var url = contextPath+ "/studio/findStudioList";
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
            viewHtml("audioList", result.rows);
        }
    })
}

function viewHtml(IDom, data) {
    var navitem = document.getElementById('list').innerHTML;
    var html = juicer(navitem, {
        data: data,
    });
    $("#" + IDom).html(html);
}

function audit(){
    var auditId = $("#auditId").val();
    var auditRs = $("input[name='radio']:checked").val();
    if(auditRs == '' || auditRs == undefined){
        alert("请选择审核状态！");
        return;
    }
    $.ajax({
        url: contextPath +"/studio/audit",
        type:"post",
        async:false,
        data:{studioId: auditId, auditRs: auditRs},
        success:function(result){
            if(result.status == 0) {
               alert("审核成功！");
            }else {
               alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function detail(){
    var auditId = $("#auditId").val();
    $.ajax({
        url: contextPath +"/studio/selectStudioByPrimaryKey",
        type:"post",
        async:false,
        data:{id: auditId},
        success:function(result){
            if(result.status == 0) {
                console.log(result);
                var data = result.value;
                var temp = document.getElementById('stdioInfo').innerHTML;
                var html = juicer(temp, {
                    info: data
                });
                $("#info").html(html);
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}
function edit(id){
    $("#auditId").val(id);
    detail();
}
function setval(id){
    $("#auditId").val(id);
}