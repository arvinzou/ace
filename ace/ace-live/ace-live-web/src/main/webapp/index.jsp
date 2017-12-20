<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
session.setAttribute("KEY0001","M009485553455");
%>

<!DOCTYPE HTML>
<html>
<head>

    <title>My WebSocket</title>
</head>
<script src="${pageContext.request.contextPath}/content/common/js/reconnecting-websocket.js"></script>
<style>


</style>
<body>

Welcome<br/>
<input id="text" type="text" /><button onclick="send()">Send</button>    <button onclick="closeWebSocket()">Close</button>
<div id="message">
</div>
</body>


</html>