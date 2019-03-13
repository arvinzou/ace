$(function(){
    var acct = localStorage.getItem("username");
    var pwd = localStorage.getItem("password");
    if(acct == undefined || acct == null || pwd == undefined || pwd == null){
        window.location.href = contextPath + '/www/login/index.jsp';
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

        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
});