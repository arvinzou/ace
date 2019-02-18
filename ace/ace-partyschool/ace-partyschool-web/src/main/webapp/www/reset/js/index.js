var countdown = 60;
function getCode(obj){
    var mobile = $("input[name = 'mobile']").val();
    if(mobile == '' || mobile == undefined){
        alert("请输入手机号！");
        return;
    }else{
        $.post( contextPath+"/www/sign/sendMsgByMobile",{mobile:mobile},function(result){
            if(result.status == 0){
               alert(result.info);
            }else{
                if(!result.inifo){
                   alert(result.info);
                }else{
                   alert(result.errorMessage);
                }
                return;
            }

        });
        settime();
    }
}

function settime(){
    var btnName = "获取验证码";
    if (countdown == 0) {
        btnName = "获取验证码";
        countdown = 50;
        stop = true;
    } else {
        stop = false;
        btnName = "重新获取 " + countdown + "";
        countdown--;
    }

    stop = stop;
    countdown = countdown;
    $('#getCode').text(btnName);
    $('#getCode').attr("disabled", true);
    if (!stop) {
        setTimeout(function () {
            this.settime()
        }, 1000)
    }else{
        $('#getCode').attr("disabled", false);
    }
}

function save(){
    var mobile = $("input[name='mobile']").val();
    var code = $("input[name='code']").val();
    var password = $("input[name='password']").val();
    if(mobile == "" || password == undefined){
        alert("请输入手机号码！");
        return;
    }else{
        var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
        if (!myreg.test(mobile)) {
            alert("手机号码输入不合法！");
            return;
        }
    }
    if(code == "" || password == undefined){
        alert("请输入验证码！");
        return;
    }
    if(password == "" || password == undefined){
        alert("请输入新密码！");
        return;
    }
    if(password.length <=4){
        alert("密码输入必须是4位及以上！");
        return;
    }
    $.ajax({
        url: contextPath + "/www/sign/updatePwd",
        type: "post",
        async: false,
        data: {
            mobile: mobile,
            account: mobile,
            code: code,
            newPwd: password
        },
        success: function(result) {
            alert(result.info);
            window.location.href = contextPath+"/www/login/index.jsp";
        },
        error: function() {
            alert("系统服务内部异常!");
        }
    });
}