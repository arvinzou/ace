var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>创建公告</span></li>");
        initPage();
        initEvents();

    });
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
    initUpload();
}

function initEvents() {
    /*表单验证*/
    $("input[name=noticeId]").val(guid());
    $("#fm-add").validate({
        onfocusout: function (element) {
            $(element).valid();
        },
		errorPlacement: function(error, element) {
             $(element).closest( "form" ).find( ".error-" + element.attr( "name" )).append( error );
        },
        rules: {
            title: {
                required: true,
                maxlength: 100
            },
            category: {
                required: true
            },
            deadline: {
                required: true
            },
            content: {
                required: true
            }
        },
        messages: {
            title: {
                required: "请输入标题",
                maxlength: "标题字符长度不能超过100"
            },
            category: {
                required: "请选择公告类型"
            },
            deadline: {
                required: "请输入有效日期"
            },
            content: {
                required: "请输入内容"
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
                status: '1'
            });
            console.log(params);
            save(params);
            return false;
        }
    });
	$("input[name=deadline]").datetimepicker({
	　　　　　　format: 'yyyy-mm-dd hh:ii',
	　　　　　　language: 'zh-CN',
	　　　　　　weekStart: 1,
	　　　　　　todayBtn: 1,//显示‘今日’按钮
	　　　　　　autoclose: 1,
	　　　　　　todayHighlight: 1,
	　　　　　　startView: 2,
	　　　　　　minView: '0',  //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
	　　　　　　clearBtn:true,//清除按钮
	　　　　　　forceParse: 0
	　　	});
	$('input[name=deadline]').focus(function(){
　　　　　　$(this).blur();//不可输入状态
　　　　})
}
/*保存表单**/
function save(params) {
    $.extend(params, {startTime:params['startTime']+":00"});
    startLoad();
    $.ajax({
        url: contextPath + "/notice/insertNotice",
        type: "post",
        async: false,
        data: {
            jsons: JSON.stringify(params)
        },
        success: function (result) {
            stopLoad();
            alert(result.errorMessage);
            if (result.status == 0) {
                window.location.href = '../index.jsp?id='+urlParams.id;
            }
        },
        error: function () {
			 startLoad();
            alert("对不起出错了！");
        }
    });
}
function guid() {
    function S4() {
       return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
    }
    return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
}