jQuery(function ($) {
    loadText(id);
});

function loadText(id) {
    $.ajax({
        type: "post",
        url: contextPath + "/www/anslysis/query.do",
        data: {
            id: id,
            page: 0,
            limit: 1,
            reportId: 'loadWriter'
        },
        beforeSend: function (XMLHttpRequest) {
            $.showLoading();
        },
        success: function (rst, textStatus) {
            console.log(rst);
            $.hideLoading();
            var html = [];
            var c = 0;
            $(rst.rows).each(function (n, o) {
                $("title").html(o.name);
                $.each(o, function (key, value) {

                    if (key == 'photo') {
                        var src = fastdfs_server + value;
                        var img = new Image();
                        $(img).attr("src", "");
                        $(img).css("width", "170");
                        $(img).css("height", "170");
                        //图片加载加载后执行
                        $(img).load(function () {
                            //图片默认隐藏
                            $(this).hide();
                            //移除小动画
                            $(".loading").removeClass("loading").append(this);
                            //使用fadeIn特效
                            $(this).fadeIn("slow");
                        }).error(function () {
                            //加载失败时的处理
                        })
                        //最后设置src
                            .attr("src", src);
                    } else {
                        $("#" + key).html(value);

                    }

                })
                c++;
            });
            $("img").each(
                function () {
                    var image = $(this);
                    $(image).wrap("<a href='" + $(image).attr("src")
                        + "'  data-size='600x600'/>");
                    $(image).parent().wrap("<figure/>");
                });
            doDraw();
            initPhotoSwipeFromDOM('.my-gallery');
            doDraw();
            if (c == 0) {
                // $.alert("没有找到数据！", "很抱歉！");
            }
        },
        error: function () {
            $.hideLoading();
            alert("加载错误！");
        }
    });
}


var imglist;
//安卓4.0+等高版本不支持window.screen.width，安卓2.3.3系统支持
var _width;

window.onresize = function () {
    //捕捉屏幕窗口变化，始终保证图片根据屏幕宽度合理显示
    doDraw();
}

function doDraw() {
    _width = window.innerWidth;
    imglist = document.getElementsByTagName("img");
    for (var i = 0, len = imglist.length; i < len; i++) {
        DrawImage(imglist[i], _width - 50);
    }
}

function DrawImage(ImgD, _width) {
    var image = new Image();
    image.src = ImgD.src;
    image.onload = function () {
        //限制，只对宽高都大于30的图片做显示处理
        if (image.width > 600 || image.height > 600) {
            if (image.width > _width) {
                ImgD.width = _width;
                ImgD.height = (image.height * _width) / image.width;
            } else {
                ImgD.width = image.width;
                ImgD.height = image.height;
            }

        }
        $(ImgD).parent().attr("data-size",
            (ImgD.width * 2) + "x" + (ImgD.height * 2));
    }

}

var initPhotoSwipeFromDOM = function (gallerySelector) {

    // 解析来自DOM元素幻灯片数据（URL，标题，大小...）
    // (children of gallerySelector)
    var parseThumbnailElements = function (el) {
        var thumbElements = el.childNodes, numNodes = thumbElements.length, items = [], figureEl, linkEl, size, item;

        for (var i = 0; i < numNodes; i++) {

            figureEl = thumbElements[i]; // <figure> element

            // 仅包括元素节点
            if (figureEl.nodeType !== 1) {
                continue;
            }
            linkEl = figureEl.children[0]; // <a> element

            size = linkEl.getAttribute('data-size').split('x');

            // 创建幻灯片对象
            item = {
                src: linkEl.getAttribute('href'),
                w: parseInt(size[0], 10),
                h: parseInt(size[1], 10)
            };

            if (figureEl.children.length > 1) {
                // <figcaption> content
                item.title = figureEl.children[1].innerHTML;
            }

            if (linkEl.children.length > 0) {
                // <img> 缩略图节点, 检索缩略图网址
                item.msrc = linkEl.children[0].getAttribute('src');
            }

            item.el = figureEl; // 保存链接元素 for getThumbBoundsFn
            items.push(item);
        }

        return items;
    };

    // 查找最近的父节点
    var closest = function closest(el, fn) {
        return el && (fn(el) ? el : closest(el.parentNode, fn));
    };

    // 当用户点击缩略图触发
    var onThumbnailsClick = function (e) {
        e = e || window.event;
        e.preventDefault ? e.preventDefault() : e.returnValue = false;

        var eTarget = e.target || e.srcElement;

        // find root element of slide
        var clickedListItem = closest(eTarget, function (el) {
            return (el.tagName && el.tagName.toUpperCase() === 'FIGURE');
        });

        if (!clickedListItem) {
            return;
        }

        // find index of clicked item by looping through all child nodes
        // alternatively, you may define index via data- attribute
        var clickedGallery = clickedListItem.parentNode, childNodes = clickedListItem.parentNode.childNodes,
            numChildNodes = childNodes.length, nodeIndex = 0, index;

        for (var i = 0; i < numChildNodes; i++) {
            if (childNodes[i].nodeType !== 1) {
                continue;
            }

            if (childNodes[i] === clickedListItem) {
                index = nodeIndex;
                break;
            }
            nodeIndex++;
        }

        if (index >= 0) {
            // open PhotoSwipe if valid index found
            openPhotoSwipe(index, clickedGallery);
        }
        return false;
    };

    // parse picture index and gallery index from URL (#&pid=1&gid=2)
    var photoswipeParseHash = function () {
        var hash = window.location.hash.substring(1), params = {};

        if (hash.length < 5) {
            return params;
        }

        var vars = hash.split('&');
        for (var i = 0; i < vars.length; i++) {
            if (!vars[i]) {
                continue;
            }
            var pair = vars[i].split('=');
            if (pair.length < 2) {
                continue;
            }
            params[pair[0]] = pair[1];
        }

        if (params.gid) {
            params.gid = parseInt(params.gid, 10);
        }

        return params;
    };

    var openPhotoSwipe = function (index, galleryElement, disableAnimation,
                                   fromURL) {
        var pswpElement = document.querySelectorAll('.pswp')[0], gallery, options, items;

        items = parseThumbnailElements(galleryElement);

        // 这里可以定义参数
        options = {
            barsSize: {
                top: 100,
                bottom: 100
            },
            fullscreenEl: false, // 是否支持全屏按钮
            shareButtons: [{
                id: 'download',
                label: '保存图片',
                url: '{{raw_image_url}}',
                download: true
            }], // 分享按钮

            // define gallery index (for URL)
            galleryUID: galleryElement.getAttribute('data-pswp-uid'),

            getThumbBoundsFn: function (index) {
                // See Options -> getThumbBoundsFn section of documentation for more info
                var thumbnail = items[index].el.getElementsByTagName('img')[0], // find thumbnail
                    pageYScroll = window.pageYOffset
                        || document.documentElement.scrollTop, rect = thumbnail
                        .getBoundingClientRect();

                return {
                    x: rect.left,
                    y: rect.top + pageYScroll,
                    w: rect.width
                };
            }

        };

        // PhotoSwipe opened from URL
        if (fromURL) {
            if (options.galleryPIDs) {
                // parse real index when custom PIDs are used
                for (var j = 0; j < items.length; j++) {
                    if (items[j].pid == index) {
                        options.index = j;
                        break;
                    }
                }
            } else {
                // in URL indexes start from 1
                options.index = parseInt(index, 10) - 1;
            }
        } else {
            options.index = parseInt(index, 10);
        }

        // exit if index not found
        if (isNaN(options.index)) {
            return;
        }

        if (disableAnimation) {
            options.showAnimationDuration = 0;
        }

        // Pass data to PhotoSwipe and initialize it
        gallery = new PhotoSwipe(pswpElement, PhotoSwipeUI_Default, items,
            options);
        gallery.init();
    };

    // loop through all gallery elements and bind events
    var galleryElements = document.querySelectorAll(gallerySelector);

    for (var i = 0, l = galleryElements.length; i < l; i++) {
        galleryElements[i].setAttribute('data-pswp-uid', i + 1);
        galleryElements[i].onclick = onThumbnailsClick;
    }

    // Parse URL and open gallery if it contains #&pid=3&gid=1
    var hashData = photoswipeParseHash();
    if (hashData.pid && hashData.gid) {
        openPhotoSwipe(hashData.pid, galleryElements[hashData.gid - 1], true,
            true);
    }
};