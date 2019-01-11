var errorInfo = null;
$(function(){
    var locaUrl = window.location.href;
    var url = window.location.href.substring(locaUrl.indexOf("?")+1);
    var paramArr = url.split("&");
    for(var i=0;i < paramArr.length;i++){
        num=paramArr[i].indexOf("=");
        if(num>0){
            name=paramArr[i].substring(0,num);
            value=paramArr[i].substr(num+1);
            if(name == "error"){
                errorInfo = value;
            }
        }
    }
    if(errorInfo){
        alert(errorInfo);
    }
});

function login(){
    var acct = $("input[name='username']").val();
    var pwd = $("input[name='password']").val();
    if(acct == '' || acct == undefined){
        alert("请输入用户名!");
        return;
    }
    if(pwd == '' || pwd == undefined){
        alert("密码不能为空！");
        return;
    }
    $.ajax({
        url: contextPath+ "/www/sign/acctLogin",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
            acct:  acct,
            pwd: pwd
        },
        success:function(result){
            if(result.status == 0) {
               window.location.href = contextPath + '/www/registered/person/info.jsp'
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

function wxLogin(){
    $("#bindForm").submit();
}

function checkLogin(){
    var acct = $("input[name='username']").val();
    var pwd = $("input[name='password']").val();

    if(acct != undefined && acct!="" && pwd!=undefined && pwd != ""){
        $("#loginActive").show();
        $("#login").hide();
        $("#loginActive").click(login);
    }else{
        $("#loginActive").hide();
        $("#login").show();
    }
}

function resetPwd(){
    window.location.href = contextPath + "/www/reset/index.jsp";
}

function initData(){
    $.ajax({
        url: contextPath+ "/www/sign/getAcctInfo",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
        },
        success:function(result){
            if(result.status == 0) {
                isWxBind = result.data.isBindWx;
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