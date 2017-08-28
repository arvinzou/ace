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
  onLoad: function () {
  },
   openSuccess: function () {
     console.log("openSuccess");
      this.navigator('../dept/index');
    },
    openFail: function () {
      this.navigator('../feedback/index');
    }
});