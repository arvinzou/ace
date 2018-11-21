jQuery(function ($) {
    init_uploader({
        extensions: "jpg,gif,png,bmp",
        url: portalPath + '/files/uploadFile.do',
        target: "url",
        multipart_params: {}
    });
});

function init_uploader(config) {
    $("#uploader").pluploadQueue(
        {
            runtimes: 'html5,flash,silverlight,html4',
            chunk_size: '10mb',
            unique_names: true,
            multipart_params: config.multipart_params,
            filters: {
                max_file_size: '10mb',
                mime_types: [
                    {
                        title: "Image files",
                        extensions: config.extensions
                    }]
            },
            resize: {
                width: 600,
                height: 600,
                quality: 90
            },
            url: config.url
        });
    var uploader = $('#uploader').pluploadQueue();
    uploader.bind("UploadComplete", function () {

    });

    uploader.bind('FilesAdded', function (uploader, file) {
        // al ert('只能上传1张图片，请注意选择图片！');
        if (uploader.files.length > 1) { // 最多上传1张图
            //超过1张部分消除。
            uploader.splice(1, 999);
        }
    });

    uploader.bind("FileUploaded", function (uploader, file, responseObject) {
        console.log(responseObject.response);
        var rst = JSON.parse(responseObject.response);
        if (!rst.state) {
            alert(rst.errorMessage);
        } else {
            $('#modal-upload').modal('hide');
            $('input[name=' + config.target + ']').val(rst.value);
        }
    });
}

function reset_uploader(config) {
    var uploader = $('#uploader').pluploadQueue();
    uploader.splice();
    uploader.refresh();
    init_uploader(config);
}

function appendUploadBtn(id) {
    var html = new Array();
    html.push("<a id='btn-upload-add" + id + "' class='ace-icon glyphicon glyphicon-upload bigger-110' href='javascript:false'>上传</a>");
    html.push("<a id='btn-upload-view" + id + "' class='ace-icon fa fa-eye bigger-110' href='javascript:false'>浏览</a>");
    $("#" + id).after(html.join(''));
    $("#btn-upload-add" + id).on('click', function (e) {
        e.preventDefault();
        var config = {
            extensions: "jpg,gif,png,bmp,jpeg,mp4",
            url: portalPath + '/files/uploadFile.do',
            target: id,
            multipart_params: {}
        };
        reset_uploader(config);
        $("#tt").addClass('hide');
        $('#modal-upload').modal('show');


    });

    $("#btn-upload-view" + id).on('click', function (e) {
        e.preventDefault();
        $('#modal-upload-view').modal('show');
        var fileName = $('input[name=' + id + ']').val();
        if (!fileName || fileName == '') {
            return;
        }
        var src = fileName;
        if (src.indexOf("http") == -1) {
            src = fastdfs_server + src;
        }
        var img = new Image();
        $(img).attr("src", "");
        //图片加载加载后执行
        $(img).load(function () {
            //图片默认隐藏
            $(this).hide();
            //移除小动画
            $(".loading").removeClass("loading").append(this);
            //使用fadeIn特效
            $(this).fadeIn("slow");
        }).error(function () {
            //加载失败时的处理
        }).attr("src", src);//最后设置src


    });
}
