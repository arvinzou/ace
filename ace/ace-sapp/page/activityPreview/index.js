var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
  data: {
    serverfile: cfg.serverfile,
    indicatorDots: true,
    autoplay: true,
    interval: 5000,
    duration: 1000,
    o: {},
    commentType:'0',
    viewshow:false,
    images: {
      width: 0,
      height: 0
    },
    max: 0,
    loading: false,
    disabled: false,
    uuid: util.uuid(),
    files: []
  },
  onReady: function (res) {
    wx.setNavigationBarColor({
      frontColor: cfg.frontColor,
      backgroundColor: cfg.backgroundColor,
      animation: {
        duration: 400,
        timingFunc: 'easeIn'
      }
    });
  },
  onShareAppMessage: function (res) {
    var that = this;
    if (res.from === 'button') {
      // 来自页面内转发按钮
      console.log(res.target)
    }
    return {
      title: that.data.o.name,
      path: '/page/activityPreview/index?id=' + that.data.o.id,
      success: function (res) {
        // 转发成功
      },
      fail: function (res) {
        // 转发失败
      }
    }
  },
  openLocation: function () {
    var o = this.data.o;

    var obj = {
      latitude: o.latitude,
      longitude: o.longitude,
      scale: 28,
      name: o.activityLocation,
      address: o.activityLocation
    };
    console.log(obj);
    wx.openLocation(obj)
  },
  previewImage: function (e) {
   console.log(e);
    var that = this;
    wx.previewImage({
      current: '', // 当前显示图片的http链接
      urls: [e.currentTarget.dataset.url] // 需要预览的图片http链接列表
    })
  },
  previewPhoto: function () {
    var o = this.data.o;
    var that = this;
    wx.previewImage({
      current: '', // 当前显示图片的http链接
      urls: [that.data.serverfile + o.photo] // 需要预览的图片http链接列表
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */

  onLoad: function (options) {
    var that = this;
    this.setData({
      id: options.id,
      WXSESSIONID: wx.getStorageSync('WX-SESSION-ID'),
      fileList: [],
      checkImageUrl: cfg.checkImageUrl,
      uuid: util.uuid()
    }),
      util.request(cfg.selectActivityById, { id: options.id },
        function (data) {
          that.setData({
            o: data,
            config: cfg
          });
          wx.setNavigationBarTitle({ title: that.data.o.name });
        }
      );
    util.request(cfg.selectPhotoListById, { id: options.id },
      function (data) {
        that.setData({
          list: data
        });
      }
    );
    util.request(cfg.selectUserListByActivityId, { id: options.id },
      function (data) {
        that.setData({
          userList: data
        });
      }
    );
    util.request(cfg.updateActivity, { id: options.id,type:"reading" },
      function (data) {
        var o = that.data.o;
        o.reading = o.reading+1;
        that.setData({
          o:o
        });
      }
    );
    that.loadComment();
  },
  loadComment:function(){
    var that=this;
    util.request(cfg.selectCommentListByActivityId, { id: that.data.id },
      function (data) {
        that.setData({
          commentList: data
        });
      }
    );
  },
  comment: function (e) {
    console.log(e);
    var that=this;
    that.setData({
      viewshow: !that.data.viewshow
    });
  }
  ,
  up: function (e) {
    var that=this;
    var o = that.data.o;
    console.log(e);
    util.request(cfg.updateActivity, { id: o.id, type: "up" },
      function (data) {
        o.up = o.up + 1;
        that.setData({
          o: o
        });
      }
    );
  },
  formSubmit: function (e) {
    var that = this;
    this.setData({
      loading: true,
      disabled: true
    })
    console.log('form发生了submit事件，携带数据为：', e.detail.value);
    e.detail.value["id"] = this.data.uuid;
    e.detail.value["activityId"] = this.data.id;
    e.detail.value["commentType"] = this.data.commentType;
    
    util.request(cfg.insertActivityComment, e.detail.value,
      function (data) {
        that.setData({
          loading: false,
          disabled: false
        });
        that.loadComment();
        wx.showModal({
          title: '提示',
          content: data.errorMessage,
          success: function (res) {
            if (res.confirm) {

            }
          }
        })
      }
    );
  }

})