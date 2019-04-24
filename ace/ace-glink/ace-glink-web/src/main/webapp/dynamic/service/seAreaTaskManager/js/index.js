var timer;
$(window).resize(function () {   //当浏览器大小变化时
    clearTimeout(timer);
    timer = setTimeout(function () {
        window.location.reload();
    }, 500)
});
var params = {
    start: 0,
    limit: 10
}

$(function () {
    initPage();
    initEvents();
    initJuicerMethod();
});


/*分页*/
function initPage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 1,
        pageSize: params.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
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
                console.log(obj);
            });
            params['initType'] = 'init';
            params['start'] = 0;
            getPageList();
            return false;
        }
    });
}

function initEvents() {
    $("#areaNodeID").combotree({
        onChange: function (newValue, oldValue) {
            setParams("areaNodeID", newValue);
        }
    });


}

function setParams(key, value) {
    params[key] = value;
    getPageList();
}

/*添加渲染*/
function renderAdd(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).append(html);
}


/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}

//juicer自定义函数
function initJuicerMethod() {
    juicer.register('parseStatus', parseStatus);
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


/**
 * 状态解析
 */
function parseStatus(status) {
    switch (status) {
        case 0:
            return "无效";
        case 1:
            return "有效";
        default:
            return "0";
    }
}


/*ajax获取数据列表*/
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
    });
}


function execute(areaNodeID, taskNo) {
    console.log("areaNodeID=" + areaNodeID + ",taskNo=" + taskNo)
    //
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
                //
                alert(rst.errorMessage);
                if (rst.status == 0) {
                    getPageList();
                }
            },
            error: function () {
                stopLoad();
                alert("对不起，出错了！");
            }
        });
    }
}
