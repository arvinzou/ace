<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <title>作品列表</title>
    <jsp:include page="../../common/common-www.jsp" />
</head>
<script>
var author='${param.id}';
 var category='${param.category}';
 </script>
<body ontouchstart style="background-color: #F8F8F8">
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
<div class="weui-panel__bd" style="background-color: #F8F8F8">
</div>




<jsp:include page="../../common/footer-1-www.jsp" />
<script src="${pageContext.request.contextPath}/content/common/lib/fastclick.js"></script>
<script src="${pageContext.request.contextPath}/content/www/js/article/controller.js"></script>
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
.swiper {
  height: 160px;
  object-fit: cover;
  width: 100%;
  margin-top:2px;
  margin-left:2px;
  margin-right: 5px;
}
.text-content {
  padding: 2px;
}
.text-line{
  margin-top:5px;
  margin-left:5px;
  margin-right: 5px;
  height: 1px;
  width: 94%;
  border-top: 1rpx solid #D8D8D8;
  color: #D8D8D8;
}
.list-item{
  flex-direction:column;

}
.kind-list-item {
  margin-top:8px;
  margin-left:8px;
  margin-right: 8px;
  background-color: #FFFFFF;
  border-radius: 4px;
  overflow: hidden;
}
.item-text{
  font-size: 14px;
  color: darkgray
}
.index-hd {
  padding: 10px;
  text-align: center;
}

.index-bd {
  padding: 0 5px 5rpx;
}
</style>

</body>
</html>