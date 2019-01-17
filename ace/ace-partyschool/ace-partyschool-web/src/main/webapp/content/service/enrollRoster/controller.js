var params = {};
jQuery(function ($) {
//查询
    $('#btn-search').on('click', function () {
        $('#fm-search').ajaxForm({
            beforeSubmit: function (formData, jqForm, options) {
                // var params = {};
                $.each(formData, function (n, obj) {
                    params[obj.name] = obj.value;
                });
                $.extend(params, {
                    time: new Date()
                });
                jQuery(cfg.grid_selector).jqGrid('setGridParam', {
                    page: 1,
                    postData: params
                }).trigger("reloadGrid");
                return false;
            }
        });
    });
//添加
    $('#btn-view-add').on('click', function () {
        jQuery(cfg.grid_selector).jqGrid('editGridRow', 'new', {
            closeAfterAdd: true,
            recreateForm: true,
            viewPagerButtons: false,
            beforeShowForm: function (e) {
                var form = $(e[0]);
                form.closest('.ui-jqdialog')
                    .find('.ui-jqdialog-titlebar')
                    .wrapInner('<div class= "widget-header" / > ');

            }
        })
    });

    //批量导入
    $('#btn-view-import').on('click', function () {
        //加载导入
        $('#modal-import').modal('show');
    });
    $('#btn-view-onoff').on('click', function () {
        //show modal
        $('#modal-onoff').modal('show');
    });
//初始化事件
    initEvents();
    initJuicerMethod();
    initClassList();
});


function selectClasses(isFirst) {
    if (isFirst) {
        alert("温馨提醒：在导入前，请先下载导入模板,并选择导入班级！");
    }
    else {
        var g = $('#combogrid-cls-list').combogrid('grid'); // get datagrid object
        var r = g.datagrid('getSelected'); // get the selected row
        if (r && r.id) {
            reset_uploader({clsId: r.id});
        } else {
            alert("请选择班级信息！");
        }
    }
}

function initClassList() {
    startLoad();
    $.ajax({
        url: contextPath + "/mailList/getClassList",
        type: "post",
        async: false,
        data: {},
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data = result.value;
                render('#select1', data, 'tpl-select-list');
            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}

//juicer自定义函数
function initJuicerMethod() {
    juicer.register('rsd', rsd);
    juicer.register('parseStatus', parseStatus);
}

/**
 * 状态
 * 0-已注销
 * 1-有效
 */
function parseStatus(status) {
    switch (status) {
        case '0':
            return "已关闭";
        case '1':
            return "已开启";
        default:
            return "已关闭";
    }
}


/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });

    $(obj).html(html);
}

function initEvents() {
    //查看框
    $('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initPreview(id);
    });

    //导入功能
    $('#modal-import').on('shown.bs.modal', function (event) {
        //班次列表
        $('#combogrid-cls-list').combogrid({
            panelWidth: 460,
            idField: 'id',
            textField: 'name',
            url: contextPath + '/classes/findByQ',
            mode: 'remote',
            fitColumns: false,
            method: 'get', columns: [[
                {field: 'name', title: '班级名称', width: 150, align: 'right'},
                {field: 'headmasterName', title: '班主任', width: 150, align: 'right'}
            ]],
            keyHandler: {
                up: function () {
                },

                down: function () {
                },

                enter: function () {
                },
                query: function (q) {
                    $('#combogrid-cls-list').combogrid("grid").datagrid("reload", {'q': q});
                    $('#combogrid-cls-list').combogrid("setValue", q);
                }
            },
            onSelect: function (index, row) {
                selectClasses(false);
            }
        });
        //
        selectClasses(true);

    });

    //批量开启/关闭功能
    $('#modal-onoff').on('shown.bs.modal', function (event) {
        // 报名花名册中，所含班级
        $('#cls-list').combogrid({
            panelWidth: 460,
            idField: 'clsId',
            textField: 'clsViewName',
            url: contextPath + '/enrollRoster/getListByCondition',
            mode: 'remote',
            fitColumns: false,
            method: 'get', columns: [[
                {field: 'clsViewName', title: '班次名称', width: 150, align: 'right'},
                {field: 'headmasterName', title: '班主任姓名', width: 150, align: 'right'}
            ]],
            keyHandler: {
                up: function () {
                },

                down: function () {
                },

                enter: function () {
                },
                query: function (q) {
                    $('#cls-list').combogrid("grid").datagrid("reload", {'q': q});
                    $('#cls-list').combogrid("setValue", q);
                }
            },
            onSelect: function (index, row) {
            }
        });
    });
    //确认按钮提交事件
    $('#modal-onoff .btn-primary').on('click', function () {
        $('#modal-onoff form').submit();
    });
    /*监听表单提交*/
    $('#modal-onoff form').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            console.log("12222222222");

            var clsId = $('input[name="status"]:checked').val();
            var rstVal = $('#modal-onoff form input[name="status"]:checked').val();
            if (rstVal == undefined) {
                alert("请选择操作方式!");
                return;
            }
            var params = {};
            $.each(formData, function (n, obj) {
                params[obj.name] = obj.value;
            });
            $.extend(params, {
                time: new Date()
            });
            console.log(params);
            onoff(params);
            return false;
        }
    });
}


/*批量开启/关闭报名*/
function onoff(p) {
    startLoad();
    $.ajax({
        url: contextPath + "/enrollRoster/updateStatusByClsId",
        type: "post",
        async: false,
        data: p,
        success: function (rst) {
            stopLoad();
            $("#modal-onoff").modal('hide');
            alert(rst.errorMessage);
            if (rst.status == 0) {
                jQuery(cfg.grid_selector).jqGrid('setGridParam', {postData: params}).trigger("reloadGrid");
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}

function initPreview(id) {
    startLoad();
    $.ajax({
        url: cfg.view_load_data_url,
        type: "post",
        async: false,
        data: {
            id: id
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                render('#fm-preview', data, 'tpl-preview');
            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}


function edit(rowid) {
    console.log(rowid);
    //
    jQuery(cfg.grid_selector).jqGrid('setSelection', rowid);
    //
    jQuery(cfg.grid_selector).jqGrid('editGridRow', rowid, {
        closeAfterAdd: true,
        recreateForm: true,
        viewPagerButtons: true,
        beforeShowForm: function (e) {
            var form = $(e[0]);
            form.closest('.ui-jqdialog')
                .find('.ui-jqdialog-titlebar')
                .wrapInner('<div class= "widget-header" / > ');

        }
    });
}

/**
 * 关闭报名
 * @param rowid 行ID
 */
function offline(rowid) {
    if (confirm("确认关闭其报名权限么？")) {
        startLoad();
        $.ajax({
            url: contextPath + '/enrollRoster/updateStatus',
            type: "post",
            async: false,
            data: {
                id: rowid,
                status: '0'
            },
            success: function (result) {
                stopLoad();
                alert(result.errorMessage);
                if (rst.status == 0) {
                    jQuery(cfg.grid_selector).jqGrid('setGridParam', {postData: params}).trigger("reloadGrid");
                }
            },
            error: function () {
                stopLoad();
                alert("对不起出错了！");
            }
        });
    }
}

var show = false;
function del(rowid) {
    console.log(rowid);
    jQuery(cfg.grid_selector).jqGrid('delGridRow', rowid, {
        beforeShowForm: function (e) {
            var form = $(e[0]);
            if (!show) {
                form.closest('.ui-jqdialog')
                    .find('.ui-jqdialog-titlebar')
                    .wrapInner('<div class= "widget-header" / > ');
            }
            show = true;
        }
    });
}

function setParams(key, value) {
    params[key] = value;
    jQuery(cfg.grid_selector).jqGrid('setGridParam', {postData: params}).trigger("reloadGrid");
}

function importXls(id) {
    reset_uploader({roadId: id});
    $('#modal-upload').modal('show');

}