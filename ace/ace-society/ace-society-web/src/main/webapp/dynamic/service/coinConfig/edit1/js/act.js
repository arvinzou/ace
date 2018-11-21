var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        initForm();
        initEvents();
    });
    jQuery.validator.addMethod("integer", function(value, element) {
        var tel =/^-?\d+$/;
        return this.optional(element) || (tel.test(value));
    }, "格式错误");
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

// function initPage() {
//     // initEditor();
//   // initUpload();
// // }

function initEvents() {
    /*表单验证*/
    $("#fm-edit").validate({
        onfocusout: function (element) {
            $(element).valid();
        },
        rules: {
            name: {required: true, maxlength: 20},
            host: {required: true, digits: true},
            participant: {required: false, integer: true}
        },
        messages: {
            name: {
                required: "请输入配置名称",
                maxlength: "字符长度不能超过20"
            },
            host: {
                required: "此项为必输项",
                digits: "必须是整数"
            }
            ,
            participant: {
                required: "",
                integer: "必须是整数"
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
                time: new Date(),
//coverUrl: $('#coverUrl').attr("src")
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
    params.id=urlParams.did;
    startLoad();
    $.ajax({
        url: contextPath + "/coinConfig/updateCoinConfig",
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
        url: contextPath + "/coinConfig/selectCoinConfigByPrimaryKey",
        type: "post",
        async: false,
        data: {id: urlParams.did},
        success: function (result) {
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                render('#fm-edit', data, 'tpl-fm');
                // initPage();
// 富文本填值
//editor.setValue(data['o'].summary);
            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            alert("对不起出错了！");
        }
    });
}
