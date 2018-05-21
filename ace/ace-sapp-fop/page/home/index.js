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
  onLoad: function () {
    var that = this;
    var id = '2e968824893a4ec19a00b62bfede0b14';
    that.setData({scene:id});
    that.initData(id);
  },
  onPullDownRefresh: function () {
    let that = this;
    that.initData(that.data.scene);
  },
  initData: function (id) {
    var that = this;
    util.request(cfg.getAppealById, {id:id},
      function (data) {
        that.setData({
          appeal: data.value
        });
        console.log(data.value);
        if(data.status!=0){
          wx.showModal({ title: "系统提示", showCancel: false, content: data.errorMessage });
        }else{
          wx.setNavigationBarTitle({ title: that.data.appeal.name});
        }
        wx.stopPullDownRefresh();
      }
    );
  },
  bindtap:function(e){
    var that=this;
    wx.navigateTo({ url:'../appeal/index?scene='+that.data.appeal.id});
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
