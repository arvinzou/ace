var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
      activityId:'',
      list:[],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      let that = this;
      console.log(options);
      let activityId = options.id;
      
      if (!activityId) {
          wx.navigateBack({})
          return;
      }
      that.data.activityId = activityId;
      that.initdata();
  },

    // 获取列表
    initdata: function () {
        let that = this;
        util.request(cfg.participants, {
            activityId: that.data.activityId,
        },
            function (rst) {
                console.log(rst);
                console.log();
                wx.hideNavigationBarLoading() //完成停止加载
                wx.stopPullDownRefresh() //停止下拉刷新
                that.setData({
                    list: rst.data
                });
            }
        );
    },

    callphone: function (e) {
        let data = e.currentTarget.dataset
        let pmobile = data.mobile;
        wx.makePhoneCall({
            phoneNumber: pmobile
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