var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
  data: {
    list: [],
    serverfile: cfg.serverfile
  },
  onReady: function (res) {
    console.log('dept.index.js.onReady');
    //设置手机顶部样式
    wx.setNavigationBarColor({
      frontColor: cfg.frontColor,
      backgroundColor: cfg.backgroundColor,
      animation: {
        duration: 400,
        timingFunc: 'easeIn'
      }
    });
  },
  //点击转发时的事件
  onShareAppMessage: function (res) {
    console.log('onShareAppMessage');
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
  //输入框的显示
  showInput: function () {
    console.log('showInput');
    this.setData({
      inputShowed: true
    });
  },
  //输入框的消失
  hideInput: function () {
    console.log('hideInput');
    this.setData({
      inputVal: "",
      inputShowed: false
    });
  },
  //输入框内容的清除
  clearInput: function () {
    console.log('clearInput');
    this.setData({
      inputVal: ""
    });
    this.initData('');

  },
  //输入框内容的实时搜索。
  inputTyping: function (e) {
    var that = this;
    this.setData({
      inputVal: e.detail.value
    });
    if (that.data.inputVal.length >= 2) {
      console.log(that.data.inputVal);
      that.initData(that.data.inputVal);
    }
    if (that.data.inputVal == '') {
      that.initData(that.data.inputVal);
    }

  },
  kindToggle: function (e) {
    var id = e.currentTarget.id, list = this.data.list;
    for (var i = 0, len = list.length; i < len; ++i) {
      if (list[i].id == id) {
        list[i].open = !list[i].open
      } else {
        list[i].open = false;
      }
    }
    this.setData({
      list: list
    });
  },
  
  //下拉刷新
  onPullDownRefresh: function () {
    console.log('--------下拉刷新-------');
    var that = this;
    that.setData({
      start: that.data.start + 10
    });
    that.initData('');
  },

  //页面打开即加载。
  onLoad: function () {
    console.log('dept.index.js.onLoad');
    var that = this;
    that.initData('');
  },
  //初始化加载数据。
  initData: function (inputVal) {
    var that = this;
    wx.showNavigationBarLoading();
    //请求数据
    util.request(cfg.selectDeptList, { q: inputVal, areaCode: cfg.areaCode },
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


