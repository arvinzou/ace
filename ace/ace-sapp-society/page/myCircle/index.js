var util = require("../../util/util.js");
var cfg = require("../../config.js");
var start=0;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(" 生命周期函数--监听页面加载");
    var that = this;
    start=0;
    if (!util.isLogin()) {
      wx.navigateTo({ url: "../userinfo/index?url=../myCircle/index" });
    }
    if (util.isLogin()){
      that.setData({
        userinfo: wx.getStorageSync('userinfo')
      });
      that.initData();
    }
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
    wx.previewImage({
      current: e.currentTarget.id, 
      urls: [ e.currentTarget.id]
    })
  }
})