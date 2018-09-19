var loading = {};
var params = {limit: 40};
window.onload = function (){
    initPage();
    initEvents();
    initJuicerMethod();
}


/*报道初始化分页*/
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
            params['start']=(num-1)*params.limit;
            params['initType']=type;
            getPageList();
        }
    });
}
/*报道条件查询*/
function t_query(){
    getPageList();
    return false;
}
function setParams(key,value){
    params[key]=value;
     getPageList();
}
/*报道加载表格数据*/
function getPageList() {
    var url = contextPath+ "/liveRpt/findLiveRptList";
    params['content']=$("input[name=keyword]").val();
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
            initPhotoPreview(".my-gallery img");
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
/*报道添加*/
function add(type){
window.location.href = 'add/index.jsp?id=' + urlParams.id;
}
/*报道编辑*/
function edit(did){
window.location.href = 'edit/index.jsp?id=' + urlParams.id + '&did=' + did;
}
/*查看详情*/
function detail(id) {
var url = contextPath + "/liveRpt/selectLiveRptByPrimaryKey";
$.getJSON(url, {id: id}, function (result) {
if (result.status == 0) {
var navitem = document.getElementById('tpl-detail').innerHTML;
var html = juicer(navitem, {data: result.value});
$("#detail-info").html(html);
$("#modal-detail").modal("show");
}
})
}

function initEvents() {
    ﻿   $('#modal-preview').on('show.bs.modal', function (event) {
    		var relatedTarget = $(event.relatedTarget);
    		var id = relatedTarget.data('id');
    		var title = relatedTarget.data('title');
    		var modal = $(this);
    		console.log(relatedTarget);
    		initPreview(id);
    	})
        $('#modal-audit').on('show.bs.modal', function (event) {
            var relatedTarget = $(event.relatedTarget);
            var id = relatedTarget.data('id');
            var title = relatedTarget.data('title');
            var modal = $(this);
            console.log(relatedTarget);
    		initForm(id);
        })
        $('#modal-audit .audit').on('click', function () {
            $('#modal-audit form').submit();
        });
    $('#modal-audit form').ajaxForm({
            beforeSubmit: function (formData, jqForm, options) {
                var params = {};
                $.each(formData, function (n, obj) {
                    params[obj.name] = obj.value;
                });
                $.extend(params, {
                    time: new Date()
                });
                console.log(params);
                audit(params);
                return false;
            }
        });
        $('#modal-status').on('show.bs.modal', function (event) {
            var relatedTarget = $(event.relatedTarget);
            var id = relatedTarget.data('id');
            var title = relatedTarget.data('title');
            var modal = $(this);
           $(modal).find("input[name=id]").val(id);
        })
            $('#modal-status .status').on('click', function () {
                $('#modal-status form').submit();
            });
            $('#modal-status form').ajaxForm({
                beforeSubmit: function (formData, jqForm, options) {
                    var params = {};
                    $.each(formData, function (n, obj) {
                        params[obj.name] = obj.value;
                    });
                    $.extend(params, {
                        time: new Date()
                    });
                    console.log(params);
                    updateStatus(params);
                    return false;
                }
            });
 $(".btn-group .btn").bind('click', function (event) {
        $(event.target).siblings().removeClass("active");
        console.log(event);
        $(event.target).addClass("active");
    });


}
/*报道审核*/
function audit(params){
    startLoad();
    $.ajax({
        url: contextPath + "/liveRpt/updateAudit",
        type:"post",
        async:false,
        data:params,
        success:function(rst){
            stopLoad();
            $("#modal-audit").modal('hide');
            alert(rst.errorMessage);
            if(rst.status == 0) {
                getPageList();
            }
        },
        error:function(){
            stopLoad();
            alert("对不起出错了！");
        }
    });
}
/*报道上架*/
function del(id){
    if(confirm("确定要删除？")){
        startLoad();
        $.ajax({
            url: contextPath + "/liveRpt/deleteLiveRptByLiveRptId",
            type:"post",
            async:false,
            data:{
               id :id
            },
            success:function(rst){
                stopLoad();
                if(rst.status == 0) {
                  getPageList();
                }else {
                    alert(rst.errorMessage);
                }
            },
            error:function(){
                stopLoad();
                alert("对不起，出错了！");
            }
        });
    }
}


//juicer自定义函数
function initJuicerMethod() {
juicer.register('parseStatus', parseStatus);
}

/**
* 状态
* 0-删除
* 1-暂存
* 2-提交审核
* 3-审核通过
* 4-审核驳回
*/
function parseStatus(status) {
    switch (status) {
        case '0':
        return "删除";
        case '1':
        return "暂存";
        case '2':
        return "待审";
        case '3':
        return "通过";
        case '4':
        return "驳回";
        default:
        return "";
    }
}


﻿function initPreview(id) {
	startLoad();
    $.ajax({
        url: contextPath +  "/liveRpt/selectLiveRptByPrimaryKey",
        type: "post",
        async: false,
        data: {
            id: id
        },
        success: function (result) {
			 stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                render('#fm-preview', data, 'tpl-preview');
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
function initForm(id) {
	startLoad();
    $.ajax({
        url: contextPath +  "/liveRpt/selectLiveRptByPrimaryKey",
        type: "post",
        async: false,
        data: {
            id: id
        },
        success: function (result) {
			 stopLoad();
            if (result.status == 0) {
                var data = {};
                data['o'] = result.value;
                render('#fm-audit', data, 'tpl-fm');
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
function updateStatus(params) {
    startLoad();
    $.ajax({
        url: contextPath + "/liveRpt/updateAudit",
        type: "post",
        async: false,
        data: params,
        success: function (rst) {
            stopLoad();
            alert(rst.errorMessage);
            if (rst.status == 0) {
                $("#modal-status").modal('hide');
                getPageList();
            }
        },
        error: function () {
            stopLoad();
            alert("对不起出错了！");
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