<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="utf-8"/>

    <title>直播</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="data-spm" content="zy-spot-web.v3">
    <link rel="stylesheet" href="//static.xinhuaapp.com/css/mobase.css"/>
    <link rel="stylesheet" href="//g.alicdn.com/de/prismplayer/1.9.9/skins/default/index.css"/>
    <link rel="stylesheet" href="/static/lib/photoswipe-4.1.1/photoswipe.css">
    <link rel="stylesheet" href="/static/lib/photoswipe-4.1.1/default-skin.css">
    <link rel="stylesheet" href="/xcy/font/iconfont.css"/>
    <link rel="stylesheet" href="/xcy/static/css/info.css?_t=1511940861000"/>
    <script src="/xcy/static/js/init-rem.js?_t=1511172278000"></script>
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
<script src="//res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="//g.alicdn.com/de/prismplayer/1.9.9/prism-min.js"></script>
<script src="/static/js/zepto-1.2.0.min.js"></script>
<script src="//static.xinhuaapp.com/js/juicer-min.js"></script>
<script src="/xcy/static/js/sugar-h5.js?_t=1510794885000"></script>
<script src="/xcy/static/js/roll.js?_t=1507519928000"></script>
<script src="/static/lib/photoswipe-4.1.1/photoswipe.js"></script>
<script src="/static/lib/photoswipe-4.1.1/photoswipe-ui-default.js"></script>
<script src="/xcy/static/js/common.js?_t=1511451507000"></script>
<script src="/xcy/static/js/login.js?_t=1511451507000"></script>
<script src="/xcy/static/js/photoswipe.js?_t=1511436259000"></script>
<script src="/xcy/static/js/soshm.js"></script>
<script src="/xcy/static/js/info.js?_t=1511967632000"></script>
<script src="//dot.xinhuazhiyun.com/logserver/spm.js"></script>
<script src="//s22.cnzz.com/z_stat.php?id=1261994174&web_id=1261994174"></script>
</script>
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