window.onload = function () {

    initPage();
};

//分页组件
function initPage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 20,
        pageSize: 20,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            getDataList(num, type);
        }
    });
}

function getDataList(num, type) {
    var url = "/jxb/postLevel/findCounselorLevelList";
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
            var navitem = document.getElementById('tmpl-data-list').innerHTML;
            var html = juicer(
                navitem,
                {
                    data: result.data.list,
                }
            );

            $("#data-list").html(html);

        }
    })
}