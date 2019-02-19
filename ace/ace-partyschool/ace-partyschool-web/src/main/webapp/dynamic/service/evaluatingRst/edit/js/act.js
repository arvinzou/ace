var loading = {};
var editor;
window.onload = function (){
jQuery(function ($) {
$(".breadcrumb").append("<li><span>编辑课程表管理</span></li>");
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
            classesId: {required: true,maxlength:50},            courseIndex: {required: true,maxlength:20},            teacherId: {required: true,maxlength:50},            courseId: {required: true,maxlength:50}    },
messages: {
            classesId: {
    required: "请输入班次",
    maxlength:"班次字符长度不能超过50"
    },            courseIndex: {
    required: "请输入课节am:上午 pm:下午",
    maxlength:"课节am:上午 pm:下午字符长度不能超过20"
    },            teacherId: {
    required: "请输入讲师",
    maxlength:"讲师字符长度不能超过50"
    },            courseId: {
    required: "请输入课程",
    maxlength:"课程字符长度不能超过50"
    }    }
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
url: contextPath + "/classSchedule/updateClassSchedule",
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
url: contextPath + "/classSchedule/selectClassScheduleByPrimaryKey",
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
