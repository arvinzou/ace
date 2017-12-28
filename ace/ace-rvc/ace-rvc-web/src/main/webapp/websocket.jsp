<%@ page language="java" pageEncoding="UTF-8" %>
<%
    //    session.setAttribute("KEY0001", "M009485553455");
    String name = request.getParameter("name");//用request得到
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>聊天室</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
</head>
<script src="${pageContext.request.contextPath}/content/common/js/reconnecting-websocket.js"></script>
<style>
    .messageDiv {
        width: 600px;
        height: 300px;
        line-height: 25px;
        white-space:nowrap;
        overflow: auto;
    }
</style>
<body> Welcome : <%=name%> <br/>

<div id="message" class="messageDiv">

</div>

<input id="text" type="text"/>
<button onclick="clearTxt()">清空</button>
<button onclick="send()">发送</button>
<%--<button onclick="closeWebSocket()">关闭连接</button>--%>

</body>

<script type="text/javascript">
    var name = getParameterStr("name");
//    var uri = "ws://127.0.0.1:6001/rvc/live/websocket/r001/" + name;//本地
    var uri = "ws://1931507me2.iok.la:28228/rvc/live/websocket/r001/" + name;//代理


    var websocket = null;
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new ReconnectingWebSocket(uri);
    }
    else {
        alert('Not support websocket');
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("error");
    };

    //连接成功建立的回调方法
    websocket.onopen = function (event) {
        setMessageInnerHTML("open");
    };

    //接收到消息的回调方法
    websocket.onmessage = function () {
        setMessageInnerHTML(event.data);
    };

    //连接关闭的回调方法
    websocket.onclose = function () {
        setMessageInnerHTML("close");
    };

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        websocket.close();
    };

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }

    //关闭连接
    function closeWebSocket() {
        websocket.close();
    }

    //发送消息
    function send() {
        var message = document.getElementById('text').value;
        websocket.send(message);
    }


    function getParameterStr(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURI(r[2]);
        return null;
    }

    function clearTxt() {
        document.getElementById('text').value = "";
    }
</script>
</html>