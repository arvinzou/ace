var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
  /**
   * 页面的初始数据
   */
  data: {
    winWidth: 0,
    winHeight: 0, 
    currentTab: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.setNavigationBarColor({
      frontColor: cfg.frontColor,
      backgroundColor: cfg.backgroundColor,
      animation: {
        duration: 400,
        timingFunc: 'easeIn'
      }
    });
    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          winWidth: res.windowWidth,
          winHeight: res.windowHeight
        });
      }
    });
  },
  onShareAppMessage: function (res) {
    return {
      title: '我发现了掌上统战小程序，一起看看吧',
      path: '/page/files/index'
    }
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    wx.stopPullDownRefresh();
  },

  /**  
     * 滑动切换tab  
     */
  bindChange: function (e) {
    var that = this;
    that.setData({ currentTab: e.detail.current });
  },
  /**  
   * 点击tab切换  
   */
  swichNav: function (e) {
    var that = this;
    if (this.data.currentTab === e.target.dataset.current) {
      return false;
    } else {
      that.setData({
        currentTab: e.target.dataset.current
      })
    }
  },

})