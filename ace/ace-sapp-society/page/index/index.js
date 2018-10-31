var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
      activity: null,
      reportData: null,
      days: '00',
      hours: '00',
      minutes: '00',
      isEnd: false
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
      util.request(cfg.findActivitying, {"start":0, "limit": limit},
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
  diy_time: function(startTime, endTime){
      var that = this;
      var date3 = new Date(endTime).getTime() - startTime.getTime();   //时间差的毫秒数      
      //计算出相差天数
      var days = Math.floor(date3 / (24 * 3600 * 1000))
      //计算出小时数
      var leave1 = date3 % (24 * 3600 * 1000)    //计算天数后剩余的毫秒数
      var hours = Math.floor(leave1 / (3600 * 1000))
      //计算相差分钟数
      var leave2 = leave1 % (3600 * 1000)        //计算小时数后剩余的毫秒数
      var minutes = Math.floor(leave2 / (60 * 1000))
      //计算相差秒数
      var leave3 = leave2 % (60 * 1000)      //计算分钟数后剩余的毫秒数
      var seconds = Math.round(leave3 / 1000)
      console.log(" 相差 " + days + "天 " + hours + "小时 " + minutes + " 分钟" + seconds + " 秒");
      that.setData({
          days: days,
          hours: hours,
          minutes: minutes
      });
      if(date3 < 0){
          that.setData({
              isEnd: true
          });
      }

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
      var that = this;
      that.activityIng(1);
      that.initReport();
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