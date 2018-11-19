var loading = {};
var params = {limit: 20};
window.onload = function () {
    initPage();
    initEvents();
    initJuicerMethod();
}


/*提现申请记录初始化分页*/
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
/*提现申请记录条件查询*/
function t_query() {
    getPageList();
    return false;
}
function setParams(key, value) {
    params[key] = value;
    getPageList();
}
/*提现申请记录加载表格数据*/
function getPageList() {
    var url = contextPath + "/withdrawRecord/findWithdrawRecordList";
    params['teacherName'] = $("input[name=keyword]").val();
    startLoad();
    $.getJSON(url, params, function (rst) {
        stopLoad();
        if (rst.status == 0) {
            if (params.initType == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: rst.total
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
/*提现申请记录添加*/
function add(type) {
    window.location.href = 'add/index.jsp?id=' + urlParams.id;
}
/*提现申请记录编辑*/
function edit(did) {
    window.location.href = 'edit/index.jsp?id=' + urlParams.id + '&did=' + did;
}
/*查看详情*/
function detail(id) {
    var url = contextPath + "/withdrawRecord/selectWithdrawRecordByPrimaryKey";
    $.getJSON(url, {id: id}, function (result) {
        if (result.status == 0) {
            var navitem = document.getElementById('tpl-detail').innerHTML;
            var html = juicer(navitem, {data: result.value});
            $("#detail-info").html(html);
            $("#modal-detail").modal("show");
        }
    });
}

function transferInfo() {
    alert("尚未获得企业付款查询接口权限");
}

function initEvents() {
    /*企业付款查询*/
    // $('#modal-wx').on('click', function (event) {
    //     // var relatedTarget = $(event.relatedTarget);
    //     // var id = relatedTarget.data('id');
    //     // var title = relatedTarget.data('title');
    //     // var modal = $(this);
    //     // console.log(relatedTarget);
    //     alert("尚未获得企业付款查询接口权限");
    // });
    /*查看*/
    $('#modal-preview').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var title = relatedTarget.data('title');
        var modal = $(this);
        console.log(relatedTarget);
        initPreview(id);
    });
    //审核
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
/*提现申请记录审核*/
function audit(params) {
    startLoad();
    $.ajax({
        url: contextPath + "/withdrawRecord/audit",
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
/*提现申请记录上架*/
function online(id) {
    if (confirm("确定要上架吗？")) {
        startLoad();
        $.ajax({
            url: contextPath + "/withdrawRecord/updateStatus",
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
/*提现申请记录下架*/
function outline(id) {
    if (confirm("确定要下架吗？")) {
        startLoad();
        $.ajax({
            url: contextPath + "/withdrawRecord/updateStatus",
            type: "post",
            async: false,
            data: {
                id: id,
                status: '0'
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
    juicer.register('parseAuditRst', parseAuditRst);
    juicer.register('parseType', parseType);
    juicer.register('parseApiRst', parseApiRst);


}

function parseApiRst(val) {
    switch (val) {
        case "SUCCESS":
            return "成功";
        case "FAILED":
            return "失败";
        default:
            return "失败";
    }
}

function parseType(val) {
    switch (val) {
        case "1":
            return "微信提现";
        case "2":
            return "线下打款";
        default:
            return "微信提现";
    }
}

function parseAuditRst(val) {
    switch (val) {
        case "temp":
            return "待审";
        case "pass":
            return "通过";
        case "reject":
            return "驳回";
        default:
            return "待审";
    }
}

function initPreview(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/withdrawRecord/selectWithdrawRecordByPrimaryKey",
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
function initForm(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/withdrawRecord/selectWithdrawRecordByPrimaryKey",
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