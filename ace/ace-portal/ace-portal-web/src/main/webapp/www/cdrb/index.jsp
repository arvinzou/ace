<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@page import="com.huacainfo.ace.portal.web.tools.JsoupUtils"%>
<%@page import="com.huacainfo.ace.common.tools.*"%>
<%@page import="java.util.*"%>
<%

int pages=0;
String p=request.getParameter("p");
if(CommonUtils.isBlank(p)){
pages=0;
}else{
    pages=Integer.parseInt(p);
}

List<Map<String,String>> list=JsoupUtils.getUrlList();
Map<String,String> index=list.get(pages);


%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="viewport" content="user-scalable=no">
    <jsp:include page="../../dynamic/common/common-www.jsp" />

<title><%=index.get("title")%></title>

</head>
<body ontouchstart>
<div class="weui-pull-to-refresh__layer">
    <div class='weui-pull-to-refresh__arrow'></div>
    <div class='weui-pull-to-refresh__preloader'></div>
    <div class="down">下拉刷新</div>
    <div class="up">释放刷新</div>
    <div class="refresh">正在刷新</div>
</div>





<div>



            <%=JsoupUtils.getPageBody(index.get("htm"),"A"+pages)%>


</div>


<footer>
   <h2 style="text-align:center"><%=index.get("title")%> </h2>
</footer>
<jsp:include page="../../dynamic/common/footer-1-www.jsp" />
<script src="${pageContext.request.contextPath}/content/common/weui/fastclick.js"></script>
<script>

jQuery(function($) {
    if (!window.innerHeight) {
    var height = 0;
    if (window.innerHeight) {
    height = window.innerHeight;
    } else if (document.documentElement
        && document.documentElement.clientHeight) {
    height = document.documentElement.clientHeight;
    } else if (document.body && document.body.clientHeight) {
    height = document.body.clientHeight;
    }
    window.innerHeight = height;
    }
    if (!window.innerWidth) {
    var width = 0;
    if (document.documentElement && document.documentElement.clientWidth) {
    width = document.documentElement.clientWidth;
    } else if (document.body && document.body.clientWidth) {
    width = document.body.clientWidth;
    } else if (window.innerWidth) {
    width = window.innerWidth;
    }
    window.innerWidth = width;
    }
    var areaLendth = document.getElementsByTagName('area').length;
    var bi=window.innerWidth/322;
    for(var k=0;k<areaLendth;k++){
        var coords=document.getElementsByTagName('area')[k].getAttribute('coords');
        var arrCoords=coords.split(',');
        for(var i=0;i<arrCoords.length;i++){
            arrCoords[i]=parseInt(arrCoords[i]*bi);
        }
        document.getElementsByTagName('area')[k].setAttribute('coords',arrCoords.join(','))
    }

             $(function() {
              FastClick.attach(document.body);
            });

      var pages=<%= pages%>;
      var total=<%= list.size()%>;
      $(document.body).pullToRefresh().on("pull-to-refresh", function() {
        setTimeout(function() {
           pages=pages+1;
           if(pages>=total){
                pages=0;
           }
           location.href="index.jsp?p="+pages;

        }, 100);
      });
});

</script>
</body>
</html>