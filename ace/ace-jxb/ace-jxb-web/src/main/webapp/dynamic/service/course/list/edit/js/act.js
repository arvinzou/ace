var loading = {};


function App() {


}

function initEditor() {
    var editor = new Simditor({
        textarea: $('textarea[name=introduction]'),
        toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol',
            'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'
        ],
        upload: {
            url: portalPath + '/files/uploadImage.do', //文件上传的接口地址
            params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
            fileKey: 'file', //服务器端获取文件数据的参数名
            connectionCount: 3,
            leaveConfirm: '正在上传文件'
        }
    });
}
function save(params) {
    $.extend(params, {
        courseId: urlParams.courseId,
		partId: urlParams.partId,
		id:urlParams.did
    });
    startLoad();
    $.ajax({
        url: contextPath + "/courseSource/updateCourseSource",
        type: "post",
        async: false,
        data: {
            jsons: JSON.stringify(params)
        },
        success: function (result) {
            stopLoad();
			alert(result.errorMessage);
			if(result.status==0){
				window.location.href=contextPath+"/dynamic/service/course/list/index.jsp?did="+urlParams.courseId+"&id="+urlParams.id
			}
        },
        error: function () {
            alert("系统服务内部异常！");
        }
    });
}




function renderPage(dom, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
	console.log(tpl);
    var html = juicer(tpl, {
        data: data,
    });
	console.log(html);
    $(dom).html(html);
}

function initPage() {
    $("#fm-add").validate({
            onfocusout: function(element) { $(element).valid(); },
			rules: {
				name: {
					required: true,
					minlength: 4,
					maxlength:28
				},
				mediUrl: {
					required: true,
					minlength: 15,
					maxlength:200
				},
				duration: {
					required: true,
					minlength: 2,
					maxlength:10
				}
			},
			messages: {
				name: {
					required: "请输入课件名称",
					minlength:"课件名称至少四个字符",
					maxlength:"课件名称长度不能超过28"
				},
				mediUrl: {
					required: "音频地址不能为空",
					minlength:"音频地址至少15个字符",
					maxlength:"音频地址长度不能超过200"
				},
				duration: {
					required: "请输入课件时长",
					minlength:"课件时长至少2个字符",
					maxlength:"课件时长长度不能超过10"
				}
			}
		});

    $('#fm-add').ajaxForm({
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
    $('input[name=name]').maxlength({
        alwaysShow: true
    });
    $('input[name=duation]').maxlength({alwaysShow: true});
    initEditor();
}


function initForm(){
	startLoad();
    $.ajax({
        url: contextPath + "/courseSource/selectCourseSourceByPrimaryKey",
        type:"post",
        async:false,
        data:{
            id: urlParams.did
        },
        success:function(result){
			stopLoad();
            if(result.status == 0) {
				renderPage($(".form-panel"),result.value, 'tpl-fm');
				initPage();
                initUpload();
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
jQuery(function ($) {
	initForm();
	 $(".breadcrumb").append("<li>编辑课件</li>");
        initUpload();

});

function initUpload(){
    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'uploadSource',
        url: '/portal/files/uploadFile.do',
        file_data_name: 'file',
        multi_selection: false,
        filters: {
            max_file_size: '2048mb',
            mime_types: [
                {title: "audio files", extensions: "mp3"}
            ]
        },
        init: {
            FileFiltered: function (up, files) {
                /*showUploadText('.viewPicture2 img', '.uploadText2');*/
                up.start();
                return false;
            },
            UploadProgress: function(e, t) {
                var r = t.percent;
                $(".uploadPloadprogress").html("开始上传（" + r + "%）");
            },
            FileUploaded: function (uploader, file, responseObject) {
                var rst = JSON.parse(responseObject.response);
                /* viewCover(rst.value[0], '.pictureContainer2','.viewPicture2 img','.uploadText2');*/
                $("#mediUrl").val(rst.value[0]);
                coverImg = rst.value[0];
            }
        }
    });
    uploader.init();
}