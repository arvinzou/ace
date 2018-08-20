window.onload = function () {
    getUserinfo();
    getMyStudioList();
    getStudioInfo();
    initDoc();
    $('.submit_btn').click(submitForm);
    $('#studioInfoModal .idCardBoxs').on('click', '.deleteBtn', deleteBanner);
};

var editor;

function initDoc() {
    editor = new Simditor({
        textarea: $('#notNull1'),
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


function deleteBanner() {
    $(this).parent().parent().remove();
    $('#indexImg').show();
}


function userStudioStaff(id) {
    if (id) {
        window.location.href = '../userStudioStaff/index.jsp?id=' + id;
    }
}


function detail() {
    $('#studioInfo').modal('show');
}

function getMyStudioList() {
    var url = "/jxb/studio/getMyStudioList";
    $.getJSON(url, function (result) {
        if (result.status == 0) {
            if (result.data.join) {
                var navitem = document.getElementById('temp_studioList1').innerHTML;
                var html = juicer(navitem, {
                    data: result.data.join
                });
                $("#studioList").html(html);
            }
            if (result.data.my) {
                navitem = document.getElementById('temp_studioList').innerHTML;
                html = juicer(navitem, {
                    data: result.data.my
                });
                $("#studioList").append(html);
            }
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


function viewUserinfo(data) {
    for (key in data) {
        $('.user-' + key).text(data[key]);
    }
    ;
    $('.user-imagePhotoUrl').prop('src', data['imagePhotoUrl']);
}

var studioId;

function modify(id) {
    studioId = id;
    if (id) {
        getStudioInfo(id);
    }
    $('.modal input').val('');
    $('.modal textarea').val('');
    $('.modal #logo').prop('src', 'addImg.png');
    $('.modal .imgSrc').remove();
    $('#indexImg').show();
    $('#studioInfoModal').modal('show');

}

function getStudioInfo(id) {
    var url = "/jxb/studio/selectStudioByPrimaryKey";
    var data = {
        id: id
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            fillForm(result.value);
        }
    });
}

function fillForm(data) {
    for (key in data) {
        if (key.indexOf("Url") != -1) {
            $('.form_' + key).prop("src", data[key]);
            continue;
        }
        $('[name=form_' + key + ']').val(data[key]);
    }
    editor.setValue(data['introduce']);
    var imgs = data['imgList'];
    for (var i = 0; i < imgs.length; i++) {
        var index = $('#indexImg').siblings().length;
        var html = '<div class="imgSrc">' +
            '           <div class="idCardBox">' +
            '                <img class="select_img form_idCardImgUrl" src="' + imgs[i].imgUrl + '">' +
            '               <div class="deleteBtn">X</div>' +
            '           </div>' +
            '     </div>';
        $('#indexImg').before($(html));
        if (index == 4) {
            $('#indexImg').hide();
        }
    }
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
        return;
    }
    formObject.logoImgUrl = logoImgUrl;
    var imgs = $('.idCardBoxs .imgSrc');

    var imgURL = new Array();
    var lth = imgs.length;
    if (lth == 0) {
        alert("需要上传轮播图");
        return;
    }
    for (var i = 0; i < lth; i++) {
        var url = imgs.eq(i).find('img').prop('src');
        imgURL[i] = url;
    }
    formObject.id = studioId;
    var url = '/jxb/studio/modifyStudio';
    var data = {
        json: JSON.stringify({
            object: JSON.stringify(formObject),
            imgUrl: JSON.stringify(imgURL),
        }),
    }
    $.post(url, data, function (result) {
        if (result.status == 0) {
            alert("信息更新成功");
            $('body').on('hidden.bs.modal', '.modal', function () {
                $('#studioInfoModal').removeData('bs.modal');
            });
            return;
        }
        alert("信息更新失败,请稍后再试！");

    })
}


