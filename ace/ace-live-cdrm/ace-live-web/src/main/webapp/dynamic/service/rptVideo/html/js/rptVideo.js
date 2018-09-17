var start;
var limit =1000;
var orderByStr = null;
$(function () {
    initWeb();
    $('.videobar').mouseover(showStatusDo);
    $('.videobar').mouseout(hideStatusDo);
    $('.startStatus').click(startVideoDo);
    $('.stopStatus').click(stopVideoDo);
    $('.delect-video').click(delectVideoDo);
});

$("#startDate").datetimepicker({
    format: "yyyy-mm-dd",
    language: 'zh-CN',
    autoclose: true,
    todayBtn: true,
    endDate: new Date(),
    maxView: '4',
    minView: '2',
}).on('change', function (ev) {
    var startDate = $('#startDate').val();
    $("#endDate").datetimepicker('setStartDate', startDate);
    $("#startDate").datetimepicker('hide');
});
$("#endDate").datetimepicker({
    format: "yyyy-mm-dd",
    language: 'zh-CN',
    autoclose: true,
    todayBtn: true,
    endDate: new Date(),
    maxView: '4',
    minView: '2',
}).on('change', function (ev) {
    var endDate = $("#endDate").val();
    $("#startDate").datetimepicker('setEndDate', endDate);
    $("#endDate").datetimepicker('hide');
});

function initWeb() {
    $('.startStatus').hide();
    $('.stopStatus').hide();
    loadReportVideoList();
}

/*显示状态*/
function showStatusDo() {
    console.log(13213);
    var media = $(this).find('video')[0];
    if (media.paused) {
        var $startStatus = $('.startStatus');
        $(this).find($startStatus).show();
    } else {
        var $stopStatus = $('.stopStatus');
        $(this).find($stopStatus).show();
    }
}

function hideStatusDo() {
    $('.startStatus').hide();
    $('.stopStatus').hide();
}

function startVideoDo() {
    $('video').each(function () {
        $(this)[0].pause();
    });
    var media = $(this).parent().parent().find('video')[0];
    media.play();
}

function stopVideoDo() {
    var media = $(this).parent().parent().find('video')[0];
    media.pause();
}

/*查找选中的视频*/
function delectVideoDo() {
    $(":checked").each(function (index) {
        var $this = $(this);
        if ($this.is(':checked')) {
            var id = $this.parent().parent().parent().data('videoId');
            actionDelectVideo(id);
        }
        ;
    });
}

/*开始删除视频*/
function actionDelectVideo(id) {
    var url = '/live/liveRpt/deleteLiveRptByLiveRptId.do';
    var data = {
        'id': id
    }

    $.getJSON(url, JSON.stringify(data), function (result) {
        console.log(result);
    });
}

/*下载直播数据*/
function loadReportVideoList(name) {
    console.log('loadReportList');
    var url = '/live/liveRpt/findLiveRptList.do';
    console.log(url);
    var data = {
        'name': name,
        'start': start,
        'limit': limit,
        'orderBy': orderByStr,
        'mediaType': '1'
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            viewReportVideoList(result.rows);
        }
    });
}

function viewReportVideoList(data) {
    $('.reportList').empty();
    for (var i = 0; i < data.length; i++) {
        var liReport = liVideoTemplate;
        liReport = liReport.replace('[content]', data[i].content);
        liReport = liReport.replace('[mediaContent]', data[i].mediaContent);
        liReport = liReport.replace('[mediaContent]', data[i].mediaContent);
        liReport = liReport.replace('[createTime]', data[i].createTime.substring(0, 16));
        var $li = $(liReport).data("videoId", data[i].id);
        $('.reportList').append($li);
    }
    hideStatusDo();
}

var liVideoTemplate = '<li class="picbar">' +
    '            <div class="videobar">' +
    '                <div class="pic">' +
    '                    <video src="[mediaContent]"></video>' +
    '                </div>' +
    '                <div class="mediaMark">' +
    '                    <span class="startStatus"><i class="iconfont">&#xe769;</i></span>' +
    '                    <span class="stopStatus"><i class="iconfont">&#xe7af;</i></span>' +
    '                </div>' +
    '                <label class="Topcheckbox">' +
    '                    <input type="checkbox">' +
    '                </label>' +
    '                <div class="title omission">[content]</div>' +
    '            </div>' +
    '            <div class="msgbar">' +
    '                <span class="omission msgbar-common creater">' +
    '                    <i class="iconfont">&#xe61a;</i>[name]' +
    '                </span>' +
    '                <span class="msgbar-common msgbar-time">' +
    '                    <i class="iconfont">&#xe651;</i>[createTime]' +
    '                </span>' +
    '                <a href="[mediaContent]" download="">下载</a>' +
    '                <a class="j-report">发布</a>' +
    '            </div>' +
    '        </li>';