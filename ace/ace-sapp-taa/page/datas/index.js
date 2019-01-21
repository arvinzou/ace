var Charts = require('../../util/wxcharts.js');
var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
      date: '2019-01',
      region: ['湖南省', '常德市','武陵区'],
      type: 0,
      array: ['路长1', '路长2', '路长3', '路长4'],
      objectArray: [
          {
              id: 0,
              name: '路长1'
          },
          {
              id: 1,
              name: '路长2'
          },
          {
              id: 2,
              name: '路长3'
          },
          {
              id: 3,
              name: '路长4'
          }
      ],
      index: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      that.initTrafficList();
      that.columnByAccident();
      that.columnByDiedNum();
      that.trendLine();
    
  },
  /**
   * 获取事故Top10
   */
  initTrafficList: function(){
      var that = this;
      util.request(cfg.server + '/taa/www/report/multipleReport', {
          areaCode: '430702',
          dateTimeStr: that.data.date
      },
          function (ret) {
              console.log(ret);
              wx.showModal({
                  title: '提示',
                  content: ret.info,
                  success: function (res) { }
              });
          }
      );
  },
  bindRegionChange(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
        region: e.detail.value
    });
  },
  bindMonthChange: function(e){
      this.setData({
          date: e.detail.value
      });
  },
  changeChartType: function(e){
      var that = this;
      that.setData({ type: e.target.dataset.id});
      console.log(e);
  },
  columnByAccident: function(){
      new Charts({
          canvasId: 'accidentColumn',
          type: 'column',
          categories: ['常德市', '武陵区', '鼎城区', '桃源县', '汉寿县', '石门县','临澧县','澧县','津市市'],
          series: [{
              name: '次',
              data: [60, 20, 45, 37, 4, 56, 34, 25, 46]
          }],
          yAxis: {
              format: function (val) {
                  return val + '次';
              }
          },
          extra: {
              column: {
                  width: 15
              }
          },
          width: 350,
          height: 300,
          dataLabel: false
      });
  },
  columnByDiedNum: function(){
      new Charts({
          canvasId: 'diedNumColumn',
          type: 'column',
          categories: ['常德市', '武陵区', '鼎城区', '桃源县', '汉寿县', '石门县', '临澧县', '澧县', '津市市'],
          series: [{
              name: '人',
              data: [2, 0, 3, 5, 4, 9, 4, 5, 1]
          }],
          yAxis: {
              format: function (val) {
                  return val + '人';
              }
          },
          extra: {
              column: {
                  width: 15
              }
          },
          width: 350,
          height: 300,
          dataLabel: false
      });
  },
  trendLine: function(){
      new Charts({
          canvasId: 'trendLine',
          type: 'line',
          categories: ['1月', '2月', '3月', '4月', '5月', '6月','7月','8月','9月','10月','11月','12月'],
          series: [{
              name: '2018',
              data: [15, 2, 45, 37, 4, 8, 18, 9, 23, 12, 34, 22],
              format: function (val) {
                  return val.toFixed(2) + '万';
              }
          }, {
              name: '2017',
              data: [30, 37, 65, 7, 69, 9, 12, 14, 23, 22, 14, 16],
              format: function (val) {
                  return val.toFixed(2) + '万';
              }
          }],
          yAxis: {
              title: '次',
              format: function (val) {
                  return val;
              },
              min: 0
          },
          width: 350,
          height: 300
      });
  },
    bindRoadChange(e) {
        console.log('picker发送选择改变，携带值为', e.detail.value)
        this.setData({
            index: e.detail.value
        })
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