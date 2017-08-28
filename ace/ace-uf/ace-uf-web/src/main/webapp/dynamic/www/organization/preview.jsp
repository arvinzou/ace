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
        var id='${param.id}';

    </script>
</head>
<body ontouchstart style="padding:5px" ng-controller="myCtrl">
<div>

    <div class="swiper-container" data-space-between='10' data-pagination='.swiper-pagination' data-autoplay="1000">
        <div class="swiper-wrapper">
            <div class="swiper-slide" style="height:250px" ng-repeat="x in o.list" on-finish-render-filters><img
                    src="{{fastdfs_server+ x.url}}"
                    alt=""></div>

        </div>
        <div class="swiper-pagination"></div>
    </div>

    <div style="flex-direction:column;">
        <div  class="navigator">
            <div class="title-strong-big1"> {{o.name}}</div>
        </div>
        <div   bindtap="openLocation" class="navigator">
            <div class="navigator-text">
                <img class="iconList" src="${portalPath}/content/common/weui/image/pin_location_50.04px_1189687_easyicon.net.png" mode="aspectFit"/> {{o.addr}}
            </div>

        </div>
        <div   class="navigator">
            <div class="navigator-text">
                <img class="iconList" src="${portalPath}/content/common/weui/image/phone_32.088397790055px_1187704_easyicon.net.png" mode="aspectFit"/> {{o.tel}}    </div>

        </div>
        <div  class="navigator">
            <img class="iconList"  src="${portalPath}/content/common/weui/image/time_71.003460207612px_1195888_easyicon.net.png" mode="aspectFit"/>   {{o.serviceTimeStart}}-{{o.serviceTimeEnd}}
        </div>
        <div class="navigator center" ng:if="o.icon">
            <img    src="{{fastdfs_server+o.icon}}"></img>
        </div>

        <div  class="navigator" style="flex-direction:column;">
            <div style="text-align:left;width:100%" class="title-strong-big2">
                服务途径
            </div>
            <div style="font-size:14px">
                {{o.serviceWay}}
            </div>
        </div>

        <div class="navigator" style="flex-direction:column;">
            <div style="text-align:left;width:100%" class="title-strong-big2">
                说明
            </div>
            <div style="font-size:14px">
                {{o.remark}}
            </div>
        </div>

    </div>


    <jsp:include page="../../common/footer-1-www.jsp"/>
    <script
            src="${portalPath}/content/common/angularjs/angular.min.js"></script>
    <script src="${pageContext.request.contextPath}/content/www/organization/preview.js"></script>
    <style>
    .title-strong-big1{
  font-size: 25px;
  font-weight: 800;
}
.title-strong-big2{
  font-size: 20px;
  font-weight: 800;
}


.iconList{
  width:16px; height: 16px;
  padding-right: 10px;
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