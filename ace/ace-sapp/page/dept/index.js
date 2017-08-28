var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
  data: {
    list: [
    ],
    serverfile: cfg.serverfile
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
      path: '/',
      success: function (res) {
        // 转发成功
      },
      fail: function (res) {
        // 转发失败
      }
    }
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
    var that =this;
    this.setData({
      inputVal: e.detail.value
    });
    if (that.data.inputVal.length >=2) {
      console.log(that.data.inputVal);
      that.initData(that.data.inputVal);
    }
    if (that.data.inputVal==''){
      that.initData(that.data.inputVal);
    }
    
  },
  kindToggle: function (e) {
    var id = e.currentTarget.id, list = this.data.list;
    for (var i = 0, len = list.length; i < len; ++i) {
      if (list[i].id == id) {
        list[i].open = !list[i].open
      } else {
        list[i].open = false
      }
    }
    this.setData({
      list: list
    });
  },
  onPullDownRefresh:function () {
    console.log('--------下拉刷新-------')
    var that = this;
    that.setData({
      start: that.data.start + 10
    });
    that.initData('');
  },
  onLoad: function () {
    var that=this;
    that.initData('');
  },
  initData: function (inputVal){
    var that = this;
    wx.showNavigationBarLoading();
    util.request(cfg.selectDeptList, { q: inputVal,areaCode:cfg.areaCode},
      function (data) {
        wx.hideNavigationBarLoading(); //完成停止加载
        wx.stopPullDownRefresh(); //停止下拉刷新
        that.setData({
          list: data
        });
      }
    );
  }
})


