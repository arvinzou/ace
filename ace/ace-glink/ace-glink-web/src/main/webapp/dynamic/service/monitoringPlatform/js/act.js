var loading = {};
var params = {limit: 10};
window.onload = function () {
    // initPage();
    initEvents();
    initJuicerMethod();
    initCondition();
};


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
function initCondition() {
    dtPickerOptions.format = 'yyyy-mm-dd';
    //
    initDatetimepicker('p-startDt', dtPickerOptions);
    initDatetimepicker('p-endDt', dtPickerOptions);
}

/*控制器映射关系初始化分页*/
function initPage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 1,
        pageSize: params.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: "<li class='prev'><a href='javascript:;'>上一页</a></li>",
        next: "<li class='next'><a href='javascript:;'>下一页</a></li>",
        page: "<li class='page'><a href='javascript:;'>{{page}}</a></li>",
        onPageChange: function (num, type) {
            params['start'] = (num - 1) * params.limit;
            params['initType'] = type;
            getPageList();
        }
    });

    $('#fm-search').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            $.each(formData, function (n, obj) {
                params[obj.name] = obj.value;
            });
            params['initType'] = 'init';
            params['start'] = 0;
            getPageList();
            return false;
        }
    });
}

function setParams(key, value) {
    params[key] = value;
    getPageList();
}

/*控制器映射关系加载表格数据*/
function getPageList() {
    var url = contextPath + "/mapCtrlBuilding/findMapCtrlBuildingList";
    params['name'] = $("input[name=keyword]").val();
    startLoad();
    $.getJSON(url, params, function (rst) {
        stopLoad();
        if (rst.status == 0) {
            if (params.initType == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: rst.total == 0 ? 1 : rst.total,
                    currentPage: 1
                });
            }
            render($("#page-list"), rst.rows, "tpl-list");
        }
    })
}

/*页面渲染*/
// function render(obj, data, tplId) {
//     var tpl = document.getElementById(tplId).innerHTML;
//     var html = juicer(tpl, {
//         data: data,
//     });
//     $(obj).html(html);
// }


/*控制器映射关系添加*/
// function add(type) {
//     window.location.href = 'add/index.jsp?id=' + urlParams.id;
// }

/*控制器映射关系编辑*/
// function edit(did) {
//     window.location.href = 'edit/index.jsp?id=' + urlParams.id + '&did=' + did;
// }

/*查看详情*/
// function detail(id) {
//     var url = contextPath + "/mapCtrlBuilding/selectMapCtrlBuildingByPrimaryKey";
//     $.getJSON(url, {id: id}, function (result) {
//         if (result.status == 0) {
//             var navitem = document.getElementById('tpl-detail').innerHTML;
//             var html = juicer(navitem, {data: result.value});
//             $("#detail-info").html(html);
//             $("#modal-detail").modal("show");
//         }
//     })
// }

function importInit() {
    reset_uploader();
}

// function importXls() {
//     $('#modal-import').modal('show');
// }


function initEvents() {



}

// /*删除*/
// function del(id) {
//     var args = {id: id};
//     startLoad();
//     $.ajax({
//         url: contextPath + "/mapCtrlBuilding/deleteMapCtrlBuildingByMapCtrlBuildingId",
//         type: "post",
//         async: false,
//         data: {jsons: JSON.stringify(args)},
//         success: function (rst) {
//             stopLoad();
//             alert(rst.errorMessage);
//             if (rst.status == 0) {
//                 getPageList();
//             }
//         },
//         error: function () {
//             stopLoad();
//             alert("对不起出错了！");
//         }
//     });
// }
//
// /*控制器映射关系上架*/
// function online(id) {
//     if (confirm("确定要上架吗？")) {
//         startLoad();
//         $.ajax({
//             url: contextPath + "/mapCtrlBuilding/updateStatus",
//             type: "post",
//             async: false,
//             data: {
//                 id: id,
//                 status: '1'
//             },
//             success: function (rst) {
//                 stopLoad();
//                 if (rst.status == 0) {
//                     getPageList();
//                 } else {
//                     alert(rst.errorMessage);
//                 }
//             },
//             error: function () {
//                 stopLoad();
//                 alert("对不起，出错了！");
//             }
//         });
//     }
// }
//
// /*控制器映射关系下架*/
// function outline(id) {
//     if (confirm("确定要下架吗？")) {
//         startLoad();
//         $.ajax({
//             url: contextPath + "/mapCtrlBuilding/updateStatus",
//             type: "post",
//             async: false,
//             data: {
//                 id: id,
//                 status: '0'
//             },
//             success: function (rst) {
//                 stopLoad();
//                 if (rst.status == 0) {
//                     getPageList();
//                 } else {
//                     alert(rst.errorMessage);
//                 }
//             },
//             error: function () {
//                 stopLoad();
//                 alert("对不起，出错了！");
//             }
//         });
//     }
// }

//juicer自定义函数
// function initJuicerMethod() {
//     juicer.register('rsd', rsd);
//     juicer.register('parseStatus', parseStatus);
// }

/**
 * 状态解析
 */
// function parseStatus(status) {
//     switch (status) {
//         case '0':
//             return "删除";
//         case '1':
//         default:
//             return "0";
//     }
// }

// function initPreview(id) {
//     startLoad();
//     $.ajax({
//         url: contextPath + "/mapCtrlBuilding/selectMapCtrlBuildingByPrimaryKey",
//         type: "post",
//         async: false,
//         data: {
//             id: id
//         },
//         success: function (result) {
//             stopLoad();
//             if (result.status == 0) {
//                 var data = {};
//                 data['o'] = result.value;
//                 render('#fm-preview', data, 'tpl-preview');
//             } else {
//                 alert(result.errorMessage);
//             }
//         },
//         error: function () {
//             stopLoad();
//             alert("对不起出错了！");
//         }
//     });
// }

// function initForm(id) {
//     startLoad();
//     $.ajax({
//         url: contextPath + "/mapCtrlBuilding/selectMapCtrlBuildingByPrimaryKey",
//         type: "post",
//         async: false,
//         data: {
//             id: id
//         },
//         success: function (result) {
//             stopLoad();
//             if (result.status == 0) {
//                 var data = {};
//                 data['o'] = result.value;
//                 render('#fm-audit', data, 'tpl-fm');
//             } else {
//                 alert(result.errorMessage);
//             }
//         },
//         error: function () {
//             stopLoad();
//             alert("对不起出错了！");
//         }
//     });
// }
