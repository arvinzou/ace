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
        console.log(result)
    });
}