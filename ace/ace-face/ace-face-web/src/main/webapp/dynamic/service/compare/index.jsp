<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>人脸对比</title>
</head>
<jsp:include page="../../common/common.jsp"/>
<link rel="stylesheet" href="${portalPath}/content/common/swiper/css/swiper.min.css">
<style>
.swiper-container1 {
      width: 90%;
      padding-top: 50px;
      padding-bottom: 50px;
    }
    .swiper-container2 {
      width: 90%;
      padding-top: 50px;
      padding-bottom: 50px;
    }
    .swiper-slide {
      background-position: center;
      background-size: cover;
      width: 100px;
      height: 100px;
    }
    .center{
        text-align:center
    }
    .photo{
        max-height:300px;
        min-height:300px;
    }

</style>
<body>
<div class="page-content">
    <div class="row">
        <div class="col-xs-12 col-sm-6 center photo" id="box1"></div>
        <div class="col-xs-12 col-sm-6 center photo" id="box2"></div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-6 center">
            <div class="swiper-container1">
                <div class="swiper-wrapper">
                    <div class="swiper-slide" data="http://lorempixel.com/600/600/nature/1" style="background-image:url(http://lorempixel.com/600/600/nature/1)"></div>
                    <div class="swiper-slide" data="http://lorempixel.com/600/600/nature/2" style="background-image:url(http://lorempixel.com/600/600/nature/2)"></div>
                    <div class="swiper-slide" data="http://lorempixel.com/600/600/nature/3" style="background-image:url(http://lorempixel.com/600/600/nature/3)"></div>
                    <div class="swiper-slide" data="http://lorempixel.com/600/600/nature/4"  style="background-image:url(http://lorempixel.com/600/600/nature/4)"></div>
                    <div class="swiper-slide" data="http://lorempixel.com/600/600/nature/5"  style="background-image:url(http://lorempixel.com/600/600/nature/5)"></div>
                    <div class="swiper-slide" data="http://lorempixel.com/600/600/nature/6"  style="background-image:url(http://lorempixel.com/600/600/nature/6)"></div>
                    <div class="swiper-slide" data="http://lorempixel.com/600/600/nature/7"  style="background-image:url(http://lorempixel.com/600/600/nature/7)"></div>
                    <div class="swiper-slide" data="http://lorempixel.com/600/600/nature/8"  style="background-image:url(http://lorempixel.com/600/600/nature/8)"></div>
                    <div class="swiper-slide" data="http://lorempixel.com/600/600/nature/9"  style="background-image:url(http://lorempixel.com/600/600/nature/9)"></div>
                    <div class="swiper-slide" data="http://lorempixel.com/600/600/nature/10"  style="background-image:url(http://lorempixel.com/600/600/nature/10)"></div>
                </div>
                <!-- Add Pagination -->
                <div class="swiper-pagination"></div>
            </div>

        </div>
        <div class="col-xs-12 col-sm-6 center">
            <div class="swiper-container2">
                <div class="swiper-wrapper">
                    <div class="swiper-slide" data="http://lorempixel.com/600/600/nature/1" style="background-image:url(http://lorempixel.com/600/600/nature/1)"></div>
                    <div class="swiper-slide" data="http://lorempixel.com/600/600/nature/2" style="background-image:url(http://lorempixel.com/600/600/nature/2)"></div>
                    <div class="swiper-slide" data="http://lorempixel.com/600/600/nature/3" style="background-image:url(http://lorempixel.com/600/600/nature/3)"></div>
                    <div class="swiper-slide" data="http://lorempixel.com/600/600/nature/4"  style="background-image:url(http://lorempixel.com/600/600/nature/4)"></div>
                    <div class="swiper-slide" data="http://lorempixel.com/600/600/nature/5"  style="background-image:url(http://lorempixel.com/600/600/nature/5)"></div>
                    <div class="swiper-slide" data="http://lorempixel.com/600/600/nature/6"  style="background-image:url(http://lorempixel.com/600/600/nature/6)"></div>
                    <div class="swiper-slide" data="http://lorempixel.com/600/600/nature/7"  style="background-image:url(http://lorempixel.com/600/600/nature/7)"></div>
                    <div class="swiper-slide" data="http://lorempixel.com/600/600/nature/8"  style="background-image:url(http://lorempixel.com/600/600/nature/8)"></div>
                    <div class="swiper-slide" data="http://lorempixel.com/600/600/nature/9"  style="background-image:url(http://lorempixel.com/600/600/nature/9)"></div>
                    <div class="swiper-slide" data="http://lorempixel.com/600/600/nature/10"  style="background-image:url(http://lorempixel.com/600/600/nature/10)"></div>
                </div>
                <!-- Add Pagination -->
                <div class="swiper-pagination"></div>
            </div>

        </div>
    </div>
    <div class="row center">

    </div>
    <div class="row center">

    </div>

</div>
<jsp:include page="../../common/footer-1.jsp"/>

<link rel="stylesheet"
      href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
      type="text/css" media="screen"/>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
<script  src="${pageContext.request.contextPath}/content/service/person/face.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/swiper/js/swiper.min.js"></script>
<jsp:include page="../../common/footer-2.jsp"/>
<script>
  var swiper1 = new Swiper('.swiper-container1', {
      effect: 'coverflow',
      grabCursor: true,
      centeredSlides: true,
      slidesPerView: 'auto',
      coverflowEffect: {
        rotate: 50,
        stretch: 0,
        depth: 100,
        modifier: 1,
        slideShadows : true,
      },
      pagination: {
        el: '.swiper-pagination',
      },
      on: {
        transitionEnd: function () {
          var image_url=$(".swiper-container1").find(".swiper-slide-active").attr("data");
          $("#box1").html("<img src='"+image_url+"' class='photo'/>");
        }
      }
    });

    var swiper2 = new Swiper('.swiper-container2', {
      effect: 'coverflow',
      grabCursor: true,
      centeredSlides: true,
      slidesPerView: 'auto',
      coverflowEffect: {
        rotate: 50,
        stretch: 0,
        depth: 100,
        modifier: 1,
        slideShadows : true,
      },
      pagination: {
        el: '.swiper-pagination',
      },
      on: {
        transitionEnd: function () {
             var image_url=$(".swiper-container2").find(".swiper-slide-active").attr("data");
          $("#box2").html("<img src='"+image_url+"' class='photo'/>");
        }
      }
    });
</script>
</body>
</html>