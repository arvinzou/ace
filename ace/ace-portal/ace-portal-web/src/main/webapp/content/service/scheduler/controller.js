var params = {};
jQuery(function ($) {
    //查询
    $('#btn-search').on('click', function () {
        $('#fm-search').ajaxForm({
            beforeSubmit: function (formData, jqForm, options) {

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

            },
        })
    });

    //初始化事件
    initEvents();
});

function initSwitchCtrl(eid) {
    $(eid).bootstrapSwitch({
        onText: "ON",
        offText: "OFF",
        onColor: "success",
        offColor: "info",
        size: "Mini",
        onSwitchChange: function (event, state) {
            if (state == true) {
                alert('已打开');
            } else {
                alert('已关闭');
            }
        }
    });
}

function renderSwitch() {
    console.log("123123123");
    initSwitchCtrl('#validState');
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
    $('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initPreview(id);
        var data = {};
        data.key = 'category';
        data.list = staticDictObject['170'];
        render($("#check-group-category"), data, "tpl-check-group");

    })
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
                .wrapInner("<div class='widget-header'/>");

        }
    });
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
                    .wrapInner("<div class='widget-header'/>");
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

function parseValidState(val) {
    var rst = "";
    switch (val) {
        case '1' :
            rst = "已生效";
            break;
        case '0' :
            rst = "已失效";
            break;
        default :
            rst = "N/A";
    }
    return rst;
}

function updateValidState(rowid, state) {
    startLoad();
    if (confirm("非要这么做么，您可考虑清楚了?")) {
        $.ajax({
            url: portalPath + '/scheduler/updateValidState',
            type: "post",
            async: false,
            data: {
                id: rowid,
                state: state
            },
            success: function (result) {
                stopLoad();
                if (result.status == 0) {
                    var data = {};
                    data['o'] = result.value;
                    setParams('', '');
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
