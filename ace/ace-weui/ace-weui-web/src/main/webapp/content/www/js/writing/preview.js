jQuery(function($) {
	loadText(id);
	updateReading(id);
});
function loadText(id) {
	$.ajax({
		type : "post",
		url : contextPath + "/www/anslysis/query.do",
		data : {
			id : id,
			start : 0,
			limit : 0,
			reportId : 'loadWriting'
		},
		beforeSend : function(XMLHttpRequest) {
			$.showLoading();
		},
		success : function(rst, textStatus) {
			console.log(rst);
			$.hideLoading();
			var html = [];
			var c = 0;
			$(rst.value).each(function(n, o) {
				$.each(o, function(key, value) {

					if (key == 'image'&&value) {
						var src = fastdfs_server + value;
						var img = new Image();
						$(img).attr("src", "");

						//图片加载加载后执行
						$(img).load(function() {
							//图片默认隐藏
							$(this).hide();
							//移除小动画
							$(".loading").removeClass("loading").append(this);
							$(img).addClass("swiper");
							//使用fadeIn特效
							$(this).fadeIn("slow");
						}).error(function() {
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
			if (c == 0) {
				$.alert("没有找到数据！", "很抱歉！");
			}
			doDraw();
		},
		error : function() {
			$.hideLoading();
			alert("加载错误！");
		}
	});
}

function updateReading(id) {
	$.ajax({
		type : "post",
		url : contextPath + "/www/anslysis/updateReading.do",
		data : {
			id : id,
			reportId : 'loadWriting'
		}
	});
}
window.onresize = function() {
	doDraw();
}

function doDraw() {
	var clientWidth = document.body.offsetWidth;
	var clientHeight = document.body.offsetHeight;
	$("img").each(function() {
		$(this).removeAttr("width");
		$(this).removeAttr("height");
		$(this).css("width", "100%");
		$(this).css("max-width", clientWidth+"px");
		$(this).css("max-height", clientWidth+"px");
		$(this).wrap("<a href='" + $(this).attr("src")+ "'  data-size='600x600'/>");
        $(this).parent().wrap("<figure/>");
        DrawImage(this)
	});
	initPhotoSwipeFromDOM('figure');
}
function DrawImage(ImgD) {
	var image = new Image();
	image.src = ImgD.src;
	image.onload = function() {
		var clientWidth = document.body.offsetWidth;
		if (image.width > clientWidth || image.height > clientWidth) {
			if (image.width > clientWidth) {
				ImgD.width = clientWidth;
				ImgD.height = (image.height * clientWidth) / image.width;
			} else {
				ImgD.width = image.width;
				ImgD.height = image.height;
			}
		}
		$(ImgD).parent().attr("data-size",(ImgD.width * 2) + "x" + (ImgD.height * 2));
	}
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