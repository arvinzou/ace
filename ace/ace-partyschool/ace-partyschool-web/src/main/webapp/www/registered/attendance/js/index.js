var staticParameter={
    server : "https://www.cdswdx.top",
    lCardNo:'',
    flag:''
}
$(function () {
    $('#calendar').calendar({
        data: [],
        onSelected: function (view, date, data) {
            if (staticParameter.flag == 'MOBILE') {
                getPhoneData(formatDate(date));
            } else {
                findAttDataByDay(formatDate(date));
            }
        }
    });

    var nowDate = formatDate(new Date());
    initData(nowDate);
});

function initData(date) {
    var url = contextPath + "/www/att/getAttSrc";
    $.getJSON(url, function (rst) {
        if (rst.status == 0) {
            staticParameter.flag=rst.data.config_value;
            if (staticParameter.flag == 'MOBILE') {
                getPhoneData(date);
            } else {
                getUserInfo(date);
            }
            return;
        }
        alert(rst.info ? rst.info : rst.errorMessage);
    })
}

/**获取手机端打卡数据*/

function getPhoneData(date){
    var url = contextPath + "/www/att/findList";
    var data={
        dateTimeStr:date
    }
    $.getJSON(url,data, function (rst) {
        if (rst.status == 0) {
            renderPage('list', rst.data, 'list-tplTwo');
            return
        }
        alert(rst.info ? rst.info : rst.errorMessage);
    })
}

function getUserInfo(date) {
    var url = contextPath + "/www/sign/getAcctInfo";
    $.getJSON(url, function (result) {
        if (result.status == 0) {
            staticParameter.lCardNo = result.data.lCardNo;
            if (staticParameter.lCardNo) {
                findAttDataByDay(date);
            } else {
                alert("您还没有绑定卡的信息！");
            }
            return
        }
        alert(result.info ? result.info : result.errorMessage);
    })
}

function findAttDataByDay(date) {
    var url = staticParameter.server + "/api/www/api/findAttDataByDay";
    var data = {
        lCardNo: staticParameter.lCardNo,
        dateTimeStr: date
    };
    $.getJSON(url,data,function(result){
        if (result.status == 0) {
            renderPage('list', result.data, 'list-tpl');
            return;
        }
        alert(result.info ? result.info : result.errorMessage);
    })
}

function formatDate(date) {
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    if (month < 10) {
        month = "0" + month;
    }
    var day = date.getDate();
    if (day < 10) {
        day = "0" + day;
    }
    return year + "-" + month + "-" + day;
}

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}