var mySwiper, flag, eid, totalScore = 0, cost;
$(function () {
    initWeb();
    $('.test_main .button').on('click', '.start', startTesting);
    $('.swiper-wrapper').on('click', '.prevBtn', preTest);
    $('.swiper-wrapper').on('click', 'li', chooseThis);
    $('.swiper-wrapper').on('click', '.test_done', testDone);
});

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
                $('.test_main .button .box .start').show();
                $('.test_main .button .box .result').show();
            } else {
                if (cost == 0) {
                    $('.test_main .button .box .start').show();
                } else {
                    var url = "";
                    var data = "";
                    $.getJSON(url, data, function (result) {
                        if (result.status == 0) {
                            /*有支付*/
                            if ("有支付") {
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
            cost = data.discountCost;

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

