
const app = getApp();
export const connect = function (cb,url) {  // 定义一个方法
  wx.connectSocket({ // 创建一个 WebSocket 连接
    url: url,
    fail(err) {
      if (err) {
        console.log('%cWebSocket连接失败', 'color:red', err)
        app.globalData.socketConnectFail = true // 定义一个全局变量，当链接失败时改变变量的值
      }
    }
  })

  wx.onSocketOpen(function (res) { // 监听WebSocket连接打开事件。
    console.log('WebSocket打开成功');
    app.globalData.socketConnectFail = false;
  })

  wx.onSocketMessage(function (res) { // 监听WebSocket接受到服务器的消息事件。
    console.log('WebSocket接收到消息:',res.data);
    var data = JSON.parse(res.data);
    cb(data); // 将接收到的消息进行回调，如果是ArryBuffer，需要进行转换
  })

  wx.onSocketError(function (res) { // 监听WebSocket错误。
    console.log('WebSocket连接打开失败');
    app.globalData.socketConnectFail = true ;
  })

  wx.onSocketClose(function (res) { // 监听WebSocket关闭。
    console.log('WebSocket关闭');
    app.globalData.socketConnectFail = true ;
  })
};
function ArryBuffer2Json(data) { // ArryBuffer转换成Json
  try {
    var Base64String = String.fromCharCode.apply(null, new Uint8Array(data));
    var Base64JsonStr = decodeURIComponent(base64_decode(Base64String));
    return JSON.parse(Base64JsonStr);
  } catch (err) {
    console.log(err);
    return false;
  }
}

function Buffer2Base64() { // 用于订阅的参数，视具体情况而定
  var packet = {};
  packet["cmd"] = "subscribe";
  packet["reqNo"] = "" + new Date().getTime();
  packet["params"] = { token: token, channelId: 'xcx', columnIds: "1" };
  return stringToBuffer(JSON.stringify(packet))
}

function stringToBuffer(string) {
  var string = base64_encode(encodeURIComponent(string)),
    charList = string.split(''),
    uintArray = [];
  for (var i = 0; i < charList.length; i++) {
    uintArray.push(charList[i].charCodeAt(0));
  }
  return new Uint8Array(uintArray).buffer;
}

function base64_encode(str) { // base64编码
  var c1, c2, c3;
  var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
  var i = 0, len = str.length, string = '';

  while (i < len) {
    c1 = str.charCodeAt(i++) & 0xff;
    if (i == len) {
      string += base64EncodeChars.charAt(c1 >> 2);
      string += base64EncodeChars.charAt((c1 & 0x3) << 4);
      string += "==";
      break;
    }
    c2 = str.charCodeAt(i++);
    if (i == len) {
      string += base64EncodeChars.charAt(c1 >> 2);
      string += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
      string += base64EncodeChars.charAt((c2 & 0xF) << 2);
      string += "=";
      break;
    }
    c3 = str.charCodeAt(i++);
    string += base64EncodeChars.charAt(c1 >> 2);
    string += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
    string += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >> 6));
    string += base64EncodeChars.charAt(c3 & 0x3F)
  }
  return string
}

function base64_decode(input) { // base64解码
  var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
  var output = "";
  var chr1, chr2, chr3;
  var enc1, enc2, enc3, enc4;
  var i = 0;
  input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
  while (i < input.length) {
    enc1 = base64EncodeChars.indexOf(input.charAt(i++));
    enc2 = base64EncodeChars.indexOf(input.charAt(i++));
    enc3 = base64EncodeChars.indexOf(input.charAt(i++));
    enc4 = base64EncodeChars.indexOf(input.charAt(i++));
    chr1 = (enc1 << 2) | (enc2 >> 4);
    chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
    chr3 = ((enc3 & 3) << 6) | enc4;
    output = output + String.fromCharCode(chr1);
    if (enc3 != 64) {
      output = output + String.fromCharCode(chr2);
    }
    if (enc4 != 64) {
      output = output + String.fromCharCode(chr3);
    }
  }
  return utf8_decode(output);
}

function utf8_decode(utftext) { // utf-8解码
  var string = '';
  let i = 0;
  let c = 0;
  let c1 = 0;
  let c2 = 0;
  while (i < utftext.length) {
    c = utftext.charCodeAt(i);
    if (c < 128) {
      string += String.fromCharCode(c);
      i++;
    } else if ((c > 191) && (c < 224)) {
      c1 = utftext.charCodeAt(i + 1);
      string += String.fromCharCode(((c & 31) << 6) | (c1 & 63));
      i += 2;
    } else {
      c1 = utftext.charCodeAt(i + 1);
      c2 = utftext.charCodeAt(i + 2);
      string += String.fromCharCode(((c & 15) << 12) | ((c1 & 63) << 6) | (c2 & 63));
      i += 3;
    }
  }
  return string;
}