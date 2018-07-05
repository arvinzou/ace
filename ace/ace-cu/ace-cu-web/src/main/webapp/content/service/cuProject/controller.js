jQuery(function ($) {
    $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
        _title: function (title) {
            var $title =
                this.options.title || '&nbsp;'
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
                    beforeSubmit: function (postdata) {
                        postdata.description = editor.getValue();
                        var coverUrl = postdata.coverUrl;
                        if (!coverUrl.startWith("http")) {
                            coverUrl = fastdfs_server + coverUrl;
                        }
                        postdata.coverUrl = coverUrl;
                        return [true, "", ""];
                    },
                    beforeShowForm: function (e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find(
                            '.ui-jqdialog-titlebar').wrapInner(
                            '<div class="widget-header" />')
                        style_edit_form(form);
                        //富文本编辑器
                        initSimditor($("textarea[name=description]"), "");
                        //封面上传
                        appendUploadBtn("coverUrl");
                    }
                })
        });
    $('#btn-view-edit').on(
        'click',
        function () {
            var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
            if (!gr) {
                $.jgrid.info_dialog($.jgrid.nav.alertcap, $.jgrid.nav.alerttext)
            }
            var gd = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
            var targetAmount = gd.targetAmount;
            if (!isNull(targetAmount) && !isAmount(targetAmount)) {
                alert("请输入正确的目标金额!");
                return;
            }

            jQuery(cfg.grid_selector).jqGrid(
                'editGridRow',
                gr,
                {
                    closeAfterAdd: true,
                    recreateForm: true,
                    viewPagerButtons: true,
                    beforeSubmit: function (postdata) {
                        postdata.description = editor.getValue();
                        var coverUrl = postdata.coverUrl;
                        if (!coverUrl.startWith("http")) {
                            coverUrl = fastdfs_server + coverUrl;
                        }
                        postdata.coverUrl = coverUrl;
                        return [true, "", ""];
                    },
                    beforeShowForm: function (e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find(
                            '.ui-jqdialog-titlebar').wrapInner(
                            '<div class="widget-header" />')
                        style_edit_form(form);
                        //
                        // $("#TblGrid_grid-table").after("<div id='custom-dia'></div>");
                        //富文本编辑器
                        loadText(gd.id);
                        //封面上传
                        appendUploadBtn("coverUrl");
                        //支出项目调整
                        if (gd.type == '3') {
                            //重设控件属性
                            retSetWidgetAttr(gd);
                            $('#targetAmount').attr("disabled", "disabled");
                        }
                    }
                })
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

    //审核按钮
    $('#btn-view-audit').on(
        'click',
        function (e) {
            e.preventDefault();
            var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
            if (!gr) {
                $.jgrid.info_dialog($.jgrid.nav.alertcap,
                    $.jgrid.nav.alerttext);
                return;
            }
            var rowData = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
            if (rowData.status != "1") {
                alert("不能重复审核！")
                return;
            }
            var dialog = $("#dialog-message-audit").removeClass('hide').dialog({
                modal: true,
                width: 380,
                title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i> " + rowData.title + "</h4></div>",
                title_html: true,
                buttons: [
                    {
                        html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
                        "class": "btn btn-info btn-xs",
                        id: 'ajax_button_audit',
                        click: function () {
                            //for testing
                            var audit_result = $('input[name="audit_result"]:checked').val();
                            var audit_opinion = $('#audit_opinion').val();
                            if (audit_result == undefined) {
                                alert("请选择审核结果!");
                                return;
                            }
                            $(this).dialog("close");
                            $.ajax({
                                type: "post",
                                url: contextPath + "/cuProject/audit",
                                data: {id: rowData.id, auditResult: audit_result, auditOpinion: audit_opinion},
                                beforeSend: function (XMLHttpRequest) {
                                    style_ajax_button('ajax_button_audit', true);
                                },
                                success: function (rst, textStatus) {
                                    style_ajax_button('ajax_button_audit', false);
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
                                    style_ajax_button('ajax_button_audit', false);
                                },
                                error: function () {
                                    style_ajax_button('ajax_button_audit', true);
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
        });

    //添加使用记录
    $('#btn-view-add-use-record').on(
        'click',
        function (e) {
            e.preventDefault();
            var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
            if (!gr) {
                $.jgrid.info_dialog($.jgrid.nav.alertcap,
                    $.jgrid.nav.alerttext);
                return;
            }
            var rowData = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
            var pProjectId = rowData.id;

            //new form
            jQuery(cfg.grid_selector).jqGrid(
                'editGridRow',
                'new',
                {
                    closeAfterAdd: true,
                    recreateForm: true,
                    viewPagerButtons: false,
                    beforeSubmit: function (postdata) {
                        postdata.description = editor.getValue();
                        var coverUrl = postdata.coverUrl;
                        if (!coverUrl.startWith("http") && !isNull(coverUrl)) {
                            coverUrl = fastdfs_server + coverUrl;
                        }
                        postdata.coverUrl = coverUrl;
                        postdata.parentId = pProjectId;

                        var amount = postdata.targetAmount;
                        console.log("amount:" + amount);
                        console.log("isNull:" + isNull(amount));
                        console.log("isAmount:" + isAmount(amount));
                        if (isNull(amount) || !isAmount(amount) || 0 == amount) {
                            alert("请输入正确的支出金额!");
                            return [false, "", ""];
                        }
                        // return [false, "", ""];//for test

                        return [true, "", ""];
                    },
                    beforeShowForm: function (e) {

                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find(
                            '.ui-jqdialog-titlebar').wrapInner(
                            '<div class="widget-header" />')
                        style_edit_form(form);
                        //富文本编辑器
                        initSimditor($("textarea[name=description]"), "");
                        //封面上传
                        appendUploadBtn("coverUrl");
                        //使用记录标题
                        var recordTitle = '"' + rowData.projectName + '"' + " - " + "添加使用记录";
                        $('.ui-jqdialog-title').html(recordTitle);
                        //重设控件属性
                        retSetWidgetAttr(rowData);
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
                // if (key == 'category') {
                //     value = rsd(value, '83');
                // }
                if (key == 'status') {
                    value == "1" ? "正常" : "关闭";
                }
                if (key.indexOf('Date') != -1 || key.indexOf('time') != -1 || key.indexOf('Time') != -1 || key.indexOf('birthday') != -1) {
                    value = Common.DateFormatter(value);
                }
                $("#dialog-message-view").find('#' + key).html(value);
            });
        },
        error: function () {
            alert("加载错误！");
        }
    });
}

function initSimditor(textarea, text) {
    editor = new Simditor({
        textarea: textarea,//jQuery对象，HTML元素或选择器字符串可以传递给这个选项
        params: {},// 在textarea中插入隐藏的输入来存储参数（键值对）。
        toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'],
        upload: {
            url: portalPath + '/files/uploadImage.do', //文件上传的接口地址
            params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
            fileKey: 'file', //服务器端获取文件数据的参数名
            connectionCount: 3,
            leaveConfirm: '正在上传文件'
        }
    });
    if (text) {
        editor.setValue(text);
    }
}

function loadText(id) {
    $.ajax({
        type: "post",
        url: cfg.view_load_data_url,
        data: {id: id},
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
            initSimditor($("textarea[name=description]"), rst.value.content);
        },
        error: function () {
            alert("加载错误！");
        }
    });
}

String.prototype.startWith = function (str) {
    var reg = new RegExp("^" + str);
    return reg.test(this);
}
String.prototype.endWith = function (str) {
    var reg = new RegExp(str + "$");
    return reg.test(this);
}

function retSetWidgetAttr(rowData) {
    //项目类型
    $("#type").get(0).selectedIndex = 4;//.attr("selected", true);
    $('#type').attr("disabled", true);
    //目标金额 => 支出金额
    $('#tr_targetAmount').find(".CaptionTD").html("支出金额");
    $('#targetAmount').after('<span style="color:red;font-size:16px;font-weight:800">*</span>');

}


function isNull(str) {
    if (str == "") return true;
    var regu = "^[ ]+$";
    var re = new RegExp(regu);
    return re.test(str);
}

function isAmount(amount) {
    var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
    return reg.test(amount);
}