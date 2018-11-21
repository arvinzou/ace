var util = require("../../util/util.js");
var cfg = require("../../config.js");
const app = getApp();
Page({
  data: {
    max: 0,
    loading: false,
    disabled: true
  },
  onReady: function (res) {

  },
  onLoad: function (param) {
    var that = this;
    that.setData(param);
    that.setData({
      WXSESSIONID: wx.getStorageSync('WX-SESSION-ID'),
      userProp: wx.getStorageSync('userProp')
    });
    that.initData(that.data.id);
  },
  formSubmit: function (e) {
    var that = this;
    that.setData({
      loading: true,
      disabled: true
    });
    var data = {};
    data = e.detail.value;
    console.log(data);
    that.updateDetailsOfProgress(that.data.id, data.content);
  },
  valueChange: function (e) {
    console.log(e);
    if (e.detail && e.detail.value.length > 0) {
      this.setData({
        max: e.detail.value.length,
        disabled: false
      });
    }
  },
  updateDetailsOfProgress: function (id, detailsOfProgress) {
    var that = this;
    util.request(cfg.updateDetailsOfProgress, { id: id, detailsOfProgress: detailsOfProgress, client: 'miniApp' },
      function (data) {
        that.setData({
          loading: false,
          disabled: false
        })
        wx.showModal({
          title: '提示',
          showCancel: false,
          content: data.errorMessage,
          success: function (res) {
            if (res.confirm && data.status == 0) {
              that.setData({
                content: ''
              });
            }
          }
        })
      }
    );
  },
  initData: function (id) {
    var that = this;
    util.request(cfg.selectAppealCaseByPrimaryKey, { id: id },
      function (data) {
        that.setData({
          appeal: data.value
        });
        console.log(data.value);
        if (data.status != 0) {
          wx.showModal({ title: "系统提示", showCancel: false, content: data.errorMessage });
        } else {
          wx.setNavigationBarTitle({ title: that.data.appeal.title });
        }
        wx.stopPullDownRefresh();
      }
    );
  }
});