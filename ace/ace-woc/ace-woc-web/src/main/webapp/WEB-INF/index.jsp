<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>首页</title>
    <link rel="stylesheet" href="${portalPath}/content/common/tableDatas/css/datatables.css">

    <link rel="stylesheet" href="${portalPath}/content/common/bootstrap/css/bootstrap.css">
    <script type="text/javascript" src="${portalPath}/content/common/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${portalPath}/content/common/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="${portalPath}/content/common/tableDatas/js/datatables.js"></script>
    <style>
        .main_container {
            height: 100%;
        }

        .main_container > div {
            float: left;
        }

        .main_container_left {
            width: 70%;
            height: 100%;
        }

        .main_container_right {
            width: 30%;
            height: 100%;
        }
    </style>
</head>
<body>
<div class="container-fluid main_container">
    <div class="main_container_left">
        <table id="Ttable"></table>
    </div>
    <div class="main_container_right"></div>
</div>
</body>
<script src="${pageContext.request.contextPath}/content/service/index/index.js?version=${cfg.version}"></script>
</html>
