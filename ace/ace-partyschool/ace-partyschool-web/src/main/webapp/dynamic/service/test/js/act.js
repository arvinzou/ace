var loading = {};
var params = {limit: 10};
window.onload = function () {
    juicer.register('parseIntF', parseIntF);
    juicer.register('formatIndex', formatIndex);
    juicer.register('formatScore', formatScore);
    initPage();
    initEvents();
}


/*测评结果管理初始化分页*/
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
            getPageList();
        }
    });

}

/**计算并取整*/
function parseIntF(num) {
    return parseInt(num * 0.9);
}

/**计算序列*/
function formatIndex(num) {
    return index = (parseInt(num) + 1);
}

/**计算序列*/
function formatScore(num) {
    if (parseFloat(num) > 0)
        return '(' + num + '分)';
}


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

function setParams(key, value) {
    params[key] = value;
    getPageList();
}

/*测评结果管理加载表格数据*/
function getPageList() {
    var url = contextPath + "/test/findTestList";
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

/*测评结果管理添加*/
function add(type) {
    window.location.href = 'add/index.jsp?id=' + urlParams.id;
}

/*测评结果管理编辑*/
function edit(did) {
    window.location.href = 'edit/index.jsp?id=' + urlParams.id + '&did=' + did;
}

/*查看详情*/
function detail(id) {
    var url = contextPath + "/test/selectTestByPrimaryKey";
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
    $('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initPreview(id);
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
}

/*测评结果管理审核*/
function audit(params) {
    startLoad();
    $.ajax({
        url: contextPath + "/test/audit",
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

/*测评结果管理上架*/
function online(id) {
    if (confirm("确定要上架吗？")) {
        startLoad();
        $.ajax({
            url: contextPath + "/test/updateStatus",
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

function del(id) {
    updataStatus(id, '0');
}


function del(id) {
    if (confirm("确定要删除吗？")) {
        updataStatus(id, '0');
    }
}

function storage(id) {
    updataStatus(id, '1');
}

function release(id) {
    if (confirm("确定要发布吗？")) {
        updataStatus(id, '2');
    }
}

/*测评结果管理下架*/
function updataStatus(id, status) {
    startLoad();
    $.ajax({
        url: contextPath + "/test/updateStatus",
        type: "post",
        async: false,
        data: {
            id: id,
            status: status,
        },
        success: function (rst) {
            stopLoad();
            if (rst.status == 0) {
                getPageList();
            } else {
                alert(rst.errorMessage);
            }
        }
        ,
        error: function () {
            stopLoad();
            alert("对不起，出错了！");
        }
    });
}


function initPreview(id) {
    startLoad();
    var url=contextPath + "/topic/findTopicFullList";
    var data={
        testId:id
    }
    $.getJSON(url,data,function (rst) {
        stopLoad();
        if(rst.status==0){
            render('#fm-preview',rst.data,'tpl-preview');
        }
        else {
            alert("获取用户信息失败！");
        }
    })




}

function initForm(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/test/selectTestByPrimaryKey",
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