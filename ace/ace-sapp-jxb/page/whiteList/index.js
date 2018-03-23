var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
  onShareAppMessage: function (res) {
    if (res.from === 'button') {
      // 来自页面内转发按钮
      console.log(res.target)
    }
    return {
      title: '我发现了掌上统战小程序，一起看看吧',
      path: '/page/whiteList/index',
      success: function (res) {
        // 转发成功
      },
      fail: function (res) {
        // 转发失败
      }
    }
  },
  data: {
    loading: false,
    disabled: false
  },
  onReady: function (res) {
    wx.setNavigationBarColor({
      frontColor: cfg.frontColor,
      backgroundColor: cfg.backgroundColor,
      animation: {
        duration: 400,
        timingFunc: 'easeIn'
      }
    });
  },
  
  formSubmit: function (e) {
    console.log(e);
    var that = this;
    this.setData({
      loading: true,
      disabled: true
    })
    console.log('form发生了submit事件，携带数据为：', e.detail.value);
    var data = e.detail.value;
    data.formId = e.detail.formId;
    util.request(cfg.whiteListUrl, data,
      function (data) {
        that.setData({
          loading: false,
          disabled: false
        })
        if (data.status == '0') {
          wx.setStorage({
            key: "userInfo",
            data: data.value
          })
          wx.showModal({
            title: '提示',
            content: '申请成功',
            success: function (res) {
              that.navigator(that.data.redirectTo);
            }
          })
        } else {
          wx.showModal({
            title: '提示',
            content: data.errorMessage,
            success: function (res) {
              that.navigator("/page/whiteList/index?redirectTo=" + that.data.redirectTo);
            }
          })
        }
      }
    );
  },
  onLoad: function (options) {
    this.setData({
      redirectTo: options.redirectTo
    });
  },

  navigator: function (url) {
    console.log(url)
    wx.navigateTo({
      url: url
    });
  }
});