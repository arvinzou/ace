var lCardNo = null;
var server = "http://api.huacainfo.com";
$(function () {

    $.ajax({
        url: contextPath + "/www/sign/getAcctInfo",
        type: "post",
        async: false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {},
        success: function (result) {
            if (result.status == 0) {
                lCardNo = result.data.lCardNo;
            } else {
                if (result.info) {
                    alert(result.info);
                } else {
                    alert(result.errorMessage);
                }
                return;
            }
        },
        error: function () {
            alert("系统服务内部异常！");
        }
    });

    if (lCardNo) {
        initList(lCardNo);
        initCount(lCardNo);
    } else {
        $("#balance").html('<div class="noneCard">未绑定卡信息</div>');
    }
});

function initList(lCardNo) {
    var nowDate = new Date();
    var year = nowDate.getFullYear();
    var month = nowDate.getMonth() + 1;
    if (month < 10) {
        month = "0" + month;
    }
    var dateTimeStr = year + "-" + month;
    $.ajax({
        url: server + "/api/www/api/findTeacherFinDataList",
        type: "post",
        async: false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {
            lCardNo: lCardNo,
            dateTimeStr: dateTimeStr,
            startNum: 0,
            endNum: 999
        },
        success: function (result) {
            if (result.status == 0) {
                renderPage('list', result.data, 'list-tpl');
            } else {
                if (result.info) {
                    alert(result.info);
                } else {
                    alert(result.errorMessage);
                }
                return;
            }
        },
        error: function () {
            alert("系统服务内部异常！");
        }
    });
}

function initCount(lCardNo) {
    $.ajax({
        url: server + "/api/www/api/findTeacherBalance",
        type: "post",
        async: false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {
            lCardNo: lCardNo
        },
        success: function (result) {
            if (result.status == 0) {
                renderPage('balance', result.data, 'balance-tpl');
            } else {
                if (result.info) {
                    alert(result.info);
                } else {
                    alert(result.errorMessage);
                }
                return;
            }
        },
        error: function () {
            alert("系统服务内部异常！");
        }
    });
}

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}