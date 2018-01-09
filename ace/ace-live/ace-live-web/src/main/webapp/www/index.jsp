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
    <a href="${pageContext.request.contextPath}/www/view/live.jsp?rid={{=it.id}}">
        <div class="live-list" style="background-image: url({{=fastdfs_server+it.imageSrc}})">

            <div class="live-nop">
                <img class="live-nop-icon" src="${pageContext.request.contextPath}/content/www/image/nop.png"/>
                {{=it.nop}}
            </div>
            <div class="live-title">{{=it.name}}</div>
        </div>
    </a>
    <div class="list-after"></div>
</script>
<body ontouchstart>
<div class="weui-pull-to-refresh__layer">
    <div class='weui-pull-to-refresh__arrow'></div>
    <div class='weui-pull-to-refresh__preloader'></div>
    <div class="down">下拉刷新</div>
    <div class="up">释放刷新</div>
    <div class="refresh">正在刷新</div>
</div>

<div class="page__bd" style="background-color:#fff">

</div>
<jsp:include page="/dynamic/common/footer-1-www.jsp"/>

<script src="${pageContext.request.contextPath}/content/www/live/index.js"></script>
<style>
.image-list{
    width:100%;
}
.live-title{
    float:left;
    padding-top:200px;
    padding-left:20px;
    font-size:20px;
    color:#fff;

}
.live-nop{
    font-size:20px;
    color:#fff;
    float:right;
    padding:20px;
}
.live-nop-icon{
    max-height:20px;
}
.live-list:after{
  content: " ";
}
.live-list{
    background-size: cover;
    height:250px;
}
.list-after{
    height:10px;
}
</style>
</body>
</html>