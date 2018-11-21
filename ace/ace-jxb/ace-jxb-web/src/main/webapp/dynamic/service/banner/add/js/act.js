var loading = {};
var editor;
window.onload = function (){
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>创建轮播图</span></li>");
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
initUpload();
}
function initEvents(){
    /*表单验证*/
    $("#fm-add").validate({
        onfocusout: function(element) { $(element).valid(); },
        rules: {
                                         title: {required: true,maxlength:50},                             imgUrl: {required: true,maxlength:200},                             url: {required: true,maxlength:200}                                 },
        messages: {
                                         title: {
                    required: "请输入标题",
                    maxlength:"标题字符长度不能超过50"
                },                             imgUrl: {
                    required: "请输入图片URL",
                    maxlength:"图片URL字符长度不能超过200"
                },                             url: {
                    required: "请输入资源地址",
                    maxlength:"资源地址字符长度不能超过200"
                }                                 }
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
    $.extend(params, {
    });
    startLoad();
    $.ajax({
        url: contextPath + "/banner/insertBanner",
        type: "post",
        async: false,
        data: {
            jsons: JSON.stringify(params)
        },
        success: function (result) {
            stopLoad();
			alert(result.errorMessage);
            if (result.status == 0) {
                window.location.href ='../index.jsp?id='+urlParams.id;
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}