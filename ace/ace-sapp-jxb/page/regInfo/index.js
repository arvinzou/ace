var util = require("../../util/util.js");
var cfg = require("../../config.js");
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
  navigator: function (url) {
    wx.switchTab({
      url: url
    });
  },
  onLoad: function (option) {
    this.setData({
      redirectTo: option.redirectTo
    })
    console.log(option);
  },
  openSuccess: function () {
    console.log("openSuccess");
    var that=this;
    this.navigator(this.data.redirectTo);
  },
  openFail: function () {
    var that = this;
    this.navigator('../reg/index?redirectTo=' + this.data.redirectTo);
  }
});