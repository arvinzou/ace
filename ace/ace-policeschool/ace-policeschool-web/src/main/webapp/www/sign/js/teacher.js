var political = null;
var account = null;
var pwd = null;
var workUnit = null;
var sex = null;
$(function () {
    upload();

    var unit = staticDictObject['159'];     //处室字典
    var unitArr = [];
    for (var i = 0; i < unit.length; i++) {
        if (unit[i].CODE != "" && unit[i].NAME != '') {
            var o = {};
            o.id = unit[i].CODE;
            o.value = unit[i].NAME;
            unitArr.push(o);
        }
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
    var politicalArr = [{"id": "public", "value": "群众"}, {"id": "party", "value": "党员"}, {"id": "member", "value": "团员"}];
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

});
function selectSex(obj, value) {
    $(obj).attr("src", 'img/icon-sex.png');
    $(obj).parent().siblings().find("img").attr("src", 'img/sex_unselect.png');
    sex = value;
}
function regist() {
    var name = $("input[name='name']").val();
    var idCard = $("input[name='idCard']").val();
    var postName = $("input[name='postName']").val();              //单位职务
    var signAcct = $("input[name='mobile']").val();
    var pwd = $("input[name='pwd']").val();                        //第一次输入的密码
    var singPwd = $("input[name='password']").val();

    if (!isEmpty(name)) {
        alert("姓名不能为空！");
        return;
    }
    if (isEmpty(idCard)) {
        isCardNo(idCard);
    }
    if (!isEmpty(sex)) {
        alert("性别不能为空！");
        return;
    }
    if (!isEmpty(political)) {
        alert("政治面貌不能为空！");
        return;
    }
    if (!isEmpty(workUnit)) {
        alert("处室不能为空！");
        return;
    }
    if (!isEmpty(postName)) {
        alert("职务职称不能为空！");
        return;
    } else {
        if (postName.length < 3) {
            alert("职务职称输入在3个字符以上！");
            return;
        }
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
        alert("密码位数必须大于4位！");
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
        url: contextPath + "/www/sign/tsign",
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
            workUnitName: workUnit[0].id,
            postName: postName,
            uid: new Date().getTime(),
            sex: sex
        },
        success: function (result) {
            if (result.status == 0) {
                alert(result.info);
                account = signAcct;
                pwd = singPwd;
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

function imgChange(fileUrl) {
    $.ajax({
        url: contextPath+"/www/toolKit/ocridcard",
        type: "post",
        async: false,
        data: {
            imgUrl: fileUrl
        },
        success: function(result) {
            console.log("====================" + result);
            $("input[name='name']").val(result.data.cards[0].name);
            $("input[name='idCard']").val(result.data.cards[0].id_card_number);
        },
        error: function(e) {
            alert("出错了！");
        }
    });
}

function cancel() {
    $("#bindModal").hide();
    $("body").removeClass("modalhide");
    window.history.go(-1);
}

function bindWx() {
    var o = {};
    o.account = account.toString();
    $("#bindForm input[name='jsonData']").val(JSON.stringify(o));

    $("#bindForm").submit();
}

function upload(){
    var load = new Loading();
    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'upload',
        url: '/portal/www/upload.do',
        file_data_name: 'file',
        multi_selection: false,
        max_file_size: '100mb',
        //一次上传数据大小
        chunk_size: '100mb',
        resize: {
            width: 1024,
            height: 1024,
            crop: true,
            quality: 60,
            preserve_headers: false
        },
        filters: [

        ]
    });
    uploader.init();
    uploader.bind("FileFiltered",function(uploader,file){
        uploader.start();
        load.start();
        return false;
    });
    uploader.bind("FileUploaded",function (uploader,file,responseObject) {
        var rst = JSON.parse(responseObject.response);
        var fileUrl = rst.file_path;
        imgChange(fileUrl);
        load.stop();
        uploader.removeFile(file);
    });

}
