var picid = null;
jQuery(function ($) {
    loadText(id);
});

$(function () {
    $('.like_btn').on("click", '#likeHeart', Ilike);
});

function loadText(id) {
    picid = id;
    $.ajax({
        type: "post",
        url: contextPath + "/www/anslysis/query.do",
        data: {
            id: id,
            page: 1,
            limit: 1,
            reportId: 'loadWriting'
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

                    if (key == 'docText') {
                        var src = fastdfs_server + value;
                        var img = new Image();
                        $(img).attr("src", "");
                        // $(img).css("width", "140");
                        // $(img).css("height", "170");
                        //图片加载加载后执行
                        $(img).load(function () {
                            //图片默认隐藏
                            $(this).hide();
                            //移除小动画
                            //$(".loading").removeClass("loading").append(this);
                            //使用fadeIn特效
                            $(this).fadeIn("slow");
                        }).error(function () {
                            //加载失败时的处理
                        })
                        //最后设置src
                            .attr("src", src);

                        $("figure").html('<a href="' + src + '" data-size="600x600"><img src="' + src + '"></a>');
                        doDraw();
                        initPhotoSwipeFromDOM('.my-gallery');

                    } else {
                        $("#" + key).html(value);
                    }

                })
                c++;
            });
            if (c == 0) {
                $.alert("没有找到数据！", "很抱歉！");
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
    //doDraw();
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
        console.log(ImgD.width);
        console.log(ImgD.height);
        $(ImgD).parent().attr("data-size", (ImgD.width * 2) + "x" + (ImgD.height * 2));
    }

}

var initPhotoSwipeFromDOM = function (gallerySelector) {

    // 解析来自DOM元素幻灯片数据（URL，标题，大小...）
    // (children of gallerySelector)
    var parseThumbnailElements = function (el) {
        var thumbElements = el.childNodes,
            numNodes = thumbElements.length,
            items = [],
            figureEl,
            linkEl,
            size,
            item;

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
        return el && ( fn(el) ? el : closest(el.parentNode, fn) );
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
        var clickedGallery = clickedListItem.parentNode,
            childNodes = clickedListItem.parentNode.childNodes,
            numChildNodes = childNodes.length,
            nodeIndex = 0,
            index;

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
        var hash = window.location.hash.substring(1),
            params = {};

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

    var openPhotoSwipe = function (index, galleryElement, disableAnimation, fromURL) {
        var pswpElement = document.querySelectorAll('.pswp')[0],
            gallery,
            options,
            items;

        items = parseThumbnailElements(galleryElement);

        // 这里可以定义参数
        options = {
            barsSize: {
                top: 100,
                bottom: 100
            },
            fullscreenEl: false, // 是否支持全屏按钮
            shareButtons: [

                {id: 'download', label: '保存图片', url: '{{raw_image_url}}', download: true}
            ], // 分享按钮

            // define gallery index (for URL)
            galleryUID: galleryElement.getAttribute('data-pswp-uid'),

            getThumbBoundsFn: function (index) {
                // See Options -> getThumbBoundsFn section of documentation for more info
                var thumbnail = items[index].el.getElementsByTagName('img')[0], // find thumbnail
                    pageYScroll = window.pageYOffset || document.documentElement.scrollTop,
                    rect = thumbnail.getBoundingClientRect();

                return {x: rect.left, y: rect.top + pageYScroll, w: rect.width};
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
        gallery = new PhotoSwipe(pswpElement, PhotoSwipeUI_Default, items, options);
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
        openPhotoSwipe(hashData.pid, galleryElements[hashData.gid - 1], true, true);
    }
};

var likeFlag = false;

function Ilike() {
    if (likeFlag) {
        return;
    }
    var url = contextPath + "/www/anslysis/updataLike.do";
    var data = {id: picid};
    $.post(url, data, function (result) {
        if (result.status == 0) {
            likeFlag = true;
            var likeNum = $('#likes').text();
            $('#likes').text(++likeNum);
            $('.like_btn img').prop('src', likeimg);
        }
    });

}

var likeimg = 'data:image/png;base64,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';


// execute above function
