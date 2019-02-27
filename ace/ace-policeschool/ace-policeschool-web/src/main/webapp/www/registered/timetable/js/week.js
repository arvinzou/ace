$(function () {
    var weekfn = new jcalendar_week({
        element: "#jcalendar_week",//填充日历的dom元素
        dayaddclass: function (date) {
            //添加某天时给添加类名
            return "";
        },
        dayclick: function (date, obj) {
            //day点击事件
            $(obj).addClass("calendar_day_act").siblings().removeClass("calendar_day_act");
            initCourseList(date + " 00:00:00");
        }
    });
    //获取周第一天
    console.log(weekfn.weekfirstdate(2018, 36));
    //获取传入日期为当年第几周（注:这里的月份从0开始）
    console.log(weekfn.getweeknum(2018, 0, 16));
    //跳转到指定周
    function jumpweek() {
        var getyear = parseInt($("#setyear").val());
        var getweek = parseInt($("#setweek").val());
        weekfn.confirmweek(getyear, getweek);
        $('#pop').hide();
    }

    //跳转到本周
    function tonowweek() {
        weekfn.nowweek();
        $('#pop').hide();
    }

    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    initCourseList(year + "-" + month + "-" + day + " 00:00:00");
});

function initCourseList(date) {
    $.ajax({
        url: contextPath + "/www/classSchedule/findMyClassSchedule",
        type: "post",
        async: false,
        data: {
            courseDateStr: date
        },
        success: function (result) {
            if (result.status == 0) {
                renderPage('courseList', result.data, 'list-tpl');
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