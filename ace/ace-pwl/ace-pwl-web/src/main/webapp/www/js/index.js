var mySwiper;
var totalScore = 0;
var minute = 0;
var second = 0;
var dingshiqi;

$(function () {
    loadTestInfo();
    loadTest();
    initWeb();
    starttimer();
    $('.footer').on('click', '.show_testlist', showTestList);
    $('.model_testlist .model_tool').on('click', '.goon', hiddenTestList);
    $('.model_testlist .model_tool').on('click', '.over', testDone);
    $('.model_testlist .model_list_lists').on('click', 'li', slideToTest);
    $('.swiper-wrapper').on('click', '.options', selectiOption);
});

function starttimer() {
    dingshiqi = setInterval(timer, 1000);
}

function timer() {
    var strSec = "00";
    var strMin = "00";
    second = second + 1;
    strSec = second < 10 ? ('0' + second) : second;
    if (second >= 60) {
        second = 0;
        minute = minute + 1;
        strMin = minute < 10 ? ('0' + minute) : minute;
    }
    $('.footer .timer').text(strMin + ':' + strSec);
}

function loadTestInfo() {
    var id = localStorage["id"];
    var url = "/portal/www/test/getEvaluatTpl.do";
    var data = {
        id: id,
    }
    $.getJSON(url, data, function (result) {
        viewInfo(result.data);
    });
}

function viewInfo(data) {
    for (var key in data) {
        $('.info_' + key).text(data[key]);
    }
}

function testDone() {
    $('.swiper-wrapper').off('click', '.options', selectiOption);
    statisticsScore();
    $('.model_testlist .show_score .total').text(totalScore);
    $('.model_testlist .model_tool .goon').text('回顾考题');
    $('.model_testlist .model_tool .over').text('结束测试');
    window.clearInterval(dingshiqi);
    $('.model_testlist .show_score .time_end').text($('.footer .timer').text());
    $('.model_testlist .show_score').show();
    $('.model_testlist .model_tool').off('click', '.over', testDone);
    $('.model_testlist .model_tool').on('click', '.over', endTest);
}

function endTest() {
    location.href = 'testList.html';
}

function statisticsScore() {
    var $tests = $('.swiper-wrapper .swiper-slide');
    var answer, answerArray, flag, $test;
    $('.model_list_lists ul li').addClass('error');
    for (var i = 0; i < $tests.length; i++) {
        $test = $tests.eq(i);
        answer = $test.data('solution');
        answerArray = answer.split(",");
        flag = true;
        $test.find('.options_done').addClass('error');
        for (var j = 0; j < answerArray.length; j++) {
            $test.find('.icon').eq(answerArray[j] - 1).removeClass('error').addClass('right');
            if (!($test.find('.icon').eq(answerArray[j] - 1)).is('.options_done')) {
                flag = false;
                continue;
            }
        }
        if (flag) {
            $('.model_list_lists ul li').eq(i).removeClass('error').addClass('right');
            totalScore += $test.data('score');
        }
    }
}

function loadTest() {
    var id = localStorage["id"];
    if (!id) {
        location.href = 'testList.html';
    }
    var url = "/portal/www/test/getEvaluatCaseList.do";
    var data = {
        evaluatTplId: id,
    }
    $.ajaxSettings.async = false;
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            viewTest(result.rows);
        }
    });
    $.ajaxSettings.async = true;
}

function viewTest(data) {
    $('.swiper-wrapper').empty();
    $('.model_list_lists ul').empty();
    for (var i = 0; i < data.length; i++) {
        $('.swiper-wrapper').append(replaceTestStr(data[i]));
        $('.model_list_lists ul').append('<li>' + (i + 1) + '</li>');
    }
}

function replaceTestStr(data) {
    var itmp = testCardTemplate;
    itmp = itmp.replace('#title#', data.title);
    itmp = itmp.replace('#options1#', data.aName1);
    itmp = itmp.replace('#options2#', data.aName2);
    itmp = itmp.replace('#options3#', data.aName3);
    itmp = itmp.replace('#options4#', data.aName4);
    itmp = itmp.replace(/\#onlyOne#/g, data.solution.length > 1 ? "" : "onlyOne");
    return $(itmp).data('solution', data.solution).data('score', data.score);
}

var testCardTemplate = '<div class="swiper-slide">' +
    '					<div class="test">' +
    '						<div class="test_content">' +
    '							<div class="title">#title#</div>' +
    '							<div class="options #onlyOne#">' +
    '								<div class="icon">' +
    '									A' +
    '								</div>' +
    '								<div class="options_text">#options1#</div>' +
    '							</div>' +
    '							<div class="options #onlyOne#">' +
    '								<div class="icon">' +
    '									B' +
    '								</div>' +
    '								<div class="options_text">#options2#</div>' +
    '							</div>' +
    '							<div class="options #onlyOne#">' +
    '								<div class="icon">' +
    '									C' +
    '								</div>' +
    '								<div class="options_text">#options3#</div>' +
    '							</div>' +
    '							<div class="options #onlyOne#">' +
    '								<div class="icon">' +
    '									D' +
    '								</div>' +
    '								<div class="options_text">#options4#</div>' +
    '							</div>' +
    '						</div>' +
    '					</div>' +
    '				</div>';

function initWeb() {
    mySwiper = new Swiper('.swiper-container', {
        pagination: {
            el: '.swiper-pagination',
            type: 'fraction',
        },
    })
}

function selectiOption(e) {
    var $this = $(this);
    var index = mySwiper.activeIndex;
    if ($this.is('.onlyOne')) {
        $this.siblings().children("div.icon").removeClass('options_done');
        $this.children("div.icon").addClass('options_done');
        mySwiper.slideTo(index + 1, 1000, false);
    } else {
        if ($this.children("div.icon").is('.options_done')) {
            $this.children("div.icon").removeClass('options_done');
        } else {
            $this.children("div.icon").addClass('options_done');
        }
    }
    if ($this.parent().find('.options_done').length >= 1) {
        $('.model_list_lists ul li').eq(index).addClass('test_done');
    }
}

function showTestList() {
    $('.model_testlist').removeClass('model_testlist_action');
    $('.model_testlist').removeClass('model_testlist_feaction');
    $('.model_testlist').addClass('model_testlist_action');
}

function hiddenTestList() {
    $('.model_testlist').removeClass('model_testlist_feaction');
    $('.model_testlist').removeClass('model_testlist_action');
    $('.model_testlist').addClass('model_testlist_feaction');
}

function slideToTest() {
    var $this = $(this);
    hiddenTestList();
    var index = $this.text();
    mySwiper.slideTo(index - 1, 1000, false);
}