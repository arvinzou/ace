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
    var that = this;
    that.initData();
  },

  /**
   * 获取用户信息
   */
  initData: function () {
      var that = this;
      util.request(cfg.server + '/taa/www/register/findCustomerVo', {},
          function (res) {
              if (res.status == 0) {
                  that.setData({
                      userData: res.data
                  });
              } else {
                  if (res.info == '用户尚未注册') {
                      wx.navigateTo({
                          url: '../regist/index',
                      });
                  } else {
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
  /**
   * 修改手机号码
   */
  formSubmit: function(e){
      var that = this;
      var mobile = e.detail.value.mobile;
      if (mobile == "" || mobile == null || mobile == undefined){
          wx.showModal({
              title: '提示',
              content: '手机号不能为空！',
              success: function (res) { }
          });
      }
      util.request(cfg.server + '/taa/www/register/updateMobile', { mobile: mobile},
          function (res) {
              wx.showModal({
                  title: '提示',
                  content: res.info,
                  success: function (res) { }
              });

          }
      );

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