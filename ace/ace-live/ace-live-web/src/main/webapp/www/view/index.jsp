<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>hello world</title>
</head>
<body>
<div style="text-align:center">
    <h2>${SESSION_USERINFO_KEY.nickname}</h2>
    <img src="${SESSION_USERINFO_KEY.headimgurl}" style="max-height:100px">
</div>
</body>
</html>