window.onload = function() {

    console.log(window.location.href);
    var url =   window.location.search.substring(1);
    var primaryId = url.substring(url.indexOf('=')+1);
    console.log(primaryId);
    findDetail(primaryId);
}

function findDetail(id){
    $.ajax({
        url: contextPath+ "/www/consult/getCounselorDetail",
        type:"post",
        async:false,
        data:{
            counselorId: id
        },
        success:function(result){
            if(result.status == 0) {
                console.log(result);
                var head = document.getElementById('headTemp').innerHTML;
                var headhtml = juicer(head, {
                    headData: result.data.counselorVo,
                });
                $(".chead").html(headhtml);
                var navitem = document.getElementById('consulorTemp').innerHTML;
                var html = juicer(navitem, {
                    data: result.data,
                });
                $("#consulor").html(html);

                var orderTemp = document.getElementById('createOrderTemp').innerHTML;
                var orderhtml = juicer(orderTemp, {
                    counselor: result.data.counselorVo,
                });
                $("#createOrder").html(orderhtml);
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

function createOrder(id){
    window.location.href = contextPath + '/www/view/order/index.jsp?id='+id;
}