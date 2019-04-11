var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>创建故障短信模板</span></li>");
        initPage();
        initEvents();

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
            subareaCode: {required: true, maxlength: 50},
            smsName: {required: true, maxlength: 50},
            smsContent: {required: true, maxlength: 200}
        },
        messages: {
            subareaCode: {
                required: "请输入分区代码",
                maxlength: "分区代码字符长度不能超过50"
            }, smsName: {
                required: "请输入模板名称",
                maxlength: "模板名称字符长度不能超过50"
            }, smsContent: {
                required: "请输入模板内容",
                maxlength: "模板内容字符长度不能超过200"
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
        url: contextPath + "/errWarnSms/insertErrWarnSms",
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
    var data = staticDictObject;
    render('#fm-add-panel', data, 'tpl-fm-add');
    //
    $('input[name=subareaCode]').combogrid({
        panelWidth: 500,
        idField: 'code',
        textField: 'name',
        url: contextPath + '/topSubarea/findTopSubareaList',
        mode: 'remote',
        fitColumns: true,
        method: 'get',
        columns: [
            [{
                field: 'name',
                title: '分区名称',
                width: 100
            }, {
                field: 'code',
                title: '分区编码',
                width: 100
            }]

        ],
    });
}

