function App() {
    console.log("=============================App Start==============================");
    console.log(window.location.href);
    var url =   window.location.search.substring(1);
    primaryId = url.substring(url.indexOf('=')+1);
    console.log(primaryId);
    initData(primaryId);
}

function initData(id){
    $.ajax({
        url: contextPath+ "/www/order/findDetail",
        type:"post",
        async:false,
        data:{
            orderId:id
        },
        success:function(result){
            if(result.status == 0) {
                var orderDetailTemp = document.getElementById('orderDetailTemp').innerHTML;
                var orderDetailHtml = juicer(orderDetailTemp, {
                    data: result.data
                });
                $("#box").append(orderDetailHtml);
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

function showOrderDetail(id){
    window.location.href = contextPath + '/www/view/consultantDetail/index.jsp?id='+id;
}