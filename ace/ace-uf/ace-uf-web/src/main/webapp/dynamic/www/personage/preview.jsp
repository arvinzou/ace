<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn" ng-app="myApp">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <title>统战人士</title>
    <jsp:include page="../../common/common-www.jsp"/>
    <script>
var id='${param.id}';


    </script>
</head>
<body ontouchstart style="padding:5px" ng-controller="myCtrl">
<div>
    <div class="weui-flex">
        <div class="weui-flex__item" style="text-align: center;">
            <div data-pswp-uid="1" class="my-gallery">

                <img class="photo" ng-if="o.photo"
                     src="{{fastdfs_server+ o.photo}}"/>

            </div>
            <div style="text-align: center;padding:2px;font-weight:800;font-size:14px">
                {{o.name}}
            </div>
        </div>
        <div class="weui-flex__item">
            <div class="weui-form-preview__bd">

                <div class="weui-form-preview__item item-text-min">
                    <label class="weui-form-preview__label">党派</label>
                    <span class="weui-form-preview__value">{{o.party}}</span>
                </div>

                <div class="weui-form-preview__item item-text-min">
                    <label class="weui-form-preview__label">辖区</label>
                    <span class="weui-form-preview__value">{{o.areaName}}</span>
                </div>
                <div class="weui-form-preview__item item-text-min">
                    <label class="weui-form-preview__label">单位</label>
                    <span class="weui-form-preview__value">{{o.deptName}}</span>
                </div>
                <div class="weui-form-preview__item item-text-min">
                    <label class="weui-form-preview__label">生日</label>
                    <span class="weui-form-preview__value">{{o.birthday}}</span>
                </div>
                <div class="weui-form-preview__item item-text-min">
                    <label class="weui-form-preview__label">电话</label>
                    <span class="weui-form-preview__value">{{o.tel}}</span>
                </div>
            </div>
        </div>
    </div>


    <div class="weui-form-preview__bd">
        <div class="weui-form-preview__item item-text">
            <label class="weui-form-preview__label">类别</label>
            <span class="weui-form-preview__value">{{o.category}}</span>
        </div>
        <div class="weui-form-preview__item item-text">
            <label class="weui-form-preview__label">籍贯</label>
            <span class="weui-form-preview__value">{{o.placeOfOriginName}}</span>
        </div>
        <div class="weui-form-preview__item item-text">
            <label class="weui-form-preview__label">民族</label>
            <span class="weui-form-preview__value">{{o.nation}}</span>
        </div>
        <div class="weui-form-preview__item item-text">
            <label class="weui-form-preview__label">职级</label>
            <span class="weui-form-preview__value">{{o.rank}}</span>
        </div>
        <div class="weui-form-preview__item item-text">
            <label class="weui-form-preview__label">加入时间</label>
            <span class="weui-form-preview__value">{{o.joinDate}}</span>
        </div>
        <div class="weui-form-preview__item item-text">
            <label class="weui-form-preview__label">行政职务</label>
            <span class="weui-form-preview__value">{{o.administrativeDuty}}</span>
        </div>
        <div class="weui-form-preview__item item-text">
            <label class="weui-form-preview__label">学历</label>
            <span class="weui-form-preview__value">{{o.degreee}}</span>
        </div>

        <div class="weui-form-preview__item item-text">
            <label class="weui-form-preview__label">职称</label>
            <span class="weui-form-preview__value">{{o.academicTitle}}</span>
        </div>

        <div class="weui-form-preview__item item-text">
            <label class="weui-form-preview__label">现任职务时间</label>
            <span class="weui-form-preview__value">{{o.currentPostDate}}</span>
        </div>
        <div class="weui-form-preview__item item-text">
            <label class="weui-form-preview__label">现任职级时间</label>
            <span class="weui-form-preview__value">{{o.currentRankDate}}</span>
        </div>

    </div>
    <h3 class="header-title" ng-if="o.intro">简介</h3>
    <div style="padding:12px" ng-if="o.intro">
        {{o.intro}}
    </div>


    <div style="padding:12px" ng-if="o.remark">
        <img class="photo" src="{{fastdfs_server+ o.remark}}"/>
        <div  style="text-align: center;padding:2px;font-weight:800;font-size:14px">二维码</div>
    </div>

</div>
<script
        src="${portalPath}/content/common/js/dict_uf.js"></script>

<jsp:include page="../../common/footer-1-www.jsp"/>


<script src="${portalPath}/content/common/photoview/photoswipe.js"></script>
<script src="${portalPath}/content/common/photoview/photoswipe-ui-default.min.js"></script>
<link rel="stylesheet prefetch" href="${portalPath}/content/common/photoview/photoswipe.css">
<link rel="stylesheet prefetch" href="${portalPath}/content/common/photoview/default-skin/default-skin.css">
<script src="${portalPath}/content/common/photoview/photoswipe.js"></script>
<script src="${portalPath}/content/common/photoview/photoswipe-ui-default.min.js"></script>


<div class="pswp" tabindex="-1" role="dialog" aria-hidden="true">

    <!-- Background of PhotoSwipe.
         It's a separate element as animating opacity is faster than rgba(). -->
    <div class="pswp__bg"></div>

    <!-- Slides wrapper with overflow:hidden. -->
    <div class="pswp__scroll-wrap">

        <!-- Container that holds slides.
            PhotoSwipe keeps only 3 of them in the DOM to save memory.
            Don't modify these 3 pswp__item elements, data is added later on. -->
        <div class="pswp__container">
            <div class="pswp__item"></div>
            <div class="pswp__item"></div>
            <div class="pswp__item"></div>
        </div>

        <!-- Default (PhotoSwipeUI_Default) interface on top of sliding area. Can be changed. -->
        <div class="pswp__ui pswp__ui--hidden">

            <div class="pswp__top-bar">

                <!--  Controls are self-explanatory. Order can be changed. -->

                <div class="pswp__counter"></div>

                <button class="pswp__button pswp__button--close" title="Close (Esc)"></button>

                <button class="pswp__button pswp__button--share" title="Share"></button>

                <button class="pswp__button pswp__button--fs" title="Toggle fullscreen"></button>

                <button class="pswp__button pswp__button--zoom" title="Zoom in/out"></button>

                <!-- element will get class pswp__preloader--active when preloader is running -->
                <div class="pswp__preloader">
                    <div class="pswp__preloader__icn">
                        <div class="pswp__preloader__cut">
                            <div class="pswp__preloader__donut"></div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
                <div class="pswp__share-tooltip"></div>
            </div>

            <button class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)">
            </button>

            <button class="pswp__button pswp__button--arrow--right" title="Next (arrow right)">
            </button>

            <div class="pswp__caption">
                <div class="pswp__caption__center"></div>
            </div>

        </div>

    </div>

</div>
<script
        src="${portalPath}/content/common/angularjs/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/content/www/personage/preview.js"></script>
<style>
.photo{
    max-width: 150px;
    max-height: 150px;

}
.header-title {
    line-height: 40px;
    margin-bottom: 5px;
    margin-top: 5px;
    padding-bottom: 4px;
    border-bottom: 1px solid #e5e5e5;
    font-weight:800;
    font-size:14px;
}
.item-text {
    line-height: 40px;
    margin-bottom: 5px;
    margin-top: 5px;
    padding-bottom: 4px;
    border-bottom: 1px solid #e5e5e5;
    font-weight:800;
    font-size:14px;
}
.weui-form-preview__bd {
    padding: 10px 15px;
    font-size: .9em;
    text-align: right;
    line-height: 2;
}
.weui-form-preview__label {
    float: left;
    margin-right: 1em;
    min-width: 1em;
    text-align: justify;
    text-align-last: justify;
}


</style>
</body>
</html>