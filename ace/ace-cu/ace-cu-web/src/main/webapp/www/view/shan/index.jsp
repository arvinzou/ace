<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <title>日行一善</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/barrager.css"/>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <script>

        window.alert = function(name){
            var iframe = document.createElement("IFRAME");
            iframe.style.display="none";
            iframe.setAttribute("src", 'data:text/plain,');
            document.documentElement.appendChild(iframe);
            window.frames[0].window.alert(name);
            iframe.parentNode.removeChild(iframe);
        };
        window.confirm = function (message) {
            var iframe = document.createElement("IFRAME");
            iframe.style.display = "none";
            iframe.setAttribute("src", 'data:text/plain,');
            document.documentElement.appendChild(iframe);
            var alertFrame = window.frames[0];
            var result = alertFrame.window.confirm(message);
            iframe.parentNode.removeChild(iframe);
            return result;
        };

    </script>
    <script src="js/flexible.js" type="text/javascript" charset="utf-8"></script>
</head>

<body>
<div class="content">
    <div class="menu">
        <ul class="menuBtn">
            <li class="href1" data-type="1">
                <img src="img/icon1.png" alt=""/>
                <p>积分: <span id="point">0</span></p>
            </li>
            <li class="href1" data-type="2">
                <img src="img/icon2.png" alt=""/>
                <p>爱的奉献</p>
            </li>
            <li class="href2">
                <img src="img/icon3.png" alt=""/>
                <p>积分排行</p>
            </li>
            <li>
                <img src="img/icon4.png" alt=""/>
                <p>年度表彰</p>
            </li>
        </ul>
    </div>
    <div class="message">
        <div id="message">
            <a href="javascript:donate();" class="btn">点击捐款</a>
        </div>
    </div>
    <div class="todayIntegral" id="sss">

    </div>
</div>

<script id="tpl-sss" type="text/template">
    <div class="shanxing progress">
        <div class="title help" data-message="善行积分：每天一次性捐款金额超过1元即可获得1积分，每日获得积分上限为1积分">善行积分
            <span class="icon">?</span></div>
        <div>
            <div class="status \${data.todayActionPoint==0?'active':''}"></div>
            <div class="text">
                <span>累计\${data.totalActionPoint}积分</span><span>今日<span class="style1">\${data.todayActionPoint}</span></span>
            </div>
        </div>
    </div>
    <div class="shanxin progress">
        <div class="title help" data-message="善心积分：捐款总金额每增加10元，即可获得1积分。">
            善心积分<span class="icon">?</span></div>
        <div>
            <div class="status \${data.todayHeartPoint==0?'active':''}"></div>
            <div class="text">
                <span>累计\${data.totalHeartPoint}积分</span> <span>今日<span class="style1">\${data.todayHeartPoint}</span></span>
            </div>
        </div>
    </div>
    <div class="pool progress">
        <div class="title">捐款池</div>
        <div>
            <div class="status status1"></div>
            <div class="text text1">
                <span>累计\${data.totalAmount}元</span><span class="style1">今日<span>\${data.dayAmount}</span></span>
            </div>
        </div>
    </div>
</script>
<script type="text/javascript" src="/cu/www/common/js/jquery-3.2.1.min.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="/cu/www/common/js/juicer/juicer-min.js?v=<%=System.currentTimeMillis() %>"></script>
<script src="js/jquery.barrager.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
</body>

</html>