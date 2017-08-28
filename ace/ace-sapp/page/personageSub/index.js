var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
  /**
   * 页面的初始数据
   */
  data: {
  serverfile: cfg.serverfile,
    o:{},
    images: {
      width: 0,
      height: 0
    }
  },
  
  imageLoad: function (e) {
    var $width = e.detail.width,    //获取图片真实宽度
      $height = e.detail.height,
      ratio = $width / $height;    //图片的真实宽高比例
    var viewWidth = 200,           //设置图片显示宽度，左右留有16rpx边距
      viewHeight = 200 / ratio;    //计算的高度值
    var image = this.data.images;
    //将图片的datadata-index作为image对象的key,然后存储图片的宽高值
    image = {
      width: viewWidth,
      height: viewHeight
    }
    console.log("image->",image);
    this.setData({
      images: image
    })
  },
  previewImage: function () {
    var o = this.data.o;
    var that=this;
    wx.previewImage({
      current: '', // 当前显示图片的http链接
      urls: [that.data.serverfile+o.remark] // 需要预览的图片http链接列表
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
      id: options.id
    }),
      util.request(cfg.selectPersonage, { id: options.id},
        function (data) {
          that.setData({
            o: data,
            config: cfg
          });
          wx.setNavigationBarTitle({ title:that.data.o.name});
        }
      );
  },
  makePhoneCall: function () {
    var phoneNumber = this.data.o.tel
    wx.makePhoneCall({
      phoneNumber: phoneNumber //仅为示例，并非真实的电话号码
    })
  }
  ,
  addPhoneContactMobile: function () {
    var o = this.data.o;
    wx.showModal({
      title: '添加通讯录',
      content: '是否添加到通讯录？',
      success: function (res) {
        if (res.confirm) {
          wx.addPhoneContact({
            firstName: o.contact_name,
            mobilePhoneNumber: o.contact_mobile,
            success: function () {

            }
          });
        } else if (res.cancel) {

        }
      }
    })
  }
})