//此数组用来保存答案
var Qnum = 0, //问题数量
    Anwsers = new Array(),
    ClickFlag = false,
    timer = null,
    userName = null,
    totalScore = 0;
// serviceUrl = "http://localhost/portal",
    serviceImg = "http://zx.huacainfo.com",
    syid = 'pwl';

//检查是否回答完
function finished() {
    var questions = [];
    if (Anwsers.length == Qnum) {
        //显示提交按钮
        $(".remind-content").find("h3").html("已回答完所有问题，点击提交即可查看结果");
        ShowRemind(1);
    }
}

function ShowRemind(data) {
    //1--答题已完成 2--提交成功 3--显示错误信息
    var btn1, btn2, html;
    if (data == 1) {
        btn1 = "提交";
        btn2 = "再看看";
        html = "<button class=\"defult-btn btn-1\" onclick=\"Submit();\">" + btn1 +
            "</button><button class=\"defult-btn btn-2\" onclick=\"CloseRemind();\">" + btn2 + "</button>";
    } else if (data == 2) {
        btn1 = "看答案";
        // btn2 = "寻找有缘人";
        html = "<button class=\"defult-btn btn-1\" onclick=\"ShowAnwser();\">" + btn1 + "</button>";
        // "</button><button class=\"defult-btn btn-2\" onclick=\"GoToRecommend()\">" + btn2 + "</button>";
    } else {
        html = "<button class=\"defult-btn btn-1\" onclick=\"CloseRemind();\">确定</button>"
    }
    $(".btn-bg").append(html);
    //调整位置,改变窗口可视大小的时候，会重复触发
    $(".remind-bg").css("display", "block");
    var top = $(window).height() - $(".remind-content").height()
    $(".remind-content").css("margin-top", top / 2 + "px");
}

//关闭提示窗
function CloseRemind() {
    $(".remind-bg").css("display", "none");
    $(".btn-bg").children().remove();
}

//提交答案
function Submit() {
    //提交之前先判断一下答题情况

    console.log("提交成功");
    $('.swiper-slide').off('click', '.option', selectiOption);
    //$(".remind-bg").css("display", "none");
    statisticsScore();
    $(".btn-bg").children().remove();
    resultsAndEvaluation();
    $(".remind-content").find("h3").html("总分：" + totalScore);
    ShowRemind(2);
}

function resultsAndEvaluation() {
    var id = localStorage["id"];
    var url = "/portal/www/test/getEvaluation.do";
    var data = {
        evaluatTplId: id,
        score: totalScore
        //在这里提交分数
    }
    $.ajaxSettings.async = false;
    $.getJSON(url, data, function (result) {
        console.log(result);
        if (result.status == 0) {
            $(".remind-content").find("h4").html("评价：" + result.data.content);
        }
    });
}

function statisticsScore() {
    var $tests = $('.swiper-wrapper .swiper-slide');
    var answer, answerArray, flag, $test;
    for (var i = 0; i < $tests.length; i++) {
        $test = $tests.eq(i);
        answer = $test.data('solution');
        answerArray = answer.split(",");
        flag = true;
        $test.find('.active').addClass('error');
        for (var j = 0; j < answerArray.length; j++) {
            console.log(answerArray[j]);
            $test.find('.option').eq(answerArray[j] - 1).removeClass('error').addClass('right');
            if (!($test.find('.option').eq(answerArray[j] - 1)).is('.active')) {
                console.log('错误');
                flag = false;
                continue;
            }
        }
        if (flag) {
            totalScore += $test.data('score');
        }
    }
    console.log(totalScore);
}

function initWeb() {
    var mySwiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: true,
        // spaceBetween: 30,
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        grabCursor: true, //鼠标变手型
        parallax: true,
        shortSwipes: true, //进行快速短距离的拖动无法触发Swiper
        paginationBulletRender: function (index, className) {
            return '<span class="' + className + '">' + (index + 1) + '</span>';
        },
    });
}

function ShowAnwser() {
    $(".remind-bg").css("display", "none");
    $(".btn-bg").children().remove();
    $(".answer-show").css("display", "block");
}