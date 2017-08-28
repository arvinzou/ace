var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
  /**
   * 页面的初始数据
   */
  data: {
    indicatorDots: true,
    autoplay: false,
    interval: 5000,
    duration: 1000,
    scaleWidth: 0,//缩放后的宽 
    scaleHeight: 0,//缩放后的高
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
  previewQrcode: function () {
    var o = this.data.org;
    var that = this;
    wx.previewImage({
      current: '', // 当前显示图片的http链接
      urls: [cfg.serverfile + o.icon] // 需要预览的图片http链接列表
    })
  },
  openLocation: function () {
    var o=this.data.org;
   
    var obj = {
      latitude: o.latitude,
      longitude: o.longitude,
      scale: 28,
      name: o.name,
      address: o.addr
    };
    console.log(obj);
    wx.openLocation(obj)
  },
  /**
   * 生命周期函数--监听页面加载
   */

    onLoad: function(options) {
      var that = this;
      this.setData({
        id: options.id
      }),
        util.request(cfg.selectOrganization, { id: options.id},
        function (data) {
          that.setData({
            org: data,
            config:cfg
          });
        }
      );
    },
  makePhoneCall:function(){
    var phoneNumber = this.data.org.tel
    wx.makePhoneCall({
      phoneNumber: phoneNumber //仅为示例，并非真实的电话号码
    })
  }
  ,
  imageLoad: function (e) {
    var imageSize = util.imageUtil(e,150)
    this.setData({
      scaleWidth: imageSize.imageWidth,
      scaleHeight: imageSize.imageHeight
    })
  }
})