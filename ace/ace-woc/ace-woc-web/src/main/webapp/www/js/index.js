var hours;
$(function () {
    oneSecondTimes();
    //	oneMinutesTimes();
});

function oneSecondTimes() {
    window.setInterval(function () {
        /*时间*/
        clockTime();
        /*数据统计显示*/
    }, 1000);
}

//function oneMinutesTimes() {
//	statisticalDisplay();
//	window.setInterval(function() {
//		console.log(111111111111111);
////		数据统计显示
//		statisticalDisplay();
//	}, 60000);
//}

function statisticalDisplay() {
    var url = 'http://106.75.69.81/woc/www/data/statistics';
    var data = {};
    $.getJSON(url, data, function (result) {
        viewNumber('.trafficCounts', result.trafficCounts);
        viewNumber('.trafficIllegalCounts', result.trafficIllegalCounts);
    });
}

function viewNumber(_class, data) {
    var number = parseInt(data);
    $(_class).empty();
    for (i = 0; i < 5; i++) {
        var item = number % 10;
        number = parseInt(number / 10)
        $(_class).prepend($('<li>' + item + '</li>'));
    }
}

function clockTime() {
    var date = new Date();
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate();
    hours = date.getHours();
    var mm = date.getMinutes();
    var s = date.getSeconds();
    var dateTime = y +
        '-' + (m < 10 ? ('0' + m) : m) +
        '-' + (d < 10 ? ('0' + d) : d) +
        ' ' + (hours < 10 ? ('0' + hours) : hours) +
        ':' + (mm < 10 ? ('0' + mm) : mm) +
        ':' + (s < 10 ? ('0' + s) : s);
    $('.head-div .title span').html(dateTime)
}

function toDecimal(x) {
    var f = parseFloat(x);
    if (isNaN(f)) {
        return;
    }
    f = Math.round(x * 100) / 100;
    return f;    
}