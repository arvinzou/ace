/**
 * 导航栏
 */

$(function(){
	var th_width = $(".news-module li").eq(0).width();
	var th_left = $(".news-module li").eq(0).position().left;
	var slider_width = $(".news-slider").width();
	var slider_left = th_left + (th_width/2) - slider_width/2;
	$(".news-slider").css("left",slider_left-8);
	$(".news-module li").on("click",function(){
	    var n = $(this).index();
	    var th_width = $(this).width();
	    var th_left = $(this).position().left;
	    var slider_width = $(".news-slider").width();
	    var slider_left = th_left + (th_width/2) - slider_width/2;
	    $(".news-slider").css("left",slider_left);
	    $(this).addClass("active").siblings().removeClass("active");
	});
});
