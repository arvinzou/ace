<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <title>作品信息</title>
    <jsp:include page="../../common/common-www1.jsp"/>
    <script>
        var id = '${param.id}';
    </script>
</head>
<body ontouchstart style="padding:5px">
<!-- Root element of PhotoSwipe. Must have class pswp. -->
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
<a href="javascript:return false" class="weui-media-box weui-media-box_appmsg">
    <div class="weui-media-box__bd">
        <h4 class="weui-media-box__title" id="name"></h4>
        <p class="weui-media-box__desc"><span id="dateOfPublication"></span> 作者：<span id="author"></span> 阅读：<span
                id="reading"></span>点赞数：<span id="likes">1</span></p>
    </div>
</a>


<!--data-pswp-uid在每个相册中必须是唯一的，data-size指定放大时图片显示的宽和高-->
<div class="my-gallery" data-pswp-uid="1" style="text-align:center">
    <figure>

    </figure>
</div>

<div id="intro" style="padding:12px">
</div>
<div id="descri" class="my-gallery" data-pswp-uid="1" style="text-align:center;padding:12px">

</div>
<div class="like_btn">
    <img id="likeHeart"
         src="data:image/png;base64,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"
         alt="">
</div>

<link rel="stylesheet prefetch" href="${pageContext.request.contextPath}/content/common/photoview/photoswipe.css">
<link rel="stylesheet prefetch"
      href="${pageContext.request.contextPath}/content/common/photoview/default-skin/default-skin.css">
<script src="${pageContext.request.contextPath}/content/common/photoview/photoswipe.js"></script>
<script src="${pageContext.request.contextPath}/content/common/photoview/photoswipe-ui-default.min.js"></script>
<script src="${pageContext.request.contextPath}/content/common/lib/fastclick.js"></script>

<link rel="stylesheet prefetch" href="${pageContext.request.contextPath}/content/common/photoview/photoswipe.css">
<link rel="stylesheet prefetch"
      href="${pageContext.request.contextPath}/content/common/photoview/default-skin/default-skin.css">
<script src="${pageContext.request.contextPath}/content/common/photoview/photoswipe.js"></script>
<script src="${pageContext.request.contextPath}/content/common/photoview/photoswipe-ui-default.min.js"></script>

<jsp:include page="../../common/footer-1-www1.jsp"/>
<script src="${pageContext.request.contextPath}/content/common/lib/fastclick.js"></script>
<script src="${pageContext.request.contextPath}/content/www/js/writing/preview.js"></script>

<style>
    .like_btn {
        position: fixed;
        bottom: 10%;
        right: 10%;
    }

    .like_btn img {
        width: 50px;
    }
</style>

</body>
</html>