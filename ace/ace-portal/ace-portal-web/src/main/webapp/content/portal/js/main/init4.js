var loading;
var title="仪表盘";
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
								html.push('<a class="nav-link nav-toggle" href="#" url="'+menu.href+'"><i class=""></i>'+ menu.text + '<span class="arrow"></span>');
							}else{
							    html.push('<a class="nav-link" href="#" url="'+menu.href+'"><i class=""></i>'+ menu.text + '<span class="arrow"></span>');
							}
							html.push('</a>');
							//html.push('<b class="arrow"></b>');
							var initSubMenu = function(menu) {
								if (menu.leaf != true && menu.leaf != 'true') {
									var childrens = menu.children, len = childrens.length;
									html.push('<ul class="dropdown-menu">');
									for (var i = 0; i < len; i++) {

									if(urlParams.id){
                                        if(urlParams.id==childrens[i].id){
                                            title=childrens[i].text;
                                        }
                                     }
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
			}
		}
	});

	$("title").html(title);
    $(".breadcrumb1 li span").html(title);

}


function initMenu(){

    if(sessionStorage&&sessionStorage.getItem("menu")){
        var srt=sessionStorage.getItem("menu");
        var data=JSON.parse(srt);
        console.log(data);
        buildMenu(data);
        loadCommon();
     }else{
        $.ajax({
            url : portalPath + '/system/getTreeList.do?loadButton=false',
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
                loadCommon();

            }
        });
     }

}

 function loadCommon(){
         var urls=[];
         urls.push({path:portalPath,url:'/content/common/assets/js/gz/bootstrap.min.js',type:'js'});
         urls.push({path:portalPath,url:'/content/common/assets/js/gz/bootbox.min.js',type:'js'});
         urls.push({path:portalPath,url:'/content/common/assets/pages/scripts/ui-modals.min.js',type:'js'});
         urls.push({path:portalPath,url:'/content/common/assets/global/scripts/app.min.js',type:'js'});
          for(var i=0;i<urls.length;i++){
             loader(urls[i]);
          }
          console.log('authority');
          	if(!authorConfig){
          		console.log("authorConfig is empty");
          		$.each($('button'),function(i,obj){
          			console.log($(obj).attr("authority"));
          			obj.disabled=true;
          			if(obj.disabled){
          				$(obj).hide();
          			}else{
          				$(obj).show();
          			}
          		});
          	}else{
          		console.log(authorConfig);
          		$.each($('button'),function(i,obj){
          			obj.disabled=!authorConfig.hasOwnProperty($(obj).attr("authority"));
          			if($(obj).attr("authority")=='false'){
          				obj.disabled=false;
          			}
          			console.log($(obj).attr("authority"));
          			console.log(obj.disabled==true?'FAIL':'OK');
          			var agent = navigator.userAgent.toLowerCase() ;
          			console.log(agent);
          			if(agent.indexOf("chrome") != -1||agent.indexOf("safari") != -1){
          				if($(obj).text()==null||$(obj).text().trim()==""){
                          	$(obj).prepend(authorConfig[$(obj).attr("authority")])
                          }
          			}else{
          				if($(obj).text()==null||$(obj).text()==""||$(obj).text()==" "||$(obj).text()=="  "){
                          	$(obj).prepend(authorConfig[$(obj).attr("authority")])
                          }
          			}
          			if(obj.disabled&&$(obj).attr("authority")){
          				$(obj).hide();
          			}else{
          				$(obj).show();
          			}
          			//console.log($(obj).text());
          		});
          	}
          	//console.log(systemUser.users);
}

function autoWidth(){
    $(cfg.grid_selector).jqGrid('setGridWidth',$(".page-content").width());
}


function createNav(){
    $(".page-content").wrap('<div class="page-content-inner"></div>');
    $(".page-content-inner").wrap('<div class="container"></div>');
    $(".page-content-inner").before('<ul class="breadcrumb1"><li><span>仪表盘</span></li></ul>');

	$(".container").wrap('<div class="page-content-wrapper"></div>');
	$(".page-content-wrapper").wrap('<div class="page-container"></div>');
    $(".page-container").wrap('<div class="page-wrapper-middle"></div>');
    $(".page-wrapper-middle").wrap('<div class="page-wrapper-row full-height"></div>');
    $(".page-wrapper-row").wrap('<div class="page-wrapper"></div>');
    $(".page-content").css("padding","10px");

    $(".page-content-wrapper div:first").before('<div class="page-head"><div class="container"><div class="page-title"><h3><small></small></h3></div></div>');



}

jQuery(function($) {
 createNav();
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
});
function autoWidth(){
    $(cfg.grid_selector).jqGrid('setGridWidth',$(".page-content").width());
}

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

}