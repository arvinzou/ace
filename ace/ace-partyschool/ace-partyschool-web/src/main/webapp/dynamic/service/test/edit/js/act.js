var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>编辑测评结果管理</span></li>");
        initForm();
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
    // $("input[name=startTime]").datetimepicker({
    //     format: 'yyyy-mm-dd hh:ii:ss',
    //     language: 'zh-CN',
    //     weekStart: 1,
    //     todayBtn: 1, //显示‘今日’按钮
    //     autoclose: 1,
    //     todayHighlight: 1,
    //     startView: 2,
    //     minView: 'hour', //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
    //     clearBtn: true, //清除按钮
    //     forceParse: 0
    // }).on('hide', function(event) {
    //     event.preventDefault();
    //     event.stopPropagation();
    //     var startTime = event.date;
    //     $("input[name=endTime]").datetimepicker('setStartDate',startTime);
    //     $("input[name=endTime]").val("");
    // });
    // $("input[name=endTime]").datetimepicker({
    //     format: 'yyyy-mm-dd hh:ii:ss',
    //     language: 'zh-CN',
    //     weekStart: 1,
    //     todayBtn: 1, //显示‘今日’按钮
    //     autoclose: 1,
    //     todayHighlight: 1,
    //     startView: 2,
    //     minView: 'hour', //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
    //     clearBtn: true, //清除按钮
    //     forceParse: 0
    // }).on('hide', function(event) {
    //     event.preventDefault();
    //     event.stopPropagation();
    //     var endTime = event.date;
    //     $("input[name=startTime]").datetimepicker('setEndDate',endTime);
    // });
}

function initEvents() {
    /*表单验证*/
    $("#fm-edit").validate({
        onfocusout: function (element) {
            $(element).valid();
        },
        rules: {
            name: {required: true, maxlength: 50}
        },
        messages: {
            name: {
                required: "请输入测试名称",
                maxlength: "测试名称字符长度不能超过50"
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
        url: contextPath + "/test/updateTest",
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
        url: contextPath + "/test/selectTestByPrimaryKey",
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
//富文本填值
//editor.setValue(data['o'].summary);
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
