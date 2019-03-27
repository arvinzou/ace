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
                    .wrapInner('<div class = "widget-header" / > ');
                //
                appendUploadBtn("photoUrl");
            }
        })
    });
    //初始化按钮事件
    initBtnEvent();
    //初始化事件
    initEvents();
    //
    initJuicerMethod();
});

function initBtnEvent() {
    $("#btn-view-sort").on('click', function (e) {
        e.preventDefault();
        $('#modal-sort').modal('show');
        getSortList();
    });
}

function getSortList() {
    $.ajax({
        type: "post",
        url: cfg.grid_load_data_url,
        data: {status: 1, start: 0, limit: 9999},
        success: function (rst, textStatus) {
            console.log(rst);
            var html = [];
            $(rst.rows).each(function (i, o) {
                html.push('<li draggable="false" data-id="' + o.id + '" data-name="' + o.name + '">' + o.name + '</li>');
            });

            $(".sortable").html(html.join(""));
            var el = document.getElementById('sortable');// $("#sortable");//
            var sortable = Sortable.create(el, {
                group: "words",
                animation: 150,
                onAdd: function (evt) {
                    console.log('onAdd.bar:', evt.item);
                },
                onUpdate: function (evt) {
                    console.log('onUpdate.bar:', evt.item);
                },
                onRemove: function (evt) {
                    console.log('onRemove.bar:', evt.item);
                },
                onStart: function (evt) {
                    console.log('onStart.foo:', evt.item);
                },
                onEnd: function (evt) {
                    console.log('onEnd.foo:', evt.item);
                    console.log(sortable.toArray());
                    updateSort(sortable.toArray());
                }
            });

        },
        error: function () {
            alert("对不起，出差了。");
        }
    });
}


function updateSort(arr) {
    var data = [];
    for (var i = 0; i < arr.length; i++) {
        data.push({id: arr[i], index: i});
    }
    $.ajax({
        type: "post",
        url: contextPath + "/teacher/updateSort",
        data: {json: JSON.stringify(data)},
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
            if (!rst.state) {
                alert(rst.errorMessage);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {

        },
        error: function () {

        }
    });
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
    })
    $(".btn-group .btn").bind('click', function (event) {
        $(event.target).siblings().removeClass("active");
        console.log(event);
        $(event.target).addClass("active");
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

            appendUploadBtn("photoUrl");

            //readOnly: true,
            // $("#mobile").attr("readOnly", true);
        }
    });
}

var show = false;

function del(rowid) {
    // jQuery(cfg.grid_selector).jqGrid('delGridRow', rowid, {
    //     beforeShowForm: function (e) {
    //         var form = $(e[0]);
    //         if (!show) {
    //             form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
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

var params = {
    status: '1'
};

function setParams(key, value) {
    params[key] = value;
    jQuery(cfg.grid_selector).jqGrid('setGridParam', {postData: params}).trigger("reloadGrid");
}

function recover(rowid) {
    if (confirm("确认审核通过么？")) {
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

//juicer自定义函数
function initJuicerMethod() {
    juicer.register('parseStatus', parseStatus);
    juicer.register('rsd', rsd);
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