var util = require("../../util/util.js");
var cfg = require("../../config.js");
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
     commoditylist: [],
     sitelist: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
      var that = this;
      util.request(cfg.mallList, {"start": 0, "limit": 9999 },
          function (ret) {
              var datas = ret.data.rows;
              var commodityArr = [];
              var siteArr = [];
              for(var i=0; i < datas.length; i++){
                  if (datas[i].commodityType == '1'){
                      siteArr.push(datas[i]);
                  }else{
                      commodityArr.push(datas[i]);
                  }
              }
              that.setData({ commoditylist: commodityArr });
              that.setData({ sitelist: siteArr });
          }
      );
  },
  commodityDetal: function(e){
      var id = e.currentTarget.dataset.id;
      wx.navigateTo({
          url: '../commodity/index?id='+id
      })
  },
    siteDetail: function(e){
        var id = e.currentTarget.dataset.id;
        wx.navigateTo({
            url: '../site/index?id='+id
        })
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