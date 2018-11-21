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
            chunk_size: '0',
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
            flash_swf_url: portalPath + '/content/plupload-2.1.2/js/Moxie.swf',
            silverlight_xap_url: portalPath + '/content/plupload-2.1.2/js/Moxie.xap',
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
            initMedia(config.trafficId);
            if (config.target) {
                $('input[name=' + config.target + ']').val(rst.value);
                if (config.target == 'grid') {
                    jQuery(cfg.grid_selector).jqGrid('setGridParam', {
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
                                        $(this).dialog("close");
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
                            }]
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

function initMedia(id) {
    $.ajax({
        type: "post",
        url: contextPath + "/trafficSub/findTrafficSubList",
        data: {trafficId: id},
        success: function (rst, textStatus) {
            initPhotoDom(rst.rows, id);

        }
    });
}

function initPhotoDom(rst, trafficId) {
    var html = new Array();
    html.push('<ul class="ace-thumbnails clearfix">');
    $.each($(rst), function (i, o) {

        html.push('<li>');
        html.push('<a href="' + fastdfs_server + o.fileUrl + '" title="' + rsd(o.category, '124') + '" data-rel="colorbox" class="cboxElement">');
        if (o.category == 5) {
            html.push('<video alt="60x60" class="photo" src="' + fastdfs_server + o.fileUrl + '"></video>')
        } else {
            html.push('<img alt="60x60" class="photo" src="' + fastdfs_server + o.fileUrl + '">');
        }
        html.push('</a>');
        html.push('<div class="tools tools-bottom">');
        html.push('<a href="javascript:delPhoto(\'' + o.id + '\',\'' + o.trafficId + '\')">');
        html.push('<i class="ace-icon fa fa-times red"></i>');
        html.push('</a>');
        html.push('</div>');
        html.push('</li>');
    });

    html.push('<li>');

    html.push('<a href="javascript:false"><img style="padding:20px" alt="60x60" id="btn-image-upload" class="photo" src="' + portalPath + '/content/common/image/add.png"></a>');

    html.push('</li>');
    html.push('</ul>');

    $("#custom-dia").empty();
    $("#custom-dia").html(html.join(""));

    var $overflow = '';
    var colorbox_params = {
        rel: 'colorbox',
        reposition: true,
        scalePhotos: true,
        scrolling: false,
        previous: '<i class="ace-icon fa fa-arrow-left"></i>',
        next: '<i class="ace-icon fa fa-arrow-right"></i>',
        close: '&times;',
        current: '{current} of {total}',
        maxWidth: '100%',
        maxHeight: '100%',
        onOpen: function () {
            $overflow = document.body.style.overflow;
            document.body.style.overflow = 'hidden';
        },
        onClosed: function () {
            document.body.style.overflow = $overflow;
        },
        onComplete: function () {
            $.colorbox.resize();
        }
    };

    $('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
    $('#btn-image-upload').on('click', function () {
        var config = {
            extensions: "jpg,gif,png,bmp,jpeg,mp4",
            url: contextPath + '/trafficSub/uploadFile',
            target: "grid",
            multipart_params: {
                id: trafficId
            },
            trafficId: trafficId
        };
        reset_uploader(config);
        $("#tt").addClass('hide');
        var dialog = $("#dialog-message").removeClass('hide').dialog({
            modal: true,
            width: 750,
            title: "<div class='widget-header widget-header-small'><div class='widget-header-pd' >图片上传</div></div>",
            title_html: true,
            buttons: [{
                html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
                "class": "btn btn-info btn-xs",
                click: function () {
                    $(this).dialog("close");
                }
            }]
        });
    });
}

function delPhoto(id, trafficId) {
    $.ajax({
        type: "post",
        url: contextPath + "/trafficSub/deleteTrafficSubByTrafficSubIdSub",
        data: {id: id},
        success: function (rst, textStatus) {
            initMedia(trafficId);
        }
    });
}

var liImg = '<li>' +
    '	<a href="[fileUrl]" title="[plateNo]" data-rel="colorbox" class="cboxElement">' +
    '	<img alt="60x60" class="photo" src="[fileUrl]">' +
    '	</a>' +
    '	<div class="tools tools-bottom">' +
    '		<a href="javascript:delPhoto(\'[id]\',\'[trafficId]\')">' +
    '			<i class="ace-icon fa fa-times red"></i>' +
    '		</a>' +
    '	</div>' +
    '</li>';

var liVdo = '<li>' +
    '	<a href="[fileUrl]" title="[plateNo]" data-rel="colorbox" class="cboxElement">' +
    '	<video alt="60x60" class="photo" src="[fileUrl]"></video>' +
    '	</a>' +
    '	<div class="tools tools-bottom">' +
    '		<a href="javascript:delPhoto(\'[id]\',\'[trafficId]\')">' +
    '			<i class="ace-icon fa fa-times red"></i>' +
    '		</a>' +
    '	</div>' +
    '</li>';