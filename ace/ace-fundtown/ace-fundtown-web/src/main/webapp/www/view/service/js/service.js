$(function () {
    userInfo();
    $('.list').on('click', 'li', enter);
})

var status;


function userInfo() {
    var url = "/fundtown/www/process/getMyVipInfo";
    $.ajaxSettings.async = false;
    $.getJSON(url, function (result) {
        status = result.data.vipStatus;
        viewInfo(result.data);
    });
    $.ajaxSettings.async = true;
}


function viewInfo(data) {
    $('.name').text(data.nickName);
    $('.headimgUrl').prop('src', data.headimgUrl);
}

function enter() {
    var $that = $(this);
    var index = $that.index();
    switch (index) {
        case 0:
            window.location.href = 'form.html';
            break;
        case 1:
            window.location.href = 'video.html';
            break;
    }
}