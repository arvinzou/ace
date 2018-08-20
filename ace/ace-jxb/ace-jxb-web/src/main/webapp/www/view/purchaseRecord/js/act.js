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
    orderList();
}

function orderList(state){
    $.ajax({
        url: contextPath+ "/www/order/findList",
        type:"post",
        async:false,
        data:{
            findType: '2',
            payStatus: state,
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
    window.location.href = contextPath + '/www/view/courseOrderDetail/index.jsp?orderId='+orderId;
}