var loading;

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
            html.push('<li class="nav-item" id="menu'+i+'">');
            html.push('<a class="nav-link nav-toggle" href="#"><i class="'+ menu.icon + '"></i><span class="title">'+ menu.text + ' </span>');
            html.push('<span class="selected"></span>');
            if (menu.leaf != true && menu.leaf != 'true') {
                html.push('<span class="arrow"></span>');
            }
            html.push('</a>');
            var initSubMenu = function(menu) {
                if (menu.leaf != true && menu.leaf != 'true') {
                    var childrens = menu.children, len = childrens.length;
                    html.push('<ul class="sub-menu" style="display: none;">');
                    for (var i = 0; i < len; i++) {
                        if(urlParams.id){
                            if(urlParams.id==childrens[i].id){
                                html.push('<li class="nav-item active open">');
                                if (childrens[i].href) {
                                    html.push('<a class="nav-link"  href="#" title="'+ childrens[i].text+ '" url="'+ childrens[i].href+ '" ><i class1="'+ childrens[i].icon+ '"></i><span class="title">'+ childrens[i].text + '</span></a><span class="arrow selected"></span>');
                                } else {
                                    html.push('<a class="nav-link nav-toggle" href="#"><i class1="'+ childrens[i].icon + '"></i><span class="title">'+ childrens[i].text+ '</span><span class="arrow selected"></span></a>');
                                }
                            }else{
                                html.push('<li class="nav-item">');
                                if (childrens[i].href) {
                                    html.push('<a class="nav-link"  href="#" title="'+ childrens[i].text+ '" url="'+ childrens[i].href+ '" ><i class1="'+ childrens[i].icon+ '"></i><span class="title">'+ childrens[i].text + '</span></a><span class="arrow"></span>');
                                } else {
                                    html.push('<a class="nav-link nav-toggle" href="#"><i class1="'+ childrens[i].icon + '"></i><span class="title">'+ childrens[i].text+ '</span><span class="arrow"></span></a>');
                                }
                            }
                        }else{
                            html.push('<li class="nav-item">');
                            if (childrens[i].href) {
                                html.push('<a class="nav-link"  href="#" title="'+ childrens[i].text+ '" url="'+ childrens[i].href+ '" ><i class1="'+ childrens[i].icon+ '"></i><span class="title">'+ childrens[i].text + '</span></a><span class="arrow"></span>');
                            } else {
                                html.push('<a class="nav-link nav-toggle" href="#"><i class1="'+ childrens[i].icon + '"></i><span class="title">'+ childrens[i].text+ '</span><span class="arrow"></span></a>');
                            }
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
	//loader({path:portalPath,url:'/content/common/assets/global/scripts/app.min.js',type:'js'});
	//loader({path:portalPath,url:'/content/common/assets/layouts/layout/scripts/layout.min.js',type:'js'});
	 loadCommon();
	$('#menu a[url]').bind('click', function() {
		var url = $(this).attr("url");
		if (url) {
			location.href=url;
		}
	});
	$('.nav-item').bind('click', function() {
    		$('.nav-item').removeClass("active");
    		$('.nav-item').find("#selected").removeClass("selected");
    		$(this).siblings().removeClass("open");
            $(this).addClass("active open");
            $(this).find("#selected").addClass("selected");
   });
  var activeNode=$(".page-sidebar-menu li .open");
  if(urlParams.id){
     $(activeNode).parent().css('display','block');
      $(activeNode).parent().parent().addClass("active open");

      $(activeNode).parent().parent().parent().css('display','block');
      $(activeNode).parent().parent().parent().parent().addClass("active open");

      var title=$(activeNode).find("a span").html();

      $("title").html(title);
      $("#widget-box h5").html(title);
  }else{
        $("#menu0").addClass("active");
       $("#menu0").find("#selected").addClass("selected");

  }
}

function initMenu(){
    createNav();
    if(sessionStorage&&sessionStorage.getItem("menu")){
        var srt=sessionStorage.getItem("menu");
        var data=JSON.parse(srt);
        console.log(data);
        buildMenu(data);

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


            }
        });
     }

}

 function loadCommon(){
         var urls=[];
         urls.push({path:portalPath,url:'/content/common/assets/js/gz/bootstrap.min.js',type:'js'});
         urls.push({path:portalPath,url:'/content/common/assets/js/gz/bootbox.min.js',type:'js'});
         urls.push({path:portalPath,url:'/content/common/assets/pages/scripts/ui-modals.min.js',type:'js'});
         urls.push({path:portalPath,url:'/content/common/assets/global/scripts/app.min.js',type:'js',callback:function(){
            loader({path:portalPath,url:'/content/common/assets/layouts/layout/scripts/layout.min.js',type:'js'});
         }});
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
    $("body").addClass("page-header-fixed page-sidebar-closed-hide-logo page-content-white");
	$(".page-content").wrap('<div class="page-content-wrapper"></div>');
	$(".page-content-wrapper").wrap('<div class="page-container"></div>');
	$(".page-content-wrapper").before('<div class="page-sidebar-wrapper"> <div class="page-sidebar navbar-collapse collapse"><ul id="menu" class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 0px"></ul></div></div>');


	$(".page-container").wrap('<div class="page-wrapper"></div>');
	$(".page-container").before('<div class="clearfix"></div>');

	 if(sessionStorage&&sessionStorage.getItem("html_top")){
            var data=sessionStorage.getItem("html_top");
             $("body .page-container div:first").before(data);
         }else{
            $.ajax({
                url : portalPath + '/dynamic/common/tpl/html_top.jsp',
                type : 'POST',
                timeout : 30000,
                dataType : 'text',
                success : function(data) {
                   $("body .page-container div:first").before(data);
                   if(sessionStorage){
                       console.log("sessionStorage");
                       sessionStorage.setItem("html_top",data);
                   }
                }
            });
        }
        $(".page-content").css("padding","10px");


}

jQuery(function($) {

     initMenu();
});
function autoWidth(){
    $(cfg.grid_selector).jqGrid('setGridWidth',$(".page-content").width());
}