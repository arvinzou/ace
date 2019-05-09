var loading = {};
var editor;
var id;
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
        errorPlacement: function (error, element) {
            $(element).closest("form").find(".error-" + element.attr("name")).append(error);
        },
        rules: {
            title: {
                required: true,
                maxlength: 100
            },
            category: {
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

}
/*保存表单**/
function save(params) {
    startLoad();
    $.ajax({
        url: contextPath + "/notice/updateNotice",
        type: "post",
        async: false,
        data: {
            jsons: JSON.stringify(params)
        },
        success: function (result) {
            stopLoad();
            alert(result.errorMessage);
            if (result.status == 0) {
                window.location.href = '../index.jsp?id=' + urlParams.id;
            }
        },
        error: function () {
            startLoad();
            alert("对不起出错了！");
        }
    });
}


function loadAttach(noticeId) {
    $.ajax({
        type: "get",
        url: portalPath + "/attach/findAttachList.do",
        data: {noticeId: noticeId},
        success: function (rst, textStatus) {
            if (rst && rst.state) {
                var html = [];
                $.each(rst.value, function (n, file) {
                    html.push('<div id="' + file.fileUrl + '"><a href="' + fastdfs_server + file.fileUrl + '" target="_blank">' + file.fileName + '</a> (' + parseInt(file.fileSize / 1024) + 'kb) <a class=\'ace-icon glyphicon glyphicon-remove bigger-110\' href="javascript:deleteAttach(\'' + file.attachId + '\')"></a><b></b></div>');
                });
                $('#filelist-history').html(html.join(''));
            } else {
                alert(rst.errorMessage);
            }
        }
    });
}
function deleteAttach(fileName) {
    $.ajax({
        type: "get",
        url: portalPath + "/attach/deleteAttachByFileName.do",
        data: {fileName: fileName},
        success: function (rst, textStatus) {
            if (rst && rst.state) {
                loadAttach(urlParams.did);
            } else {
                alert(rst.errorMessage);
            }
        }
    });
}

function initForm() {
    startLoad();
    $.ajax({
        url: contextPath + "/notice/selectNoticeByPrimaryKey",
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
                render('#fm-edit', data, 'tpl-fm-edit');
                initEditor();
                editor.setValue(data['o'].content);
                initUploader();
                loadAttach(urlParams.did);

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
