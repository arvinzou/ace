<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"  />
    <meta name="format-detection" content="telephone=no"/>
    <%@ include file="../../../dynamic/common/common.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/www/view/jxbCources/jpCources/css/jpCources.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/dynamic/common/js/common.js"></script>
    <script src="${pageContext.request.contextPath}/content/www/js/init-rem.js"></script>
    <script src="${pageContext.request.contextPath}/content/www/js/juicer-min.js"></script>
    <title>精品课程</title>
</head>

<body>
<div class="jxbcontent">
    <div class="row" style="width:100%;margin: 0 auto;">
        <div id="myCarousel"  class="carousel slide" data-ride="carousel">
            <!-- 轮播（Carousel）指标 -->
            <ol class="carousel-indicators" style="padding-bottom: 0.02rem">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
            </ol>
            <!-- 轮播（Carousel）项目 -->
            <div class="carousel-inner" id="sliceImgContainer">
                <!-- <div class="item active">
                     <img src="html/img/1.png" alt="First slide" style="height: 3rem; width: 100%; object-fit: cover;"/>
                 </div>
                 <div class="item">
                     <img src="html/img/2.png" alt="Second slide" style="height: 3rem; width: 100%; object-fit: cover;">
                 </div>
                 <div class="item">
                     <img src="html/img/3.png" alt="Third slide" style="height: 3rem; width: 100%; object-fit: cover;">
                 </div>-->
            </div>
        </div>
    </div>
    <div style="margin-top: 0.3rem"></div>
    <div class="row" style="text-align:center;color: #aaa; width:100%;margin: 0 auto;overflow: hidden;" >
        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
            <div  data-field="type"  onclick="changeCourseTab('01')">
                <div style="width: 1.5rem;height:1.5rem;background-color: #7a8479;border-radius:50%;margin: 0 auto;">
                    <img src="jpCources/img/qzgy.png" height="100%" width="100%"/>
                </div>
                <div class="title" style="font-size: 12px;">亲子关系</div>
            </div>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
            <div  data-field="type" onclick="changeCourseTab('02')">
                <div style="width: 1.5rem;height:1.5rem;background-color: #7a8479;border-radius:50%;margin: 0 auto;">
                    <img src="jpCources/img/hyjt.png" height="100%" width="100%"/>
                </div>
                <div class="title" style="font-size: 12px;">婚姻家庭</div>
            </div>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
            <div  data-field="type"  onclick="changeCourseTab('03')">
                <div style="width: 1.5rem;height:1.5rem;background-color: #7a8479;border-radius:50%;margin: 0 auto;">
                    <img src="jpCources/img/qxtk.png" height="100%" width="100%"/>
                </div>
                <div class="title" style="font-size: 12px;">情绪调控</div>
            </div>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
            <div  data-field="type"   onclick="changeCourseTab('04')">
                <div style="width: 1.5rem;height:1.5rem;background-color: #7a8479;border-radius:50%;margin: 0 auto;">
                    <img src="jpCources/img/zcyl.png" height="100%" width="100%"/>
                </div>
                <div class="title" style="font-size: 12px;">职场压力</div>
            </div>
        </div>
    </div>
    <div style="margin-bottom : 0.3rem;"></div>
    <div  class="row" style="width: 100%;margin: 0 auto;">
        <ul class="sceneList">

        </ul>
    </div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/www/view/jxbCources/jpCources/js/jpCources.js"></script>
</html>

