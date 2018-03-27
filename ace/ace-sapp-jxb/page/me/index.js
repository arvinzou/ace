var util = require("../../util/util.js");
var cfg = require("../../config.js");
var app = getApp()
Page({
  data:{
    items: [
      {
        icon: '../../image/my_order@2x.png',
        text: '我的直播'
      },
      {
        icon: '../../image/my_wallet@2x.png',
        text: '我的钱包',
        arrow: '../../image/0106arrow3x.png'
      },
      {
        icon: '../../image/my_collecte@2x.png',
        text: '我的收藏'
      },

      {
        icon: '../../image/my_about@2x.png',
        text: '关于我们'
      }
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
  onLoad: function () {
    var that=this;
    that.setData({
      userinfo: wx.getStorageSync('userinfo')
    });
  }
})