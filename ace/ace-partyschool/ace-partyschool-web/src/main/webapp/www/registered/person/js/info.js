var account = 0;
var regType = null;
var userInfo = {};
var political = null;
var workUnit = null;
var sex = null;
$(function(){
    initUserinfo();
});
function initUserinfo(){
    $.ajax({
        url: contextPath+ "/www/sign/getAcctInfo",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
        },
        success:function(result){
            if(result.status == 0) {
                userInfo = result.data;
                renderPage('userInfo', result.data, 'info-tpl');
                renderPage('option', result.data, 'option-tpl');
                regType = result.data.regType;
                initPolitical();
                if(regType == 'teacher'){
                    initWorkUnit();
                }
                sex = result.data.sex;
                if(result.data.regType == 'student'){
                    account = result.data.student.mobile;
                }else{
                    account = result.data.teacher.mobile;
                }
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
function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}

function exit(){
    $.ajax({
        url: contextPath+ "/www/sign/logout",
        type:"post",
        async:false,
        data:{
        },
        success:function(result){
            if(result.status == 0) {
               window.location.href = contextPath + '/www/login/index.jsp';
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

function editPassword(){
    window.location.href = contextPath + '/www/reset/index.jsp';
}

function bindWx(){
    var o={};
    o.account=account;
    $("#bindForm input[name='jsonData']").val(JSON.stringify(o));
    $("#bindForm").submit();
}

function initPolitical(){
    var politicalArr = [{"id":"normal","value":"非党员"},{"id":"party","value":"党员"}];
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
}

function initWorkUnit(){
    var unit = staticDictObject['156'];     //处室字典
    var unitArr = [];
    for(var i=0; i<unit.length; i++){
        var o = {};
        o.id = unit[i].CODE;
        o.value = unit[i].NAME;
        unitArr.push(o);
    }
    var unitSelect= new MobileSelect({
        trigger: '#workUnit',
        title: '处室选择',
        wheels: [
            {data: unitArr}
        ],
        position:[1], //初始化定位 打开时默认选中的哪个 如果不填默认为0
        transitionEnd:function(indexArr, data){
            workUnit = data;
        },
        callback:function(indexArr, data){
            workUnit = data;
        }
    });
}

function save(){
    if(regType == 'student'){
        editStudent();
    }else{
        editTeacher();
    }
}

function selectSex(obj,value){
    $(obj).attr("src",'img/icon-sex.png');
    $(obj).parent().siblings().find("img").attr("src",'img/sex_unselect.png');
    sex = value;
}

function isCardNo(card)
{
// 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if(reg.test(card) === false)
    {
        return false;
    }
}

function editStudent(){
    var idCard = $("input[name='idCard']").val();
    var mobile = $("input[name='mobile']").val();
    var workUnitName = $("input[name='workUnitName']").val();
    var postName = $("input[name='postName']").val();
    var politicalName = userInfo.student.political;
    if(!isCardNo(idCard)){
        alert("身份证输入不合法！");
        return;
    }
    if(mobile == "" || mobile == undefined || mobile == null){
        alert("手机号码不能为空！");
        return;
    }
    $.ajax({
        url: contextPath+ "/www/sign/student/update",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
         jsons:  JSON.stringify({
                name: userInfo.student.name,
                sex: sex,
                mobile: mobile,
                idCard: idCard,
                political:political == null? politicalName: political[0].id,
                workUnitName: workUnitName,
                postName: postName,
                classId: userInfo.student.classId
        })
        },
        success:function(result){
            if(result.status == 0) {
                alert(result.info);
                exit();
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

function editTeacher(){
    var idCard = $("input[name='idCard']").val();
    var mobile = $("input[name='mobile']").val();
    var workUnitName = userInfo.teacher.workUnitName;
    var postName = $("input[name='postName']").val();
    var politicalName = userInfo.teacher.political;
    $.ajax({
        url: contextPath+ "/www/sign/teacher/update",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
            jsons:  JSON.stringify({
                name: userInfo.teacher.name,
                sex: sex,
                mobile: mobile,
                idCard: idCard,
                political:political == null? politicalName: political[0].id,
                workUnitName: workUnit == null?workUnitName: workUnit[0].id ,
                postName: postName
            })
        },
        success:function(result){
            if(result.status == 0) {
                alert(result.info);
                exit();
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