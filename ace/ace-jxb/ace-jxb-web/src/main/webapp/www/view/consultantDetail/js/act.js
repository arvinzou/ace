window.onload = function() {
    console.log(window.location.href);

    var primaryId = null;
    var locaUrl = window.location.href;
    var url = window.location.href.substring(locaUrl.indexOf("?")+1);
    var paramArr = url.split("&");
    for(var i=0;i < paramArr.length;i++){
        num=paramArr[i].indexOf("=");
        if(num>0){
            name=paramArr[i].substring(0,num);
            value=paramArr[i].substr(num+1);
            if(name == "id"){
                primaryId = value;
            }
        }
    }
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