<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta name="format-detection" content="telephone=no" />
    <title>个人中心</title>
    <jsp:include page="../../../dynamic/common/base.jsp"/>
    <link href="css/index.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/loader.js"></script>
    <script type="text/javascript" src="js/index.js"></script>
</head>
<body>
<div class="index">
    <div class="main">
        <div class="box">
            <div class="home_top">
            </div>
        </div>
    </div>
    <div class="badding">
        <div class="phoneNum">
            <input type="text" name="mobile" id="mobile" placeholder="请输入手机号码" onfocus="this.placeholder=''" onblur="this.placeholder='请输入手机号码'"/>
            <button id="getNumBtn" onclick="getNumFun();">获取验证码</button>
        </div>
        <div class="code">
            <input type="text" name="codeNum" id="codeNum" placeholder="请输入验证码" onfocus="this.placeholder=''" onblur="this.placeholder='请输入验证码'"/>
        </div>
        <div class="option">
            <button id="band" name="band" onclick="bandFun();">立即绑定</button>
        </div>
    </div>
    <div class="footer">
        <div class="footer_logo"><img src="img/logo@3x.png"/></div>
    </div>
</div>
</body>
</html>