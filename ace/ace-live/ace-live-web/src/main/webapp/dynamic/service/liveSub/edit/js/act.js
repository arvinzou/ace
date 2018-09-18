var loading = {};
var editor;
window.onload = function (){
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>编辑直播</span></li>");
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
                                         name: {required: true,maxlength:100},                             category: {required: true,maxlength:20},                             deptId: {required: true,maxlength:100},                             remark: {required: true,maxlength:500},                             content: {required: true,maxlength:2147483647},                             imageSrc: {required: true,maxlength:200},                             auditStatus: {required: true,maxlength:1}                                 },
        messages: {
                                             name: {
                        required: "请输入名称",
                        maxlength:"名称字符长度不能超过100"
                    },                                 category: {
                        required: "请输入直播类型",
                        maxlength:"直播类型字符长度不能超过20"
                    },                                 deptId: {
                        required: "请输入组织单位",
                        maxlength:"组织单位字符长度不能超过100"
                    },                                 remark: {
                        required: "请输入摘要",
                        maxlength:"摘要字符长度不能超过500"
                    },                                 content: {
                        required: "请输入活动介绍",
                        maxlength:"活动介绍字符长度不能超过2147483647"
                    },                                 imageSrc: {
                        required: "请输入封面",
                        maxlength:"封面字符长度不能超过200"
                    },                                 auditStatus: {
                        required: "请输入审核状态1待审2通过3驳回",
                        maxlength:"审核状态1待审2通过3驳回字符长度不能超过1"
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
        url: contextPath + "/live/updateLive",
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
        url: contextPath + "/live/selectLiveByPrimaryKey",
        type:"post",
        async:false,
data:{ id: urlParams.did },
        success:function(result){
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
            alert("对不起出错了！");
        }
    });
}
