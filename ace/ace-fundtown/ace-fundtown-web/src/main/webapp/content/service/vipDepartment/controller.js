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
                // console.log("====================");
                // console.log(params);
                jQuery(cfg.grid_selector).jqGrid('setGridParam', {
                    page: 1,
                    postData: params
                }).trigger("reloadGrid");
                return false;
            }
        });
    });
// 添加
    $('#btn-view-add').on('click', function () {
        var regAddr = "常德市柳叶湖旅游度假区七里桥街道戴家岗社区清科基金小镇";
        jQuery(cfg.grid_selector).jqGrid(
            'editGridRow',
            'new',
            {
                closeAfterAdd: true,
                recreateForm: true,
                viewPagerButtons: false,
                beforeSubmit: function (postdata) {
                    if (postdata.type == '1'
                        && postdata.parentDepartmentId != null
                        && postdata.parentDepartmentId != '') {
                        postdata.parent = {departmentName: postdata.parentDepartmentId};
                    }

                    return [true, "", ""];
                },
                beforeShowForm: function (e) {
                    var form = $(e[0]);
                    form.closest('.ui-jqdialog').find(
                        '.ui-jqdialog-titlebar').wrapInner(
                        '<div class="widget-header" />')
                    style_edit_form(form);
                    //默认注册地址
                    $('#regAddr').val(regAddr);
                }
            })
    });
// 编辑
    $('#btn-view-edit').on('click', function () {
        var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
        if (!gr) {
            $.jgrid.info_dialog($.jgrid.nav.alertcap, $.jgrid.nav.alerttext)
        }
        var rowData = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
        var regAddr = rowData.regAddr;
        if (regAddr == '' || null == regAddr || regAddr == undefined) {
            regAddr = "常德市柳叶湖旅游度假区七里桥街道戴家岗社区清科基金小镇";
        }
        jQuery(cfg.grid_selector).jqGrid('editGridRow', gr, {
            closeAfterAdd: true,
            recreateForm: true,
            viewPagerButtons: true,
            beforeSubmit: function (postdata) {
                postdata.departmentId = rowData.departmentId;
                return [true, "", ""];
            },
            beforeShowForm: function (e) {
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find(
                    '.ui-jqdialog-titlebar').wrapInner(
                    '<div class="widget-header" />')
                style_edit_form(form);
                //默认注册地址
                $('#regAddr').val(regAddr);
                //loadText
                loadText(rowData.departmentId);
            }
        })
    });
    //删除
    $('#btn-view-del').on('click', function () {
        var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
        if (!gr) {
            $.jgrid.info_dialog($.jgrid.nav.alertcap, $.jgrid.nav.alerttext);
            return;
        }
        var rowData = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
        $.ajax({
            type: "post",
            url: contextPath + "/vipDepartment/delete",
            data: {departmentId: rowData.departmentId},
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
                                    //重载数据
                                    jQuery(cfg.grid_selector).jqGrid('setGridParam', {
                                        page: 1
                                    }).trigger("reloadGrid");
                                }
                            }
                        }
                    });
                }
            },
            complete: function (XMLHttpRequest, textStatus) {
                style_ajax_button('ajax_button_audit', false);
            },
            error: function () {
                style_ajax_button('ajax_button_audit', true);
            }
        });
    });

    //入驻审核
    $('#btn-view-audit').on('click', function (e) {
        e.preventDefault();
        var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
        if (!gr) {
            $.jgrid.info_dialog($.jgrid.nav.alertcap,
                $.jgrid.nav.alerttext);
            return;
        }
        var rowData = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
        // return;
        //show dialog=============
        var dialog = $("#dialog-message-audit").removeClass('hide').dialog({
            modal: true,
            width: 420,
            title: "<div class='widget-header widget-header-small'><h4 class='smaller'>" +
            "<i class='ace-icon fa fa-cog'></i> " + rowData.departmentName + "</h4>" +
            "</div>",
            title_html: true,
            buttons: [
                {
                    html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
                    "class": "btn btn-info btn-xs",
                    id: 'ajax_button_audit',
                    click: function () {
                        var auditResult = $('input[name="auditResult"]:checked').val();
                        var auditOpinion = $('#auditOpinion').val();
                        var nodeId = $('#fm-audit').find('input[name="node-list"]').val();
                        console.log("nodid:" + nodeId);
                        if (nodeId == undefined || nodeId == '' || nodeId == null) {
                            alert("请选择入驻节点!");
                            return;
                        }
                        if (auditResult == undefined || auditResult == '' || auditResult == null) {
                            alert("请选择审核结果!");
                            return;
                        }
                        $(this).dialog("close");
                        //ajax 调用
                        $.ajax({
                            type: "post",
                            url: contextPath + "/vipDepartment/audit",
                            data: {
                                deptId: rowData.departmentId,
                                nodeId: nodeId,
                                auditResult: auditResult,
                                auditOpinion: auditOpinion
                            },
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
                                                    //重载数据
                                                    jQuery(cfg.grid_selector).jqGrid('setGridParam', {
                                                        page: 1
                                                    }).trigger("reloadGrid");
                                                }
                                            }
                                        }
                                    });
                                }
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

    //信息公示
    $('#btn-view-publicity').on('click', function (e) {
        e.preventDefault();
        var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
        if (!gr) {
            $.jgrid.info_dialog($.jgrid.nav.alertcap, $.jgrid.nav.alerttext);
            return;
        }
        var rowData = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
        // if (rowData.started == "1") {
        //     alert("项目已上线，请勿重复操作")
        //     return;
        // }

        $.ajax({
            type: "post",
            url: contextPath + "/vipDepartment/publicity",
            data: {deptId: rowData.departmentId},
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
                                    //重载数据
                                    jQuery(cfg.grid_selector).jqGrid('setGridParam', {
                                        page: 1
                                    }).trigger("reloadGrid");
                                }
                            }
                        }
                    });
                }
            },
            complete: function (XMLHttpRequest, textStatus) {
                style_ajax_button('ajax_button_audit', false);
            },
            error: function () {
                style_ajax_button('ajax_button_audit', true);
            }
        });

    });
});

//字符串判空
function strIsEmpty(obj) {
    if (typeof obj == "undefined" || obj == null || obj == "") {
        return true;
    } else {
        return false;
    }
}
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
    //加载附件信息
    loadAttach(id);
    //初始化上传按钮事件
    initUploader("pickfiles", id);

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
            console.log(JSON.stringify(rst.value));
            $.each(rst.value, function (key, value) {
                if (key == 'status') {
                    var rst = "";
                    switch (value) {
                        case '0' :
                            rst = "已删除";
                            break;
                        case '1' :
                            rst = "入驻中";
                            break;
                        case '2' :
                            rst = "注册成功";
                            break;
                        default :
                            rst = "入驻中";
                    }
                    value = rst;
                }
                if (key.indexOf('Date') != -1 ||
                    key.indexOf('time') != -1 ||
                    key.indexOf('Time') != -1 ||
                    key.indexOf('birthday') != -1) {
                    value = Common.DateFormatter(value);
                }
                if (key == 'type') {
                    value = rsd(value, '147');
                }

                $("#dialog-message-view").find('#' + key).html(value);
            });
        },
        error: function () {
            alert("加载错误！");
        }
    });
}
//加载附件信息
function loadAttach(id) {
    $.ajax({
        type: "post",
        url: contextPath + "/vipDepartment/findVipResList",
        data: {deptId: id},
        success: function (rst, textStatus) {
            console.log(rst);
            //
            if (rst && rst.status == 0) {
                initAttachDom(rst.data, id);
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
//初始化附件节点
function initAttachDom(data, deptId) {
    console.log("========initAttachDom:" + data)
    var html = [];
    var fileName;
    $.each(data, function (n, file) {
        fileName = strIsEmpty(file.resName) ? '未知文件名' : file.resName;
        html.push('<div id="' + file.id + '">' +
            '<a href="' + file.resUrl + '" target="_blank">' + fileName + '</a>   ' +
            ' <a class=\'ace-icon glyphicon glyphicon-remove bigger-110\' ' +
            'href="javascript:deleteAttach(\'' + file.id + '\',\'' + deptId + '\')"></a>' +
            '<b></b></div>');
    });
    $('#filelist-history').html(html.join(''));
}
//删除文件
function deleteAttach(id, deptId) {
    $.ajax({
        type: "post",
        url: contextPath + "/vipDepartment/deleteAttach",
        data: {resId: id},
        success: function (rst, textStatus) {
            if (rst && rst.status == 0) {
                loadAttach(deptId);
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


function loadText(id) {
    console.log("id = " + id);
    $.ajax({
        type: "post",
        url: cfg.view_load_data_url,
        data: {
            id: id
        },
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
            $("#TblGrid_grid-table").find("input[name=parentDepartmentId]").val(rst.value.parentDepartmentName);
        },
        error: function () {
            alert("加载错误！");
        }
    });
}

function repealPublicity(deptId) {

    $.ajax({
        type: "post",
        url: contextPath + "/vipDepartment/repealPublicity",
        data: {deptId: deptId},
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
                                //重载数据
                                jQuery(cfg.grid_selector).jqGrid('setGridParam', {
                                    page: 1
                                }).trigger("reloadGrid");
                            }
                        }
                    }
                });
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            style_ajax_button('ajax_button_audit', false);
        },
        error: function () {
            style_ajax_button('ajax_button_audit', true);
        }
    });
}


