var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>编辑路由器运行状态</span></li>");
        initForm();
        initEvents();
        initJuicerMethod();
    });
}

//juicer自定义函数
function initJuicerMethod() {
    juicer.register('rsd', rsd);
    juicer.register('parseStatus', parseStatus);
}

/**
 * 状态解析
 */
function parseStatus(status) {
    switch (status) {
        case '0':
            return "未读";
        case '1':
            return "已读";
        default:
            return "未读";
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
//   initUpload();
}

function initEvents() {
    /*表单验证*/
    $("#fm-edit").validate({
        onfocusout: function (element) {
            $(element).valid();
        },
        rules: {},
        messages: {}
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
        url: contextPath + "/seRouteState/updateSeRouteState",
        type: "post",
        async: false,
        data: {
            jsons: JSON.stringify(params)
        },
        success: function (result) {
            stopLoad();
            alert(result.errorMessage);
            if (result.status == 0) {
                window.location.href = '../index.jsp?id=' + urlParams.id;
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}

function initForm() {
    startLoad();
    $.ajax({
        url: contextPath + "/seRouteState/selectSeRouteStateByPrimaryKey",
        type: "post",
        async: false,
        data: {id: urlParams.did},
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                render('#fm-edit', data, 'tpl-fm');
                initPage();
            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}
