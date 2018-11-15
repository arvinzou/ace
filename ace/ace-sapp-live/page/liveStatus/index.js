var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
  data: {
    loading: false,
    disabled: false,
    statusItems: [
      { name: '预播', value: '1', checked: true },
      { name: '直播', value: '2' },
      { name: '历史', value: '3' }
    ]
  },
  onReady: function (res) {
  },
  onLoad: function (param) {
    var that = this;
    that.setData(param);
    if (!util.isLogin()) {
      wx.navigateTo({ url: "../userinfo/index?url=../liveStatus/index?id=" + params.id });
    } 
  },
  onShow: function () {
    var that = this;
  },
  formSubmit: function (e) {
    var that = this;
    that.setData({
      loading: true,
      disabled: true
    });
    var data =  e.detail.value;
    console.log(data);
    
    util.request(cfg.server+"/live/live/www/updateStatus", {id: that.data.id, status: data.status},
      function (data) {
        that.setData({
          loading: false,
          disabled: false
        })
        wx.showModal({
          title: '提示',
          content: data.errorMessage,
          showCancel: false,
          success: function (res) {
            if (res.confirm && data.status == 0) {
              wx.navigateTo({ url: "../myLive/index" });
            }
          }
        })
      }
    );
  },
  statusChange: function (e) {
    console.log('radio发生change事件，携带value值为：', e.detail.value);
    var statusItems = this.data.statusItems;
    for (var i = 0, len = statusItems.length; i < len; ++i) {
      statusItems[i].checked = statusItems[i].value == e.detail.value;
    }
    this.setData({
      statusItems: statusItems
    });
  }
});