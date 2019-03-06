var loading = {};
var params = {limit: 10,category:'2'};
window.onload = function () {
    initPage();
    initEvents();
    $('#preview').on('click', '.btn-sign-primary', auditActivity);
    $('#info').on('click', '.personnelInfo', optionPersonner);
}

/*不同的选项显示不同的按钮*/
function beforeActive() {
    var val = $(this).val();
    if (val == 3) {
        $(this).next().hide();
        $('#coinConfig').show();
    } else if (val == 4) {
        $(this).next().show();
        $('#coinConfig').hide();
    }
}

function App() {
    console.log("=============================App Start==============================");
    loadCustom();
}

/*加载资源*/
function loadCustom() {
    var urls = [];
    urls.push({path: contextPath, url: '/content/common/js/jqPaginator.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/js/jquery.form.js', type: 'js'});
    for (var i = 0; i < urls.length; i++) {
        loader(urls[i]);
    }
}

/*线下活动初始化分页*/
function initPage() {
    $.jqPaginator('#pagination1', {
        totalCounts: 1,
        pageSize: params.limit,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            params['start'] = (num - 1) * params.limit;
            params['initType'] = type;
            getPageList();
        }
    });
}

/*选择参加者*/
function optionPersonner() {
    var $that = $(this);
    if ($that.is('.active_flag')) {
        $that.removeClass('active_flag')
        return;
    }
    $that.addClass('active_flag');
}


/*线下活动条件查询*/
function t_query() {
    getPageList();
    return false;
}

/*线下活动加载表格数据*/
function getPageList() {
    var url = contextPath + "/activity/findActivityList";
    params['title'] = $("input[name=keyword]").val();
    startLoad();
    $.getJSON(url, params, function (rst) {
        stopLoad();
        if (rst.status == 0) {
            if (params.initType == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: rst.total
                });
            }
            render($("#page-list"), rst.rows, "tpl-list");
        }
    })
}

/*页面渲染*/
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}

/*线下活动添加*/
function add(type) {
    window.location.href = add / index.jsp;
}

/*线下活动编辑*/
function edit(id) {
    window.location.href = 'edit/index.jsp?id=' + id;
}

/*个人信息审核*/
function audit(params) {
    startLoad();
    $.ajax({
        url: contextPath + "/activity/audit",
        type: "post",
        async: false,
        data: params,
        success: function (rst) {
            stopLoad();
            $("#modal-audit").modal('hide');
            alert(rst.errorMessage);
            if (rst.status == 0) {
                getPageList();
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}



/*线下活动上架*/
function online(id) {
    if (confirm("确定要上架吗？")) {
        startLoad();
        $.ajax({
            url: contextPath + "/activity/updateStatus",
            type: "post",
            async: false,
            data: {
                id: id,
                status: '1'
            },
            success: function (rst) {
                stopLoad();
                if (rst.status == 0) {
                    getPageList();
                } else {
                    alert(rst.errorMessage);
                }
            },
            error: function () {
                stopLoad();
                alert("对不起，出错了！");
            }
        });
    }
}

/*线下活动下架*/
function outline(id) {
    if (confirm("确定要下架吗？")) {
        startLoad();
        $.ajax({
            url: contextPath + "/activity/updateStatus",
            type: "post",
            async: false,
            data: {
                id: id,
                status: '0'
            },
            success: function (rst) {
                stopLoad();
                if (rst.status == 0) {
                    getPageList();
                } else {
                    alert(rst.errorMessage);
                }
            },
            error: function () {
                stopLoad();
                alert("对不起，出错了！");
            }
        });
    }
}

function deleteData(id) {
    if (confirm("确定删除该活动吗，删除后将不能恢复。")) {
        var url = contextPath + "/activity/softDelete";
        var data = {
            jsons: JSON.stringify({
                id: id,
            })
        }
        startLoad();
        $.post(url, data, function (rst) {
            stopLoad();
            if (rst.status == 0) {
                getPageList();
                return;
            }
            alert(rst.errorMessage);
        })
    }
}


/*查看详情*/
function details(id) {
    var url = contextPath + "/activity/selectActivityByPrimaryKey";
    $.getJSON(url, {id: id}, function (result) {
        if (result.status == 0) {
            var navitem = document.getElementById('tpl-detail').innerHTML;
            var html = juicer(navitem, {data: result.value});
            $("#fm-detail").html(html);
            $("#modal-detail").modal("show");
            initPhotoPreview(".my-gallery img");
        }
    });
}


/*查看详情*/
function signInfo(id,total) {
    var url = contextPath + "/activity/selectActivityByPrimaryKey";
    var data={
        id:id,
        start:0,
        limit:total
    }
    $.getJSON(url,data, function (result) {
        if (result.status == 0) {
            var navitem = document.getElementById('tpl-fm-sign').innerHTML;
            var html = juicer(navitem, {data: result.value});
            $("#fm-sign").html(html);
            $("#preview").modal("show");
            initPhotoPreview(".sign-content .imgBox img");
        }
    });
}

function initEvents() {
    $('#modal-audit').on('show.bs.modal', function (event) {
        var relatedTarget = $(event.relatedTarget);
        var id = relatedTarget.data('id');
        var modal = $(this);
        modal.find('.modal-body input[name=id]').val(id);
        initAuditForm(id);
    })
    $('#modal-audit .btn-primary').on('click', function () {
        $('#modal-audit form').submit();
    });

    $('#modal-audit form').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var rstVal = $('input[name="rst"]:checked').val();
            if (rstVal == undefined) {
                alert("请选择审核结果!");
                return;
            }

            var params = {};
            $.each(formData, function (n, obj) {
                params[obj.name] = obj.value;
            });
            $.extend(params, {
                time: new Date()
            });
            audit(params);
            return false;
        }
    });


}


function initAuditForm(id) {
    startLoad();
    $.ajax({
        url: contextPath + "/activity/selectActivityByPrimaryKey",
        type: "post",
        async: false,
        data: {
            id: id
        },
        success: function (result) {
            stopLoad();
            if (result.status == 0) {
                render('#fm-audit', result.value, 'tpl-fm-audit');
                initPhotoPreview(".my-gallery img");

            } else {
                alert(result.errorMessage);
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
        }
    });
}


function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}

function setParams(key, value) {
    params[key] = value;
    getPageList();
}


function auditActivity() {
    var param={};
    var rst=$('.signCheck input[name="rst"]:checked').val();
    if (!rst) {
        alert("请选择审核结果!");
        return;
    }
    var id=$('.signCheck input[name="id"]').val();
    var message=$('.signCheck textarea[name="message"]').val();
    param={
        rst:rst,
        id:id,
        message:message,
    }
    if(rst==33){
        var list=[];
        $('input[name="list"]:checked').each(function () {
            list.push($(this).val());
        })
        param.list=list.join(",");
    }
    var url = contextPath + "/activity/endAudit";
    $.post(url, param, function (result) {
        if (result.status == 0) {
            getPageList();
            $("#preview").modal("hide");
        }
    });
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