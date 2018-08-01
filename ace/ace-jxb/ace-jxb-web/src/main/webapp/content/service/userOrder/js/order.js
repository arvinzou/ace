window.onload = function () {
    getUserinfo();
    juicer.register('formaCategory', formaCategory);
    juicer.register('formaPayStatus', formaPayStatus);
    initpage();
}


function getUserinfo() {
    var url = "/jxb/counselor/getMyinfo"
    $.getJSON(url, function (result) {
        if (result.status == 0) {
            viewUserinfo(result.value);
        }
    })
}


function viewUserinfo(data) {
    for (key in data) {
        $('.user-' + key).text(data[key]);
    }
    ;
    $('.user-imagePhotoUrl').prop('src', data['imagePhotoUrl']);
}


function formaCategory(type) {
    switch (type) {
        case "1":
            return "咨询订单";
            break;
        case "2":
            return "课程订单";
            break;
    }
}

function formaPayStatus(type) {
    switch (type) {
        case "1":
            return "待支付";
            break;
        case "3":
            return "已付款";
            break;
        case "4":
            return "申请退款";
            break;
        case "5":
            return "已退款";
            break;
        case "6":
            return "结束/待评价";
            break;
        case "7":
            return "已完结";
            break;
        case "7":
            return "自动关闭";
            break;
    }
}

function detail(id) {
    var url = "/jxb/baseOrder/orderInfo"
    var data = {
        id: id
    }
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


function initpage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 20,
        pageSize: 20,
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
    var url = "/jxb/baseOrder/findMyOrderList";
    var data = {
        page: num,
        limit: 20
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            if (type == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: result.data.total,
                });
            }
            var navitem = document.getElementById('temp_orderList').innerHTML;
            var html = juicer(navitem, {
                data: result.data.list,
            });
            $("#orderList").html(html);

        }
    })
}



