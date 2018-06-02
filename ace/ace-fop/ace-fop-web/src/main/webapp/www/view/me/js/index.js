
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
	    	if(result.userProp){
	    		mobile = result.userProp.tel;
	    		$(".user_name").text(result.userProp.name);
				$(".corpName").text(result.userProp.corpName);
	    		//$(".badding").attr("hidden",true);
	    		//$(".cancle").attr("hidden",false);
				
				$(".badding").attr("hidden",false);
	    		$(".cancle").attr("hidden",true);	
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