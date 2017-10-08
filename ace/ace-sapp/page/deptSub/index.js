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
    scaleHeight: 0,
    title: "单位主要职责",
    showTel:false
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
      urls: [cfg.serverfile + o.qrcode] // 需要预览的图片http链接列表
    })
  },
  openLocation: function () {
    var o = this.data.org;

    var obj = {
      latitude: o.latitude,
      longitude: o.longitude,
      scale: 28,
      name: o.department_name,
      address: o.reg_addr
    };
    console.log(obj);
    wx.openLocation(obj)
  },
  /**
   * 生命周期函数--监听页面加载
   */
  reg: function () {
    wx.navigateTo({
      url: '../reg/index?redirectTo=/page/dept/index'
    })
  },
  onLoad: function (options) {
    var that = this;
    this.setData({
      id: options.id
    }),
      util.request(cfg.selectDept, { id: options.id },
        function (data) {
          var title="";
          if(data.type=='1'){
            title = "单位主要职责";
          } else if (data.type == '3'){
            title = "公司介绍";
          } else if (data.type == '4') {
            title = "场所介绍";
          }else{
            title = "单位介绍";
          }
          var showTel=false;
          if (data.type == '1') {
            showTel = true;
          }
          that.setData({
            org: data,
            showTel: showTel,
            config: cfg,
            title:title
          });
        }
      );

    console.log("getStorage -> userInfo start")
    var o = wx.getStorageSync('userInfo');
    console.log(o)
    if (!o) {
      that.setData({
        userLogin: false
      });
     // that.reg();
    } else {

      if (o.role && (o.role == 'admin')) {
        that.setData({
          userLogin: true
        });
      } else {
        
        if (o.deptId && o.deptId == options.id){
          that.setData({
            userLogin: true
          });
        }else{
          //that.reg();
        }
      }
    }
  },
  makePhoneCall: function () {
    var phoneNumber = this.data.org.contact_tel
    wx.makePhoneCall({
      phoneNumber: phoneNumber //仅为示例，并非真实的电话号码
    })
  }
  ,
  makePhoneCall2: function () {
    var phoneNumber = this.data.org.contact_mobile
    wx.makePhoneCall({
      phoneNumber: phoneNumber //仅为示例，并非真实的电话号码
    })
  },
  addPhoneContactTel:function(){
    var org = this.data.org;
    wx.showModal({
      title: '添加通讯录',
      content: '是否添加到通讯录？',
      success: function (res) {
        if (res.confirm) {
          wx.addPhoneContact({
            firstName: org.department_name,
            mobilePhoneNumber: org.contact_tel,
            success: function () {

            }
          });
        } else if (res.cancel) {

        }
      }
    })
  }
  ,
  addPhoneContactMobile: function () {
    var org = this.data.org;
    wx.showModal({
      title: '添加通讯录',
      content: '是否添加到通讯录？',
      success: function (res) {
        if (res.confirm) {
          wx.addPhoneContact({
            firstName: org.contact_name,
            mobilePhoneNumber: org.contact_mobile,
            success: function () {

            }
          });
        } else if (res.cancel) {
          
        }
      }
    })
    
  }
  ,
  imageLoad: function (e) {
    var imageSize = util.imageUtil(e, 150)
    this.setData({
      scaleWidth: imageSize.imageWidth,
      scaleHeight: imageSize.imageHeight
    })
  }
})