var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append(" <li><span>创建评测管理</span></li>");
        initEvents();
        $('.addOption').on('click','',addOption);
        $('#evaluatingRst ').on('click','.removeOption',removeOption);
    });
}

function removeOption() {
    $(this).parent().remove();
}

function addOption() {
    var index=$('#evaluatingRst .form-group').length;
    var temp=optionTemp;
    temp = temp.replace('#index#',index);
    temp = temp.replace('#index#',index);
    var $p = $(temp);
    $('#evaluatingRst').append($p);

}


var optionTemp='<div class="form-group">\n' +
    '                            <label class="col-md-2 control-label">\n' +
    '                                内容\n' +
    '                                <span class="required" aria-required="true"> * </span>\n' +
    '                            </label>\n' +
    '                            <div class="col-md-10">\n' +
    '                                <input type="text" class="form-control" name="evaluationIndex[#index#].name"\n' +
    '                                       maxlength="10"\n' +
    '                                       placeholder="请输入超时设定（建议字数在14个字以内，不超过10个字)">\n' +
    '                                <span class="help-block"></span>\n' +
    '                            </div>\n' +
    '                            <label class="col-md-2 control-label">\n' +
    '                                分值\n' +
    '                                <span class="required" aria-required="true"> * </span>\n' +
    '                            </label>\n' +
    '                            <div class="col-md-6">\n' +
    '                                <input type="text" class="form-control" name="evaluationIndex[#index#].score"\n' +
    '                                       maxlength="10"\n' +
    '                                       placeholder="请输入超时设定（建议字数在14个字以内，不超过10个字)">\n' +
    '                                <span class="help-block"></span>\n' +
    '                            </div>\n' +
    '                            <button type="button" class="btn btn-success removeOption col-md-1">删除</button>'+
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
    evaluating={};
    evaluating.name=params.name;
    evaluating.timeout=params.timeout;
    evaluating.introduce=params.introduce;
    evaluationIndex=[];
    var index=0;
    while(params['evaluationIndex['+index+'].name']){
        evaluationIndex.push({
            name:params['evaluationIndex['+index+'].name'],
            score:params['evaluationIndex['+index+'].score']
        })
        index++;
    }
    params.evaluating=evaluating;
    params.evaluationIndex=evaluationIndex;
    console.log(params);
    startLoad();
    $.ajax({
        url: contextPath + "/evaluating/insertEvaluating",
        type: "post",
        async: false,
        data:  {jsons:JSON.stringify(params)},
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