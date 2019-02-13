$(function(){
    initData();
});

function initData(){
    $.ajax({
        url: contextPath+ "/www/classes/getClassesInfo",
        type:"post",
        async:false,
        data:{
        },
        success:function(result){
            if(result.status == 0) {
                var fileName = result.data.list.fileUrl;
                var index01 = fileName.indexOf("filename=");
                var index02 = fileName.lastIndexOf(".");
                fileName = fileName.substring(index01+9, index02-1);
                result.data.list.fileName = fileName;
                renderPage('classNotice', result.data, 'class-tpl');
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

function toMailList(){
    window.location.href = contextPath + '/www/registered/maillist/index.jsp';
}