jQuery(function ($) {
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
            appendUploadBtn("photoUrl");
            appendUploadBtn("fileUrl");
            }
        })
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
    $('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initPreview(id);
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

function loadText(id) {
    $.ajax({
        type: "post",
        url: cfg.view_load_data_url,
        data: {
            id: id
        },
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
        },
        error: function () {
            alert("加载错误！");
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
                appendUploadBtn("photoUrl");
                appendUploadBtn("fileUrl");
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
                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
            }
            show = true;
        }
    });
}

function setParams(key, value) {
    params[key] = value;
    jQuery(cfg.grid_selector).jqGrid('setGridParam', {postData: params}).trigger("reloadGrid");
}

function initJuicerMethod() {
    juicer.register('parseStatus', parseStatus);
}
function parseStatus(val) {
    switch (val) {
        case '0':
            return "注销";
        case '1':
            return "正常";
    }
}