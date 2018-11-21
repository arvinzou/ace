var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>创建诉求</span></li>");
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
	render($("select[name=tplCode]"), staticDictObject['136'], "tpl-option");
    render($("select[name=answerTplCode]"), staticDictObject['136'], "tpl-option");
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
                maxlength: 50
            },
			cover: {
				required: true
			},
			qrcoteUrl: {
				required: true
			},
            remark: {
                required: true,
                maxlength: 500
            },
			openId: {
				required: true
			},
			tplCode: {
				required: true
			},
			answerTplCode: {
				required: true
			}
        },
        messages: {
            name: {
                required: "请输入名称",
                maxlength: "名称字符长度不能超过30"
            },
			cover: {
				required: "请上传封面图"
			},
			qrcoteUrl: {
				required: "请上传微信二维码"
			},
            remark: {
                required: "请输入摘要",
                maxlength: "摘要字符长度不能超过500"
            },
			openId: {
				required: "请选择处理人"
			},
			tplCode: {
				required: "请选择微信处理通知模板"
			},
			answerTplCode: {
				required: "请选择微信答复通知模板"
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
    startLoad();
    $.ajax({
        url: contextPath + "/appeal/insertAppeal.do",
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
