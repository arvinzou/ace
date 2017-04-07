<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<title>作家</title>
<jsp:include page="../../common/common-www.jsp" />
</head>
<body ontouchstart>
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
           <input type="search" class="weui-search-bar__input" id="searchInput" placeholder="搜索" required="">
           <a href="javascript:" class="weui-icon-clear" id="searchClear"></a>
         </div>
         <label class="weui-search-bar__label" id="searchText" style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1);">
           <i class="weui-icon-search"></i>
           <span>搜索</span>
         </label>
       </form>
       <a href="javascript:" class="weui-search-bar__cancel-btn" id="searchCancel">取消</a>
     </div>
    <div class="page__bd">
      <div class="weui-panel weui-panel_access">
        <div class="weui-panel__hd">作家列表</div>
        <div class="weui-panel__bd">

        </div>

      </div>
    </div>




<jsp:include page="../../common/footer-1-www.jsp" />
 <script src="${pageContext.request.contextPath}/content/common/lib/fastclick.js"></script>
  <script src="${pageContext.request.contextPath}/content/www/js/writer/controller.js"></script>
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
          loadText(null,start,10);

        }, 100);
      });
    </script>


</body>
</html>