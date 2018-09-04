window.onload = function () {
    initPage();
    $('.modify_btn').onclick(activeModify)
};

//分页组件
function initPage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 1,
        pageSize: 10,
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
        limit: 10
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

function modifyLevel(id, postId) {
    $('#counselorLevelModal').modal('show');
    var url = '/jxb/postLevel/findPostLevelList';
    var data = {
        star: 0,
        page: 100
    }
    $.post(url, data, function (result) {
        if (result.status == 0) {
            var navitem = document.getElementById('levelListModel').innerHTML;
            var html = juicer(navitem, {
                data: result.rows,
            });
            $("#levelList").html(html);
            $("#levelList").data('id', id);
            $("#levelList").data('postId', postId);
            $('#postId').val(postId)
            $('#counselorLevelModal').modal('show');
        }
    });
}

function activeModify() {
    var id = $("#levelList").data('id');
    var pId = $("#levelList").data('postId');
    var postId = $('#postId').val();
    if (postId == pId) {
        return;
    }
    var url = /jxb/
    postLevel / findPostLevelList
    '
}