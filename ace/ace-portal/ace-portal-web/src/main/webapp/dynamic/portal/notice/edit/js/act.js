var loading = {};
var editor;
var noticeId=urlParams.did;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>编辑公告</span></li>");
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
    $('#fm-edit').ajaxForm({
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
    $.extend(params, {deadline:params['deadline']+":00"});
    $.extend(params, {top:'0'});
    startLoad();
    $.ajax({
        url: contextPath + "/notice/updateNotice.do",
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

function loadAttach(noticeId){
	$.ajax({
		type : "get",
		url : contextPath + "/attach/findAttachList.do",
		data:{noticeId:noticeId},
		success : function(rst, textStatus) {
			if(rst&&rst.state){
				var html=[];
				$.each(rst.value, function(n, file) {
					html.push('<div id="' + file.fileUrl + '"><a href="'+fastdfs_server+file.fileUrl+'" target="_blank">' + file.fileName + '</a> (' + parseInt(file.fileSize/1024) + 'kb) <a class=\'ace-icon glyphicon glyphicon-remove bigger-110\' href="javascript:deleteAttach(\''+file.attachId+'\')"></a><b></b></div>');
				});
				$('#filelist-history').html(html.join(''));
			}else{
				alert(rst.errorMessage);
			}
		}
	});
}
function deleteAttach(fileName){
	$.ajax({
		type : "get",
		url : contextPath + "/attach/deleteAttachByFileName.do",
		data:{fileName:fileName},
		success : function(rst, textStatus) {
			if(rst&&rst.state){
				loadAttach(noticeId);
			}else{
				alert(rst.errorMessage);
			}
		}
	});
}

function initForm() {
	startLoad();
    $.ajax({
        url: contextPath + "/notice/selectNoticeByPrimaryKey.do",
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
                 initEditor();
                editor.setValue(data['o'].content);
                initUpload();

            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
			 stopLoad();
            alert("对不起出错了！");
        }
    });
    loadAttach(urlParams.did);
}

var uploader;
function initUpload(){

        uploader = new plupload.Uploader({
		runtimes : 'html5',
		browse_button : 'pickfiles',
		container: document.getElementById('container'),
		url : contextPath+'/attach/uploadFile.do?noticeId='+noticeId+'&collectionName=notice',
		filters : {
			max_file_size : '100mb',
			mime_types: [
				{title : "Image files", extensions : "jpg,gif,png"},
	            {title : "Office files", extensions : "xls,xlsx,doc,docx,ppt,pdf"},
	            {title : "Artive files", extensions : "zip,rar,gzip"}
			]
		},

		init: {
			PostInit: function() {
				document.getElementById('filelist').innerHTML = '';
				document.getElementById('uploadfiles').onclick = function() {
					uploader.start();
					return false;
				};
			},

			FilesAdded: function(up, files) {
				plupload.each(files, function(file) {
					document.getElementById('filelist').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
				});
			},
			UploadProgress: function(up, file) {
				document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
			},

			Error: function(up, err) {
				document.getElementById('console').innerHTML += "\nError #" + err.code + ": " + err.message;
			}
		}
	});

 uploader.init();
	uploader.bind("FileUploaded", function (uploader,file,responseObject) {
			console.log(file.id);
			var id=file.id;
			var rst=JSON.parse(responseObject.response);
		if (!rst.state) {
			alert(rst.errorMessage);

		}else{
			var html=[];
			$.each(rst.value, function(n, file) {
				html.push('<div id="' + file.fileUrl + '"> <a href="'+fastdfs_server+file.fileUrl+'" target="_blank">' + file.fileName + '</a> (' + parseInt(file.fileSize/1024) + 'kb) <a class=\'ace-icon glyphicon glyphicon-remove bigger-110\' href="javascript:deleteAttach(\''+file.attachId+'\')"></a><b></b></div>');
			});
			document.getElementById('filelist-history').innerHTML+=html.join('');
			$('#'+id).html('');

		}
    });
}