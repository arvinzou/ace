var regType = null;
var dorm;
$(function () {

    $.ajax({
        url: contextPath + "/www/sign/getAcctInfo",
        type: "post",
        async: false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {},
        success: function (result) {
            if (result.status == 0) {
                regType = result.data.regType;
                if(result.data.student){
                    var i=result.data.student.dorm;
                    dorm=i?i:'';
                }
                renderPage('userInfo', result.data, 'userinfo-tpl');
            } else {
                if (result.info) {
                    alert(result.info);
                } else {
                    alert(result.errorMessage);
                }
                return;
            }
        },
        error: function () {
            alert("系统服务内部异常！");
        }
    });

    initTodayCourse();
    initNoticeTop1();
    initAnalysis();
});

function initTodayCourse() {
    var nowDate = new Date();
    var year = nowDate.getFullYear();
    var month = nowDate.getMonth() + 1;
    if (month < 10) {
        month = "0" + month;
    }
    var day = nowDate.getDate();
    var dateTime = year + "-" + month + "-" + day + " 00:00:00";
    $.ajax({
        url: contextPath + "/www/classSchedule/findMyClassSchedule",
        type: "post",
        async: false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {
            startTimeStr: dateTime
        },
        success: function (result) {
            if (result.status == 0) {
                renderPage('todayCourse', result.data.reverse(), 'course-tpl');
            } else {
                if (result.info) {
                    alert(result.info);
                } else {
                    alert(result.errorMessage);
                }
                return;
            }
        },
        error: function () {
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

function weekCourse() {
    window.location.href = contextPath + '/www/registered/timetable/week.jsp';
}

function toMailList() {
    window.location.href = contextPath + '/www/registered/maillist/index.jsp';
}
function toClassFiles() {
    window.location.href = contextPath + '/www/registered/files/clazz/index.jsp';
}
function toConsumption() {
    if (regType == "student") {
        window.location.href = contextPath + '/www/registered/consumption/student.jsp';
    } else {
        window.location.href = contextPath + '/www/registered/consumption/teacher.jsp';
    }
}
function showPersonInfo() {
    if (regType == 'student') {
        window.location.href = contextPath + '/www/registered/person/sinfo.jsp';
    } else {
        window.location.href = contextPath + '/www/registered/person/tinfo.jsp';
    }
}
function classNotice() {
    window.location.href = contextPath + '/www/registered/classnotice/index.jsp?dorm='+dorm;
}
function attendtion() {
    window.location.href = contextPath + '/www/registered/attendance/index.jsp';
}
function moreNotice() {
    window.location.href = contextPath + '/www/registered/notice/index.jsp';
}
function toTest() {
    window.location.href = contextPath + '/www/registered/test/index.jsp';
}

function initNoticeTop1() {
    $.ajax({
        url: contextPath + "/www/notice/findNoticeLists",
        type: "post",
        async: false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {
            start: 0,
            limit: 1
        },
        success: function (result) {
            if (result.status == 0) {
                if (result.data.list.length > 0) {
                    renderPage('noticeTop', result.data.list[0], 'noticeTop-tpl');
                    $("#more").append('<span class="new">' + result.data.count + '</span>');

                } else {
                    renderPage('noticeTop', result.data.history[0], 'noticeTop-tpl');
                    $("#more").append('<span class="new">' + 0 + '</span>');
                }
            } else {
                if (result.info) {
                    alert(result.info);
                } else {
                    alert(result.errorMessage);
                }
                return;
            }
        },
        error: function () {
            alert("系统服务内部异常！");
        }
    });
}

function initAnalysis() {
    $.ajax({
        url: contextPath + '/anslysis/query',
        type: "post",
        async: false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {
            reportId: "appPortal"
        },
        success: function (result) {
            if (result.status == 0) {
                var data = {};
                var temp = result.value;
                for (var i = 0; i < temp.length; i++) {
                    if (temp[i].id == 'eva') {
                        data.eva = temp[i].VALUE;
                    } else if (temp[i].id == 'teacher') {
                        data.teacher = temp[i].VALUE;
                    } else if (temp[i].id == 'student') {
                        data.student = temp[i].VALUE;
                    } else if (temp[i].id == 'file') {
                        data.file = temp[i].VALUE;
                    } else if (temp[i].id == 'test') {
                        data.test = temp[i].VALUE;
                    }
                    data.regType = regType;
                }
                renderPage('menu', data, 'menu-tpl');
            } else {
                if (result.info) {
                    alert(result.info);
                } else {
                    alert(result.errorMessage);
                }
                return;
            }
        },
        error: function () {
            alert("系统服务内部异常！");
        }
    });
}