jQuery(function ($) {
    console.log("----------------------------inntel");
    init_uploader({
        extensions: "jpg,png,bmp,doc,xlsx,xls,docx,pdf,pptx,ppt",
        url: portalPath + '/files/uploadFile.do',
        target: "url",
        multipart_params: {}
    });
});

/**
 * 初始化。
 * @param config
 */
function init_uploader(config) {
    console.log("===========init_uploader");
    $("#uploader").pluploadQueue(
        {
            //指定上传方式。
            runtimes: 'html5,flash,silverlight,html4',
            //分片上传文件时，每片文件被切割成的大小
            chunk_size: '1mb',
            //当值为true时会为每个上传的文件生成一个唯一的文件名，并作为额外的参数post到服务器端，参数明为name,值为生成的文件名。
            unique_names: true,
            //上传时的附加参数，以json对的形式传入
            multipart_params: config.multipart_params,
            //可以使用该参数来限制上传文件的类型
            filters: {
                //允许文件最大上传距离
                max_file_size: '10mb',
                //mime_types：该对象有title和extensions两个属性，
                mime_types: [{
                    //title为该过滤器的名称，
                    title: "files",
                    //extensions为文件扩展名，有多个时用逗号隔开。该属性默认为一个空数组，即不做限制。
                    extensions: config.extensions,
                    //不允许文件重复
                    prevent_duplicates:true
                }]
            },

            // Resize images on clientside if we can
            //可以使用该参数对将要上传的图片进行压缩
            resize: {
                width: 600,
                height: 600,
                //压缩后图片的质量
                quality: 90
            },

            //服务器端接收和处理上传文件的脚本地址
            url: config.url,
            //用flash上传时组件的url地址
            flash_swf_url: portalPath + '/content/plupload-2.1.2/js/Moxie.swf',
            //用silverlight上传时组件的url地址
            silverlight_xap_url: portalPath + '/content/plupload-2.1.2/js/Moxie.xap',
            //获取文件信息
            file_data_name：
        });
    var uploader = $('#uploader').pluploadQueue();
    uploader.bind("UploadComplete", function () {});
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

function reset_uploader(config){
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
    //点击上传按键
    $("#btn-upload-add" + id).on('click', function (e) {
        console.log("======================================file 上传");
        e.preventDefault();
        var config = {
            extensions: "jpg,png,bmp,doc,xlsx,xls,docx,pdf,pptx,ppt",
            url: portalPath + '/files/uploadFile.do',
            target: id,
            multipart_params: {}
        };
        reset_uploader(config);
        $("#tt").addClass('hide');
        var dialog = $("#dialog-message").removeClass('hide').dialog(
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

    $("#btn-upload-view" + id).on('click', function (e) {
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

