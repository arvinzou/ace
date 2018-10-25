var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    detail: null,
    commentList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      if (!util.is_login()) {
          wx.navigateTo({ url: "../userinfo/index?url=../ideaDetail/index"});
      }
      if (util.is_login()) {
          that.setData({
              userinfo: wx.getStorageSync('userinfo')
          });
          that.initData();
          that.initCommentList();
      }
  },
  initData: function (){
      var that = this;
        util.request(cfg.ideaDetail, { "ideaId": wx.getStorageSync('ideaId'), "unionId": "0" },
          function (ret) {
              if (ret.status == 0) {
                  console.log(ret);
                  that.setData({ detail: ret.data });
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
  initCommentList: function(){
      var that = this;
      util.request(cfg.server + "/society/www/comment/findList", { "bisId": wx.getStorageSync('ideaId'), "bisType": "idea" },
          function (ret) {
              if (ret.status == 0) {
                  console.log(ret);
                  that.setData({ commentList: ret.data.rows });
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
      var that = this;
      that.setData({
          userinfo: wx.getStorageSync('userinfo')
      });
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
      var that = this;
      that.initData();
      that.initCommentList();
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
        util.request(cfg.server + "/society/www/comment/submitComment", { "bisType": "idea", "userId": wx.getStorageSync('WX-SESSION-ID'), "bisId": wx.getStorageSync('ideaId'), "content": content, "unionId": wx.getStorageSync('WX-SESSION-ID')},
            function (data) {
                that.initCommentList();
            }
        );

    },
    addlike: function (e) {
        console.log(e.currentTarget.dataset.id);
        var that = this;
        util.request(cfg.server + "/society/www/comment/admire", { "bisId": e.currentTarget.dataset.id, "bisType": 'idea', "unionId": wx.getStorageSync('WX-SESSION-ID')},
            function (data) {
                that.initCommentList();
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
})