var loading = {};
var editor;
window.onload = function (){
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>议题编辑</span></li>");
       initForm();
       initEvents();
    });
}
function App() {
    console.log("=============================App Start==============================");
}
function initEditor() {
        editor = new Simditor({
        textarea: $('textarea[name=content]'),
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
}
function initEvents(){
    /*表单验证*/
    $("#fm-edit").validate({
        onfocusout: function(element) { $(element).valid(); },
        rules: {
            title: {required: true,maxlength:50}                                 },
        messages: {
            title: {
                        required: "请输入议题标题",
                        maxlength:"议题标题字符长度不能超过50"
                    }                                 }
    });
     /*监听表单提交*/
    $('#fm-edit').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var params = {};
            $.each(formData, function (n, obj) {
                params[obj.name] = obj.value;
            });
            $.extend(params, {
                time: new Date()
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
        url: contextPath + "/subject/updateSubject",
        type: "post",
        async: false,
        data: {
            jsons: JSON.stringify(params)
        },
        success: function (result) {
            stopLoad();
			alert(result.errorMessage);
            if (result.status == 0) {
                window.location.href ='../index.jsp';
            }
        },
        error: function () {
            alert("对不起出错了！");
        }
    });
}

function initForm(){
    $.ajax({
        url: contextPath + "/subject/selectSubjectByPrimaryKey",
        type:"post",
        async:false,
        data:{
            id: urlParams.did
        },
        success:function(result){
            if(result.status == 0) {
               var data={};
               data['o']=result.value;
               render($("#fm-edit"),data,'tpl-fm');
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("对不起出错了！");
        }
    });
    initPage();
}