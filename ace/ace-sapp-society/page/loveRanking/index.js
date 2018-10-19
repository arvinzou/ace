var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
      rankList: [],
      currentTab: 'month'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      that.initRank(that.data.currentTab);
  },
  detail: function(){
      wx.navigateTo({
          url: '../coinDetail/index'
      })
  },
  initRank: function(rankType){
      var that = this;
      util.request(cfg.pointRank, {  "rankType": rankType},
          function (ret) {
              if (ret.status == 0) {
                  console.log(ret);
                  that.setData({ rankList: ret.data.list});
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
  changeType: function(e){
      var that = this;
      var temp = e.currentTarget.dataset.value;
      that.setData({
          currentTab: temp
      });
      that.initRank(that.data.currentTab);
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