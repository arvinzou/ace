var loading = {};
var params = {limit: 10};
window.onload = function () {
    initPage();
    initEvents();
    initJuicerMethod();
    type();

}


/*设备管理初始化分页*/
function initPage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 1,
        pageSize: params.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev" > <a href="javascript:;" > 上一页 < /a></li >',
        next: '<li class = "next" > <a href = "javascript:;" > 下一页 < /a></li>',
        page: '<li class = "page" > <a href = "javascript:;" > {{page}}</a></li>',
        onPageChange: function (num, type) {
            params['start'] = (num - 1) * params.limit;
            params['initType'] = type;
            getPageList();
        }
    });
}

function initBtnEvents() {
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

/*设备管理加载表格数据*/
function getPageList() {
    var url = contextPath + "/topDevice/findTopDeviceList";
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

/*设备管理添加*/
function add(type) {
    window.location.href = 'add/index.jsp?id=' + urlParams.id;
}

/*设备管理编辑*/
function edit(did) {
    window.location.href = 'edit/index.jsp?id=' + urlParams.id + '&did=' + did;
}

/*查看详情*/
function detail(id) {
    var url = contextPath + "/topDevice/selectTopDeviceByPrimaryKey";
    $.getJSON(url, {id: id}, function (result) {
        if (result.status == 0) {
            var navitem = document.getElementById('tpl-detail').innerHTML;
            var html = juicer(navitem, {data: result.value});
            $("#detail-info").html(html);
            $("#modal-detail").modal("show");
        }
    })
}

function initEvents() {
//初始化按钮组件
    initBtnEvents();

//模态框事件
﻿$('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initPreview(id);
    });
    $('#modal-upload').on('shown.bs.modal', function (event) {
        //加载班级列表
        alert("温馨提醒：在导入前，请先下载导入模板,并选择导入！");
        importInit();
    });
    $('#modal-audit').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initForm(id);
    });
    $('#modal-audit .audit').on('click', function () {
        $('#modal-audit form').submit();
    });
    $('#modal-audit form').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var params = {};
            $.each(formData, function (n, obj) {
                params[obj.name] = obj.value;
            });
            $.extend(params, {
                time: new Date()
            });
            console.log(params);
            audit(params);
            return false;
        }
    });
    $(".btn-group .btn").bind('click', function (event) {
        $(event.target).siblings().removeClass("active");
        console.log(event);
        $(event.target).addClass("active");
    });

    $('#btn-view-importXls').on('click', function () {
        importXls();
    });
}

function importInit() {
    reset_uploader();
}

function importXls() {
    // reset_uploader();
    $('#modal-upload').modal('show');

}
/*设备管理审核*/
function audit(params) {
    startLoad();
    $.ajax({
        url: contextPath + "/topDevice/audit",
        type: "post",
        async: false,
        data: params,
        success: function (rst) {
            stopLoad();
            $("#modal-audit").modal('hide');
            alert(rst.errorMessage);
            if (rst.status == 0) {
                getPageList();
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}

/*设备管理上架*/
function online(id) {
    if (confirm("确定要上线吗？")) {
        startLoad();
        $.ajax({
            url: contextPath + "/topDevice/updateStatus",
            type: "post",
            async: false,
            data: {
                id: id,
                status: '1'
            },
            success: function (rst) {
                stopLoad();
                if (rst.status == 0) {
                    getPageList();
                } else {
                    alert(rst.errorMessage);
                }
            },
            error: function () {
                stopLoad();
                alert("对不起，出错了！");
            }
        });
    }
}

/*设备管理下架*/
function outline(id) {
    if (confirm("确定要下线吗？")) {
        startLoad();
        $.ajax({
            url: contextPath + "/topDevice/updateStatus",
            type: "post",
            async: false,
            data: {
                id: id,
                status: '2'
            },
            success: function (rst) {
                stopLoad();
                if (rst.status == 0) {
                    getPageList();
                } else {
                    alert(rst.errorMessage);
                }
            },
            error: function () {
                stopLoad();
                alert("对不起，出错了！");
            }
        });
    }
}

//juicer自定义函数
function initJuicerMethod() {
    juicer.register('parseStatus', parseStatus);
}

/**
 * 状态
 * 0-删除
 * 1-暂存
 * 2-提交审核
 * 3-审核通过
 * 4-审核驳回
 */
function parseStatus(status) {
    switch (status) {
        case '0':
            return "删除";
        case '1':
            return "暂存";
        case '2':
            return "待审";
        case '3':
            return "通过";
        case '4':
            return "驳回";
        default:
            return "";
    }
}


function initPreview(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/topDevice/selectTopDeviceByPrimaryKey",
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
                data['dict'] = staticDictObject;
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

//juicer自定义函数
function initJuicerMethod() {
    juicer.register('parseType', parseType);
}

function parseType(type) {
    var typeList = staticDictObject['178'];
    for (var i = 0; i < typeList.length; i++) {
        if (type == typeList[i].CODE) {
            return typeList[i].NAME;
        }
    }
}

function type() {
    var data = staticDictObject['178'];
    var dataList = [];
    for (var i = 0; i < data.length; i++) {
        if (data[i].CODE != "") {
            dataList.push(data[i]);
        }
    }
    render('#type', dataList, 'type-tpl');
    console.log(dataList);
}

function initForm(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/topDevice/selectTopDeviceByPrimaryKey",
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
                render('#fm-audit', data, 'tpl-fm');
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

function del(did) {
    startLoad();
    var params = {};
    params.id = did;
    if (confirm("确定要删除吗？")) {
        $.ajax({
            url: contextPath + "/topDevice/deleteTopDeviceByTopDeviceId",
            type: "post",
            async: false,
            data: {
                jsons: JSON.stringify(params)
            },
            success: function (rst) {
                stopLoad();
                if (rst.status == 0) {
                    alert("删除成功！");
                    getPageList();
                }
            },
            error: function () {
                stopLoad();
                alert("对不起出错了！");
            }
        });
    }
}