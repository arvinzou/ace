<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>提现记录</title>
    <link rel="stylesheet" type="text/css" href="css/earnings.css"/>
    <jsp:include page="../../../dynamic/common/base.jsp"/>
    <script type="text/javascript" src="../../common/js/loader.js"></script>
    <script type="text/javascript" src="js/earnings.js"></script>
</head>
<body id="list">

</body>

<script id="tmp-list" type="text/template">
    {@each data as item}
    <div class="list">
        <div class="top">
            <span class="name">收益提现</span>
            <span class="money_style">+<span>\${item.applyAmount} </span></span>
        </div>
        <div class="info">
            <span class="time">\${item.createDate} </span>
            {@if item.auditRst == 'pass'}
            <span class="stutas">提现成功</span>
            {@else if item.auditRst == 'temp'}
            <span class="stutas stutas1">待审核</span>
            {@else if item.auditRst == 'reject'}
            <span class="stutas stutas2">提现失败</span>
            {@/if}
        </div>
    </div>
    {@/each}
</script>

</html>
