<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <title>直播</title>
    <jsp:include page="/dynamic/common/common-www.jsp"/>
</head>
<script id="pagetmpl" type="text/x-dot-template">
    <h1>{{=it.name}}</h1>
</script>
<body ontouchstart>
<div class="weui-pull-to-refresh__layer">
    <div class='weui-pull-to-refresh__arrow'></div>
    <div class='weui-pull-to-refresh__preloader'></div>
    <div class="down">下拉刷新</div>
    <div class="up">释放刷新</div>
    <div class="refresh">正在刷新</div>
</div>

<div class="page__bd">

</div>
<jsp:include page="/dynamic/common/footer-1-www.jsp"/>

<script src="${pageContext.request.contextPath}/content/www/live/controller.js"></script>
</body>
</html>