function loadView(id) {
    $.ajax({
        type: "post",
        url: contextPath + '/fopCompany/selectFopCompanyByPrimaryKey',
        data: {id: id},
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
            //动态渲染
            var tpl = document.getElementById('tpl-view-page').innerHTML;//'tpl-company'
            var renderHtml = juicer(tpl, rst.value);
            $('.main_box').html(renderHtml);
            //
            initGrid();
            //
            $.each(rst.value, function (key, value) {
                if (key == "thirdLaborRelation") {
                    var rst = "";
                    switch (value) {
                        case '0' :
                            rst = "否";
                            break;
                        case '1' :
                            rst = "是";
                            break;
                        default :
                            rst = "";
                    }
                    value = rst;
                }
                //企业类型 "0": "企业会员", "4": "个人会员"},//, "1": "团体企业", "2": "律师事务所", "3": "银行机构"
                if (key == "companyType") {
                    var rst = "";
                    switch (value) {
                        case '0' :
                            rst = "企业会员";
                            break;
                        case '1' :
                            rst = "团体企业";
                            break;
                        case '2' :
                            rst = "律师事务所";
                            break;
                        case '3' :
                            rst = "银行机构";
                            break;
                        case '4' :
                            rst = "个人会员";
                            break;
                        default :
                            rst = "N/A";
                    }
                    value = rst;
                }
                //status
                if (key == "status") {
                    if ("1" == value) {
                        value = "非会员";
                    }
                    if ("2" == value) {
                        value = "会员";
                    }
                }
                //文化程度
                if (key == "lpEducation") {
                    value = rsd(value, "10");
                }
                //民族
                if (key == "lpNationality") {
                    value = rsd(value, "09");
                }
                //性别
                if (key == "lpSex") {
                    value = rsd(value, "01");
                }
                //企业性质
                if (key == "companyProperty") {
                    value = rsd(value, "134");
                }
                //日期格式化
                if (key.indexOf('Dt') != -1 ||
                    key.indexOf('Date') != -1 ||
                    key.indexOf('time') != -1 ||
                    key.indexOf('Time') != -1 ||
                    key.indexOf('birthday') != -1) {
                    value = Common.DateFormatter(value);
                }

                // console.log("key=" + key + ",value=" + value);
                $(".form_info").find('span[name=' + key + ']').html(value);
            });
            //
            initPhotoPreview(".my-gallery img");
            //event
            $(".image-box .delete").click(function () {
                var annexId = $(this).parent().attr("name");
                console.log("annexId=" + annexId);
                delAnnex(annexId, $(this).parent());
            });
        },
        error: function () {
            alert("加载错误！");
        }
    });

    //initEvent
}

function delAnnex(annexId, divObj) {
    $.ajax({
        type: "post",
        url: contextPath + '/fopCompany/delAnnex',
        data: {id: annexId},
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {

            alert(rst.errorMessage);
            if (rst.state) {
                //
                divObj.remove();
            }
        },
        error: function () {
            alert("网络错误！");
        }
    });
}

function loadEditView(id) {
    $.ajax({
        type: "post",
        url: contextPath + '/fopCompany/findByPK',
        data: {id: id},
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
            console.log(JSON.stringify(rst));
            //动态渲染
            var tpl = document.getElementById('tpl-view-page').innerHTML;//'tpl-company'
            var renderHtml = juicer(tpl, rst.value);
            $('.main_box').html(renderHtml);

            initGrid();

            $.each(rst.value, function (key, value) {
                if (key == "thirdLaborRelation") {
                    var rst = "";
                    switch (value) {
                        case '0' :
                            rst = "否";
                            break;
                        case '1' :
                            rst = "是";
                            break;
                        default :
                            rst = "";
                    }
                    value = rst;
                }
                //企业类型 "0": "企业会员", "4": "个人会员"},//, "1": "团体企业", "2": "律师事务所", "3": "银行机构"
                if (key == "companyType") {
                    var rst = "";
                    switch (value) {
                        case '0' :
                            rst = "企业会员";
                            break;
                        case '1' :
                            rst = "团体企业";
                            break;
                        case '2' :
                            rst = "律师事务所";
                            break;
                        case '3' :
                            rst = "银行机构";
                            break;
                        case '4' :
                            rst = "个人会员";
                            break;
                        default :
                            rst = "N/A";
                    }
                    value = rst;
                }
                //status
                if (key == "status") {
                    if ("1" == value) {
                        value = "非会员";
                    }
                    if ("2" == value) {
                        value = "会员";
                    }
                }
                //文化程度
                if (key == "lpEducation") {
                    value = rsd(value, "10");
                }
                //民族
                if (key == "lpNationality") {
                    value = rsd(value, "09");
                }
                //性别
                if (key == "lpSex") {
                    value = rsd(value, "01");
                }
                //企业性质
                if (key == "companyProperty") {
                    value = rsd(value, "134");
                }
                //日期格式化
                if (key.indexOf('Dt') != -1 ||
                    key.indexOf('Date') != -1 ||
                    key.indexOf('time') != -1 ||
                    key.indexOf('Time') != -1 ||
                    key.indexOf('birthday') != -1) {
                    value = Common.DateFormatter(value);
                }
                $(".form_info").find('span[name=' + key + ']').html(value);
            });

            //
            initPhotoPreview(".my-gallery img");
        },
        error: function () {
            alert("加载错误！");
        }
    });


}

var Common = {

    // EasyUI用DataGrid用日期格式化
    TimeFormatter: function (value, rec, index) {
        if (value == undefined) {
            return "";
        }
        /* json格式时间转js时间格式 */
        value = value.substr(1, value.length - 2);
        var obj = eval('(' + "{Date: new " + value + "}" + ')');
        var dateValue = obj["Date"];
        if (dateValue.getFullYear() < 1900) {
            return "";
        }
        var val = dateValue.format("yyyy-mm-dd HH:MM");
        return val.substr(11, 5);
    },
    DateTimeFormatter: function (value, rec, index) {
        if (value == undefined) {
            return "";
        }
        /* json格式时间转js时间格式 */
        value = value.substr(1, value.length - 2);
        var obj = eval('(' + "{Date: new " + value + "}" + ')');
        var dateValue = obj["Date"];
        if (dateValue.getFullYear() < 1900) {
            return "";
        }

        return dateValue.format("yyyy-mm-dd HH:MM");
    },

    // EasyUI用DataGrid用日期格式化
    DateFormatter: function (value, rec, index) {
        if (value == undefined) {
            return "";
        }
        return value.substr(0, 10);
    }
};

function rsd(value, kernelKey) {
    try {
        var name = value;
        if ((value + "") && ("" + value).indexOf(',') < 0) {
            if (staticDictObject && kernelKey && staticDictObject[kernelKey]) {
                for (var i = 0; i < staticDictObject[kernelKey].length; i++) {
                    if (staticDictObject[kernelKey][i].CODE == value) {
                        name = staticDictObject[kernelKey][i].NAME;
                        break;
                    }
                }
            }
        } else {
            if (value) {
                var nameArray = [];
                var v = (value + "").split(',');
                for (var j = 0; j < v.length; j++) {
                    for (var i = 0; i < staticDictObject[kernelKey].length; i++) {
                        if (staticDictObject[kernelKey][i].CODE == v[j]) {
                            nameArray.push(staticDictObject[kernelKey][i].NAME);
                            break;
                        }
                    }
                }
                name = nameArray.join(',');
            }
        }
    } catch (err) {
        console.info("渲染错误", value + ":" + kernelKey + ":" + err);
    }
    return name;
}


function initPhotoPreview(imgSelector) {
    $(imgSelector).each(function (i, o) {
        var orgin = o;
        var image = new Image();
        image.src = orgin.src;
        image.onload = function () {
            $(orgin).wrap('<figure><a href="' + orgin.src + '"  data-size="' + image.width + 'x' + image.height + '"></a></figure>');
        }
    });

    initPhotoSwipeFromDOM('.my-gallery');
}

var initPhotoSwipeFromDOM = function (gallerySelector) {
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

                {
                    id: 'download',
                    label: '保存图片',
                    url: '{{raw_image_url}}',
                    download: true
                }
            ], // 分享按钮

            // define gallery index (for URL)
            galleryUID: galleryElement.getAttribute('data-pswp-uid'),

            getThumbBoundsFn: function (index) {
                // See Options -> getThumbBoundsFn section of documentation for more info
                var thumbnail = items[index].el.getElementsByTagName('img')[0], // find thumbnail
                    pageYScroll = window.pageYOffset || document.documentElement.scrollTop,
                    rect = thumbnail.getBoundingClientRect();

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