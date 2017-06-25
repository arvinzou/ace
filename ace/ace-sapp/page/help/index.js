var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
  data: {
    list: [
    ]
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
  onLoad: function () {
    this.autoLogin();
  },
  initData: function (inputVal){
    var that = this;
    util.request(cfg.selectOrganizationList, { q: inputVal},
      function (data) {
        that.setData({
          list: data
        });
      }
    );
  },
  autoLogin:function () {
    var that = this;
    wx.login({
      success: function (o) {
        wx.getUserInfo({
          success: function (res) {
            wx.request({
              url: cfg.loginUrl,
              data: {
                appid: cfg.appid,
                appsecret: cfg.appsecret,
                code: o.code,
                encryptedData: res.encryptedData,
                iv: res.iv
              },
              success: function (res) {
                wx.setStorageSync('WX-SESSION-ID', res.data.value['3rd_session']);
                console.log('login success', result);
                that.initData('');
              },
              fail: function ({errMsg}) {
                console.log('request fail', errMsg)
              }
            })
          }
        })
      }
    })
  }
})