var app = getApp();
var util = require("../../util/util.js");


Page({
  data: {
    hasLocation: false,
    showModalStatus: false,
    deptName: '老街中心店',
    view: {
      height: '100vh'
    },
    list: [
      {
        id: 'view',
        name: '视图容器',
        open: false,
        pages: ['view', 'scroll-view', 'swiper']
      }
    ],
    
    latitude: 29.031673,
    longitude: 111.698497,
    markers: [{
      iconPath: "../../image/location.png",
      id: '久久鸭脖中心店',
      latitude: 29.031673,
      longitude: 111.698497,
      width: 35,
      height: 45
    }],
    polyline: [{
      points: [{
        longitude: '116.481451',
        latitude: '40.006822'
      }, {
        longitude: '116.487847',
        latitude: '40.002607'
      }, {
        longitude: '116.496507',
        latitude: '40.006103'
      }],
      color: "#FF0000DD",
      width: 3,
      dottedLine: true
    }],
    circles: [{
      latitude: '40.007153',
      longitude: '116.491081',
      color: '#FF0000DD',
      fillColor: '#7cb5ec88',
      radius: 400,
      strokeWidth: 2
    }],
    controls: [{
      id: 1,
      iconPath: '../../image/record.png',
      position: {
        top: 250,
        left: 320,
        width: 50,
        height: 50
      },
      clickable: true
    }]
  },
  controltap: function (e) {
    console.log(e.controlId);
    var that = this;
    that.showModal();
  },
  bindtap: function (e) {
    console.debug("bindtap click");
    var that = this;
    that.hideModal();
  },
  markertap: function (e) {
    var that = this;
    that.setData({
      deptName: e.markerId
    });
    that.showModal();
    console.log(e)
  },
  //获取经纬度  
  getLocation: function (e) {
    console.log(e)
    var that = this
    wx.getLocation({
      success: function (res) {
        var latitude = res.latitude
        var longitude = res.longitude
        var speed = res.speed
        var accuracy = res.accuracy
        console.log("latitude:" + latitude)
        console.log("longitude:" + longitude)
        console.log("speed:" + speed)
        console.log("accuracy:" + accuracy)
        that.setData({
          latitude: latitude,
          longitude: longitude,
          scale: 28
        })
      }
    })
  },
  //根据经纬度在地图上显示  
  openLocation: function (e) {
    console.log("openLocation" + e)
    var value = e.detail.value
    wx.openLocation({
      longitude: Number(value.longitude),
      latitude: Number(value.latitude)
    })
  },
  //选择位置位置  
  chooseLocation: function (e) {
    console.log(e)
    var that = this
    wx.chooseLocation({
      success: function (res) {
        // success  
        console.log(res)
        that.setData({
          hasLocation: true,
          location: {
            longitude: res.longitude,
            latitude: res.latitude
          }
        })
      },
      fail: function () {
        // fail  
      },
      complete: function () {
        // complete  
      }
    })
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
  getUserInfo: function () {
    var that = this
    if (app.globalData.hasLogin === false) {
      wx.login({
        success: function (o) {
          _getUserInfo();
          wx.getUserInfo({
            success: function (res) {
              wx.request({
                url: 'https://demo.huacainfo.com/portal/www/authority.do',
                data: {
                  appid: 'wxa09a5be5fd228680',
                  appsecret: 'd520d29f8c26c7e3885d80b1812a8d91',
                  code: o.code,
                  encryptedData: res.encryptedData,
                  iv: res.iv
                },
                success: function (res) {
                  wx.setStorageSync('WX-SESSION-ID', res.data.value['3rd_session']);
                  console.log('request success', result);
                  that.getLocation();
                },
                fail: function ({errMsg}) {
                  console.log('request fail', errMsg)
                }
              })
            }
          })
        }
      })
    } else {
      _getUserInfo()
    }

    function _getUserInfo() {
      wx.getUserInfo({
        success: function (res) {
          that.setData({
            hasUserInfo: true,
            userInfo: res.userInfo
          })
          //that.update()
        }
      })
    }
  },
  clear: function () {
    this.setData({
      hasUserInfo: false,
      userInfo: {}
    })
  },
  onLoad: function () {
    console.log('onLoad');
    this.getUserInfo();
  },
  showModal: function () {
    // 显示遮罩层
    this.setData({
      showModalStatus: true,
      view: {
        height: '60vh'
      }
    })
  },
  hideModal: function () {
    // 隐藏遮罩层
    this.setData({
      showModalStatus: false,
      view: {
        height: '100vh'
      }
    });
  }
})


