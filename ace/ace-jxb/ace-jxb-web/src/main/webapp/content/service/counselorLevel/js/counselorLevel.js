window.onload = function () {
    initpage()

};

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
            getChecklist(num, type);
        }
    });
}


function getChecklist(num, type) {
    var url = "/jxb/baseOrder/findBaseOrderListSecond";
    var data = {
        category: category,
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
                orderCategory: category,
                data: result.data.list,
            });
            $("#orderList").html(html);

        }
    })
}
