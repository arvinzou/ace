var util = require("../../util/util.js");
var cfg = require("../../config.js");
var app = getApp()
Page({
  data: {
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
    onShareAppMessage: function (res) {
      if (res.from === 'button') {
        // 来自页面内转发按钮
        console.log(res.target)
      }
      return {
        title: '我发现了掌上统战小程序，一起看看吧',
        path: '/page/service/index',
        success: function (res) {
        },
        fail: function (res) {
          // 转发失败
        }
      }
    }
})