var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
  data: {
    countries: [],
    countryIndex: 0,
  },
  formSubmit: function (e) {
    var that=this;
    that.navigator('../info/index');
    console.log('form发生了submit事件，携带数据为：', e.detail.value);
    util.request(cfg.insertFeedback, e.detail.value,
      function (data) {
        wx.showModal({
          title: '提示',
          content: data.errorMessage,
          success: function (res) {
            that.navigator('../info/index');
          }
        })
      }
    );
  },
  formReset: function (e) {
    console.log('form发生了reset事件，携带数据为：', e.detail.value)
    this.setData({
      chosen: ''
    })
  },
  onLoad: function () {
    this.setData({
      WXSESSIONID: wx.getStorageSync('WX-SESSION-ID')
    });
    var that = this;
    util.request(cfg.selectAreaCodeList, {areaCode:cfg.areaCode},
      function (data) {
        var countries = [];
        for (var i = 0; i < data.length; i++) {
          var o = data[i];
          countries.push(o.name);
        }
        that.setData({
          countries: countries,
          checkImageUrl: cfg.checkImageUrl
        });
      }
    );
  },
  navigator: function (url) {
    wx.navigateTo({
      url: url
    });
  }
});