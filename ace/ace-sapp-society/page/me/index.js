var util = require("../../util/util.js");
var cfg = require("../../config.js");
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
      
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      console.log("util.is_login()============================================="+util.is_login());
      if (!util.is_login()) {
          wx.navigateTo({ url: "../userinfo/index?url=../me/index" });
      }else{
          that.setData({
              userinfo: wx.getStorageSync('userinfo')
          });
        
      }
      
  },
  initUserData: function () {
        util.request(cfg.findUserInfo, {},
            function (ret) {
                if (ret.status == 0) {
                    return true;
                } else {
                    if (ret.info == '用户尚未注册') {
                        return false;
                    }
                }

            }
        );
    },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },
  loveRanking: function(){
      wx.navigateTo({
          url: '../loveRanking/index'
      })
  },
    myActivity: function(){
        wx.navigateTo({
            url: '../myActivity/index'
        })
    },
    historyOrder:function(){
        wx.navigateTo({
            url: '../historyOrder/index'
        })
    },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
      var that = this;
      if (wx.getStorageSync('userinfo')){
          if(!that.initUserData()){
              wx.navigateTo({ url: "../regist/index" });
          }
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