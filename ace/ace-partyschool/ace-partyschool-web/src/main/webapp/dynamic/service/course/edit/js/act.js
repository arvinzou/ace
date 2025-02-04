var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>编辑课程管理</span></li>");
        initForm();
        initEvents();
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
    $("#fm-edit").validate({
        onfocusout: function (element) {
            $(element).valid();
        },
        rules: {
            name: {required: true, maxlength: 50},
            category: {required: true, maxlength: 50},
            teacherId: {required: true, maxlength: 50}
        },
        messages: {
            name: {
                required: "请输入名称",
                maxlength: "名称字符长度不能超过50"
            }, category: {
                required: "请输入类别",
                maxlength: "类别字符长度不能超过50"
            }, teacherId: {
                required: "请输入授课人",
                maxlength: "类别字符长度不能超过50"
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
                id: urlParams.did
            });
            params.teacherIds = $(".js-example-basic-single2").select2("val");
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
        url: contextPath + "/course/updateCourse",
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

function initSelect(data) {
    var select2 = $(".js-example-basic-single1").select2({
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

    var select3 = $(".js-example-basic-single2").select2({
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
        multiple: true,
        maximumSelectionLength: 5,
        placeholder: '选择授课人',
        escapeMarkup: function (markup) {
            return markup;
        }, // let our custom formatter work
        templateResult: formatState
    });
}

function initForm() {
    startLoad();
    $.ajax({
        url: contextPath + "/course/selectCourseByPrimaryKey",
        type: "post",
        async: false,
        data: {id: urlParams.did},
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                data['dict154'] = staticDictObject['154'];
                render('#fm-edit', data, 'tpl-fm');
                initSelect(data);
                var list = result.value.courseTeacherVoList;
                var ids = [];
                for (var i = 0; i < list.length; i++) {
                    ids.push(list[i].teacher_id);
                }
                $('.js-example-basic-single2').val(ids).trigger('change');
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
