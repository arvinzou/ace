var util = require("../../util/util.js");
var cfg = require("../../config.js");
var dateTimePicker = require('../../util/dateTimePicker.js');
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    traffId: null,
    detail: null,
    wArray: [],
    wArrayObject: [],
    dictObject: null,
    index: null,
    carsList: null,
    cIndex: null,
    seasonList: [],
    carTypeParam: [],
    seasonParam: [],
    dateTimeArray: null,
    createDate: null,
    accidentTime: null,
    startYear: 2000,
    endYear: 2050,
    sectionName: null,
    sectionId: null,
    roadManId: null,
    roadManName: null,
    linkid: '' //链接id, 0详情， 1续报
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    that.setData({
      traffId: options.id
    });
    that.initDateTime();
    that.initDict();
    // 获取linkid
    console.log(options.linkid)
    if (options.linkid != undefined && options.linkid != null) {
      that.setData({
        linkid: options.linkid
      });
    }

  },
  /**
   * 初始化日期时间
   */
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
  initData: function() {
    var that = this;
    util.request(cfg.server + '/taa/www/report/selectTraAccInfo', {
        traAccId: that.data.traffId
      },
      function(res) {
        if (res.status == 0) {
          console.log("*****************************" + res.value.accidentTime);
          app.globalData.sectionName = res.value.roadSectionName;
          app.globalData.sectionId = res.value.roadSectionId;
          app.globalData.roadManId = res.value.roadManId;
          app.globalData.roadManName = res.value.roadManName;

          that.setData({
            detail: res.value,
            createDate: that.fotmatPicker(res.value.createDate),
            accidentTime: that.fotmatPicker(res.value.accidentTime),
            sectionName: app.globalData.sectionName,
            sectionId: app.globalData.sectionId,
            roadManId: app.globalData.roadManId,
            roadManName: app.globalData.roadManName
          });

          that.convertCode(res.value.weather);
          var mtypeList = res.value.mtypeList;
          for (var i = 0; i < mtypeList.length; i++) {
            that.initCarType(mtypeList[i].vehicleType);
          }

          var causeList = res.value.causeList;
          if (causeList.length < 1) {
            that.initSeason();
          } else {
            for (var i = 0; i < causeList.length; i++) {
              that.initSeason(causeList[i].cause);
            }
          }

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
   * 初始化字典
   */
  initDict: function() {
    var that = this;
    util.request(cfg.server + '/portal/content/common/js/dict_taa.js', {},
      function(res) {
        var oIndex = res.indexOf("=");
        var retData = res.substring(oIndex + 1, res.length);
        var retObj = JSON.parse(retData);
        that.setData({
          dictObject: retObj
        });
        that.initData();

      }
    );
  },

  convertCode: function(key) {
    var that = this;
    var data = that.data.dictObject;
    var weather = data['171'];
    var wArray = [];
    var wArrayObject = [];
    for (var i in weather) {
      wArray.push(weather[i].NAME);
      wArrayObject.push(weather[i]);
      that.setData({
        wArray: wArray,
        wArrayObject: wArrayObject
      });
      if (key == weather[i].CODE) {
        that.setData({
          index: i
        });
      }

    }
  },
  initCarType: function(key) {
    var that = this;
    var data = that.data.dictObject;
    var cars = data['172'];
    for (var i in cars) {
      if (key == cars[i].CODE) {
        cars[i].checked = true;
      }
    }
    that.setData({
      carsList: cars
    });
  },
  initSeason: function(key) {
    var that = this;
    var data = that.data.dictObject;
    var cause = data['173'];
    if (key != null && key != undefined && key != '') {
      for (var i in cause) {
        if (key == cause[i].CODE) {
          cause[i].checked = true;
        }
      }
    }

    that.setData({
      seasonList: cause
    })
  },
  bindWeatherChange: function(e) {
    var that = this;
    that.setData({
      index: e.detail.value
    });
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },
  carTypeChange: function(e) {
    var that = this;
    var param = [];
    param.push(e.detail.value);
    that.setData({
      carTypeParam: param
    });
  },
  seasonChange: function(e) {
    var that = this;
    console.log('checkbox发生change事件，携带value值为：', e.detail.value);
    var param = [];
    param.push(e.detail.value);
    that.setData({
      seasonParam: param
    });
  },
  /**
   * 表单提交
   */
  formSubmit: function(e) {
    var that = this;
    var createDate = e.detail.value.createDate;
    var weather = e.detail.value.weather;
    var vehicleType = e.detail.value.vehicleType;
    var accidentTime = e.detail.value.accidentTime;
    var injuries = e.detail.value.injuries;
    var deadthToll = e.detail.value.deadthToll;
    var cause = e.detail.value.cause;
    var causeList = [];
    var mtypeList = [];
    for (var i = 0; i < cause.length; i++) {
      var traAccCause = {};
      traAccCause.cause = cause[i];
      causeList.push(traAccCause);
    }
    for (var i = 0; i < vehicleType.length; i++) {
      var traAccMtype = {};
      traAccMtype.vehicleType = vehicleType[i];
      mtypeList.push(traAccMtype);
    }
    util.request(cfg.server + '/taa/www/traAcc/report', {
        data: JSON.stringify({
          id: that.data.traffId,
          createDate: that.formatDT(createDate),
          weather: weather,
          accidentTime: that.formatDT(accidentTime),
          injuries: injuries,
          deadthToll: deadthToll,
          causeList: causeList,
          mtypeList: mtypeList,
          roadSectionId: that.data.sectionId,
          roadManId: that.data.roadManId,
          cause: '酒驾'
        })
      },
      function(res) {
        if (res.status == 0) {
          wx.showModal({
            title: '提示',
            content: res.info,
            showCancel: false,
            success: function(res) {
              wx.navigateTo({
                url: '../accidentList/index',
              });
            }
          })
        } else {
          wx.showModal({
            title: '提示',
            content: res.info,
            showCancel: false,
            success: function(res) {}
          });
        }

      }
    );
  },
  changeDateTime: function(e) {
    var name = e.currentTarget.dataset.name;
    var temp = {};
    temp[name] = e.detail.value,
      this.setData(temp);
  },
  changeDateTimeColumn: function(e) {
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
  fotmatPicker: function(dataTime) {
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
  formatDT: function(arr) {
    return '20' + this.FN(arr[0]) + '-' + this.FN(arr[1] + 1) + '-' + this.FN(arr[2] + 1) + ' ' + this.FN(arr[3]) + ':' + this.FN(arr[4]) + ':' + this.FN(arr[5]);
  },

  FN: function(num) {
    return num >= 10 ? num : '0' + num;
  },

  /**
   * 修改选择路段
   */
  selectRoadSection: function() {
    wx.navigateTo({
      url: '../collection/index?type=xb',
    });
  },


  /**
   * 修改选择路长
   */
  selectRoad: function() {
    wx.navigateTo({
      url: '../roadlist/index?type=xb',
    });
  },
  noSelectRoad: function() {

  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    var that = this;
    that.setData({
      sectionName: app.globalData.sectionName,
      sectionId: app.globalData.sectionId,
      roadManId: app.globalData.roadManId,
      roadManName: app.globalData.roadManName
    });

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

  }
})