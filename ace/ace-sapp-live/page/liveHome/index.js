var util = require("../../util/util.js");
let openSocket = require('../../util/socket.js');
var cfg = require("../../config.js");
Date.prototype.Format = function (fmt) { //author: meizz  
  var o = {
    "M+": this.getMonth() + 1,         //月份  
    "d+": this.getDate(),          //日  
    "h+": this.getHours(),          //小时  
    "m+": this.getMinutes(),         //分  
    "s+": this.getSeconds(),         //秒  
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度  
    "S": this.getMilliseconds()       //毫秒  
  };
  if (/(y+)/.test(fmt))
    fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt))
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
  return fmt;
}
const app = getApp();
var page = 1;
Page({
  data: {
    serverfile: cfg.serverfile,
    items: [],
    listLive: [],
    startX: 0, //开始坐标
    startY: 0,
    nameDisplay:'none',
    sysUserInfo: null
  },

  onLoad: function () {
    var that = this;
    if (!util.isLogin()) {
      wx.navigateTo({ url: "../userinfo/index?url=../liveHome/index" });
    } else {
      that.initData();
      that.setData({
        userinfo: wx.getStorageSync('userinfo')
      });
    }
  },
  onPullDownRefresh: function () {
    let that = this;
    page += 1;
    that.initData();
  },
  initData: function () {
    var that = this;
    that.initUserData();
    util.request(cfg.server + "/live/www/live/getListByCompany", { page: page, companyId: cfg.companyId },
      function (data) {
        console.log(data.data);
        data.data.forEach(function (o, i) {
          o.startTime = new Date(o.startTime).Format('yyyy-MM-dd hh:mm');
          that.data.listLive.push(o);
        });
       
        that.setData({
          listLive: that.data.listLive
        });
        wx.stopPullDownRefresh();
      }
    );
  },
    initUserData: function () {
        var that = this;
        util.request(cfg.findUserInfo, {},
            function (ret) {
                if (ret.status == 0) {
                    console.log(ret);
                    util.setSysUser(ret.data);
                    that.setData({ sysUserInfo: ret.data});
                } else {
                    wx.navigateTo({ url: "../regist/index" });
                }
            }
        );
    },

    add: function () {
        let that = this;
        wx.navigateTo({
            url: '../liveCsl/index?act=add',
        });
    },
});
