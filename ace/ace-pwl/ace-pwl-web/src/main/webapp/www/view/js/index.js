var mySwiper;
var totalScore = 0;
var minute = 0;
var second = 0;
var dingshiqi;
var sumTest;
var flag = true;
var eid;

$(function () {
    loadTestInfo();
    loadTest();
    initWeb();
    starttimer();
    $('.footer').on('click', '.show_testlist', showTestList);
    $('.model_testlist .model_tool').on('click', '.goon', hiddenTestList);
    $('.model_testlist .model_tool').on('click', '.over', testDone);
    $('.prompt_box  .content').on('click', '.isover', testDone);
    $('.model_testlist .model_list_lists').on('click', 'li', slideToTest);
    $('.swiper-wrapper').on('click', '.options', selectiOption);
    $('.bottom').on('click', '.show_ranking', ranking);
    $('.ranking_list').on('click', '.remove', ranking);
});

function ranking() {
    if (flag) {
        flag = false;
        var url = "/portal/www/test/findEvaluatDataList.do";
        var data = {
            evaluatTplId: eid
        };
        $.post(url, data, function (result) {
            if (result.status == 0) {
                viewRanking(result.data);
            }
        });
    }
    $('.ranking_list').toggleClass("ranking_list_hide");
}


function viewRanking(data) {
    $('.ranking_list .lists .list_li').empty();
    for (var i = 0; i < data.length; i++) {
        var li = RankingLi;
        li = li.replace('[index]', i + 1)
            .replace('[headimgurl]', data[i].headimgurl)
            .replace('[nickname]', data[i].nickname)
            .replace('[score]', data[i].score);
        $('.ranking_list .lists .list_li').append($(li));
    }
}


var RankingLi = ' <li>' +
    '                <span class="number">[index]</span><span class="user"><img src="[headimgurl]">[nickname]</span><span' +
    '                    class="score">[score]</span>\n' +
    '            </li>';



function starttimer() {
    dingshiqi = setInterval(timer, 1000);
}

function timer() {
    var strMin = "00";
    second = second + 1;
    strSec = second < 10 ? ('0' + second) : second;
    if (second >= 60) {
        second = 0;
        minute = minute + 1;
    }
    strMin = minute < 10 ? ('0' + minute) : minute;
    $('.footer .timer').text(strMin + ':' + strSec);
}

function loadTestInfo() {
    var url = window.location.href;//'http://zx.huacainfo.com/fundtown/www/download.html?id=b95afac02ed749589b4595a3388a8afc'//
    var obj = parseQueryString(url);
    eid = obj.id;
    if (!eid) {
        window.location.href = 'testList.html';
    }
    var url = "/portal/www/test/getEvaluatTpl.do";
    var data = {
        id: eid
    }
    $.getJSON(url, data, function (result) {
        viewInfo(result.data);
    });
}


function parseQueryString(url) {
    var obj = {};
    var start = url.indexOf("?") + 1;
    var str = url.substr(start);
    var arr = str.split("&");
    for (var i = 0; i < arr.length; i++) {
        var arr2 = arr[i].split("=");
        obj[arr2[0]] = arr2[1];
    }
    return obj;
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
    $('.model_testlist .show_score .time_end .zztime').text($('.footer .timer').text());
    $('.model_testlist .show_score').show();
    $('.prompt_box').hide();
    pingjia();
    showTestList();
    $('.model_testlist .model_tool').off('click', '.over', testDone);
    $('.model_testlist .model_tool').on('click', '.over', endTest);
}

/*评价分数*/
function pingjia() {
    var url = "/portal/www/test/getEvaluation.do";
    var data = {
        score: totalScore,
        evaluatTplId: eid
    };
    $.post(url, data, function (result) {
        var data = result.data;
        $('.pingyu .evaluatGauge').text(data.evaluatGauge["content"]);
        $('.ranking .ranking_num').text(data.ranking);
    });
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
    var url = "/portal/www/test/getEvaluatCaseList.do";
    var data = {
        evaluatTplId: eid
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
    sumTest = data.length
    for (var i = 0; i < sumTest; i++) {
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
    itmp = itmp.replace('#type#', data.solution.length > 1 ? "多选题" : "单选题");
    itmp = itmp.replace('#score#', data.score);
    itmp = itmp.replace(/\#onlyOne#/g, data.solution.length > 1 ? "" : "onlyOne");
    return $(itmp).data('solution', data.solution).data('score', data.score);
}

var testCardTemplate = '<div class="swiper-slide">' +
    '					<div class="test">' +
    '						<div class="test_content">' +
    '							<div class="title">#title# <span class="remark">（#type#）（#score#分）</span></div>' +
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
        on: {
            slideChangeTransitionEnd: function () {
                if ((sumTest - 1) == this.activeIndex) {
                    endingCheck();
                }
            },
        },
    })
}

function endingCheck() {
    var awer = checkDone();
    if (awer === undefined) {
        //弹出题目没有做完，是否提交
        return
    }
    if (awer) {
        promptBox(true);//做完。
        return;
    }
    promptBox(false);//没做完。
}


function promptBox(flag) {
    var msg;
    var btnText;
    $('.prompt_box  .content .istool').off('click');
    if (flag) {
        msg = "题目已经全部做完。";
        btnText = "再看看";
        $('.prompt_box  .content').on('click', '.istool', continueto);
    } else {
        msg = "注意！还有题目没有做完。";
        btnText = "继续测试";
        $('.prompt_box  .content').on('click', '.istool', showTestList);
    }
    $('.prompt_work').text(msg);
    $('.istool').text(btnText);
    $('.prompt_box').show();
}

//继续看看
function continueto() {
    $('.prompt_box').hide();
}

function checkDone() {
    var $tests = $('.swiper-wrapper .swiper-slide');
    if (!$tests.eq(sumTest - 1).find(".options_done").length) {
        return undefined;
    }
    for (var i = 0; i < $tests.length; i++) {
        if (!$tests.eq(i).find(".options_done").length) {
            return false;//题目没做完。。是否提交
        }
    }
    return true;
}

function selectiOption(e) {
    var $this = $(this);
    var index = mySwiper.activeIndex;
    if ($this.is('.onlyOne')) {
        $this.siblings().children("div.icon").removeClass('options_done');
        $this.children("div.icon").addClass('options_done');
        mySwiper.slideTo(index + 1, 700, false);
    } else {
        if ($this.children("div.icon").is('.options_done')) {
            $this.children("div.icon").removeClass('options_done');
        } else {
            $this.children("div.icon").addClass('options_done');
        }
    }
    if (index == (sumTest - 1)) {
        endingCheck();
    }
    /*标记题目列表状态*/
    if ($this.parent().find('.options_done').length >= 1) {
        $('.model_list_lists ul li').eq(index).addClass('test_done');
    }
}

function showTestList() {
    $('.prompt_box').hide();
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