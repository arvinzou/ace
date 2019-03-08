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
    mapHeight: '100vh',
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
    sectionStartName: null,  //事故快报中路段的地点和终点
    sectionEndName: null,
    isAdd: false,     //点击添加事故快报按钮添加快报
    showModal: false,  //是否显示路段选择模态框
    sectionList: [],
    modalSeclect: 0,   //路段模态框选择
    carTypeModal: 0,    //汽车类型选择
    carTypeStr: ""
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
   * 首页选项卡切换
   */

  changeTab: function(e) {
    var that = this;
    that.getLocation();
    that.setData({
      tab: e.target.dataset.index,
      polyline: [{
        points: [],
        color: '#4350FC',
        width: 8,
        dottedLine: false
      }]
    });

    app.globalData.startName = '';
    app.globalData.endName = null;
    app.globalData.sectionId = null;
    app.globalData.sectionName = '';
    app.globalData.tab = null;
    app.globalData.cjSectionId = null;
    app.globalData.roadManId = null;
    app.globalData.roadManName = null;
    app.globalData.collectionId = null;
    that.setData({
      sectionName: '请选择路段',
      collectionId: app.globalData.collectionId
    });
    that.initUserData();
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
        lat: latitude,  //latitude   29.014811
        lon: longitude, //longitude   111.722444
        radius: '10000'
      },
      function(res) {
        if (res.status == 0) {
          console.log(res);
          var dataList = res.data.rows;
          if (dataList.length > 0){
              that.setData({
                  sectionFlag: true,
                  sectionName: dataList[0].sectionName,
                  sectionId: dataList[0].roadSectionId,
                  roadManName: dataList[0].manName,
                  roadManId: dataList[0].roadManId,
                  sectionStartName: dataList[0].startName,
                  sectionEndName: dataList[0].endName,
                  sectionList: dataList
              });
          }else{
              wx.showModal({
                  title: '提示',
                  content: '没有获取到最近位置的路段！',
                  success: function (res) { }
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
  openCarTypeModal: function(){
      var that = this;
      that.setData({
          carTypeModal: 1,
          isEdit: false
      });
  },

/**
 * 关闭车辆选择模态框
 */
 cancelCarTypes: function(){
     var that = this;
     var carTypeList = that.data.carTypes;
     for(var i=0; i<carTypeList.length; i++){
         if (carTypeList[i].checkFlag == true){
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
    if (checkFlag == true){
        obj.checkFlag = false;
    }else{
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
 confirmCarTypes: function(e){
     var that = this;
     var carTypeStr = "";
     var that = this;
     var carTypeList = that.data.carTypes;
     for(var i=0; i<carTypeList.length; i++){
         if (carTypeList[i].checkFlag == true){
             carTypeStr += carTypeList[i].NAME;
         }
         carTypeStr = carTypeStr+" ";
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
    if (that.data.isEdit == false) {
      //展开表单
      that.setData({
        isEdit: true
      });

    } else {
      that.setData({
        isEdit: false
      });
    }
  },


    /**
     * 点击添加快报按钮
     */
    addKb: function(){
        var that = this;
        that.setData({
            isEdit: true
        })
    },
    /**
     * 快报中，重新获取路段
     */
    resetSection: function(){
        var that = this;
        that.getLocation();
        that.setData({
            showModal: true,
            isEdit: false
        })
    },
    closeModal: function(){
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
    wx.navigateTo({
      url: '../collection/index?type=kb',
    });
  },
  /**
   * 快报弹框选择路段信息
   */
  selectModalSection: function(e){
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
          modalSeclect: e.currentTarget.dataset.index
      })
     
  },
  initDateTime: function() {
    var that = this;
    var obj = dateTimePicker.dateTimePicker(that.data.startYear, that.data.endYear);
    console.log("============================" + obj.dateTime);
    that.setData({
      dateTimeArray: obj.dateTimeArray,
      createDate: obj.dateTime,
      accidentTime: obj.dateTime,
    });
    console.log(obj);
  },
  changeDateTime:function(e) {
    var name = e.currentTarget.dataset.name;
    var temp = {};
    temp[name] = e.detail.value,
      this.setData(temp);
  },
  changeDateTimeColumn:function(e) {
    console.log(e);
    var name = e.currentTarget.dataset.name;
    var arr = this.data[name],
      dateArr = this.data.dateTimeArray;

    arr[e.detail.column] = e.detail.value;
    dateArr[2] = dateTimePicker.getMonthDay(dateArr[0][arr[0]], dateArr[1][arr[1]]);

    this.setData({
      dateTimeArray: dateArr
    });
  },
  fotmatPicker:function(dataTime) {
    var val = [];
    console.log(dataTime);
    val.push(parseInt(dataTime.substring(2, 4)));
    val.push(parseInt(dataTime.substring(5, 7) - 1));
    val.push(parseInt(dataTime.substring(8, 10)) - 1);
    val.push(parseInt(dataTime.substring(11, 13)));
    val.push(parseInt(dataTime.substring(14, 16)));
    val.push(parseInt(dataTime.substring(17, 19)));
    return val;
  },
  formatDT:function(arr) {
    return '20' + this.FN(arr[0]) + '-' + this.FN(arr[1] + 1) + '-' + this.FN(arr[2] + 1) + ' ' + this.FN(arr[3]) + ':' + this.FN(arr[4]) + ':' + this.FN(arr[5]);
  },

  FN:function(num) {
    return num >= 10 ? num : '0' + num;
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
    //var createDate = that.formatDT(e.detail.value.createDate);
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
    /*if (createDate == null || createDate == undefined || createDate == "") {
     wx.showModal({
     title: '提示',
     content: '请选择快报时间！',
     success: function(res) {}
     });
     return;
     }*/
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
        if (vehicleType[i].checkFlag == true){
            checkedCarType = true;
            var traAccMtype = {};
            traAccMtype.vehicleType = vehicleType[i].CODE;
            mtypeList.push(traAccMtype);
        }
      }

      if (checkedCarType == false){
          wx.showModal({
              title: '提示',
              content: '请选择车型！',
              success: function (res) { }
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
          that.setData({
            sectionFlag: false,
            sectionName: null,
            sectionId: null,
            isEdit: false
          });
          that.getLocation();
          that.initDict();
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
        url: "../userinfo/index?url=../index/index&type=navigateTo"
      });
    } else {
      that.initDict();
      that.getLocation();
      that.initUserData(); // 重置后从定向首页，初始化用户信息     
      if (app.globalData.collectionId) {
        that.setData({
          startName: app.globalData.startName,
          endName: app.globalData.endName,
        });
        console.log("startName=========endName==============" + that.data.startName);
        that.initGpsList(app.globalData.collectionId);
      }else{
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
              roadManId: app.globalData.roadManId,
              sectionStartName: app.globalData.startName,
              sectionEndName: app.globalData.endName
          });
      }
      
    }
    //设置采集标志，判断是否显示隐藏开始，暂停，结束按钮
    that.setData({
      isCJ: app.globalData.isCJ
    })

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
                      function (res) {
                          if (res.status == 0) {
                              wx.showModal({
                                  title: '提示',
                                  content: res.info,
                                  showCancel: false,
                                  success: function (res) {

                                  }
                              });
                              wx.navigateTo({
                                  url: '../collection/index?tab=1',
                              })
                          } else {
                              wx.showModal({
                                  title: '提示',
                                  content: res.info,
                                  success: function (res) { }
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
   * 重新定位，重置数据
   */
  resetData: function() {
    var that = this;
    wx.showModal({
          title: '提示',
          content: '确定要重新获取位置信息吗？重置后您当前的信息会被清空。',
          success:function(res) {
              if (res.confirm) {
                  that.setData({
                      sectionFlag: false,
                      sectionName: null,
                      sectionId: null,
                      polyline: [{
                          points: [],
                          color: '#4350FC',
                          width: 8,
                          dottedLine: false
                      }]
                  });
                  that.getLocation();
                  that.initDict();
              } else if (res.cancel) {
                  
              }
          }
    })
  },
  /**
   * 重新定位采集信息
   */
  resetCj: function() {
    var that = this;
      wx.showModal({
          title: '提示',
          content: '确定要重新获取位置信息吗？重置后您当前的信息会被清空。',
          success:function(res) {
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