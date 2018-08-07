var index = null;
var unitPrice = null;
var totalPrice = null;
var sex = "1";    //默认选中男生
var primaryId = null;
var commodityId = null;
function App() {
    console.log("=============================App Start==============================");
    console.log(window.location.href);
    var url =   window.location.search.substring(1);
    primaryId = url.substring(url.indexOf('=')+1);
    console.log(primaryId);
    initData(primaryId);
    loader({
        path: contextPath,
        url: '/www/common/js/layer.mobile-v2.0/layer_mobile/need/layer.css',
        type: 'css'
    });
    loader({
        path: contextPath,
        url: '/www/common/js/layer.mobile-v2.0/layer_mobile/layer.js',
        type: 'js'
    });
    loader({
        path: contextPath,
        url: '/www/common/js/jweixin-1.2.0.js',
        type: 'js'
    });
}
window.onload = function(){
    index = layer.open({
        type: 1,
        content: $("#notes").html(),
        shadeClose: false,
    });

    $("#read").click(function(){
        var content = $("#read").attr("src");
        if(content.indexOf("yes") > 0){
            $("#read").attr('src','img/no.png');
        }else {
            $("#read").attr('src','img/yes.png');
        }
    });
}


function closeTips() {
	layer.close(index);
}

function add(){
    //初始化增加减少的分量
    if(unitPrice == null || unitPrice == undefined){
        alert("请先选择咨询方式！");
        return;
    }
	var num = parseInt($("#num").text());
	num ++;
	$("#num").text(num);
    $("#totalMoney").text(num * unitPrice);
}
function reduce(){
    if(unitPrice == null || unitPrice == undefined){
        alert("请先选择咨询方式！");
        return;
    }
	var num = parseInt($("#num").text());
	if(num > 1){
		num --
	}else{
		alert("次数至少是1");
	}
	$("#num").text(num);
    $("#totalMoney").text(num * unitPrice);
}
function changeSex(obj,value){
	$(obj).removeClass("unchecked").addClass("checked");
	$(obj).siblings().removeClass("checked").addClass("unchecked");
    sex = value;
}

function changeType(obj, priceStr, id){
	$(obj).removeClass("unactive").addClass("active");
	$(obj).parent().siblings().children().removeClass("active").addClass("unactive");
    unitPrice = parseInt(priceStr);
    commodityId = id;
    var num = parseInt($("#num").text());
    $("#totalMoney").text(num * unitPrice);
}
function initData(id){
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
                var info = document.getElementById('consulorTemp').innerHTML;
                var infohtml = juicer(info, {
                    infoData: result.data,
                });
                $("#consultorInfo").html(infohtml);
                var type = document.getElementById('labelTemp').innerHTML;
                var html = juicer(type, {
                    data: result.data,
                });
                $("#lebel").html(html);
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
function createOrder(){
    var content = $("#read").attr("src");
    //基本信息
    var username = $("input[name='username']").val();
    var age = $("input[name='age']").val();
    var phoneNum = $("input[name='phoneNum']").val();
    var problem = $("textarea[name='problem']").val();
    var num = parseInt($("#num").text());
    totalPrice = num * unitPrice;
    //紧急联系人信息
    var contact_name = $("input[name='contact_name']").val();
    var contact_relation = $("input[name='contact_relation']").val();
    var contact_phone = $("input[name='contact_phone']").val();
    if(unitPrice == null || unitPrice == undefined){
        alert("请选择咨询方式！");
        return;
    }
    if(username == '' || username == undefined){
        alert("姓名不能为空！");
        return;
    }
    if(age == '' || age == undefined){
        alert("年龄不能为空！");
        return;
    }
    if(sex == null || sex == undefined){
        alert("性别不能为空！");
        return;
    }
    if(phoneNum == '' || phoneNum == undefined){
        alert("联系方式不能为空！");
        return;
    }
    if(problem == '' || problem == undefined){
        alert("问题描述不能为空！");
        return;
    }
    if(contact_name == '' || contact_name == undefined){
        alert("紧急联系人姓名不能为空！");
        return;
    }
    if(contact_relation == '' || contact_relation == undefined){
        alert("紧急联系人关系不能为空！");
        return;
    }
    if(contact_phone == '' || contact_phone == undefined){
        alert("紧急联系人联系方式不能为空！");
        return;
    }
    if(content.indexOf("no") > 0){
        alert("请仔细阅读《顾问在线服务协议》");
        return;
    }
    var data = {
        //          --订单基本情况
                "base": {
                    "businessId": primaryId,
                    "commodityId": commodityId,
                    "category": "1",          //1代表咨询订单，2代表课程订单
                    "commodityName": "肖海平预约咨询消费",
                    "businessName": "肖海平",
                    "amount": num,
                    "price":unitPrice,
                   // "payMoney": totalPrice,
                    "payMoney": 0.01
                },
    //            --预约详情
                "consult": {
                     "tel": phoneNum,
                    "name":username,
                    "age":age,
                    "sex":sex,
                    "info":problem,
                    "secName":contact_name,
                    "relationship":contact_relation,
                    "secTel": contact_phone
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
                        fee: '0.01',
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
                    alert("操作失败！");
                }
            }
        });
    });
}