window.onload = function() {

    console.log(window.location.href);
    var url =   window.location.href.substring(1);
    var primaryId = url.substring(url.indexOf('=')+1);
    console.log(primaryId);
    findDetail(primaryId);
}

function findDetail(id){
    $.ajax({
        url: contextPath+ "/www/order/findDetail",
        type:"post",
        async:false,
        data:{
            orderId:id
        },
        success:function(result){
            if(result.status == 0) {
                var dataTemp = document.getElementById('dataTemp').innerHTML;
                var html = juicer(dataTemp, {
                    data: result.data
                });
                $("#index").append(html);
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