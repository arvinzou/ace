<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
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
				
                <div class="user_info">
					<div class="corpName">
                        
                    </div>
                    <div class="user_img">
                        <img id="headImg" src="img/header_img.png" />
                    </div>
                    <div class="user_name">
                        小武先森
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="badding" >
        <div class="badding_opt">
            <button id="bandBtn" onclick="location.href='../bind/index.jsp'">立即绑定</button>
        </div>
    </div>
    <div class="cancle" hidden="true" id="banded">
        <div class="badding_opt">
            <button id="cancelBand" onclick="cancelBand();">取消绑定</button>
        </div>
        <!--<div class="info">已绑定车辆</div>-->
    </div>
    <div class="footer">
        <div class="footer_logo"><img src="img/logo@3x.png"/></div>
    </div>
</div>
</body>
</html>
