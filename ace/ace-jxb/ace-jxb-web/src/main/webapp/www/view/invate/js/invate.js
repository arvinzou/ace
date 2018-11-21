window.onload = function() {
    console.log(window.location.href);
    var url =   window.location.search.substring(1);
    var primaryId = url.substring(url.indexOf('=')+1);
    console.log(primaryId);
    initData(primaryId);
};

function initData(id){
    $.ajax({
        url: contextPath+ "/www/studio/getQRCode",
        type:"post",
        async:false,
        data:{
            studioId: id,
            type: '0',
            refresh: '0'
        },
        success:function(result){
            if(result.status == 0) {
                console.log(result);
                var tempt = document.getElementById('codeTemp').innerHTML;
                var html = juicer(tempt, {
                    data: result.data
                });
                $(".main_box").append(html);
            }else {
                alert(result.info);
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}