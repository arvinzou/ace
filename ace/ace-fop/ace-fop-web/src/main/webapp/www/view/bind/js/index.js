
var countdown = 50;
var stop = true;
function getNumFun(){
	var mobile = $("#mobile").val();
	if(mobile == '' || mobile == undefined){
		alert("请输入手机号码！");
	}else{
		$.post( portalPath+"/www/oauth2/sendCmccByMobile.do",{mobile:mobile,signature:'常德市工商联'},function(result){
	    	console.log(result);

	    	alert(result.errorMessage);
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
	$.post( portalPath+"/www/oauth2/bind.do",{mobile:mobile, captcha: captcha},function(result){
	    	console.log(result);
	    	alert(result.errorMessage);
	    	if(result.status == '0'&&result.value.status == '0'){
	    		location.href='../me/index.jsp?t='+new Date();
	    	}
	 });
}
function App(){
	console.log("start App");
	
}