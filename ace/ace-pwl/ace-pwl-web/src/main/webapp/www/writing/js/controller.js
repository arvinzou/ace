jQuery(function ($) {
    loadText(null, author, 1, 99999);
});

function t_query() {
    start = 0;
    $(".weui_grids").html("");
    var name = $("#searchInput").val();
    loadText(name, author, 1, 99999);
    return false;
}

function loadText(name, author, start, limit) {
    $
        .ajax({
            type: "post",
            url: contextPath + "/www/anslysis/query.do",
            data: {
                author: author,
                category: category,
                name: name,
                page: start,
                limit: limit,
                reportId: 'writing'
            },
            beforeSend: function (XMLHttpRequest) {
                $.showLoading();
            },
            success: function (rst, textStatus) {
                console.log(rst);
                $.hideLoading();
                var html = [];
                var c = 0;
                $(rst.rows)
                    .each(
                        function (n, o) {
                            html
                                .push(' <a href="preview.jsp?id='
                                    + o.id
                                    + '" class="weui_grid js_grid">');
                            html
                                .push(' <div class="weui_grid_icon2">');
                            html
                                .push(' <img src="'
                                    + fastdfs_server
                                    + o.docText
                                    + '" class="Absolute-Center is-Image" alt=""> ');
                            html.push(' </div>');

                            html
                                .push(' <p class="weui_grid_label">'
                                    + o.name + '</p>');

                            html.push(' </a>');
                            c++;
                        });
                $(".weui_grids").append(html.join(" "));

                var imglist = document.getElementsByTagName("img");
                for (var i = 0, len = imglist.length; i < len; i++) {
                    //AutoResizeImage(100,100,imglist[i]);
                }
                if (c == 0) {
                    //$.alert("没有找到数据！", "很抱歉！");
                }
                $("#time").text(new Date);

                $(document.body).pullToRefreshDone();
            },
            error: function () {
                $.hideLoading();
                alert("加载错误！");
            }
        });
}

function AutoResizeImage(maxWidth, maxHeight, objImg) {
    console.log(objImg.width);
    console.log(objImg.height);
    var img = new Image();
    img.src = objImg.src;
    var hRatio;
    var wRatio;
    var Ratio = 1;
    var w = img.width;
    var h = img.height;
    wRatio = maxWidth / w;
    hRatio = maxHeight / h;
    if (maxWidth == 0 && maxHeight == 0) {
        Ratio = 1;
    } else if (maxWidth == 0) {//
        if (hRatio < 1)
            Ratio = hRatio;
    } else if (maxHeight == 0) {
        if (wRatio < 1)
            Ratio = wRatio;
    } else if (wRatio < 1 || hRatio < 1) {
        Ratio = (wRatio <= hRatio ? wRatio : hRatio);
    }
    if (Ratio < 1) {
        w = w * Ratio;
        h = h * Ratio;
    }
    objImg.height = h;
    objImg.width = w;

    console.log(objImg.width);
    console.log(objImg.height);
}
