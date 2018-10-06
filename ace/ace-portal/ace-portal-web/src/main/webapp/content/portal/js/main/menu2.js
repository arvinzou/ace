var buildMenu = function(menus) {
	var buildMenuHtml = function(menus) {
		var html = [];
		var num = 0;
		$
				.each(
						menus,
						function(i, menu) {
							if(i==0){
                                html.push('<li class="hover">');
                            }else{
                                html.push('<li class="hover">');
                            }
							html
									.push('<a class="dropdown-toggle" href="#"><i class="menu-icon '
											+ menu.icon
											+ '"></i><span class="menu-text">'
											+ menu.text + ' </span>');
							if (menu.leaf != true && menu.leaf != 'true') {
								html
										.push('<b class="arrow fa fa-angle-down"></b>');


							}
							html.push('</a>');
							html.push('<b class="arrow"></b>');
							var initSubMenu = function(menu) {
								if (menu.leaf != true && menu.leaf != 'true') {
									var childrens = menu.children, len = childrens.length;
									html.push('<ul class="submenu">');
									for (var i = 0; i < len; i++) {

										if(i==0){
										    html.push('<li class="hover">');
										}else{
										    html.push('<li class="hover">');
										}
										if (childrens[i].href) {
											html
													.push('<a  href="#" title="'
															+ childrens[i].text
															+ '" url="'
															// + contextPath
															+ childrens[i].href
															+ '" ><i class="menu-icon fa fa-caret-right"></i>'
															+ childrens[i].text
															+ '</a><b class="arrow"></b>');
										} else {
											html
													.push('<a class="dropdown-toggle" href="#"><i class="menu-icon '
															+ childrens[i].icon
															+ '"></i>'
															+ childrens[i].text
															+ '<b class="arrow fa fa-angle-down"></b></a>');
										}
										initSubMenu(childrens[i]);
										html.push('</li>');

									}
									html.push('</ul>');
								}
							}
							initSubMenu(menu);
							html.push('</li>');
						});
		return html.join('');

	};

	var htmlFrame = buildMenuHtml(menus);
	$('#menu').empty().append(htmlFrame);
	$('#menu a[url]').bind('click', function() {

		var url = $(this).attr("url");
		$('#menu a[url]').parent('li').removeClass("active")
		$(this).parent('li').addClass("active");
		if (url) {
			if(url.indexOf("/")!=-1){
				//普通地址
				addPanel($(this).attr("title"), url, true)
			}else{
				//工作流
				var key=url.split('?')[0];
				var name=$(this).attr("title");
				addWorkflow(key, name)
			}

			// $("#mainFrame").attr("src",url);
		}
	});

}


function rsd(value, kernelKey, staticDictObjects) {
	try {
		if (!staticDictObjects) {
			staticDictObjects = parent.staticDictObject;
		}

		var name = value;

		if ((value + "") && ("" + value).indexOf(',') < 0) {
			if (staticDictObjects && kernelKey && staticDictObjects[kernelKey]) {
				for (var i = 0; i < staticDictObjects[kernelKey].length; i++) {
					if (staticDictObjects[kernelKey][i].CODE == value) {
						name = staticDictObjects[kernelKey][i].NAME;
						break;
					}
				}
			}
		} else {
			if (value) {
				var nameArray = [];
				var v = (value + "").split(',');
				for (var j = 0; j < v.length; j++) {
					for (var i = 0; i < staticDictObjects[kernelKey].length; i++) {
						if (staticDictObjects[kernelKey][i].CODE == v[j]) {
							nameArray.push(staticDictObjects[kernelKey][i].NAME);
							break;
						}
					}
				}
				name = nameArray.join(',');
			}
		}
	} catch (err) {
		console.log("渲染错误", value + ":" + kernelKey + ":" + err);
	}
	return name;
}

function odparse(kernelKey, staticDictObjects) {
	var rst = [];
	try {
		if (!staticDictObjects) {
			staticDictObjects = parent.staticDictObject;
		}
		if (staticDictObjects && kernelKey && staticDictObjects[kernelKey]) {
			for (var i = 0; i < staticDictObjects[kernelKey].length; i++) {
				rst.push(staticDictObjects[kernelKey][i].CODE + ":"
						+ staticDictObjects[kernelKey][i].NAME);
			}
		}
	} catch (err) {
		console.log("渲染错误", value + ":" + kernelKey + ":" + err);
	}
	return rst.join(";");
}