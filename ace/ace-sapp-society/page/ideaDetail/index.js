var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    detail: null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      util.request(cfg.ideaDetail, { "ideaId": options.ideaId, "unionId": "0" },
          function (ret) {
              if (ret.status == 0) {
                  console.log(ret);
                  that.setData({ detail: ret.data});
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
 * 发布评论
 */
  releaseComment: function(){

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