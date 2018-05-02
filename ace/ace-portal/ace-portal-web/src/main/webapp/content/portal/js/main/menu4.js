var buildMenu = function(menus) {
	var buildMenuHtml = function(menus) {
		var html = [];
		var num = 0;
		$.each(menus,function(i, menu) {
		                    html.push('<li class="menu-dropdown mega-menu-dropdown" aria-haspopup="true">');
							if (menu.leaf != true && menu.leaf != 'true') {
								html.push('<a class="nav-link nav-toggle" href="#" url="'+menu.href+'"><i class=""></i>'+ menu.text + '<span class="arrow"></span>');
							}else{
							    html.push('<a class="nav-link" href="#" url="'+menu.href+'"><i class="'+ menu.icon+ '"></i>'+ menu.text + '<span class="arrow"></span>');
							}
							html.push('</a>');
							//html.push('<b class="arrow"></b>');
							var initSubMenu = function(menu) {
								if (menu.leaf != true && menu.leaf != 'true') {
									var childrens = menu.children, len = childrens.length;
									html.push('<ul class="dropdown-menu">');
									for (var i = 0; i < len; i++) {
										if (childrens[i].href) {
										    html.push('<li class="" aria-haspopup="true">');
											html.push('<a  class="nav-link" href="#" title="'
															+ childrens[i].text
															+ '" url="'
															+ childrens[i].href
															+ '" ><i class=""></i>'
															+ childrens[i].text
															+ '<span class="arrow"></span></a>');
										} else {
										    html.push('<li class="dropdown-submenu" aria-haspopup="true">');
											html.push('<a class="nav-toggle" href="#"><i class=""></i>'
															+ childrens[i].text
															+ '<span class="arrow"></span></a>');
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
				location.href=url;
			}else{
				//工作流
				var key=url.split('?')[0];
				var name=$(this).attr("title");
				addWorkflow(key, name)
			}
		}
	});

}

