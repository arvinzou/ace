$(function () {
    userInfo();
})

var deptID;


function userInfo() {
    var url = "/fundtown/www/process/getMyVipInfo";
    $.getJSON(url, function (result) {
        if (result.status == 0) {
            deptID = result.data.deptId;
            viewInfo(result.data);
            $('li').on('click', enter);
        }
        else {
            alert("没有获取到用户信息。");
        }
    });
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
            if (deptID) {
                window.location.href = 'schedule.html';
                break;
            }
            window.location.href = 'form.html';
            break;
        case 1:
            window.location.href = 'video.html';
            break;
    }
}