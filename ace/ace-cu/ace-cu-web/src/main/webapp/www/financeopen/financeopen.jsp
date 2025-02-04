<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>财务公开</title>
    <link rel="stylesheet" type="text/css" href="/cu/www/common/css/nav.css?v=<%=System.currentTimeMillis()%>"/>
    <link rel="stylesheet" type="text/css" href="css/finance.css?v=<%=System.currentTimeMillis()%>"/>
</head>

<body>
<div class="financebox">
    <div class="finance_title_nav">
        <div class="navigation">
            <div class="news-title">
                <ul class="news-module finance_nav_ul clear">
                    <li class="active" onclick="hoverli('raise');">项目实施情况</li>
                    <li onclick="hoverli('pay');">会计报表资料</li>
                </ul>
                <div class="news-slider"></div>
            </div>
        </div>
    </div>
    <div class="finance_content">
        <div id="raise" class="dis">
            <img src="img/finance01.png" style="width: 100%;height: 100%;"/>
            <img src="img/finance02.jpg" style="width: 100%;height: 100%;"/>
        </div>
        <div id="pay" class="undis">
            <img src="img/finance03.jpg" style="width: 100%;height: 100%;"/>
            <img src="img/finance04.jpg" style="width: 100%;height: 100%;"/>
            <img src="img/finance05.jpg" style="width: 100%;height: 100%;"/>
        </div>
    </div>
</div>

<script type="text/javascript" src="/cu/www/common/js/jquery-3.2.1.min.js?v=<%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="/cu/www/common/js/init-rem.js?v=<%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="/cu/www/common/js/nav.js?v=<%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="js/finance.js?v=<%=System.currentTimeMillis()%>"></script>
</body>

</html>