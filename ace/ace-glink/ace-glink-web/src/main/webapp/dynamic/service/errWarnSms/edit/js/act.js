var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>编辑故障短信模板</span></li>");
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

/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}

function initPage() {
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

function initEvents() {
    /*表单验证*/
    $("#fm-edit").validate({
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
        url: contextPath + "/errWarnSms/updateErrWarnSms",
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
        url: contextPath + "/errWarnSms/selectErrWarnSmsByPrimaryKey",
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
