var ngControllerName = "angularjsCtrl";
var ngAppName = "angularjsApp";
var app =angular.module(ngAppName, []);
var isBill = false;        //是否需要票据信息
var isCustom = false;      //是否选择自定义金额
var donateMoney = 10;       //捐款金额，初始化默认选择10元
var needReceipt = 0;       //是否需要票据，0不需要，1是需要
var orderResultData = null;
var payData = null;

app.controller(ngControllerName,function($scope) {

    console.log(window.location.href);
    var url = window.location.href.substring(1);
    var projectId = url.substring(url.indexOf('=')+1);
    console.log(projectId);

    $("#onoffswitch").on('click', function() {
        clickSwitch()
    });

    var clickSwitch = function() {
        if($("#onoffswitch").is(':checked')) {
            $("#bill_information").show();
            isBill = true;
        } else {
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

   /* $("#no").click(function(){
        $(this).hide();
        $(this).siblings().show();
        isName = true;
    });
    $("#yes").click(function(){
        $(this).hide();
        $(this).siblings().show();
        isName = false;
    });*/

    $scope.donateMoney = function(){
    	var realName = $("#realName").val();    //捐款人姓名
		var phoneNum = $("#phoneNum").val();    //捐款人联系电话
		var message = $("#message").val();      //留言
		var largeAddress = null;  //省市区
		var smallAddress = null;   //详细地址
		var sheng = null;    //省份
		var shi  = null;     //市
		var qu = null;       //区
		if(isBill){
            largeAddress = $("#demo1").val();
            smallAddress = $("#detailAddress").val();
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
            needReceipt = 1;
		}
		if(isCustom){
            donateMoney = $("#amountMoney").val();
            if(donateMoney == null || donateMoney == undefined || donateMoney == ''){
            	alert("请输入捐款金额！");
            	return;
            }
            if(!isMoney(donateMoney)){
                alert("输入金额格式不正确！");
                return;
            }
        }

        $.ajax({
            url: "/cu/www/project/createDonateOrder",
            type:"post",
            async:false,
            dataType:"json",
            data:{json:JSON.stringify({
                    "projectId": projectId,
					"donateAmount": donateMoney,
					"donateName": realName,
					"mobileNumber": phoneNum,
					"payType" : 0,     //0微信支付，1银行卡支付
					"needReceipt":needReceipt,
					"province": sheng,
					"city": shi,
					"district": qu,
					"address":smallAddress,
					"remark": message
				})
			    },
            success:function(result){
                if(result.status == 0) {
                    orderResultData = result.data;
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
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

        $.ajax({
            url: "/cu//www/wxpay/unifiedorder",
            type:"post",
            async:false,
            data:{
                fee: orderResultData.donateAmount,
                body: orderResultData.orderNo,
                attach: orderResultData.id
            },
            success:function(result){
                if(result.status == 0) {
                    payData = result.data;
                    onBridgeReady(payData);
                    console.log(result);
                    if (!$scope.$$phase) {
                        $scope.$apply();
                    }
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
});

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
                    // 支付成功后的回调函数
                   /* if (typeof successCB == 'function') {
                        //successCB();
                        console.log('success');
                    }*/
                   alert("支付成功！");
                } else {
                    // 处理调用失败逻辑
                  /*  if (typeof successCB == 'function') {
                        //failCB();
                        console.log('fail');
                    }*/
                    alert("支付失败！");
                }
            }
        });
    });
}

function selectMoney(obj, amount) {
	$(obj).addClass("lightborder");
	$(obj).siblings().removeClass("lightborder");
	$(obj).parent().siblings(".money_02").find("span").removeClass("lightborder");
	$("#amountMoney").hide();
    donateMoney = amount;
    console.log(donateMoney);
    isCustom = false;
}

function inputMoney(obj) {
	$(obj).addClass("lightborder");
	$(obj).parent().siblings(".money_01").find("span").removeClass("lightborder");
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