var political = null;
var account = null;
var workUnit = null;
$(function(){
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

});

function regist(){
    var name = $("input[name='name']").val();
    var idCard = $("input[name='idCard']").val();
    var postName = $("input[name='postName']").val();              //单位职务
    var signAcct = $("input[name='mobile']").val();
    var pwd = $("input[name='pwd']").val();                        //第一次输入的密码
    var singPwd = $("input[name='password']").val();

    if(!isEmpty(name)) {
        alert("姓名不能为空！");
        return;
    }
    if(!isEmpty(idCard)){
        alert("身份证号不能为空！");
        return;
    }
    if(!isEmpty(political)){
        alert("政治面貌不能为空！");
        return;
    }
    if(!isEmpty(workUnit)){
        alert("处室不能为空！");
        return;
    }
    if(!isEmpty(signAcct)){
        alert("手机号不能为空！");
        return;
    }
    if(!isEmpty(pwd)){
        alert("设置密码不能为空！");
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
        url: contextPath+ "/www/sign/tsign",
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
            political: political[0].id,
            workUnitName: workUnit[0].id,
            postName: postName,
            uid: new Date().getTime()
        },
        success:function(result){
            if(result.status == 0) {
                alert(result.info);
                account = signAcct;
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
    if(file){
        coolFile.filetype = file.type;
        coolFile.size = file.size;
        coolFile.filename = file.name;
        reader.readAsBinaryString(file);
    }
}

function imgChange() {
    var idCardData = "";
    base64($('input[type="file"]'), function(data) {
        idCardData = data.base64;
        console.log("idCardData=================================="+idCardData);
        $.ajax({
            url: "https://api-cn.faceplusplus.com/cardpp/v1/ocridcard",
            type: "post",
            async: false,
            data: {
                "api_key": "-5Wf1CueJ8FffHLeEap4RtVOE77P6IQT",
                "api_secret": "dxWQqNdaXugnohd021ba1Cu_g4tfLmW3",
                "image_base64": idCardData
            },
            success: function(result) {
                console.log("====================" + result);
                $("input[name='name']").val(result.cards[0].name);
                $("input[name='idCard']").val(result.cards[0].id_card_number);
            },
            error: function() {
                console.log("出错了！");
                alert("出错了！");
            }
        });
    });
}

function cancel(){
    $("#bindModal").hide();
    $("body").removeClass("modalhide");
}

function bindWx(){
    var o={};
    o.account=account;
    $("#bindForm input[name='jsonData']").val(JSON.stringify(o));
    $("#bindForm").submit();
}