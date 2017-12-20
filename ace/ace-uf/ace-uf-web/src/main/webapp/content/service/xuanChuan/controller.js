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
                    time: new Date()
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

    $('#btn-view-add').on('click', function() {
        jQuery(cfg.grid_selector).jqGrid('editGridRow', 'new',
            {
                closeAfterAdd : true,
                recreateForm : true,
                viewPagerButtons : false,
                beforeSubmit : function(postdata) {
                    postdata.content=editor.getValue();
                    return [true,"",""];

                },
                beforeShowForm : function(e) {
                    var form = $(e[0]);
                    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
                        .wrapInner('<div class="widget-header" />')
                    style_edit_form(form);
                    initSimditor($("textarea[name=content]"),null);
                    appendUploadBtn("cover");
                }
            })
    });


    $('#btn-view-edit').on('click', function() {
        var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
        if (!gr) {
            $.jgrid.info_dialog($.jgrid.nav.alertcap, $.jgrid.nav.alerttext)
        }
        jQuery(cfg.grid_selector).jqGrid('editGridRow', gr,
            {
                closeAfterAdd : true,
                recreateForm : true,
                viewPagerButtons : true,
                beforeSubmit : function(postdata) {
                    postdata.content=editor.getValue();
                    return [true,"",""];
                },
                beforeShowForm : function(e) {
                    var form = $(e[0]);
                    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
                        .wrapInner('<div class="widget-header" />')
                    style_edit_form(form);
                    $("#TblGrid_grid-table").after("<div id='custom-dia'></div>");
                    var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam','selrow');
                    var gd=jQuery(cfg.grid_selector).jqGrid('getRowData',gr);
                    loadText(gd.id);
                    appendUploadBtn("cover");
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
                if (key.indexOf('Date') != -1 || key.indexOf('time') != -1 || key.indexOf('Time') != -1 || key.indexOf('birthday') != -1|| key.indexOf('published') != -1) {
                    value = Common.DateFormatter(value);
                }
                if (key == 'cover') {
                    if (value != '') {
                        value = '<image src="' + fastdfs_server + value
                            + '" />';
                    } else {
                        value = '待上传';
                    }

                }
                $("#dialog-message-view").find('#' + key).html(value);

            });
        },
        error: function () {
            alert("加载错误！");
        }
    });
}

function initSimditor(textarea,text){
    editor = new Simditor({
        textarea:textarea,//jQuery对象，HTML元素或选择器字符串可以传递给这个选项
        params :{},// 在textarea中插入隐藏的输入来存储参数（键值对）。
        toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough','fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'],
        upload: {
            url: portalPath+'/files/uploadImage.do', //文件上传的接口地址
            params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
            fileKey: 'file', //服务器端获取文件数据的参数名
            connectionCount: 3,
            leaveConfirm: '正在上传文件'
        }
    });
    if(text){
        editor.setValue(text);
    }
}

function loadText(id) {
    $.ajax({
        type : "post",
        url : cfg.view_load_data_url,
        data : {
            id : id
        },
        beforeSend : function(XMLHttpRequest) {
        },
        success : function(rst, textStatus) {
            initSimditor($("textarea[name=content]"),rst.value.content);
        },
        error : function() {
            alert("加载错误！");
        }
    });
}