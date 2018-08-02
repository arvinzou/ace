window.onload = function () {
    getUserinfo();
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

function detail(id) {
    var url = "/jxb/counselor/getCounselorInfo";
    var data = {
        id: id
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            var navitem = document.getElementById('temp_CounselorInfo1').innerHTML;
            var html = juicer(navitem, {
                data: result.value
            });
            $("#CounselorInfo1").html(html);
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
            findMyCounselors(num, type);
        }
    });
}


function findMyCounselors(num, type) {
    var url = "/jxb/counselor/findMyCounselors";
    var data = {
        page: num,
        limit: 20,
        name: '',
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            if (type == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: result.data.total,
                });
            }
            var navitem = document.getElementById('temp_studioCounselorList').innerHTML;
            var html = juicer(navitem, {
                data: result.data.list,
            });
            $("#studioCounselorList").html(html);

        }
    })
}



