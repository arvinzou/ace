jQuery(function($) {
    loadText(null,author,0,99999);
});
function t_query(){
   start=0;
   $(".weui-panel__bd").html("");
   var name=$("#searchInput").val();
   loadText(name,author,0,99999);
   return false;
}
function loadText(name,author,start,limit) {
	$.ajax({
		type : "post",
		url : contextPath+"/www/anslysis/query.do",
		data : {
			author : author,
			category : category,
			name : name,
			start:start,
			limit:limit,
			reportId:'writing'
		},
		beforeSend : function(XMLHttpRequest) {
             $.showLoading();
		},
		success : function(rst, textStatus) {
            console.log(rst);
            $.hideLoading();
             var html=[];
            var c=0;
            $(rst.value).each(function(n,o){
                html.push('<div class="kind-list-item">');
                            html.push('<a href="preview.jsp?id='+o.id+'" class="weui-media-box weui-media-box_appmsg">');
                              html.push('<div style="flex-direction:column;">');
                              if(o.image){
                                html.push('<image src="'+fastdfs_server+o.image+'" class="swiper"/>');
                              }

                                html.push('<div class="text-line"></div>');
                                html.push('<div class="text-content" style="flex-direction:column;">');
                                  html.push('<div class="title-strong-big2"> '+o.name+'</div>');
                                  html.push('<div class="item-text"> '+o.dateOfPublication+'  '+o.author+' 阅读：'+o.reading+'</div>');
                                html.push('</div>');
                               html.push('</div>');
                            html.push('</a>');
                        html.push('</div>');
                c++;
            });
            $(".weui-panel__bd").append(html.join(" "));
            if(c==0){
                 //$.alert("没有找到数据！", "很抱歉！");
            }
            $("#time").text(new Date);
            $(document.body).pullToRefreshDone();
		},
		error : function() {
		    $.hideLoading();
			alert("加载错误！");
		}
	});
}




var initPhotoSwipeFromDOM = function(gallerySelector) {

	// 解析来自DOM元素幻灯片数据（URL，标题，大小...）
	// (children of gallerySelector)
	var parseThumbnailElements = function(el) {
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
				src : linkEl.getAttribute('href'),
				w : parseInt(size[0], 10),
				h : parseInt(size[1], 10)
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
	var onThumbnailsClick = function(e) {
		e = e || window.event;
		e.preventDefault ? e.preventDefault() : e.returnValue = false;

		var eTarget = e.target || e.srcElement;

		// find root element of slide
		var clickedListItem = closest(eTarget, function(el) {
			return (el.tagName && el.tagName.toUpperCase() === 'FIGURE');
		});

		if (!clickedListItem) {
			return;
		}

		// find index of clicked item by looping through all child nodes
		// alternatively, you may define index via data- attribute
		var clickedGallery = clickedListItem.parentNode, childNodes = clickedListItem.parentNode.childNodes, numChildNodes = childNodes.length, nodeIndex = 0, index;

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
	var photoswipeParseHash = function() {
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

	var openPhotoSwipe = function(index, galleryElement, disableAnimation,
			fromURL) {
		var pswpElement = document.querySelectorAll('.pswp')[0], gallery, options, items;

		items = parseThumbnailElements(galleryElement);

		// 这里可以定义参数
		options = {
			barsSize : {
				top : 100,
				bottom : 100
			},
			fullscreenEl : false, // 是否支持全屏按钮
			shareButtons : [ {
				id : 'download',
				label : '保存图片',
				url : '{{raw_image_url}}',
				download : true
			}], // 分享按钮

			// define gallery index (for URL)
			galleryUID : galleryElement.getAttribute('data-pswp-uid'),

			getThumbBoundsFn : function(index) {
				// See Options -> getThumbBoundsFn section of documentation for more info
				var thumbnail = items[index].el.getElementsByTagName('img')[0], // find thumbnail
				pageYScroll = window.pageYOffset
						|| document.documentElement.scrollTop, rect = thumbnail
						.getBoundingClientRect();

				return {
					x : rect.left,
					y : rect.top + pageYScroll,
					w : rect.width
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