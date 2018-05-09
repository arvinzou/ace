/**
     * 加载js或css到head中
     * @param     jsonData.path        前缀路径
     * @param     jsonData.url        需要加载的js路径或css路径
     * @param     jsonData.type        需要加载的类型 js或css
     */
     var  urlParams = {};
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
     function loadCommon(){
         var urls=[];
         urls.push({path:portalPath,url:'/content/common/js/init-rem.js',type:'js'});
         urls.push({path:portalPath,url:'/content/common/js/loading.js',type:'js'});
         urls.push({path:portalPath,url:'/system/getUserProp.do',type:'js'});
         urls.push({path:portalPath,url:'/content/common/assets/css/font-awesome.min.css',type:'css'});
         urls.push({path:portalPath,url:'/content/common/assets/global/plugins/bootstrap/css/bootstrap.min.css',type:'css'});
         urls.push({path:portalPath,url:'/content/common/assets/global/css/components.min.css',type:'css'});
         urls.push({path:portalPath,url:'/content/common/assets/layouts/layout3/css/layout.min.css',type:'css'});
         urls.push({path:portalPath,url:'/content/common/assets/global/plugins/bootstrap/js/bootstrap.min.js',type:'js'});
         urls.push({path:portalPath,url:'/content/portal/js/main/menu4.js',type:'js'});
         try{
            if(typeof(eval("App")) == "function"){
               urls.push({path:portalPath,url:'/content/common/juicer/juicer-min.js',type:'js',callback:App});
            }
         }catch(e){
            urls.push({path:portalPath,url:'/content/common/juicer/juicer-min.js',type:'js'});
         }
          for(var i=0;i<urls.length;i++){
             loader(urls[i]);
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
     loader({path:portalPath,url:'/content/common/assets/global/plugins/jquery.min.js',type:'js',callback:loadCommon});




