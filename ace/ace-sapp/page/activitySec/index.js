var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
  data: {
    list: [
    ],
    serverfile: cfg.serverfile,
    start: 0,
    limit: 10,
    images: {
      width: 100,
      height: 100
    },
    indicatorDots: false,
    autoplay: false,
    interval: 5000,
    duration: 1000,
    category:'1'
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
    if (res.from === 'button') {
      // 来自页面内转发按钮
      console.log(res.target)
    }
    return {
      title: '我发现了掌上统战小程序，一起看看吧',
      path: '/page/activitySec/index',
      success: function (res) {
        // 转发成功
      },
      fail: function (res) {
        // 转发失败
      }
    }
  },
  imageLoad: function (e) {
    var $width = e.detail.width,    //获取图片真实宽度
      $height = e.detail.height,
      ratio = $width / $height;    //图片的真实宽高比例
    var viewWidth = 110,           //设置图片显示宽度，左右留有16rpx边距
      viewHeight = 110 / ratio;    //计算的高度值
    var image = this.data.images;
    //将图片的datadata-index作为image对象的key,然后存储图片的宽高值
    image[e.target.dataset.index] = {
      width: viewWidth,
      height: viewHeight
    }
    this.setData({
      images: image
    })
  },
  showInput: function () {
    this.setData({
      inputShowed: true
    });
  },
  hideInput: function () {
    this.setData({
      inputVal: "",
      inputShowed: false
    });
  },
  clearInput: function () {
    this.setData({
      inputVal: ""
    });
    this.initData('');

  },
  inputTyping: function (e) {
    var that = this;
    this.setData({
      inputVal: e.detail.value,
      start: 0,
      list:[]
    });
    if (that.data.inputVal.length >= 2) {
      console.log(that.data.inputVal);
      that.initData(that.data.inputVal);
    }
    if (that.data.inputVal == '') {
      that.initData(that.data.inputVal);
    }
  },
  onLoad: function (options) {
    this.setData({ category: options.category});
    if (this.data.category=='1'){
      wx.setNavigationBarTitle({ title: "统战管理" });
    }
    if (this.data.category == '2') {
      wx.setNavigationBarTitle({ title: "同心行动" });
    }
    this.initData('');
  },
  initData: function (inputVal) {
    console.log("initData");
    var that = this;
    util.request(cfg.selectActivityPageList, {
      name: inputVal,category:that.data.category,start:that.data.start,limit:that.data.limit
    },
      function (data) {
        wx.hideNavigationBarLoading() //完成停止加载
        wx.stopPullDownRefresh() //停止下拉刷新
        that.setData({
          list: that.data.list.concat(data)
        });
      }
    );
  },
  onPullDownRefresh: function () {
    console.log('--------下拉刷新-------')
    wx.showNavigationBarLoading();
    var that = this;
    that.setData({
      start: that.data.start + 10
    });
    that.initData('');
  }
})
