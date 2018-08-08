window.onload = function (){
    initpage('1');    //初始化显示单品课程
}

function initpage(courseType) {
    $.jqPaginator('#pagination1', {
        totalCounts: 20,
        pageSize: 10,
        visiblePages: 20,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            getCourseList(num, type, courseType);
        }
    });
}

function getCourseList(num, type, courseType) {
    var url = contextPath+ "/course/findCourseList";
    var data = {
        page: num,
        limit: 10,
        type: courseType                  //1是单节课程，2是系列课程
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            if (type == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: result.total,
                });
            }
            viewHtml("courseList", result.rows, "list");
            viewHtml("makeCourse", result.rows[0], "makeTemp");
            viewHtml("createCourse", result.rows[0], "createTemp");
        }
    })
}

function viewHtml(IDom, data, tempId) {
    var navitem = document.getElementById(tempId).innerHTML;
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

function makeSeriesCourse(){
    var id = $('input[name="course"]:checked').val();
    if(id == '' || id == undefined){
        alert("请选择要制作的课程！");
        return;
    }
    window.location.href = contextPath + '/dynamic/service/course/makeSeries.jsp?id='+id;
}
function deleteCourse(id){
    $.ajax({
        url: contextPath + "/course/deleteCourseByCourseId",
        type:"post",
        async:false,
        data:{
            jsons:JSON.stringify({
               id: id
            })
        },
        success:function(result){
            if(result.status == 0) {
                alert("删除成功！");
                window.location.reload();
            }else {
                alert(result.errorMessage);
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function changeCourseType(type){
    initpage(type);
}
