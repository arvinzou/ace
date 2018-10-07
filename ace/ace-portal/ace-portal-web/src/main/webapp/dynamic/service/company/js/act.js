var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        //$(".breadcrumb").append("<li><span>编辑直播</span></li>");
        initForm();
        initEvents();
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
    		departmentName: {
    			required: true,
    			maxlength: 100
    		},
    		shortName: {
    			required: true
    		},
    		logo: {
    			required: true,
    			maxlength: 500
    		},
    		watermark1: {
    			required: true
    		},
            watermark2: {
                required: true
            }
    	},
    	messages: {
    		departmentName: {
    			required: "请输入机构全称",
    			maxlength: "字符长度不能超过20"
    		},
    		shortName: {
    			required: "请输入机构简称",
                maxlength: "字符长度不能超过20"
    		},
    		logo: {
    			required: "请上传LOGO图片"
    		},
            watermark1: {
                required: "请上传图片水印1"
            },
                         watermark2: {
                             required: "请上传图片水印2"
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
    $.extend(params, {id: userProp.corpId});
    startLoad();
    $.ajax({
        url: contextPath + "/department/updateDepartment.do",
        type: "post",
        async: false,
        data: {
            jsons: JSON.stringify(params)
        },
        success: function (result) {
            stopLoad();
            alert(result.errorMessage);
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
        url: contextPath + "/department/selectDepartmentByPrimaryKey.do",
        type: "post",
        async: false,
        data: {
            departmentId: userProp.corpId
        },
        success: function (result) {
			 stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                render('#fm-edit', data, 'tpl-fm');
                initPage();
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
