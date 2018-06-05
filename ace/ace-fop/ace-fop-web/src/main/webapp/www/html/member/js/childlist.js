$(function(){
    parent.document.getElementById("iframe").height= 700;
    parent.document.getElementById("iframe").height=document.documentElement.scrollHeight;

    $(".menu-nav ul li a").eq(0).addClass("checked");
    $(".menu-nav ul li").hover(function(){
        $(this).find("a").addClass("checked");
        $(this).siblings().find("a").removeClass("checked");
    });
});
