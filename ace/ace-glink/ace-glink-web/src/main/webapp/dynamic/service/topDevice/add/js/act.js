var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li> <span> 创建设备管理 </span></li> ");
        initPage();
        initEvents();

    });
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
    initForm();
}

function initEvents() {
    /*表单验证*/
    $("#fm-add").validate({
        onfocusout: function (element) {
            $(element).valid();
        },
        rules: {
            code: {required: true, maxlength: 50}, name: {required: true, maxlength: 50}
        },
        messages: {
            code: {
                required: "请输入设备编号",
                maxlength: "设备编号字符长度不能超过50"
            }, name: {
                required: "请输入设备名称",
                maxlength: "设备名称字符长度不能超过50"
            }
        }
    });
    /*监听表单提交*/
    $('#fm-add').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var params = {};
            $.each(formData, function (n, obj) {
                params[obj.name] = obj.value;
            });
            $.extend(params, {
                time: new Date(),
//coverUrl: $('#coverUrl').attr("src"),
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
        url: contextPath + "/topDevice/insertTopDevice",
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

function datetimepicker(name) {
    name.datetimepicker({
        format: 'yyyy-mm-dd hh:ii:ss',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1, //显示‘今日’按钮
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 'hour', //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
        clearBtn: true, //清除按钮
        forceParse: 0
    });
    name.focus(function () {
        $(this).blur(); //不可输入状态
    });
}



function initForm() {
    var data = staticDictObject;
    render('#fm-add-panel', data, 'tpl-fm-add');
    datetimepicker($('#onlineDate'));
    datetimepicker($('#offlineDate'));
    datetimepicker($('#prcDate'));

    $(".form-body input[name='nodeCode']").combogrid({
        url: contextPath + "/topNode/findTopNodeList",
        method: 'get',
        loadMsg: "正在获取...",
        width: '100%',
        mode: 'remote',
        idField: 'code',
        textField: 'name',
        columns: [[
            {field: 'name', title: '节点名称', width: 200},
            {field: 'code', title: '节点编号', width: 200}
        ]]
    });

}
