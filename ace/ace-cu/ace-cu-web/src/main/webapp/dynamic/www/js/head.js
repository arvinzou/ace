// JavaScript Document

$(document).ready(function () {
    var winwidth = $(window).width()
    if (winwidth < 1240) {

        $(".menuBox").css({width: 1000})
        $(".menuBox").css({marginLeft: '-500px'})

    }
//头部下拉菜单	 
    var menuX = $(".menuTab .active").index();//记录原始位
    var menuoff = 0;//判断是否有二级


    $(".menuTab li").on("mouseenter", function () {

        //获取显示图层id
        $(".menuTab li").removeClass("active")//清除光标停留
        var menubox = "#" + $(this).attr("class")//清除光标停留
        var nb = $(menubox).length;

        if (nb == 1) {

            $(".menuBox").show();
            $(this).addClass("active");
            $(".menuBox .menumain").css({display: 'none'});
            $(menubox).fadeIn();
        } else {


            $(this).addClass("active")
            $(".menuBox .menumain").fadeOut()
            $(".menuBox").fadeOut()


        }


    })


    $(".head_menu").on("mouseleave", function () {

        $(".menuTab li").removeClass("active")
        $(".menuTab li").eq(menuX).addClass("active")  //回复原始位置
        $(".menuBox .menumain").fadeOut()
        $(".menuBox").fadeOut()

    })


});