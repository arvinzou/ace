var uid = getParameterStr("uid");
var rid = getParameterStr("rid");
//    var uri = "ws://127.0.0.1:6001/rvc/live/websocket/r001/" + name;//本地
// var domain = "localhost:6001";
var domain = "1931507me2.iok.la:28228";
var uri = "ws://" + domain + "/rvc/live/websocket/" + rid + "/" + uid;//代理


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
    console.log("websocket error");
    // setMessageInnerHTML("error");
};

//连接成功建立的回调方法
websocket.onopen = function (event) {
    console.log("websocket connected");
    // setMessageInnerHTML("open");
};

//接收到消息的回调方法
websocket.onmessage = function () {
    var data = JSON.parse(event.data);
    console.log(data);
    var action = data.action;//用于逻辑判断
    var content = data.content;//输出文本

    setMessageInnerHTML(content);
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
    var action = "text";
    var jsonData = {
        action: action,
        content: message
    }

    websocket.send(JSON.stringify(jsonData));
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