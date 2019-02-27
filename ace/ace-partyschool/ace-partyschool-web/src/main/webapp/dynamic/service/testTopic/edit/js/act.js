var loading = {};
var editor;
window.onload = function (){
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>编辑试题管理</span></li>");
       initForm();
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
//   initUpload();
}
function initEvents(){
    /*表单验证*/
    $("#fm-edit").validate({
        onfocusout: function(element) { $(element).valid(); },
        rules: {
                                         testId: {required: true,maxlength:50},                             content: {required: true,maxlength:200},                             analysis: {required: true,maxlength:200}                                 },
        messages: {
                                             testId: {
                        required: "请输入测试主键",
                        maxlength:"测试主键字符长度不能超过50"
                    },                                 content: {
                        required: "请输入题目内容",
                        maxlength:"题目内容字符长度不能超过200"
                    },                                 analysis: {
                        required: "请输入测试分析",
                        maxlength:"测试分析字符长度不能超过200"
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
time: new Date(),
//coverUrl: $('#coverUrl').attr("src")
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
        url: contextPath + "/topic/updateTopic",
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

function initForm(){
 startLoad();
    $.ajax({
        url: contextPath + "/topic/selectTopicByPrimaryKey",
        type:"post",
        async:false,
data:{ id: urlParams.did },
        success:function(result){
        stopLoad();
            if(result.status == 0) {
               var data={};
               data['o']=result.value;
render('#fm-edit',data,'tpl-fm');
               initPage();
//富文本填值
//editor.setValue(data['o'].summary);
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
        stopLoad();
            alert("对不起出错了！");
        }
    });
}
