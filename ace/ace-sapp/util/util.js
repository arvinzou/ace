function formatTime(time) {
  if (typeof time !== 'number' || time < 0) {
    return time
  }

  var hour = parseInt(time / 3600)
  time = time % 3600
  var minute = parseInt(time / 60)
  time = time % 60
  var second = time

  return ([hour, minute, second]).map(function (n) {
    n = n.toString()
    return n[1] ? n : '0' + n
  }).join(':')
}

function formatLocation(longitude, latitude) {
  if (typeof longitude === 'string' && typeof latitude === 'string') {
    longitude = parseFloat(longitude)
    latitude = parseFloat(latitude)
  }

  longitude = longitude.toFixed(2)
  latitude = latitude.toFixed(2)

  return {
    longitude: longitude.toString().split('.'),
    latitude: latitude.toString().split('.')
  }
}





function request(url, data, success, fail, complete) {
  console.log('request url-->', url);
  console.log('request data-->',data);
  var _url = url,
    _data = data,
    _success = success,
    _fail = fail,
    _complete = complete;
  wx.showNavigationBarLoading();
  wx.request({
    url: url,
    data: data,
    method: "GET",
    dataType: "json",
    header: {
      'WX-SESSION-ID': wx.getStorageSync('WX-SESSION-ID') //每次请求带上登录标志
    },
    success: function (res) {
      console.log( res);
      wx.hideNavigationBarLoading() //完成停止加载
      if (typeof _success == "function") {
        _success(res.data);
      }
    },
    fail: function (res) {
      wx.hideNavigationBarLoading() //完成停止加载
      if (typeof _fail == "function") {
        _fail(res);
      }
    },
    complete: function (res) {
      if (typeof _complete == "function") {
        _complete(res);
      }
    }
  });
}
//util.js 
function imageUtil(e,padding) {
  var imageSize = {};
  var originalWidth = e.detail.width;//图片原始宽 
  var originalHeight = e.detail.height;//图片原始高 
  var originalScale = originalHeight / originalWidth;//图片高宽比 
  console.log('originalWidth: ' + originalWidth)
  console.log('originalHeight: ' + originalHeight)
  //获取屏幕宽高 
  wx.getSystemInfo({
    success: function (res) {
      var windowWidth = res.windowWidth - padding;
      var windowHeight = res.windowHeight - padding;
      var windowscale = windowHeight / windowWidth;//屏幕高宽比 
      console.log('windowWidth: ' + windowWidth)
      console.log('windowHeight: ' + windowHeight)
      if (originalScale < windowscale) {//图片高宽比小于屏幕高宽比 
        //图片缩放后的宽为屏幕宽 
        imageSize.imageWidth = windowWidth;
        imageSize.imageHeight = (windowWidth * originalHeight) / originalWidth;
      } else {//图片高宽比大于屏幕高宽比 
        //图片缩放后的高为屏幕高 
        imageSize.imageHeight = windowHeight;
        imageSize.imageWidth = (windowHeight * originalWidth) / originalHeight;
      }

    }
  })
  console.log('缩放后的宽: ' + imageSize.imageWidth)
  console.log('缩放后的高: ' + imageSize.imageHeight)
  return imageSize;
} 
function uuid() {
  var s = [];
  var hexDigits = "0123456789abcdef";
  for (var i = 0; i < 36; i++) {
    s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
  }
  s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
  s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
  s[8] = s[13] = s[18] = s[23] = "-";

  var uuid = s.join("");
  return uuid;
}
module.exports = {
  formatTime: formatTime,
  formatLocation: formatLocation,
  request: request,
  imageUtil: imageUtil,
  uuid: uuid 
}