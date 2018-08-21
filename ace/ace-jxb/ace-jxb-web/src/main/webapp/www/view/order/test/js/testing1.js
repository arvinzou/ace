var mySwiper, flag, eid, totalScore = 0, cost;
$(function () {
    initWeb();
    $('.test_main .button').on('click', '.start', startTesting);
    $('.test_main .button').on('click', '.pay', buyTest);
    $('.test_main .button').on('click', '.result', historyRes);
    $('.swiper-wrapper').on('click', '.prevBtn', preTest);
    $('.swiper-wrapper').on('click', 'li', chooseThis);
    $('.swiper-wrapper').on('click', '.test_done', testDone);
});

/*查看历史测试结果*/
function historyRes() {
    $('.test_main').hide();
    $('.test_result').show();
}

/*购买测试*/
function buyTest() {
    var data = {
        //          --订单基本情况
        "base": {
            "commodityId": eid,
            "category": "3",          //1代表咨询订单，2代表课程订单，3代表测试订单
            "price": cost,
            "payMoney": cost,
            "amount": 1
        }
    }
    $.ajax({
        url: "/jxb/www/order/create",
        type: "post",
        async: false,
        /*dataType:"json",*/
        data: {data: JSON.stringify(data)},
        success: function (result) {
            if (result.status == 0) {
                console.log(result);
                var orderResultData = result.data;
                $.ajax({
                    url: "/jxb//www/wxpay/unifiedorder",
                    type: "post",
                    async: false,
                    data: {
                        fee: orderResultData.payMoney,
                        body: '测试产品',
                        attach: orderResultData.orderId
                    },
                    success: function (result) {
                        if (result.status == 0) {
                            var payData = result.data;
                            onBridgeReady(payData, orderResultData.orderId);
                            console.log(result);
                        } else {
                            if (result.info) {
                                alert(result.info);
                            } else if (result.errorMessage) {
                                alert(result.errorMessage);
                            }
                            return;
                        }
                    },
                    error: function () {
                        alert("系统服务内部异常！");
                        return;
                    }
                });
            } else {
                alert(result.info);
                return;
            }
        },
        error: function () {
            alert("系统服务内部异常！");
            return;
        }
    });
}


function onBridgeReady(obj, orderId) {
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
                    $('.test_main .button .box .start').show();
                    $('.test_main .button .box .pay').hide();
                } else {
                    alert("操作失败！");
                }
            }
        });
    });
}




/*初始化按钮*/
function initBtn() {
    /*查询有没有历史纪录*/
    var url = '/portal/www/test/getMyhistoryRes.do';
    var data = {
        evaluatTplId: eid
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            if (result.data == null) {
                /*如果有历史记录*/
                $('.test_result .content p').text(data.evaluatGauge["content"]);
                $('.test_main .button .box .start').show();
                $('.test_main .button .box .result').show();
            } else {
                if (cost == 0) {
                    $('.test_main .button .box .start').show();
                } else {
                    var url = "/jxb/www/order/paidQuery";
                    var data = {
                        commodityId: eid
                    };
                    $.getJSON(url, data, function (result) {
                        if (result.status == 0) {
                            /*有支付*/
                            if (result.data) {
                                $('.test_main .button .box .start').show();
                            }
                            else {
                                $('.test_main .button .box .pay').show();
                            }
                        } else {
                            alert("获取测试失败，将回退到上一页。");
                            // window.history.back();
                        }
                    })
                }
            }
        } else {
            alert("获取测试失败，将回退到上一页。");
            // window.history.back();
        }
    })

}




/*题目做完了统计分数*/
function testDone() {
    var slides = $('.swiper-wrapper .swiper-slide');
    for (var i = 0; i < slides.length; i++) {
        totalScore += parseInt(slides.eq(i).find('.test_option_action').data("score"));
    }
    pingjia();
}


/*评价分数*/
function pingjia() {
    var url = "/portal/www/test/getEvaluation.do";
    var data = {
        score: totalScore,
        evaluatTplId: eid
    };
    $.post(url, data, function (result) {
        if (result.status == 0) {
            var data = result.data;
            $('.test_result .content p').text(data.evaluatGauge["content"]);
            $('.test_content').css("visibility", "hidden");
            $('.test_result').show();
        } else {
            alert("对不起！测试提交失败，可能要重新测试。");
        }
    });
}


/*选择这个选项*/
function chooseThis() {
    var $that = $(this);
    $that.siblings().removeClass('test_option_action');
    $that.addClass('test_option_action');
    if (flag) {
        $('.test_done').show();
        return;
    }
    mySwiper.slideNext();
}

/*上一道题*/
function preTest() {
    flag = false;
    mySwiper.slidePrev();
}

/*开始测试*/
function startTesting() {
    $('.test_main').hide();
    $('.test_content').css("visibility", "visible");
}


/*初始化swriper*/
function initSwriper() {
    mySwiper = new Swiper('.swiper-container', {
        effect: 'fade',
        simulateTouch: false,
        allowTouchMove: false,
        pagination: {
            el: '.swiper-pagination',
            type: 'progressbar',
        },
        on: {
            reachEnd: function () {
                flag = true;
            },
        },
    })
}


/*初始化页面*/
function initWeb() {
    eid = parseQueryString("id");
    var url = '/portal/www/test/getEvaluatTpl.do';
    var data = {
        id: eid
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            initBtn();
            cost = result.data.discountCost;
            viewTestInfo(result.data);
            viewTest(result.data.evaluatCaseList);
            initSwriper();
        }
    });
}

function viewTest(data) {
    $('.swiper-wrapper').empty();
    sumTest = data.length
    for (var i = 0; i < sumTest; i++) {
        $('.swiper-wrapper').append(replaceTestStr(data[i], sumTest, i + 1));
        // mySwiper.addSlide(i,replaceTestStr(data[i],sumTest,i+1)[0]);
    }
}


function replaceTestStr(data, total, index) {
    var itmp = slideTemplate;
    itmp = itmp.replace('[title]', data.title);
    itmp = itmp.replace('[total]', total);
    itmp = itmp.replace('[index]', index);
    itmp = index == total ? itmp.replace('[testDone]', 'test_done') : itmp.replace('[testDone]', '');
    var $itmp = $(itmp);
    if (index == 1) {
        $itmp.find('.prevBtn').hide();
    }
    var options = data.caseSubData;
    for (var i = 0; i < options.length; i++) {
        $itmp.find('.test_options').append($('<li data-score="' + options[i].optionScore + '" class="">' + options[i].name + '</li>'));
    }
    return $itmp
}


function viewTestInfo(data) {
    var info = document.getElementById('temp_testInfo').innerHTML;
    var infohtml = juicer(info, {
        data: data,
    });
    $("#testInfo").html(infohtml);
}


/*解析地址参数*/
function parseQueryString(prop) {
    var obj = {};
    var url = window.location.href;
    var start = url.indexOf("?") + 1;
    var str = url.substr(start);
    var arr = str.split("&");
    for (var i = 0; i < arr.length; i++) {
        var arr2 = arr[i].split("=");
        obj[arr2[0]] = arr2[1];
    }
    return obj[prop];
}


var slideTemplate = '<div class="swiper-slide">' +
    '                <section>' +
    '                    <div class="test_top">' +
    '                        <div class="fenye">' +
    '                            <span class="pagination-current">[index]</span> /' +
    '                            <span class="pagination-total">[total]</span>' +
    '                        </div>' +
    '                        <div class="jindu">' +
    '                            <div class="swiper-pagination"></div>' +
    '                        </div>' +
    '                    </div>' +
    '                    <div class="test">' +
    '                        <p class="test_title">[title]</p>' +
    '                        <ul class="test_options">' +
    '                        </ul>' +
    '                    </div>' +
    '                    <div class="test_bottom">' +
    '                        <span class="prevBtn"><i class="iconfont">&#xe655;</i>上一题</span>' +
    '                        <input class="[testDone] start" style="display: none" type="button" name="" id="" value="完成测试"/>' +
    '                    </div>' +
    '                </section>' +
    '            </div>';

