function loadlocal() {
    var urls = [];
    urls.push({path: contextPath, url: '/www/view/common/js/nav.js', type: 'js'});
    for (var i = 0; i < urls.length; i++) {
        loader(urls[i]);
    }
}
function App() {
   /* loadlocal();*/
}

window.onload = function(){
    startLoad();
    renderPage($("#menuObject"), staticDictObject['149'], "menuObject-tpl");
    courseList();
    initSwriper();
    // var th_width = $(".news-module li").eq(0).width();
    // var th_left = $(".news-module li").eq(0).position().left;
    // var slider_width = $(".news-slider").width();
    // var slider_left = th_left + (th_width/2) - slider_width/2;
    // $(".news-slider").css("left",slider_left-8);
    $(".swiper-container .swiper-slide").on("click",function(){
        $(this).addClass("swiper-slide-active").siblings().removeClass("swiper-slide-active");
    });

    var level = $(".course_nav_ul .active").text();
    if(level == "全部"){
        level = null;
    }
    $(document).not($(".selectbox")).click(function(){
        $(".citylist,.citylist2").slideUp();
    })
    $(".selectbox").click(function(event){
        event.stopPropagation();
        courseList(level);
    });
    $(".selemenu").click(function(){
        $(this).next().slideToggle();
        $(this).parents().siblings().find(".citylist,.citylist2").slideUp();
        courseList(level);
    })
    $(".citylist span").click(function(){
        var text=$(this).text();
        $(this).parent().prev().html(text);
        $(this).parent().prev().css("color","#666")
        $(this).parent().fadeOut();

    })
    $(".shangquan li").click(function(){
        $(".shangquan li").removeClass("active")
        $(this).addClass("active")
        var text1=$(this).text();
        var value1 = $(this).attr('data-value');
        $("#ability").val(value1);
    })
    $(".chengshi li").click(function(){
        $(".chengshi li").removeClass("active")
        $(this).addClass("active")
        var text2=$(this).text();
        $("#csinput").val(text2);
    })
    $(".qu li").click(function(){
        $(".qu li").removeClass("active")
        $(this).addClass("active")
        var text3=$(this).text();
        $("#quinput").val(text3);
        $(".citylist2").slideUp();
    });
    completeLoading();
    document.onreadystatechange = completeLoading;
}

function courseList(level){
    var type = null;
    var costType = null;
    var purport = $("#ability").val();    //针对能力
    var typeName = $("#csinput").val();   //课程类别
    var payType = $("#quinput").val();     //付费方式
    if(purport == '' || purport == undefined || purport == null){
        purport = null;
    }
    if(typeName == '单节课程'){
        type = "1";
    }else if(typeName == "系列课程"){
        type = "2";
    }
    if(payType == '免费'){
        costType = "0";
    }else if(payType == '付费'){
        costType = "1";
    }
    $.ajax({
        url: contextPath+ "/www/course/findList",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
            start: 0,
            limit: 999,
            objects:level,
            auditRst:'1',
            purport: purport,
            type: type,
            costType: costType,
            lineState: '1'
        },
        success:function(result){
            if(result.status == 0) {
                var courseTemp = document.getElementById('courseTemp').innerHTML;
                var html = juicer(courseTemp, {
                    data: result.data.rows
                });
                $("#courseList").html(html);
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

function courseDetail(id){
    window.location.href = contextPath + '/www/view/courseSummary/index.jsp?id='+id;
}

function completeLoading() {
    if (document.readyState == "complete") {
        stopLoad();
    }
}

function renderPage(dom, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(dom).html(html);
}

/*初始化Swriper*/
function initSwriper() {
    var menuSwiper = new Swiper('.swiper-container', {
        spaceBetween: 10,
        slidesPerView: 'auto'
    })
}