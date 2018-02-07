var uid = getParameterStr("uid");
var rid = getParameterStr("rid");
//    var uri = "ws://127.0.0.1:6001/rvc/live/websocket/r001/" + name;//本地
var domain = "1931507me2.iok.la:28228";//"1931507me2.iok.la:28228";
//var domain='192.168.1.104:6001'
var uri = "ws://" + domain + "/rvc/live/websocket/" + 'D9F7E76732A24488BCCD652B7EEBFD09' + "/" + 'E341A7084C56499AB0390BCAA3AA5BCD';//代理
$(function(){
	$('#btnSend').click(send);
	sendMessageKey();
});

function sendMessageKey(){
	$('#inputContentDiv').keypress(function(e){
		if(e.keyCode==13){
			console.log(111111);
			send();
		}
	});
}

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
websocket.onmessage = function (event) {
    var data = JSON.parse(event.data);
    console.log(data);
	var container='';
    if(data.self){
		container=selfBubble;
	}else{
		container=bubble;
	}
	showMessageAction(container,data);
};

/*显示信息*/
function showMessageAction(container,data){
	var content='';
	if('image'==data.action){
		content='<img src='+data.content+'></img>';
	}else if('text'==data.action){
		content=data.content;
		if(content.indexOf('[#')==-1){
			content=addEmojiAction(content);
		}
	}
	
	var msg=container.replace('[name]',data.speakerName).replace('[content]',content);
	$('#chatTextWindow').append($(msg));
	$('#chatTextWindow').scrollTop( $('#chatTextWindow')[0].scrollHeight );
}

/*[##]文件替换*/

function addEmojiAction(content){
	var emojishow='<img style="width=16px; height=16px;" src="images/emoji/[i].png"/>';
	while(content.indexOf('[#')==-1){
        var frist=content.indexOf("[#");
        var last=content.indexOf("#]");
        var cc=content.substring(frist,last+2);
        var num=content.substring(frist+2,last);
		emojishow=emojishow.replace('[i]',num);
		content=content.replace(cc,emojishow);
	}
	return content;
}


//连接关闭的回调方法
websocket.onclose = function () {
    //setMessageInnerHTML("close");
};

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    websocket.close();
};
 var bubble='<div class="talkMessage"> '+
	 			'<span class="portrait-div">'+
                        '<image class="portrait-image" src="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2450994032,3525797548&fm=27&gp=0.jpg"/>'+
                '</span>'+
                '<div class="content-warper">'+
                       '<p class="content">'+
 							'<b class="name-b">[name]:</b><br/>'+
                       '[content]</p>'+
                '</div>'+
            '</div>';

var selfBubble='<div class="selfTalkMessage"> '+
					'<div class="selfContent-warper">'+
						   '<p class="selfContent">'+
								'<b class="name-b">我:</b><br/>'+
						   '[content]</p>'+
					'</div>'+
					'<span class="portrait-div">'+
							'<image class="portrait-image" src="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2450994032,3525797548&fm=27&gp=0.jpg"/>'+
					'</span>'+
               '</div>';

//关闭连接
function closeWebSocket() {
    websocket.close();
}

//发送消息
function send() {
	console.log(12);
    var message = $('#chatInputText').val();
    var action = "text";
    var jsonData = {
        action: action,
        content: message
    };
    websocket.send(JSON.stringify(jsonData));
	$('#chatInputText').val('');
}


function getParameterStr(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	console.log(reg);
    var r = window.location.search.substr(1).match(reg);
	console.log(window.location);
	console.log(window.location.search);
	console.log(window.location.search.substr(1));
    if (r != null) 
	{return decodeURI(r[2]);}
    return null;
}

function clearTxt() {
   $('.chatInputText').val('');
}