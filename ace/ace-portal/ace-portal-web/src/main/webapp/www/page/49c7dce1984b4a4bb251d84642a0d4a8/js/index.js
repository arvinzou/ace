function App() {
    console.log("=============================App Start==============================");
    loader({
        path: contextPath,
        url: '/www/page/49c7dce1984b4a4bb251d84642a0d4a8/css/style.css',
        type: 'css'
    });
		
    getList();
}

function getList() {
    $.ajax({
        url: contextPath + '/www/getTplPageById.do',
        type: 'POST',
        timeout: 30000,
				data:{id:pageId},
        dataType: 'json',
        beforeSend: function () {
            try {
                loading = startLoading();
            } catch (e) {};
            if (loading) {
                loading.settext("正在加载，请稍后......");
            }
        },
        success: function (data) {
            console.log(data);
            var carousel = document.getElementById('tpl-carousel').innerHTML;
            var covers = data.data.covers;
            var newcovers = [];
            for(var i=2; i>=0; i--){
                newcovers.push(covers[i]);
            }
            data.data.covers = newcovers;
            data.data.category=category;
            var html = juicer(carousel, {
                data: data
            });
            $("#myCarousel").append(html);
			var navigation = document.getElementById('tpl-navigation').innerHTML;
			if(category){
			    navigation = document.getElementById('tpl-navigation-category').innerHTML;
			}
            var html = juicer(navigation, {
                data: data
            });
            $(".navigation").append(html);
			var navitem = document.getElementById('tpl-navitem').innerHTML;
			if(category){
                navitem = document.getElementById('tpl-navitem-category').innerHTML;
            }
            var html = juicer(navitem, {
                data: data
            });
            $("#navitem").append(html);
            $('.carousel').carousel();
            $("title").html(data.data.page.name);
            if(data.data.categorys){
                var len=data.data.categorys.length;
                $(".news-module li").css("width",(100/len)+"%");
                if(len==1){
                    $(".navigation").css("display",'none');
                }
            }
            initTabs();
            if(category){
                var th_width = $(".navigation .active").width();
                var th_left = $(".navigation .active").offset().left;
                var slider_width = $(".news-slider").width();
                var slider_left = th_left + (th_width / 2) - slider_width / 2;
                $(".news-slider").css("left", slider_left);
            }
            if (loading) {
                loading.remove();
            }
            getCover();
        }
    });

}

function initTabs() {
    var th_width = $(".news-module li").eq(0).width();
    var th_left = $(".news-module li").eq(0).offset().left;
    var slider_width = $(".news-slider").width();
    var slider_left = th_left + (th_width / 2) - slider_width / 2;
    $(".news-slider").css("left", slider_left);
    $(".news-module li").on("click", function () {
        var n = $(this).index();
		var id=$(this).data("id");
		console.log(id);
        var th_width = $(this).width();
        var th_left = $(this).offset().left;
        var slider_width = $(".news-slider").width();
        var slider_left = th_left + (th_width / 2) - slider_width / 2;
        $(".news-slider").css("left", slider_left);
        $(this).addClass("active").siblings().removeClass("active");
        $(".navitem").each(function(i,o){
                console.log(o);
                console.log($(o).data('id'));
                if($(o).data('id')==id){
                    $(o).css("display",'block');
                }else{
                    $(o).css("display",'none');
                }
        });
    });
}


function getCover(){
 var url = portalPath + '/www/getById.do';
    $.ajax({
        url: url,
        type: 'POST',
        timeout: 30000,
        dataType: 'json',
        data: {
            id:pageId
        },
        beforeSend: function () {
        },
        success: function (data) {
            console.log(data);
            if (data.status == 0) {
              if(data.data.cover){
                    var baner = document.getElementById('tpl-baner').innerHTML;
                    var html = juicer(baner, {
                        data: data
                    });
                    $("#myCarousel").html(html);
              }else{

              }
            } else {
                alert(data.errorMessage);
            }
        }
    });
}