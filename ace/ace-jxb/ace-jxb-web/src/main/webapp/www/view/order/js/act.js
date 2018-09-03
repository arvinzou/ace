var index = null;
var unitPrice = null;
var totalPrice = null;
var sex = "1";    //默认选中男生
var primaryId = null;
var commodityId = null;
var counselorName = null;

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
    loader({
        path: contextPath,
        url: '/www/common/js/datetime/LCalendar/js/LCalendar.js',
        type: 'js'
    });
}
window.onload = function(){
    index = layer.open({
        type: 1,
        content: $("#notes").html(),
        btn: '我知道了',

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

    /**
     * 初始化预约时间
     */
    var calendar = new LCalendar();
    calendar.init({
        'trigger': '#start_date', //标签id
        'type': 'datetime', //date 调出日期选择 datetime 调出日期时间选择 time 调出时间选择 ym 调出年月选择,
        'minDate': (new Date().getFullYear()) + '-' + (new Date().getMonth()+1) + '-' + (new Date().getDate()+1), //最小日期
        'maxDate': (new Date().getFullYear()+3) + '-' + 12 + '-' + 31 //最大日期
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
    unitPrice = parseFloat(priceStr);
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
                counselorName = result.data.counselorVo.name;
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
    var start_date = $("input[name = 'start_date']").val();
    var num = parseInt($("#num").text());
    totalPrice = num * unitPrice;
    //紧急联系人信息
    var contact_name = $("input[name='contact_name']").val();
    var contact_relation = $("input[name='contact_relation']").val();
    var contact_phone = $("input[name='contact_phone']").val();
    var tagboardli = $(".tagboard .tagCheck");
    var tags = "";
    for(var i=0; i<tagboardli.length; i++){
        if(i != tagboardli.length-1){
            tags = tags + tagboardli[i].innerHTML + ",";
        }else{
            tags = tags + tagboardli[i].innerHTML
        }
    }

    if(unitPrice == null || unitPrice == undefined){
        alert("请选择咨询方式！");
        return;
    }
    if(start_date == null || start_date == undefined || start_date == ''){
        alert("请选择预约时间！");
        return;
    }else if(new Date() > new Date((start_date+":00").replace(/\-/g, "/"))){
        alert("预约时间不能在当前时间之前！");
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
    }else if(phoneNum.length >11){
        alert("联系方式输入过长！");
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
    }else if(contact_phone.length > 11){
        alert("紧急联系人联系方式输入过长！");
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
                    "commodityName": "咨询预约",
                    "businessName": counselorName,
                    "amount": num,
                    "price":unitPrice,
                    "payMoney": totalPrice,
                    //"payMoney": 0.01
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
                    "secTel": contact_phone,
                    "tags": tags,
                    "reserveDate":start_date+":00"
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
                    WeixinJSBridge.invoke('closeWindow', {}, function (res) {
                        if (res.err_msg =="get_brand_wcpay_request:ok")
                        {
                            //  alert("支付成功err_code=" + res.err_code + ",err_desc=" + res.err_desc + ",err_msg=" + res.err_msg);
                             WeixinJSBridge.invoke('closeWindow', {}, function (res) {
                            });

                         }
                    });
                }
            }
        });
    });
}

function checkTags(obj){
    //点击时，未选中的选中，选中的设为不选中
    if($(obj).attr("class").indexOf("tagUncheck")>=0){
        $(obj).removeClass('tagUncheck').addClass('tagCheck');
    }else{
        $(obj).removeClass('tagCheck').addClass('tagUncheck');
    }
}