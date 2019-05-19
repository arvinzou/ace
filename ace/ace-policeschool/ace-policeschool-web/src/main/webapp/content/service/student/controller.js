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
        importXls();
    });

//初始化事件
    initEvents();
    initJuicerMethod();
});

function clearAreaCode() {
    $('#p-areaCode').combotree('setValue', '');
    setParams('areaCode', '');
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
    $('#modal-import').on('shown.bs.modal', function (event) {
        //加载班级列表 -- 导入列表
        initImportClassList('d-cls-list');
        alert("温馨提醒：在导入前，请先下载导入模板,并选择导入班级！");
        var clsId = $('#d-cls-list option:selected').val();//选中的值;
        importInit(clsId);
    });

    $('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initPreview(id);
    });
    //班级下拉筛选列表 -- 查询班级列表
    initClassList('s-cls-list');
    $(".btn-group .btn").bind('click', function (event) {
        $(event.target).siblings().removeClass("active");
        console.log(event);
        $(event.target).addClass("active");
    });
}

function initImportClassList(ctrlId) {
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
                var all = {id: "", name: ""};
                data.unshift(all);
                render('#' + ctrlId, data, 'tpl-cls-option');
                params.category = '2';
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

function initClassList(ctrlId) {
    startLoad();
    $.ajax({
        url: contextPath + "/classes/getClassList",
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
                params.category = '2';
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
    jQuery(cfg.grid_selector).jqGrid('setSelection', rowid);

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
            //$("#mobile").attr("readOnly", true);
        }
    });
}

var show = false;

function del(rowid) {

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

function importInit(clsId) {
    reset_uploader({clsId: clsId});
}

//juicer自定义函数
function initJuicerMethod() {
    juicer.register('parseStatus', parseStatus);
    juicer.register('parsePolitical', parsePolitical);
    juicer.register('rsd', rsd);
    juicer.register('formatDate', formatDate);
}

function formatDate(date) {
    if (null == date) {
        return "";
    }
    return date.substr(0, 10);
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
            return "注销";
        case '1':
            return "正常";
        default:
            return "正常";
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