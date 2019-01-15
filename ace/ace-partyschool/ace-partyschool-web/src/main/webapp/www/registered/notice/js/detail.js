$(function(){

    var noticeId = null;
    var locaUrl = window.location.href;
    var url = window.location.href.substring(locaUrl.indexOf("?")+1);
    var paramArr = url.split("&");
    for(var i=0;i < paramArr.length;i++){
        num=paramArr[i].indexOf("=");
        if(num>0){
            name=paramArr[i].substring(0,num);
            value=paramArr[i].substr(num+1);
            if(name == "noticeId"){
                noticeId = value;
            }
        }
    }

    if(noticeId){
        initData(noticeId);
    }
});

function initData(noticeId){
    $.ajax({
        url:  contextPath + "/www/notice/getDetails",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
            id: noticeId
        },
        success:function(result){
            if(result.status == 0) {
                var fileUrl = result.data.fileUrl;
                var nameIndex = fileUrl.indexOf('filename=');
                var fileName = fileUrl.substring(nameIndex+9, fileUrl.length);
                var typeIndex = fileUrl.lastIndexOf(".");
                var type = fileUrl.substring(typeIndex+1, fileUrl.length);
                result.data.filename = fileName;
                result.data.type = type;
                renderPage('detail', result.data, 'detail-tpl');

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