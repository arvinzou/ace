<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>考勤记录</title>
    <jsp:include page="../../common/common.jsp"/>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <link rel="stylesheet" type="text/css" href="css/calendar.css"/>
</head>
<body>
<div class="index">
    <div class="box">
        <div class="calendar" id="calendar">

        </div>
    </div>
    <div class="attend">
        <div id="list">

        </div>
    </div>
</div>

<script id="list-tpl" type="text/template">
    <div class="item">
        <div class="item-left">
            <img src="img/circle.png" class="circle"/>
            <span>上午</span>
        </div>
        <div class="item-right">
            <div class="inner">
                {@each data.am as item_am, index01}
                <p class="row">\${item_am.dealTime}</p>
                {@/each}
            </div>
        </div>
    </div>
    <div class="item">
        <div class="item-left">
            <img src="img/circle.png" class="circle"/>
            <span>下午</span>
        </div>
        <div class="item-right">
            <div class="inner">
                {@each data.pm as item_pm, index01}
                <p class="row">\${item_pm.dealTime}</p>
                {@/each}
            </div>
        </div>
    </div>
    <div class="item">
        <div class="item-left">
            <img src="img/circle.png" class="circle"/>
            <span>晚上</span>
        </div>
        <div class="item-right">
            <div class="inner">
                {@each data.night as item_night, index01}
                <p class="row">\${item_night.dealTime}</p>
                {@/each}
            </div>
        </div>
    </div>

</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</body>
</html>
