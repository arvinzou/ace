var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>编辑节目管理</span></li>");
        initForm();
        initEvents();
        initUploadPlayUrl();
        initUploadPrePlayUrl();
        initUploadPreImgUrl();
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
//   initUpload();
}

function initEvents() {
    /*表单验证*/
    $("#fm-edit").validate({
        onfocusout: function (element) {
            $(element).valid();
        },
        rules: {
            name: {required: true, maxlength: 50}
        },
        messages: {
            name: {
                required: "请输入节目名称",
                maxlength: "节目名称字符长度不能超过50"
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
//coverUrl: $('#coverUrl').attr("src")
            });
            console.log(params);
            save(params);
            return false;
        }
    });
}

/*保存表单**/
function save(params) {
    $.extend(params, {});
    startLoad();
    $.ajax({
        url: contextPath + "/animaRes/updateAnimaRes",
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
        url: contextPath + "/animaRes/selectAnimaResByPrimaryKey",
        type: "post",
        async: false,
        data: {id: urlParams.did},
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                data['dict'] = staticDictObject;
                render('#fm-edit', data, 'tpl-fm');
                initPage();
//富文本填值
//editor.setValue(data['o'].summary);
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

function checkNumber(obj){
    var value = obj.value;
    var re=/^\d*$/;
    if(!re.test(value)){
        alert("输入数据格式只能是数字。");
        $(obj).val("");
       return false;
    }
}

/**
 * 播放文件上传
 */
function initUploadPlayUrl(){
    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'uploadPlayUrl',
        url: '/portal/files/uploadFile.do',
        file_data_name: 'file',
        multi_selection: false,
        filters: {
            max_file_size: '2048mb',
            mime_types: [

            ]
        },
        init: {
            FileFiltered: function (up, files) {
                up.start();
                return false;
            },
            UploadProgress: function(e, t) {
                var r = t.percent;
                $(".uploadPloadprogress").html("开始上传（" + r + "%）");
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
 * 预览播放文件上传
 */
function initUploadPrePlayUrl(){
    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'uploadPrePlayUrl',
        url: '/portal/files/uploadFile.do',
        file_data_name: 'file',
        multi_selection: false,
        filters: {
            max_file_size: '2048mb',
            mime_types: [

            ]
        },
        init: {
            FileFiltered: function (up, files) {
                up.start();
                return false;
            },
            UploadProgress: function(e, t) {
                var r = t.percent;
                $(".uploadPloadprogress").html("开始上传（" + r + "%）");
            },
            FileUploaded: function (uploader, file, responseObject) {
                var rst = JSON.parse(responseObject.response);
                $("#prePlayUrl").val(rst.value[0]);
            }
        }
    });
    uploader.init();
}

/**
 * 封面上传
 */
function initUploadPreImgUrl(){
    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'uploadPreImgUrl',
        url: '/portal/files/uploadFile.do',
        file_data_name: 'file',
        multi_selection: false,
        filters: {
            max_file_size: '2048mb',
            mime_types: [

            ]
        },
        init: {
            FileFiltered: function (up, files) {
                up.start();
                return false;
            },
            UploadProgress: function(e, t) {
                var r = t.percent;
                $(".uploadPloadprogress").html("开始上传（" + r + "%）");
            },
            FileUploaded: function (uploader, file, responseObject) {
                var rst = JSON.parse(responseObject.response);
                $("#preImgUrl").val(rst.value[0]);
            }
        }
    });
    uploader.init();
}