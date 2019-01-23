var util = require("../../util/util.js");
var cfg = require("../../config.js");
var dateTimePicker = require('../../util/dateTimePicker.js');

var interval;
Page({

  /**
   * 页面的初始数据
   */
  data: {
      tab: 0,
      isNull: 0,
      wIndex: 0,
      cIndex: 0,
      latitude: null,
      longitude: null,
      current: [],
      mapHeight: '70vh',
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
      weatherArray: [
         
      ],
      carTypes:[],
      cjSectionId: null,
      startFlag: false,
      breakFlag: false,
      endFlag: false,
      polyline: [{
          points: [],
          color: '#4350FC',
          width: 8,
          dottedLine: false
      }],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      
  },
  selectRoad: function(){
      wx.navigateTo({
          url: '../collection/index?type=cj',
      });
  },
  changeTab: function(e){
     var that = this;
     that.setData({
         tab: e.target.dataset.index
     });
  },
  getLocation: function(e){
      var that = this;
      wx.getLocation({
          success: function(res) {
              var latitude = res.latitude;
              var longitude = res.longitude;
              var o = {
                iconPath: '../../image/icon-locate.png',
                longitude: res.longitude,
                latitude: res.latitude,
                width: 25,
                height: 25   
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
 toAddress: function (latitude, longitude){
     var that = this;
     util.request(cfg.server + '/taa/www/mapApi/tx/reversePoint', { lat: latitude, lon: longitude},
         function (res) {
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
                     success: function (res) { }
                 });
             }

         }
     );
  },
    getRoadSection: function (latitude, longitude){
      var that = this;
        util.request(cfg.server + '/taa/www/road/getCloseRoadSection', { lat: latitude, lon: longitude, radius: '10000' },
          function (res) {
              if (res.status == 0) {
                 console.log(res);
                 sectionFlag = true;
              } else {
                  
              }

          }
      );
  },
  bindWeatherChange: function(e){
      this.setData({
          wIndex: e.detail.value
      })
  },
  selectCarType: function(e){
      var that = this;
      var flag = e.currentTarget.dataset.value;
      if(flag){
          that.setData({hidden: false});
      }else{
          that.setData({ hidden: true });
      }
  },
  closeAndOpen: function(){
      var that = this;
      if (that.data.isEdit == false){
          //展开表单
          that.setData({
              isEdit : true,
              mapHeight : "0vh"
          });
         
      }else{
          that.setData({
              isEdit: false,
              mapHeight: "100vh"
          });
      }
  },

  /**
   * 事故快报选取路长信息
   */
  selectRoadMan: function(){
      wx.navigateTo({
          url: '../roadlist/index?type=kb',
      })
  },
  /**
   * 选择路段信息
   */
  selectRoadSection: function(){
      wx.navigateTo({
          url: '../collection/index?type=kb',
      });
  },
  initDateTime: function () {
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
    changeDateTime(e) {
        let name = e.currentTarget.dataset.name;
        let temp = {};
        temp[name] = e.detail.value,
            this.setData(temp);
    },
    changeDateTimeColumn(e) {
        console.log(e);
        let name = e.currentTarget.dataset.name;
        var arr = this.data[name],
            dateArr = this.data.dateTimeArray;

        arr[e.detail.column] = e.detail.value;
        dateArr[2] = dateTimePicker.getMonthDay(dateArr[0][arr[0]], dateArr[1][arr[1]]);

        this.setData({
            dateTimeArray: dateArr
        });
    },
    fotmatPicker(dataTime) {
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
    formatDT(arr) {
        return '20' + this.FN(arr[0]) + '-' + this.FN(arr[1] + 1) + '-' + this.FN(arr[2] + 1) + ' ' + this.FN(arr[3]) + ':' + this.FN(arr[4]) + ':' + this.FN(arr[5]);
    },

    FN(num) {
        return num >= 10 ? num : '0' + num;
    },

    /**
     * 字典
     */
    initDict: function () {
        var that = this;
        util.request(cfg.server + '/portal/content/common/js/dict_taa.js', {},
            function (res) {
                var oIndex = res.indexOf("=");
                var retData = res.substring(oIndex + 1, res.length);
                var retObj = JSON.parse(retData);
                var wObj = retObj['171'];
                var weather = [];
                var weatherArray = [];
                for (var i = 0; i < wObj.length; i++){
                    weather.push(wObj[i].NAME);
                    weatherArray.push(wObj[i]);
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

  xbSubmit: function(e){
      var that = this;
      var createDate = that.formatDT(e.detail.value.createDate);
      var weather = e.detail.value.weather;
      var vehicleType = e.detail.value.vehicleType;
      var mtypeList = [];
      if (that.data.sectionId == null || that.data.sectionId == undefined || that.data.sectionId == ""){
          wx.showModal({
              title: '提示',
              content: '请选择路段！',
              success: function (res) { }
          });
          return;
      }
      if (that.data.roadManId == null || that.data.roadManId == undefined || that.data.roadManId == ""){
          wx.showModal({
              title: '提示',
              content: '请选择路长！',
              success: function (res) { }
          });
          return;
      }
      if (createDate == null || createDate == undefined || createDate == ""){
          wx.showModal({
              title: '提示',
              content: '请选择快报时间！',
              success: function (res) { }
          });
          return;
      }
      if (weather == null || weather == undefined || weather == "") {
          wx.showModal({
              title: '提示',
              content: '请选择天气！',
              success: function (res) { }
          });
          return;
      }
      if (vehicleType.length < 1){
          wx.showModal({
              title: '提示',
              content: '请选择车型！',
              success: function (res) { }
          });
          return;
      }else{
          for (var i = 0; i < vehicleType.length; i++) {
              var traAccMtype = {};
              traAccMtype.vehicleType = vehicleType[i];
              mtypeList.push(traAccMtype);
          }
      }

      util.request(cfg.server + '/taa/www/traAcc/flashReport', { data: JSON.stringify({
          latitude: that.data.latitude,
          longitude: that.data.longitude,
          address: that.data.address,
          weather: weather,
          mtypeList: mtypeList,
          createDate: createDate,
          roadManId: that.data.roadManId,
          roadSectionId: that.data.sectionId,
          areaCode: that.data.areaCode
      }) },
          function (res) {
              if (res.status == 0) {
                  wx.showModal({
                      title: '提示',
                      content: res.info,
                      success: function (res) { }
                  });
              } else {
                  wx.showModal({
                      title: '提示',
                      content: res.info,
                      success: function (res) { }
                  });
              }

          }
      );
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
      var that = this;
      if (!util.is_login()) {
          wx.navigateTo({
              url: "../userinfo/index?url=../index/index&type=switchTab"
          });
          return;
      } else {
          that.getLocation();
          if (wx.getStorageSync('roadSectionId')){
              that.setData({
                  sectionFlag: true
              });
          }
          if (wx.getStorageSync('startName')){
              that.setData({
                  startFlag: true
              });
          }
          that.setData({
              roadManId: wx.getStorageSync('roadManId'),
              roadManName: wx.getStorageSync('roadManName'),
              sectionName: wx.getStorageSync('roadSectionName'),
              sectionId: wx.getStorageSync('roadSectionId'),
              tab: wx.getStorageSync('tab'),
              cjSectionId: wx.getStorageSync('roadSectionId'),
              startName: wx.getStorageSync('startName'),
              endName: wx.getStorageSync('endName')
          });
          that.initDateTime();
          that.initDict();
      }
  },

 start: function(e){
     var locateList = [];
     var that = this;
     that.setData({
         startFlag: false,
         breakFlag: true,
         endFlag: true,
     })
     interval = setInterval(() => {
         locateList.push(that.getLocate());
         
     }, 5000);
 },
break: function(e){
    clearInterval(interval);
},
end: function(e){
    var that = this;
    clearInterval(interval);
    var pointList = that.data.polyline[0].points;
    var list = [];
    for(var i in pointList){
        var roadGps = {};
        roadGps.roadSectionId = that.data.cjSectionId;
        roadGps.latitude = pointList[i].latitude;
        roadGps.longitude = pointList[i].longitude;
        list.push(roadGps);
    }
    util.request(cfg.server + '/taa/www/road/gather', { jsonData: JSON.stringify({list}) },
        function (res) {
            if (res.status == 0) {
                wx.showModal({
                    title: '提示',
                    content: res.info,
                    success: function (res) { }
                });
            } else {
                wx.showModal({
                    title: '提示',
                    content: res.info,
                    success: function (res) { }
                });
            }

        }
    );
},
/**
 * 获取地址
 */
 getLocate: function(){
     var o = {};
     var that = this;
     wx.getLocation({
         success: function (res) {
             var latitude = res.latitude;
             var longitude = res.longitude;
             o = {
                 longitude: res.longitude,
                 latitude: res.latitude
             }
             var pointList = that.data.polyline[0].points;
             pointList.push(o);
             that.setData({
                 ['polyline[0].points']: pointList
             });
         },
     });
     return o;
 },

 locateLine: function(){

 },
  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  }
})