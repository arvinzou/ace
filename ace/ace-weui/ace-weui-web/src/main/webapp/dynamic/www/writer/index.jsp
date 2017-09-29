<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <title>常德文艺人才名录</title>
    <jsp:include page="../../common/common-www.jsp" />
    <script>
            var category='${param.category}';
  </script>
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

<div class="topdiv">
    本“名录”由常德本土纯文艺微信公众号《走向》收集、整理和维护，联系邮箱：cdwyxzx@163.com
</div>
<div class="slider" id="slider">
    <div class="slider-content" id="sliderContent"></div>
    <ul class="sliderNav" id="sliderNav"></ul>
</div>




<jsp:include page="../../common/footer-1-www.jsp" />
<script src="${pageContext.request.contextPath}/content/common/lib/fastclick.js"></script>
<script src="${pageContext.request.contextPath}/content/www/js/writer/index.js"></script>
<script>
            $(function() {
              FastClick.attach(document.body);
            });
  </script>
<script>
      var start=0;
      $(document.body).pullToRefresh().on("pull-to-refresh", function() {
        setTimeout(function() {
           //start=start+10;
          loadText(null,0,9000);

        }, 100);
      });
    </script>

<style>
    .weui-media-box__thumb2{
        width:60px;
        height:60px;
        object-fit: cover;
        margin-bottom:5px;
        padding-bottom:5px;
    }
a,ul,li{
    list-style: none;
    text-decoration: none;
}
.slider{width: 100%;height: 100%;position: relative ; padding-top: 5px }
.sliderNav{width: 10%;position: fixed;right: 0;height: 100%;text-align: center;top: 1.5rem;}
.slider>h2{
    font-size: 2rem;
    line-height: 3.5rem;
    padding-left: 1.5rem;
}
.sliderNav>li>a{
    font-size: 16px;
    color: #18a2ff;
}
.slider-content{
    position: absolute;
    width: 100%;
}
.slider-content>div{
    font-size: 16px;
    padding-left: 1.5rem;
    height: 50px;
    line-height: 50px;
    background-color: #F8F8F8;
}
.slider-content>div:nth-child(2n+1){
    background-color: #F8F8F8;
}
.slider-content>div:nth-child(2n){
    background-color: #F8F8F8;
}

.slider-content>ul{
    padding-top: 0.5rem;
}
.slider-content>ul>li{
}
.topdiv{
width: 100%; padding: 5px ;
 font-size: 12px;
 color: #999;
}
</style>
</body>
</html>