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
var type = null;
function App() {
    loadlocal();
    loader({
        path: contextPath,
        url: '/www/view/common/js/star-rating.js',
        type: 'js',
        callback: function () {
            jQuery(document).ready(function() {
                var $inp = $('#rating-input');
                $inp.rating({
                    min: 0,
                    max: 5,
                    step: 1,
                    size: 'xs',
                    theme: 'krajee-fa',
                    showClear: false,
                    starCaptions: {1: '极差', 2: '差', 3: '一般', 4: '良好', 5: '推荐'},
                    captionElement: "#kv-caption",
                    filledStar: '<i class="fas fa-star"></i>',
                    emptyStar: '<i class="fas fa-star"></i>',

                })
            });
        }
    });

}

window.onload = function(){
    var url =   window.location.search.substring(1);
    primaryId = url.substring(url.indexOf('=')+1);
    juicer.register('getObjectName', getObjectName);
    juicer.register('getPurportName', getPurportName);
    initData(primaryId);
    $('.myComment').click(commentModal);
}


function commentModal() {
    if (cost == '免费') {
        $('#myModal').modal('show');
        return;
    }
    var url = "/jxb/www/order/paidQuery";
    var data = {
        commodityId: primaryId,
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            /*有支付*/
            if (result.data) {
                $('#myModal').modal('show');
                return
            }
            alert("需要购买后才能评价。")
        }
    })
}

function initData(primaryId){
    startLoad();
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
                var costType = result.data.costType;
                type = result.data.type;
                if(costType == '0'){
                    cost = "免费";
                }else if(costType == '1' && result.data.cost == '0'){
                    cost = "免费";
                }else if(costType == '1'){
                    cost =  result.data.cost;
                }
                $("#totalCost").text("¥"+cost);

                if(type == '1'){
                    //单节课程
                    viewHtml('chapterContent', result.data.courseSource, 'djchapterTemp');
                }else if(type == '2'){
                    //系列课程
                    findPartInfo(primaryId);
                }
                viewHtml('footerBox', result.data, 'footerTemp');
                initCommentsList();
                findOrderByCommodityid();
                stopLoad();
            }else {
                if(result.info){
                    alert(result.info);
                }else{
                    alert(result.errorMessage);
                }
                stopLoad();
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
            stopLoad();
        }
    });
}
function findPartInfo(primaryId){
    $.ajax({
        url: contextPath+ "/www/course/findCoursePartInfo",
        type:"post",
        async:false,
        data:{
            courseId: primaryId,
            orderBy: "createDate",
            sord: "asc"
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

function playSource(sourceId, free){
    if(findOrderByCommodityid() == false && cost != '免费'){
        if(free == '0'){   //可试听
            window.location.href = contextPath + '/www/view/play/index.jsp?courseId='+primaryId+'&sourceId='+sourceId;
        }else{
            alert("课程必须先购买！");
        }
    }else{
        window.location.href = contextPath + '/www/view/play/index.jsp?courseId='+primaryId+'&sourceId='+sourceId;
    }
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
                viewHtml('commentList', result.data.rows, 'commentsListTemp');
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
        if(type == '1'){
            window.location.href = contextPath + '/www/view/play/index.jsp?courseId='+primaryId;
        }else{
            window.location.href = contextPath + '/www/view/catalogues/index.jsp?courseId='+primaryId;
        }
    }else{
        if(findOrderByCommodityid() == true){
            if(type == '1'){
                window.location.href = contextPath + '/www/view/play/index.jsp?courseId='+primaryId;
            }else{
                window.location.href = contextPath + '/www/view/catalogues/index.jsp?courseId='+primaryId;
            }
        }else{
            var data = {
                //          --订单基本情况
                "base": {
                    "commodityId": primaryId,
                    "category": "2",          //1代表咨询订单，2代表课程订单
                    "price":cost,
                    "payMoney": cost,
                    "amount": 1
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
                                body: '课程产品',
                                attach: orderResultData.orderId
                            },
                            success:function(result){
                                if(result.status == 0) {
                                    var payData = result.data;
                                    onBridgeReady(payData, orderResultData.orderId);
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
    }
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
                    window.location.href = contextPath + '/www/view/courseSummary/index.jsp?courseId='+primaryId;
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

function commitComments(){
    var level = $("#rating-input").val();
    var content = $("textarea[name = 'content']").val();
    if(content == '' || content == undefined){
        alert("留下点评价呗~");
        return;
    }
    $.ajax({
        url: contextPath+ "/www/course/cmt/add",
        type:"post",
        async:false,
        data:{
            courseId: primaryId,
            content: content,
            grade: level
        },
        success:function(result){
            if(result.status == 0) {
                alert("感谢您的评价，下次我们会做得更好！");
                initCommentsList();
                $('#myModal').modal('hide');
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

function findOrderByCommodityid(){
    var isBuy = null;
    $.ajax({
        url: contextPath+ "/www/order/paidQuery",
        type:"post",
        async:false,
        data:{
            commodityId: primaryId
        },
        success:function(result){
            if(result.status == 0) {
                isBuy = result.data;
                if(isBuy){
                    $("#buy").text("去听课");
                }
            }else {
                alert(result.info);
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
    return isBuy;
}

function getObjectName(code){
    var dataList = staticDictObject['149'];
    for(var i=0; i<dataList.length; i++){
        if(code == dataList[i].CODE){
            return dataList[i].NAME;
        }
    }
}

function getPurportName(code){
    var dataList = staticDictObject['150'];
    for(var i=0;i<dataList.length; i++){
        if(code == dataList[i].CODE){
            return dataList[i].NAME;
        }
    }
}