var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>创建直播</span></li>");
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
    //initEditor();
    initUpload();
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
	$("input[name=startTime]").datetimepicker({
	　　　　　　format: 'yyyy-mm-dd hh:mm',
	　　　　　　language: 'zh-CN',
	　　　　　　weekStart: 1,
	　　　　　　todayBtn: 1,//显示‘今日’按钮
	　　　　　　autoclose: 1,
	　　　　　　todayHighlight: 1,
	　　　　　　startView: 2,
	　　　　　　minView: 'hour',  //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
	　　　　　　clearBtn:true,//清除按钮
	　　　　　　forceParse: 0
	　　	});
	$('input[name=startTime]').focus(function(){
　　　　　　$(this).blur();//不可输入状态
　　　　})
}
/*保存表单**/
function save(params) {
    $.extend(params, {startTime:params['startTime']+":00"});
    startLoad();
    $.ajax({
        url: contextPath + "/live/insertLive",
        type: "post",
        async: false,
        data: {
            jsons: JSON.stringify(params)
        },
        success: function (result) {
            stopLoad();
            alert(result.errorMessage);
            if (result.status == 0) {
                window.location.href = 'index.jsp?id='+urlParams.id+"&did="+params.rid;
            }
        },
        error: function () {
			 startLoad();
            alert("对不起出错了！");
        }
    });
}
