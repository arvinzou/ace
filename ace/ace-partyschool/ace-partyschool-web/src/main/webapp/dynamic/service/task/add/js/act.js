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
                required: "请输入测试介绍",
                maxlength: "任务名称字符长度不能超过200"
            },

            testId: {
                required: "请选择测评模板",
                maxlength: "测评模板字符长度不能超过50"
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