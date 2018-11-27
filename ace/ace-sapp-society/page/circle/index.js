var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
  /**
   * 页面的初始数据
   */
  data: {
      list:[],
      maskFlag: true,
      videoUrl: null,
      userinfo: wx.getStorageSync('userinfo')
  },
    
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(" 生命周期函数--监听页面加载");
    var that = this;
    that.setData({ sysUserInfo: wx.getStorageSync("sysUserInfo")});
    that.initData();
  },
    viewVideo: function (e) {
        console.log("查看视频地址=====================================" + e.currentTarget.id);
        var that = this;
        that.setData({
            videoUrl: e.currentTarget.id,
            maskFlag: false
        });
    },
    exitVideo: function (e) {
        console.log("==================================退出视频观看");
        var that = this;
        var videoContent = wx.createVideoContext("video");
        videoContent.pause();
        that.setData({
            maskFlag: true,
            videoUrl: null
        });
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
      that.setData({ sysUserInfo: wx.getStorageSync("sysUserInfo") });
      that.initData();
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

  },
  initData:function(){
    var that=this;
      util.request(cfg.server + "/society/circle/www/getList", { start: 0, limit: 1000, status:"3"},
      function (data) {
        that.setData({
          list: data
        })
      }
    );
  },
  add:function(){
    wx.navigateTo({
      url: '../circleAdd/index',
    })
  },
  previewImage: function (e) {
    console.log(e);
    var list = e.currentTarget.dataset.list;
    var arrTemp = [];
    for(var i=0; i<list.length; i++){
        arrTemp.push(list[i].url);
    }
    wx.previewImage({
      current: e.currentTarget.id, 
      urls: arrTemp
    })
  },
  addlike: function (e) {
    console.log(e.currentTarget.dataset.id);
    var that=this;
    util.request(cfg.server + "/society/circle/www/updateAddLike", {id: e.currentTarget.dataset.id },
      function (data) {
        that.initData();
      }
    );
  },
  addcmt: function (e) {
    console.log(e.currentTarget.dataset.id);
    var that = this;
    that.setData({
      actionComment: true,
      commentType: 'cmt',
      id: e.currentTarget.dataset.id
    })
  },
  hiddenComment: function (e) {
    let that = this;
    if (that.data.commentVal) {
      wx.showModal({
        title: '提示',
        content: '确定放弃吗？',
        success: function (res) {
          if (!res.confirm) {
            return;
          }
        }
      });
    }
    that.setData({
      actionComment: false,
    });
  },
  getValue: function (e) {
    let that = this;
    that.data.commentVal = e.detail.value;
  },
  formSubmit: function (e) {
    var commentVal = e.detail.value.commentVal;
    var that = this;
    if (!commentVal) {
      wx.showToast({
        title: '发送内容不为空',
        icon: 'none',
        duration: 1000
      })
      return false;
    }
    if (that.data.commentType == 'cmt') {
      that.sendComment(commentVal);
    }
    that.setData({
      actionComment: false
    });
  },
  sendComment: function (content) {
    var that = this;
    var data = {};
    data.circleId = that.data.id;
    data.uid = that.data.userinfo.unionId;
    data.content = content;
    util.request(cfg.server + "/society/circleCmt/www/insertCircleCmt", { jsons: JSON.stringify(data) },
      function (data) {
        that.initData();
      }
    );

  }
})