var loading = {};
var payType = "0";
function loadlocal() {
    var urls = [];
    for (var i = 0; i < urls.length; i++) {
        loader(urls[i]);
    }
}

function App() {
    console.log("=============================App Start==============================");
    loadlocal();

}

function initEditor() {
    var editor = new Simditor({
        textarea: $('textarea[name=introduce]'),
        toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol',
            'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'
        ],
        upload: {
            url: portalPath + '/files/uploadImage.do', //文件上传的接口地址
            params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
            fileKey: 'file', //服务器端获取文件数据的参数名
            connectionCount: 3,
            leaveConfirm: '正在上传文件'
        }
    });
}
/*图片上传成功后*/
function viewCover(img, clazz, imgClazz, textClazz) {
    $(clazz).data('imgSrc', img);
    var imagePath = img;
    showUploadImg(imagePath, imgClazz, textClazz);
}

/*显示上传文字*/
function showUploadText(imgClazz, textClazz) {
    $(imgClazz).prop('src', '');
    $(textClazz).show();
}

/*显示上传图片*/
function showUploadImg(imgpath, imgClazz, textClazz) {
    $(imgClazz).removeClass('hidder').addClass('displayer');
    $(imgClazz).prop('src', imgpath);
    $(textClazz).hide();
}

function payTypeCheck(dom) {
    if (dom == 'noPay') {
        payType = '0';
        $('.price-panel').hide();
    } else {
        payType = '1';
        $('.price-panel').show();
    }
}

function save(params) {
    $.extend(params, {
        type: urlParams.type,
        category: '1',
        mediType: '1',
        cover: $("#courseCover").attr('src'),
        demandNum: 0,
        likeNum: 0
    });
    startLoad();
    $.ajax({
        url: contextPath + "/course/insertCourse",
        type: "post",
        async: false,
        data: {
            jsons: JSON.stringify(params)
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                console.log(result);
                alert("创建成功！");
                window.location.href = contextPath + '/dynamic/service/course/index.jsp';
            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            alert("系统服务内部异常！");
        }
    });
}




function renderPage(dom, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(dom).html(html);
}

function initPage() {
    renderPage($("#dict-149"), staticDictObject['149'], "tpl-dict-149");
    renderPage($("#dict-150"), staticDictObject['150'], "tpl-dict-150");
    $('#fm-add').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var params = {};
            $.each(formData, function (n, obj) {
                params[obj.name] = obj.value;
            });
            $.extend(params, {
                time: new Date()
            });
            console.log(params);
            save(params);
            return false;
        }
    });
    $('input[name=name]').maxlength({
        alwaysShow: true
    });
    initEditor();
    initUpload();


}


jQuery(function ($) {
    initPage();
});
