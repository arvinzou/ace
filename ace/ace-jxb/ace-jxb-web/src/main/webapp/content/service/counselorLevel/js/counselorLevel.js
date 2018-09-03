window.onload = function () {
    initPage();
};

//分页组件
function initPage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 1,
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
    startLoad();
    $.getJSON(url, data, function (result) {
        stopLoad();
        console.log("tatol:" + result.total);
        console.log(JSON.stringify(result));
        if (result.status == 0) {
            if (type == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: result.total,
                });
            }
            var navitem = document.getElementById('tmpl-data-list').innerHTML;
            var html = juicer(navitem, {
                data: result.rows,
            });
            console.log(html);
            $("#data-list").html(html);

        }
    })
}