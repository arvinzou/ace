<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta name="format-detection" content="telephone=no" />
    <%@ include file="../../../dynamic/common/common.jsp" %>
    <script src="${pageContext.request.contextPath}/content/www/js/init-rem.js"></script>
    <script type="text/javascript" src="plugin/rating/js/star-rating.js"></script>
    <link rel="stylesheet" href="plugin/rating/css/star-rating.css" />
    <link rel="stylesheet" href="css/playCourse.css" />
    <title>课程点播</title>
</head>

<body>
<div class="main">
    <div class="coursePlay">
        <!--视频播放区-->
        <div class="audio" id="videoContainer">
            <video id="video">
                <source src="img/test.mp4" type="video/mp4">
                <source src="img/test.ogg" type="video/ogg">
            </video>
        </div>
        <!--视频进度条控制-->
        <div id="videoControls">
            <div id="progressWrap">
                <div id="playProgress">
                </div>
                <div id="videoTime">
                    <!--播放进度-->
                    <span id="showProgress">00.00</span>
                    <!--视频总时长-->
                    <span id="showVedioTime">04.15</span>
                </div>
            </div>
            <div class="play" id="playBtn">
                <span class="glyphicon glyphicon-play"></span>
            </div>
        </div>
    </div>

    <div class="gap"></div>
    <!--导航栏-->

    <div class="navcontent">
        <div class="news-title">
            <ul class="news-module clear">
                <li class="active" attrType="1" data-toggle="tab">课程介绍</li>
                <li attrType="0" data-toggle="tab">学员评价</li>
            </ul>
            <div class="slideContainer">
                <div class="news-slider"></div>
            </div>
        </div>
    </div>

    <div id="myTabContent" class="tab-content">
        <!--课程介绍-->
        <div id="course">
            <div class="courseInduc">
                <div class="courseTitle">
                    <p>自闭症家庭培训课程</p>
                </div>
                <div class="coursePrice">
                    <p>¥2.00</p>
                </div>
                <div>
                    <div class="gap"></div>
                    <div class="courseInduc">
                        <div class=" courseInfo">
                            <p>课程介绍</p>
                        </div>
                        <div class="infos">
                            主要跟大家介绍以下4个方面的内容：<br /> 1、哪些儿童需要语言训练及三大类儿童的发音特点；
                            <br /> 2、掌握发音规律和训练规律对训练孩子有什么作用？
                            <br /> 3、儿童的发音规律是怎么样的？

                        </div>

                    </div>
                </div>
            </div>
        </div>
        <div  id="statement">
            <div class="myStatement" data-toggle="modal" data-target="#myModal">
                <p>我的评价</p>
                <span class="glyphicon glyphicon-star"></span>
                <span class="glyphicon glyphicon-star"></span>
                <span class="glyphicon glyphicon-star"></span>
                <span class="glyphicon glyphicon-star"></span>
                <span class="glyphicon glyphicon-star"></span>
            </div>
            <div class="gap"></div>
            <div class="stat">
                <div class="statInfoTitle"><span>评价详情</span><span>(682个评价)</span></div>
                <div class="statInfo">
                    <div>
                        <div class="userImg">
                            <img src="img/user.png" />
                        </div>
                        <div class="userInfo">
                            <span>半分</span><br />
                            <span class="commentTime">2018-04-03</span>
                        </div>
                        <div class="userLevel">
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                        </div>
                    </div>
                    <div style="clear: both;height: 0.2rem;"></div>
                    <div class="userComment">
                        <span>刚刚看完的这个视频，多少了解了自闭症的信息比如自闭症和疫苗没有关系，自闭症假按揭骄傲骄傲加进去啊uauaui</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--底部栏-->
<div class="ops">
    <button name="buy" class="buy">立即购买</button>
</div>

<!--评价框模态窗-->
<div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container star">
                    <form>
                        <input value="5" id="rating-input" type="text" title="" />
                        <span class="demo">推荐</span><br />
                        <textarea class="point" placeholder="鼓励下老师吧~" onfocus="this.placeholder=''" onblur="this.placeholder='鼓励下老师吧~'"></textarea><br />
                        <a href="#" class="commit">提&nbsp;交</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function() {
        $("#course").show();
        $("#statement").hide();

        $('.news-title li').click(function(){
            var attribute = $(this).attr("attrType");
            //属性值为1表示课程介绍，0为学员评价
            if(attribute == "1"){
                $("#course").show();
                $(".ops").show();
                $("#statement").hide();
            }else{
                $("#course").hide();
                $(".ops").hide();
                $("#statement").show();
            }
        });
    });

    jQuery(document).ready(function() {
        var $inp = $('#rating-input');
        $inp.rating({
            min: 0,
            max: 5,
            step: 1,
            size: 'xs',
            showClear: false
        });
    });

    var th_width = $(".news-module li").eq(0).width();
    var th_left = $(".news-module li").eq(0).offset().left;
    var slider_width = $(".news-slider").width();
    var slider_left = th_left + (th_width / 2) - slider_width / 2;
    $(".news-slider").css("left", slider_left);
    $(".news-module li").on("click", function() {
        var n = $(this).index();
        var th_width = $(this).width();
        var th_left = $(this).offset().left;
        var slider_width = $(".news-slider").width();
        var slider_left = th_left + (th_width / 2) - slider_width / 2;
        $(".news-slider").css("left", slider_left);
        $(this).addClass("active").siblings().removeClass("active");
    });

    //视频进度条控制
    /*(function(window, document){*/
    $(function(){
        // 获取要操作的元素
        var videoControls = document.getElementById("videoControls");
        var videoContainer = document.getElementById("videoContainer");
        //var controls = document.getElementById("video_controls");
        var playBtn = document.getElementById("playBtn");
        var progressWrap = document.getElementById("progressWrap");
        var playProgress = document.getElementById("playProgress");
        var progressFlag;

        // 创建我们的操作对象，我们的所有操作都在这个对象上。
        var videoPlayer = {
            init: function(){
                var that = this;
                video.removeAttribute("controls");
                bindEvent(video, "loadeddata", videoPlayer.initControls);
                videoPlayer.operateControls();
            },
            initControls: function(){
                videoPlayer.showHideControls();
            },
            showHideControls: function(){
                bindEvent(video, "mouseover", showControls);
                bindEvent(videoControls, "mouseover", showControls);
                bindEvent(video, "mouseout", hideControls);
                bindEvent(videoControls, "mouseout", hideControls);
            },
            operateControls: function(){
                bindEvent(playBtn, "click", play);
                bindEvent(video, "click", play);
                bindEvent(progressWrap, "mousedown", videoSeek);
            }
        }

        videoPlayer.init();

        // 原生的JavaScript事件绑定函数
        function bindEvent(ele, eventName, func){
            if(window.addEventListener){
                ele.addEventListener(eventName, func);
            }
            else{
                ele.attachEvent('on' + eventName, func);
            }
        }
        // 显示video的控制面板
        function showControls(){
            videoControls.style.opacity = 1;
        }
        // 隐藏video的控制面板
        function hideControls(){
            // 为了让控制面板一直出现，我把videoControls.style.opacity的值改为1
            videoControls.style.opacity = 1;
        }
        // 控制video的播放
        function play(){
            if ( video.paused || video.ended ){
                if ( video.ended ){
                    video.currentTime = video.duration;
                    playBtn.innerHTML = "<span class='glyphicon glyphicon-play'></span>";
                }
                video.play();
                playBtn.innerHTML = "<span class='glyphicon glyphicon-pause'></span>";
                progressFlag = setInterval(getProgress, 60);
            }
            else{
                video.pause();
                playBtn.innerHTML = "<span class='glyphicon glyphicon-play'></span>";
                clearInterval(progressFlag);
            }
        }
        // video的播放条
        function getProgress(){
            var percent = video.currentTime / video.duration;
            playProgress.style.width = percent * (progressWrap.offsetWidth) - 2 + "px";
            //showProgress.innerHTML = (percent * 100).toFixed(1) + "%";
            showProgress.innerHTML = video.currentTime;
        }
        // 鼠标在播放条上点击时进行捕获并进行处理
        function videoSeek(e){
            if(video.paused || video.ended){
                play();
                enhanceVideoSeek(e);
            }
            else{
                enhanceVideoSeek(e);
            }

        }
        function enhanceVideoSeek(e){
            clearInterval(progressFlag);
            var length = e.pageX - progressWrap.offsetLeft;
            var percent = length / progressWrap.offsetWidth;
            playProgress.style.width = percent * (progressWrap.offsetWidth) - 2 + "px";
            video.currentTime = percent * video.duration;
            progressFlag = setInterval(getProgress, 60);
        }

        /*}(this, document))*/
    });
</script>
</body>

</html>