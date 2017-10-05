if (!window.console)
	window.console = {};
if (!window.console.log)
	window.console.log = function() {
	};
if (!window.innerHeight) {
	var height = 0;
	if (window.innerHeight) {
		height = window.innerHeight;
	} else if (document.documentElement
			&& document.documentElement.clientHeight) {
		height = document.documentElement.clientHeight;
	} else if (document.body && document.body.clientHeight) {
		height = document.body.clientHeight;
	}
	window.innerHeight = height;
}
if (!window.innerWidth) {
	var width = 0;
	if (document.documentElement && document.documentElement.clientWidth) {
		width = document.documentElement.clientWidth;
	} else if (document.body && document.body.clientWidth) {
		width = document.body.clientWidth;
	} else if (window.innerWidth) {
		width = window.innerWidth;
	}
	window.innerWidth = width;
}
if(portalType=='2'){
    layoutTopHeight=300;
}
function detectOS() {
    var sUserAgent = navigator.userAgent;
    var isWin = (navigator.platform == "Win32") || (navigator.platform == "Windows");
    var isMac = (navigator.platform == "Mac68K") || (navigator.platform == "MacPPC") || (navigator.platform == "Macintosh") || (navigator.platform == "MacIntel");
    if (isMac) return "Mac";
    var isUnix = (navigator.platform == "X11") && !isWin && !isMac;
    if (isUnix) return "Unix";
    var isLinux = (String(navigator.platform).indexOf("Linux") > -1);
    if (isLinux) return "Linux";
    if (isWin) {
        var isWin2K = sUserAgent.indexOf("Windows NT 5.0") > -1 || sUserAgent.indexOf("Windows 2000") > -1;
        if (isWin2K) return "Win2000";
        var isWinXP = sUserAgent.indexOf("Windows NT 5.1") > -1 || sUserAgent.indexOf("Windows XP") > -1;
        if (isWinXP) return "WinXP";
        var isWin2003 = sUserAgent.indexOf("Windows NT 5.2") > -1 || sUserAgent.indexOf("Windows 2003") > -1;
        if (isWin2003) return "Win2003";
        var isWinVista= sUserAgent.indexOf("Windows NT 6.0") > -1 || sUserAgent.indexOf("Windows Vista") > -1;
        if (isWinVista) return "WinVista";
        var isWin7 = sUserAgent.indexOf("Windows NT 6.1") > -1 || sUserAgent.indexOf("Windows 7") > -1;
        if (isWin7) return "Win7";
    }
    return "Win10";
}
function winis(){
	var osName=detectOS();
	if(osName.indexOf("Win")>-1){
		return true;
	}
	return false;
}
if(winis()){
	var agent = navigator.userAgent.toLowerCase() ;
	if(agent.indexOf("chrome") != -1){
	    console.log("=======chrome=====");
        window.innerHeight= window.innerHeight - 15;
	}else{
	    console.log("=======ie=====");
	    window.innerHeight= window.innerHeight - 50;
	}
	layoutTopHeight=layoutTopHeight-8;
}


function rsd(value, kernelKey, staticDictObjects) {
	try {
		if (!staticDictObjects) {
			staticDictObjects = parent.staticDictObject;
		}

		var name = value;

		if ((value + "") && ("" + value).indexOf(',') < 0) {
			if (staticDictObjects && kernelKey && staticDictObjects[kernelKey]) {
				for (var i = 0; i < staticDictObjects[kernelKey].length; i++) {
					if (staticDictObjects[kernelKey][i].CODE == value) {
						name = staticDictObjects[kernelKey][i].NAME;
						break;
					}
				}
			}
		} else {
			if (value) {
				var nameArray = [];
				var v = (value + "").split(',');
				for (var j = 0; j < v.length; j++) {
					for (var i = 0; i < staticDictObjects[kernelKey].length; i++) {
						if (staticDictObjects[kernelKey][i].CODE == v[j]) {
							nameArray.push(staticDictObjects[kernelKey][i].NAME);
							break;
						}
					}
				}
				name = nameArray.join(',');
			}
		}
	} catch (err) {
		console.log("渲染错误", value + ":" + kernelKey + ":" + err);
	}
	return name;
}

function odparse(kernelKey, staticDictObjects) {
	var rst = [];
	try {
		if (!staticDictObjects) {
			staticDictObjects = parent.staticDictObject;
		}
		if (staticDictObjects && kernelKey && staticDictObjects[kernelKey]) {
			for (var i = 0; i < staticDictObjects[kernelKey].length; i++) {
				rst.push(staticDictObjects[kernelKey][i].CODE + ":"
						+ staticDictObjects[kernelKey][i].NAME);
			}
		}
	} catch (err) {
		console.log("渲染错误", value + ":" + kernelKey + ":" + err);
	}
	return rst.join(";");
}
function style_ajax_button(btnId, status, icon) {
	// console.log(icon);
	var btn = $('#' + btnId);
	if (status) {
		if (icon) {
			btn.find('i').removeClass(icon);
		} else {
			btn.find('i').removeClass('fa-check');
		}
		btn.find('i').addClass('fa-spinner fa-spin');
		btn.attr('disabled', true);

	} else {
		btn.find('i').removeClass('fa-spinner');
		btn.find('i').removeClass('fa-spin');
		if (icon) {
			btn.find('i').addClass(icon);
		} else {
			btn.find('i').addClass('fa-check');
		}
		btn.removeAttr("disabled");
	}
}
var Common = {

	// EasyUI用DataGrid用日期格式化
	TimeFormatter : function(value, rec, index) {
		if (value == undefined) {
			return "";
		}
		/* json格式时间转js时间格式 */
		value = value.substr(1, value.length - 2);
		var obj = eval('(' + "{Date: new " + value + "}" + ')');
		var dateValue = obj["Date"];
		if (dateValue.getFullYear() < 1900) {
			return "";
		}
		var val = dateValue.format("yyyy-mm-dd HH:MM");
		return val.substr(11, 5);
	},
	DateTimeFormatter : function(value, rec, index) {
		if (value == undefined) {
			return "";
		}
		/* json格式时间转js时间格式 */
		value = value.substr(1, value.length - 2);
		var obj = eval('(' + "{Date: new " + value + "}" + ')');
		var dateValue = obj["Date"];
		if (dateValue.getFullYear() < 1900) {
			return "";
		}

		return dateValue.format("yyyy-mm-dd HH:MM");
	},

	// EasyUI用DataGrid用日期格式化
	DateFormatter : function(value, rec, index) {
		if (value == undefined) {
			return "";
		}
		return value.substr(0, 10);
	}
};
var cssColor = [ 'red', 'blue', 'green' ];
var cssColor9 = [ 'grey', 'success', 'warning', 'danger', 'info', 'purple',
		'inverse', 'pink', 'yellow' ];
function GetRandomNum(Min, Max) {
	var Range = Max - Min;
	var Rand = Math.random();
	return (Min + Math.round(Rand * Range));
}
function GetRandomNumSeq(k, max) {
	if (k > max) {
		return k - max
	}
	return k;
}
function reloadGrid(){
	console.log('reloadGrid');
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
	}).trigger("reloadGrid");
}
function winis(){
	var osName=detectOS();
	if(osName.indexOf("Win")>-1){
		return true;
	}
	return false;
}

function uuid() {
    var s = [];
    var hexDigits = "0123456789abcdef";
    for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
    }
    s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
    s[8] = s[13] = s[18] = s[23] = "-";
 
    var uuid = s.join("");
    return uuid;
}
function theWeek() {
    var totalDays = 0;
    now = new Date();
    years = now.getYear()
    if (years < 1000)
        years += 1900
    var days = new Array(12);
    days[0] = 31;
    days[2] = 31;
    days[3] = 30;
    days[4] = 31;
    days[5] = 30;
    days[6] = 31;
    days[7] = 31;
    days[8] = 30;
    days[9] = 31;
    days[10] = 30;
    days[11] = 31;

    //判断是否为闰年，针对2月的天数进行计算
    if (Math.round(now.getYear() / 4) == now.getYear() / 4) {
        days[1] = 29
    } else {
        days[1] = 28
    }

    if (now.getMonth() == 0) {
        totalDays = totalDays + now.getDate();
    } else {
        var curMonth = now.getMonth();
        for (var count = 1; count <= curMonth; count++) {
            totalDays = totalDays + days[count - 1];
        }
        totalDays = totalDays + now.getDate();
    }
    //得到第几周
    var week = Math.round(totalDays / 7);
    return week+2;
}

Date.prototype.pattern=function(fmt) {
    var o = {
    "M+" : this.getMonth()+1, //月份
    "d+" : this.getDate(), //日
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
    "H+" : this.getHours(), //小时
    "m+" : this.getMinutes(), //分
    "s+" : this.getSeconds(), //秒
    "q+" : Math.floor((this.getMonth()+3)/3), //季度
    "S" : this.getMilliseconds() //毫秒
    };
    var week = {
    "0" : "/u65e5",
    "1" : "/u4e00",
    "2" : "/u4e8c",
    "3" : "/u4e09",
    "4" : "/u56db",
    "5" : "/u4e94",
    "6" : "/u516d"
    };
    if(/(y+)/.test(fmt)){
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    if(/(E+)/.test(fmt)){
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);
    }
    for(var k in o){
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
}
Date.prototype.format = function(fmt)
{ //author: meizz
  var o = {
    "M+" : this.getMonth()+1,                 //月份
    "d+" : this.getDate(),                    //日
    "h+" : this.getHours(),                   //小时
    "m+" : this.getMinutes(),                 //分
    "s+" : this.getSeconds(),                 //秒
    "q+" : Math.floor((this.getMonth()+3)/3), //季度
    "S"  : this.getMilliseconds()             //毫秒
  };
  if(/(y+)/.test(fmt))
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
  for(var k in o)
    if(new RegExp("("+ k +")").test(fmt))
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
  return fmt;
}