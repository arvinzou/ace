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
    var that = this;
    wx.setNavigationBarColor({
      frontColor: cfg.frontColor,
      backgroundColor: cfg.backgroundColor,
      animation: {
        duration: 400,
        timingFunc: 'easeIn'
      }
    });

  },
  onLoad: function (params) {
    var that = this;
    that.setData(params);
    that.initData(that.data.id);
  },
  onPullDownRefresh: function () {
    let that = this;
    that.initData(that.data.id);
  },
  initData: function (id) {
    var that = this;
    util.request(cfg.selectAppealCaseByPrimaryKey, {id:id},
      function (data) {
        that.setData({
          o: data.value
        });
        console.log(data.value);
        if(data.status!=0){
          wx.showModal({ title: "系统提示", showCancel: false, content: data.errorMessage });
        }else{
          wx.setNavigationBarTitle({ title: that.data.o.title});
        }
        wx.stopPullDownRefresh();
      }
    );
  },
  previewImage: function (e) {
    console.log(e);
    var that = this;
    wx.previewImage({
      current: '', // 当前显示图片的http链接
      urls: [e.currentTarget.dataset.url] // 需要预览的图片http链接列表
    })
  }
});
