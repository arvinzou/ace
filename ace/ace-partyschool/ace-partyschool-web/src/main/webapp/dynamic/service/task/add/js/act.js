var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>创建任务管理</span></li>");
        initPage();
        initEvents();
    });
}

function initPage() {
/*    $("input[name=startTime]").datetimepicker({
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
    }).on('hide', function (event) {
        event.preventDefault();
        event.stopPropagation();
        var startTime = event.date;
        $("input[name=endTime]").datetimepicker('setStartDate', startTime);
        $("input[name=endTime]").val("");
    });

    $("input[name=endTime]").datetimepicker({
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
    }).on('hide', function (event) {
        event.preventDefault();
        event.stopPropagation();
        var endTime = event.date;
        $("input[name=startTime]").datetimepicker('setEndDate', endTime);
    });*/

    $(".js-example-basic-single1").select2({
        ajax: {
            url: contextPath + "/test/findTestList",
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {

                    name: params.term, // search term
                    page: params.page
                };
            },
            processResults: function (data, params) {
                params.page = params.page || 1;
                var datas = $.map(data.rows, function (obj) {
                    obj.text = obj.text || obj.name; // replace name with the property used for the text
                    return obj;
                });
                datas = $.map(datas, function (obj) {
                    obj.id = obj.id; // replace name with the property used for the text
                    return obj;
                });
                return {
                    results: datas,
                    pagination: {
                        more: (params.page * 30) < data.total_count
                    },
                    paginate: {
                        more: true
                    }
                };
            },
            cache: true
        },
        placeholder: '选择测评模板',
        escapeMarkup: function (markup) {
            return markup;
        }, // let our custom formatter work
    });
}

function initEvents() {
    /*表单验证*/
    $("#fm-add").validate({
        onfocusout: function (element) {
            $(element).valid();
        },
        rules: {
            name: {required: true, maxlength: 50},
            introduce: {required: true, maxlength: 200},
            testId: {required: true, maxlength: 50}
        },
        messages: {
            name: {
                required: "请输入任务名称",
                maxlength: "任务名称字符长度不能超过50"
            },
            introduce: {
                required: "请输入任务名称",
                maxlength: "任务名称字符长度不能超过200"
            },

            testId: {
                required: "请输入test主键",
                maxlength: "test主键字符长度不能超过50"
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
        url: contextPath + "/task/insertTask",
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