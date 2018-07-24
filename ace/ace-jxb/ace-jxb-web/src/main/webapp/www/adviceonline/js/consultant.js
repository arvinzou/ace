$(function(){ 
	$('.retrie dt a').click(function(){
		var $t=$(this);
		if($t.hasClass('up')){
			$(".retrie dt a").removeClass('up');
			$('.downlist').hide();
			$(".lode").hide();
		}else{
			$(".retrie dt a").removeClass('up');
			$('.downlist').hide();
			$t.addClass('up');
			$('.downlist').eq($(".retrie dt a").index($(this)[0])).show();
			$(".lode").show();
		}
	});
	$(".area ul li a:contains('"+$('#area').text()+"')").addClass('selected');
	$(".wage ul li a:contains('"+$('#wage').text()+"')").addClass('selected');
	
});

function screen01(obj){
	$(obj).addClass("checked");
	$(obj).siblings().removeClass("checked");
	$("#area").text($(obj).children().text());
	$('.downlist').hide();
	$(".lode").hide();
}

function screen02(obj){
	$(obj).addClass("checked");
	$(obj).siblings().removeClass("checked");
	$("#wage").text($(obj).children().text());
	$('.downlist').hide();
	$(".lode").hide();
}
function screen03(obj){
	$(obj).addClass("checked");
	$(obj).siblings().removeClass("checked");
	$("#sort").text($(obj).children().text());
	$('.downlist').hide();
	$(".lode").hide();
}
