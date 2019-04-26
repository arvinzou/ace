<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="description" content="overview &amp; stats"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/css/index.css">
    <title>${cfg.sys_name}</title>
</head>

<body>
<div class="logo">
    <img src="content/common/img/logo.png" alt="logo" />
</div>
<div class="title">
    江汉区云控平台
</div>
<div class="entrances">
    <div class="entrance">
        <a href="${pageContext.request.contextPath}/main.jsp">
        <img src="content/common/img/inputBg.png" alt="" />
        运营平台</a>
    </div>
    <div class="arrow">

    </div>
    <div class="entrance mark">
        <img src="content/common/img/inputBg.png" alt="" /> 控制平台
        <div class="weakElectricity electricity">
            <a href="${pageContext.request.contextPath}/dynamic/service/control/model1/index.jsp"> 弱电</a>
        </div>
        <div class="forceElectricity electricity">
            <a href="${pageContext.request.contextPath}/dynamic/service/control/model3/index.jsp">强电</a>
        </div>
    </div>
    <div class="arrow">
    </div>
    <div class="entrance">
        <a href="${pageContext.request.contextPath}/dynamic/service/monitoringPlatform/index.jsp">
        <img src="content/common/img/inputBg.png" alt="" />
        监控平台</a>
    </div>
</div>
</body>
<style>
    a{
        display: inline-block;
        width: 100%;
        height: 100%;
        color: rgba(0, 243, 247, 1);
        z-index: 9999;
    }
</style>
</html>