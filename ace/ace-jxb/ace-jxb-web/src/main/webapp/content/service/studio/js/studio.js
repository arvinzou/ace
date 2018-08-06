window.onload = function () {
    getUserinfo();
    getMyStudioList();
    getStudioInfo();
    $('.submit_btn').click(submitForm);
};


function getMyStudioList() {
    var url = "/jxb/studio/getMyStudioList";
    $.getJSON(url, function (result) {
        if (result.status == 0) {
            console.log(result);
        }
    })
}


function getUserinfo() {
    var url = "/jxb/counselor/getMyinfo"
    $.getJSON(url, function (result) {
        if (result.status == 0) {
            viewUserinfo(result.value);
            // fillForm(result.value)
        }
    })
}

function getStudioInfo() {
    var url = "/jxb/studio/getMyStudioInfo";
    $.getJSON(url, function (result) {
        if (result.status == 0) {
            fillForm(result.data);
        }
    });
}

function fillForm(data) {
    for (key in data) {
        if (key.indexOf("Url") != -1) {
            $('.form_' + key).prop("src", data[key]);
            continue
        }
        $('[name=form_' + key + ']').val(data[key]);
    }
}


function viewUserinfo(data) {
    for (key in data) {
        $('.user-' + key).text(data[key]);
    }
    ;
    $('.user-imagePhotoUrl').prop('src', data['imagePhotoUrl']);
}

function submitForm() {
    var formObject = {
        name: "notNull",
        introduce: "notNull1",
    }
    for (key in formObject) {
        var idName = formObject[key];
        var result = validateform(idName);
        if (result.status == 0) {
            formObject[key] = result.message;
        } else {
            $('#' + idName).next().text(result.message);
            return
        }
    }
    ;
    var logoImgUrl = $('#logo').prop('src');
    if (logoImgUrl.indexOf("zx.huacainfo.com") == -1) {
        alert("需要上传形象照");
        return
    }
    formObject.logoImgUrl = logoImgUrl;
    var url = '/jxb/studio/insertStudio';
    $.post(url, formObject, function (result) {
        if (result.status == 0) {
            alert("信息更新成功");
            return;
        }
        alert("信息更新失败,请稍后再试！");

    })
}