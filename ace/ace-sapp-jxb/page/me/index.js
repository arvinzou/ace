var util = require("../../util/util.js");
var cfg = require("../../config.js");
var app = getApp()
Page({
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
  onLoad: function () {
    this.getUserInfo();
  },
  getUserInfo:function () {
    var that=this;
    wx.getUserInfo({
      success: function (res) {
        console.log(res);
        that.setData({
          hasUserInfo: true,
          userInfo: res.userInfo
        })
        that.update()
      }
    })
  }
})