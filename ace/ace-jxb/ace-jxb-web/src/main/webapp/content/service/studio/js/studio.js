window.onload = function () {
    getUserinfo();
    getMyStudioList();
    initDoc();
    $('#studioInfoModal .idCardBoxs').on('click', '.deleteBtn', deleteBanner);
};

var editor, mySwiper;

/*初始化轮播图*/
function initSwriper() {
    mySwiper = new Swiper('.swiper-container', {
        loop: true,
        // 如果需要分页器
        pagination: {
            el: '.swiper-pagination',
        },

        // 如果需要前进后退按钮
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },

        // 如果需要滚动条
        scrollbar: {
            el: '.swiper-scrollbar',
        },
    })
}


/*初始化富文本框*/
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

/*删除图片*/
function deleteBanner() {
    $(this).parent().parent().remove();
    $('#indexImg').show();
}

/*转到工作室成员列表*/
function userStudioStaff(id) {
    if (id) {
        window.location.href = '../userStudioStaff/index.jsp?id=' + id;
    }
}

/*点击创建工作室*/
function createStudio() {
    cleanForm();
    $('#studioInfoModal').modal('show');
    $('.submit_btn').off("click");
    $('.submit_btn').click(createMyStudio);
}

/*创建我的工作室*/
function createMyStudio() {
    var data = {json: submitForm()};
    var url = '/jxb/studio/insertStudioVo';
    $.post(url, data, function (result) {
        if (result.status == 0) {
            alert("工作室创建成功");
            $('#studioInfoModal').modal('hide');
            getMyStudioList();
            return;
        }
        alert("信息更新失败,请稍后再试！");

    })
}

/*清空form表单*/
function cleanForm() {
    $('.modal input').val('');
    $('.modal textarea').val('');
    $('.modal #logo').prop('src', 'addImg.png');
    $('.modal .imgSrc').remove();
    $('#indexImg').show();
    editor.setValue('');
}

/*查看工作室详情*/
function detail(id) {
    // if(mySwiper){
    //     mySwiper.destroy();
    // }
    $('#studioInfo').modal('show');
    if (id) {
        var url = "/jxb/studio/selectStudioByPrimaryKey";
        var data = {
            id: id
        }
        $.getJSON(url, data, function (result) {
            if (result.status == 0) {
                var navitem = document.getElementById('temp_modalstudioInfo').innerHTML;
                var html = juicer(navitem, {
                    data: result.value
                });
                $("#modalstudioInfo").html(html);
                initSwriper();

            }
        });
    }
}

/*获取工作室列表*/
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


/*获取我的信息*/
function getUserinfo() {
    var url = "/jxb/counselor/getMyinfo"
    $.getJSON(url, function (result) {
        if (result.status == 0) {
            viewUserinfo(result.value);
            // fillForm(result.value)
        }
    })
}

/*渲染我的信息*/
function viewUserinfo(data) {
    for (key in data) {
        $('.user-' + key).text(data[key]);
    }
    ;
    $('.user-imagePhotoUrl').prop('src', data['imagePhotoUrl']);
}

var studioId;


/*触发修改工作室*/
function modify(id) {
    studioId = id;
    if (id) {
        cleanForm();
        getStudioInfo(id);
    }
    $('#studioInfoModal').modal('show');
    $('.submit_btn').off("click");
    $('.submit_btn').click(modifyStudio);

}

/*提交工作室修改*/
function modifyStudio() {
    var url = '/jxb/studio/modifyStudio';
    var data = {json: submitForm()};
    $.post(url, data, function (result) {
        if (result.status == 0) {
            alert("工作室修改成功");
            $('#studioInfoModal').modal('hide');
            getMyStudioList();
            return;
        }
        alert("信息更新失败,请稍后再试！");
    })
}


/*获取单个工作室信息*/
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


/*修改数据钱填充数据*/
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


/*提交表单获取数据*/
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
    return JSON.stringify({
        object: JSON.stringify(formObject),
        imgUrl: JSON.stringify(imgURL),
    });
}


