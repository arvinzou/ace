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
    weather: [],
    weatherArray: [],
    carTypes: [],
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
    sectionStartName: null, //事故快报中路段的地点和终点
    sectionEndName: null,
    isAdd: false, //点击添加事故快报按钮添加快报
    showModal: false, //是否显示路段选择模态框
    sectionList: [],
    modalSeclect: 0, //路段模态框选择
    carTypeModal: 0, //汽车类型选择
    carTypeStr: "",
    isSrink: false //表单是否收缩
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    
      app.globalData.startName = '';
      app.globalData.endName = '';
      that.setData({
        polyline: [{
          points: [],
          color: '#4350FC',
          width: 8,
          dottedLine: false
        }]
      });
    app.globalData.sectionId = null;
    app.globalData.sectionName = '';
    app.globalData.tab = null;
    app.globalData.cjSectionId = null;
    app.globalData.roadManId = null;
    app.globalData.roadManName = null;
    that.setData({
      sectionName: '请选择路段'
    });
    that.getLocation();
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
        that.toAddress(latitude, longitude);
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
   * 获取地址信息
   */
  toAddress: function(latitude, longitude) {
    var that = this;
    util.request(cfg.server + '/taa/www/mapApi/tx/reversePoint', {
        lat: latitude,
        lon: longitude
      },
      function(res) {
        if (res.status == 0) {
          that.setData({
            address: res.result.address,
            areaCode: res.result.ad_info.adcode
          });
          that.getRoadSection(latitude, longitude);
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
   * 获取街道信息
   */
  getRoadSection: function(latitude, longitude) {
    var that = this;
    util.request(cfg.server + '/taa/www/road/getCloseRoadSection', {
        lat: latitude, //latitude   29.014811
        lon: longitude, //longitude   111.722444
        radius: '10000'
      },
      function(res) {
        if (res.status == 0) {
          console.log(res);
          var dataList = res.data.rows;
          if (dataList.length > 0) {
            that.setData({
              sectionFlag: true,
              sectionName: dataList[0].sectionName,
              sectionId: dataList[0].roadSectionId,
              roadManName: dataList[0].manName,
              roadManId: dataList[0].roadManId,
              sectionStartName: dataList[0].startName,
              sectionEndName: dataList[0].endName,
              distance: dataList[0].distance,
              sectionList: dataList
            });
          } else {
            wx.showModal({
              title: '提示',
              content: '没有获取到最近位置的路段！',
              success: function(res) {}
            });
          }

        } else {

        }

      }
    );
  },
  bindWeatherChange: function(e) {
    this.setData({
      wIndex: e.detail.value
    })
  },

  /**
   * 打开车辆选择模态框
   */
  openCarTypeModal: function() {
    var that = this;
    that.setData({
      carTypeModal: 1,
      isEdit: false
    });
  },

  /**
   * 关闭车辆选择模态框
   */
  cancelCarTypes: function() {
    var that = this;
    var carTypeList = that.data.carTypes;
    for (var i = 0; i < carTypeList.length; i++) {
      if (carTypeList[i].checkFlag == true) {
        carTypeList[i].checkFlag = false;
      }
    }
    that.setData({
      carTypeModal: 0,
      isEdit: true,
      carTypes: carTypeList,
      carTypeStr: ""
    });
  },
  /**
   * 选择车的类型
   */
  selectCarType: function(e) {
    var that = this;
    var obj = e.currentTarget.dataset.obj;
    var checkFlag = e.currentTarget.dataset.checked;
    if (checkFlag == true) {
      obj.checkFlag = false;
    } else {
      obj.checkFlag = true;
    }
    var index = e.currentTarget.dataset.index;
    var carTypeList = that.data.carTypes;
    carTypeList[index] = obj;
    that.setData({
      carTypes: carTypeList
    });
  },

  /**
   * 确定车辆选择
   */
  confirmCarTypes: function(e) {
    var that = this;
    var carTypeStr = "";
    var that = this;
    var carTypeList = that.data.carTypes;
    for (var i = 0; i < carTypeList.length; i++) {
      if (carTypeList[i].checkFlag == true) {
        carTypeStr += carTypeList[i].NAME;
      }
      carTypeStr = carTypeStr + " ";
    }
    that.setData({
      carTypeStr: carTypeStr,
      carTypeModal: 0,
      isEdit: true
    });
  },

  /**
   * 弹框缩下去
   */
  closeAndOpen: function() {
    var that = this;
    if (that.data.isSrink == false) {
      //展开表单
      that.setData({
        isSrink: true
      });

    } else {
      that.setData({
        isSrink: false
      });
    }
  },


  /**
   * 点击添加快报按钮
   */
  addKb: function() {
    var that = this;
    that.setData({
      isEdit: true
    })
  },
  /**
   * 快报中，重新获取路段
   */
  resetSection: function() {
    var that = this;
    that.getLocation();
    that.setData({
      showModal: true,
      isEdit: false
    })
  },
  closeModal: function() {
    var that = this;
    that.setData({
      showModal: false,
      isEdit: true
    });
  },
  /**
   * 事故快报选取路长信息
   */
  selectRoadMan: function() {
    wx.navigateTo({
      url: '../roadlist/index?type=kb',
    })
  },
  /**
   * 选择路段信息
   */
  selectRoadSection: function() {
    var that = this;
    that.setData({
      showModal: false,
      isEdit: true
    });
    wx.navigateTo({
      url: '../collection/index?type=kb',
    });
  },
  /**
   * 快报弹框选择路段信息
   */
  selectModalSection: function(e) {
    var that = this;
    app.globalData.sectionId = e.currentTarget.dataset.sectionid;
    app.globalData.sectionName = e.currentTarget.dataset.sectionname;
    app.globalData.roadManId = e.currentTarget.dataset.roadmanid;
    app.globalData.roadManName = e.currentTarget.dataset.roadman;
    app.globalData.startName = e.currentTarget.dataset.startname;
    app.globalData.endName = e.currentTarget.dataset.endname;

    that.setData({
      sectionName: app.globalData.sectionName,
      sectionId: app.globalData.sectionId,
      roadManName: app.globalData.roadManName,
      roadManId: app.globalData.roadManId,
      sectionStartName: app.globalData.startName,
      sectionEndName: app.globalData.endName,
      modalSeclect: e.currentTarget.dataset.index,
      distance: e.currentTarget.dataset.distance
    });

  },

  /**
   * 字典
   */
  initDict: function() {
    var that = this;
    util.request(cfg.server + '/portal/content/common/js/dict_taa.js', {},
      function(res) {
        var oIndex = res.indexOf("=");
        var retData = res.substring(oIndex + 1, res.length);
        var retObj = JSON.parse(retData);
        var wObj = retObj['171'];
        var weather = [];
        var weatherArray = [];
        for (var i = 0; i < wObj.length; i++) {
          if (wObj[i].CODE != '') {
            weather.push(wObj[i].NAME);
            weatherArray.push(wObj[i]);
          }
        }
        that.setData({
          weather: weather,
          weatherArray: weatherArray
        });

        var carObj = retObj['172'];
        that.setData({
          carTypes: carObj
        })
      }
    );
  },

  xbSubmit: function(e) {
    var that = this;
    var createDate = util.formatDateTime(new Date());
    var weather = e.detail.value.weather;
    var vehicleType = that.data.carTypes;
    var mtypeList = [];
    if (that.data.sectionId == null || that.data.sectionId == undefined || that.data.sectionId == "") {
      wx.showModal({
        title: '提示',
        content: '请选择路段！',
        success: function(res) {}
      });
      return;
    }
    if (that.data.roadManId == null || that.data.roadManId == undefined || that.data.roadManId == "") {
      wx.showModal({
        title: '提示',
        content: '请选择路长！',
        success: function(res) {}
      });
      return;
    }
   
    if (weather == null || weather == undefined || weather == "") {
      wx.showModal({
        title: '提示',
        content: '请选择天气！',
        success: function(res) {}
      });
      return;
    }
    var checkedCarType = false;
    for (var i = 0; i < vehicleType.length; i++) {
      if (vehicleType[i].checkFlag == true) {
        checkedCarType = true;
        var traAccMtype = {};
        traAccMtype.vehicleType = vehicleType[i].CODE;
        mtypeList.push(traAccMtype);
      }
    }

    if (checkedCarType == false) {
      wx.showModal({
        title: '提示',
        content: '请选择车型！',
        success: function(res) {}
      });
      return;
    }

    util.request(cfg.server + '/taa/www/traAcc/flashReport', {
        data: JSON.stringify({
          latitude: that.data.latitude,
          longitude: that.data.longitude,
          address: that.data.address,
          weather: weather,
          mtypeList: mtypeList,
          createDate: createDate,
          roadManId: that.data.roadManId,
          roadSectionId: that.data.sectionId,
          areaCode: that.data.areaCode
        })
      },
      function(res) {
        if (res.status == 0) {
          wx.showModal({
            title: '提示',
            content: res.info,
            success: function(res) {}
          });
         wx.navigateTo({
             url: '../accidentList/index',
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
  },

  /**
   * 清空表单
   */
  formReset: function() {
    var that = this;
    var that = this;
    that.setData({
      isEdit: false,
      carTypes: [],
      carTypeStr: ""
    });
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
          that.initDict();
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
        url: "../userinfo/index?url=../accidentKb/index&type=navigateTo"
      });
    } else {
      that.initDict();
      that.setData({
        sectionName: app.globalData.sectionName,
        sectionId: app.globalData.sectionId,
        roadManName: app.globalData.roadManName,
        roadManId: app.globalData.roadManId,
        sectionStartName: app.globalData.startName,
        sectionEndName: app.globalData.endName,
        distance: app.globalData.distance
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
  },
  /**
   * 重新定位，重置数据
   */
  resetData: function() {
    var that = this;
    wx.showModal({
      title: '提示',
      content: '确定要重新获取位置信息吗？重置后您当前的信息会被清空。',
      success: function(res) {
        if (res.confirm) {
          that.setData({
            sectionFlag: false,
            sectionName: null,
            sectionId: null,
          });
          that.getLocation();
          that.initDict();
        } else if (res.cancel) {

        }
      }
    })
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