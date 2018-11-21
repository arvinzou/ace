$(function(){
    $(".menu-nav ul li a").eq(0).addClass("checked");
    $(".menu-nav ul li").click(function () {
        $(this).find("a").addClass("checked");
        $(this).siblings().find("a").removeClass("checked");
    });
});
