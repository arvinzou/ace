jQuery(function ($) {
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

    $("input[name=startDate]").datetimepicker({
        format: 'yyyy-mm-dd hh:mm',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,//显示‘今日’按钮
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 'hour',  //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
        clearBtn: true,//清除按钮
        forceParse: 0
    });
    $('input[name=startDate]').focus(function () {
        $(this).blur();//不可输入状态
    });


    $("input[name=endDate]").datetimepicker({
        format: 'yyyy-mm-dd hh:mm',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,//显示‘今日’按钮
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 'hour',  //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
        clearBtn: true,//清除按钮
        forceParse: 0
    });
    $('input[name=endDate]').focus(function () {
        $(this).en();//不可输入状态
    });

    $('#btn-view-add').on('click', function () {
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

});

function preview(id, title) {
    $('#modal-preview').modal('show');

    loadView(id);
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
                if (key.indexOf('Date') != -1 ||
                    key.indexOf('time') != -1 ||
                    key.indexOf('Time') != -1 ||
                    key.indexOf('birthday') != -1) {
                    //value = Common.DateFormatter(value);
                }
                if (key == 'needReceipt') {
                    var rst = "";
                    switch (value) {
                        case '1' :
                            rst = "开票";
                            break;
                        case '0' :
                            rst = "不开票";
                            break;
                        default :
                            rst = "不开票";
                    }
                    value = rst;
                }
                $("#modal-preview").find('#' + key).html(value);
            });
        },
        error: function () {
            alert("加载错误！");
        }
    });
}

function edit(rowid) {
    console.log(rowid);
    jQuery(cfg.grid_selector).jqGrid(
        'editGridRow',
        rowid,
        {
            closeAfterAdd: true,
            recreateForm: true,
            viewPagerButtons: true,
            beforeShowForm: function (e) {
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find(
                    '.ui-jqdialog-titlebar').wrapInner(
                    '<div class="widget-header" />')

            }
        });
}
var show = false;
function del(rowid) {
    console.log(rowid);
    jQuery(cfg.grid_selector).jqGrid('delGridRow',
        rowid,
        {
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
