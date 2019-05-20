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
    upload();

    $("#close").click(function () {
        $("#nameModal").hide();
        $("body").removeClass("modalhide");
    });

    var mobileSelect = new MobileSelect({
        trigger: '#nativePlacePicker',
        title: '请选择地区',
        wheels : city,
        // position:[0, 1, 0, 1, 0],
        transitionEnd:function(indexArr, data){
            console.log(data)
            // mobileSelect.setTitle(data[0].value+data[1].value+data[2].value)
            $("#nativePlace").val(data[2].id);
        },
        callback:function(indexArr, data){
            console.log(data);
            $("#nativePlace").val(data[2].id);
        }
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
    var signAcct = null;                                            //账号
    var singPwd = null;                                             //密码
    var nativePlace = $("input[name='nativePlace']").val();    //籍贯
    var college = $("input[name='college']").val();            //毕业院校
    var badgeNum = $("input[name='badgeNum']").val();          //警号
    var mobile = $("input[name='mobile']").val();              //手机号码
    if (!isEmpty(name)) {
        alert("姓名不能为空！");
        return;
    }
    if(!isEmpty(idCard)){
        alert("身份证号不能为空！");
        return;
    }else{
        isCardNo(idCard);
    }

    if(idCard.length == 15){  //15位身份证或者18位身份证
        singPwd = idCard.substring(9, 15);
    }else if(idCard.length == 18){
        singPwd = idCard.substring(12, 18);
    }
    if (!isEmpty(nativePlace)) {
        alert("籍贯不能为空！");
        return;
    }
    if(!isEmpty(clazz)){
        alert("班次不能为空！");
        return;
    }
    if(!isEmpty(badgeNum)){
        alert("警号不能为空！");
        return;
    }else{
        signAcct = badgeNum;              //账号默认为警号
    }
    if(!isEmpty(mobile)){
        alert("手机号码不能为空！");
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
            idCard: idCard,
            political: political[0].id,
            workUnitName: workUnitName,
            postName: postName,
            classId: clazz[0].id,
            uid: new Date().getTime(),
            sex: sex,
            nativePlace: nativePlace,
            college: college,
            badgeNum: badgeNum,
            mobile: mobile
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
    if(card) {
        // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
        var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if (reg.test(card) === false) {
            alert("身份证输入不合法");
            return;
        }
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

                if(student[0].clsId){
                    var tempClass = [{"id": student[0].clsId, "value": student[0].clsName}];
                    clazz = tempClass;
                    $("#classes").text(clazz[0].value);
                }

                var classesSelect= new MobileSelect({
                    trigger: '#classes',
                    title: '警校班级选择',
                    wheels: [
                        {data: classesArr}
                    ],
                    position:[0], //初始化定位 打开时默认选中的哪个 如果不填默认为0
                    transitionEnd:function(indexArr, data){
                        clazz = data;
                    },
                    callback:function(indexArr, data){
                        clazz = data;
                    }
                });
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

function select(o, id) {
    $(o).removeClass('user-unactive').addClass('user-active');
    $(o).siblings().removeClass('user-active').addClass('user-unactive');
    userInfo.id = id;
}
function confirm() {
    if (!$(".user-active")) {
        alert("请确认选择报名信息，如有错误，可在注册进行编辑！");
        return;
    }
    var sPolitical = null;
    if (slen == 1) {
        //如果默认只有一条信息，默认选择第一条
        $("input[name='name']").val(student[0].name);
        $("input[name='idCard']").val(student[0].idCard);
        $("input[name='college']").val(student[0].college);
        $("input[name='workUnitName']").val(student[0].workUnitName);
        $("input[name='postName']").val(student[0].postName);
        $("input[name='badgeNum']").val(student[0].badgeNum);
        $("input[name='mobile']").val(student[0].mobile);
        $("#nativePlace").val(student[0].areaCode);
        if(student[0].areaCode){
            $("#nativePlacePicker").text(student[0].areaCodeName);
        }
        sPolitical = student[0].political;
        sex = student[0].sex;
    } else {
        var tempObj = null;
        for(var i=0; i<student.length; i++){
            if(userInfo.id == student[i].id){
                tempObj = student[i];
            }
        }
        $("input[name='name']").val(tempObj.name);
        $("input[name='workUnitName']").val(tempObj.workUnitName);
        $("input[name='postName']").val(tempObj.postName);
        $("input[name='idCard']").val(tempObj.idCard);
        $("input[name='college']").val(tempObj.college);
        $("input[name='badgeNum']").val(tempObj.badgeNum);
        $("input[name='mobile']").val(tempObj.mobile);
        sPolitical = tempObj.political;
        sex = tempObj.sex;

        $("#nativePlace").val(tempObj.areaCode);
        if(student[0].areaCode){
            $("#nativePlacePicker").text(tempObj.areaCodeName);
        }
    }

    if(sPolitical == "public"){
        political = [{"id": "public", "value": "群众"}];
    }else if(sPolitical == "party"){
        political = [{"id": "party", "value": "党员"}];
    }else if(sPolitical == "member"){
        political = [{"id": "member", "value": "团员"}];
    }

    if(sPolitical){
        $("#political").text(political[0].value);
    }

    var politicalArr = [{"id": "public", "value": "群众"}, {"id": "party", "value": "党员"}, {"id": "member", "value": "团员"}];

    var politicalSelect = new MobileSelect({
        trigger: '#political',
        title: '政治面貌选择',
        wheels: [
            {data: politicalArr}
        ],
        position: [0], //初始化定位 打开时默认选中的哪个 如果不填默认为0
        transitionEnd: function (indexArr, data) {
            political = data;
        },
        callback: function (indexArr, data) {
            political = data;
        }
    });

    if(sex == "1"){
        $("#nan").attr("src", 'img/icon-sex.png');
        $("#nv").attr("src", 'img/sex_unselect.png');
    }else if(sex == "2"){
        $("#nv").attr("src", 'img/icon-sex.png');
        $("#nan").attr("src", 'img/sex_unselect.png');
    }


    $("#nameModal").hide();
    $("body").removeClass("modalhide");
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
        searchByName();
        uploader.removeFile(file);
    });

}
