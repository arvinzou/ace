<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn" ng-app="myApp" >
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <title>周边资源</title>
    <jsp:include page="../../common/common-www.jsp"/>
    <script>
            var category='${param.category}';
    </script>
</head>
<body ontouchstart ng-controller="myCtrl">
<div class="weui-pull-to-refresh__layer">
    <div class='weui-pull-to-refresh__arrow'></div>
    <div class='weui-pull-to-refresh__preloader'></div>
    <div class="down">下拉刷新</div>
    <div class="up">释放刷新</div>
    <div class="refresh">正在刷新</div>
</div>
<div >

    <div class="weui-search-bar" id="searchBar">
        <form class="weui-search-bar__form" onsubmit="return t_query()">
            <div class="weui-search-bar__box">
                <i class="weui-icon-search"></i>
                <input type="search" ng-model="name" ng-change="query()"  class="weui-search-bar__input"
                       placeholder="搜索" required="">
                <a href="javascript:" class="weui-icon-clear" id="searchClear" ng-click="clear()"></a>
            </div>
            <label class="weui-search-bar__label" id="searchText"
                   style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1);">
                <i class="weui-icon-search"></i>
                <span>搜索</span>
            </label>
        </form>
        <a href="javascript:" class="weui-search-bar__cancel-btn" ng-click="clear()" id="searchCancel">取消</a>
    </div>
    <div class="page__bd">
        <div class="weui-panel weui-panel_access">
            <div class="weui-panel__hd">周边资源</div>
            <div class="weui-panel__bd">
                <a ng-repeat="o in list" href="preview.jsp?id={{o.id}}" class="weui-media-box weui-media-box_appmsg">

                    <div class="weui-media-box__bd">
                        <h4 class="weui-media-box__title">{{o.name}}</h4>
                        <div style="font-size:14px">
                            <img class="iconList"  src="${portalPath}/content/common/weui/image/pin_location_50.04px_1189687_easyicon.net.png" />{{o.addr}}
                        </div>
                        <div style="font-size:14px">
                            <img class="iconList"  src="${portalPath}/content/common/weui/image/phone_32.088397790055px_1187704_easyicon.net.png" />
                            {{o.tel}}

                            <img class="iconList"  src="${portalPath}/content/common/weui/image/time_71.003460207612px_1195888_easyicon.net.png" />
                            {{o.serviceTimeStart}}-{{o.serviceTimeEnd}}

                        </div>
                    </div>

                </a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../../common/footer-1-www.jsp"/>
<script
        src="${portalPath}/content/common/angularjs/angular.min.js"></script>
<script src="${portalPath}/content/common/weui/fastclick.js"></script>
<script src="${pageContext.request.contextPath}/content/www/organization/controller.js"></script>
<style>
.navigator-arrow {
  padding-right: 26px;
  position: relative;
}
.navigator-arrow:after {
  content: " ";
  display: inline-block;
  height: 18px;
  width: 18px;
  border-width: 1px 1px 0 0;
  border-color: #888888;
  border-style: solid;
  transform: matrix(0.71, 0.71, -0.71, 0.71, 0, 0);
  position: absolute;
  top: 50%;
  margin-top: -8px;
  right: 28px;
}
.iconList{
  width:16px; height: 16px;
  padding-right: 10rpx;
  vertical-align: middle;

}
.photo {
    height: 70px;
    max-height:70px;
    max-width:70px;
}
</style>
</body>
</html>