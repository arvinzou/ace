<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <title>直播</title>
    <jsp:include page="/dynamic/common/common-www.jsp"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/content/common/ckplayer/ckplayer.js"
            charset="utf-8"></script>
    <script>
        var rid='${param.rid}';

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
<div id="a1"></div>
<div class="tab_menu">
    <ul>
        <li><a class="selected" href="#">图文直播</a></li>
        <li><a class="unselected" href="#">互动聊天</a></li>
        <li><a class="unselected" href="#">活动介绍</a></li>
    </ul>
</div>
<div class="tab_box">
    <div id="livesub" class="tab_content">
        图文直播
    </div>
    <div class="hide tab_content" id="livemsg">
        互动聊天
    </div>
    <div class="hide tab_content" id="content">
        活动介绍
    </div>
</div>
<jsp:include page="/dynamic/common/footer-1-www.jsp"/>
<script src="${pageContext.request.contextPath}/content/www/live/live.js"></script>
<script src="${pageContext.request.contextPath}/content/www/live/play.js"></script>
<style>
        .tab_box{
           width: 100%;
           background-color: #f8f8f8;
        }
        .selected{
            color: red;
            font-size:18px;
            border-bottom: 2px solid red;
        }
        .unselected{
            color: #000000;
            font-size:16px;
        }
        .tab_menu ul li {
           display: inline-block;
         }

       .tab_menu ul li a{
            display: block;
            padding-bottom: 10px;
            padding-top: 10px;
            padding-left: 20px;
            padding-right:20px;
            text-decoration: none;
        }

        ul{
             list-style: none;
             margin: 0;
             padding: 0;
        }

        .hide{
            display: none;
        }
        .tab_menu{
            text-align:center;
        }
        .tab_content{
           padding:15px;
        }


</style>
<style>
</style>
</body>
</html>