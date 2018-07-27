var status = 0;
var regType = null ; //注册类型，1是老师，2是家长
var countdown = 50;   //验证码计时器
$(function(){
	$(document).on("swipeleft", ".ui-page", function () {
        var thePage = $(this),
                next = thePage.jqmData("next");
        if (next) {
            $(":mobile-pagecontainer").pagecontainer("change", "#" + next, {transition: "slide"});
        }
    });
    $(document).on("swiperight", ".ui-page", function () {
        var thePage = $(this),
                prev = thePage.jqmData("prev");
        if (prev) {
            $(":mobile-pagecontainer").pagecontainer("change", "#" + prev, {transition: "slide", reverse: true});
        }
    });

    /**
	 * 获取用户信息
     */
    $.ajax({
        url: "/jxb/www/reg/findInfo",
        type:"post",
        async:false,
        data:{

        },
        success:function(result){
            if(result.status == 0) {
               console.log(result);
            }else {
                if(result.data == 'unregister'){   //当前用户尚未注册
                    alert(result.info);
                }
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
});

function selectType(id){
	if(id == 'parent'){
		$("#"+id).attr('src','img/parent.png');
		$("#teacher").attr('src','img/unteacher.png');
        regType = 2;
	}else{
		$("#"+id).attr('src','img/teacher.png');
		$("#parent").attr('src','img/unparent.png');
        regType = 1;
	}
	$("#nextStep").children().attr('src','img/next_active.png');
	$("#nextStep").attr('href','#page2');
}
    
function phoneInput(){
	var phoneNum = $("input[name='phoneNum']").val();
	if(phoneNum != '' && phoneNum != undefined){
		$("#confirm").attr('src','img/confirm_active.png');
        $(".telphone").text("+86"+phoneNum);
	}else{
		$("#confirm").attr('src','img/confirm_unactive.png');
	}
}

function codeInput(){
	var code = $("input[name='code']").val();
	if(code != '' && code != undefined){
		$("#login").attr('src','img/login_active.png');
	}else{
		$("#login").attr('src','img/login_unactive.png');
	}
}

function sendCode(){
    var phoneNum = $("input[name='phoneNum']").val();
    if(phoneNum == '' || phoneNum == undefined){
        alert("手机号码不能为空！");
    }else{
        $.ajax({
            url: "/jxb/www/reg/sendCode",
            type:"post",
            async:false,
            data:{
                mobile: phoneNum
            },
            success:function(result){
                if(result.status == 0) {
                    console.log(result);
                    alert("短信发送成功，请注意查收！");
                    $("#sendCode").attr('href', '#page3');
                }else {
                   alert(result.info);
                    return;
                }
            },
            error:function(){
                alert("系统服务内部异常！");
            }
        });
        settime();
    }
}

function registon(){
    var code = $("input[name='code']").val();
    var phoneNum = $("input[name='phoneNum']").val();
    if(code == '' || code == undefined){
        alert("验证码不能为空！");
    }else{
        $.ajax({
            url: "/jxb/www/reg/register",
            type:"post",
            async:false,
            data:{
                regType: regType,
                mobile:phoneNum,
                code:code
            },
            success:function(result){
                if(result.status == 0) {
                    console.log(result);
                    alert("注册成功！");
                    window.location.href = '/jxb/www/view/mine/mine.jsp';
                }else {
                    alert(result.info);
                    return;
                }
            },
            error:function(){
                alert("系统服务内部异常！");
            }
        });
    }
}

function settime(){
    var btnName = "<a href='#page2'>获取验证码</a>";
    if (countdown == 0) {
        btnName = "<a href='#page2'>获取验证码</a>";
        countdown = 50;
        stop = true;
    } else {
        stop = false;
        btnName =  countdown +"秒";
        countdown--;
    }

    stop = stop;
    countdown = countdown;
    $('.timeleft').html(btnName);
    if (!stop) {
        setTimeout(function () {
            this.settime()
        }, 1000)
    }
}
