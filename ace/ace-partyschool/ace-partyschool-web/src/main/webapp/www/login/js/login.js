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
    }else{
        $("#loginActive").hide();
        $("#login").show();
    }
}