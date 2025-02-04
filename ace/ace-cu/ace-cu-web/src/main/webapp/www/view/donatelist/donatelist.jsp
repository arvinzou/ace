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
    <link rel="stylesheet" type="text/css" href="css/donatelist.css?v=<%=System.currentTimeMillis()%>"/>
</head>
<body>
<div class="list_box">
    <div class="banner">
        <img src="img/banner.png"/>
    </div>
    <div class="mine">
        <div class="mineItem" id="mine">

        </div>
    </div>
    <div class="list" id="donateList">

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
            {@if item.indexNum == 1}
            <img src="img/no1.png" style="width: 0.6rem;height: 0.8rem;padding-top: 0.1rem;"/>
            {@else if item.indexNum == 2}
            <img src="img/no2.png" style="width: 0.6rem;height: 0.8rem;padding-top: 0.1rem;"/>
            {@else if item.indexNum == 3}
            <img src="img/no3.png" style="width: 0.6rem;height: 0.8rem;padding-top: 0.1rem;"/>
            {@else}
            <span>\${item.indexNum}</span>
            {@/if}
        </div>
        <div class="headImg">
            <img src="\${item.headimgurl}"/>
        </div>
        <div class="detail">
            <p class="user">\${item.nickname}</p>
            <p class="user_detail">捐赠<span class="unit">\${item.donateDays}</span>天</p>
        </div>
        <div class="money"><span class="money_amount">\${item.totalDonateAmount}</span><span
                class="unit">元</span></div>
        <div style="clear: both;"></div>
    </div>
    {@/each}
</script>
<script type="text/javascript" src="/cu/www/common/js/jquery-3.2.1.min.js?v=<%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="/cu/www/common/js/init-rem.js?v=<%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="/cu/www/common/js/juicer/juicer-min.js?v=<%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="js/donatelist.js?v=<%=System.currentTimeMillis()%>"></script>
</body>
</html>
