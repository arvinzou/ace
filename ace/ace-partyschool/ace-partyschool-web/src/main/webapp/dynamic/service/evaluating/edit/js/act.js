var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append(" <li><span>编辑测评管理</span></li>");
        initForm();
        initEvents();
        $('.addOption').on('click', '', addOption);
        $('#evaluatingRst ').on('click', '.removeOption', removeOption);
    });
}


function removeOption() {
    var index = $('#evaluatingRst .form-group').length;
    $('#evaluatingRst .removeOption'+(index-2)).show();
    $(this).parent().parent().parent().remove();
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


var optionTemp= '                        <div class="form-group">\n' +
    '                            <div class="row">\n' +
    '                                <label class="col-md-2 control-label">\n' +
    '                                    指标名称\n' +
    '                                </label>\n' +
    '                                <div class="col-md-4">\n' +
    '                                    <input type="text" class="form-control" name="evaluationIndex[#index#].name"\n' +
    '                                           maxlength="4"\n' +
    '                                           placeholder="请输入测评指标（建议字数在6个字以内)">\n' +
    '                                    <span class="help-block"></span>\n' +
    '                                </div>\n' +
    '                                <label class="col-md-1 control-label">\n' +
    '                                    指标分值\n' +
    '                                </label>\n' +
    '                                <div class="col-md-1">\n' +
    '                                    <input type="text" class="form-control scores" name="evaluationIndex[#index#].score"\n' +
    '                                           maxlength="10"\n' +
    '                                           placeholder="分值">\n' +
    '                                    <span class="help-block"></span>\n' +
    '                                </div>\n' +
    '                            </div>\n' +
    '                            <div class="row">\n' +
    '                                <label class="col-md-2 control-label">\n' +
    '                                    指标内容\n' +
    '                                </label>\n' +
    '                                <div class="col-md-6">\n' +
    ' <textarea name="evaluationIndex[#index#].introduce" id="" rows="3"></textarea>'+
    '                                    <span class="help-block"></span>\n' +
    '                                </div>\n' +
    '                           <div class="col-md-1" style="text-align: center;"><span type="button" class=" removeOption removeOption#index#">删除</span></div> ' +
    '                            </div>\n' +
    '                        </div>';

/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
    $('#evaluatingRst .removeOption').hide();
    $('#evaluatingRst .form-group').last().find('.removeOption').show();
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
                required: "请输入名称",
                maxlength: "名称字符长度不能超过50"
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
    evaluating.id = params.id;
    evaluationIndex = [];
    var index = 0;
    while (params['evaluationIndex[' + index + '].name']) {
        evaluationIndex.push({
            name: params['evaluationIndex[' + index + '].name'],
            score: params['evaluationIndex[' + index + '].score'],
            introduce: params['evaluationIndex[' + index + '].introduce'],
            id: params['evaluationIndex[' + index + '].id']
        })
        index++;
    }
    params.evaluating = evaluating;
    params.evaluationIndex = evaluationIndex;

    startLoad();
    $.ajax({
        url: contextPath + "/evaluating/updateEvaluating",
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
        url: contextPath + "/evaluating/selectEvaluatingByPrimaryKey",
        type: "post",
        async: false,
        data: {id: urlParams.did},
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data = result.value;
                render('#fm-edit', data, 'tpl-fm');
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
