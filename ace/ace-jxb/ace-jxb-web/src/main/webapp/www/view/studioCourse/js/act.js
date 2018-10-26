window.onload = function(){
    console.log(window.location.href);
    var url =   window.location.search.substring(1);
    var primaryId = url.substring(url.indexOf('=')+1);
    console.log(primaryId);

    startLoad();
    courseList(primaryId);
    completeLoading();
    document.onreadystatechange = completeLoading;
}

function courseList(id){
    $.ajax({
        url: contextPath+ "/www/studio/findCourseList",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
            "start": 0,
            "limit": 999,
            "studioId": id
        },
        success:function(result){
            if(result.status == 0) {
                var courseTemp = document.getElementById('courseTemp').innerHTML;
                var html = juicer(courseTemp, {
                    data: result.data.rows
                });
                $("#courseList").html(html);
            }else {
                alert(result.info);
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function courseDetail(id){
    window.location.href = contextPath + '/www/view/courseSummary/index.jsp?id='+id;
}

function completeLoading() {
    if (document.readyState == "complete") {
        stopLoad();
    }
}