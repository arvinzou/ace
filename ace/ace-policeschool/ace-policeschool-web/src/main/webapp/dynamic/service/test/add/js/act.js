var loading = {};
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li> <span> 创建课程管理 </span></li> ");
        // initPage();
        initcategory();
        initEvents();
        initSelect();
    });
}

/**
 * 初始化类型*/
function initcategory() {
    console.log(staticDictObject['154']);
    render($("#categorys"), staticDictObject['154'].slice(1), "tpl-categorys");
}

/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}

function initSelect() {
    $(".js-example-basic-single1").select2({
        ajax: {
            url: contextPath + "/evaluating/findEvaluatingList",
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

    $(".js-example-basic-single2").select2({
        ajax: {
            url: contextPath + '/teacher/findTeacherList',
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    status: 1,
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
        placeholder: '选择授课讲师',
        escapeMarkup: function (markup) {
            return markup;
        }, // let our custom formatter work
        templateResult: formatState
    });
}

function formatState(state) {
    if (!state.id) {
        return state.text;
    }
    if (!state.photoUrl) {
        state.photoUrl = contextPath + '/content/common/img/default_header.png';
    }
    var $state = $(
        '<div style="height: 50px; margin-bottom: 5px;"><img style="height: 50px;width: 50px;object-fit: cover; overflow: hidden;margin-right: 10px" src="' + state.photoUrl + '" class="img-flag" /> ' + state.text + '</div>'
    );
    return $state;
};


/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}

function initEvents() {
    /*表单验证*/
    $("#fm-add").validate({
        onfocusout: function (element) {
            $(element).valid();
        },
        rules: {
            name: {required: true, maxlength: 50}, category: {required: true, maxlength: 50}
        },
        messages: {
            name: {
                required: "请输入名称",
                maxlength: "名称字符长度不能超过50"
            }, category: {
                required: "请输入类别",
                maxlength: "类别字符长度不能超过50"
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
        url: contextPath + "/course/insertCourse",
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