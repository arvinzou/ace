var loading = {};
var params = {limit: 10};
window.onload = function () {
    initPage();
    initEvents();
    initJuicerMethod();
}


/*配电箱数据初始化分页*/
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

/*配电箱数据加载表格数据*/
function getPageList() {
    var url = contextPath + "/seNode/findSeNodeList";
    params['nodeID'] = $("input[name=keyword]").val();
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
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}

function initEvents() {
    $("#areaNodeID").combotree({
        onChange: function (newValue, oldValue) {
            setParams("areaNodeID", newValue);
        }
    });

    $('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        console.log(relatedTarget);
        initPreview(id);
    });

    $('#modal-monitor').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var nodeID = relatedTarget.data('id');
        //ajax data
        initMonitor(nodeID);
    });

}

function showMonitorDeviceCH(deviceCode, chName) {
    startLoad();
    $.ajax({
        url: contextPath + "/seNode/getMonitorDeviceCH",
        type: "post",
        async: false,
        data: {
            deviceCode: deviceCode,
            chName: chName
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                render('#fm-monitor-dch', data, 'tpl-monitor-dch');
                //
                $("#modal-monitor-dch").modal('show');
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

function initMonitor(nodeID) {
    startLoad();
    $.ajax({
        url: contextPath + "/seNode/getMonitorData",
        type: "post",
        async: false,
        data: {nodeID: nodeID},
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                data['monitorDeviceList'] = result.value.monitorDeviceList;
                render('#fm-monitor', data, 'tpl-monitor');
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

/*配电箱数据同步*/
function syncData() {
    if (confirm("配电箱基础数据即将更新，是否继续？")) {
        startLoad();
        $.ajax({
            url: contextPath + "/seNode/syncData",
            type: "post",
            async: false,
            data: {},
            success: function (rst) {
                stopLoad();
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
}

//juicer自定义函数
function initJuicerMethod() {
    juicer.register('rsd', rsd);
    juicer.register('parseStatus', parseStatus);
    juicer.register('parseOFState', parseOFState);
    juicer.register('parseCHState', parseCHState);

}

function parseCHState(val) {
    // 1-开、0-关、2-未知
    switch (val) {
        case 1:
            return '开';
        case 0:
            return '关';
        case 2:
            return '未知';
        default:
            return '未知';
    }
}

function parseOFState(val) {
    // 1在线，0离线
    switch (val) {
        case 1:
            return '在线';
        case 0:
            return '离线';
        default:
            return '离线';
    }
}

/**
 * 状态解析
 */
function parseStatus(status) {
    switch (status) {
        case '0':
            return "删除";
        case '1':
        default:
            return "0";
    }
}

function initPreview(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/seNode/selectSeNodeByPrimaryKey",
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
                data['deviceList'] = result.value.deviceList;
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

function initForm(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/seNode/selectSeNodeByPrimaryKey",
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


/*配电箱数据同步*/
function syncMonitorData() {
    if (confirm("配电箱监控数据即将更新，是否继续？")) {
        startLoad();
        $.ajax({
            url: contextPath + "/seNode/syncMonitorData",
            type: "post",
            async: false,
            data: {},
            success: function (rst) {
                stopLoad();
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

}

//树形数据搜索清除
function clearQparams() {
    $('#areaNodeID').combotree('setValue', '');
}
