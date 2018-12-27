function loadlocal() {
    var urls = [];
    urls.push({path: contextPath, url: '/www/view/common/js/nav.js', type: 'js'});
    for (var i = 0; i < urls.length; i++) {
        loader(urls[i]);
    }
}
var primaryId = null;
function App() {
    loadlocal();
}
window.onload = function(){
    orderList();
}
function orderList(state){
    $.ajax({
        url: contextPath+ "/www/order/findList",
        type:"post",
        async:false,
        data:{
            findType: '1',
            payStatus: state,
            category: '3',
            start: 0,
            limit: 999
        },
        success:function(result){
            if(result.status == 0) {
                viewHtml('orderList', result.data.rows, 'orderListTemp');
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

function viewHtml(IDom, data, tempId) {
    var navitem = document.getElementById(tempId).innerHTML;
    var html = juicer(navitem, {
        data: data,
    });
    $("#" + IDom).html(html);
}

function showOrderDetail(orderId){
    if (orderId) {
        window.location.href = '../order/testing1.html?id=' + orderId;
    }
}

/**
 * 未支付的订单再支付
 */
function pay(orderId, payMoney){
    $.ajax({
        url: contextPath+ "/www/wxpay/unifiedorder",
        type:"post",
        async:false,
        data:{
            fee: payMoney,
            body: '测试产品',
            attach: orderId
        },
        success:function(result){
            if(result.status == 0) {
                var payData = result.data;
                onBridgeReady(payData, orderId);
                console.log(result);
            }else {
                if(result.info){
                    alert(result.info);
                }else if(result.errorMessage){
                    alert(result.errorMessage);
                }
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
            return;
        }
    });
}