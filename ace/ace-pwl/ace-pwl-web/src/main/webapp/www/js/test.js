$(function () {
    loadTestInfo();
    loadTest();
    initWeb();
    $('.swiper-slide').on('click', '.option', selectiOption);
    $('.button').on('click', '.yesDo', actionTest);
    $('.button').on('click', '.noDo', backTest);
});

function actionTest() {
    $('.introduction').addClass('introduction_action');
}

function backTest() {
    location.href = 'testList.html';
}


function selectiOption(e) {
    var $this = $(this);
    if ($this.is('.onlyOne')) {
        $this.parent().find('.onlyOne').removeClass('active');
        $this.addClass('active');
    } else {
        if ($this.is('.active')) {
            $this.removeClass('active');
        } else {
            $this.addClass('active');
        }
    }
    /*判读是否全部做完*/
    isAnswerAll();
}

function isAnswerAll() {
    var $tests = $('.swiper-wrapper .swiper-slide');
    var flag = true;
    Qnum = 0;
    for (var i = 0; i < $tests.length; i++) {
        if ($tests.eq(i).find(".active").length) {
            //回答的
            Qnum++;
            $('.progress progress').prop('value', Qnum);
        }
        else {
            //没有回答的
            flag = false;
        }
    }
    if (flag) {
        $(".remind-content").find("h3").html("已回答完所有问题，点击提交即可查看结果");
        ShowRemind(1);
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
            $('.progress progress').prop('max', result.total);
            $('.info_total').text(result.total);
        }
    });
    $.ajaxSettings.async = true;
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

function viewTest(data) {
    $('.swiper-wrapper').empty();
    for (var i = 0; i < data.length; i++) {
        $('.swiper-wrapper').append(replaceTestStr(data[i]));
    }
}

function replaceTestStr(data) {
    var itmp = testCardTemplate;
    itmp = itmp.replace('#title#', data.title);
    itmp = itmp.replace('#options1#', data.aName1);
    itmp = itmp.replace('#options2#', data.aName2);
    itmp = itmp.replace('#options3#', data.aName3);
    itmp = itmp.replace('#options4#', data.aName4);
    itmp = itmp.replace('#answer#', data.solution);
    itmp = itmp.replace('#score#', data.score);
    itmp = itmp.replace('#optionType#', data.solution.length > 1 ? "多选题" : "单选题");
    itmp = itmp.replace(/\#onlyOne#/g, data.solution.length > 1 ? "" : "onlyOne");
    return $(itmp).data('solution', data.solution).data('score', data.score);
}

var testCardTemplate = '<div class="swiper-slide">' +
    '					<section>' +
    '						<h4>#title#</h4>#optionType# #score#分' +
    '						<br>' +
    '                       <div class="options">' +
    '						<div class="tabs tabs-1 option #onlyOne#">' +
    '							<span>' +
    '                            #options1#' +
    '                        </span>' +
    '						</div>' +
    '						<div class="tabs tabs-1 option #onlyOne#">' +
    '							<span>' +
    '                            #options2#' +
    '                        </span>' +
    '						</div>' +
    '						<div class="tabs tabs-1 option #onlyOne#">' +
    '							<span>' +
    '                            #options3#' +
    '                        </span>' +
    '						</div>' +
    '						<div class="tabs tabs-1 option #onlyOne#">' +
    '							<span>' +
    '                            #options4#' +
    '                        </span>' +
    '						</div>' +
    '						<h5>答案详解:</h5>' +
    '						<div class="answer answer-show">' +
    '							答案：#answer#' +
    '						</div>' +
    '                       <div>' +
    '					</section>' +
    '				</div>';