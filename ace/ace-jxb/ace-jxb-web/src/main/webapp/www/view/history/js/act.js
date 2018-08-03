function App() {
    console.log("=============================App Start==============================");

    initSilder();
    $(".news-module li").on("click",function(){
        var n = $(this).index();
        var th_width = $(this).width();
        var th_left = $(this).offset().left;
        var slider_width = $(".news-slider").width();
        var slider_left = th_left + (th_width/2) - slider_width/2;
        $(".news-slider").css("left",slider_left);
        $(this).addClass("active").siblings().removeClass("active");
    });

    initData();
}

function  initSilder() {
    var th_width = $(".news-module li").eq(0).width();
    var th_left = $(".news-module li").eq(0).offset().left;
    var slider_width = $(".news-slider").width();
    var slider_left = (th_left + (th_width/2) - slider_width/2)-8;
    $(".news-slider").css("left",slider_left);
}

function initData(){
    $.ajax({
        url: contextPath+ "/www/order/findList",
        type:"post",
        async:false,
        data:{
            start: 0,
            limit: 999
        },
        success:function(result){
            if(result.status == 0) {
               /* var roombaseTemp = document.getElementById('roomInfoTemp').innerHTML;
                var html = juicer(roombaseTemp, {
                    roombase: result.data
                });
                $(".room_info").append(html);*/
            }else {
                alert(result.info);
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}

function orderList(data){
    $.ajax({
        url: contextPath+ "/www/order/findList",
        type:"post",
        async:false,
        data:{
            payStatusArray: data,
            start: 0,
            limit: 999
        },
        success:function(result){
            if(result.status == 0) {
              /*  var roombaseTemp = document.getElementById('roomInfoTemp').innerHTML;
                var html = juicer(roombaseTemp, {
                    roombase: result.data
                });
                $(".room_info").append(html);*/
            }else {
                alert(result.info);
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}