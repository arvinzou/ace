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
    console.log('file.index.js.onLoad');
    var that = this;
    that.initData('');
  },
  /**
   * 初始化加载数据。
   */
  initData: function (inputVal) {
    console.log("initData");
    var that = this;
    util.request(cfg.findFilesList, {
      type: 1,
      category: that.data.category,
      start: that.data.start,
      limit: that.data.limit
    },
      function (data) {
        wx.hideNavigationBarLoading() //完成停止加载
        wx.stopPullDownRefresh() //停止下拉刷新
        that.setData({
          list: that.data.list.concat(data)
        });
      }
    );
  },

  /**
   * 点击文件事件
   */
  ViewFile: function () {
    console.log("hehe");
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
    var that = this;
    //设施顶部样式
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
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    console.log("123");
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