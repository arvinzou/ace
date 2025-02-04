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
            chunk_size: '1mb',
            unique_names: true,
            multipart_params: config.multipart_params,
            filters: {
                max_file_size: '10mb',
                mime_types: [{
                    title: "Image files",
                    extensions: config.extensions
                }]
            },

            // Resize images on clientside if we can
            resize: {
                width: 600,
                height: 600,
                quality: 90
            },

            url: config.url,
            flash_swf_url: portalPath
            + '/content/plupload-2.1.2/js/Moxie.swf',
            silverlight_xap_url: portalPath
            + '/content/plupload-2.1.2/js/Moxie.xap',
        });
    var uploader = $('#uploader').pluploadQueue();
    uploader.bind("UploadComplete", function () {

    });
    uploader.bind("FileUploaded", function (uploader, file, responseObject) {
        console.log(responseObject.response);
        var rst = JSON.parse(responseObject.response);
        if (!rst.state) {
            bootbox.dialog({
                title: '系统提示',
                message: rst.errorMessage,
                detail: rst.detail,
                buttons: {
                    "success": {
                        "label": "<i class='ace-icon fa fa-check'></i>确定",
                        "className": "btn-sm btn-success",
                        "callback": function () {
                            $("#dialog-message").dialog("close");
                        }
                    }
                }
            });

        } else {

            if (config.target) {
                $('input[name=' + config.target + ']').val(rst.value);
                if (config.target == 'grid') {
                    jQuery(cfgsub.grid_selector).jqGrid('setGridParam', {
                        page: 1,
                        postData: {}
                    }).trigger("reloadGrid");
                }
            } else {
                bootbox.dialog({
                    title: '系统提示',
                    message: rst.errorMessage,
                    detail: rst.detail,
                    buttons: {
                        "success": {
                            "label": "<i class='ace-icon fa fa-check'></i>确定",
                            "className": "btn-sm btn-success",
                            "callback": function () {
                                $("#dialog-message").dialog("close");
                            }
                        }
                    }
                });
                jQuery(cfg.grid_selector).jqGrid('setGridParam', {
                    page: 1,
                    postData: {
                        areaCode: ''
                    }
                }).trigger("reloadGrid");
            }
            $("#dialog-message").dialog("close");
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
    html
        .push("<a id='btn-upload-add" + id + "' class='ace-icon glyphicon glyphicon-upload bigger-110' href='javascript:false'>上传</a>");
    html
        .push("<a id='btn-upload-view" + id + "' class='ace-icon fa fa-eye bigger-110' href='javascript:false'>浏览</a>");
    $("#" + id).after(html.join(''));
    $("#btn-upload-add" + id)
        .on(
            'click',
            function (e) {
                e.preventDefault();
                var config = {
                    extensions: "jpg,gif,png,bmp",
                    url: portalPath + '/files/uploadFile.do',
                    target: id,
                    multipart_params: {}
                };
                reset_uploader(config);
                $("#tt").addClass('hide');
                var dialog = $("#dialog-message")
                    .removeClass('hide')
                    .dialog(
                        {
                            modal: true,
                            width: 750,
                            title: "<div class='widget-header widget-header-small'><div class='widget-header-pd' >文件上传</div></div>",
                            title_html: true,
                            buttons: [
                                {
                                    html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
                                    "class": "btn btn-info btn-xs",
                                    click: function () {
                                        $(this).dialog(
                                            "close");
                                    }
                                }
                            ]
                        });

            });

    $("#btn-upload-view" + id)
        .on(
            'click',
            function (e) {
                e.preventDefault();
                var dialog = $("#dialog-message-file")
                    .removeClass('hide')
                    .dialog(
                        {
                            modal: true,
                            width: 500,
                            title: "<div class='widget-header widget-header-small'><div class='widget-header-pd' >文件</div></div>",
                            title_html: true,
                            buttons: [{
                                html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
                                "class": "btn btn-info btn-xs",
                                click: function () {
                                    $(this).dialog("close");
                                }
                            }

                            ]
                        });
                var fileName = $('input[name=' + id + ']').val();
                if (!fileName || fileName == '') {
                    return;
                }
                var src = fastdfs_server + fileName;
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
                })
                //最后设置src
                    .attr("src", src);

            });
}

