var buildMenu = function(menus) {
	var buildMenuHtml = function(menus) {
		var html = [];
		var num = 0;
		$
				.each(
						menus,
						function(i, menu) {
							html.push('<li class="nav-item">');
							html
									.push('<a class="nav-link nav-toggle" href="#"><i class="'
											+ menu.icon
											+ '"></i><span class="title">'
											+ menu.text + ' </span>');
							if (menu.leaf != true && menu.leaf != 'true') {
								html
										.push('<span class="arrow open"></span>');
							}
							html.push('</a>');
							var initSubMenu = function(menu) {
								if (menu.leaf != true && menu.leaf != 'true') {
									var childrens = menu.children, len = childrens.length;
									html.push('<ul class="sub-menu">');
									for (var i = 0; i < len; i++) {
										html.push('<li class="nav-item">');
										if (childrens[i].href) {
											html.push('<a class="nav-link"  href="#" title="'
															+ childrens[i].text
															+ '" url="'
															// + contextPath
															+ childrens[i].href
															+ '" ><i class1="'
															+ childrens[i].icon
															+ '"></i><span class="title">'
															+ childrens[i].text
															+ '</span></a><b class="arrow"></b>');
										} else {
											html
													.push('<a class="nav-link nav-toggle" href="#"><i class1="'
															+ childrens[i].icon
															+ '"></i><span class="title">'
															+ childrens[i].text
															+ '</span><span class="arrow open"></span></a>');
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
		$(this).parent('li').addClass("active open");

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

