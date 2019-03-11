<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>重置密码</title>
    <jsp:include page="../common/common.jsp"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
    <script type="text/javascript" src="js/index.js"></script>
</head>
<body>
<div class="index">
    <div class="head">
        <img src="img/icon_head.png"/>
        <p>重置密码</p>
    </div>
    <div class="row">
        <input class="nomal-input" name="mobile" type="number" placeholder="请输入手机号"/>
    </div>
    <div class="row codebox">
        <div class="input-text-box"><input name="code" type="number" class="input-text" placeholder="请输入验证码"/></div>
        <div class="code-text-box">
            <button id="getCode" class="code-button" onclick="getCode(this);">获取验证码</button>
        </div>
    </div>
    <div class="row">
        <input class="nomal-input" name="password" type="password" minlength="4" placeholder="请输入新密码(4位及以上)"/>
    </div>
</div>
<div class="footer">
    <img src="img/save-active.png" onclick="save();"/>
</div>
</body>
</html>
