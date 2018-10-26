var primaryId = null;
window.onload = function() {
    console.log(window.location.href);
    var url =   window.location.search.substring(1);
    primaryId = url.substring(url.indexOf('=')+1);
    console.log(primaryId);
    initData(primaryId);
    initStudioReport(primaryId);
    initFineCourse(primaryId);
};

function initData(id){
    $.ajax({
        url: contextPath+ "/www/studio/getStudioDetail",
        type:"post",
        async:false,
        data:{
            studioId: id
        },
        success:function(result){
            if(result.status == 0) {
                var roombaseTemp = document.getElementById('roomInfoTemp').innerHTML;
                var html = juicer(roombaseTemp, {
                    roombase: result.data
                });
                $(".room_info").append(html);

                var contentTemp = document.getElementById('roomContentTemp').innerHTML;
                var content = juicer(contentTemp, {
                    roomContent: result.data
                });
                $("#roomContent").append(content);

                var memberTemp = document.getElementById('memberListTemp').innerHTML;
                var memberHtml = juicer(memberTemp, {
                    member: result.data
                });
                $("#memberList").append(memberHtml);

                var slideTemp = document.getElementById('bannerSlideTemp').innerHTML;
                var slideHtml = juicer(slideTemp, {
                    slide: result.data.imgList
                });
                $("#bannerslide").append(slideHtml);

                var detail = document.getElementById('workroomDetailTemp').innerHTML;
                var detailHtml = juicer(detail, {
                    detail: result.data.introduce
                });
                $("#workroomDetail").append(detailHtml);
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
function initStudioReport(id){
    $.ajax({
        url: contextPath+ "/www/studio/studioReport",
        type:"post",
        async:false,
        data:{
            "studioId": id
        },
        success:function(result){
            if(result.status == 0) {
                var roombaseTemp = document.getElementById('report-tpl').innerHTML;
                var html = juicer(roombaseTemp, {
                    data: result.data
                });
                $("#report").append(html);
                $("#num").val(result.data.consultCount);
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

/**
 * 查询精品课程
 */
function initFineCourse(id){
    $.ajax({
        url: contextPath+ "/www/studio/findExcellentCourse",
        type:"post",
        async:false,
        data:{
            "studioId": id
        },
        success:function(result){
            if(result.status == 0) {
                console.log(result.data.rows);
                var courseTemp = document.getElementById('course-tpl').innerHTML;
                var html = juicer(courseTemp, {
                    data: result.data.rows
                });
                $("#qualityCourse").append(html);
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
function showMember(id){
    window.location.href = contextPath + '/www/view/workmember/index.jsp?id='+id;
}

function invate(id){
    window.location.href = contextPath + '/www/view/invate/index.jsp?id='+id;
}
/**
 * 查看课程详情
 * @param id
 */
function courseDetail(id){
    window.location.href = contextPath + '/www/view/courseSummary/index.jsp?id='+id;
}

/**
 *查看精品课程更多
 */
function more(){
    window.location.href = contextPath + '/www/view/studioCourse/index.jsp?id='+primaryId;
}