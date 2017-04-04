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

if(winis()){
	window.innerHeight= window.innerHeight - 10;
	layoutTopHeight=layoutTopHeight-8;
}
function isIE() {
	return navigator.appName.indexOf("Microsoft Internet Explorer") != -1;
}

function isIE6() {
	return navigator.userAgent.split(";")[1].toLowerCase().indexOf("msie 6.0") == "-1" ? false
			: true;
}

function isIE7() {
	return navigator.userAgent.split(";")[1].toLowerCase().indexOf("msie 7.0") == "-1" ? false
			: true;
}

function isIE8() {
	return navigator.userAgent.split(";")[1].toLowerCase().indexOf("msie 8.0") == "-1" ? false
			: true;
}
function isIE9() {
	return navigator.userAgent.split(";")[1].toLowerCase().indexOf("msie 9.0") == "-1" ? false
			: true;
}
function isNN() {
	return navigator.userAgent.indexOf("Netscape") != -1;
}

function isOpera() {
	return navigator.appName.indexOf("Opera") != -1;
}

function isFF() {
	return navigator.userAgent.indexOf("Firefox") != -1;
}

function isChrome() {
	return navigator.userAgent.indexOf("Chrome") > -1;
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