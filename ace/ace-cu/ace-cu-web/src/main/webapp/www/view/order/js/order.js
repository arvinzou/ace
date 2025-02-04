var isBill = false;        //是否需要票据信息
var isCustom = false;      //是否选择自定义金额
var donateMoney = 10;       //捐款金额，初始化默认选择10元
var needReceipt = 0;       //是否需要票据，0不需要，1是需要
var orderResultData = null;
var payData = null;
var isName = false;
var paramsObj;
var anonymity='0';
var placeholder="您的暖心留言会弹在屏幕上～";


/**url参数解析为对象*/
function queryURL(){
    var url=window.location.href;
    var arr1 = url.split("?");
    var params = arr1[1].split("&");
    var obj = {};//声明对象
    for(var i=0;i<params.length;i++){
        var param = params[i].split("=");
        obj[param[0]] = param[1];//为对象赋值
    }
    return obj;
}


function placeholderChange() {
    if(paramsObj.donateType){
        $('#message').prop('placeholder',placeholder);
    }
}

window.onload = function(){
    paramsObj=queryURL();
    placeholderChange();
    $("#onoffswitch").on('click', function() {
        clickSwitch()
    });
    var clickSwitch = function() {
        if($("#onoffswitch").is(':checked')) {
            $(".testswitch-switch").removeClass("switchoff").addClass("switchon");
            $("#bill_information").show();
            isBill = true;
        } else {
            $(".testswitch-switch").removeClass("switchon").addClass("switchoff");
            $("#bill_information").hide();
            isBill = false;
        }
    };

    var area1 = new LArea();
    area1.init({
        'trigger': '#demo1', //触发选择控件的文本框，同时选择完毕后name属性输出到该位置
        'valueTo': '#value1', //选择完毕后id属性输出到该位置
        'keys': {
            id: 'id',
            name: 'name'
        }, //绑定数据源相关字段 id对应valueTo的value属性输出 name对应trigger的value属性输出
        'type': 1, //数据源类型
        'data': LAreaData //数据源
    });
    area1.value = [1, 13, 3]; //控制初始位置，注意：该方法并不会影响到input的value

    $("#no").click(function(){
        $(this).hide();
        $(this).siblings().show();
        isName = true;
        $("#userInfo").hide();
        anonymity="1";
        placeholder="选择匿名捐赠您的头像不会显示在弹幕上哦～";
        placeholderChange();
    });
    $("#yes").click(function(){
        $(this).hide();
        $(this).siblings().show();
        isName = false;
        $("#userInfo").show();
        anonymity="0";
        placeholder="您的暖心留言会弹在屏幕上～";
        placeholderChange();
    });


};

function donateMoney1 (){
    var realName = $("#realName").val();    //捐款人姓名
    var phoneNum = $("#phoneNum").val();    //捐款人联系电话
    var message = $("#message").val();      //留言
    var largeAddress = null;  //省市区
    var smallAddress = null;   //详细地址
    var donatePostName = $("input[name = 'donatePostName']").val();
    var sheng = null;    //省份
    var shi  = null;     //市
    var qu = null;       //区
    if(isBill){
        largeAddress = $("#demo1").val();
        smallAddress = $("#detailAddress").val();
        var billName = $("#billName").val();
        var billphoneNumber = $("#billphoneNumber").val();
        if(billName == null || billName == undefined || billName == ''){
            alert("请输入收货人姓名！");
            return;
        }
        if(largeAddress == null || largeAddress == undefined || largeAddress == ''){
            alert("请选择地址！");
            return;
        }else{
            sheng = largeAddress.split(",")[0];
            shi = largeAddress.split(",")[1];
            qu = largeAddress.split(",")[2];
        }
        if(smallAddress == null || smallAddress == undefined || smallAddress == ''){
            alert("请输入详细地址！");
            return;
        }
        if(billphoneNumber == null || billphoneNumber ==undefined || billphoneNumber ==''){
            alert("收货人联系电话不能为空！");
            return;
        }
        needReceipt = 1;
    }
    if(!isName){
        if(realName == null || realName == undefined || realName ==''){
            alert("捐款人姓名不能为空！");
            return;
        }
    }
    if(isCustom){
        donateMoney = $("#amountMoney").val();
        if(donateMoney == null || donateMoney == undefined || donateMoney == ''){
            alert("请输入捐款金额！");
            return;
        }
    }

    var donateType=paramsObj.donateType?paramsObj.donateType:"1";
    $.ajax({
        url: "/cu/www/project/createDonateOrder",
        type:"post",
        async:false,
        dataType:"json",
        data:{json:JSON.stringify({
                "projectId": paramsObj.projectId,
                "donateAmount": donateMoney,
                "donateName": realName,
                "consigneeName": billName,
                "mobileNumber": phoneNum,
                "payType" : "2",     //0微信支付，1银行卡支付
                "needReceipt":needReceipt,
                "country":"中国",
                "province": sheng,
                "city": shi,
                "district": qu,
                "address":smallAddress,
                "remark": message,
                "consigneeMobileNumber":billphoneNumber,
                "donatePostName": donatePostName,
                'anonymity':anonymity,
                "donateType":donateType//	1慈善一日捐 ，2日行一善
            })
        },
        success:function(result){
            if(result.status == 0) {
                orderResultData = result.data;
                $.ajax({
                    url: "/cu/www/wxpay/ccbPay",
                    type:"post",
                    async:false,
                    data:{
                        payAmount: orderResultData.donateAmount,
                        orderId: orderResultData.id
                    },
                    success:function(result){
                        if(result.status == 0) {
                            /* payData = result.data;
                             onBridgeReady(payData);
                             console.log(result);
                             if (!$scope.$$phase) {
                                 $scope.$apply();
                             }*/
                            var url = decodeURIComponent(result.data);
                            window.location.href = url;

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

function onBridgeReady(obj){
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
                   alert("捐款成功！");
                    WeixinJSBridge.invoke('closeWindow', {}, function (res) {
                        if (res.err_msg =="get_brand_wcpay_request:ok")
                        {
                            //  alert("支付成功err_code=" + res.err_code + ",err_desc=" + res.err_desc + ",err_msg=" + res.err_msg);
                            WeixinJSBridge.invoke('closeWindow', {}, function (res) {
                            });
                        }
                    });

                } else {
                    //alert("操作失败！");
                }
            }
        });
    });
}

function selectMoney(obj, amount) {
    $(".money_02 span").removeClass("lightborder");
    $(".money_01 span").removeClass("lightborder");
    $("#amountMoney").removeClass("lightborder");
	$(obj).addClass("lightborder");
    donateMoney = amount;
    isCustom = false;
}

function inputMoney(obj) {
    $(".money_02 span").removeClass("lightborder");
    $(".money_01 span").removeClass("lightborder");
	$(obj).addClass("lightborder");
	$("#amountMoney").show();
    isCustom = true;
}

/**
 * 判断输入金额是否合法
 * @param s
 * @returns {boolean}
 */
function isMoney(s) {
    var regu = "^[0-9]+[\.][0-9]{0,3}$";
    var re = new RegExp(regu);
    if (re.test(s)) {
        return true;
    } else {
        return false;
    }
}

