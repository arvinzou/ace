var regType = null;
$(function(){

    $.ajax({
        url: contextPath+ "/www/sign/getAcctInfo",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
        },
        success:function(result){
            if(result.status == 0) {
                regType = result.data.regType;
                renderPage('userInfo', result.data, 'userinfo-tpl');
            }else {
                if(result.info){
                    alert(result.info);
                }else{
                    alert(result.errorMessage);
                }
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });

    initTodayCourse();
});

function initTodayCourse() {
    var nowDate = new Date();
    var year = nowDate.getFullYear();
    var month = nowDate.getMonth() +1;
    if(month <10){
        month = "0"+month;
    }
    var day = nowDate.getDate();
    var dateTime = year+"-"+month+"-"+day+" 00:00:00";
    $.ajax({
        url: contextPath+ "/www/classSchedule/findMyClassSchedule",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
            courseDateStr:dateTime
        },
        success:function(result){
            if(result.status == 0) {
                renderPage('todayCourse', result.data, 'course-tpl');
            }else {
                if(result.info){
                    alert(result.info);
                }else{
                    alert(result.errorMessage);
                }
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}

function weekCourse(){
    window.location.href = contextPath + '/www/registered/timetable/week.jsp';
}

function toMailList(){
    window.location.href = contextPath + '/www/registered/maillist/index.jsp';
}
function toClassFiles(){
    window.location.href = contextPath + '/www/registered/files/clazz/index.jsp';
}
function toConsumption(){
    if(regType == "student"){
        window.location.href = contextPath + '/www/registered/consumption/student.jsp';
    }else{
        window.location.href = contextPath + '/www/registered/consumption/teacher.jsp';
    }
}
function showPersonInfo(){
    window.location.href = contextPath + '/www/registered/person/info.jsp';
}
function classNotice(){
    window.location.href = contextPath + '/www/registered/classnotice/index.jsp';
}
function attendtion(){
    window.location.href = contextPath + '/www/registered/attendance/index.jsp';
}
function moreNotice(){
    window.location.href = contextPath + '/www/registered/notice/index.jsp';
}
function toTest(){
    window.location.href = contextPath + '/www/registered/test/index.jsp';
}