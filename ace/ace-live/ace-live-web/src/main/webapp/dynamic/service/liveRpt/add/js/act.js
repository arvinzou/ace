var loading = {};
var editor;
window.onload = function (){
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>创建报道</span></li>");
        initPage();
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
    initEditor();
//    initUpload();
}
function initEvents(){
    /*表单验证*/
    $("#fm-add").validate({
        onfocusout: function(element) { $(element).valid(); },
        rules: {
                                         rid: {required: true,maxlength:50},                             uid: {required: true,maxlength:50}                                 },
        messages: {
                                         rid: {
                    required: "请输入直播间编号",
                    maxlength:"直播间编号字符长度不能超过50"
                },                             uid: {
                    required: "请输入用户编号",
                    maxlength:"用户编号字符长度不能超过50"
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
        url: contextPath + "/liveRpt/insertLiveRpt",
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