jQuery(function ($) {
    //清空查询条件
    $('#btn-clear').on('click', function () {
        $('.form-control').val('');
        $('.combo-value').val('');
    });
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
                    .wrapInner('<div class="widget-header" />');

            }
        })
    });
    //批量导入
    $('#btn-view-import').on('click', function () {
        //加载导入
        importXls("123");
    });

//初始化事件
    initEvents();
    initJuicerMethod();
});

/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });

    $(obj).html(html);
}

function initEvents() {
    $('#modal-import').on('shown.bs.modal', function (event) {
        //
        selectClasses(true);
    });

    $('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initPreview(id);
    });
    //查询筛选列表
    $('#cls-condition').combogrid({
        panelWidth: 300,
        idField: 'id',
        textField: 'name',
        url: contextPath + '/classes/findClassesList',
        mode: 'remote',
        fitColumns: false,
        method: 'get', columns: [[
            {field: 'name', title: '班级名称', width: 100, align: 'right'},
            {field: 'headmasterName', title: '班主任', width: 100, align: 'right'}
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
        }
    });

    //班级数筛选列表
    // $('.easyui-combogrid').combogrid({
    $('#combogrid-cls-list').combogrid({
        panelWidth: 460,
        idField: 'id',
        textField: 'name',
        url: contextPath + '/classes/findClassesList',
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
    jQuery(cfg.grid_selector).jqGrid('editGridRow', rowid, {
        closeAfterAdd: true,
        recreateForm: true,
        viewPagerButtons: true,
        beforeShowForm: function (e) {
            var form = $(e[0]);
            form.closest('.ui-jqdialog')
                .find('.ui-jqdialog-titlebar')
                .wrapInner('<div class="widget-header" />');

            //readOnly: true,
            $("#mobile").attr("readOnly", true);
        }
    });
}

var show = false;
function del(rowid) {
    // jQuery(cfg.grid_selector).jqGrid('delGridRow', rowid, {
    //     beforeShowForm: function (e) {
    //         var form = $(e[0]);
    //         if (!show) {
    //             form.closest('.ui-jqdialog')
    //                 .find('.ui-jqdialog-titlebar')
    //                 .wrapInner('<div class = "widget-header" / > ');
    //         }
    //         show = true;
    //     }
    // });

    if (confirm("确认注销么？")) {
        var jsons = {id: rowid};
        startLoad();
        $.ajax({
            url: cfg.grid_delete_data_url,
            type: "post",
            async: false,
            data: {
                jsons: JSON.stringify(jsons),
            },
            success: function (result) {
                stopLoad();
                alert(result.errorMessage);
                jQuery(cfg.grid_selector).jqGrid('setGridParam', {postData: params}).trigger("reloadGrid");
            },
            error: function () {
                stopLoad();
                alert("对不起出错了！");
            }
        });
    }
}
var params = {};
function setParams(key, value) {
    params[key] = value;
    jQuery(cfg.grid_selector).jqGrid('setGridParam', {postData: params}).trigger("reloadGrid");
}

function importXls() {
    $('#modal-import').modal('show');
}

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

//juicer自定义函数
function initJuicerMethod() {
    juicer.register('parseStatus', parseStatus);
    juicer.register('parsePolitical', parsePolitical);
}

/**
 * 政治面貌
 * party-党员
 * normal-非党员
 */
function parsePolitical(val) {
    switch (val) {
        case 'party':
            return "党员";
        case 'normal':
            return "非党员";
        default:
            return "非党员";
    }
}

/**
 * 状态
 * 0-已注销
 * 1-有效
 */
function parseStatus(status) {
    switch (status) {
        case '0':
            return "已注销";
        case '1':
            return "有效";
        default:
            return "有效";
    }
}


function recover(rowid) {
    if (confirm("确认恢复么？")) {
        startLoad();
        $.ajax({
            url: cfg.grid_recover_url,
            type: "post",
            async: false,
            data: {
                id: rowid
            },
            success: function (result) {
                stopLoad();
                alert(result.errorMessage);
                jQuery(cfg.grid_selector).jqGrid('setGridParam', {postData: params}).trigger("reloadGrid");
            },
            error: function () {
                stopLoad();
                alert("对不起出错了！");
            }
        });
    }
}