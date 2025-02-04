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
    //查询
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
    //新增
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
    //修改
    $('#btn-view-edit').on(
        'click',
        function () {
            var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
                'selrow');
            if (!gr) {
                $.jgrid.info_dialog($.jgrid.nav.alertcap,
                    $.jgrid.nav.alerttext)
            }
            var rowData = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
            //1-未审核，2-已审核
            if (rowData.payResult == "1") {
                alert("记录已确认，不允许再修改");
                return;
            }

            jQuery(cfg.grid_selector).jqGrid(
                'editGridRow',
                gr,
                {
                    closeAfterAdd: true,
                    recreateForm: true,
                    viewPagerButtons: true,
                    beforeShowForm: function (e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find(
                            '.ui-jqdialog-titlebar').wrapInner(
                            '<div class="widget-header" />')
                        style_edit_form(form);
                    }
                })
        });
    //删除
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
    //缴费确认
    $('#btn-view-audit').on(
        'click',
        function () {
            var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
                'selrow');
            if (!gr) {
                $.jgrid.info_dialog($.jgrid.nav.alertcap,
                    $.jgrid.nav.alerttext);
                return;
            }
            var rowData = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
            //0-未缴纳 ， 1-已缴纳
            if (rowData.payResult == "1") {
                alert("请勿重复确认!");
                return;
            }
            if (confirm("是否确认已缴费?")) {
                $.ajax({
                    type: "post",
                    url: contextPath + "/fopPayRecord/audit",
                    data: {id: rowData.id},
                    beforeSend: function (XMLHttpRequest) {
                        sb('btn-view-audit', true, 'glyphicon glyphicon-refresh');
                    },
                    success: function (rst, textStatus) {
                        sb('btn-view-audit', false, 'glyphicon glyphicon-refresh');
                        if (rst) {
                            bootbox.dialog({
                                title: '系统提示',
                                message: rst.errorMessage,
                                detail: rst.detail,
                                buttons: {
                                    "success": {
                                        "label": "<i class='ace-icon fa fa-check'></i>确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            //刷新查询数据
                                            jQuery(cfg.grid_selector).jqGrid('setGridParam',
                                                {
                                                    page: 1
                                                }).trigger("reloadGrid");
                                        }
                                    }
                                }
                            });
                        }
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        sb('btn-view-audit', false, 'glyphicon glyphicon-refresh');
                    },
                    error: function () {
                        sb('btn-view-audit', false, 'glyphicon glyphicon-refresh');
                    }
                });
            }
        });

    //催缴通知
    $('#btn-view-send-notice').on(
        'click',
        function () {
            var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
                'selrow');
            if (!gr) {
                $.jgrid.info_dialog($.jgrid.nav.alertcap,
                    $.jgrid.nav.alerttext);
                return;
            }
            var rowData = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
            if (confirm("是否确认发送催缴通知?")) {
                $.ajax({
                    type: "post",
                    url: contextPath + "/fopPayRecord/sendNotice",
                    data: {id: rowData.id},
                    beforeSend: function (XMLHttpRequest) {
                        sb('btn-view-audit', true, 'glyphicon glyphicon-refresh');
                    },
                    success: function (rst, textStatus) {
                        sb('btn-view-audit', false, 'glyphicon glyphicon-refresh');
                        if (rst) {
                            bootbox.dialog({
                                title: '系统提示',
                                message: rst.errorMessage,
                                detail: rst.detail,
                                buttons: {
                                    "success": {
                                        "label": "<i class='ace-icon fa fa-check'></i>确定",
                                        "className": "btn-sm btn-success",
                                        "callback": function () {
                                            //刷新查询数据
                                            jQuery(cfg.grid_selector).jqGrid('setGridParam',
                                                {
                                                    page: 1
                                                }).trigger("reloadGrid");
                                        }
                                    }
                                }
                            });
                        }
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        sb('btn-view-audit', false, 'glyphicon glyphicon-refresh');
                    },
                    error: function () {
                        sb('btn-view-audit', false, 'glyphicon glyphicon-refresh');
                    }
                });
            }
        });

    function sb(btnId, status, iconCss) {
        console.log(status);
        var btn = $('#' + btnId);
        if (status) {
            btn.find('i').removeClass(iconCss);
            btn.find('i').addClass('fa-spinner fa-spin');
            btn.attr('disabled', "true");

        } else {
            btn.find('i').removeClass('fa-spinner');
            btn.find('i').removeClass('fa-spin');
            btn.find('i').addClass(iconCss);
            btn.removeAttr("disabled");
        }
    };
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
            });
        },
        error: function () {
            alert("加载错误！");
        }
    });
}