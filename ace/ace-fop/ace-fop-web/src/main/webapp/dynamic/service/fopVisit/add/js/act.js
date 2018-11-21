var loading = {};
var editor;
var noticeId=guid();
function guid() {
    function S4() {
       return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
    }
    return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
}
window.onload = function (){
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>创建企业走访</span></li>");
        initPage();
        initEvents();
        	$("input[name=visitDate]").datetimepicker({
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
        	$('input[name=visitDate]').focus(function(){
        　　　　　　$(this).blur();//不可输入状态
        　　　　})

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
}
function initEvents(){
     $("input[name=id]").val(noticeId);
    /*表单验证*/
    $("#fm-add").validate({
        onfocusout: function(element) { $(element).valid(); },
        rules: {
             companyId: {required: true,maxlength:50},
             title: {required: true,maxlength:200},
             content: {required: true}
         },
        messages: {
                companyId: {
                    required: "请输入走访企业名称",
                    maxlength:"走访企业名称字符长度不能超过50"
                }, title: {
                    required: "请输入标题",
                    maxlength:"标题字符长度不能超过200"
                },content: {
                    required: "请输入走访内容"
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
time: new Date()
            });
            $.extend(params, {visitDate:params['visitDate']+":00"});
            console.log(params);
            save(params);
            return false;
        }
    });
     uploader.init();
}
/*保存表单**/
function save(params) {
    $.extend(params, {
    });
    startLoad();
    $.ajax({
        url: contextPath + "/fopVisit/insertFopVisit",
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



function loadAttach(noticeId){
	$.ajax({
		type : "get",
		url : portalPath + "/attach/findAttachList.do",
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
		url : portalPath + "/attach/deleteAttachByFileName.do",
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