var political = null;
var clazz = null;
var account = null;
var password = null;
var classesArr = [];
var userInfo = {};
var sex = null;
var slen = 0;
var student = null;
$(function(){
    upload();

    var dictObj = staticDictObject['155'];     //处室字典
    var politicalArr = [];
    for(var i=0; i<dictObj.length; i++){
        if(dictObj[i].CODE != "" && dictObj[i].NAME!=''){
            var o = {};
            o.id = dictObj[i].CODE;
            o.value = dictObj[i].NAME;
            politicalArr.push(o);
        }
    }
	var politicalSelect= new MobileSelect({
	    trigger: '#political',
	    title: '政治面貌选择',
	    wheels: [
	                {data: politicalArr}
	            ],
	    position:[1], //初始化定位 打开时默认选中的哪个 如果不填默认为0
	    transitionEnd:function(indexArr, data){
            political = data;
	    },
	    callback:function(indexArr, data){
            political = data;
	    }
	});

	$("#close").click(function(){
        $("#nameModal").hide();
        $("body").removeClass("modalhide");
    });
    initClassList();

});
function selectSex(obj,value){
    $(obj).attr("src",'img/icon-sex.png');
    $(obj).parent().siblings().find("img").attr("src",'img/sex_unselect.png');
    sex = value;
}
function regist(){
    var name = $("input[name='name']").val();
    var idCard = $("input[name='idCard']").val();
    var workUnitName = $("input[name='workUnitName']").val();     //单位名称
    var postName = $("input[name='postName']").val();              //单位职务
    var signAcct = $("input[name='mobile']").val();
    var pwd = $("input[name='pwd']").val();                        //第一次输入的密码
    var singPwd = $("input[name='password']").val();

    if(!isEmpty(name)) {
        alert("姓名不能为空！");
        return;
    }
    if(!isEmpty(sex)){
        alert("性别不能为空！");
        return;
    }
    if(isEmpty(idCard)){
        isCardNo(idCard);
    }
    if(!isEmpty(workUnitName)){
        alert("单位全称不能为空！");
        return;
    }else{
        if(workUnitName.length < 3){
            alert("单位全称输入在3个字符以上！");
            return;
        }
    }
    if(!isEmpty(postName)){
        alert("职务全称不能为空！");
        return;
    }else{
        if(postName.length < 3){
            alert("职务全称输入在3个字符以上！");
            return;
        }

    }
    if(!isEmpty(clazz)){
        alert("所属班级不能为空！");
        return;
    }
    if(!isEmpty(signAcct)){
        alert("手机号不能为空！");
        return;
    }else{
        if(signAcct.length != 11){
            alert("手机号码必须为11位！");
            return;
        }
    }
    if(!isEmpty(pwd)){
        alert("设置密码不能为空！");
        return;
    }
    if(pwd.length < 4){
        alert("密码位数必须在4位以上！");
        return;
    }
    if(!isEmpty(singPwd)){
        alert("确认密码不能为空！");
        return;
    }
    if(pwd != singPwd){
        alert("设置密码和确认密码前后输入不一致！");
        return;
    }
    $.ajax({
        url: contextPath+ "/www/sign/ssign",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
            isBindWx: "0",
            signAcct: signAcct,
            singPwd: singPwd,
            name: name,
            mobile: signAcct,
            idCard: idCard,
            political: political==null?null:political[0].id,
            workUnitName: workUnitName,
            postName: postName,
            classId: clazz[0].id,
            uid: new Date().getTime(),
            sex: sex
        },
        success:function(result){
            if(result.status == 0) {
                alert(result.info);
                account = signAcct;
                password = singPwd;
                $("#bindModal").show();
                $("body").addClass("modalhide");
            }else {
                if(result.info){
                    alert(result.info);
                }else{
                    alert(result.errorMessage);
                }
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });

}

function isEmpty(str){
    if(str == '' || str == undefined || str == null){
        return false;
    }else{
        return true;
    }
}

function isCardNo(card)
{
// 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if(reg.test(card) === false)
    {
        alert("身份证输入不合法");
        return;
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

function cancel(){
    $("#bindModal").hide();
    $("body").removeClass("modalhide");
    window.location.href = contextPath+"/www/login/index.jsp";
}
function bindWx(){
    var o={};
    o.account=account.toString();
    $("#bindForm input[name='jsonData']").val(JSON.stringify(o));
}

function initClassList(){
    $.ajax({
        url: contextPath + "/www/classes/findClassList",
        type: "post",
        async: false,
        data: {
            status: "1",
            start: 0,
            limit: 999
        },
        success: function(result) {
           if(result.status == 0){
                var classList = result.rows;
                for(var i=0; i<classList.length; i++){
                    var obj = {};
                    obj.id = classList[i].id;
                    obj.value = classList[i].name;
                    classesArr.push(obj);
                }
           }else{
               alert(result.info);
           }
        },
        error: function() {
            alert("出错了！");
        }
    });
}

function searchByName(){
    var name = $("input[name='name']").val();
    if(name == "" || name == null || name == undefined ){
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
        success: function(result) {
            if(result.status == 0){
                $("#nameModal").show();
                $("body").addClass("modalhide");
                renderPage('userInfo', result.data, 'user-tpl');
                slen = result.data.length;
                student = result.data;
                if(student[0].clsId){
                    var tempClass = [{"id": student[0].clsId, "value": student[0].clsViewName}];
                    clazz = tempClass;
                    $("#classes").text(clazz[0].value);
                }

                var classesSelect= new MobileSelect({
                    trigger: '#classes',
                    title: '党校班级选择',
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

            }else{
                alert(result.info);
                return;
            }
        },
        error: function() {
            alert("出错了！");
            return;
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

function select(obj, name, workUnitName, postName){
    $(obj).removeClass('user-unactive').addClass('user-active');
    $(obj).siblings().removeClass('user-active').addClass('user-unactive');
    userInfo.name = name;
    userInfo.workUnitName = workUnitName;
    userInfo.postName = postName;
}
function confirm(){
    if(!$(".user-active")){
        alert("请确认选择报名信息，如有错误，可在注册进行编辑！");
        return;
    }
    if(slen == 1){
        $("input[name='name']").val(student[0].name);
        $("input[name='workUnitName']").val(student[0].workUnitName);
        $("input[name='postName']").val(student[0].postName);
    }else{
        $("input[name='name']").val(userInfo.name);
        $("input[name='workUnitName']").val(userInfo.workUnitName);
        $("input[name='postName']").val(userInfo.postName);
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
