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
      if (typeof _success == "function") {
        _success(res.data);
      }
    },
    fail: function (res) {
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
module.exports = {
  formatTime: formatTime,
  formatLocation: formatLocation,
  request: request
}