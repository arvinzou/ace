var editor, cityCode, singleSelect1, regAuditRst;


window.onload = function () {

    getUserinfo();
    //单选设置城市
    $('.submit_btn').click(submitForm);

};


function initcitySelect() {
    singleSelect1 = $('#single-select-1').citySelect({
        dataJson: cityData,
        multiSelect: false,
        whole: false,
        shorthand: true,
        search: true,
        addInputClass: 'form-control',
        onInit: function () {
            console.log(this)
        },
        onTabsAfter: function (target) {
            console.log(target)
        },
        onCallerAfter: function (target, values) {
            console.log(JSON.stringify(values));
            cityCode = values.name;
        }
    });
}


function initDoc() {
    editor = new Simditor({
        textarea: $('#notNull_profile'),
        toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'],
        upload: {
            url: portalPath + '/files/uploadImage.do', //文件上传的接口地址
            params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
            fileKey: 'file', //服务器端获取文件数据的参数名
            connectionCount: 3,
            leaveConfirm: '正在上传文件'
        }
    });
}

function getUserinfo() {
        var url = "/jxb/counselor/getMyinfo"
        $.getJSON(url, function (result) {
            if (result.status == 0) {
                regAuditRst = result.value.regAuditRst;
                var navitem = document.getElementById('temp-form_content').innerHTML;
                var html = juicer(navitem, {
                    data: regAuditRst
                });

                $("#form_content").html(html);
                initDoc();
                initcitySelect();
                fillForm(result.value)
            }
        })
}


function fillForm(data) {
    for (key in data) {
        if (key.indexOf("Url") != -1) {
            $('.form_' + key).prop("src", data[key]);
            continue
        }
        $('[name=form_' + key + ']').val(data[key]);
    }
    singleSelect1.setCityVal(data.cityCode + "市");
    editor.setValue(data['profile'] ? data['profile'] : '');
    var tag = $('#certification .md-radio');
    var tags = data["certification"];
    filloption(tag, tags);
}

function filloption(tag, tags) {
    if (tags) {
        for (var i = 0; i < tag.length; i++) {
            var text = tag.eq(i).find('label').text().trim();
            if (tags.indexOf(text) != -1) {
                tag.eq(i).find('input').attr('checked', true)
            }
        }
    }
}


// function viewUserinfo(data) {
//     for (key in data) {
//         $('.user-' + key).text(data[key]);
//     }
//     ;
//     $('.user-imagePhotoUrl').prop('src', data['imagePhotoUrl']);
// }

function submitForm() {
    if (!$(".protocol[type='checkbox']").prop('checked')) {
        alert("需要同意近心帮协议");
        return null;
    }
    var formObject = {
        profile: "notNull_profile",
        duration: 'naturalNumber',
        peopleNum: 'naturalNumber1'
    }
    if (regAuditRst != 1) {
        formObject.name = "chineseName";
        formObject.mobile = "mobilePhone";
        formObject.certificateNo = "notNull1";
        formObject.idCard = "IDcard";
        formObject.idCard = "IDcard";
    }
    for (key in formObject) {
        var idName = formObject[key];
        var result = validateform(idName);
        if (result.status == 0) {
            formObject[key] = result.message;
        } else {
            $('#' + idName).next().text(result.message);
            return null;
        }
    }
    ;

    if (regAuditRst != 1) {
        var sex = $('input:radio[name="form_sex"]:checked').val();
        formObject.sex = sex;
        var idCardImgUrl = $('#IDcardz').prop('src');
        if (idCardImgUrl.indexOf("zx.huacainfo.com") == -1) {
            alert("需要上传身份证正面照");
            return null;
        }
        formObject.idCardImgUrl = idCardImgUrl;


        var idCardImgUrl1 = $('#IDcardf').prop('src');

        if (idCardImgUrl1.indexOf("zx.huacainfo.com") == -1) {
            alert("需要上传身份证反面照");
            return null;
        }
        formObject.idCardSideImgUrl = idCardImgUrl1;


        var evidenceImgUrl = $('#IDcardsc').prop('src');

        if (evidenceImgUrl.indexOf("zx.huacainfo.com") == -1) {
            alert("需要上传手持身份证照片");
            return null;
        }
        formObject.evidenceImgUrl = evidenceImgUrl;


        var certification = $('input:radio[name="form_certification"]:checked').next().text();
        formObject.certification = certification.trim();


        var certificateImgUrl = $('#certificateimg').prop('src');

        if (certificateImgUrl.indexOf("zx.huacainfo.com") == -1) {
            alert("需要上传资格证书照片");
            return null;
        }
        formObject.certificateImgUrl = certificateImgUrl;

    }

    var imagePhotoUrl = $('#headimg1').prop('src');
    if (imagePhotoUrl.indexOf("zx.huacainfo.com") == -1) {
        alert("需要上传形象照");
        return null;
    }
    formObject.imagePhotoUrl = imagePhotoUrl;

    formObject.cityCode = cityCode;

    var url = '/jxb/counselor/updateUserinfo';
    $.post(url, formObject, function (result) {
        if (result.status == 0) {
            if (regAuditRst == 1) {
                alert("更新成功");
                return null;
            }
            alert("提交成功，平台官方会在2个工作日内反馈申请结果。");
            return null;
        }
        alert("信息更新失败,请稍后再试！");
    })
}
