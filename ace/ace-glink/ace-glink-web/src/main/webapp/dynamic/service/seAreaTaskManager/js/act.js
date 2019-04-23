var loading = {};
var params = {
    limit: 10,
};
window.onload = function () {

    initPage();
    initEvents();
    initJuicerMethod();
}


/*强电-任务管理初始化分页*/
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
            //区域筛选后，才能加载数据列表
            if ($.isEmptyObject(params.areaNodeID)) {
                console.log("未加载区域数据！~");
            } else {
                getPageList();
            }
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

function execute(areaNodeID, taskNo) {
    if (confirm("确认执行该任务么？")) {
        startLoad();
        $.ajax({
            url: contextPath + "/seAreaTask/exeTask",
            type: "post",
            async: false,
            data: {
                areaNodeID: areaNodeID,
                taskNo: taskNo
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

function setParams(key, value) {
    params[key] = value;
    getPageList();
}

/*强电-任务管理加载表格数据*/
function getPageList() {
    var url = contextPath + "/seAreaTask/findSeAreaTaskList";
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
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}

/*查看详情*/
function detail(id) {
    var url = contextPath + "/seAreaTask/selectseAreaTaskByPrimaryKey";
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

    $("#areaNodeID").combotree({
        onChange: function (newValue, oldValue) {
            setParams("areaNodeID", newValue);
        }
    });

}

//juicer自定义函数
function initJuicerMethod() {
    juicer.register('rsd', rsd);
    juicer.register('parseExeState', parseExeState);
}

/**
 * 状态解析
 */
function parseExeState(val) {
    switch (val) {
        case 'ok':
            return "已执行";
        case 'error':
            return "未执行";
        default:
            return "未执行";
    }
}

