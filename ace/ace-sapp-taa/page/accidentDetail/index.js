var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
      traffId: null,
      detail: null,
      wArray:[],
      wArrayObject:[],
      dictObject: null,
      index: null,
      carsList: null,
      cIndex : null,
      seasonList: [],
      carTypeParam: [],
      seasonParam: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      that.setData({
          traffId: options.id
      });
      that.initDict();
      
  },
  initData: function(){
      var that = this;
      util.request(cfg.server + '/taa/www/report/selectTraAccInfo', { traAccId: that.data.traffId },
          function (res) {
              if (res.status == 0) {
                 that.setData({
                     detail: res.value
                 });
                  that.convertCode(res.value.weather);
                  that.initCarType(res.value.vehicleType);
                  that.initSeason();
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
  initDict: function(){
      var that = this;
      util.request(cfg.server + '/portal/content/common/js/dict_taa.js', { },
          function (res) {
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

 convertCode: function(key){
     var that = this;
     var data = that.data.dictObject;
     var weather = data['171'];
     var wArray = [];
     var wArrayObject = [];
     for (var i in weather){
         wArray.push(weather[i].NAME);
         wArrayObject.push(weather[i]);
         if (key == weather[i].CODE){
            that.setData({
                index: i,
                wArray: wArray,
                wArrayObject: wArrayObject
            });
            return i;
         }

     }
 },
  initCarType: function(key){
      var that = this;
      var data = that.data.dictObject;
      var cars = data['172'];
      for(var i in cars){
          if (key == cars[i].CODE){
              cars[i].checked = true;
          }
      }
      that.setData({
          carsList: cars
      });
  },
  initSeason: function(){
      var that = this;
      var data = that.data.dictObject;
      var cars = data['173'];
      that.setData({
          seasonList: cars
      })
  },
  bindWeatherChange: function(e){
      var that = this;
      that.setData({
          index: e.detail.value
      });
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },
  carTypeChange(e){
      var param = [];
      param.push(e.detail.value);
      that.setData({
          carTypeParam: param
      });
  },
  seasonChange(e) {
    var that = this;
    console.log('checkbox发生change事件，携带value值为：', e.detail.value);
    var param = [];
    param.push(e.detail.value);
    that.setData({
        seasonParam: param
    });
  },
 formSubmit: function(e){
     var that = this;
     var createDate = e.detail.value.createDate;
     var weather = e.detail.value.weather;
     var vehicleType = e.detail.value.vehicleType;
     var accidentTime = e.detail.value.accidentTime;
     var injuries = e.detail.value.injuries;
     var deadthToll = e.detail.value.deadthToll;
     var cause = e.detail.value.cause;
     util.request(cfg.server + '/taa/www/traAcc/report', { id: that.data.traffId, createDate: createDate, weather: weather,  accidentTime: accidentTime, injuries: injuries, deadthToll: deadthToll},
         function (res) {
             if (res.status == 0) {
                console.log(res);
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
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    
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