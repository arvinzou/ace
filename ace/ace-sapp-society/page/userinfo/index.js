var util = require("../../util/util.js");
let openSocket = require('../../util/socket.js');
var cfg = require("../../config.js");
const app = getApp();

Page({
  data: {
    serverfile: cfg.serverfile
  },
  onReady: function (res) {
    var that = this;
  },
  onLoad: function (params) {
    var that = this;
    console.log(params);
    that.setData(params);
  },
  onPullDownRefresh: function () {
    let that = this;
  },
  goback: function (e) {
    var that = this;
    console.log('============>goback'+that.data.url);
    //wx.navigateTo({ url: that.data.url });
    if (that.data.type == 'navigateTo'){
        wx.navigateTo({ url: decodeURIComponent(that.data.url) });
    } else if (that.data.type == 'switchTab'){
        wx.switchTab({
            url: decodeURIComponent(that.data.url) 
        });
    }else{
        wx.navigateTo({ url: decodeURIComponent(that.data.url)});
    }
  },
  /**
   * 用户授权
   */
  bindgetuserinfo: function (e) {
    var that = this;
    var userInfo = e.detail.userInfo;
    console.log(e.detail.userInfo);
    //判断用户是否授权
    if (userInfo != null && userInfo != undefined){  //授权
      util.login(that.goback);
    } else {   //不授权
      console.log(333, '执行到这里，说明拒绝了授权')
      wx.showToast({
        title: "为了您更好的体验,请先同意授权",
        icon: 'none',
        duration: 2000
      });
    }
    
  },
});


