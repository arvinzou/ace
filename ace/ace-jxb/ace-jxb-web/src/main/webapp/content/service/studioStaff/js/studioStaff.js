
window.onload = function () {
    // getUserinfo();
    initpage();
    getMyStudioList();
    $('#studioIdList').change(changeStudio)
}


function changeStudio() {
    var id = $("#studioIdList option:selected").val();
    $('#pagination1').jqPaginator('destroy');
    initpage(id);
}

function parseQueryString(prop) {
    var obj = {};
    var url = window.location.href;
    var start = url.indexOf("?") + 1;
    var str = url.substr(start);
    var arr = str.split("&");
    for (var i = 0; i < arr.length; i++) {
        var arr2 = arr[i].split("=");
        obj[arr2[0]] = arr2[1];
    }
    return obj[prop];
}


function getMyStudioList() {
    var url = "/jxb/studio/getMyStudioList";
    $.getJSON(url, function (result) {
        if (result.status == 0) {
            var navitem = document.getElementById('temp_studioIdList').innerHTML;
            var html = juicer(navitem, {
                data: result.data.my
            });
            $("#studioIdList").html(html);
            $("#studioIdList").val(parseQueryString("id"));
        }
    })
}


// function getUserinfo() {
//     var url = "/jxb/counselor/getMyinfo"
//     $.getJSON(url, function (result) {
//         if (result.status == 0) {
//             viewUserinfo(result.value);
//         }
//     })
// }


// function viewUserinfo(data) {
//     for (key in data) {
//         $('.user-' + key).text(data[key]);
//     }
//     ;
//     $('.user-imagePhotoUrl').prop('src', data['imagePhotoUrl']);
// }

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


function initpage(studioId) {
    $.jqPaginator('#pagination1', {
        totalCounts: 20,
        pageSize: 5,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            findMyCounselors(num, type, studioId);
        }
    });
}


function findMyCounselors(num, type, studioId) {
    studioId = studioId ? studioId : parseQueryString("id");
    var url = "/jxb/counselor/findStudioCounselors";
    var data = {
        studioId: studioId,
        page: num,
        limit: 5,
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



