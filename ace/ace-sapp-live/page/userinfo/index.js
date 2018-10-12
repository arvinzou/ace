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
    that.setData(params);
    
  },
  onPullDownRefresh: function () {
    let that = this;
  },
  goback: function (e) {
    var that = this;
    console.log('============>goback');
    wx.navigateTo({ url: that.data.url });
  },
  bindgetuserinfo: function (e) {
    console.log(e.detail.userInfo);
    var that = this;
    util.login(that.goback);
  }
});


