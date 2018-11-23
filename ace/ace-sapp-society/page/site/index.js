var util = require("../../util/util.js");
var cfg = require("../../config.js");
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
     siteId: null,
     siteObj : null,
     summary: null,
     userinfoData: null,
     feeType: 1
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      that.setData({ siteId: options.id});
      console.log(options.id);

      util.request(cfg.siteDetail, { "commodityId": that.data.siteId },
          function (ret) {
              var content = ret.data.summary;
              content = content.replace('<img', '<img style="max-width:100%;height:auto" ');
              that.setData({ siteObj: ret.data});
              that.setData({ summary: content});
          }
      );

      that.initUserInfo();
  },
  change: function(){
    var that = this;
    wx.setStorageSync("siteId", that.data.siteId);
    wx.navigateTo({
        url: '../siteChoose/index?siteId=' + that.data.siteId + '&feeType=' + that.data.feeType
    })
  },
    feeTypeChange: function (e) {
        console.log('radio发生change事件，携带value值为：', e.detail.value);
        var that = this;
        that.setData({ feeType: e.detail.value });
    },
    initUserInfo: function () {
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