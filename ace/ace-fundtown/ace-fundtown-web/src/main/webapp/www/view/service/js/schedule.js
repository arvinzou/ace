$(function () {
    userInfo();
    initweb();
})

var deptID;

function userInfo() {
    var url = "/fundtown/www/process/getMyVipInfo";
    $.ajaxSettings.async = false;
    $.getJSON(url, function (result) {
        if (result.status == 0) {
            deptID = result.data.deptId;
        }
        else {
            alert("网络异常。")
        }

    });
    $.ajaxSettings.async = true;
}


function initweb() {
    var url = '/fundtown/www/process/getMyProcess';
    var data = {
        deptId: deptID
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            viewSchedule(result.data);
        }
    });
}

function viewSchedule(data) {
    $('.lists').empty();

    for (var i = 0; i < data.length; i++) {
        var zt = data[i].auditResult;
        var color;
        var text;
        if (i == 0) {
            color = 'blue';
            text = zt == 0 ? '待审核' : zt == 1 ? '审核通过' : "审核拒绝";
        } else {
            color = zt == 0 ? 'hui' : zt == 1 ? 'blue' : "blue";
            text = "";
        }
        var $div = template;
        $div = $div.replace('[color]', color).replace('[color]', color).replace('[text]', text).replace('[nodeName]', data[i].nodeName);
        $('.lists').append($($div));
    }
}


var template = '<div class="schedule [color]">' +
    '        <p class="text">[nodeName] <span class="mark [color]">[text]</span></p>' +
    '    </div>';