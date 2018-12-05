<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>提现记录</title>
    <link rel="stylesheet" type="text/css" href="css/incomeRecord.css"/>
    <link rel="stylesheet" type="text/css" href="css/earnings.css"/>
    <script>
        !function () {
            function n() {
                var n = screen.width > 0 && (window.innerWidth >= screen.width || 0 == window.innerWidth) ? screen.width : window.innerWidth,
                    i = screen.height > 0 && (window.innerHeight >= screen.height || 0 == window.innerHeight) ? screen.height : window.innerHeight;
                return {w: n, h: i}
            }

            function i() {
                return 0 == window.orientation || 180 == window.orientation
            }

            function e() {
                t = n(), t.w <= 750 ? w.style["font-size"] = t.w / 10 + "px" : w.style["font-size"] = "48px"
            }

            var t = n(), o = "onorientationchange" in window ? "orientationchange" : "resize",
                w = document.documentElement;
            void 0 != window.orientation ? (i() ? e() : w.style["font-size"] = "37.5px", window.addEventListener(o, function () {
                setTimeout(function () {
                    var e = n();
                    i() ? w.style["font-size"] = e.w / 10 + "px" : w.style["font-size"] = "37.5px"
                }, 100)
            }, !1)) : e()
        }();
    </script>
    <script src="../common/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/iScroll.js"></script>
    <script type="text/javascript" src="js/Mdate.js"></script>
    <script type="text/javascript" src="js/incomeRecord.js"></script>
</head>
<body>
<div class="month">
    <input type="text" id="dateSelectorOne" placeholder="选择年月" value="2018-12-21" data-year="1991" data-month="8">
</div>
<div class="list">
    <div class="top">
        <span class="name">公益微课之如何让宝宝爱上学习 系列课程</span>
        <span class="money_style">+<span>200.00 </span></span>
    </div>
    <div class="info">
        <span class="time">2018-04-07  17:30:15 </span>
        <span class="stutas">我是一条鱼购买 </span>
    </div>
</div>

<div class="list">
    <div class="top">
        <span class="name">咨询收益—电话咨询 </span>
        <span class="money_style">+<span>200.00 </span></span>
    </div>
    <div class="info">
        <span class="time">2018-04-07  17:30:15 </span>
        <span class="stutas">半分购买 </span>
    </div>
</div>

<div class="list">
    <div class="top">
        <span class="name">肖海平老师—为什么孩子总不听 你的系列课程 </span>
        <span class="money_style">+<span>200.00 </span></span>
    </div>
    <div class="info">
        <span class="time">2018-04-07  17:30:15 </span>
        <span class="stutas">傻不拉几购买 </span>
    </div>
</div>

</body>
</html>