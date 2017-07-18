var buildMenu = function(menus) {
	var buildMenuHtml = function(menus) {
		var html = [];
		var num = 0;
		$
				.each(
						menus,
						function(i, menu) {
							html.push('<li class="nav-item" id="menu'+i+'">');
							html
									.push('<a class="nav-link nav-toggle" href="#"><i class="'
											+ menu.icon
											+ '"></i><span class="title">'
											+ menu.text + ' </span>');
							html.push('<span id="selected"></span>');
							if (menu.leaf != true && menu.leaf != 'true') {

								html.push('<span class="arrow"></span>');
							}
							html.push('</a>');
							var initSubMenu = function(menu) {
								if (menu.leaf != true && menu.leaf != 'true') {
									var childrens = menu.children, len = childrens.length;
									html.push('<ul class="sub-menu" style="display: none;">');
									for (var i = 0; i < len; i++) {
										html.push('<li class="nav-item" id="menu'+childrens[i].id+'">');
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
															+ '</span></a><span class="arrow"></span>');
										} else {
											html
													.push('<a class="nav-link nav-toggle" href="#"><i class1="'
															+ childrens[i].icon
															+ '"></i><span class="title">'
															+ childrens[i].text
															+ '</span><span class="arrow"></span></a>');
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


		if (url) {
			if(url.indexOf("/")!=-1){
				//普通地址
				//addPanel($(this).attr("title"), url, true);
				location.href=url;
			}else{
				//工作流
				var key=url.split('?')[0];
				var name=$(this).attr("title");
				addWorkflow(key, name)
			}

			// $("#mainFrame").attr("src",url);
		}
	});
	$('.nav-item').bind('click', function() {
    		 $('.nav-item').removeClass("active");
    		 $('.nav-item').find("#selected").removeClass("selected");
    		 $(this).siblings().removeClass("open");
            $(this).addClass("active open");
            $(this).find("#selected").addClass("selected");

   });

    activeMenu($("#menu"+cmenu));
    $("#menu"+cmenu).addClass("active open");
}

function activeMenu(o){
    var tagName=$(o)[0].tagName;
    if($(o).parent()&&(tagName=='LI'||tagName=='UL')){
        $(o).parent().addClass("active open");
        $(o).find(".sub-menu").css("display","display");
        activeMenu($(o).parent());
    }
}

