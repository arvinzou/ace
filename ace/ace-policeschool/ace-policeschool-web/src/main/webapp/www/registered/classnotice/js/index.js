$(function () {
    initData(queryURL().dorm);
});

/**url参数解析为对象*/
function queryURL(){
    var url=window.location.href;
    var arr1 = url.split("?");
    var params = arr1[1].split("&");
    var obj = {};//声明对象
    for(var i=0;i<params.length;i++){
        var param = params[i].split("=");
        obj[param[0]] = param[1];//为对象赋值
    }
    return obj;
}

function initData(dorm) {
    $.ajax({
        url: contextPath + "/www/classes/getClassesInfo",
        type: "post",
        async: false,
        data: {},
        success: function (result) {
            if (result.status == 0) {
                var fileName = result.data.list.fileUrl;
                var index01 = fileName.indexOf("filename=");
                var index02 = fileName.lastIndexOf(".");
                fileName = fileName.substring(index01 + 9, index02);
                result.data.list.fileName = fileName;
                result.data.dorm=dorm;
                renderPage('classNotice', result.data, 'class-tpl');
            } else {
                if (result.info) {
                    alert(result.info);
                } else {
                    alert(result.errorMessage);
                }
                return;
            }
        },
        error: function () {
            alert("系统服务内部异常！");
        }
    });
}

function viewPdf(url){
    if(url){
        window.location.href="./pdf/web/viewer.html?file="+encodeURI(url);
        return;
    }
    alert("文件地址确实");
}

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}

function toMailList() {
    window.location.href = contextPath + '/www/registered/maillist/index.jsp';
}

function downloadFile(url) {
    window.location.href = contextPath + "/www/download/byUrl?url=" + url;
}