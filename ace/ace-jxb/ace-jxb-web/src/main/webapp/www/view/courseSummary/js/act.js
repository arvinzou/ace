function loadlocal() {
    var urls = [];
    urls.push({path: contextPath, url: '/www/view/common/js/nav.js', type: 'js'});
    urls.push({path: contextPath, url: '/www/common/js/jweixin-1.2.0.js', type: 'js'});
    for (var i = 0; i < urls.length; i++) {
        loader(urls[i]);
    }
}
var primaryId = null;
var cost = "";
function App() {
    loadlocal();

    console.log(window.location.href);
    var url =   window.location.search.substring(1);
    primaryId = url.substring(url.indexOf('=')+1);
    console.log(primaryId);
    initData(primaryId);
}

function initData(primaryId){
    $.ajax({
        url: contextPath+ "/www/course/findCourseDetail",
        type:"post",
        async:false,
        data:{
            courseId: primaryId
        },
        success:function(result){
            if(result.status == 0) {
                viewHtml('banner', result.data, 'bannerTemp');
                viewHtml('courseDetail', result.data, 'courseDetailTemp');
                var courseContent = result.data.introduce;
                var costType = result.data.costType;
                var type = result.data.type;
                if(costType == '0'){
                    cost = "免费";
                }else if(costType == '1'){
                    cost =  result.data.cost;
                }
                $("#courseContent").html(courseContent);
                $("#totalCost").text("¥"+cost);

                if(type == '1'){
                    //单节课程
                    viewHtml('chapterContent', result.data.courseSource, 'djchapterTemp');
                }else if(type == '2'){
                    //系列课程
                    findPartInfo(primaryId);
                }
                initCommentsList();
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
function findPartInfo(primaryId){
    $.ajax({
        url: contextPath+ "/www/course/findCoursePartInfo",
        type:"post",
        async:false,
        data:{
            courseId: primaryId
        },
        success:function(result){
            if(result.status == 0) {
                viewHtml('chapterContent', result.data, 'chapterTemp');
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
function hoverLi(domId){
    $("#"+domId).removeClass('menuHide').addClass('menuShow');
    $("#"+domId).siblings().removeClass('menuShow').addClass('menuHide');
}

function viewHtml(IDom, data, tempId) {
    var navitem = document.getElementById(tempId).innerHTML;
    var html = juicer(navitem, {
        data: data,
    });
    $("#" + IDom).html(html);

}

function playSource(sourceId){
   window.location.href = contextPath + '/www/view/play/index.jsp?courseId='+primaryId+'&sourceId='+sourceId;
}

function initCommentsList(){
    $.ajax({
        url: contextPath+ "/www/course/cmt/findCmtList",
        type:"post",
        async:false,
        data:{
            courseId: primaryId,
            start: 0,
            limit: 9999
        },
        success:function(result){
            if(result.status == 0) {
                viewHtml('comments', result.data.rows, 'commentsListTemp');
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

function buy(){
    if(cost == '免费'){
        cost = '0';
        window.location.href = contextPath + '/www/view/play/index.jsp'
    }
    var data = {
        //          --订单基本情况
        "base": {
            "commodityId": primaryId,
            "category": "2",          //1代表咨询订单，2代表课程订单
            "price":cost,
            "payMoney": cost,
            amount: 1
        }
    }

    $.ajax({
        url: contextPath+ "/www/order/create",
        type:"post",
        async:false,
        /*dataType:"json",*/
        data:{data:JSON.stringify(data)},
        success:function(result){
            if(result.status == 0) {
                console.log(result);
                var orderResultData = result.data;
                $.ajax({
                    url: contextPath+ "/www/wxpay/unifiedorder",
                    type:"post",
                    async:false,
                    data:{
                        fee: orderResultData.payMoney,
                        body: '咨询预约产品',
                        attach: orderResultData.orderId
                    },
                    success:function(result){
                        if(result.status == 0) {
                            var payData = result.data;
                            onBridgeReady(payData, orderResultData.orderId);
                            console.log(result);
                            if (!$scope.$$phase) {
                                $scope.$apply();
                            }
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
            }else {
                alert(result.info);
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
                    window.location.href = contextPath + '/www/view/purchaseRecord/index.jsp'
                    /*WeixinJSBridge.invoke('closeWindow', {}, function (res) {
                        if (res.err_msg =="get_brand_wcpay_request:ok")
                        {
                            //  alert("支付成功err_code=" + res.err_code + ",err_desc=" + res.err_desc + ",err_msg=" + res.err_msg);
                            WeixinJSBridge.invoke('closeWindow', {}, function (res) {
                            });

                        }
                    });*/

                } else {
                    alert("操作失败！");
                }
            }
        });
    });
}