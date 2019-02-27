var account = 0;
var regType = null;
var userInfo = {};
var political = null;
var workUnit = null;
var sex = null;
var aCity = {
    11: "北京",
    12: "天津",
    13: "河北",
    14: "山西",
    15: "内蒙古",
    21: "辽宁",
    22: "吉林",
    23: "黑龙江",
    31: "上海",
    32: "江苏",
    33: "浙江",
    34: "安徽",
    35: "福建",
    36: "江西",
    37: "山东",
    41: "河南",
    42: "湖北",
    43: "湖南",
    44: "广东",
    45: "广西",
    46: "海南",
    50: "重庆",
    51: "四川",
    52: "贵州",
    53: "云南",
    54: "西藏",
    61: "陕西",
    62: "甘肃",
    63: "青海",
    64: "宁夏",
    65: "新疆",
    71: "台湾",
    81: "香港",
    82: "澳门",
    91: "国外"
}
$(function () {
    initUserinfo();
});
function initUserinfo() {
    $.ajax({
        url: contextPath + "/www/sign/getAcctInfo",
        type: "post",
        async: false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {},
        success: function (result) {
            if (result.status == 0) {
                userInfo = result.data;
                renderPage('userInfo', result.data, 'info-tpl');
                renderPage('option', result.data, 'option-tpl');
                regType = result.data.regType;
                initPolitical();
                if (regType == 'teacher') {
                    initWorkUnit();
                }
                sex = result.data.sex;
                if (result.data.regType == 'student') {
                    account = result.data.student.mobile;
                } else {
                    account = result.data.teacher.mobile;
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
function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}

function exit() {
    $.ajax({
        url: contextPath + "/www/sign/logout",
        type: "post",
        async: false,
        data: {},
        success: function (result) {
            if (result.status == 0) {
                window.location.href = contextPath + '/www/login/index.jsp';
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

function editPassword() {
    window.location.href = contextPath + '/www/reset/index.jsp';
}

function bindWx() {
    var o = {};
    o.account = account.toString();
    $("#bindForm input[name='jsonData']").val(JSON.stringify(o));
    $("#bindForm").submit();
}

function initPolitical() {
    var politicalArr = [{"id": "normal", "value": "非党员"}, {"id": "party", "value": "党员"}];
    var politicalSelect = new MobileSelect({
        trigger: '#political',
        title: '政治面貌选择',
        wheels: [
            {data: politicalArr}
        ],
        position: [1], //初始化定位 打开时默认选中的哪个 如果不填默认为0
        transitionEnd: function (indexArr, data) {
            political = data;
        },
        callback: function (indexArr, data) {
            political = data;
        }
    });
}

function initWorkUnit() {
    var unit = staticDictObject['156'];     //处室字典
    var unitArr = [];
    for (var i = 0; i < unit.length; i++) {
        var o = {};
        o.id = unit[i].CODE;
        o.value = unit[i].NAME;
        unitArr.push(o);
    }
    var unitSelect = new MobileSelect({
        trigger: '#workUnit',
        title: '处室选择',
        wheels: [
            {data: unitArr}
        ],
        position: [1], //初始化定位 打开时默认选中的哪个 如果不填默认为0
        transitionEnd: function (indexArr, data) {
            workUnit = data;
        },
        callback: function (indexArr, data) {
            workUnit = data;
        }
    });
}

function save() {
    if (regType == 'student') {
        editStudent();
    } else {
        editTeacher();
    }
}

function selectSex(obj, value) {
    $(obj).attr("src", 'img/icon-sex.png');
    $(obj).parent().siblings().find("img").attr("src", 'img/sex_unselect.png');
    sex = value;
}

function isCardNo(sId) {
    var iSum = 0;
    var info = "";
    if (!/^\d{17}(\d|x)$/i.test(sId)) return "你输入的身份证长度或格式错误";
    sId = sId.replace(/x$/i, "a");
    if (aCity[parseInt(sId.substr(0, 2))] == null) return "你的身份证地区非法";
    sBirthday = sId.substr(6, 4) + "-" + Number(sId.substr(10, 2)) + "-" + Number(sId.substr(12, 2));
    var d = new Date(sBirthday.replace(/-/g, "/"));
    if (sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate()))return "身份证上的出生日期非法";
    for (var i = 17; i >= 0; i--) iSum += (Math.pow(2, i) % 11) * parseInt(sId.charAt(17 - i), 11);
    if (iSum % 11 != 1) return "你输入的身份证号非法";
    return true;
}

function editStudent() {
    var idCard = $("input[name='idCard']").val();
    var mobile = $("input[name='mobile']").val();
    var workUnitName = $("input[name='workUnitName']").val();
    var postName = $("input[name='postName']").val();
    var politicalName = userInfo.student.political;
    if (idCard != '' && idCard != null && idCard != undefined) {
        if (isCardNo(idCard) != true) {
            alert(isCardNo(idCard));
            return;
        }
    }

    if (mobile == "" || mobile == undefined || mobile == null) {
        alert("手机号码不能为空！");
        return;
    }
    $.ajax({
        url: contextPath + "/www/sign/student/update",
        type: "post",
        async: false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {
            jsons: JSON.stringify({
                name: userInfo.student.name,
                sex: sex,
                mobile: mobile,
                idCard: idCard,
                political: political == null ? politicalName : political[0].id,
                workUnitName: workUnitName,
                postName: postName,
                classId: userInfo.student.classId
            })
        },
        success: function (result) {
            if (result.status == 0) {
                alert(result.info);
                exit();
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

function editTeacher() {
    var idCard = $("input[name='idCard']").val();
    var mobile = $("input[name='mobile']").val();
    var workUnitName = userInfo.teacher.workUnitName;
    var postName = $("input[name='postName']").val();
    var politicalName = userInfo.teacher.political;
    $.ajax({
        url: contextPath + "/www/sign/teacher/update",
        type: "post",
        async: false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {
            jsons: JSON.stringify({
                name: userInfo.teacher.name,
                sex: sex,
                mobile: mobile,
                idCard: idCard,
                political: political == null ? politicalName : political[0].id,
                workUnitName: workUnit == null ? workUnitName : workUnit[0].id,
                postName: postName
            })
        },
        success: function (result) {
            if (result.status == 0) {
                alert(result.info);
                exit();
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