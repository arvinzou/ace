
var nickName = '';
var headImg = '';
var mobile = '';
function userinfo(){ 
	$.get( portalPath+"/www/oauth2/userinfo.do",{},function(result){
	    	console.log(result);
	    	if(result){
	    		nickName = result.nickname;
	    		headImg = result.headimgurl;
	    		$(".user_name").text(nickName);
	    		$("#headImg").attr("src",headImg);
	    	}else{
	    		$(".user_name").text("未登录");
	    	}
	    	if(result.mobile != ''){
	    		mobile = result.data.mobile;
	    		$(".user_name").text(result.data.name);
	    		//手机号码不为空，证明有绑定信息
	    		$(".badding").attr("hidden",true);
	    		$(".cancle").attr("hidden",false);
	    		
	    		//查询绑定信息
	    		$.get( server+"/woc/www/api/getPerson",{},function(ret){
	    			console.log(ret);
	    			$("#driverLicence").text("驾驶证号码"+ret.data.driverLicenseCode);
	  			});
	    	}
	  	});
}

/**
 * 取消绑定
 */
function cancelBand(){

	$.post( server+"/woc/www/api/unbindMobile",{},function(ret){
	    console.log(ret);
	    alert(ret.info);
	    if(ret.status == '0'){
	    	$(".badding").attr("hidden",false);
	    	$(".cancle").attr("hidden",true);
	    }			
	 });
}
function App(){
    console.log("start App");
	userinfo();
}