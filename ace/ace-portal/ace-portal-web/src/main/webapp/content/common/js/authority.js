jQuery(function($) {
	console.log('authority');
	if(!authorConfig){
		console.log("authorConfig is empty");
		$.each($('button'),function(i,obj){
			console.log($(obj).attr("authority"));
			obj.disabled=true;
			if(obj.disabled){
				$(obj).hide();
			}else{
				$(obj).show();
			}
		});
	}else{
		console.log(authorConfig);
		$.each($('button'),function(i,obj){
			obj.disabled=!authorConfig.hasOwnProperty($(obj).attr("authority"));
			if($(obj).attr("authority")=='false'){
				obj.disabled=false;
			}
			console.log($(obj).attr("authority"));
			console.log(obj.disabled==true?'FAIL':'OK');
			var agent = navigator.userAgent.toLowerCase() ;
			if(agent.indexOf("chrome") == -1){
				if($(obj).text()==null||$(obj).text()==""||$(obj).text()==" "||$(obj).text()=="  "){
					$(obj).prepend(authorConfig[$(obj).attr("authority")])
					//$(obj).text(authorConfig[$(obj).attr("authority")]);
				}
			}else{
				if($(obj).text()==null||$(obj).text().trim()==""){
					$(obj).prepend(authorConfig[$(obj).attr("authority")])
					//$(obj).text(authorConfig[$(obj).attr("authority")]);
				}
			}
			
			if(obj.disabled&&$(obj).attr("authority")){
				$(obj).hide();
			}else{
				$(obj).show();
			}
			//console.log($(obj).text());
		});
	}
	//console.log(systemUser.users);
});