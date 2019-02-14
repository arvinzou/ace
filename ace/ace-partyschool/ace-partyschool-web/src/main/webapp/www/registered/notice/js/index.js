$(function(){
    initList();
});

function initList(){
    $.ajax({
        url:  contextPath + "/www/notice/findNoticeLists",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
           start: 0,
            limit: 999
        },
        success:function(result){
            if(result.status == 0) {
                renderPage('list', result.data, 'list-tpl');
            }else {
                if(result.info){
                    alert(result.info);
                }else{
                    alert(result.errorMessage);
                }
                return;
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

function findDetail(noticeId){
    window.location.href = contextPath + '/www/registered/notice/detail.jsp?noticeId='+noticeId;
}