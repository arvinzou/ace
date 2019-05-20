<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/mine.css"/>
    <script src="js/flexible.js" type="text/javascript" charset="utf-8"></script>
    <title></title>
</head>

<body>
<div class="content">
    <div class="head">
        <div class="headImg">
            <img src="img/people.png" alt=""/>
        </div>
        <p class="name">半分</p>
        <div class="type">
            <div onclick="showWhich(1)" class="tab1 tabF active">
                <img src="img/icon-jf.png" alt=""/><span id="point"></span>
                <p>积分</p>
            </div>
            <div onclick="showWhich(2)" class="tab2 tabF">
                <img src="img/icon-ry.png" alt=""/> <span id="xunzhang"></span>
                <p>荣誉勋章</p>
            </div>
        </div>
    </div>
    <div class="body">
        <div class="tab1 tabF record">
            <p class="start"><img src="img/icon-jf.png"/>共获得积分记录</p>
            <ul class="listStyle" id="sss">
            </ul>
        </div>
        <div class="tab2 tabF medalList">
            <ul class="medal" id="aaa">

            </ul>
        </div>
    </div>
</div>

<script id="tpl-sss" type="text/template">
    {@each data as item, index}
    <li>
        <div class="info">
            <p class="time">\${item.date}</p>
            为“日行一善”项目捐赠\${item.amount}元，获得善心\${item.heartPoint}积分,善
            行\${item.actionPoint}积分
        </div>
    </li>
    {@/each}
</script>

<script id="tpl-aaa" type="text/template">
    <li class="\${+data.point >0?'':'gray'}">
        <img src="img/zhu.png" alt=""/>
        <div>助人为乐</div>
    </li>
    <li class="\${+data.point>9?'':'gray'}">
        <img src="img/chi.png" alt=""/>
        <div>持之以恒</div>
    </li>
    <li class="\${+data.point>50?'':'gray'}">
        <img src="img/ai.png" alt=""/>
        <div>爱的奉献</div>
    </li>
    <li class="\${+data.point>100?'':'gray'}">
        <img src="img/zhi.png" alt=""/>
        <div>挚爱之心</div>
    </li>
    <li class="\${+data.point>500?'':'gray'}">
        <img src="img/man.png" alt=""/>
        <div>爱满人间</div>
    </li>
    <li class="\${+data.point>1000?'':'gray'}">
        <img src="img/ren.png" alt=""/>
        <div>仁爱无敌</div>
    </li>
</script>

<script type="text/javascript" src="/cu/www/common/js/jquery-3.2.1.min.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="/cu/www/common/js/juicer/juicer-min.js?v=<%=System.currentTimeMillis() %>"></script>
<script src="js/mine.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>