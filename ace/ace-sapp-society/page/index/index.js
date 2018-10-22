var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
      activity: null,
      reportData: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      that.activityIng(1);
      that.initReport();
  },

  /**
   * 正在进行中的活动
   */
  activityIng: function(limit){
      var that = this;
      util.request(cfg.findActivitying, {},
          function (ret) {
              if (ret.status == 0) {
                 console.log(ret);
                  if(ret.data[0]){
                      //剩余报名天数
                      ret.data[0].leastDays = that.diy_time(new Date(), ret.data[0].dendline);
                      console.log("剩余报名天数==========================" + ret.data[0].leastDays);
                      ret.data[0].dendline = ret.data[0].dendline.substring(0,16);
                      that.setData({ activity: ret.data[0] });
                  }
                  
              } else {
                  wx.showModal({
                      title: '提示',
                      content: ret.errorMessage,
                      success: function (res) { }
                  });
              }

          }
      );
  },
  diy_time: function(time1, time2){
        time1 = Date.parse(time1);
        time2 = Date.parse(new Date(time2));
        return Math.abs(parseInt((time2 - time1) / 1000 / 3600 / 24));
  },

  /**
   * 精选往事
   */
  initReport: function(){
      var that = this;
      util.request(cfg.findPublicActivityReportList, { "start": 0, "limit": 2},
          function (ret) {
              if (ret.status == 0) {
                  that.setData({ reportData: ret.data})
              } else {
                  wx.showModal({
                      title: '提示',
                      content: ret.errorMessage,
                      success: function (res) { }
                  });
              }

          }
      );
  },
  behavior: function(){
      wx.navigateTo({
          url: '../behavior/index'
      })
  },
  idea: function(){
      wx.navigateTo({
          url: '../idea/index'
      })
  },
  showMoreActivity: function(){
      wx.navigateTo({
          url: '../indexMore/index'
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