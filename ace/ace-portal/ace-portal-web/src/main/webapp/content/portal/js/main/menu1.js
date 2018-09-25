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
		html.push(document.getElementById("tpl-menu-base").innerHTML);
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
	    loader({path:portalPath,url:'/content/common/assets/global/scripts/app.min.js',type:'js',callback:function(){
        loader({path:portalPath,url:'/content/common/assets/layouts/layout/scripts/layout.min.js',type:'js'});
     }});

	$('#menu a[url]').bind('click', function() {
		var url = $(this).attr("url");
		if (url) {
			if(url.indexOf("/")!=-1){
				location.href=url;
			}
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


          $(activeNode).parent().parent().parent().parent().css('display','block');
          $(activeNode).parent().parent().parent().parent().parent().addClass("active open");


          $(activeNode).parent().parent().parent().parent().parent().css('display','block');
          $(activeNode).parent().parent().parent().parent().parent().parent().addClass("active open");

         var title=$(activeNode).find("a span").html();

         $("title").html(title);
         $(".todo-header").html(title);
     }else{
           $("#menu999999").addClass("active");
            $("#menu999999").find("#selected").addClass("selected");
            var title="仪表盘";
            $("title").html(title);
             $(".todo-header").html(title);

     }


}



function initMenu(){
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

jQuery(function($) {
     $("body").addClass("page-header-fixed page-sidebar-closed-hide-logo page-content-white");
     initMenu();
     try{
     if(typeof(eval("App")) == "function"){
              App();
          }
     }catch(e){
     }

});

if(!window.console){
        window.console={};
        window.console.log=function(log){

        }
     }
