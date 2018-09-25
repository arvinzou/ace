var util = require("../../util/util.js");
var cfg = require("../../config.js");
var app = getApp()
Page({
  data: {
    items: [],
    startX: 0, //开始坐标
    startY: 0
  },
  onLoad: function () {
   
  },
  bindgetuserinfo: function (e) {
    console.log(e);
    var that = this;
    util.login(that.ok);
  },
  ok:function(){
    wx.showModal({ title: "提示", showCancel: false, content: "鉴权OK" });
  }
})