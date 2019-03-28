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
                var form = $(e[0]);
                form.closest('.ui-jqdialog')
                    .find('.ui-jqdialog-titlebar')
                    .wrapInner('<div class = "widget-header" / > ');

            }
        })
    });
    //中控数据导入
    $('#btn-view-import').on('click', function () {
        $('#modal-upload').modal('show');
    });
    //数据导出
    $('#btn-view-export').on('click', function () {
        $('#modal-export').modal('show');
    });
    //初始化事件
    initEvents();
    initJQGrid();
    initJuicerMethod();
    initCondition();
});

var dtPickerOptions = {
    format: 'yyyy-mm-dd hh:ii:ss',
    language: 'zh-CN',
    minView: 'month',  //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
    weekStart: 1,
    todayBtn: true,//显示‘今日’按钮
    autoclose: true,
    todayHighlight: 1,
    startView: 2,
    clearBtn: true,//清除按钮
    forceParse: 0
}

function initDatetimepicker(id, options) {
    $("#" + id).datetimepicker(options);
    $('#' + id).focus(function () {
        $(this).blur();//不可输入状态
    });
}

function initCondition() {
    dtPickerOptions.format = 'yyyy-mm-dd';
    //
    initDatetimepicker('p-startDt', dtPickerOptions);
    initDatetimepicker('p-endDt', dtPickerOptions);
    //
    initDatetimepicker('ext-startDt', dtPickerOptions);
    initDatetimepicker('ext-endDt', dtPickerOptions);
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
        case '1':
            return "手机定位";
        case '2':
            return "中控数据";
        default:
            return "手机定位";
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
    $('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initPreview(id);
    })
    //查询条件
    initClassList('s-cls-list');
    //导入窗体
    $('#modal-import').on('shown.bs.modal', function (event) {
        var p = {};
        reset_uploader(p);
    });
    initExportModal();
}

//字符串判空
function strIsEmpty(obj) {
    if (typeof obj == "undefined" || obj == null || obj == "") {
        return true;
    } else {
        return false;
    }
}

/**
 * 验证时间有效性
 * @param val
 */
function validateAccTime(val) {
    var nowTime = Date.parse(new Date());
    var inputTime = Date.parse(val)
    if (inputTime > nowTime) {
        alert("时间不得大于当前系统时间!");
    }

}

function initExportModal() {
    //导出模态框
    $('#modal-export').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        initClassList('ext-cls-list');
    });
    //form submit
    $('#modal-export .btn-primary').on('click', function () {
        var clsId = $('#ext-cls-list option:selected').val();//选中的值;
        if (strIsEmpty(clsId)) {
            alert('请选择班次信息！');
            return false;
        }

        //     var startDt = $('#ext-startDt').val();
        //   var endDt = $('#ext-endDt').val();
        /*  if (strIsEmpty(endDt) || strIsEmpty(startDt)) {
              alert('请选择时间段！');
              return false;
          }*/
        //submit
        $('#modal-export form').submit();
    });
    $('#modal-export form').form({
        beforeSubmit: function (formData, jqForm, options) {
            $('#export_info').addClass("hide");
        },
        success: function (rst) {
            var obj = jQuery.parseJSON(rst);
            var htmlTxt = "导出成功";
            if (obj.status == 1) {
                htmlTxt = "<font style='color: red;'>" + obj.info + "</font>"
            } else {
                htmlTxt = "<font style='color: green;'>" + obj.info + "</font>"
            }
            $('#export_info').removeClass("hide").html(htmlTxt);
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
                initJQGrid();
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
    jQuery(cfg.grid_selector).jqGrid('editGridRow', rowid, {
        closeAfterAdd: true,
        recreateForm: true,
        viewPagerButtons: true,
        beforeShowForm: function (e) {
            var form = $(e[0]);
            form.closest('.ui-jqdialog')
                .find('.ui-jqdialog-titlebar')
                .wrapInner('<div class = "widget-header" / > ');

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