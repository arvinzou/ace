var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
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
   loadPersonData();
}

function initEvents() {
    /*表单验证*/
    $("#fm-add").validate({
        onfocusout: function (element) {
            $(element).valid();
        },
		errorPlacement: function(error, element) {
             $(element).closest( "form" ).find( ".error-" + element.attr( "name" )).append( error );
        },
        rules: {
            name: {
                required: true,
                maxlength: 100
            },
            category: {
                required: true
            },
            remark: {
                required: true,
                maxlength: 500
            },
            content: {
                required: true
            }
        },
        messages: {
            name: {
                required: "请输入名称",
                maxlength: "名称字符长度不能超过100"
            },
            category: {
                required: "请选择直播类型"
            },
            remark: {
                required: "请输入摘要",
                maxlength: "摘要字符长度不能超过500"
            },
            content: {
                required: "请输入活动介绍"
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
    $.ajax({
        url: contextPath + "/live/insertLive",
        type: "post",
        async: false,
        beforeSend: function (XMLHttpRequest) {
            startLoad();
        },
        data: {
            jsons: JSON.stringify(params)
        },
        success: function (result) {
            stopLoad();
            alert(result.errorMessage);
        },
        error: function () {
			stopLoad();
            alert("对不起出错了！");
        }
    });
}

function loadPersonData() {
    $.ajax({
        type: "post",
        url: contextPath+"/fopPerson/www/selectFopPersonByCurCorpId",
        data: {
        },
        beforeSend: function (XMLHttpRequest) {
            startLoad();
        },
        success: function (rst, textStatus) {
            stopLoad();
        },
        error: function () {
            stopLoad();
            alert("加载错误！");
        }
    });
}