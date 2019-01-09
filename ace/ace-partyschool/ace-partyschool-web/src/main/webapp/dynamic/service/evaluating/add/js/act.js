var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append(" <li><span>创建评测管理</span></li>");
        initEvents();
        $('.addOption').on('click', '', addOption);
        $('#evaluatingRst ').on('click', '.removeOption', removeOption);
    });
}

function removeOption() {
    var index = $('#evaluatingRst .form-group').length;
    $('#evaluatingRst .removeOption' + (index - 2)).show();
    $(this).parent().remove();
}

function addOption() {
    var index = $('#evaluatingRst .form-group').length;
    $('#evaluatingRst .removeOption').hide();
    var temp = optionTemp;
    temp = temp.replace('#index#', index);
    temp = temp.replace('#index#', index);
    temp = temp.replace('#index#', index);
    temp = temp.replace('#index#', index);
    var $p = $(temp);
    $('#evaluatingRst').append($p);
}


var optionTemp = '<div class="form-group">\n' +
    '                            <label class="col-md-2 control-label">\n' +
    '                                评测指标\n' +
    '                                <span class="required" aria-required="true"> * </span>\n' +
    '                            </label>\n' +
    '                            <div class="col-md-10">\n' +
    '                                <input type="text" class="form-control" name="evaluationIndex[#index#].name"\n' +
    '                                       maxlength="10"\n' +
    '                                       placeholder="请输入评测指标（建议字数在6个字以内)">\n' +
    '                                <span class="help-block"></span>\n' +
    '                            </div>\n' +
    '                            <label class="col-md-2 control-label">\n' +
    '                                指标内容\n' +
    '                                <span class="required" aria-required="true"> * </span>\n' +
    '                            </label>\n' +
    '                            <div class="col-md-10">\n' +
    '                                <input type="text" class="form-control" name="evaluationIndex[#index#].introduce"\n' +
    '                                       maxlength="10"\n' +
    '                                       placeholder="请输入评测内容（建议字数在30个字以内)">\n' +
    '                                <span class="help-block"></span>\n' +
    '                            </div>\n' +
    '                            <label class="col-md-2 control-label">\n' +
    '                                指标分值\n' +
    '                                <span class="required" aria-required="true"> * </span>\n' +
    '                            </label>\n' +
    '                            <div class="col-md-6">\n' +
    '                                <input type="text" class="form-control" name="evaluationIndex[#index#].score"\n' +
    '                                       maxlength="10"\n' +
    '                                       placeholder="请输入评测分值(填写数字)">\n' +
    '                                <span class="help-block"></span>\n' +
    '                            </div>\n' +
    '                            <button type="button" class="btn btn-success removeOption removeOption#index# col-md-1">删除</button>' +
    '                        </div>';


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
            name: {required: true, maxlength: 30},
            introduce: {required: true, maxlength: 200},
            timeout: {required: true, digits:true},
            'evaluationIndex[0].name': {required: true, maxlength: 6},
            'evaluationIndex[0].introduce': {required: true, maxlength: 40},
            'evaluationIndex[0].introduce': {required: true, digits:true}
        },
        messages: {
            name: {
                required: "请输入",
                maxlength: "字数不能超过30"
            },
            introduce: {
                required: "请输入",
                maxlength: "字数不能超过200"
            },
            timeout: {
                required: "请输入",
                digits:"必须为整数字",
            },
            'evaluationIndex[0].name': {
                required: "请输入",
                maxlength: "字数不能超过6"
            },
            'evaluationIndex[0].introduce': {
                required: "请输入",
                maxlength: "字数不能超过40"
            },
            'evaluationIndex[0].score': {
                required: "请输入指标分值",
                digits:"必须为整数字",
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
    evaluating = {};
    evaluating.name = params.name;
    evaluating.timeout = params.timeout;
    evaluating.introduce = params.introduce;
    evaluationIndex = [];
    var index = 0;
    while (params['evaluationIndex[' + index + '].name']) {
        evaluationIndex.push({
            name: params['evaluationIndex[' + index + '].name'],
            score: params['evaluationIndex[' + index + '].score']
        })
        index++;
    }
    params.evaluating = evaluating;
    params.evaluationIndex = evaluationIndex;
    startLoad();
    $.ajax({
        url: contextPath + "/evaluating/insertEvaluating",
        type: "post",
        async: false,
        data: {jsons: JSON.stringify(params)},
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