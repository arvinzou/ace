var loading = {};
var editor;
window.onload = function (){
jQuery(function ($) {
$(".breadcrumb").append("<li><span>创建建筑物管理</span></li>");
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
initForm();
}
function initEvents(){
/*表单验证*/
$("#fm-add").validate({
    onfocusout: function(element) { $(element).valid(); },
    rules: {
            code: {required: true,maxlength:50},
            name: {required: true,maxlength:50},
            address: {required: true,maxlength:200},
            subareaCode: {required: true,maxlength:50},
            type: {required: true, maxlength:5}
            },
    messages: {
            code: {
                required: "请输入建筑编号",
                maxlength:"建筑编号字符长度不能超过50"
            },
            name: {
                required: "请输入建筑名称",
                maxlength:"建筑名称字符长度不能超过50"
             },
            address: {
                required: "请输入所在地",
                maxlength:"所在地字符长度不能超过200"
            },
            subareaCode: {
                required: "请输入分区编码",
                maxlength:"分区编码字符长度不能超过50"
            },
            type: {
                required: "请选择建筑类型",
                maxlength:"建筑类型字符长度不能超过5"
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
$.extend(params, {
});
startLoad();
$.ajax({
url: contextPath + "/topBuilding/insertTopBuilding",
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
    var data=staticDictObject['177'];
    var dataList = [];
    for(var i=0; i < data.length; i++){
        if(data[i].CODE != ""){
            dataList.push(data[i]);
        }
    }
    render('#type',dataList,'type-tpl');
    initSubAreaList();
}

function initSubAreaList(){
    startLoad();
    $.ajax({
        url: contextPath + "/topSubarea/findTopSubareaList",
        type: "post",
        async: false,
        data: {
            start: 0,
            limit: 999
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                render('#areaList', result.rows, 'area-tpl');
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
