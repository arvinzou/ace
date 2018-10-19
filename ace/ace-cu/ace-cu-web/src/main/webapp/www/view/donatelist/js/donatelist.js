
window.onload = function(){

    var locaUrl = window.location.href;
    var url = window.location.href.substring(locaUrl.indexOf("?")+1);
    var primaryId = null;
    var paramArr = url.split("&");
    for(var i=0;i < paramArr.length;i++){
        num=paramArr[i].indexOf("=");
        if(num>0){
            name=paramArr[i].substring(0,num);
            value=paramArr[i].substr(num+1);
            if(name == "projectId"){
                primaryId = value;
            }
        }
    }

    if(primaryId != null){
        $.ajax({
            url: "/cu/www/report/donateRank",
            type:"post",
            async:false,
            data:{start:0, limit: 9999, projectId: primaryId, needOpenId: "1"},
            success:function(result){
                if(result.status == 0) {
                    renderPage('mine', result.data.own, 'mine-tpl');
                    renderPage('donateList',  result.data.list, 'donate-tpl');
                }else {
                    alert(result.info);
                }
            },
            error:function(){
                alert("系统服务内部异常！");
            }
        });
    }
};

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}