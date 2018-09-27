window.onload = function() {
    initSilder();
    $(".news-module li").on("click",function(){
        var n = $(this).index();
        var th_width = $(this).width();
        var th_left = $(this).offset().left;
        var slider_width = $(".news-slider").width();
        var slider_left = th_left + (th_width/2) - slider_width/2;
        $(".news-slider").css("left",slider_left);
        $(this).addClass("active").siblings().removeClass("active");
    });

    initData();
}

function  initSilder() {
    var th_width = $(".news-module li").eq(0).width();
    var th_left = $(".news-module li").eq(0).offset().left;
    var slider_width = $(".news-slider").width();
    var slider_left = (th_left + (th_width/2) - slider_width/2)-8;
    $(".news-slider").css("left",slider_left);
}

function initData(){
    $.ajax({
        url: contextPath+ "/www/order/findList",
        type:"post",
        async:false,
        data:{
            findType: '1',
            category: '1',
            start: 0,
            limit: 999
        },
        success:function(result){
            if(result.status == 0) {
                var orderListTemp = document.getElementById('orderListTemp').innerHTML;
                var html = juicer(orderListTemp, {
                    data: result.data
                });

                $("#orderList").html(html);
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

function orderList(data){
    $.ajax({
        url: contextPath+ "/www/order/findList",
        type:"post",
        async:false,
        data:{
            findType: '1',
            payStatusArray: data,
            category: '1',
            start: 0,
            limit: 999
        },
        success:function(result){
            if(result.status == 0) {
               var orderTemp = document.getElementById('orderListTemp').innerHTML;
                var html = juicer(orderTemp, {
                    data: result.data
                });
                $("#orderList").html(html);
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

var orderID;
function setVal(id){
    orderID = id;
}
function commit(){
    var content = $("textarea[name = 'comment']").val();
    if(content == undefined || content == ''){
        alert("请输入投诉的内容！");
        return;
    }
    $.ajax({
        url: contextPath+ "/www/order/submitComplaint",
        type:"post",
        async:false,
        data:{
            orderId: orderID,
            content: content
        },
        success:function(result){
            if(result.status == 0) {
               alert("提交投诉内容成功！");
                $("#myModal").modal('hide');
                $("textarea[name = 'comment']").val("");
            }else {
                alert(result.errorMessage);
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}