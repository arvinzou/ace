var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    that.initAccidentList('');
  },
  editAccident: function(){
      wx.navigateTo({
          url: '../accidentDetail/index',
      });
  },
  initAccidentList: function(name){
      var that = this;
      util.request(cfg.server + '/taa/www/traAcc/findList', { start: 0, limit: 999, keyword:name},
          function (res) {
              if (res.status == 0) {
                 that.setData({
                     list: res.data.rows
                 });
              } else {
                    wx.showModal({
                        title: '提示',
                        content: res.info,
                        success: function (res) { }
                    });
              }

          }
      );
  },
  revoke: function(e){
      
  },
  editAccident: function(e){
      var traAccId = e.target.dataset.id;
      wx.navigateTo({
          url: '../accidentDetail/index?id='+traAccId,
      });
  },
  revoke: function(e){
      var traAccId = e.target.dataset.id;
      var that = this;
      util.request(cfg.server + '/taa/www/traAcc/repealReport', { traAccId: traAccId },
          function (res) {
              if (res.status == 0) {
                  wx.showModal({
                      title: '提示',
                      content: res.info,
                      success: function (res) { }
                  });
                  that.initAccidentList('');
              } else {
                  wx.showModal({
                      title: '提示',
                      content: res.info,
                      success: function (res) { }
                  });
              }

          }
      );
  },
    searchRoad: function(e){
      var that = this;
      var name = e.detail.value;
      that.initAccidentList(name);
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