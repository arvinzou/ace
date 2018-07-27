var upimgObject;


function storeObject() {
    upimgObject = $(this);
}

var areaCode;


window.onload = function () {

    getUserinfo();
    $('.fileUploads').on('click', '.fileUp', storeObject);
    $("#city").click(function (e) {
        SelCity(this, e);
    });
    $("#city_edit").click(function (e) {
        SelCity(this, e);
    });
    $("s").click(function (e) {
        SelCity(document.getElementById("city"), e);
    });
    $('.submit_btn').click(submitForm);
};


function getUserinfo() {
    var url = "/jxb/counselor/getMyinfo"
    $.getJSON(url, function (result) {
        if (result.status == 0) {
            viewUserinfo(result.value)
        }
    })
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
        name: "chineseName",
        mobile: "mobilePhone",
        profile: "notNull",
        certificateNo: "notNull1",
        idCard: "IDcard"
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
    var sex = $('input:radio[name="sex"]:checked').val();
    formObject.sex = sex;
    var imagePhotoUrl = $('#headimg1').prop('src');
    if (imagePhotoUrl.indexOf("zx.huacainfo.com") == -1) {
        alert("需要上传形象照");
        return
    }
    formObject.imagePhotoUrl = imagePhotoUrl;
    var idCardImgUrl = $('#IDcardz').prop('src');

    if (idCardImgUrl.indexOf("zx.huacainfo.com") == -1) {
        alert("需要上传身份证正面照");
        return
    }
    formObject.idCardImgUrl = idCardImgUrl;


    var idCardImgUrl1 = $('#IDcardf').prop('src');

    if (idCardImgUrl1.indexOf("zx.huacainfo.com") == -1) {
        alert("需要上传身份证反面照");
        return
    }
    formObject.idCardImgUrl1 = idCardImgUrl1;


    var evidenceImgUrl = $('#IDcardsc').prop('src');

    if (evidenceImgUrl.indexOf("zx.huacainfo.com") == -1) {
        alert("需要上传手持身份证照片");
        return
    }
    formObject.evidenceImgUrl = evidenceImgUrl;


    var certification = $('input:radio[name="certification"]:checked').next().text();
    formObject.certification = certification.trim();


    var certificateImgUrl = $('#certificateimg').prop('src');

    if (certificateImgUrl.indexOf("zx.huacainfo.com") == -1) {
        alert("需要上传资格证书照片");
        return
    }
    formObject.certificateImgUrl = certificateImgUrl;

    var arr = new Array();
    $('#tags :checkbox:checked').each(function (i) {
        arr[i] = $(this).next().text().trim();
    });
    var tags = arr.join(",");
    if (tags.length = 0) {
        alert("还没有选择您的个人擅长");
        return
    }
    formObject.tags = tags;
    var url = '/jxb/counselor/updateUserinfo';
    $.post(url, formObject, function (result) {
        if (result.status == 0) {
            alert("信息更新成功");
            return;
        }
        alert("信息更新失败,请稍后再试！");

    })
}