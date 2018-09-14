var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        initForm();
    });
}
function App() {
    console.log("=============================App Start==============================");
    loadCustom();
}
function loadCustom() {
    var urls = [];
    for (var i = 0; i < urls.length; i++) {
        loader(urls[i]);
    }
}
function initEditor() {
    editor = new Simditor({
        textarea: $('textarea[name=introduce]'),
        toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol',
            'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'
        ],
        upload: {
            url: portalPath + '/files/uploadImage.do',
            params: null,
            fileKey: 'file',
            connectionCount: 3,
            leaveConfirm: '正在上传文件'
        }
    });
}
/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}

function initPage() {
    initEditor();
    initUpload();
}
function initEvents() {
    /*表单验证*/
    $("#fm-edit").validate({
        onfocusout: function (element) {
            $(element).valid();
        },
        rules: {
            realName: {required: true, maxlength: 50}, mobilePhone: {required: true, maxlength: 20}
        },
        messages: {
            realName: {
                required: "请输入姓名",
                maxlength: "姓名字符长度不能超过50"
            }, mobilePhone: {
                required: "请输入手机号",
                maxlength: "手机号字符长度不能超过20"
            }
        }
    });
    /*监听表单提交*/
    $('#fm-edit').ajaxForm({
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
}
/*保存表单**/
function save(params) {
    $.extend(params, {});
    startLoad();
    $.ajax({
        url: contextPath + "/personInfo/updatePersonInfo",
        type: "post",
        async: false,
        data: {
            jsons: JSON.stringify(params)
        },
        success: function (result) {
            stopLoad();
            alert(result.errorMessage);
            if (result.status == 0) {
                window.location.href = '../index.jsp';
            }
        },
        error: function () {
            alert("对不起出错了！");
        }
    });
}

function initForm() {
    $.ajax({
        url: contextPath + "/personInfo/selectPersonInfoByPrimaryKey",
        type: "post",
        async: false,
        data: {
            id: urlParams.did
        },
        success: function (result) {
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                renderPage($("#fm-edit"), data, 'tpl-fm');
                initPage();
            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            alert("对不起出错了！");
        }
    });
}