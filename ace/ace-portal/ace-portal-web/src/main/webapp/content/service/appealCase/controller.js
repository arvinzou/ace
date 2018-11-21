jQuery(function ($) {
    $('#btn-search').on('click', function () {
        $('#fm-search').ajaxForm({
            beforeSubmit: function (formData, jqForm, options) {
                var params = {};
                $.each(formData, function (n, obj) {
                    params[obj.name] = obj.value;
                });
                $.extend(params, {
                    time: new Date(),
                    // corpId: "00010001"
                });
                // console.log(params);
                jQuery(cfg.grid_selector).jqGrid('setGridParam', {
                    page: 1,
                    postData: params
                }).trigger("reloadGrid");
                return false;
            }
        });
    });
    $(".btn-group .btn").bind('click', function (event) {
            $(event.target).siblings().removeClass("active");
            console.log(event);
            $(event.target).addClass("active");
    });
    /*表单验证*/
        $("#fm-accept").validate({
            onfocusout: function (element) {
                $(element).valid();
            },
            errorPlacement: function(error, element) {
                 $(element).closest( "form" ).find( ".error-" + element.attr( "name" )).append( error );
            },
            rules: {
                answerDept: {
                    required: true
                },
                operator: {
                    required: true
                }
            },
            messages: {
                answerDept: {
                    required: '请输入受理部门'
                },
                operator: {
                    required: '请输入受理人员'
                }
            }
        });
    $('#modal-accept .accept').on('click', function () {
            $('#modal-accept form').submit();
    });
    $('#modal-accept form').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var params = {};
            $.each(formData, function (n, obj) {
                params[obj.name] = obj.value;
            });
            $.extend(params, {
                time: new Date()
            });
            console.log(params);
            acceptAppealCase(params);
            return false;
        }
    });



     $("#fm-proc").validate({
                onfocusout: function (element) {
                    $(element).valid();
                },
                errorPlacement: function(error, element) {
                     $(element).closest( "form" ).find( ".error-" + element.attr( "name" )).append( error );
                },
                rules: {
                    detailsOfProgress: {
                        required: true
                    }
                },
                messages: {
                    detailsOfProgress: {
                        required: '请输入处理进度'
                    }
                }
            });
        $('#modal-proc .proc').on('click', function () {
                $('#modal-proc form').submit();
        });
        $('#modal-proc form').ajaxForm({
            beforeSubmit: function (formData, jqForm, options) {
                var params = {};
                $.each(formData, function (n, obj) {
                    params[obj.name] = obj.value;
                });
                $.extend(params, {
                    time: new Date()
                });
                console.log(params);
                progressAppealCase(params);
                return false;
            }
        });



     $("#fm-answer").validate({
                onfocusout: function (element) {
                    $(element).valid();
                },
                errorPlacement: function(error, element) {
                     $(element).closest( "form" ).find( ".error-" + element.attr( "name" )).append( error );
                },
                rules: {
                    answerContent: {
                        required: true
                    }
                },
                messages: {
                    answerContent: {
                        required: '请输入答复内容'
                    }
                }
            });
        $('#modal-answer .answer').on('click', function () {
                $('#modal-answer form').submit();
        });
        $('#modal-answer form').ajaxForm({
            beforeSubmit: function (formData, jqForm, options) {
                var params = {};
                $.each(formData, function (n, obj) {
                    params[obj.name] = obj.value;
                });
                $.extend(params, {
                    time: new Date()
                });
                console.log(params);
                answerAppealCase(params);
                return false;
            }
        });
});
/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}
function preview(id, title) {
    $("#modal-preview").modal("show");
    loadView(id);
}
function loadView(id) {
    $.ajax({
        type: "post",
        url: cfg.view_load_data_url,
        data: {
            id: id
        },
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
            $.each(rst.value, function (key, value) {
                if (key == 'category') {
                    value = rsd(value, '83');
                }
                if (key == 'status') {
                    value == "1" ? "正常" : "关闭";
                }
                if (key.indexOf('Date') != -1 || key.indexOf('time') != -1 || key.indexOf('Time') != -1 || key.indexOf('birthday') != -1) {

                }
            });
            var data = {};
            data['o'] = rst.value;
            render('#fm-preview', data, 'tpl-preview');
            initPhotoPreview(".my-gallery img");
        },
        error: function () {
            alert("加载错误！");
        }
    });
}

//受理诉求
function acceptAppealCase(data) {
   $.ajax({
       type: "post",
       url: contextPath + "/www/appealCase/updateAccept.do",
       data:data,
       beforeSend: function (XMLHttpRequest) {
            startLoad();
       },
       success: function (rst, textStatus) {
           $("#modal-accept").modal("hide");
           if (rst) {
              alert(rst.errorMessage);
           }
           jQuery(cfg.grid_selector).jqGrid('setGridParam', {}).trigger("reloadGrid");
       },
       complete: function (XMLHttpRequest, textStatus) {
            stopLoad();
       },
       error: function () {
            stopLoad();
       }
   });
}
function  accept(id, title) {
    $("#modal-accept").modal("show");
    $('#modal-accept input[name=id]').val(id);
    $('#modal-accept .title').html(title);
}
function  proc(id, title) {
    $("#modal-proc").modal("show");
    $('#modal-proc input[name=id]').val(id);
    $('#modal-proc .title').html(title);
    getDetailsOfProgress(id);
}
function  answer(id, title) {
    $("#modal-answer").modal("show");
    $('#modal-answer input[name=id]').val(id);
    $('#modal-answer .title').html(title);
}
//处理详情
function progressAppealCase(data) {

     $.ajax({
        type: "post",
        url: contextPath + "/www/appealCase/updateDetailsOfProgress.do",
        data: data,
        beforeSend: function (XMLHttpRequest) {
            startLoad();
        },
        success: function (rst, textStatus) {
            if (rst) {
                alert(rst.errorMessage);
                $("#modal-proc").modal("hide");
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            stopLoad();
        },
        error: function () {
            stopLoad();
        }
    });
}
function answerAppealCase(data) {
   $.ajax({
       type: "post",
       url: contextPath + "/www/appealCase/reply.do",
       data:data,
       beforeSend: function (XMLHttpRequest) {
            startLoad();
       },
       success: function (rst, textStatus) {
           $("#modal-answer").modal("hide");
           if (rst) {
              alert(rst.errorMessage);
           }
           jQuery(cfg.grid_selector).jqGrid('setGridParam', {}).trigger("reloadGrid");
       },
       complete: function (XMLHttpRequest, textStatus) {
            stopLoad();
       },
       error: function () {
            stopLoad();
       }
   });
}
//字符串判空
function strIsEmpty(obj) {
    if (typeof obj == "undefined" || obj == null || obj == "") {
        return true;
    } else {
        return false;
    }
}
var params={};
function setParams(key, value) {
	params[key] = value;
	jQuery(cfg.grid_selector).jqGrid('setGridParam', {
		postData: params
	}).trigger("reloadGrid");
}
function initPhotoPreview(imgSelector) {
    $(imgSelector).each(function(i,o){
		var orgin = o;
		var image = new Image();
		image.src = orgin.src;
		image.onload = function () {
			$(orgin).wrap('<figure><a href="'+orgin.src+'"  data-size="'+image.width+'x'+image.height+'"></a></figure>');
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


function getDetailsOfProgress(id) {
    $.ajax({
        type: "post",
        url: cfg.view_load_data_url,
        data: {
            id: id
        },
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
            $('#modal-proc textarea[name=detailsOfProgress]').html(rst.value.detailsOfProgress);
        },
        error: function () {
            alert("加载错误！");
        }
    });
}