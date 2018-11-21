var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>编辑诉求</span></li>");
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
        url: contextPath + "/appeal/updateAppeal.do",
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
        url: contextPath + "/appeal/selectAppealByPrimaryKey.do",
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
				data['dict136']=staticDictObject['136'];
                render('#fm-edit', data, 'tpl-fm');
                initPage();
				$("select[name=openId]").combogrid({
					panelWidth: 500,
					idField: 'openid',
					textField: 'nickname',
					url: portalPath+'/system/selectUserinfo.do?id='+data.o.openId,
					mode: 'remote',
					fitColumns: true,
					method: 'get',
					value:data.o.openId,
					columns: [
						[{
							field: 'openid',
							title: 'OPENID',
							width: 200
						}, {
							field: 'nickname',
							title: '昵称',
							width: 100
						}]
					],
					fitColumns: true,
					label: '',
					labelPosition: 'top'
					
				});
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
