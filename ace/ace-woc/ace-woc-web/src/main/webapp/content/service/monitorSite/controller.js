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
            var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
            if (!gr) {
                $.jgrid.info_dialog($.jgrid.nav.alertcap, $.jgrid.nav.alerttext)
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
    $("#btn-view-da").on('click', function (e) {
        e.preventDefault();
        var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
        if (!gr) {
            $.jgrid.info_dialog($.jgrid.nav.alertcap, $.jgrid.nav.alerttext);
            return;
        }
        var r = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);

        console.log(gr);
        console.log(r);
        var dialog = $("#dialog-message").removeClass('hide').dialog({
            modal: true,
            width: 1000,
            title: "<div class='widget-header widget-header-small'><div class='widget-header-pd' > " + r.monitorSiteName + "</div></div>",
            title_html: true,
            buttons: [
                {
                    html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 提交",
                    "class": "btn btn-info btn-xs",
                    id: 'ajax_button',
                    click: function () {
                        console.log('click');
                        // var rowIds = jQuery("#mydevice-grid-table").jqGrid('getDataIDs');
                        // console.log(rowIds);
                        // if (rowIds.length < 1) {
                        //     console.log('检测到没有添加设备');
                        //     bootbox.confirm("<div><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> 至少要分配一个设备!</h4></div>",
                        //         function (result) {
                        //     });
                        //     return;
                        // }

                        $("#dialog-confirm").removeClass('hide').dialog({
                            resizable: false,
                            modal: false,
                            title: "<div class='widget-header'><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> 确认分配设备吗?</h4></div>",
                            title_html: true,
                            buttons: [
                                {
                                    html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 提交",
                                    "class": "btn btn-info btn-xs",
                                    click: function () {
                                        $(this).dialog("close");
                                        var s = '';
                                        var rowIds = jQuery("#mydevice-grid-table").jqGrid('getDataIDs');
                                        if (rowIds) {
                                            s = rowIds.join(',')
                                        }
                                        $.ajax({
                                            type: "post",
                                            url: contextPath + "/monitorSite/bindMonitorSiteDevice",
                                            data: {monitorSiteId: gr, deviceIds: s},
                                            beforeSend: function (XMLHttpRequest) {
                                                style_ajax_button('ajax_button', true);
                                            },
                                            success: function (rst, textStatus) {
                                                style_ajax_button('ajax_button', false);
                                                if (rst) {
                                                    bootbox.dialog({
                                                        title: '系统提示',
                                                        message: rst.errorMessage,
                                                        buttons:
                                                            {
                                                                "success":
                                                                    {
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
                                }
                                ,
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

        jQuery('#mydevice-grid-table').jqGrid('setGridParam', {
            url: contextPath + "/monitorSite/findMonitorSiteDetailList",
            postData: {monitorSiteId: gr}
        }).trigger("reloadGrid");
    });
    $("#btn-view-da-add").on('click', function (e) {
        var gr = jQuery('#alldevice-grid-table').jqGrid('getGridParam', 'selrow');
        console.log(gr);
        if (!gr) {
            bootbox.confirm("<div><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i> 请选择要分配的设备!</h4></div>",
                function (result) {
                });
            return;
        }
        var srows = jQuery('#alldevice-grid-table').jqGrid('getGridParam', 'selarrrow');
        console.log(srows);
        var rowIds = jQuery("#mydevice-grid-table").jqGrid('getDataIDs');
        var repeat = false, rpn = [];
        $.each(srows, function (i, o) {
            var rd = jQuery('#alldevice-grid-table').jqGrid('getRowData', o);
            if ($.inArray(o, rowIds) > -1) {
                repeat = true, rpn.push(rd.deviceName);
            } else {
                jQuery("#mydevice-grid-table").jqGrid('addRowData', o, rd);
                jQuery("#alldevice-grid-table").jqGrid('delRowData', o);
            }
        });
        if (repeat) {
            bootbox.confirm("<div>" +
                "<h4 class='smaller'>" +
                "<i class='ace-icon fa fa-exclamation-triangle red'></i>" +
                " 请选择要分配的设备!" +
                "</h4>" +
                "</div>重复的设备<b>" + rpn.join(',') + "<b>!</h4></div>", function (result) {
            });
        }
    });
    $("#btn-view-da-del").on('click', function (e) {
        var gr = jQuery('#mydevice-grid-table').jqGrid('getGridParam', 'selrow');
        if (!gr) {
            bootbox.confirm("<div><h4 class='smaller'><i class='ace-icon fa fa-exclamation-triangle red'></i>请选择要移除的设备!</h4></div>", function (result) {
            });
            return;
        }
        var srows = jQuery('#mydevice-grid-table').jqGrid('getGridParam', 'selarrrow');
        $.each(srows, function (i, o) {
            var rd = jQuery('#mydevice-grid-table').jqGrid('getRowData', o);
            jQuery("#mydevice-grid-table").jqGrid('delRowData', o);
            jQuery("#alldevice-grid-table").jqGrid('addRowData', o, rd);
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
    var url=contextPath + "/monitorSite/findMonitorSiteDetailList";
    var postData={monitorSiteId: id};
    $.getJSON(url,postData,function (result) {
        if(result.status==0){
            tablePreview(result.rows);
        }
    });


}

function tablePreview(data) {
    console.log(data);
    $('#device-table').empty();
    for(var i=0;i<data.length;i++){
        var t=tableView.replace('[index]',i+1+'');
        t=t.replace('[deviceName]',data[i].deviceName);
        t=t.replace('[deviceType]',data[i].deviceType);
        t=t.replace('[deviceNo]',data[i].deviceNo);
        $('#device-table').append($(t));
    }
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

var tableView='<tr>'+
    '                <th>[index]</th>'+
    '                <th>[deviceName]</th>'+
    '                <th>[deviceType]</th>'+
    '                <th>[deviceNo]</th>'+
    ' </tr>';