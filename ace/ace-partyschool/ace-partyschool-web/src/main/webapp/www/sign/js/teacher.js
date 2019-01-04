var political = null;
$(function(){
    var politicalArr = [{"id":"normal","value":"普通学员"},{"id":"party","value":"党员"}];
	
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
    var workUnitName = $("input[name='workUnitName']").val();     //单位名称
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
            workUnitName: workUnitName,
            postName: postName,
            uid: new Date().getTime()
        },
        success:function(result){
            if(result.status == 0) {
                alert(result.info);
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