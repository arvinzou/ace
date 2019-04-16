var classId = '1';
var nowDate;
var dateData;
var mySwiper;
var weekStr = ['周日', '周一', '周二', '周三', '周四', '周五', '周六'];
var timerModal;

$(function () {
    clock_12h();
    dateData = new Date();
    initSwriper();
    initClassRoom();
    // initpdf();
    initClock();
    ClockData();
    $('.info_box').on('click', '.active_course', viewCourse);
    $('.info_box').on('click', '.active_classInfo', viewClassInfo);
    $('.weeekClass').on('click', '.nextWeek', nextWeek);
    $('.weeekClass').on('click', '.prevWeek', prevWeek);
    $('.modal').on('click', '.hideModal', hideModal);
    $('body').on('click', '.modal', timerClearModal);
    $('.classInfo').on('click', '.active_photos', viewPhotos);
    $('.classInfo').on('click', '.active_student', viewStudent);
    // $('.content').on('click','.active_changeRoom',changeRoom);
    // $('.classRoom').on('click','.roomItem',chooesRoom);


})


function initSwriper() {
    mySwiper = new Swiper('.swiper-container', {
        autoplay: {
            delay: 10000
        },
        // 如果需要前进后退按钮
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
    })
}


function viewStudent() {
    var url = contextPath + "/www/classes/findStudentList";
    var data = {
        classId: classId,
        start: 0,
        limit: 500
    }
    $.getJSON(url, data, function (rst) {
        if (rst.status == 0) {
            renderPage('student', rst.rows, 'tpl-student');
            $('.modal4').show();
        }
    });
}


function getNotices() {
    var url = contextPath + "/www/notice/findPublicNotice";
    var data = {
        classesId: classId,
    }
    $.getJSON(url, data, function (rst) {
        if (rst.status == 0) {
            mySwiper.removeAllSlides();
            var datas = rst.data;
            var temp;
            for (var i = 0; i < datas.length; i++) {
                var item = datas[i];
                if (item.fileUrl) {
                    if (item.fileUrl.lastIndexOf('.pdf') != -1) {
                        temp = pdfTemp.replace('#index#', i);
                        mySwiper.appendSlide(temp);
                        initpdf(item.fileUrl, i);
                        continue;
                    } else if (item.fileUrl.lastIndexOf('.jpg') != -1 || item.fileUrl.lastIndexOf('.png') != -1 || item.fileUrl.lastIndexOf('gif') != -1) {
                        temp = imgTemps.replace('#url#', item.fileUrl);
                        mySwiper.appendSlide(temp);
                        continue;
                    }
                }
                if (item.content) {
                    temp = txtTemp.replace('#text#', item.content)
                    mySwiper.appendSlide(temp);
                }
            }
        }
    });
}


var imgTemps = '<div class="swiper-slide"><div class="imgMessage"><img src="#url#" alt=""></div></div>';
var txtTemp = '<div class="swiper-slide"><div class="txtMessage">#text#</div></div>';
var pdfTemp = '<div class="swiper-slide"><div class="pdfMessage" id="fileMessage#index#"></div></div>';


function initClassRoom() {
    var roomname=GetQueryString('roomname');
    getClassId(roomname);
    $('.room_name').text(roomname);
}

function getClassId(roomname) {
    var url = contextPath + "/www/classes/selectClass";
    var data = {
       name:roomname
    }
    $.getJSON(url, data, function (rst) {
        if(rst.status==0&&rst.value){
            classId=rst.value.id;
            getData();
        }else{

        }
    });
}


function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
    if (r != null) return decodeURI( r[2] );
    return null;
}

/*function chooesRoom() {
    classId=$(this).data('id');
    if(!classId){
        alert("刷新重试");
        return;
    }
    localStorage.setItem("classId",classId);
    getData();
    $('.modal5').hide();
}*/


/*function changeRoom(){
    var url = contextPath + "/www/classes/findClassList";
    var data = {
        status:1,
        start:0,
        limit:50
    }
    $.getJSON(url, data, function (rst) {
        if(rst.status==0){
            renderPage('roomTemp',rst.rows,'tpl-roomTemp');
            $('.modal5').show();
        }
    });
}*/


function viewPhotos() {
    var url = contextPath + "/www/files/findFilesListVo";
    var data = {
        category: 2,
        classesId: classId,
        start: 0,
        limit: 50
    }
    $.getJSON(url, data, function (rst) {
        if (rst.status == 0) {
            $('#imgTemp').viewer('destroy');
            renderPage('imgTemp', rst.rows, 'tpl-imgTemp');
            $('.modal3').show();
            $('#imgTemp').viewer();
        }
    });
}


function viewClassInfo() {
    getClassinfo();
    $('.modal1').show();
}


function hideModal() {
    $(this).closest('.modal').hide();
}

function nextWeek() {
    dateData = addDate(dateData, 7);
    viewCourse();
}

function prevWeek() {
    dateData = addDate(dateData, -7);
    viewCourse();
}


function viewCourse() {
    var url = contextPath + "/www/classSchedule/findMyClassSchedule";
    var data = {
        weekDate: getDateTimeStr(dateData),
        classList: classId
    }
    $.getJSON(url, data, function (rst) {
        if (rst.status == 0) {
            $('#weeekCourseList').empty();
            viewCourseTable();
            var datas = rst.data;
            var len = datas.length;
            for (var i = 0; i < len; i++) {
                var item = datas[i];
                var f = item.courseDate.substring(5, 10);
                $('.' + f + item.courseIndex + 'Teacher').text(item.teacher.name);
                $('.' + f + item.courseIndex + 'Course').text(item.course.name);
            }
        }
    });
    $('.modal2').show();
}


function viewCourseTable() {
    var fristDayDate = GetMonday(dateData);
    for (var i = 0; i < 7; i++) {
        var dayDateString = getDateTimeStr(fristDayDate);
        var dayStyle1 = dayDateString.substring(5, 10);
        var dayStyle2 = dayStyle1.replace('-', '.');
        var weekString = weekStr[fristDayDate.getDay()];
        var temp = dayCourse;
        if (i > 4) {
            temp = dayCourseE
        }
        temp = temp.replace(' #dateString#', dayStyle2);
        temp = temp.replace('[weekString]', weekString);
        temp = temp.replace(/#dateString#/g, dayStyle1);
        $('#weeekCourseList').append($(temp));
        fristDayDate = addDate(fristDayDate, 1);
    }
}


function getDateTimeStr(dateData) {
    var year = dateData.getFullYear();
    var month = dateData.getMonth() + 1;
    var date = dateData.getDate();
    month = month > 9 ? month : '0' + month;
    date = date > 9 ? date : '0' + date;
    return year + '-' + month + '-' + date + ' 00:00:00';
}


function GetMonday(dd) {
    var week = dd.getDay(); //获取时间的星期数
    var minus = week ? week - 1 : 6;
    dd.setDate(dd.getDate() - minus); //获取minus天前的日期
    var y = dd.getFullYear();
    var m = dd.getMonth(); //获取月份
    var d = dd.getDate();
    return new Date(y, m, d);
}


var dayCourse = '     <div class="dayClass">\n' +
    '      <div class="left">\n' +
    '       <p> #dateString#</p>\n' +
    '       <p>[weekString]</p>\n' +
    '      </div>\n' +
    '      <div class="right">\n' +
    '       <table>\n' +
    '        <tr>\n' +
    '         <td class="style6">上午</td>\n' +
    '         <td class=" #dateString#amCourse">自习</td>\n' +
    '         <td class="style6">授课人</td>\n' +
    '         <td class=" #dateString#amTeacher style7">- -</td>\n' +
    '        </tr>\n' +
    '        <tr>\n' +
    '         <td class="style6">下午</td>\n' +
    '         <td class=" #dateString#pmCourse">自习</td>\n' +
    '         <td class="style6">授课人</td>\n' +
    '         <td class=" #dateString#pmTeacher  style7">- -</td>\n' +
    '        </tr>\n' +
    '       </table>\n' +
    '      </div>\n' +
    '     </div>';

var dayCourseE = '     <div class="dayClass">\n' +
    '      <div class="left">\n' +
    '       <p> #dateString#</p>\n' +
    '       <p>[weekString]</p>\n' +
    '      </div>\n' +
    '      <div class="right">\n' +
    '       <table>\n' +
    '        <tr>\n' +
    '         <td class="style6">上午</td>\n' +
    '         <td class=" #dateString#amCourse">休息</td>\n' +
    '         <td class="style6">授课人</td>\n' +
    '         <td class=" #dateString#amTeacher style7">- -</td>\n' +
    '        </tr>\n' +
    '        <tr>\n' +
    '         <td class="style6">下午</td>\n' +
    '         <td class=" #dateString#pmCourse">休息</td>\n' +
    '         <td class="style6">授课人</td>\n' +
    '         <td class=" #dateString#pmTeacher  style7">- -</td>\n' +
    '        </tr>\n' +
    '       </table>\n' +
    '      </div>\n' +
    '     </div>';


function getClassinfo() {
    var url = contextPath + "/www/classes/getClassesInfo";
    var data = {
        classId: classId
    }
    $.getJSON(url, data, function (rst) {
        if (rst.status == 0) {
            var datas = rst.data;
            $('.class_name').text(datas.list.name);
            $('.class_people').text(datas.count);
            renderPage('classTemp', datas, 'tpl-classTemp');
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


function addDate(dateData, day) {
    dateData.setDate(dateData.getDate() + day);
    var m = dateData.getMonth();
    return new Date(dateData.getFullYear(), m, dateData.getDate());
}


function getCourseList() {
    var url = contextPath + "/www/classSchedule/findMyClassSchedule";
    var data = {
        courseDateStr: nowDate,
        classList: classId
    }
    $.getJSON(url, data, function (rst) {
        if (rst.status == 0) {
            var datas = rst.data;
            revertClass();
            for (var i = 0; i < datas.length; i++) {
                var icon = datas[i].courseIndex;
                $('.' + icon + 'Class').text(datas[i].course.name);
                $('.' + icon + 'ClassTeacher').text(datas[i].course.teacherNames);
            }
        }
    });
}


function revertClass() {
    $('.amClass').text('自习');
    $('.amClassTeacher').text("- -");
    $('.pmClass').text('自习');
    $('.pmClassTeacher').text("- -");
}


/*initClock*/
function initClock() {
    var myTime = setInterval(clock_12h, 15000);
    timerClearModal();
}

function timerClearModal() {
    clearInterval(timerModal);
    timerModal = setInterval(function () {
        $('.modal').hide();
        $('#imgTemp').viewer('hide');
    }, 60000);
}


function ClockData() {
    setInterval(function () {
        getData();
    }, 180000);
}


function getData() {
    getClassinfo();
    getCourseList();
    getNotices();
}


function initpdf(url, index) {
    var success = new PDFObject({
        url: url
    }).embed("fileMessage" + index);
}

var PDFObject = function (y) {
    if (!y || !y.url) {
        return false;
    }
    var w = "1.2",
        b = y.id || false,
        i = y.width || "100%",
        z = y.height || "100%",
        r = y.pdfOpenParams,
        a, x;
    var v = function () {
        var c = null;
        if (window.ActiveXObject) {
            c = new ActiveXObject("AcroPDF.PDF");
            if (!c) {
                c = new ActiveXObject("PDF.PdfCtrl");
            }
            if (c !== null) {
                return true;
            }
        }
        return false;
    };
    var u = function () {
        var c, f = navigator.plugins,
            d = f.length,
            e = /Adobe Reader|Adobe PDF|Acrobat/gi;
        for (c = 0; c < d; c++) {
            if (e.test(f[c].name)) {
                return true;
            }
        }
        return false;
    };
    var t = function () {
        var c = navigator.mimeTypes["application/pdf"];
        return (c && c.enabledPlugin);
    };
    var s = function () {
        var c = null;
        if (u() || v()) {
            c = "Adobe";
        } else {
            if (t()) {
                c = "generic";
            }
        }
        return c;
    };
    var q = function () {
        var e = document.getElementsByTagName("html");
        if (!e) {
            return false;
        }
        var c = e[0].style,
            d = document.body.style;
        c.height = "100%";
        c.overflow = "hidden";
        d.margin = "0";
        d.padding = "0";
        d.height = "100%";
        d.overflow = "hidden";
    };
    var p = function (d) {
        var c = "",
            e;
        if (!d) {
            return c;
        }
        for (e in d) {
            if (d.hasOwnProperty(e)) {
                c += e + "=";
                if (e === "search") {
                    c += encodeURI(d[e]);
                } else {
                    c += d[e];
                }
                c += "&";
            }
        }
        return c.slice(0, c.length - 1);
    };
    var o = function (d) {
        var c = null;
        switch (d) {
            case "url":
                c = a;
                break;
            case "id":
                c = b;
                break;
            case "width":
                c = i;
                break;
            case "height":
                c = z;
                break;
            case "pdfOpenParams":
                c = r;
                break;
            case "pluginTypeFound":
                c = x;
                break;
            case "pdfobjectversion":
                c = w;
                break;
        }
        return c;
    };
    var n = function (d) {
        if (!x) {
            return false;
        }
        var c = null;
        if (d) {
            c = (d.nodeType && d.nodeType === 1) ? d : document.getElementById(d);
            if (!c) {
                return false;
            }
        } else {
            c = document.body;
            q();
            i = "100%";
            z = "100%";
        }
        c.innerHTML = '<object	data="' + a + '" type="application/pdf" width="' + i + '" height="' + z + '"></object>';
        return c.getElementsByTagName("object")[0];
    };
    a = encodeURI(y.url) + "#" + p(r);
    x = s();
    this.get = function (c) {
        return o(c);
    };
    this.embed = function (c) {
        return n(c);
    };
    return this;
};

function clock_12h() {
    var today = new Date();//获得当前时间
    //获得年、月、日，Date()函数中的月份是从0－11计算
    var year = today.getFullYear();
    var month = today.getMonth() + 1;
    var date = today.getDate();
    var hour = today.getHours();  //获得小时、分钟、秒
    var minute = today.getMinutes();
    // var second = today.getSeconds();
    nowDate = year + '-' + month + '-' + date + ' 00:00:00';
// var apm="AM"; //默认显示上午: AM
// if (hour>12) //按12小时制显示
// {
//  hour=hour-12;
//  apm="PM"  ;
// }
    var weekday = 0;
    switch (today.getDay()) {
        case 0:
            weekday = "星期日";
            break;
        case 1:
            weekday = "星期一";
            break;
        case 2:
            weekday = "星期二";
            break;
        case 3:
            weekday = "星期三";
            break;
        case 4:
            weekday = "星期四";
            break;
        case 5:
            weekday = "星期五";
            break;
        case 6:
            weekday = "星期六";
            break;
    }
    document.getElementById("clock").innerHTML = "<p>" + year + "年" + month + "月" + date + "日</p><p>" + hour + ":" + minute + "&nbsp;" + weekday + "</p>";
}