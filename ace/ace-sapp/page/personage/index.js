var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
  data: {
    list: [
    ],
    serverfile: cfg.serverfile,
    userLogin:false,
    start:0,
    limit:10,
    images: {
      width: 100,
      height: 100}
  },
  onShareAppMessage: function (res) {
    if (res.from === 'button') {
      // 来自页面内转发按钮
      console.log(res.target)
    }
    return {
      title: '我发现了掌上统战小程序，一起看看吧',
      path: '/page/personage/index',
      success: function (res) {
        // 转发成功
      },
      fail: function (res) {
        // 转发失败
      }
    }
  },
  imageLoad: function (e) {
   /* var $width = e.detail.width,    //获取图片真实宽度
      $height = e.detail.height,
      ratio = $width / $height;    //图片的真实宽高比例
    var viewWidth = 80,           //设置图片显示宽度，左右留有16rpx边距
      viewHeight = 80 / ratio;    //计算的高度值
    var image = this.data.images;
    //将图片的datadata-index作为image对象的key,然后存储图片的宽高值
    image[e.target.dataset.index] = {
      width: viewWidth,
      height: viewHeight
    }
    this.setData({
      images: image
    })*/
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
      inputVal: e.detail.value,
      start:0
    });
    if (that.data.inputVal.length >=2) {
      console.log(that.data.inputVal);
      that.initData(that.data.inputVal);
    }
    if (that.data.inputVal==''){
      that.initData(that.data.inputVal);
    }
    
  },
  searchNodeById:function(data, id){
    var that=this;
    for(var i in data) {
      //console.log('i', i);
      //console.log('datai', data[i].children);
      if (data[i].id == id) {
        console.log(data[i]);
        if(data[i].state =='closed'){
          data[i].state = 'open'
        }else{
          data[i].state = 'closed'
        }
        break;
      } else {
          if (data[i].children){
            that.searchNodeById(data[i].children, id);
          }
      }
    }
  },
  kindToggle: function (e) {
    var that = this;
    var id = e.currentTarget.id, list = that.data.list;
    that.searchNodeById(list,id);
    that.setData({
      list: list
    });
  },
  onLoad: function () {
    console.log("===============personage on load");
    var that=this;
    wx.setNavigationBarColor({
      frontColor: cfg.frontColor,
      backgroundColor: cfg.backgroundColor,
      animation: {
        duration: 400,
        timingFunc: 'easeIn'
      }
    });
    that.initData('');
  },
  reg:function(){
    wx.navigateTo({
      url: '../reg/index?redirectTo=/page/personage/index'
    })
  },
  initData: function (inputVal){
    var that = this;
    console.log("initData");
    console.log("getStorage -> userInfo start")
    var o = wx.getStorageSync('userInfo');
    if (!o) {
      that.setData({
        userLogin: false
      });
      that.reg();
    } else {
      that.setData({
        userLogin: true
      });
      util.request(cfg.getPersonageTreeList, {
          q: inputVal
        },
        function (data) {
          wx.stopPullDownRefresh() //停止下拉刷新
          that.setData({
            list: data
          });
        }
      );
      wx.getLocation({
        type: 'gcj02',
        success: function (res) {
          console.log(res);
          that.setData({
            latitude: res.latitude,
            longitude: res.longitude
          });
          that.autoLogin();
        }
      });
    }
    console.log(o);
    console.log("getStorage -> userInfo end");
   
  },
  onPullDownRefresh:function() {
    　　console.log('--------下拉刷新-------')
    var that=this;
    that.setData({
      start: that.data.start+10
      });
    that.initData('');
  },
  autoLogin: function () {
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
                iv: res.iv,
                latitude: that.data.latitude,
                longitude: that.data.longitude
              },
              success: function (res) {
                wx.setStorageSync('WX-SESSION-ID', res.data.value['3rd_session']);
                console.log('login success', result);
              },
              fail: function ({ errMsg }) {
                console.log('request fail', errMsg)
              }
            })
          }
        })
      }
    })
  }
})