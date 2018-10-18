<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>慈善榜单</title>
    <link rel="stylesheet" type="text/css" href="/cu/www/common/js/dropload/dropload.css?v=<%=System.currentTimeMillis() %>"/>
    <link rel="stylesheet" type="text/css" href="css/rankings.css?v=<%=System.currentTimeMillis() %>"/>
</head>
<body>
<div class="rankings_box">
    <div class="ranking_list_title">
        <ul>
            <li style="width: 15%;"><span class="sborder">排名</span></li>
            <li style="width: 30%;"><span class="sborder">慈善人</span></li>
            <li style="width: 25%;"><span class="sborder">捐助次数</span></li>
            <li style="width: 30%;"><span>捐助总金额(元)</span></li>
            <div style="clear: both;"></div>
        </ul>
    </div>
    <div class="rank_list" id="list">

    </div>
</div>

<script id="list-tpl" type="text/template">
    {@each data as item, index}
    <ul>
        <li style="width: 10%;">\${parseInt(index)+1}</li>
        <li style="width: 30%;">\${item.nickname}</li>
        <li style="width: 30%;">\${item.donateCount}</li>
        <li style="width: 30%;color: #EA4436;">\${item.totalDonateAmount}</li>
        <div style="clear: both;"></div>
    </ul>
    {@/each}
</script>
<script type="text/javascript" src="/cu/www/common/js/jquery-3.2.1.min.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="/cu/www/common/js/init-rem.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="/cu/www/common/js/dropload/dropload.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="/cu/www/common/js/juicer/juicer-min.js?v=<%=System.currentTimeMillis() %>"></script>
<script type="text/javascript" src="js/ranking.js?v=<%=System.currentTimeMillis() %>"></script>
</body>
</html>
