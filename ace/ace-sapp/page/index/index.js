var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
  data: {
    serverfile: cfg.serverfile,
    list:[
      { id: "1", opentype:"navigate",name: "统战单位", icon: "tzdw-icon.png", page:"../dept/index"},
      { id: "2", opentype: "navigate", name: "统战人士", icon: "tzrs-icon.png", page: "../personage/index" },
      { id: "3", opentype: "navigate", name: "统战活动", icon: "tzhd-icon.png", page: "../activity/index" },
      { id: "4", opentype: "navigate", name: "统战文件", icon: "tzwj-icon.png", page: "../files/index" },
      { id: "5", opentype: "navigate", name: "统战GIS", icon: "tzjs-icon.png", page: "../service/index" },
      { id: "6", opentype: "navigate", name: "统战飞讯", icon: "tzfs-icon.png", page: "../notice/index" }
      
    ]
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
  next: function () {
    this.navigator('../dept/index');
  }
});