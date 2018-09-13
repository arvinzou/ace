var loading;
var  urlParams = {};
function getQueryString() {
      var qs = location.search.substr(1),
        args = {},
        items = qs.length ? qs.split("&") : [],
        item = null,
        len = items.length;

      for(var i = 0; i < len; i++) {
        item = items[i].split("=");
        var name = decodeURIComponent(item[0]),
          value = decodeURIComponent(item[1]);
        if(name) {
          args[name] = value;
        }
      }
      return args;
}
urlParams=getQueryString();
function loader(jsonData)
     {

       jsonData.path = jsonData.path != undefined ? jsonData.path : "";
       if(jsonData.type == "js")
       {
         var _js = document.createElement("script");
         _js.setAttribute("type", "text/javascript");
         _js.setAttribute("src", jsonData.path + jsonData.url+"?v="+version);
         _js.onload = _js.onreadystatechange=function(){
           if(!this.readyState||this.readyState=='loaded'||this.readyState=='complete'){
             if("function" == typeof(jsonData["callback"]) && jsonData["callback"]){
                jsonData["callback"].call(this);
             }
           }
           _js.onload=_js.onreadystatechange=null;
         }
         document.getElementsByTagName("head")[0].appendChild(_js);//追加到head标签内
         console.log(_js);
       }
       else if(jsonData.type == "css")
       {
         var _css = document.createElement("link");
         _css.setAttribute("type", "text/css");
         _css.setAttribute("rel", "stylesheet");
         _css.setAttribute("href", jsonData.path + jsonData.url+"?v="+version);
         document.getElementsByTagName("head")[0].appendChild(_css);//追加到head标签内
         console.log(_css);
       }
     }
var buildMenu = function(menus) {
	var buildMenuHtml = function(menus) {
		var html = [];
		var num = 0;

		$.each(menus,function(i, menu) {


		                    if(urlParams.id){
		                        if(urlParams.id==menu.id){
                                    html.push('<li class="menu-dropdown mega-menu-dropdown active" aria-haspopup="true">');
                                }else{
                                    html.push('<li class="menu-dropdown mega-menu-dropdown" aria-haspopup="true">');
                                }
		                    }else{
		                        if(i==0){
		                            html.push('<li class="menu-dropdown mega-menu-dropdown active" aria-haspopup="true">');
		                        }else{
		                            html.push('<li class="menu-dropdown mega-menu-dropdown" aria-haspopup="true">');
		                        }
		                    }
							if (menu.leaf != true && menu.leaf != 'true') {
								html.push('<a class="nav-link nav-toggle" href="#" url="'+menu.href+'"><i class=""></i><small>'+ menu.text + '</small><span class="arrow"></span>');
							}else{
							    html.push('<a class="nav-link" href="#" url="'+menu.href+'"><i class=""></i><small>'+ menu.text + '</small><span class="arrow"></span>');
							}
							html.push('</a>');
							//html.push('<b class="arrow"></b>');
							var initSubMenu = function(menu) {
								if (menu.leaf != true && menu.leaf != 'true') {
									var childrens = menu.children, len = childrens.length;
									html.push('<ul class="dropdown-menu">');
									for (var i = 0; i < len; i++) {
										if (childrens[i].href) {
										    if(urlParams.id){
                                                if(urlParams.id==childrens[i].id){
                                                     html.push('<li class=" active" aria-haspopup="true">');
                                                    											html.push('<a  class="nav-link" href="#" title="'
                                                    															+ childrens[i].text
                                                    															+ '" url="'
                                                    															+ childrens[i].href
                                                    															+ '" ><i class=""></i><small>'
                                                    															+ childrens[i].text
                                                    															+ '</small><span class="arrow"></span></a>');
                                                }else{
                                                    html.push('<li class="" aria-haspopup="true">');
                                                   											html.push('<a  class="nav-link" href="#" title="'
                                                   															+ childrens[i].text
                                                   															+ '" url="'
                                                   															+ childrens[i].href
                                                   															+ '" ><i class=""></i><small>'
                                                   															+ childrens[i].text
                                                   															+ '</small><span class="arrow"></span></a>');
                                                }
                                            }

										} else {
										    html.push('<li class="dropdown-submenu" aria-haspopup="true">');
											html.push('<a class="nav-toggle" href="#"><i class=""></i><small>'
															+ childrens[i].text
															+ '</small><span class="arrow"></span></a>');
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

	var activeNode=$("#menu .active");
	 console.log(activeNode);
         if(urlParams.id){
            $(activeNode).parent().css('display','block');
             $(activeNode).parent().parent().addClass("active");

             $(activeNode).parent().parent().parent().css('display','block');
             $(activeNode).parent().parent().parent().parent().addClass("active");

             var title=$(activeNode).find("a small").html();

             $("title").html(title);
             $(".todo-header").html(title);
              console.log(title);
         }else{
               $("#menu999999").addClass("active");
                $("#menu999999").find("#selected").addClass("selected");
                var title="仪表盘";
                $("title").html(title);
                 $(".todo-header").html(title);

         }


}


jQuery(function($) {
    if(sessionStorage&&sessionStorage.getItem("html_nav")){
        var data=sessionStorage.getItem("html_nav");
         $("body .page-wrapper div:first").before(data);
         initMenu();
         initBottom();
     }else{
        $.ajax({
            url : portalPath + '/dynamic/common/tpl/html_nav.jsp',
            type : 'POST',
            timeout : 30000,
            dataType : 'text',
            success : function(data) {
               $("body .page-wrapper div:first").before(data);
               initMenu();
               initBottom();
               if(sessionStorage){
                   console.log("sessionStorage");
                   sessionStorage.setItem("html_nav",data);
               }
            }
        });
    }
     try{App();}catch(e){};

});
function initBottom(){
    if(sessionStorage&&sessionStorage.getItem("html_bottom")){
        var data=sessionStorage.getItem("html_bottom");
         $(".bottom").before(data);
     }else{
        $.ajax({
                 url : portalPath + '/dynamic/common/tpl/html_bottom.jsp',
                 type : 'POST',
                 timeout : 30000,
                 dataType : 'text',
                 success : function(data) {
                    $(".bottom").before(data);
                    if(sessionStorage){
                       console.log("sessionStorage");
                       sessionStorage.setItem("html_bottom",data);
                    }
                 }
             });
     }
  $('.scroll-to-top').click(function(e) {
       e.preventDefault();
       $('html, body').animate({scrollTop: 0}, 500);
       return false;
   });

}
function initMenu(){
    if(sessionStorage&&sessionStorage.getItem("menu")){
        var srt=sessionStorage.getItem("menu");
        var data=JSON.parse(srt);
        console.log(data);
        buildMenu(data);
     }else{
        $.ajax({
                    url : portalPath + '/system/getTreeList.do?loadButton=false&client=c',
                    type : 'POST',
                    timeout : 30000,
                    dataType : 'json',
                    beforeSend:function(){
                        loading=startLoading();
                        if(loading) {
                           loading.settext("正在加载，请稍后......");
                        }

                    },
                    success : function(data) {
                        buildMenu(data);
                        if(loading){
                            loading.remove();
                        }
                        if(sessionStorage){
                            console.log("sessionStorage");
                            sessionStorage.setItem("menu",JSON.stringify(data));
                        }
                    }
                });
     }

}


if(!window.console){
        window.console={};
        window.console.log=function(log){

        }
     }
