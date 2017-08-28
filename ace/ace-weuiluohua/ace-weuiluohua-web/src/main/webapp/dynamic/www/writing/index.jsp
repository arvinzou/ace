<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<title>国艺烙画</title>

    <%
    session.setAttribute("portalPath", "/portal");

    %>
    <script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}';
	var portalPath = '${portalPath}';
	var fastdfs_server = 'http://zx.huacainfo.com/';
</script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/lib/weui.min.css?version=${cfg.version}" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/lib/weui.css?version=${cfg.version}" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/lib/weui2.css?version=${cfg.version}" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/lib/weui3.css?version=${cfg.version}" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/css/jquery-weui.min.css?version=${cfg.version}" />
</head>
<script>
var author='${param.id}';
 var category='${param.category}';
 </script>
<body ontouchstart style="background-color: #ffffff;">

<div class="weui-pull-to-refresh__layer">
     <div class='weui-pull-to-refresh__arrow'></div>
     <div class='weui-pull-to-refresh__preloader'></div>
     <div class="down">下拉刷新</div>
     <div class="up">释放刷新</div>
     <div class="refresh">正在刷新</div>
   </div>
 <div class="weui-search-bar" id="searchBar">
      <form class="weui-search-bar__form" onsubmit="return t_query()">
        <div class="weui-search-bar__box">
          <i class="weui-icon-search"></i>
                 <input type="search"  class="weui-search-bar__input" id="searchInput" placeholder="搜索" required="">
          <a href="javascript:" class="weui-icon-clear" id="searchClear"></a>
        </div>
        <label class="weui-search-bar__label" id="searchText" style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1);">
          <i class="weui-icon-search"></i>
          <span>搜索</span>
        </label>
      </form>
      <a href="javascript:" class="weui-search-bar__cancel-btn" id="searchCancel">取消</a>
    </div>
<!--
   <div class="page__bd">
     <div class="weui-panel weui-panel_access">
           <div class="weui-panel__hd">作品列表</div>
           <div class="weui-panel__bd">
           </div>
     </div>
   </div>
-->
 <div class="weui_grids">

 </div>



 <jsp:include page="../../common/footer-1-www.jsp" />
 <script src="${pageContext.request.contextPath}/content/common/lib/fastclick.js"></script>
  <script src="${pageContext.request.contextPath}/content/www/js/writing/controller.js"></script>
  <script>
            $(function() {
              FastClick.attach(document.body);
            });
          </script>
<script>
     var start=0;
      $(document.body).pullToRefresh().on("pull-to-refresh", function() {
        setTimeout(function() {
           start=start+10;
          loadText(author,null,start,10);

        }, 100);
      });
    </script>
<style>

 .weui_grid_icon2 {
    display: inline-block;
    width: 100px;
    height: 100px;
    overflow: hidden;

}
.weui_grid_icon2 img {
    max-width: 90px;
    max-height: 90px;
}
.weui_grid {
    position: relative;
    float: left;
    padding: 20px 10px;
    width: 33.33333333%;
    box-sizing: border-box;
    text-align: center;
}
</style>

</body>
</html>