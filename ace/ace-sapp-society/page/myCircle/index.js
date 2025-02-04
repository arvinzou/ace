var util = require("../../util/util.js");
var cfg = require("../../config.js");
var start=0;
Page({

  /**
   * 页面的初始数据
   */
  data: {
      list:[],
      maskFlag: true,
      videoUrl: null,
      userinfo: wx.getStorageSync('userinfo')
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(" 生命周期函数--监听页面加载");
    var that = this;
    that.setData({ sysUserInfo: wx.getStorageSync("sysUserInfo")});
    start=0;
    that.initData();
  },
    viewVideo: function (e) {
        console.log("查看视频地址=====================================" + e.currentTarget.id);
        var that = this;
        that.setData({
            videoUrl: e.currentTarget.id,
            maskFlag: false
        });
    },
    exitVideo: function (e) {
        console.log("==================================退出视频观看");
        var that = this;
        var videoContent = wx.createVideoContext("video");
        videoContent.pause();
        that.setData({
            maskFlag: true,
            videoUrl: null
        });
    },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    var that=this;
    that.initData();
    wx.stopPullDownRefresh();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  initData:function(){
    var that=this;
    util.request(cfg.server +"/society/circle/www/myCircleList", {start:start,limit:100},
      function (data) {
        that.setData({
          list: data
        })
      }
    );
  },
  add:function(){
    wx.navigateTo({
      url: '../circleAdd/index',
    })
  },
  previewImage: function (e) {
    console.log(e);
      var list = e.currentTarget.dataset.list;
      var arrTemp = [];
      for (var i = 0; i < list.length; i++) {
          arrTemp.push(list[i].url);
      }
      wx.previewImage({
          current: e.currentTarget.id,
          urls: arrTemp
      })
  }
})