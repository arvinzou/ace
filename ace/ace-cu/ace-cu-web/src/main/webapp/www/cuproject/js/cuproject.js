var page = 0;
var pageSize = 9999;

window.onload = function(){
	
	$.ajax({
        url: "/cu/www/project/findListFilter",
        type:"post",
        async:false,
        data:{start: page, limit: pageSize, type: "0", orderBy: "sequence"},
        success:function(result){
            if(result.status == 0) {
                renderPage('projectList', result.data.rows, 'projectTemp');
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}

function help(projectId){
    window.location.href = '/cu/www/donation/donation.jsp?projectId='+projectId;
}