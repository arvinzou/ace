var loading = {};
var params = {limit: 5};
window.onload = function () {
    initPage();
    initEvents();
    initJuicerMethod();
}


/*订单管理初始化分页*/
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
/*订单管理条件查询*/
function t_query() {
    getPageList();
    return false;
}
/*订单管理加载表格数据*/
function getPageList() {
    var url = contextPath + "/orderInfo/findOrderInfoList";
    params['name'] = $("input[name=keyword]").val();
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
/*订单管理添加*/
function add(type) {
    window.location.href = 'add/index.jsp?id=' + urlParams.id;
}
/*订单管理编辑*/
function edit(did) {
    window.location.href = 'edit/index.jsp?id=' + urlParams.id + '&did=' + did;
}
/*查看详情*/
function detail(id) {
    var url = contextPath + "/orderInfo/selectOrderInfoByPrimaryKey";
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
    $('#modal-audit').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var modal = $(this);
        modal.find('.modal-body input[name=id]').val(id);
    })
    $('#modal-audit .btn-primary').on('click', function () {
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


}
/*订单管理审核*/
function audit(params) {
    startLoad();
    $.ajax({
        url: contextPath + "/orderInfo/audit",
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
/*订单管理上架*/
function online(id) {
    if (confirm("确定要上架吗？")) {
        startLoad();
        $.ajax({
            url: contextPath + "/orderInfo/updateStatus",
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
/*订单管理下架*/
function outline(id) {
    if (confirm("确定要下架吗？")) {
        startLoad();
        $.ajax({
            url: contextPath + "/orderInfo/updateStatus",
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
    juicer.register('parseStatus', parseStatus);
    juicer.register('parseState', parseState);
    juicer.register('parsePayType', parsePayType);
    juicer.register('parseReceiveType', parseReceiveType);

}

/**
 * 收货类型
 * @param receiveType 1-自取2-配送
 * @returns {*}
 */
function parseReceiveType(receiveType) {
    switch (receiveType) {
        case '1':
            return "自取";
        case '2':
            return "配送";
        default:
            return "自取";
    }
}

/**
 * 付款方式
 * @param payType 1-积分兑换 2-微信支付 3-龙支付
 * @returns {*}
 */
function parsePayType(payType) {
    switch (payType) {
        case '1':
            return "积分兑换";
        case '2':
            return "微信支付";
        case '3':
            return "龙支付";
        default:
            return "未知支付类型";
    }
}

/**
 * 订单付款状态
 * @param state 1-待付款 2-已付款 3-已自提   4-已配送
 * @returns {*}
 */
function parseState(state) {
    switch (state) {
        case '1':
            return "待付款";
        case '2':
            return "已付款";
        case '3':
            return "已自提";
        case '4':
            return "已配送";
        default:
            return "";
    }
}

/**
 * 状态
 * 0-删除
 * 1-暂存
 */
function parseStatus(status) {
    switch (status) {
        case '0':
            return "已删除";
        case '1':
            return "暂存";
        default:
            return "暂存";
    }
}