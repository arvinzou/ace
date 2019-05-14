var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>编辑弱电场景管理</span></li>");
        initForm();
        initEvents();
        initJuicerMethod();
    });
}

//juicer自定义函数
function initJuicerMethod() {
    juicer.register('rsd', rsd);
    juicer.register('parseStatus', parseStatus);
}

/**
 * 状态解析
 */
function parseStatus(status) {
    switch (status) {
        case '0':
            return "未读";
        case '1':
            return "已读";
        default:
            return "未读";
    }
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

}

function initEvents() {
    /*表单验证*/
    $("#fm-edit").validate({
        onfocusout: function (element) {
            $(element).valid();
        },
        rules: {
            sceneNum: {required: true, maxlength: 50}
        },
        messages: {
            sceneNum: {
                required: "请输入场景编号",
                maxlength: "场景编号字符长度不能超过50"
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

            });
            console.log(params);
            save(params);
            return false;
        }
    });
    //
    initUploadViewUrl();
    //
    initUploadPlayUrl();
}


/**
 * 播放文件上传
 */
function initUploadPlayUrl() {
    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'uploadPlayUrl',
        url: '/portal/files/uploadFile.do',
        file_data_name: 'file',
        multi_selection: false,
        filters: {
            max_file_size: '2048mb',
            mime_types: [{
                title: "video file",
                extensions: "avi,mp4"
            }]
        },
        init: {
            FileFiltered: function (up, files) {
                up.start();
                return false;
            },
            UploadProgress: function (e, t) {
                var r = t.percent;
                $(".help-block .playUrl").html("开始上传（" + r + "%）");
            },
            FileUploaded: function (uploader, file, responseObject) {
                var rst = JSON.parse(responseObject.response);
                $("#playUrl").val(rst.value[0]);
            }
        }
    });
    uploader.init();
}

/**
 * 预览图片上传
 */
function initUploadViewUrl() {
    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'uploadViewUrl',
        url: '/portal/files/uploadFile.do',
        file_data_name: 'file',
        multi_selection: false,
        filters: {
            max_file_size: '2048mb',
            mime_types: [{
                title: "image file",
                extensions: "jpg,gif,jpeg,png"
            }]
        },
        init: {
            FileFiltered: function (up, files) {
                up.start();
                return false;
            },
            UploadProgress: function (e, t) {
                var r = t.percent;
                $(".help-block .viewUrl").html("开始上传（" + r + "%）");
            },
            FileUploaded: function (uploader, file, responseObject) {
                var rst = JSON.parse(responseObject.response);
                $("#viewUrl").val(rst.value[0]);
            }
        }
    });
    uploader.init();
}

/*保存表单**/
function save(params) {
    $.extend(params, {});
    startLoad();
    $.ajax({
        url: contextPath + "/leScene/updateLeScene",
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
            stopLoad();
            alert("对不起出错了！");
        }
    });
}

function initForm() {
    startLoad();
    $.ajax({
        url: contextPath + "/leScene/selectLeSceneByPrimaryKey",
        type: "post",
        async: false,
        data: {id: urlParams.did},
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
