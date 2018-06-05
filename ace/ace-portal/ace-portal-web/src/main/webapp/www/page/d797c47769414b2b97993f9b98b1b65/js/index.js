function App() {

    console.log("=============================App Start==============================");
    loader({
        path: contextPath,
        url: '/www/page/d797c47769414b2b97993f9b98b1b65/css/style.css',
        type: 'css'
    });
	loader({
        path: contextPath,
        url: '/www/page/d797c47769414b2b97993f9b98b1b65/css/layer.css',
        type: 'css'
    });
    loader({
        path: contextPath,
        url: '/www/page/d797c47769414b2b97993f9b98b1b65/js/layer.js',
        type: 'js',
        callback: function () {
            layer.config({
                skin: 'layer-class'
            });
        }
    });

    getList();


    $(document).on('click', '.cover', function () {
        open_scroll = 0
        var top = 0;
        var url = $(this).data('url');
        var id = $(this).data('id');
        top = $(document).scrollTop();

        $('body').scrollTop(0);

        layer.open({
            title: '视频播放窗口',
            type: 2,
            content: url,
            btn: '关闭',
			closeBtn:1,
            resize: false,
            scrollbar: false,
            area: ['90%', '60%'],

            yes: function () {
                $('body').scrollTop(top);
                open_scroll = 1;
                layer.closeAll();

            },
            cancel: function () {

                $('body').scrollTop(top);

            },

        });

        /* $.post("./backend/viewcount.php", {
             id: id,
             ty: 'artical_count'
         }, function (data) {});*/

    });
}

function getList() {
    $.ajax({
        url: contextPath + '/www/getTplPageById.do',
        type: 'POST',
        timeout: 30000,
        data: {
            id: pageId
        },
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
            var html = juicer(carousel, {
                data: data
            });
            $("#myCarousel").append(html);

            var navigation = document.getElementById('tpl-navigation').innerHTML;
            var html = juicer(navigation, {
                data: data
            });
            $(".navigation").append(html);

            var navitem = document.getElementById('tpl-navitem').innerHTML;
            var html = juicer(navitem, {
                data: data
            });
            $("#navitem").append(html);
            $('.carousel').carousel();
            $("title").html(data.data.page.name);
            initTabs();
            if (loading) {
                loading.remove();
            }
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
        var id = $(this).data("id");
        console.log(id);
        var th_width = $(this).width();
        var th_left = $(this).offset().left;
        var slider_width = $(".news-slider").width();
        var slider_left = th_left + (th_width / 2) - slider_width / 2;
        $(".news-slider").css("left", slider_left);
        $(this).addClass("active").siblings().removeClass("active");
        $(".navitem").each(function (i, o) {
            console.log(o);
            console.log($(o).data('id'));
            if ($(o).data('id') == id) {
                $(o).css("display", 'block');
            } else {
                $(o).css("display", 'none');
            }
        });

    });
}
