var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    ideaList : [],
    userId: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      that.setData({userId: options.userId});
      that.initList();
  },

  initList: function(){
    var that = this;
    util.request(cfg.ideaList, {"start": 0, "limit": 999, "unionId": "0", "userId": that.data.userId},
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
    downloadFile: function (e) {
        var that = this;
        var fileObjList = e.currentTarget.dataset.value;
        var fileList = [];
        for (var i = 0; i < fileObjList.length; i++) {
            fileList.push(fileObjList[i].fileUrl);
        }
        wx.previewImage({
            current: fileList[0], // 当前显示图片的http链接
            urls: fileList// 需要预览的图片http链接列表
        });
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
      wx.setStorageSync('ideaId', e.currentTarget.dataset.id);
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
      var that = this;
      that.initList();
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