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
    initClassList('s-cls-list');
});


function initClassList(ctrlId) {
    startLoad();
    $.ajax({
        url: contextPath + "/mailList/getClassList",
        type: "post",
        async: false,
        data: {},
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = result.value;
                var all = {id: "", name: "全部"};
                data.unshift(all);
                render('#' + ctrlId, data, 'tpl-cls-option');
                initGrid();
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
        initClassList('import-cls-list');
        alert("温馨提醒：在导入前，请先下载导入模板,并选择导入班级！");
        var clsId = $('#import-cls-list option:selected').val();//选中的值;
        importClsSelected(clsId);
    });
    //批量删除
    $('#modal-onoff').on('shown.bs.modal', function (event) {
        //可删除班级列表
        initRosterClsList();
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
 * 恢复
 * @param rowid 行ID
 */
function online(rowid) {
    if (confirm("确认恢复么？")) {
        startLoad();
        $.ajax({
            url: contextPath + '/enrollRoster/updateStatus',
            type: "post",
            async: false,
            data: {
                id: rowid,
                status: '1'
            },
            success: function (result) {
                stopLoad();
                if (result.status == 0) {
                    alert("恢复成功");
                    jQuery(cfg.grid_selector).jqGrid('setGridParam', {postData: params}).trigger("reloadGrid");
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
}

/**
 * 注销
 * @param rowid 行ID
 */
function offline(rowid) {
    if (confirm("确认注销么？")) {
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

                if (result.status == 0) {
                    alert("注销成功");
                    jQuery(cfg.grid_selector).jqGrid('setGridParam', {postData: params}).trigger("reloadGrid");
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

function importClsSelected(clsId) {
    reset_uploader({clsId: clsId});
}

function batchDel() {
    var clsId = $('#d-cls-list option:selected').val();//选中的值;
    if (confirm("确认删除选中班级数据么？")) {
        startLoad();
        $.ajax({
            url: contextPath + "/enrollRoster/batchDel",
            type: "post",
            async: false,
            data: {clsId: clsId},
            success: function (result) {
                console.log(result);
                stopLoad();
                jQuery(cfg.grid_selector).jqGrid('setGridParam', {postData: params}).trigger("reloadGrid");
            },
            error: function () {
                stopLoad();
                alert("对不起出错了！");
            }
        });
    }
}


function initRosterClsList() {
    startLoad();
    $.ajax({
        url: contextPath + "/enrollRoster/getListByCondition",
        type: "post",
        async: false,
        data: {},
        success: function (result) {
            console.log(result);
            stopLoad();
            render('#d-cls-list', result.rows, 'tpl-del-cls-option');
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}