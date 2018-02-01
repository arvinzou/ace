<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<% %>
<!DOCTYPE HTML>
<html>
<head>
    <title>聊天室</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
</head>
<script src="${pageContext.request.contextPath}/content/common/js/reconnecting-websocket.js"></script>
<link href="${pageContext.request.contextPath}/script/css/chatroom.css" rel="stylesheet" type="text/css"/>
<body>聊天室<br/>

<div id="message" class="room">

    <%--<image src="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2450994032,3525797548&fm=27&gp=0.jpg"/>--%>

    <div class='row'>
        <span class="portrait-div"><image class="portrait-image"
                                          src="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2450994032,3525797548&fm=27&gp=0.jpg"/></span>
        <div class="content-warper">
            <p class="content"><b class="name-b">邹羿:</b><br/>邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹邹
            </p>
        </div>
    </div>

</div>

<div id="inputDiv" class="operate-cls">
    <div id="toolDiv" class="tool-div">
    <button id="btnFont"
            style="text-align:center;width: 15px;height: 15px;border: silver;background-color: #22252A;color:#ffffff">
        A
    </button>
    <button id="btnEmoji"
            style="text-align:center;padding-left:14px;width: 17px;height: 17px;border: silver;border: silver;background-color: #22252A;color:#ffffff">
        E
    </button>
    <button id="btnImage"
            style="text-align:center;padding-left:14px;width: 17px;height: 17px;border: silver;border: silver;background-color: #22252A;color:#ffffff">
        I
    </button>
</div>
    <div id="inputContentDiv" class="content-div">
        <textarea id="text" class="textarea-cls">
        </textarea>
        <button id="btnSend" class="send-cls" onclick="send()">发送</button>
    </div>
    <div id="inputActionDiv" class="action-div">
        <%--<button id="btnClear" class="clear-cls" onclick="clearTxt()">清空</button>--%>
        <%--<button id="btnSend" class="send-cls" onclick="send()">发送</button>--%>
        <button onclick="closeWebSocket()" style="visibility: hidden">关闭连接</button>
    </div>
</div>

<script src="${pageContext.request.contextPath}/script/js/chatroom.js"></script>
</body>
</html>