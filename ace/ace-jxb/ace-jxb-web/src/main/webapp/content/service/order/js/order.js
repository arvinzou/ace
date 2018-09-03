window.onload = function () {
    juicer.register('formatCategory', formatCategory);
    juicer.register('formatPayStatus', formatPayStatus);
    juicer.register('formatCPntType', formatCPntType);
    initpage();
}
var category = 1, orderId = '';

function formatCPntType(type) {
    //   咨询类型(1-语音咨询 2-视频咨询 3-面对面咨询)
    switch (type) {
        case "1":
            return "语音咨询";
            break;
        case "2":
            return "视频咨询";
        case "3":
            return "面对面咨询";
            break;
    }
}

function formatCategory(type) {
    switch (type) {
        case "1":
            return "咨询订单";
            break;
        case "2":
            return "课程订单";
        case "3":
            return "评测订单";
            break;
    }
}

function formatPayStatus(type) {
    switch (type) {
        case "1":
            return "待支付";
            break;
        case "2":
            return "已付款";
            break;
        case "3":
            return "申请退款";
            break;
        case "4":
            return "已退款";
            break;
        case "5":
            return "结束/待评价";
            break;
        case "6":
            return "已完结";
            break;
        case "7":
            return "自动关闭";
            break;
    }
}

function detail(id) {
    var url = "/jxb/baseOrder/orderInfo";
    var data = {
        id: id
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            var navitem = document.getElementById('temp_orderInfo').innerHTML;
            var html = juicer(navitem, {
                data: result.data
            });
            $("#orderInfo").html(html);
            $("#orderInfoModal").modal("show");
        }
    })
}

function changeType(cType) {
    category = cType;
    initpage();
}


function searchByName() {
    orderId = $('input[name="orderId"]').val();
    initpage();
}


function initpage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 1,
        pageSize: 5,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            getOrderList(num, type);
        }
    });
}


function getOrderList(num, type) {
    var url = "/jxb/baseOrder/findBaseOrderListSecond";
    var data = {
        category: category,
        page: num,
        limit: 5
    }
    $.getJSON(url, data, function (result) {
        stopLoad();
        if (result.status == 0) {
            if (type == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: result.data.total,
                });
            }
            var navitem = document.getElementById('temp_orderList').innerHTML;
            var html = juicer(navitem, {
                orderCategory: category,
                data: result.data.list,
            });
            $("#orderList").html(html);

        }
    })
}



