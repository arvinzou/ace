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
            var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
                'selrow');
            if (!gr) {
                $.jgrid.info_dialog($.jgrid.nav.alertcap,
                    $.jgrid.nav.alerttext)
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


    //统一催缴
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
            if (rowData.status == "2") {
                alert("请勿重复发送!");
                return;
            }

            if (confirm("是否确认发送催缴通知?")) {
                $.ajax({
                    type: "post",
                    url: contextPath + "/fopCallRecord/sendCallNotice",
                    data: {recordId: rowData.id},
                    beforeSend: function (XMLHttpRequest) {
                        sb('btn-view-send-notice', true, 'glyphicon glyphicon-refresh');
                    },
                    success: function (rst, textStatus) {
                        sb('btn-view-send-notice', false, 'glyphicon glyphicon-refresh');
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
                        sb('btn-view-send-notice', false, 'glyphicon glyphicon-refresh');
                    },
                    error: function () {
                        sb('btn-view-send-notice', false, 'glyphicon glyphicon-refresh');
                    }
                });
            }
        });

    //添加催缴对象
    $("#btn-view-add-call-detail").on('click', function (e) {
        e.preventDefault();
        var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
        if (!gr) {
            $.jgrid.info_dialog($.jgrid.nav.alertcap,
                $.jgrid.nav.alerttext);
            return;
        }
        //逻辑校验
        var r = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
        if (r.status == "2") {
            alert("当前记录已发送完毕!");
            return;
        }
        //弹出框
        var ajax_button;
        var dialog = $("#dialog-message").removeClass('hide').dialog({
            modal: true,
            width: 750,
            title: "<div class='widget-header widget-header-small'><div class='widget-header-pd' > " + r.payTitle + "</div></div>",
            title_html: true,
            buttons: [
                {
                    html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 提交",
                    "class": "btn btn-info btn-xs",
                    id: 'ajax_button',
                    click: function () {
                        //校验2
                        // var rowIds = jQuery("#my-mlist-grid-table").jqGrid('getDataIDs');
                        // if (rowIds.length < 1) {
                        //     bootbox.confirm("<div><h4 class='smaller'>" +
                        //         "<i class='ace-icon fa fa-exclamation-triangle red'></i> 至少要添加一个催缴对象!</h4></div>",
                        //         function (result) {
                        //         });
                        //     return;
                        // }
                        //数据准备
                        $(this).dialog("close");
                        var jsonData = [];
                        // var rowIds = jQuery("#my-mlist-grid-table").jqGrid('getDataIDs');
                        // if (rowIds) {
                        //     s = rowIds.join(',')
                        // }
                        var a = {};
                        a.name = 'nameA';
                        a.sex = '1';
                        var b = {};
                        b.name = 'nameB';
                        b.sex = '0';
                        jsonData.push(a);
                        jsonData.push(b);
                        //ajax调用
                        $.ajax({
                            type: "post",
                            url: contextPath + "/fopCallRecord/insertCallRecordDetail",
                            data: {recordId: r.id, data: JSON.stringify(jsonData)},
                            beforeSend: function (XMLHttpRequest) {
                                style_ajax_button('ajax_button', true);
                            },
                            success: function (rst, textStatus) {
                                style_ajax_button('ajax_button', false);
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
                                                }
                                            }
                                        }
                                    });

                                }
                            },
                            complete: function (XMLHttpRequest, textStatus) {
                                style_ajax_button('ajax_button', false);
                            },
                            error: function () {
                                style_ajax_button('ajax_button', true);
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
        //加载数据
        jQuery('#my-mlist-grid-table').jqGrid('setGridParam', {
            url: contextPath + "/users/selectRoleListByUserId.do",
            postData: {recordId: r.id}
        }).trigger("reloadGrid");
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
            });
        },
        error: function () {
            alert("加载错误！");
        }
    });
}

function style_ajax_button(btnId, status) {
    console.log(status);
    var btn = $('#' + btnId);
    if (status) {
        btn.find('i').removeClass('fa-check');
        btn.find('i').addClass('fa-spinner fa-spin');
        btn.attr('disabled', "true");

    } else {
        btn.find('i').removeClass('fa-spinner');
        btn.find('i').removeClass('fa-spin');
        btn.find('i').addClass('fa-check');
        btn.removeAttr("disabled");
    }
}

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
}