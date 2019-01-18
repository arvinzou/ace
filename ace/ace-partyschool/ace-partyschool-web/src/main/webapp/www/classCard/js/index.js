var classId = '1';
var nowDate;

$(function () {
    clock_12h();
    initData();
    initpdf();
    initClock();
})

/*初始化信息*/
function initData() {
    getClassinfo();
    getCourseList();
}

function getClassinfo() {

}

function getCourseList() {
    var url = contextPath + "/www/classSchedule/findMyClassSchedule";
    var data = {
        courseDateStr: nowDate,
        classList: classId
    }
    $.getJSON(url, data, function (rst) {
        if(rst.status==0){
            var datas=rst.data;
            for(var i=0;i<datas.length;i++){
                var icon=datas[i].courseIndex;
                $('.'+icon+'Class').text(datas[i].course.name);
                $('.'+icon+'ClassTeacher').text(datas[i].teacher.name);
            }
        }
    });
}


/*initClock*/
function initClock() {
    var myTime = setInterval("clock_12h()", 15000);
}

function initpdf() {
    var success = new PDFObject({
        url: "./img/高铁图.pdf"
    }).embed("fileMessage");
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
    var today = new Date(); //获得当前时间
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