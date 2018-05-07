var hours;
var page = 1;
var dataEnd = false;
var siteId, status;
$(function () {
    oneSecondTimes();
    $('.modal').on('click', '.previewLive', fixThisLive);
    $('#scrollbar').scroll(
        function () {
            if ($('#scrollbar .table').height() - $('#scrollbar').scrollTop() == $('#scrollbar').height()) {
                page++;
                preview();
            }
        });
    $('#myModal').on('hidden.bs.modal', function (e) {
        $('#passList').empty();
    });
    console.log(window.location.host);
    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            getTraffic('c55ec3b6b5a24111a7e56c458be0bbab');
//			 $('#targetDiv').addClass('move');
//          $('#embg')[0].play();
        }
        if (event.ctrlKey) {
            $('#targetDiv').removeClass('move');
        }
    });
    websocket();
});

function oneSecondTimes() {
    window.setInterval(function () {
        /*时间*/
        clockTime();
        /*数据统计显示*/
    }, 1000);
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
    var dateTime = utilFormatDataTime(date, 'datetime')
    $('.head-div .title span').html(dateTime)
}

function utilFormatDataTime(date, gs) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate();
    hours = date.getHours();
    var mm = date.getMinutes();
    var s = date.getSeconds();
    var dateString = gs.indexOf('date') == -1 ? '' : (y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d));
    var datatimeString = gs.indexOf('datetime') == -1 ? '' : ' ';
    var timeString = gs.indexOf('time') == -1 ? '' : ((hours < 10 ? ('0' + hours) : hours) + ':' + (mm < 10 ? ('0' + mm) : mm) + ':' + (s < 10 ? ('0' + s) : s));
    return dateString + datatimeString + timeString;
}

function toDecimal(x) {
    var f = parseFloat(x);
    if (isNaN(f)) {
        return;
    }
    f = Math.round(x * 100) / 100;
    return f;
}

function preview(siteIds, status) {
    var url = 'http://106.75.69.81/woc/traffic/findTrafficList';
    if (siteIds) {
        $('#passList').empty();
        page = 1;
        siteId = siteIds;
        statu = status;
    }
    var data = {
        siteId: siteId,
        limit: 20,
        status: status,
        page: page,
        start: (page - 1) * 20,
        orderBy: 'inspectTime',
        sord: 'desc',
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            viewPassList(result.rows);
            $('#myModal').modal('show');
        }
    });
}

function siteLive(siteId) {
    $('#myModalVideo').modal('show');
}

function viewPassList(data) {
    if (data.length < 20) {
        dataEnd = true;
    }
    for (var i = 0; i < data.length; i++) {
        var trpass = passTemplate;
        trpass = trpass.replace('[dataTime]', data[i].inspectTime)
            .replace('[plateNo]', data[i].plateNo)
            .replace('[direction]', data[i].direction)
            .replace('[axleCount]', data[i].axleCount)
            .replace('[totalMass]', data[i].totalMass)
            .replace('[overMass]', data[i].overMass)
            .replace('[overRate]', (data[i].overRate) * 100 + '%')
            .replace('[speed]', data[i].speed || '');
        $('#passList').append($(trpass));
    }
}

function fixThisLive() {
    var liveUrl = "http://mp4.vjshi.com/2017-06-19/10b533b358b6b7e4a8fe947958feaaf4.mp4";
    $('.live3').prop('src', $('.live2').prop('src'));
    $('.live2').prop('src', $('.live1').prop('src'));
    $('.live1').prop('src', liveUrl);
}

var passTemplate = '<tr>' +
    '					<td>[dataTime]</td>' +
    '					<td>[plateNo]</td>' +
    '					<td>[direction]</td>' +
    '					<td>[axleCount]</td>' +
    '					<td>[totalMass]</td>' +
    '					<td>[overMass]</td>' +
    '					<td class="dangerous">[overRate]</td>' +
    '				    <td>[speed]</td>' +
    '				</tr>';

