var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>编辑直播</span></li>");
        initForm();
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
    $("#fm-edit").validate({
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
    $.extend(params, {id:urlParams.did});
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
                window.location.href = '../index.jsp?id='+urlParams.id;
            }
        },
        error: function () {
			 stopLoad();
            alert("对不起出错了！");
        }
    });
}

function initForm() {
	startLoad();
    $.ajax({
        url: contextPath + "/live/selectLiveByPrimaryKey",
        type: "post",
        async: false,
        data: {
            id: urlParams.did
        },
        success: function (result) {
			 stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                render('#fm-edit', data, 'tpl-fm');
                initPage();
                //editor.setValue(data['o'].content);
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
