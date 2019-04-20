var loading = {};
var editor;
window.onload = function (){
jQuery(function ($) {
$(".breadcrumb").append("
<li><span>编辑节点能耗信息</span></li>");
initForm();
initEvents();
initJuicerMethod();
});
}

//juicer自定义函数
function initJuicerMethod() {
juicer.register('rsd', rsd);
juicer.register('parseStatus', parseStatus);
}

/**
* 状态解析
*/
function parseStatus(status) {
switch (status) {
case '0':
return "未读";
case '1':
return "已读";
default:
return "未读";
}
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
            nodeCode: {required: true,maxlength:50},            itemDecipt: {required: true,maxlength:100},            itemKey: {required: true,maxlength:50},            itemValue: {required: true,maxlength:200}    },
messages: {
            nodeCode: {
    required: "请输入节点编码",
    maxlength:"节点编码字符长度不能超过50"
    },            itemDecipt: {
    required: "请输入字段描述",
    maxlength:"字段描述字符长度不能超过100"
    },            itemKey: {
    required: "请输入字段键",
    maxlength:"字段键字符长度不能超过50"
    },            itemValue: {
    required: "请输入字段值",
    maxlength:"字段值字符长度不能超过200"
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
url: contextPath + "/ammeterNode/updateAmmeterNode",
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
url: contextPath + "/ammeterNode/selectAmmeterNodeByPrimaryKey",
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
