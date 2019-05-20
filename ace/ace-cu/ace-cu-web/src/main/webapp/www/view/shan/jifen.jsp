<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <title>爱心贡献榜</title>
    <link rel="stylesheet" type="text/css" href="/cu/www/common/js/dropload/dropload.css?v=<%=System.currentTimeMillis()%>"/>
    <link rel="stylesheet" type="text/css" href="css/jifen.css?v=<%=System.currentTimeMillis()%>"/>
    <link rel="stylesheet" href="css/common.css">
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
    <div class="rankType">
        <ul class="typeList">
            <li class="active" data-type="totalRank">
                总积分
            </li>
            <li  data-type="heartRank">
                善心积分
            </li>
            <li data-type="actionRank">
                善行积分
            </li>
        </ul>
    </div>
    <div class="list_box">
        <div class="mine">
            <div class="mineItem" id="mine">

            </div>
        </div>
        <div class="list" id="donateList">

        </div>
    </div>

</div>
<script id="mine-tpl" type="text/template">
    <div class="topnum">
        \${data.indexNum}
    </div>
    <div class="headImg">
        <img src="\${data.headimgurl}"/>
    </div>
    <div class="detail">
        <p class="user">我</p>
        <p class="user_detail">捐赠<span class="unit">\${data.donateDays}</span>天</p>
    </div>
    <div class="money"><span class="money_amount">\${data.totalDonateAmount}</span><span
            class="unit">元</span></div>
    <div style="clear: both;"></div>
</script>

<script id="donate-tpl" type="text/template">
    {@each data as item, index}
    <div class="listItem">
        <div class="topnum">
            {@if index == 0}
            <img src="img/no1.png" style="width: 0.6rem;height: 0.8rem;padding-top: 0.1rem;"/>
            {@else if index == 1}
            <img src="img/no2.png" style="width: 0.6rem;height: 0.8rem;padding-top: 0.1rem;"/>
            {@else if index == 2}
            <img src="img/no3.png" style="width: 0.6rem;height: 0.8rem;padding-top: 0.1rem;"/>
            {@else}
            <span>\${+index+1}</span>
            {@/if}
        </div>
        <div class="headImg">
            <img src="\${item.avatarUrl?item.avatarUrl:'img/people.png'}"/>
        </div>
        <div class="detail">
            <p class="user">\${item.nickname?item.nickname:'匿名好心人士'}</p>
        </div>
        <div class="money"><span class="money_amount">\${item.totalPoints}</span></div>
        <div style="clear: both;"></div>
    </div>
    {@/each}
</script>
<%--<script src="/cu/www/common/js/dropload/dropload.min.js?v=<%=System.currentTimeMillis()%>"></script>--%>
<script type="text/javascript" src="/cu/www/common/js/jquery-3.2.1.min.js?v=<%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="/cu/www/common/js/juicer/juicer-min.js?v=<%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="js/jifen.js?v=<%=System.currentTimeMillis()%>"></script>
</body>
</html>
