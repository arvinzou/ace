
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
                if(result.info){
                    alert(result.info);
                }else{
                    alert(result.errorMessage);
                }
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

/**
 * 完成订单
 */
function finishOrder(orderId){
    alert("功能建设中！");
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
            body: '咨询预约产品',
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
function onBridgeReady(obj, orderId){
    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: obj.appId, // 必填，
        timestamp: obj.timeStamp, // 必填，
        nonceStr: obj.nonceStr, // 必填，
        signature: obj.paySign,// 必填，签名，见附录1
        jsApiList: ['chooseWXPay'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });
    wx.ready(function () {
        // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
        wx.chooseWXPay({
            timestamp: obj.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
            nonceStr: obj.nonceStr, // 支付签名随机串，不长于 32 位
            package: obj.package, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
            signType: obj.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
            paySign: obj.paySign, // 支付签名
            complete: function (res) {
                window.pay_tag = true;
                if (res.errMsg == "chooseWXPay:ok") {
                    alert("支付成功！");
                    window.location.href = contextPath + '/www/view/success/index.jsp?id='+orderId;
                    /*WeixinJSBridge.invoke('closeWindow', {}, function (res) {
                        if (res.err_msg =="get_brand_wcpay_request:ok")
                        {
                            //  alert("支付成功err_code=" + res.err_code + ",err_desc=" + res.err_desc + ",err_msg=" + res.err_msg);
                           /!* WeixinJSBridge.invoke('closeWindow', {}, function (res) {

                            });*!/

                        }
                    });*/

                } else {
                    /*WeixinJSBridge.invoke('closeWindow', {}, function (res) {
                        if (res.err_msg =="get_brand_wcpay_request:ok")
                        {
                            //  alert("支付成功err_code=" + res.err_code + ",err_desc=" + res.err_desc + ",err_msg=" + res.err_msg);
                             //WeixinJSBridge.invoke('closeWindow', {}, function (res) {
                            //});

                         }
                    });*/
                    alert("支付失败！");
                }
            }
        });
    });
}
