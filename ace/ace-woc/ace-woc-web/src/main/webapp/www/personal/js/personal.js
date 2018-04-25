var server = "http://zx.huacainfo.com";
var countdown = 50;
var stop = true;

function getNumFun(){
	var mobile = $("#mobile").val();
	if(mobile == '' || mobile == undefined){
		alert("请输入手机号码！");
	}else{
		$.post( server+"/woc/www/api/sendCmccByMobile",{mobile:mobile},function(result){
	    	console.log(result);
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
            btnName = "重新发送 " + countdown + "";
            countdown--;
        }
        
       stop = stop;
       countdown = countdown;
       $('#getNumBtn').text(btnName);
        if (!stop) {
            setTimeout(function () {
                this.settime()
            }, 1000)
        }
}
function bandFun(){
	
	var mobile = $("#mobile").val();
	var captcha = $("#codeNum").val();
	$.post( server+"/woc/www/api/mobileRegister",{mobile:mobile, captcha: captcha},function(result){
	    	console.log(result);
	 });
}
