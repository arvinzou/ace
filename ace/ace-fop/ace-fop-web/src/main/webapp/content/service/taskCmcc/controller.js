jQuery(function ($) {
    $.fn.spin = function (opts) {
        this.each(function () {
            var $this = $(this),
                data = $this.data();

            if (data.spinner) {
                data.spinner.stop();
                delete data.spinner;
            }
            if (opts !== false) {
                data.spinner = new Spinner($.extend({color: $this.css('color')}, opts)).spin(this);
            }
        });
        return this;
    };

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

    $('#btn-view-add').on('click', function () {
        window.location.href = contextPath + '/dynamic/service/taskCmcc/add.jsp?id=' + urlid;
    });


    initEvents();

});

//初始化模态框事件
function initModalEvents() {
    //详情模态框
    $('#modal-detail').on('shown.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        console.log(id);
        var modal = $(this);
        modal.find('.modal-body input[name=id]').val(id);
        detail(id);
    });
}
//初始化按钮事件
function initBtnEvents() {
    $('#tree-dept').tree({
        checkbox: true,
        url: '/fop/fopCallRecord/selectMemberCheckTreeList',
        onBeforeExpand: function (node, param) {
        },
        onClick: function (node) {
        }
    });

    $('#combogrid-tmp').combogrid({
        panelWidth: 530,
        idField: 'id',
        textField: 'fullName',
        url: '/fop/fopCallRecord/selectSendList',
        mode: 'remote',
        fitColumns: false,
        method: 'get', columns: [[
            {field: 'mobile', title: '手机号', width: 100, align: 'right'},
            {field: 'fullName', title: '企业/团体/个人名称', width: 390, align: 'right'}
        ]],
        keyHandler: {
            up: function () {
            },

            down: function () {
            },

            enter: function () {
            },
            query: function (q) {
                $('#combogrid-tmp').combogrid("grid").datagrid("reload", {'q': q});
                $('#combogrid-tmp').combogrid("setValue", q);
            }

        }
    });

    $("#btn-view-select-tmp").on('click', function (e) {
        e.preventDefault();
        selectMobile();
    });

    $('#combogrid-tmp').combogrid({
        onSelect: function (index, row) {
            selectMobile();
        }
    });

    $("#btn-view-select").on('click', function (e) {
        e.preventDefault();

        var dialog = $("#dialog-message").removeClass('hide').dialog({
            modal: true,
            width: 610,
            title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i>发送人员</h4></div>",
            title_html: true,
            buttons: [
                {
                    html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
                    "class": "btn btn-info btn-xs",
                    id: 'ajax_button',
                    click: function () {
                        var html = new Array();
                        var nodes = $('#tree-dept').tree('getChecked');
                        $.each($(nodes), function (i, o) {
                            if (o.href != '') {
                                html.push('<div class="layout-user" >');
                                html.push('<user tel="' + o.href + '" class="badge badge-' + cssColor9[0] + '">');
                                html.push(o.text);
                                html.push('</user>');
                                html.push('</div>');
                            }

                        });
                        nodes = $('#tree-free').tree('getChecked');
                        $.each($(nodes), function (i, o) {
                            if (o.href != '') {
                                html.push('<div class="layout-user" >');
                                html.push('<user tel="' + o.href + '" class="badge badge-' + cssColor9[0] + '">');
                                html.push(o.text);
                                html.push('</user>');
                                html.push('</div>');
                            }
                        });
                        $('#task-content').html(html.join('') + $('#task-content-tmp').html());
                        $('#task-content-tmp').html('');
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

    });

    $("#btn-view-remove").on('click', function (e) {
        e.preventDefault();
        $('#task-content').html('');
    });

    $("#btn-view-remove-last").on('click', function (e) {
        $('#task-content').find('div:last').remove();
    });

    $("#btn-view-remove-tmp").on('click', function (e) {
        e.preventDefault();
        $('#task-content-tmp').html('');
    });

    $("#btn-view-remove-tmp-last").on('click', function (e) {
        $('#task-content-tmp').find('div:last').remove();
    });

    $("#btn-view-submit").on('click', function (e) {
        e.preventDefault();
        var tel = new Array();
        $.each($('user'), function (i, obj) {
            tel.push($(obj).attr("tel") + "," + $(obj).html());
        });
        var telext = $('#telext').val();
        if (telext != '') {
            var reg = /^1\d{10}(;1\d{10})*$/;
            var r = telext.match(reg);
            if (r == null) {
                alert('对不起，多个手机号用;隔开,请注意英文分号!');
                return;
            }

            var o = telext.split(';');
            for (var i = 0; i < o.length; i++) {
                tel.push(o[i] + ",临时");
            }
        }
        var taskCmcc = {};
        taskCmcc['msg'] = $('#msg').val() + "【常德市工商联】";
        taskCmcc['taskName'] = "催缴任务[" + new Date().getTime() + "]";// $('#taskName').val();
        taskCmcc['tel'] = tel.join(';');
        //alert(tel.join(';'));
        var json = JSON.stringify(taskCmcc);
        $.ajax({
            type: "post",
            url: contextPath + "/taskCmcc/insertTaskCmcc.do",
            data: {jsons: json},
            beforeSend: function (XMLHttpRequest) {
                style_ajax_button('btn-view-submit', true);
            },
            success: function (rst, textStatus) {
                style_ajax_button('btn-view-submit', false);
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
                                    //关闭窗口
                                    window.location.href = contextPath + '/dynamic/service/taskCmcc/index.jsp?id=4302';
                                }
                            }
                        }
                    });

                }
            },
            complete: function (XMLHttpRequest, textStatus) {
                style_ajax_button('btn-view-submit', false);
            },
            error: function () {
                style_ajax_button('btn-view-submit', true);
            }
        });
    });

    $("#btn-view-detail").on('click', function (e) {
        e.preventDefault();
        var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
        if (!gr) {
            $.jgrid.info_dialog($.jgrid.nav.alertcap, $.jgrid.nav.alerttext);
            return;
        }
        var gd = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
        var dialog = $("#dialog-message").removeClass('hide').dialog({
            modal: true,
            width: 660,
            title: "<div class='widget-header widget-header-small'><h4 class='smaller'><i class='ace-icon fa fa-cog'></i>发送详情</h4></div>",
            title_html: true,
            buttons: [

                {
                    html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
                    "class": "btn btn-info btn-xs",
                    id: 'ajax_button',
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
        $.ajax({
            type: "post",
            url: contextPath + "/taskCmcc/selectById.do",
            data: {id: gd.taskId},
            beforeSend: function (XMLHttpRequest) {
            },
            success: function (rst, textStatus) {
                var html = new Array();
                var nodes = rst.value.tel.split(';');
                $.each($(nodes), function (i, o) {
                    if (o.href != '') {
                        html.push('<div class="layout-user" >');
                        html.push('<user  class="badge badge-' + cssColor9[0] + '">');
                        html.push(o);
                        html.push('</user>');
                        html.push('</div>');
                    }

                });
                $('#task-content').html(html.join(''));
                $('#msg-content').html(rst.value.msg);
            },
            complete: function (XMLHttpRequest, textStatus) {

            },
            error: function () {
            }
        });
    });

    $("#btn-view-repeat").on('click', function (e) {
        e.preventDefault();
        var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
        if (!gr) {
            $.jgrid.info_dialog($.jgrid.nav.alertcap, $.jgrid.nav.alerttext);
            return;
        }
        var gd = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
        $.ajax({
            type: "post",
            url: portalPath + "/taskCmcc/updateTaskStatusCmccByTaskCmccId.do",
            data: {id: gd.taskId},
            beforeSend: function (XMLHttpRequest) {
                $('#btn-view-repeat').attr('disabled', "true");
            },
            success: function (rst, textStatus) {
                $('#btn-view-repeat').removeAttr("disabled");
                jQuery(cfg.grid_selector).jqGrid('setGridParam', {}).trigger("reloadGrid");

            },
            complete: function (XMLHttpRequest, textStatus) {
                $('#btn-view-repeat').removeAttr("disabled");
            },
            error: function () {
                $('#btn-view-repeat').removeAttr("disabled");
            }
        });
    });
}
//初始化事件列表
function initEvents() {

    initModalEvents();

    initBtnEvents();
    //
}

function reloadGrid() {
    jQuery(cfg.grid_selector).jqGrid('setGridParam', {}).trigger("reloadGrid");
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

function selectMobile() {
    var html = new Array();
    var g = $('#combogrid-tmp').combogrid('grid');	// get datagrid object
    var r = g.datagrid('getSelected');	// get the selected row
    console.log("+++++++++:" + JSON.stringify(r));
    var isExit = false;
    if (r && r.mobile) {
        $.each($('user'), function (i, obj) {
            if ($(obj).attr("tel") == r.mobile) {
                alert("重复的手机号。");
                isExit = true;
                return;
            }
        });
        html.push('<div class="layout-user" >');
        html.push('<user tel="' + r.mobile + '" class="badge badge-' + cssColor9[0] + '">');
        html.push(r.fullName);
        html.push('</user>');
        html.push('</div>');
        if (!isExit) {
            $('#task-content-tmp').html($('#task-content-tmp').html() + html.join(''));
        }

    } else {
        alert("请选择人员且手机号不能为空。");
    }
}
//删除
function del(rowid) {
    jQuery(cfg.grid_selector).jqGrid('delGridRow', rowid, {
        beforeShowForm: function (e) {
            var form = $(e[0]);
            form.closest('.ui-jqdialog')
                .find('.ui-jqdialog-titlebar')
                .wrapInner('<div class="widget-header" />');

        }
    })
}
//
function detail(rowid) {
    $.ajax({
        type: "post",
        url: contextPath + "/taskCmcc/selectById.do",
        data: {id: rowid},
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
            var html = new Array();
            var nodes = rst.value.tel.split(';');
            $.each($(nodes), function (i, o) {
                if (o.href != '') {
                    html.push('<div class="layout-user" >');
                    html.push('<user  class="badge badge-grey">');
                    html.push(o);
                    html.push('</user>');
                    html.push('</div>');
                }

            });
            $('#task-content').html(html.join(''));
            $('#msg-content').html(rst.value.msg);
        },
        complete: function (XMLHttpRequest, textStatus) {

        },
        error: function () {
        }
    });
}

