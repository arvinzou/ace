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
                var form = $(e[0]);
                form.closest('.ui-jqdialog')
                    .find('.ui-jqdialog-titlebar')
                    .wrapInner('<div class = "widget-header" / > ');

            }
        })
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
    juicer.register('parseDate', parseDate);

}

function parseAttState(val) {
    var rst = "";
    switch (val) {
        case 'NO_SIGN' :
            rst = "缺勤";
            break;
        case 'ON_TIME' :
            rst = "正常";
            break;
        case 'BE_LATE' :
            rst = "迟到";
            break;
        case 'LEAVE_EARLY' :
            rst = "早退";
            break;
        default :
            rst = "缺勤";
    }
    return rst;
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

//字符串判空
function strIsEmpty(obj) {
    if (typeof obj == "undefined" || obj == null || obj == "") {
        return true;
    } else {
        return false;
    }
}

function initEvents() {
    //
    $('#ext-userType').change(function () {
        var p1 = $(this).children('option:selected').val();//这就是selected的值
        if (p1 == 'student') {
            $('#fm-export .cls-select').removeClass("hide");//显隐
        } else {
            $('#fm-export .cls-select').addClass("hide");//隐藏
        }
    });
    //
    $(".btn-group .btn").bind('click', function (event) {
        $(event.target).siblings().removeClass("active");
        console.log(event);
        $(event.target).addClass("active");
    });
    //查询条件
    initClassList('s-cls-list', '全部');
    //
    $('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initPreview(id);
    });
    //导入窗体
    $('#modal-import').on('shown.bs.modal', function (event) {
        var p = {};
        reset_uploader(p);
    });

    //原始记录导出
    initExportModal();
    //教职工报表
    initTeacherModal();
    //学员报表
    initStudentModal();
}


function initTeacherModal() {
    // tea-page-export
    $('#tea-page-export').on('click', function () {
        $("#teacher-report-table").tableExport({type: 'excel', fileName: '教职工考勤报表v' + getNowFormatDate()});
    });

    //教职工考勤报表数据查询
    $('#tea-report-search').on('click', function () {
        $('#fm-search-tea').ajaxForm({
            beforeSubmit: function (formData, jqForm, options) {
                var args = {};
                $.each(formData, function (n, obj) {
                    args[obj.name] = obj.value;
                });
                //参数验证
                if (isEmpty(args.queryDate)) {
                    alert("请选择查询日期！");
                    return false;
                }
                //去空格
                args.queryDate = replaceBlank(args.queryDate);
                // console.log(replaceChar(args.queryDate,"-",""));
                // return false;

                //ajax 查询
                startLoad();
                $.ajax({
                    url: contextPath + "/exportExcel/teacherReport",
                    type: "post",
                    async: false,
                    data: args,
                    success: function (rst) {
                        // console.log(JSON.stringify(rst));
                        stopLoad();
                        if (rst.status == 0) {
                            var data = {};
                            data['report'] = rst.data;
                            data['dateArray'] = args.queryDate.split(",");
                            render('#teacher-report-table', data, 'tpl-teacher-report');

                            $('#tea-page-export').removeClass("hide");
                        } else {
                            alert(rst.info);
                        }
                    },
                    error: function () {
                        stopLoad();
                        alert("对不起出错了！");
                    }
                });
                return false;
            }
        });
    });

    //按钮弹窗事件监听
    $('#btn-export-tea').on('click', function () {
        $('#modal-export-tea').modal('show');
    });
    //
    $('#modal-export-tea').on('show.bs.modal', function (event) {
        console.log("modal-export-tea.show()")
    });

}


function initStudentModal() {

    // tea-page-export
    $('#stu-page-export').on('click', function () {
        var clsName = $('#ext-cls-list-stu').find("option:selected").text();

        $("#student-report-table").tableExport({
            type: 'excel',
            fileName: '【' + clsName + '】学员考勤报表v' + getNowFormatDate()
        });
    });

    //教职工考勤报表数据查询
    $('#stu-report-search').on('click', function () {
        $('#fm-search-stu').ajaxForm({
            beforeSubmit: function (formData, jqForm, options) {
                var args = {};
                $.each(formData, function (n, obj) {
                    args[obj.name] = obj.value;
                });
                //参数验证
                if (isEmpty(args.classId)) {
                    alert("请选择班次！");
                    return false;
                }
                if (isEmpty(args.stuAttType)) {
                    alert("请选择考勤类别！");
                    return false;
                }
                if (isEmpty(args.queryDate)) {
                    alert("请选择查询日期！");
                    return false;
                }
                //去空格
                args.queryDate = replaceBlank(args.queryDate);

                //ajax 查询
                startLoad();
                $.ajax({
                    url: contextPath + "/exportExcel/studentReport",
                    type: "post",
                    async: false,
                    data: args,
                    success: function (rst) {
                        // console.log(JSON.stringify(rst));
                        stopLoad();
                        if (rst.status == 0) {
                            var data = {};
                            data['report'] = rst.data;
                            data['dateArray'] = args.queryDate.split(",");
                            render('#student-report-table', data, 'tpl-student-report');

                            $('#stu-page-export').removeClass("hide");
                        } else {
                            alert(rst.info);
                        }
                    },
                    error: function () {
                        stopLoad();
                        alert("对不起出错了！");
                    }
                });
                return false;
            }
        });
    });

    //按钮弹窗事件监听
    $('#btn-export-stu').on('click', function () {
        $('#modal-export-stu').modal('show');
    });
    //班次下拉选框
    initClassList('ext-cls-list-stu', '请选择班次');
    //
    $('#modal-export-stu').on('show.bs.modal', function (event) {
        console.log("modal-export-stu.show()")
    });
}

function initExportModal() {
    initClassList('ext-cls-list', '请选择班次');

    //导出模态框
    $('#modal-export').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
    });
    //form submit
    $('#modal-export .btn-primary').on('click', function () {
        $('#export_info').addClass("hide");//隐藏信息框
        var userType = $('#ext-userType option:selected').val();//选中的值;
        if (strIsEmpty(userType)) {
            alert('请选择身份类别！');
            return false;
        }
        var clsId = $('#ext-cls-list option:selected').val();//选中的值;
        if (strIsEmpty(clsId) && 'student' == userType) {
            alert('请选择班次信息！');
            return false;
        }
        var startDt = $('#ext-startDt').val();
        var endDt = $('#ext-endDt').val();
        if (strIsEmpty(endDt) || strIsEmpty(startDt)) {
            alert('请选择时间段！');
            return false;
        }
        //submit
        $('#modal-export form').submit();
    });
    $('#modal-export form').form({
        beforeSubmit: function (formData, jqForm, options) {
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

function initClassList(ctrlId, blankName) {
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
                var all = {id: "", name: blankName};
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
    console.log(params);
    jQuery(cfg.grid_selector).jqGrid('setGridParam', {postData: params}).trigger("reloadGrid");
}

function importXls(id) {
    reset_uploader({roadId: id});
    $('#modal-upload').modal('show');
}

function parser(s) {
    var t = Date.parse(s);
    if (!isNaN(t)) {
        return new Date(t);
    } else {
        return new Date(s + ":00:00");
    }
}

function parseDate(str) {
    // var s = replaceChar(str, '-', '');
    return str.substr(5, str.length);
}

/**
 * 去掉字符串中的空格
 * @param str 字符串源
 * @returns str
 */
function replaceBlank(str) {
    return str.replace(/\s+/g, "");
}

/**
 * 字符串替换所有指定字符
 * @param str 字符串源
 * @param oldChar 被搜索的子字符串。
 * @param newChar 用于替换的子字符串
 * @returns str
 */
function replaceChar(str, oldChar, newChar) {
    return str.replace(new RegExp(oldChar, 'g'), newChar);
}

/**
 * 字符串判空
 * @param str 字符串
 * @returns {boolean}
 */
function isEmpty(str) {
    if (str == '' || str == undefined || str == null) {
        return true;
    } else {
        return false;
    }
}

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "";
    var seperator2 = "";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + "" + date.getHours() + seperator2 + date.getMinutes()
        + seperator2 + date.getSeconds();
    return currentdate;
}




