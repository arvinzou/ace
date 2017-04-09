
function insertFeedback(j_captcha_weui,title,docText) {
	$.ajax({
		type : "post",
		url : contextPath+"/www/feedback/insertFeedback.do",
		data : {
			title : title,
			docText:docText,
			j_captcha_weui:j_captcha_weui
		},
		beforeSend : function(XMLHttpRequest) {
             $.showLoading();
		},
		success : function(rst, textStatus) {
            $.hideLoading();
            if(rst.state){
                location.href=contextPath+"/dynamic/www/feedback/succefull.jsp";
            }else{
                $.alert(rst.errorMessage);
            }

		},
		error : function() {
		    $.hideLoading();
		}
	});
}

jQuery(function($) {
     $("#showTooltips").click(function() {
            var title = $('#title').val();
            var docText = $('#docText').val();
            var j_captcha_weui = $('#j_captcha_weui').val();
            if(!title){
                $.toptip('请输入标题');
            }else if(!docText) {
                $.toptip('请输入建议');
             }else if(!j_captcha_weui){
                $.toptip('请输入验证码');
             } else {
                //$.toptip('提交成功', 'success');
                insertFeedback(j_captcha_weui,title,docText);
             }

      });
});


function t_submit(){
    return false;
}
