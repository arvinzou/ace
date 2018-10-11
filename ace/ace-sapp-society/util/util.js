var cfg = require("../config.js");
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
    console.log('request data-->', data);
    var _url = url,
        _data = data,
        _success = success,
        _fail = fail,
        _complete = complete;
    wx.showNavigationBarLoading();
    wx.showLoading({ title: "请求中" });
    wx.request({
        url: url,
        data: data,
        method: "GET",
        dataType: "json",
        header: {
            'WX-SESSION-ID': wx.getStorageSync('WX-SESSION-ID') //每次请求带上登录标志
        },
        success: function (res) {
            console.log(res);
            wx.hideNavigationBarLoading() //完成停止加载
            wx.hideLoading();
            if (typeof _success == "function") {
                _success(res.data);
            }
        },
        fail: function (res) {
            wx.hideNavigationBarLoading() //完成停止加载
            wx.hideLoading();
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
function uuid() {
  var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
  var uuid = [], i;
  var radix =chars.length;
  var len=28;
  if (len) {
    for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random() * radix];
  } else {
    var r;
    uuid[8] = uuid[13] = uuid[18] = uuid[23] = '';
    uuid[14] = '4';
    for (i = 0; i < 36; i++) {
      if (!uuid[i]) {
        r = 0 | Math.random() * 16;
        uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
      }
    }
  }
  return uuid.join('');
}
function formatNumber(n) {
  n = n.toString()
  return n[1] ? n : '0' + n
} 
function formatTime(date, format) {
  var formateArr = ['Y', 'M', 'D', 'h', 'm', 's'];
  var returnArr = [];
  //var date = new Date(number * 1000);
  returnArr.push(date.getFullYear());
  returnArr.push(formatNumber(date.getMonth() + 1));
  returnArr.push(formatNumber(date.getDate()));

  returnArr.push(formatNumber(date.getHours()));
  returnArr.push(formatNumber(date.getMinutes()));
  returnArr.push(formatNumber(date.getSeconds()));

  for (var i in returnArr) {
    format = format.replace(formateArr[i], returnArr[i]);
  }
  return format;
}
function formatDate(date) {
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    return [year, month, day].map(formatNumber).join('-');
}
function security(role){
  var userinfo = wx.getStorageSync('userinfo');
  console.log(userinfo);
  if (!userinfo){
      wx.showToast({
        title: '身份未识别',
        icon: 'none',
        duration: 5000
      })
      return false;
  }
  if (!userinfo.role) {
    wx.showToast({
      title: '系统未授权',
      icon: 'none',
      duration: 5000
    })
    return false;
  }
  if (role) {
    if (userinfo.role!=role) {
      wx.showToast({
        title: '系统未授权',
        icon: 'none',
        duration: 5000
      })
      return false;
    }
  }
  return true;
}

function isLogin() {
    console.log("==============isLogin===============");
    var userinfo = wx.getStorageSync('userinfo');
    console.log(userinfo);
    if (!userinfo.userProp) {
        return false;
    }
    return true;
}

function login(callback) {
  var that = this;
  var _callback = callback;
  wx.getLocation({
    type: 'gcj02',
    success: function (rst) {
      wx.login({
        success: function (o) {
          wx.getUserInfo({
            success: function (res) {
              wx.request({
                url: cfg.loginUrl,
                data: {
                  appid: cfg.appid,
                  appsecret: cfg.appsecret,
                  code: o.code,
                  encryptedData: res.encryptedData,
                  iv: res.iv,
                  latitude: rst.latitude,
                  longitude: rst.longitude
                },
                success: function (res) {
                  wx.setStorageSync('WX-SESSION-ID', res.data.value['3rd_session']);
                  wx.setStorageSync('userinfo', res.data.value['userinfo']);
                  console.log('login success', res);
                  if (_callback){
                    console.log('login callback');
                    _callback(res);
                  }
                },
                fail: function ({ errMsg }) {
                  console.log('request fail', errMsg);
                  wx.showModal({ title: "提示", showCancel: false, content: "鉴权失败" });
                }
              })
            }
          })
        }
      })
    }
  });

}
/** 
  * 
  * json转字符串 
  */
function stringToJson(data) {
  return JSON.parse(data);
}
/** 
*字符串转json 
*/
function jsonToString(data) {
  return JSON.stringify(data);
}
/** 
*map转换为json 
*/
function mapToJson(map) {
  return JSON.stringify(strMapToObj(map));
}
/** 
*json转换为map 
*/
function jsonToMap(jsonStr) {
  return objToStrMap(JSON.parse(jsonStr));
}


/** 
*map转化为对象（map所有键都是字符串，可以将其转换为对象） 
*/
function strMapToObj(strMap) {
  let obj = Object.create(null);
  for (let [k, v] of strMap) {
    obj[k] = v;
  }
  return obj;
}

/** 
*对象转换为Map 
*/
function objToStrMap(obj) {
  let strMap = new Map();
  for (let k of Object.keys(obj)) {
    strMap.set(k, obj[k]);
  }
  return strMap;
}
function extend(o1,o2){
  let map = objToStrMap(o1);
  for (let k of Object.keys(o2)) {
    map.set(k, o2[k]);
  }
  return strMapToObj(map);
}
function initRadioGroupData(list,value){
  for (var i = 0, len = list.length; i < len; ++i) {
    if (list[i].value == value) {
      list[i].checked = true;
    } else {
      list[i].checked = false;
    }
  }
  return list;
}
function indexOf(arr, item) {
  console.log("========indexOf=========");
  console.log(arr);
  console.log(item);
  return arr.indexOf(item);
}
module.exports = {
    formatTime: formatTime,
    formatLocation: formatLocation,
    request: request,
    uuid: uuid,
    formatDate:formatDate,
    login:login,
    isLogin: isLogin,
    security: security,
    stringToJson: stringToJson,
    jsonToString: jsonToString,
    mapToJson: mapToJson,
    jsonToMap: jsonToMap,
    strMapToObj: strMapToObj,
    objToStrMap: objToStrMap,
    extend: extend,
    initRadioGroupData: initRadioGroupData,
    indexOf: indexOf
}
