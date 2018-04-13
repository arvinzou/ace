<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta name="format-detection" content="telephone=no" />
    <%@ include file="../../../dynamic/common/common.jsp" %>
    <script src="${pageContext.request.contextPath}/content/www/js/init-rem.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dynamic/common/js/common.js"></script>

    <link rel="stylesheet" href="css/course.css" />
    <title>课程点播</title>
</head>

<body>
<div class="coursePlay">
    <!--导航栏-->
    <div class="navigation">
        <div class="news-title">
            <ul class="news-module clear">
                <li class="active" onclick="changeCourseTab('')">全部</li>
                <li onclick="changeCourseTab('01')">亲子关系</li>
                <li onclick="changeCourseTab('02')">儿童故事</li>
                <li onclick="changeCourseTab('03')">婚姻家庭</li>
                <li onclick="changeCourseTab('04')">职场压力</li>
            </ul>
            <div class="news-slider"></div>
        </div>
    </div>
    <!--图片轮播栏-->
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- 轮播（Carousel）指标 -->
        <ol class="carousel-indicators" style="padding-bottom: 0.02rem; padding-top: 0.02rem;">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <!-- 轮播（Carousel）项目 -->
        <div class="carousel-inner" id="sliceImgContainer">
            <div class="item active">
                <img src="img/slice.png" alt="First slide" style="height: 5rem; width: 100%; object-fit: cover;" />
            </div>
            <div class="item">
                <img src="img/slice.png" alt="Second slide" style="height: 5rem; width: 100%; object-fit: cover;">
            </div>
            <div class="item">
                <img src="img/slice.png" alt="Third slide" style="height: 5rem; width: 100%; object-fit: cover;">
            </div>
        </div>
    </div>
    <!--视频直播栏-->
    <div class="audioContent">
       <%-- <div style="width: 50%;float: left;">
        <div style="width: 94%;  margin: 0 auto">
            <div class="audio">
                <div class="audioContainer">
                    <img src="img/one.png" style="height: 100%;width: 100%; object-fit: cover;"/>
                </div>
                <span class="playTime"><img src="img/playTime@2x.png"/>15:40</span>
                <span class="audioType">视频</span>
            </div>
            <div class="remark">
                <div class="audioTitle">
                    <p>自闭症家庭培训课程</p>
                </div>
                <div class="audioPrice">
                    <div class="free">
                        <span>免费</span>
                    </div>
                    <div class="playsNum">
                        <span>336人学过</span>
                    </div>
                </div>
            </div>
        </div>
        </div>
        <div style="width: 50%;float: left;">
        <div style="width: 94%; margin:0 auto">
        <div class="audio">
            <div class="audioContainer">
                <img src="img/two.png" style="height: 100%;width: 100%; object-fit: cover;"/>
            </div>
            <span class="playTime"><img src="img/playTime@2x.png"/>15:40</span>
            <span class="audioType">视频</span>
        </div>
        <div class="remark">
            <div class="audioTitle">
                <p>如何做好一场年终总结</p>
            </div>
            <div class="audioPrice">
                <div class="pay">
                    <span>¥2.00</span>
                </div>
                <div class="playsNum">
                    <span>1024人学过</span>
                </div>
            </div>
        </div>
    </div>
        </div>
        <div style="width: 50%;float: left;">
        <div style="width: 94%; margin:0 auto">
            <div class="audio">
                <div class="audioContainer">
                    <img src="img/two.png" style="height: 100%;width: 100%; object-fit: cover;"/>
                </div>
                <span class="playTime"><img src="img/playTime@2x.png"/>15:40</span>
                <span class="audioType">视频</span>
            </div>
            <div class="remark">
                <div class="audioTitle">
                    <p>如何做好一场年终总结</p>
                </div>
                <div class="audioPrice">
                    <div class="pay">
                        <span>¥2.00</span>
                    </div>
                    <div class="playsNum">
                        <span>1024人学过</span>
                    </div>
                </div>
            </div>
        </div>
        </div>
        <div style="width: 50%;float: left;">
        <div style="width: 94%; margin: 0 auto">
            <div class="audio">
                <div class="audioContainer">
                    <img src="img/two.png" style="height: 100%;width: 100%; object-fit: cover;"/>
                </div>
                <span class="playTime"><img src="img/playTime@2x.png"/>15:40</span>
                <span class="audioType">视频</span>
            </div>
            <div class="remark">
                <div class="audioTitle">
                    <p>如何做好一场年终总结</p>
                </div>
                <div class="audioPrice">
                    <div class="pay">
                        <span>¥2.00</span>
                    </div>
                    <div class="playsNum">
                        <span>1024人学过</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>--%>

    <%--<div class="audioContent">
        <div class="col-xs-6 col-md-6 col-lg-6">
            <div class="audio">
                <div class="audioContainer">
                    <img src="img/three.png" style="height: 100%;width: 100%; object-fit: cover;"/>
                </div>
                <span class="playTime"><img src="img/playTime@2x.png"/>15:40</span>
                <span class="audioType">音频</span>
            </div>
            <div class="remark">
                <div class="audioTitle">
                    <p>孩子学习不好的原因</p>
                </div>
                <div class="audioPrice">
                    <div class="pay">
                        <span>¥2.00</span>
                    </div>
                    <div class="playsNum">
                        <span>956人学过</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-6 col-md-6 col-lg-6">
            <div class="audio">
                <div class="audioContainer">
                    <img src="img/four.png" style="height: 100%;width: 100%; object-fit: cover;"/>
                </div>
                <span class="playTime"><img src="img/playTime@2x.png"/>15:40</span>
                <span class="audioType">音频</span>
            </div>
            <div class="remark">
                <div class="audioTitle">
                    <p>工作效率低下的原因及拯救方法啊啊啊啊</p>
                </div>
                <div class="audioPrice">
                    <div class="audioPrice">
                        <div class="pay">
                            <span>¥1.00</span>
                        </div>
                        <div class="playsNum">
                            <span>2624人学过</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>--%>
</div>
<!--底部菜单栏-->
<div class="footer">
    <div class="col-xs-6 col-md-6 col-lg-6">
        <div>
            <img src="img/index.png" />
        </div>
        <div>
            <span class="index">首页</span>
        </div>
    </div>
    <div class="col-xs-6 col-md-6 col-lg-6">
        <div>
            <img src="img/mime.png" />
        </div>
        <div>
            <span class="mine">我的</span>
        </div>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/www/view/course/js/course.js"></script>
</body>
</html>
