window.onload = function(){
    initData();
}
function initData(){
    $.ajax({
        url: contextPath+ "/www/order/findList",
        type:"post",
        async:false,
        data:{
            findType: '2',
            category: '1',
            payStatusArray: '2,6,7',
            start: 0,
            limit: 999
        },
        success:function(result){
            if(result.status == 0) {
                var orderListTemp = document.getElementById('myorderTemp').innerHTML;
                var html = juicer(orderListTemp, {
                    data: result.data.rows
                });

                $("#itemList").html(html);
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

function showDetail(orderId){
    window.location.href = contextPath + '/www/view/consultantDetail/index.jsp?id='+orderId;
}
