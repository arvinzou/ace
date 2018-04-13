var util = require("../../util/util.js");
let openSocket = require('../../util/socket.js');
var cfg = require("../../config.js");
const app = getApp();
Page({
    data: {
        serverfile: cfg.serverfile
    },
    onReady: function (res) {
        console.log('index.js.onReady');
        var that=this;
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
        var that = this; 
    },
    onPullDownRefresh:function(){
      let that = this;
      wx.stopPullDownRefresh();
    },
    add:function(){
      let that = this;
      wx.showActionSheet({
        itemList: ['创建直播', '创建课程'],
        success: function (res) {
          if (!res.cancel) {
            if (res.tapIndex == 0) {
              wx.navigateTo({
                url: '../liveAdd/index',
              });
            } else if (res.tapIndex == 1) {
              wx.navigateTo({
                url: '../courseAdd/index',
              });
            }
          }
        }
      })
    }
});
