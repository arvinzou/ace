var political = null;
var clazz = null;
var account = null;
var password = null;
var classesArr = [];
var userInfo = {};
var sex = null;
var slen = 0;
var student = null;
$(function () {
    var politicalArr = [{"id": "normal", "value": "非党员"}, {"id": "party", "value": "党员"}];
    initClassList();
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
    var classesSelect = new MobileSelect({
        trigger: '#classes',
        title: '党校班级选择',
        wheels: [
            {data: classesArr}
        ],
        position: [1], //初始化定位 打开时默认选中的哪个 如果不填默认为0
        transitionEnd: function (indexArr, data) {
            clazz = data;
        },
        callback: function (indexArr, data) {
            clazz = data;
        }
    });

    $("#close").click(function () {
        $("#nameModal").hide();
        $("body").removeClass("modalhide");
    });
    initClassList();
});
function selectSex(obj, value) {
    $(obj).attr("src", 'img/icon-sex.png');
    $(obj).parent().siblings().find("img").attr("src", 'img/sex_unselect.png');
    sex = value;
}
function regist() {
    var name = $("input[name='name']").val();
    var idCard = $("input[name='idCard']").val();
    var workUnitName = $("input[name='workUnitName']").val();     //单位名称
    var postName = $("input[name='postName']").val();              //单位职务
    var signAcct = $("input[name='mobile']").val();
    var pwd = $("input[name='pwd']").val();                        //第一次输入的密码
    var singPwd = $("input[name='password']").val();

    if (!isEmpty(name)) {
        alert("姓名不能为空！");
        return;
    }
    if (!isEmpty(sex)) {
        alert("性别不能为空！");
        return;
    }
    if (isEmpty(idCard)) {
        isCardNo(idCard);
    }
    if (!isEmpty(political)) {
        alert("政治面貌不能为空！");
        return;
    }
    if (!isEmpty(workUnitName)) {
        alert("单位全称不能为空！");
        return;
    } else {
        if (workUnitName.length < 3) {
            alert("单位全称输入在3个字符以上！");
            return;
        }
    }
    if (!isEmpty(postName)) {
        alert("职务全称不能为空！");
        return;
    } else {
        if (postName.length < 3) {
            alert("职务全称输入在3个字符以上！");
            return;
        }

    }
    if (!isEmpty(clazz)) {
        alert("所属班级不能为空！");
        return;
    }
    if (!isEmpty(signAcct)) {
        alert("手机号不能为空！");
        return;
    } else {
        if (signAcct.length != 11) {
            alert("手机号码必须为11位！");
            return;
        }
    }
    if (!isEmpty(pwd)) {
        alert("设置密码不能为空！");
        return;
    }
    if (pwd.length < 4) {
        alert("密码位数必须在4位以上！");
        return;
    }
    if (!isEmpty(singPwd)) {
        alert("确认密码不能为空！");
        return;
    }
    if (pwd != singPwd) {
        alert("设置密码和确认密码前后输入不一致！");
        return;
    }
    $.ajax({
        url: contextPath + "/www/sign/ssign",
        type: "post",
        async: false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {
            isBindWx: "0",
            signAcct: signAcct,
            singPwd: singPwd,
            name: name,
            mobile: signAcct,
            idCard: idCard,
            political: political[0].id,
            workUnitName: workUnitName,
            postName: postName,
            classId: clazz[0].id,
            uid: new Date().getTime(),
            sex: sex
        },
        success: function (result) {
            if (result.status == 0) {
                alert(result.info);
                account = signAcct;
                password = singPwd;
                $("#bindModal").show();
                $("body").addClass("modalhide");
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

function isEmpty(str) {
    if (str == '' || str == undefined || str == null) {
        return false;
    } else {
        return true;
    }
}

function isCardNo(card) {
// 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if (reg.test(card) === false) {
        alert("身份证输入不合法");
        return;
    }
}

function base64(file, callback) {
    var coolFile = {};

    function readerOnload(e) {
        var base64 = btoa(e.target.result);
        coolFile.base64 = base64;
        callback(coolFile)
    };
    var reader = new FileReader();
    reader.onload = readerOnload;

    var file = file[0].files[0];
    if (file) {
        coolFile.filetype = file.type;
        coolFile.size = file.size;
        coolFile.filename = file.name;
        reader.readAsBinaryString(file);
    }
}

function imgChange() {
    var idCardData = "";
    base64($('input[type="file"]'), function (data) {
        idCardData = data.base64;
        console.log("idCardData==================================" + idCardData);
        $.ajax({
            url: "https://api-cn.faceplusplus.com/cardpp/v1/ocridcard",
            type: "post",
            async: false,
            data: {
                "api_key": "-5Wf1CueJ8FffHLeEap4RtVOE77P6IQT",
                "api_secret": "dxWQqNdaXugnohd021ba1Cu_g4tfLmW3",
                "image_base64": idCardData
            },
            success: function (result) {
                console.log("====================" + result);
                $("input[name='name']").val(result.cards[0].name);
                $("input[name='idCard']").val(result.cards[0].id_card_number);
            },
            error: function () {
                console.log("出错了！");
                alert("出错了！");
            }
        });
    });
}

function cancel() {
    $("#bindModal").hide();
    $("body").removeClass("modalhide");
}
function bindWx() {
    var o = {};
    o.account = account.toString();
    $("#bindForm input[name='jsonData']").val(JSON.stringify(o));

    $("#bindForm").submit();
}

function initClassList() {
    $.ajax({
        url: contextPath + "/www/classes/findClassList",
        type: "post",
        async: false,
        data: {
            status: "1",
            start: 0,
            limit: 999
        },
        success: function (result) {
            if (result.status == 0) {
                var classList = result.rows;
                for (var i = 0; i < classList.length; i++) {
                    var obj = {};
                    obj.id = classList[i].id;
                    obj.value = classList[i].name;
                    classesArr.push(obj);
                }
            } else {
                alert(result.info);
            }
        },
        error: function () {
            alert("出错了！");
        }
    });
}

function searchByName() {
    var name = $("input[name='name']").val();
    if (name == "" || name == null || name == undefined) {
        alert("姓名不能为空！");
        return;
    }
    $.ajax({
        url: contextPath + "/www/sign/searchByName",
        type: "post",
        async: false,
        data: {
            name: name
        },
        success: function (result) {
            if (result.status == 0) {
                $("#nameModal").show();
                $("body").addClass("modalhide");
                renderPage('userInfo', result.data, 'user-tpl');
                slen = result.data.length;
                student = result.data;
            } else {
                alert(result.info);
            }
        },
        error: function () {
            alert("出错了！");
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

function select(obj, name, workUnitName, postName) {
    $(obj).removeClass('user-unactive').addClass('user-active');
    $(obj).siblings().removeClass('user-active').addClass('user-unactive');
    userInfo.name = name;
    userInfo.workUnitName = workUnitName;
    userInfo.postName = postName;
}
function confirm() {
    if (!$(".user-active")) {
        alert("请确认选择报名信息，如有错误，可在注册进行编辑！");
        return;
    }
    if (slen == 1) {
        $("input[name='name']").val(student[0].name);
        $("input[name='workUnitName']").val(student[0].workUnitName);
        $("input[name='postName']").val(student[0].postName);
    } else {
        $("input[name='name']").val(userInfo.name);
        $("input[name='workUnitName']").val(userInfo.workUnitName);
        $("input[name='postName']").val(userInfo.postName);
    }

    $("#nameModal").hide();
    $("body").removeClass("modalhide");
}