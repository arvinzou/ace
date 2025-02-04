<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" /><meta httpwebapp-equiv="Expires" content="0" />
    <title>登录</title>
    <jsp:include page="../common/common.jsp"/>
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
</head>
<body>
<div class="index">
    <div class="logo">
        <div class="logo-img"><img src="img/login-logo.png"/></div>
        <div class="logo-title">常德市公安局警官培训中心</div>
    </div>
    <div class="login-form">
        <div class="form-row">
            <input class="login-input" name="username" type="number" placeholder="请输入账号"/>
        </div>
        <div class="form-row pwd">
            <input class="login-input" name="password" type="password" placeholder="请输入密码"/>
            <img src="img/fpwd.png" class="forget" onclick="resetPwd();">
        </div>
    </div>
    <div class="login-btn loginActive" onclick="login();">登录</div>
    <div class="regist-box"><a href="${pageContext.request.contextPath}/www/sign/index.jsp" class="regist"
                               style="display: none">注册报到</a>
    </div>
    <div class="footer-box">
        <form action="${pageContext.request.contextPath}/www/oauth2/auth" id="bindForm" method="post">
            <div class="footer" onclick="wxLogin();">
                <input type="hidden" value="WX_LOGIN" name="action"/>
                <input type="hidden" value="/policeschool/www/registered/person/index.jsp" id="respUri" name="respUri"/>
                <%--  <button type="submit">微信授权登录</button>--%>
                <img src="img/icon-weixin.png" class="weixin"/>
                <p>微信登录</p>
            </div>
        </form>
    </div>
</div>
</body>
</html>
