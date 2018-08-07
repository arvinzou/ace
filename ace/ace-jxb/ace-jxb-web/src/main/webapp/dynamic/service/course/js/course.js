window.onload = function (){

    initpage();
}

function initpage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 20,
        pageSize: 10,
        visiblePages: 20,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            getCourseList(num, type);
        }
    });
}

function getCourseList(num, type) {
    var url = contextPath+ "/course/findCourseList";
    var data = {
        page: num,
        limit: 10,
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            if (type == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: result.total,
                });
            }
            viewHtml("courseList", result.rows);
        }
    })
}

function viewHtml(IDom, data) {
    var navitem = document.getElementById('list').innerHTML;
    var html = juicer(navitem, {
        data: data,
    });
    $("#" + IDom).html(html);
}

function makecourse(){
    var id = $('input[name="course"]:checked').val();
    if(id == '' || id == undefined){
        alert("请选择要制作的课程！");
        return;
    }
    window.location.href = contextPath + '/dynamic/service/course/make.jsp?id='+id;
}