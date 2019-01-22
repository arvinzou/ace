var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userData: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },
  initUserData: function(){
      var that = this;
      util.request(cfg.server + '/taa/www/register/findCustomerVo', {},
          function (res) {
              if (res.status == 0) {
                 that.setData({
                     userData: res.data
                 });
              } else {
                  if (res.info == '用户尚未注册'){
                      wx.navigateTo({
                          url: '../regist/index',
                      });
                  }else{
                      wx.showModal({
                          title: '提示',
                          content: res.info,
                          success: function (res) { }
                      });
                  }
              }

          }
      );
  },
  edit: function(){
      wx.navigateTo({
          url: '../info/index',
      });
  },
  exitLogin: function(){
      try {
          wx.clearStorage({success:function(){
              wx.navigateTo({
                  url: "../userinfo/index?url=../me/index&type=switchTab"
              });
          }
          })
      } catch (e) {
          // Do something when catch error
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
      var that = this;
      if (!util.is_login()) {
          wx.navigateTo({
              url: "../userinfo/index?url=../me/index&type=switchTab"
          });
          return;
      } else {
          that.initUserData();
      }
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