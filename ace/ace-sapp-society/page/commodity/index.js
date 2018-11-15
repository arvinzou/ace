var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
      siteId: null,
      siteObj: null,
      userinfoData: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      that.setData({ siteId: options.id });
      console.log(options.id);

      util.request(cfg.siteDetail, { "commodityId": that.data.siteId },
          function (ret) {
              that.setData({ siteObj: ret.data });
          }
      );
    
      that.initUserInfo();

      

  },
  /**
   * 查询用户信息
   */
  initUserInfo: function(){
      var that = this;
      util.request(cfg.findUserInfo, {},
          function (ret) {
              if (ret.status == 0) {
                  that.setData({
                      userinfoData: ret.data,
                  });
              } else {
                  wx.navigateTo({ url: "../regist/index" });
              }

          }
      );
  },
  buy: function(e){
     wx.setStorageSync("commodityId", e.currentTarget.dataset.id);
     wx.navigateTo({
         url: '../settlement/index?commodityId=' + e.currentTarget.dataset.id,
     })
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
    
  }
})