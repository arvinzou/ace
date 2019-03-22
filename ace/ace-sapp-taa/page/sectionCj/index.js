var util = require("../../util/util.js");
var cfg = require("../../config.js");
var dateTimePicker = require('../../util/dateTimePicker.js');

var interval;
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tab: 1, // 0:事故快报，1：路段采集 默认显示1
    isNull: 0,
    wIndex: 0,
    cIndex: 0,
    latitude: null,
    longitude: null,
    current: [],
    isEdit: false,
    roadManId: null,
    roadManName: null,
    dateTimeArray: null,
    createDate: null,
    startYear: 2000,
    endYear: 2050,
    dictObject: null,
    address: null,
    hidden: true,
    sectionFlag: false,
    sectionName: null,
    sectionId: null,
    areaCode: null,
    cjSectionId: null,
    isCollection: false,
    polyline: [{
      points: [

      ],
      color: '#4350FC',
      width: 8,
      dottedLine: false
    }],
    header: null,
    flaging: false,
    frequency: 0, //是否显示加减频率
    timeInterval: 5000, //采集频率以10秒为单位
    isRegist: true,
    isCJ: false, // 当前数据是否是采集数据的标志
   
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    
    if (!app.globalData.collectionId) {
      app.globalData.startName = '';
      app.globalData.endName = null;
      that.setData({
        polyline: [{
          points: [],
          color: '#4350FC',
          width: 8,
          dottedLine: false
        }]
      });
    } else {
      that.setData({
        collectionId: app.globalData.collectionId
      });
    }
    app.globalData.sectionId = null;
    app.globalData.sectionName = '';
    app.globalData.tab = null;
    app.globalData.cjSectionId = null;
    app.globalData.roadManId = null;
    app.globalData.roadManName = null;
    that.setData({
      sectionName: '请选择路段'
    });

  },
  selectRoad: function() {
    app.globalData.collectionId = null;
    wx.navigateTo({
      url: '../collection/index?type=cj',
    });
  },
  
  /**
   * 得到当前位置信息(经纬度)
   */
  getLocation: function(e) {
    var that = this;
    wx.getLocation({
      type: 'gcj02',
      success: function(res) {
        var latitude = res.latitude;
        var longitude = res.longitude;
        var o = {
          iconPath: '../../image/icon-locate.png',
          longitude: res.longitude,
          latitude: res.latitude,
          width: 28,
          height: 28
        }
        var arr = [];
        arr.push(o);
        that.setData({
          latitude: latitude,
          longitude: longitude,
          current: arr
        });
      },
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {
    var that = this;
    that.animation1 = wx.createAnimation();
    that.animation2 = wx.createAnimation();
    that.animation3 = wx.createAnimation();
  },

  /**
   * 初始化用户信息
   */
  initUserData: function() {
    var that = this;
    util.request(cfg.server + '/taa/www/register/findCustomerVo', {},
      function(res) {
        if (res.status == 0) {
          that.setData({
            userData: res.data
          });
          var userInfo = wx.getStorageSync("userinfo");
          that.setData({
            header: userInfo.avatarUrl,
            startName: app.globalData.startName
          })
          var sectionId = app.globalData.sectionId;
          if (sectionId) {
            that.setData({
              sectionFlag: true,
              sectionName: app.globalData.sectionName,
              sectionId: app.globalData.sectionId,
              tab: app.globalData.tab,
            });
          }
          var cjSectionId = app.globalData.cjSectionId;
          if (cjSectionId) {
            that.setData({
              isCollection: false,
              startName: app.globalData.startName,
              endName: app.globalData.endName,
              cjSectionId: app.globalData.cjSectionId
            });
          }
          if (app.globalData.roadManId) {
            that.setData({
              roadManId: app.globalData.roadManId,
              roadManName: app.globalData.roadManName
            });
          }
        } else {
          if (res.info == '用户尚未注册') {
            wx.navigateTo({
              url: '../regist/index',
            });
            return;
          }
        }

      }
    );
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    var that = this;
    if (!util.is_login()) {
      wx.navigateTo({
        url: "../userinfo/index?url=../sectionCj/index&type=navigateTo"
      });
    } else {
      that.getLocation();
      that.initUserData(); // 重置后从定向首页，初始化用户信息     
      if (app.globalData.collectionId) {
        that.setData({
          startName: app.globalData.startName,
          endName: app.globalData.endName,
        });
        console.log("startName=========endName==============" + that.data.startName);
        that.initGpsList(app.globalData.collectionId);
      } else {
        that.setData({
          polyline: [{
            points: [],
            color: '#4350FC',
            width: 8,
            dottedLine: false
          }],
          sectionName: app.globalData.sectionName,
          sectionId: app.globalData.sectionId,
          roadManName: app.globalData.roadManName,
          roadManId: app.globalData.roadManId
        });
      }

    }
    //设置采集标志，判断是否显示隐藏开始，暂停，结束按钮
    that.setData({
      isCJ: app.globalData.isCJ
    });

  },

  /**
   * 开始采集
   */
  start: function(e) {
    var that = this;
    that.getLocate();
    that.setData({
      breakBtn: 'breakBtn',
      activeBreak: 'activeBreak',
      flaging: true,
      frequency: 1
    });
    interval = setInterval(function() {
      that.getLocate();
      console.log("==================采集频率" + that.data.timeInterval);
    }, that.data.timeInterval);
  },
  /**
   * 暂停采集
   */
  break: function(e) {
    var that = this;
    clearInterval(interval);
    that.setData({
      activeBreak: '',
      frequency: 0
    })
  },
  /**
   * 结束采集
   */
  end: function(e) {
    var that = this;
    clearInterval(interval);
    var pointList = that.data.polyline[0].points;
    var list = [];
    for (var i in pointList) {
      var roadGps = {};
      roadGps.roadSectionId = that.data.cjSectionId;
      roadGps.latitude = pointList[i].latitude;
      roadGps.longitude = pointList[i].longitude;
      roadGps.gatherTime = pointList[i].gatherTime;
      list.push(roadGps);
    }
    that.setData({
      breakBtn: '',
      activeBreak: '',
      flaging: false,
      frequency: 0
    });

    wx.showModal({
      title: '提示',
      content: '确定提交？',
      success(res) {
        if (res.confirm) {
          util.post(cfg.server + '/taa/www/road/gather', {
              jsonData: JSON.stringify({
                list
              })
            },
            function(res) {
              if (res.status == 0) {
                wx.showModal({
                  title: '提示',
                  content: res.info,
                  showCancel: false,
                  success: function(res) {

                  }
                });
                wx.navigateTo({
                  url: '../collection/index?tab=1',
                })
              } else {
                wx.showModal({
                  title: '提示',
                  content: res.info,
                  success: function(res) {}
                });
              }

            }
          );
        } else if (res.cancel) {

        }
      }
    });
  },
  /**
   * 获取地址
   */
  getLocate: function() {
    var o = {};
    var that = this;
    var time = util.formatDateTime(new Date());
    wx.getLocation({
      type: 'gcj02',
      success: function(res) {
        var latitude = res.latitude;
        var longitude = res.longitude;
        o = {
          longitude: res.longitude,
          latitude: res.latitude,
          gatherTime: time
        }
        var pointList = that.data.polyline[0].points;
        pointList.push(o);
        that.setData({
          ['polyline[0].points']: pointList,
          latitude: res.latitude,
          longitude: res.longitude
        });
      },
    });
    return o;
  },

  locateLine: function() {

  },

  /**
   * 减少采集频率 以1秒为单位
   */
  reduce: function() {
    var that = this;
    var time = that.data.timeInterval;
    if (time > 1000) {
      time = time - 1000;
      that.setData({
        timeInterval: time
      });
      var interval = time / 1000;
      wx.showModal({
        title: '提示',
        showCancel: false,
        content: '采集频率开始以' + interval + '秒间隔采集！',
        success: function(res) {}
      });
    } else {
      wx.showModal({
        title: '提示',
        showCancel: false,
        content: '采集频率已经减少到最低1秒采集了！',
        success: function(res) {}
      });
    }
  },

  /**
   * 增加采集频率，以1秒为单位
   */
  increase: function() {
    var that = this;
    var time = that.data.timeInterval;
    if (time > 1000) {
      time = time + 1000;
      that.setData({
        timeInterval: time
      });
      var interval = time / 1000;
      wx.showModal({
        title: '提示',
        content: '采集频率开始以' + interval + '秒间隔采集！',
        success: function(res) {}
      });
    }
  },
  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {
    var that = this;
    that.setData({
      sectionFlag: false,
      sectionName: null,
      sectionId: null,
    });
    that.getLocation();
    that.initDict();
    // that.initDateTime();
  },
  
  /**
   * 重新定位采集信息
   */
  resetCj: function() {
    var that = this;
    wx.showModal({
      title: '提示',
      content: '确定要重新获取位置信息吗？重置后您当前的信息会被清空。',
      success: function(res) {
        if (res.confirm) {
          that.setData({
            startName: null,
            startName: null,
            tab: 1,
            polyline: [{
              points: [],
              color: '#4350FC',
              width: 8,
              dottedLine: false
            }],
            flaging: false,
            frequency: 0, //是否显示加减频率
            timeInterval: that.data.timeInterval, //采集频率以10秒为单位
            activeBreak: ''
          });
          that.getLocation();
        } else if (res.cancel) {

        }
      }
    })

  },

  /**
   * 获取已采集的坐标点
   */
  initGpsList: function(sectionId) {
    var that = this;
    util.request(cfg.server + '/taa/www/road/getGPSList', {
        sectionId: sectionId
      },
      function(res) {
        if (res.status == 0) {
          var pointList = that.data.polyline[0].points;
          var retList = res.value;
          for (var i = 0; i < retList.length; i++) {
            var latitude = retList[i].latitude;
            var longitude = retList[i].longitude;
            var o = {
              longitude: longitude,
              latitude: latitude
            }
            pointList.push(o);
          }

          that.setData({
            ['polyline[0].points']: pointList
          });
          // console.log("已经采集的信息==========================" + that.data.polyline[0].points);
        } else {
          wx.showModal({
            title: '提示',
            content: res.info,
            success: function(res) {}
          });
        }

      }
    );
  },
  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
  userInfo: function() {
    wx.navigateTo({
      url: '../me/index',
    })
  },

  dataInfo: function() {
    wx.navigateTo({
      url: '../datas/index',
    })
  }
})