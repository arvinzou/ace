var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    ideaList : []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      that.initList();
  },

initList: function(){
    var that = this;
    util.request(cfg.ideaList, {"start": 0, "limit": 999, "unionId": "0"},
        function (ret) {
            if (ret.status == 0) {
                console.log(ret);
                that.setData({ ideaList: ret.data.rows});
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

/**
 * 发布我有点子
 */
  releaseIdea: function(){
      wx.navigateTo({
          url: '../releaseIdea/index'
      })
  },
  showDetal: function(e){
      wx.navigateTo({
          url: '../ideaDetail/index?ideaId=' + e.currentTarget.dataset.id
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