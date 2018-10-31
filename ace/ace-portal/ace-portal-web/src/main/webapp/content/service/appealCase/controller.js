jQuery(function ($) {
    $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
        _title: function (title) {
            var $title = this.options.title || '&nbsp;'
            if (("title_html" in this.options)
                && this.options.title_html == true)
                title.html($title);
            else
                title.text($title);
        }
    }));
    $('#btn-search').on('click', function () {
        $('#fm-search').ajaxForm({
            beforeSubmit: function (formData, jqForm, options) {
                var params = {};
                $.each(formData, function (n, obj) {
                    params[obj.name] = obj.value;
                });
                $.extend(params, {
                    time: new Date(),
                    // corpId: "00010001"
                });
                // console.log(params);
                jQuery(cfg.grid_selector).jqGrid('setGridParam', {
                    page: 1,
                    postData: params
                }).trigger("reloadGrid");
                return false;
            }
        });
    });

    $('#btn-view-add').on(
        'click',
        function () {
            jQuery(cfg.grid_selector).jqGrid(
                'editGridRow',
                'new',
                {
                    closeAfterAdd: true,
                    recreateForm: true,
                    viewPagerButtons: false,
                    beforeShowForm: function (e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find(
                            '.ui-jqdialog-titlebar').wrapInner(
                            '<div class="widget-header" />')
                        style_edit_form(form);
                    }
                })
        });
    $('#btn-view-edit').on(
        'click',
        function () {
            //每次点击先清空其原有内容
            resetDialogFileDom();

            //
            var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
            if (!gr) {
                $.jgrid.info_dialog($.jgrid.nav.alertcap, $.jgrid.nav.alerttext)
                return;
            }
            var rowData = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
            if (rowData.status != "2") {
                alert("诉求未受理或已办结!")
                return;
            }
            cfg.appealCaseId = rowData.id;
            jQuery(cfg.grid_selector).jqGrid(
                'editGridRow', gr,
                {
                    closeAfterAdd: true,
                    recreateForm: true,
                    viewPagerButtons: true,
                    beforeShowForm: function (e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog')
                            .find('.ui-jqdialog-titlebar')
                            .wrapInner('<div class="widget-header" />');
                        style_edit_form(form);
                        //加载附件
                        $("#TblGrid_grid-table").after("<div id='filelist-history'></div>" +
                            "<div id='filelist'></div>" +
                            "<div id='container'>" +
                            "附件：<a id='pickfiles' href='javascript:;'>[添加附件]</a> " +
                            " <a id='uploadfiles' href='javascript:;'>[上传]</a>" +
                            "</div>");
                        loadAttach(rowData.id);
                        initUploader("pickfiles", rowData.id);

                        //添加资源暂时图标
                        appendMediaIcon("mediUrl", rowData.mediType);
                    }
                });
        });
    $('#btn-view-del').on(
        'click',
        function () {

            var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
                'selrow');
            if (!gr) {
                $.jgrid.info_dialog($.jgrid.nav.alertcap,
                    $.jgrid.nav.alerttext);
                return;
            }
            jQuery(cfg.grid_selector).jqGrid(
                'delGridRow',
                gr,
                {
                    beforeShowForm: function (e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find(
                            '.ui-jqdialog-titlebar').wrapInner(
                            '<div class="widget-header" />')
                        style_edit_form(form);
                    }
                })
        });
});

function preview(id, title) {
    var dialog = $("#dialog-message-view").removeClass('hide').dialog({
        modal: false,
        width: 800,
        title: "<div class='widget-header widget-header-small'><div class='widget-header-pd'>" + title + "</div></div>",
        title_html: true,
        buttons: [
            {
                html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
                "class": "btn btn-info btn-xs",
                click: function () {
                    $(this).dialog("close");
                }
            },
            {
                html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
                "class": "btn btn-xs",
                click: function () {
                    $(this).dialog("close");
                }
            }
        ]
    });
    $(dialog).parent().css("top", "1px");
    $(dialog).css("max-height", window.innerHeight - layoutTopHeight + 50);
    loadView(id);
}
function loadView(id) {
    $.ajax({
        type: "post",
        url: cfg.view_load_data_url,
        data: {
            id: id
        },
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
            $.each(rst.value, function (key, value) {
                if (key == 'category') {
                    value = rsd(value, '83');
                }
                if (key == 'status') {
                    value == "1" ? "正常" : "关闭";
                }
                if (key.indexOf('Date') != -1 || key.indexOf('time') != -1 || key.indexOf('Time') != -1 || key.indexOf('birthday') != -1) {
                    value = Common.DateFormatter(value);
                }
                $("#dialog-message-view").find('#' + key).html(value);

                if (key == 'list') {
                    //上传附件显示
                    renderImage(value);
                }
            });
        },
        error: function () {
            alert("加载错误！");
        }
    });
}

//受理诉求
function acceptAppealCase(id, title) {
    var dialog = $("#dialog-message-accept").removeClass('hide').dialog({
        modal: true,
        width: 400,
        title: "<div class='widget-header widget-header-small'><div class='widget-header-pd'>" + title + "</div></div>",
        title_html: true,
        buttons: [
            {
                html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
                "class": "btn btn-info btn-xs",
                click: function () {
                    var answer_dept = $('#answer_dept').val();
                    var operator = $('#operator').val();
                    console.log("answer_dept:" + answer_dept);
                    console.log("deal_person_name:" + operator);
                    if (strIsEmpty(answer_dept) || strIsEmpty(operator)) {
                        alert("受理科室和经办领导不能为空!");
                        return;
                    }
                    $(this).dialog("close");
                    $.ajax({
                        type: "post",
                        url: contextPath + "/www/appealCase/updateAccept.do",
                        data: {id: id, answerDept: answer_dept, operator: operator},
                        beforeSend: function (XMLHttpRequest) {
                        },
                        success: function (rst, textStatus) {
                            if (rst) {
                                bootbox.dialog({
                                    title: '系统提示',
                                    message: rst.errorMessage,
                                    buttons: {
                                        "success": {
                                            "label": "<i class='ace-icon fa fa-check'></i>确定",
                                            "className": "btn-sm btn-success",
                                            "callback": function () {
                                                dialog.dialog("close");
                                                //重载数据
                                                jQuery(cfg.grid_selector).jqGrid('setGridParam', {
                                                    page: 1
                                                }).trigger("reloadGrid");
                                            }
                                        }
                                    }
                                });
                            }
                            ;
                        },
                        complete: function (XMLHttpRequest, textStatus) {
                        },
                        error: function () {
                        }
                    });
                }
            },
            {
                html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
                "class": "btn btn-xs",
                click: function () {
                    $(this).dialog("close");
                }
            }
        ]
    });
}

//处理详情
function progressAppealCase(id, title) {
    var dialog = $("#dialog-message-progress").removeClass('hide').dialog({
        modal: true,
        width: 600,
        title: "<div class='widget-header widget-header-small'><div class='widget-header-pd'>" + title + "</div></div>",
        title_html: true,
        buttons: [
            {
                html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
                "class": "btn btn-info btn-xs",
                click: function () {
                    var details_of_progress = $('#details_of_progress').val();
                    if (strIsEmpty(details_of_progress)) {
                        alert("处理内容不能为空!");
                        return;
                    }
                    $(this).dialog("close");
                    $.ajax({
                        type: "post",
                        url: contextPath + "/www/appealCase/updateDetailsOfProgress.do",
                        data: {id: id, detailsOfProgress: details_of_progress},
                        beforeSend: function (XMLHttpRequest) {
                        },
                        success: function (rst, textStatus) {
                            if (rst) {
                                bootbox.dialog({
                                    title: '系统提示',
                                    message: rst.errorMessage,
                                    buttons: {
                                        "success": {
                                            "label": "<i class='ace-icon fa fa-check'></i>确定",
                                            "className": "btn-sm btn-success",
                                            "callback": function () {
                                                dialog.dialog("close");
                                                //重载数据
                                                jQuery(cfg.grid_selector).jqGrid('setGridParam', {
                                                    page: 1
                                                }).trigger("reloadGrid");
                                            }
                                        }
                                    }
                                });
                            }
                            ;
                        },
                        complete: function (XMLHttpRequest, textStatus) {
                        },
                        error: function () {
                        }
                    });
                }
            },
            {
                html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
                "class": "btn btn-xs",
                click: function () {
                    $(this).dialog("close");
                }
            }
        ]
    });
}

//字符串判空
function strIsEmpty(obj) {
    if (typeof obj == "undefined" || obj == null || obj == "") {
        return true;
    } else {
        return false;
    }
}

//加载附件信息
function loadAttach(appealCaseId) {
    $.ajax({
        type: "post",
        url: contextPath + "/www/appealCase/findFileList.do",
        data: {appealCaseId: appealCaseId},
        success: function (rst, textStatus) {
            console.log(rst);
            //
            if (rst && rst.status == 0) {
                initAttachDom(rst.data, appealCaseId);
            } else {
                bootbox.dialog({
                    title: '系统提示',
                    message: rst.info,
                    detail: rst.info,
                    buttons: {
                        "success": {
                            "label": "<i class='ace-icon fa fa-check'></i>确定",
                            "className": "btn-sm btn-success",
                            "callback": function () {
                                //$( this ).dialog( "close" );
                            }
                        }
                    }
                });
            }
        }
    });
}
function initAttachDom(data, appealCaseId) {
    var html = [];
    var fileName;
    $.each(data,
        function (n, file) {
            fileName = strIsEmpty(file.name) ? '未知文件名' : file.name;
            html.push('<div id="' + file.id + '">' +
                '<a href="' + file.mediUrl + '" target="_blank">' + fileName + '</a>   ' +
                ' <a class=\'ace-icon glyphicon glyphicon-remove bigger-110\' ' +
                'href="javascript:deleteAttach(\'' + file.id + '\',\'' + appealCaseId + '\')"></a>' +
                '<b></b></div>');
        });
    $('#filelist-history').html(html.join(''));
}
function deleteAttach(id, appealCaseId) {
    console.log("id=" + id + ",appealCaseId=" + appealCaseId);
    $.ajax({
        type: "post",
        url: contextPath + "/www/appealCase/deleteAppealCaseFile.do",
        data: {id: id},
        success: function (rst, textStatus) {
            if (rst && rst.status == 0) {
                loadAttach(appealCaseId);
            } else {
                bootbox.dialog({
                    title: '系统提示',
                    message: rst.info,
                    detail: rst.info,
                    buttons: {
                        "success": {
                            "label": "<i class='ace-icon fa fa-check'></i>确定",
                            "className": "btn-sm btn-success",
                            "callback": function () {
                            }
                        }
                    }
                });
            }
        }
    });
}

function appendMediaIcon(id, mediaType) {
    //renderPage
    var html = new Array();
    html.push("<a id='btn-media-view-" + id + "' class='ace-icon fa fa-eye bigger-110' href='javascript:false'>浏览</a>");
    $("#" + id).after(html.join(''));

    //append button click event
    $("#btn-media-view-" + id).on('click',
        function (e) {
            e.preventDefault();
            var dialog = $("#dialog-message-file")
                .removeClass('hide')
                .dialog({
                    modal: true,
                    width: 650,
                    height: 400,
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
            var src = fileName;
            ///** mediaType 1-图片，2-视频，3-音频
            if ("1" == mediaType) {
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
            } else {
                html = [];
                html.push("<video src='" + fileName + "' controls='controls' autoplay='true'></video>");
                $("#dialog-message-file").append(html.join(''));
            }
        });
}

function resetDialogFileDom() {
    $("#dialog-message-file").empty();
    var html = [];
    html.push('<div id="load" class="loading"></div>');
    $("#dialog-message-file").append(html.join(''));
}


function renderImage(data) {
    var html = new Array();
    html.push('<ul class="ace-thumbnails clearfix">');
    $.each($(data), function (i, o) {
        //诉求人上传附件
        if (o.type == '1') {
            /**
             * img - 图片，file - 文件 ，video- 视频，audio - 音频
             */
            switch (o.mediType) {
                case 'img' :
                    html.push('<li>');
                    html.push('<a href="' + o.mediUrl + '" title="' + o.name + '" target="view_window" data-rel="colorbox" class="cboxElement">');
                    html.push('<img height="200" width="200" class="photo" src="' + o.mediUrl + '">');
                    html.push('</a>');
                    html.push('</li>');
                    break;
                case 'file' :
                    html.push('<li>');
                    html.push('<a href="' + o.mediUrl + '" target="_blank">' + o.name + '</a>');
                    html.push();
                    html.push('</li>');
                    break;
                case 'video' :
                    html.push('<li>');
                    html.push('<a href="' + o.mediUrl + '" title="' + o.name + '" target="view_window" data-rel="colorbox" class="cboxElement">');
                    html.push('<video  src="' + o.mediUrl + '" controls="controls"></video>');
                    html.push('</a>');
                    html.push('</li>');
                    break;
                case 'audio' :
                    html.push('<li>');
                    html.push('<a href="' + o.mediUrl + '" title="' + o.name + '" target="view_window" data-rel="colorbox" class="cboxElement">');
                    html.push('<audio controls="controls">');
                    html.push('<source src="' + o.mediUrl + '" type="audio/mp3" />');
                    html.push('</audio>');
                    html.push('</a>');
                    html.push('</li>');
                    break;
                default :
                    break;
            }
        }
    });
    html.push('</ul>');
    $("#appealAddons").html(html.join(""));
}